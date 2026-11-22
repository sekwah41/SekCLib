package com.sekwah.sekclib.capabilitysync.capabilitysync;

import com.sekwah.sekclib.SekCLibNeoForge;
import com.sekwah.sekclib.capabilitysync.CapabilityEntry;
import com.sekwah.sekclib.capabilitysync.capabilitysync.annotation.Sync;
import com.sekwah.sekclib.common.capabilitysync.SyncEntry;
import com.sekwah.sekclib.common.capabilitysync.tracker.SyncTrackerSerializer;
import com.sekwah.sekclib.registries.SekCLibRegistries;
import net.minecraft.resources.Identifier;
import net.neoforged.neoforge.capabilities.EntityCapability;

import java.lang.reflect.Field;
import java.util.*;

/**
 * Currently only designed for players, though if there is demand, we can add entity syncing too.
 * <p>
 * Though entities should probably just use DataWatchers.
 */
public class CapabilitySyncRegistry {

    private static final Map<Class, SyncTrackerSerializer> CLASS_SYNC_TRACKER_SERIALIZER = new HashMap<>();

    /**
     * @param capability the capability to process
     * @param clazz      so that the fields can be pre-grabbed.
     */
    @SuppressWarnings("squid:S3011")
    static void registerPlayerCap(Identifier resourceSyncName, EntityCapability<?, ?> capability, Class<?> clazz) {
        List<Field> values = Arrays.stream(clazz.getDeclaredFields())
                .filter(value -> value.isAnnotationPresent(Sync.class))
                .sorted(Comparator.comparing(Field::getName)).toList();
        CapabilityEntry capabilityEntry = new CapabilityEntry(resourceSyncName, capability, clazz);
        SekCLibRegistries.register(resourceSyncName, capabilityEntry);
        List<String> errors = new ArrayList<>();
        int trackerId = 0;
        for (Field field : values) {
            Sync sync = field.getAnnotation(Sync.class);
            if (!CLASS_SYNC_TRACKER_SERIALIZER.containsKey(field.getType())) {
                String message = String.format("@Sync used on unsupported type %s. (Class: %s, Field: %s)", field.getType().getName(), clazz.getName(), field.getName());
                SekCLibNeoForge.LOGGER.error(message);
                errors.add(message);
            }
            try {
                field.setAccessible(true);
            } catch (UnsupportedOperationException e) {
                String message = String.format("Failed to set field accessible. (Class: %s, Field: %s)", clazz.getName(), field.getName());
                SekCLibNeoForge.LOGGER.error(message);
                errors.add(message);
            }
            try {
                SyncEntry entry = new SyncEntry(field.getName(), field, sync.minTicks(), trackerId, sync.syncGlobally(), CLASS_SYNC_TRACKER_SERIALIZER.get(field.getType()));
                capabilityEntry.addSyncEntry(entry);
                trackerId++;
            } catch (IllegalAccessException e) {
                String message = String.format("There was a problem un-reflecting (Class: %s, Field: %s)", clazz.getName(), field.getName());
                SekCLibNeoForge.LOGGER.error(message);
                errors.add(message);
            }
        }
        if (!errors.isEmpty()) {
            throw new IllegalStateException(String.join("\n", errors));
        }
    }

    static void registerSyncTrackerType(Class clazz, SyncTrackerSerializer syncTracker) {
        CLASS_SYNC_TRACKER_SERIALIZER.put(clazz, syncTracker);
    }

    public static SyncTrackerSerializer getTrackerSerializer(Class clazz) {
        return CLASS_SYNC_TRACKER_SERIALIZER.get(clazz);
    }


    /**
     * @return list of capabilities for syncing later
     */
    public static Collection<CapabilityEntry> getPlayerCapabilities() {
        return SekCLibRegistries.getValues();
    }
}
