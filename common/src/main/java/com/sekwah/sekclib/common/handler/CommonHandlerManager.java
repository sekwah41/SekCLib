package com.sekwah.sekclib.common.handler;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Handle the connectors to the mod launcher implementations
 */
public class CommonHandlerManager {

    private static CommonHandlerManager INSTANCE;

    public ILoadingHandler loadingManager;

    public static void init(ILoadingHandler loadingManager) {
        if(INSTANCE != null) {
            throw new IllegalStateException("HandlerManager has already been initialised");
        }
        INSTANCE = new CommonHandlerManager();
        INSTANCE.loadingManager = loadingManager;
    }

    public static CommonHandlerManager getInstance() {
        if(INSTANCE == null) {
            // Done this because the HandlerManager was still null in dependencies construction both on forge and fabric.
            // TODO find a better work around! :)
            try {
                Class<?> clazz = Class.forName("com.sekwah.sekclib.handler.HandlerManager");
                Method method = clazz.getMethod("init");
                method.invoke(null);
            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException | ClassNotFoundException e) {
                throw new IllegalStateException("There was an issue initialising the HandlerManager");
            }
        }
        return INSTANCE;
    }

}
