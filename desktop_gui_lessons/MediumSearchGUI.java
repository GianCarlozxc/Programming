import javax.swing.*;

public class MediumSearchGUI {
    public static void main(String[] args) {
        int target = Integer.parseInt(JOptionPane.showInputDialog("Search number in sorted array:"));
        JOptionPane.showMessageDialog(null, "Binary Search target: " + target);
    }
}