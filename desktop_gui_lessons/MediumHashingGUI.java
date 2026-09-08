import javax.swing.*;

public class MediumHashingGUI {
    public static void main(String[] args) {
        Integer[] t = new Integer[10];
        int[] keys = {11, 12, 13, 14, 15};
        for (int k : keys) t[k % 10] = k;
        String s = "Hash Table:\n";
        for (int i = 0; i < 10; i++) s += "[" + i + "] = " + t[i] + "\n";
        JOptionPane.showMessageDialog(null, s);
    }
}