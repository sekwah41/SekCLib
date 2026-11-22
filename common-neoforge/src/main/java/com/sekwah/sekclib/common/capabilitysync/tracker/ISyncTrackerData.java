package com.sekwah.sekclib.common.capabilitysync.tracker;

import com.sekwah.sekclib.common.capabilitysync.SyncEntry;

public interface ISyncTrackerData {
    SyncEntry getSyncEntry();
    Object getSendValue();
}
