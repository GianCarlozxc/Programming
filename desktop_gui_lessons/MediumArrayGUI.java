import java.util.Scanner;
import javax.swing.JOptionPane;

public class MediumArrayGUI {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        String[] menu = {
            "Burger - ₱80",
            "Pizza - ₱120",
            "Fries - ₱50",
            "Chicken - ₱100",
            "Pasta - ₱90"
        };

        int[] prices = {80, 120, 50, 100, 90};

        String menuList = "RESTAURANT MENU\n\n";

        for (int i = 0; i < menu.length; i++) {
            menuList += (i + 1) + ". " + menu[i] + "\n";
        }

        JOptionPane.showMessageDialog(null, menuList);

        System.out.print("Enter your choice (1-5): ");
        int choice = input.nextInt();

        if (choice >= 1 && choice <= 5) {
            JOptionPane.showMessageDialog(null,
                    "You selected:\n" +
                    menu[choice - 1] +
                    "\n\nPrice: ₱" + prices[choice - 1]);
        } else {
            JOptionPane.showMessageDialog(null,
                    "Invalid choice!");
        }

        input.close();
    }
}