import javax.swing.*;

public class AdvancedListADTGUI {
    public static void main(String[] args) {
        Object[] data = new Object[10];
        int count = 0;
        data[count++] = "Apple"; data[count++] = "Banana"; data[count++] = "Cherry";
        for (int i = count; i > 1; i--) data[i] = data[i - 1];
        data[1] = "Orange"; count++;
        String s = "";
        for (int i = 0; i < count; i++) s += data[i] + " ";
        JOptionPane.showMessageDialog(null, "List Elements (Slide 35):\n" + s);
    }
}