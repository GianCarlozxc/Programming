import javax.swing.*;

public class MediumLinkedListGUI {
    static class Node { int d; Node n; Node(int v) { d = v; } }
    public static void main(String[] args) {
        Node h = new Node(10); h.n = new Node(20); h.n.n = new Node(30);
        String s = "Head -> "; Node cur = h;
        while (cur != null) { s += "[" + cur.d + "] -> "; cur = cur.n; }
        JOptionPane.showMessageDialog(null, s + "NULL");
    }
}