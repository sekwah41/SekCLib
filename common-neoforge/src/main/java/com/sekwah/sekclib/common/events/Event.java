package com.sekwah.sekclib.common.events;

/**
 *
 * @param <T> the type of event fired
 */
public abstract class Event<T> {

    public abstract void register(T listener);

}
