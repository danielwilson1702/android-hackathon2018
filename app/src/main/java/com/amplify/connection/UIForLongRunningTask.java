package com.amplify.connection;

public interface UIForLongRunningTask {
    void showUIForLongRunningTask(boolean show, int text);
    void hideUIForLongRunningTask();
    void showUIForLongRunningTask(boolean show, int containerToHide, int text);
    void hideUIForLongRunningTask(int containerToShow);
    void updateUIForLongRunningTask(int text);
}
