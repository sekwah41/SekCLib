package com.sekwah.sekclib.common.handler;

/**
 * Handle the connectors to the mod launcher implementations
 */
public class HandlerManager {

    private final ILoadingManager loadingManager
            ;
    private final INetworkHandler networkHandler;

    public HandlerManager(ILoadingManager loadingManager, INetworkHandler networkHandler) {
        this.loadingManager = loadingManager;
        this.networkHandler = networkHandler;
    }

}
