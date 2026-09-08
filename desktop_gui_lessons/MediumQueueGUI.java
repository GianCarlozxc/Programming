import java.util.LinkedList;
import java.util.Queue;
import javax.swing.*;

public class MediumQueueGUI {
    public static void main(String[] args) {
        int count = Integer.parseInt(JOptionPane.showInputDialog("Number of print jobs:"));
        Queue<String> q = new LinkedList<>();
        for (int i = 0; i < count; i++) q.add(JOptionPane.showInputDialog("Document name " + (i + 1) + ":"));
        String log = "FIFO Print Queue:\n";
        while (!q.isEmpty()) log += "Printing: " + q.poll() + "\n";
        JOptionPane.showMessageDialog(null, log);
    }
}