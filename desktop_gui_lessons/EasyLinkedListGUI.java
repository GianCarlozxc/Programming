import java.util.LinkedList;
import javax.swing.*;

public class EasyLinkedListGUI {
    public static void main(String[] args) {
        LinkedList<String> list = new LinkedList<>();
        list.add("A"); list.add("B"); list.add("C"); list.add("D");
        JOptionPane.showMessageDialog(null, "Linked List (Slide 32):\n" + list);
    }
}