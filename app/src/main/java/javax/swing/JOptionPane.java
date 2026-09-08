package javax.swing;

import com.dsa.app.DialogBridge;

public class JOptionPane {

    public static void showMessageDialog(Object parentComponent, Object message) {
        String msg = message != null ? message.toString() : "null";
        System.out.println("[JOptionPane Dialog]:\n" + msg);
        DialogBridge.showMessage(msg);
    }

    public static String showInputDialog(Object message) {
        return showInputDialog(null, message);
    }

    public static String showInputDialog(Object parentComponent, Object message) {
        String msg = message != null ? message.toString() : "null";
        System.out.println("[JOptionPane Input Prompt]: " + msg);
        return DialogBridge.showInput(msg);
    }
}
