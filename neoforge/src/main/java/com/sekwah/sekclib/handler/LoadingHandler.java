package com.sekwah.sekclib.handler;

import com.sekwah.sekclib.common.handler.ILoadingHandler;
import net.neoforged.fml.loading.progress.ProgressMeter;
import net.neoforged.fml.loading.progress.StartupNotificationManager;

public class LoadingHandler implements ILoadingHandler {

    @Override
    public void addMessage(String message) {
        StartupNotificationManager.addModMessage(message);
    }

    @Override
    public IProgressBar addProgressBar(String name, int count) {
        return new ProgressBar(StartupNotificationManager.addProgressBar(name, count));
    }

    static class ProgressBar implements IProgressBar {

        private final ProgressMeter progressMeter;

        public ProgressBar(ProgressMeter progressMeter) {
            this.progressMeter = progressMeter;
        }

        @Override
        public void setProgress(int progress) {
            this.progressMeter.setAbsolute(progress);
        }

        @Override
        public void increment() {
            this.progressMeter.increment();
        }

        @Override
        public void pop() {
            StartupNotificationManager.popBar(this.progressMeter);
        }
    }

}
