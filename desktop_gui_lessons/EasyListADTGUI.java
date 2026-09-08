import java.util.ArrayList;
import javax.swing.*;

public class EasyListADTGUI {
    public static void main(String[] args) {
        ArrayList<String> f = new ArrayList<>();
        f.add("Apple"); f.add("Banana"); f.add("Cherry");
        f.set(1, "Blueberry"); f.remove("Apple");
        JOptionPane.showMessageDialog(null, "List ADT:\n" + f);
    }
}