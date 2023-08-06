package com.sekwah.sekclib.common.capabilitysync.tracker;

/**
 * If this is not implemented for the tracker it will just straight up replace the value.
 */
public interface SyncTrackerUpdater<T> {
    T updateTracker(T newData, T oldData);
}
