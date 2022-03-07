---
sidebar_position: 3
description: Info on the @Sync Annotation and how to use it
---

# @Sync Annotation
This allows for the automatic syncing of data from the server to the client for entity capabilities.

If there is demand we will also be looking into supporting other capability types if requested.

The main interfaces will be kept the same though we will be optimising and improving the underlying code.
This should not require any changes to your mod code though we will release major version bumps if it does make any incompatible changes.
We will also announce on our [Discord](https://discord.sekwah.com/) server if there are any major changes.

You can make edits on the client side, though whenever the values are broadcasted from the server it will overwrite whatever you  have set locally.
This can be used if you can accurately update client side but want to just re-sync it on an interval from the server.

## Registering a Capability for Syncing
To register a capability for syncing you will need to listen to the `RegisterCapabilitySyncEvent` on the `MOD` event bus.

[Example Code](https://github.com/sekwah41/Naruto-Mod/blob/2a87ced2daa10cf9eaf3c1f2e480a2ea5ddedd3d/src/main/java/com/sekwah/narutomod/NarutoMod.java#L91-L93)
```java
@Mod.EventBusSubscriber(modid = NarutoMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class NarutoSyncTrackers {
    @SubscribeEvent
    public static void registerCapabilitySync(RegisterCapabilitySyncEvent event) {
        /*
         * You do not need to use the same resource location as the capability though it makes it easier to keep track of
         * Provide the capability reference you would pass to .getCapability and the class that implements it.
         * This is the one you plan to use the @Sync annotations in.
         */
        event.registerPlayerCap(new ResourceLocation(NarutoMod.MOD_ID, "ninja_data"), NinjaCapabilityHandler.NINJA_DATA, NinjaData.class);
    }
}
```

## @Sync Attributes
You can further customise the way that the [@Sync](https://github.com/sekwah41/SekCLib/blob/master/src/main/java/com/sekwah/sekclib/capabilitysync/capabilitysync/annotation/Sync.java) annotation works by adding attributes to the annotation.
For the most part the names should be relatively self-explanatory.

### int minTicks
Default: 3

This is the minimum number of ticks between syncs.
This is to prevent syncing too often, a good example would be large visual changes which could be abused to cause epilepsy or generally would be visually unpleasant.

### boolean syncGlobally
If the data being synced should be available to all players.
This is useful for data needed for visual changes or other info that all players may need to know for your other features.
If this is false, the values will never be changes from their default values unless the capability is attached to their player.

A good example where you may not want to globally sync is if you are syncing a player's energy/mana.
This will avoid third party mods being able to retrieve that data and create extra feedback as well as unnecessary packets to all surrounding players if you are syncing a large object.

If the capability you are syncing only ever attached to entities, then you will need this to be set to true otherwise no data will ever be sent to a client. If you do not want this then leave the @Sync annotation off as it won't do anything.

## Registering Custom @Sync Types
If you want to sync a type we do not currently support please raise a PR on [Github](https://github.com/sekwah41/SekCLib) so we can officially support it.
Though if you plan to sync a custom type you can do so by registering it with the `RegisterSyncTrackerTypeEvent` event.
You will also need to make a custom class so that the mod knows how to add and retrieve the data from the `FriendlyByteBuf`.

### [Registering Example](https://github.com/sekwah41/Naruto-Mod/blob/2a87ced2daa10cf9eaf3c1f2e480a2ea5ddedd3d/src/main/java/com/sekwah/narutomod/trackers/NarutoSyncTrackers.java)
```java
@Mod.EventBusSubscriber(modid = NarutoMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class NarutoSyncTrackers {
    @SubscribeEvent
    public static void registerSyncTrackerEvent(RegisterSyncTrackerTypeEvent event) {
        event.registerSyncTracker(ToggleAbilityData.class, new ToggleAbilityDataSyncTracker());
        event.registerSyncTracker(DoubleJumpData.class, new DoubleJumpDataSyncTracker());
    }
}
```

### Basic Example of SyncTrackerSerializer
```java
public class ResourceLocationSyncTracker implements SyncTrackerSerializer<ResourceLocation> {

    @Override
    public void encode(ResourceLocation objectToSend, FriendlyByteBuf outBuffer) {
        outBuffer.writeUtf(objectToSend.toString());
    }

    @Override
    public ResourceLocation decode(FriendlyByteBuf inBuffer) {
        return new ResourceLocation(inBuffer.readUtf());
    }
}
```

### SyncTrackerClone
This is used to clone the data when copies of the last sent data are being used.
Without this the library would not be able to tell if the data has changed as it would be using a reference to compare to the original.

Example of a `SyncTrackerSerializer` & `SyncTrackerClone` implementation
```java
public class ToggleAbilityDataSyncTracker implements SyncTrackerSerializer<ToggleAbilityData>, SyncTrackerClone<ToggleAbilityData> {

    @Override
    public void encode(ToggleAbilityData objectToSend, FriendlyByteBuf outBuffer) {
        outBuffer.writeInt(objectToSend.getAbilitiesHashSet().size());
        for (ResourceLocation ability : objectToSend.getAbilitiesHashSet()) {
            outBuffer.writeUtf(ability.toString());
        }
    }

    @Override
    public ToggleAbilityData decode(FriendlyByteBuf inBuffer) {
        final int size = inBuffer.readInt();
        ToggleAbilityData data = new ToggleAbilityData(size);
        for(int i = 0; i < size; i++) {
            data.getAbilitiesHashSet().add(new ResourceLocation(inBuffer.readUtf()));
        }
        return data;
    }

    @Override
    public ToggleAbilityData clone(ToggleAbilityData data) {
        ToggleAbilityData cloned = new ToggleAbilityData(data.getAbilitiesHashSet().size());
        cloned.getAbilitiesHashSet().addAll(data.getAbilitiesHashSet());
        return cloned;
    }
}
```

### SyncTrackerUpdater
For some objects you may not want to replace the object every time to keep references to other objects you have created. While we do not currently have an example though the interface for
[SyncTrackerUpdater](https://github.com/sekwah41/SekCLib/blob/master/src/main/java/com/sekwah/sekclib/capabilitysync/capabilitysync/tracker/SyncTrackerUpdater.java)
should be pretty self-explanatory.

You likely will not need to use this though it is there just in case.
