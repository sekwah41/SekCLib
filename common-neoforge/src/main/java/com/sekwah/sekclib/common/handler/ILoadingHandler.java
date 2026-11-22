package com.sekwah.sekclib.common.handler;

/**
 * Handle loading errors or adding information to the loading screen
 *
 * Currently fabric doesn't have a loading screen so this will be a forge only feature.
 */
public interface ILoadingHandler {
    void addMessage(String message);

    IProgressBar addProgressBar(String name, int count);

    interface IProgressBar {
        void setProgress(int progress);
        void increment();

        /**
         * Remove the current progress bar
         */
        void pop();
    }
}
