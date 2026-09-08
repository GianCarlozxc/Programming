import javax.swing.*;

public class AdvancedLinkedListGUI {
    static class Node { String d; Node n; Node(String v) { d = v; } }
    public static void main(String[] args) {
        Node h = new Node("A"); h.n = new Node("B"); h.n.n = new Node("C"); h.n.n.n = new Node("D");
        Node cur = h;
        while (cur.n != null && !cur.n.d.equals("C")) cur = cur.n;
        if (cur.n != null) cur.n = cur.n.n;
        String s = "After deleting 'C': Head -> "; cur = h;
        while (cur != null) { s += "[" + cur.d + "] -> "; cur = cur.n; }
        JOptionPane.showMessageDialog(null, s + "NULL");
    }
}