import java.util.LinkedList;
import java.util.Queue;
import javax.swing.*;

public class EasyQueueGUI {
    public static void main(String[] args) {
        Queue<String> q = new LinkedList<>();
        q.add("Passenger Jack"); q.add("Passenger Rose"); q.add("Passenger Ed");
        JOptionPane.showMessageDialog(null, "First: " + q.peek() + "\nBoarded: " + q.poll() + "\nNext: " + q.peek());
    }
}