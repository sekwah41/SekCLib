package com.sekwah.sekclib.handler;

import com.sekwah.sekclib.common.handler.CommonHandlerManager;
import com.sekwah.sekclib.common.handler.IHandlerManager;


public class HandlerManager implements IHandlerManager {
    public static void init() {
        CommonHandlerManager.init(new LoadingHandler());
    }
}
