package com.sekwah.sekclib.network.datamanager;

import com.google.common.collect.Maps;

import java.util.Map;
import java.util.concurrent.Callable;

/**
 * Sort of similar to EntityDataManager but designed to handle any custom data
 * @param <T>
 */
public abstract class CustomDataManager<T> {

    private static final Map<Class, Integer> NEXT_ID_MAP = Maps.newHashMap();

    private Callable<? extends CustomDataTracker<T>> factory;

    public CustomDataManager(Callable<? extends CustomDataTracker<T>> factory) {
        this.factory = factory;
    }

    /**
     * Create a new tracker object.
     * @param target
     * @return returns null if there was a problem creating the tracker.
     */
    public CustomDataTracker<T> makeCustomDataTracker(T target) {
        try {
            CustomDataTracker<T> tracker = factory.call();
            tracker.setTarget(target);
            tracker.setDataManager(this);
            return tracker;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public abstract CustomDataTracker<T> getFromIdentifier(int id);

}
