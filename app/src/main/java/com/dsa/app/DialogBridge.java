package com.dsa.app;

public class DialogBridge {

    public interface Listener {
        void onMessage(String message);
        String onInput(String prompt);
    }

    private static Listener listener;

    public static void setListener(Listener l) {
        listener = l;
    }

    public static void showMessage(String message) {
        if (listener != null) {
            listener.onMessage(message);
        }
    }

    public static String showInput(String prompt) {
        if (listener != null) {
            return listener.onInput(prompt);
        }
        return "";
    }
}
