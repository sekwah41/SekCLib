package com.sekwah.sekclib.capabilitysync.capabilitysync.tracker;

public interface SyncTrackerUpdater<T> {
    T updateTracker(T newData, T oldData);
}
