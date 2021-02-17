package com.sekwah.sekclib.datamanager;

import com.google.common.collect.Maps;
import net.minecraft.network.datasync.EntityDataManager;

import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CustomDataManager {

    private final Map<Integer, EntityDataManager.DataEntry<?>> entries = Maps.newHashMap();
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    protected boolean dirty;

    public CustomDataManager() {

    }

}
