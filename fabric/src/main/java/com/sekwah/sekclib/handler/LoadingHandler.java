package com.sekwah.sekclib.handler;

import com.sekwah.sekclib.common.handler.ILoadingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Currently fabric does not have a progress bar or loading progress of any kind. Possibly add it?
 *
 * If there are any mods that implement this please open a ticket, and we could possibly add support for it.
 */
class LoadingHandler implements ILoadingHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger("SekCLib:LoadingHandler");

    @Override
    public void addMessage(String message) {
        LOGGER.info(message);
    }

    @Override
    public IProgressBar addProgressBar(String name, int count) {
        return new DummyProgressBar();
    }

    static class DummyProgressBar implements IProgressBar {

        @Override
        public void setProgress(int progress) {

        }

        @Override
        public void increment() {

        }

        @Override
        public void pop() {
        }
    }

}
