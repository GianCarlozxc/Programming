import javax.swing.*;

public class AdvancedSearchGUI {
    public static void main(String[] args) {
        char[] a = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        int low = 0, high = a.length - 1;
        String log = "Binary Search Trace for 'J' (Slides 19-21):\n";
        while (low <= high) {
            int mid = low + (high - low) / 2;
            log += "Mid: '" + a[mid] + "'\n";
            if (a[mid] == 'J') break;
            else if ('J' < a[mid]) high = mid - 1;
            else low = mid + 1;
        }
        JOptionPane.showMessageDialog(null, log + "Match found at index 9!");
    }
}