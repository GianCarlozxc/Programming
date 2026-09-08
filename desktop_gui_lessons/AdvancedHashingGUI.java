import javax.swing.*;

public class AdvancedHashingGUI {
    public static void main(String[] args) {
        Integer[] t = new Integer[10];
        int[] keys = {11, 12, 13, 14, 15, 24};
        String log = "Linear Probing (Slide 33):\n";
        for (int k : keys) {
            int idx = k % 10;
            while (t[idx] != null) idx = (idx + 1) % 10;
            t[idx] = k;
            log += "Key " + k + " -> Slot [" + idx + "]\n";
        }
        JOptionPane.showMessageDialog(null, log);
    }
}