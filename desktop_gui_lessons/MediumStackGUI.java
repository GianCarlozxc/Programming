import java.util.Stack;
import javax.swing.*;

public class MediumStackGUI {
    public static void main(String[] args) {
        int count = Integer.parseInt(JOptionPane.showInputDialog("How many numbers to push?"));
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < count; i++) {
            stack.push(Integer.parseInt(JOptionPane.showInputDialog("Enter number " + (i + 1) + ":")));
        }
        String res = "Popped in LIFO order:\n";
        while (!stack.isEmpty()) res += stack.pop() + " ";
        JOptionPane.showMessageDialog(null, res);
    }
}