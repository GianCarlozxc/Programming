import javax.swing.*;

public class AdvancedQueueGUI {
    public static void main(String[] args) {
        String[] q = new String[5];
        int front = 0, rear = -1, count = 0;
        q[++rear] = "Doc1.pdf"; count++;
        q[++rear] = "Invoice.pdf"; count++;
        q[++rear] = "Report.xlsx"; count++;
        String log = "Queue Processing:\n";
        while (count > 0) { log += "Completed: " + q[front++] + "\n"; count--; }
        JOptionPane.showMessageDialog(null, log);
    }
}