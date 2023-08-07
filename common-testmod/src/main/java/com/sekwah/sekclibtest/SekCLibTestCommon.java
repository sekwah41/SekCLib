package com.sekwah.sekclibtest;

import com.sekwah.sekclib.common.handler.CommonHandlerManager;
import com.sekwah.sekclib.common.handler.ILoadingHandler;

public class SekCLibTestCommon {

    public SekCLibTestCommon() {
        CommonHandlerManager.getInstance().loadingManager.addMessage("Hello from common!");
        ILoadingHandler.IProgressBar bar = CommonHandlerManager.getInstance().loadingManager.addProgressBar("Counting", 5);
        // loop 5 times and sleep for 1 second each time.
        for (int i = 0; i < 5; i++) {
            CommonHandlerManager.getInstance().loadingManager.addMessage("Loading " + i);
            bar.increment();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        bar.pop();
    }
}
