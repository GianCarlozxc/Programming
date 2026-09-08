import java.util.Scanner;
import javax.swing.JOptionPane;

public class AdvancedRestaurant {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        String[] menu = {
            "Burger",
            "Pizza",
            "Fries",
            "Chicken",
            "Pasta"
        };

        int[] prices = {80, 120, 50, 100, 90};
        int[] orders = new int[5];

        String menuList = "RESTAURANT MENU\n\n";

        for (int i = 0; i < menu.length; i++) {
            menuList += (i + 1) + ". " + menu[i] +
                    " - ₱" + prices[i] + "\n";
        }

        JOptionPane.showMessageDialog(null, menuList);

        System.out.print("How many items do you want to order? ");
        int quantity = input.nextInt();

        int total = 0;
        String receipt = "ORDER RECEIPT\n\n";

        for (int i = 0; i < quantity; i++) {

            System.out.print("Enter item number: ");
            int choice = input.nextInt();

            if (choice >= 1 && choice <= 5) {

                orders[choice - 1]++;

                total += prices[choice - 1];

                receipt += menu[choice - 1] +
                        " - ₱" + prices[choice - 1] + "\n";

            } else {
                System.out.println("Invalid choice!");
                i--;
            }
        }

        receipt += "\nTotal: ₱" + total;

        JOptionPane.showMessageDialog(null, receipt);

        input.close();
    }
}
