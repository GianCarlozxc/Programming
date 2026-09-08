import javax.swing.*;

public class EasyArrayGUI {
    public static void main(String[] args) {
        int[] numbers = {10, 20, 30, 40, 50};
        String result = "";
        for (int i = 0; i < numbers.length; i++) {
            result += "Element at index [" + i + "]: " + numbers[i] + "\n";
        }
        JOptionPane.showMessageDialog(null, "Array Elements:\n" + result);
    }
}