package com.sekwah.sekclib.capabilitysync.capabilitysync;

import com.sekwah.sekclib.capabilitysync.CapabilityEntry;
import com.sekwah.sekclib.capabilitysync.capabilitysync.annotation.Sync;
import com.sekwah.sekclib.common.capabilitysync.SyncEntry;
import com.sekwah.sekclib.common.capabilitysync.tracker.SyncTracker;
import com.sekwah.sekclib.common.capabilitysync.tracker.SyncTrackerSerializer;
import com.sekwah.sekclib.common.capabilitysync.tracker.implemented.*;
import dev.onyxstudios.cca.api.v3.component.Component;
import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;

import java.lang.reflect.Field;
import java.util.*;

public final class CapabilitySyncRegistry {
    private static final Map<Class<?>, SyncTrackerSerializer<?>> SERIALIZERS = new HashMap<>();
    private static final Map<ResourceLocation, CapabilityEntry> BY_KEY = new LinkedHashMap<>();
    private static boolean initialized;
    private static boolean extensionsInitialized;

    private CapabilitySyncRegistry() {
    }

    public static synchronized void registerTracker(Class<?> type, SyncTrackerSerializer<?> serializer) {
        SERIALIZERS.put(type, serializer);
    }

    public static synchronized SyncTrackerSerializer<?> getTrackerSerializer(Class<?> type) {
        return SERIALIZERS.get(type);
    }

    public static synchronized void register(ResourceLocation id, ComponentKey<? extends Component> key, Class<?> clazz) {
        if (BY_KEY.containsKey(id)) throw new IllegalArgumentException("Duplicate capability sync entry: " + id);
        CapabilityEntry entry = new CapabilityEntry(id, key, clazz);
        List<Field> fields = Arrays.stream(clazz.getDeclaredFields()).filter(f -> f.isAnnotationPresent(Sync.class)).sorted(Comparator.comparing(Field::getName)).toList();
        int trackerId = 0;
        for (Field field : fields) {
            Sync sync = field.getAnnotation(Sync.class);
            SyncTrackerSerializer<?> serializer = SERIALIZERS.get(field.getType());
            if (serializer == null)
                throw new IllegalStateException("@Sync used on unsupported type " + field.getType().getName() + " (" + clazz.getName() + "." + field.getName() + ")");
            if (!java.lang.reflect.Modifier.isPublic(field.getModifiers()) || !java.lang.reflect.Modifier.isPublic(clazz.getModifiers()))
                throw new IllegalStateException("@Sync fields and their component class must be public: " + clazz.getName() + "." + field.getName());
            try {
                entry.addSyncEntry(new SyncEntry(field.getName(), field, sync.minTicks(), trackerId++, sync.syncGlobally(), serializer));
            } catch (IllegalAccessException e) {
                throw new IllegalStateException("Could not access synced field " + field, e);
            }
        }
        BY_KEY.put(id, entry);
    }

    public static synchronized List<CapabilityEntry> entries() {
        return List.copyOf(BY_KEY.values());
    }

    public static synchronized Collection<CapabilityEntry> getPlayerCapabilities() {
        return entries();
    }

    public static synchronized int getId(CapabilityEntry entry) {
        return new ArrayList<>(BY_KEY.values()).indexOf(entry);
    }

    public static synchronized CapabilityEntry getValue(int id) {
        return new ArrayList<>(BY_KEY.values()).get(id);
    }

    public static synchronized void initializeRuntime() {
        if (initialized) return;
        initialized = true;
        registerTracker(int.class, new IntSyncTracker());
        registerTracker(float.class, new FloatSyncTracker());
        registerTracker(String.class, new StringSyncTracker());
        registerTracker(boolean.class, new BoolSyncTracker());
        registerTracker(byte.class, new ByteSyncTracker());
        registerTracker(Vec3.class, new Vec3SyncTracker());
        registerTracker(ResourceLocation.class, new ResourceLocationSyncTracker());
    }

    public static synchronized void initializeExtensions() {
        if (extensionsInitialized) return;
        extensionsInitialized = true;
        RegisterSyncTrackerTypeCallback.EVENT.invoker().register(new RegisterSyncTrackerTypeEvent());
        RegisterCapabilitySyncCallback.EVENT.invoker().register(new RegisterCapabilitySyncEvent());
    }

    public static SyncTracker newTracker(SyncEntry entry) {
        return new SyncTracker(entry);
    }
}
