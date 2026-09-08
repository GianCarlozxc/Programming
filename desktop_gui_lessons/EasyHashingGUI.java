import javax.swing.*;

public class EasyHashingGUI {
    public static void main(String[] args) {
        int[] v = {11, 12, 13, 14, 15};
        String s = "H(x) = x % 10 (Slide 33):\n";
        for (int x : v) s += x + " -> Slot " + (x % 10) + "\n";
        JOptionPane.showMessageDialog(null, s);
    }
}