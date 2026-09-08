import javax.swing.*;

public class AdvancedStackGUI {
    public static void main(String[] args) {
        String input = JOptionPane.showInputDialog("Enter text to reverse (e.g. ABCD):");
        if (input == null || input.isEmpty()) input = "ABCD";
        char[] stack = new char[input.length()];
        int top = -1;
        for (int i = 0; i < input.length(); i++) stack[++top] = input.charAt(i);
        String reversed = "";
        while (top >= 0) reversed += stack[top--];
        JOptionPane.showMessageDialog(null, "Original: " + input + "\nReversed: " + reversed);
    }
}