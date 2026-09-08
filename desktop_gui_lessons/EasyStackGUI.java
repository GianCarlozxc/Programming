import java.util.Stack;
import javax.swing.*;

public class EasyStackGUI {
    public static void main(String[] args) {
        Stack<String> plates = new Stack<>();
        plates.push("Plate 1");
        plates.push("Plate 2");
        plates.push("Plate 3");
        String msg = "Top: " + plates.peek() + "\nPopped: " + plates.pop() + "\nNew Top: " + plates.peek();
        JOptionPane.showMessageDialog(null, msg);
    }
}