import java.util.ArrayList;
import javax.swing.*;

public class MediumListADTGUI {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("Red"); list.add("Blue");
        int pos = Integer.parseInt(JOptionPane.showInputDialog("Current: " + list + "\nInsert position (0-2):"));
        String val = JOptionPane.showInputDialog("Value:");
        list.add(pos, val);
        JOptionPane.showMessageDialog(null, "Updated List: " + list);
    }
}