package com.dsa.app;

import com.dsa.lessons.*;
import java.util.*;

public class LessonRepository {

    public static List<LessonItem> getAllLessons() {
        List<LessonItem> list = new ArrayList<>();

        // 1. Array Data Structure
        list.add(new LessonItem(
                1,
                "LESSON 1",
                "Slide 31",
                "Array Data Structure",
                "Linear contiguous memory collection. Offset calculations & array operations.",
                "An array is a collection of items stored at contiguous memory locations.\n" +
                "Formula: Memory Address = Base_Address + (Index * Element_Size_In_Bytes)\n\n" +
                "• Easy: Print array elements.\n" +
                "• Medium: Read elements and find the largest.\n" +
                "• Advance: Memory offset calculation (Slide 31) and bubble sort.",
                // Console Easy
                "public class EasyArray {\n" +
                "    public static void main(String[] args) {\n" +
                "        int[] numbers = {10, 20, 30, 40, 50};\n\n" +
                "        System.out.println(\"Array Elements:\");\n" +
                "        for (int i = 0; i < numbers.length; i++) {\n" +
                "            System.out.println(numbers[i]);\n" +
                "        }\n" +
                "    }\n" +
                "}",
                "Array Elements:\n10\n20\n30\n40\n50",
                // Console Medium
                "import java.util.Scanner;\n\n" +
                "public class MediumArray {\n" +
                "    public static void main(String[] args) {\n" +
                "        Scanner input = new Scanner(System.in);\n" +
                "        System.out.print(\"Enter number of elements: \");\n" +
                "        int size = input.nextInt();\n" +
                "        int[] numbers = new int[size];\n\n" +
                "        for (int i = 0; i < numbers.length; i++) {\n" +
                "            System.out.print(\"Enter number \" + (i + 1) + \": \");\n" +
                "            numbers[i] = input.nextInt();\n" +
                "        }\n\n" +
                "        int largest = numbers[0];\n" +
                "        for (int i = 1; i < numbers.length; i++) {\n" +
                "            if (numbers[i] > largest) largest = numbers[i];\n" +
                "        }\n" +
                "        System.out.println(\"Largest number: \" + largest);\n" +
                "        input.close();\n" +
                "    }\n" +
                "}",
                "Enter number of elements: 5\nInputs: [10, 25, 99, 40, 55]\nLargest number: 99",
                "Enter array numbers (comma-separated)",
                "10, 25, 99, 40, 55",
                // Console Advance
                "public class AdvancedArray {\n" +
                "    public static void main(String[] args) {\n" +
                "        int[] numbers = {50, 20, 40, 10, 30};\n" +
                "        int baseAddress = 200;\n\n" +
                "        System.out.println(\"Memory Layout (Slide 31):\");\n" +
                "        for (int i = 0; i < numbers.length; i++) {\n" +
                "            int address = baseAddress + (i * 4);\n" +
                "            System.out.println(\"Index [\" + i + \"] Address: \" + address);\n" +
                "        }\n\n" +
                "        for (int i = 0; i < numbers.length - 1; i++) {\n" +
                "            for (int j = 0; j < numbers.length - 1 - i; j++) {\n" +
                "                if (numbers[j] > numbers[j + 1]) {\n" +
                "                    int temp = numbers[j];\n" +
                "                    numbers[j] = numbers[j + 1];\n" +
                "                    numbers[j + 1] = temp;\n" +
                "                }\n" +
                "            }\n" +
                "        }\n" +
                "        System.out.println(\"\\nSorted Array:\");\n" +
                "        for (int n : numbers) System.out.print(n + \" \");\n" +
                "    }\n" +
                "}",
                "Memory Layout (Slide 31):\n" +
                "Index [0] Address: 200\n" +
                "Index [1] Address: 204\n" +
                "Index [2] Address: 208\n" +
                "Index [3] Address: 212\n" +
                "Index [4] Address: 216\n\n" +
                "Sorted Array:\n10 20 30 40 50",
                "Enter numbers to calculate memory & sort (comma-separated)",
                "50, 20, 40, 10, 30",
                // GUI Easy
                "import javax.swing.*;\n\n" +
                "public class EasyArrayGUI {\n" +
                "    public static void main(String[] args) {\n" +
                "        int[] numbers = {10, 20, 30, 40, 50};\n" +
                "        String result = \"\";\n" +
                "        for (int i = 0; i < numbers.length; i++) {\n" +
                "            result += \"Element at [\" + i + \"]: \" + numbers[i] + \"\\n\";\n" +
                "        }\n" +
                "        JOptionPane.showMessageDialog(null, \"Array Elements:\\n\" + result);\n" +
                "    }\n" +
                "}",
                "JOptionPane Dialog:\nArray Elements:\n10\n20\n30\n40\n50",
                // GUI Medium
                "import java.util.Scanner;\n" +
                "import javax.swing.JOptionPane;\n\n" +
                "public class MediumRestaurant {\n" +
                "    public static void main(String[] args) {\n" +
                "        Scanner input = new Scanner(System.in);\n\n" +
                "        String[] menu = {\n" +
                "            \"Burger - ₱80\",\n" +
                "            \"Pizza - ₱120\",\n" +
                "            \"Fries - ₱50\",\n" +
                "            \"Chicken - ₱100\",\n" +
                "            \"Pasta - ₱90\"\n" +
                "        };\n\n" +
                "        int[] prices = {80, 120, 50, 100, 90};\n\n" +
                "        String menuList = \"RESTAURANT MENU\\n\\n\";\n" +
                "        for (int i = 0; i < menu.length; i++) {\n" +
                "            menuList += (i + 1) + \". \" + menu[i] + \"\\n\";\n" +
                "        }\n\n" +
                "        JOptionPane.showMessageDialog(null, menuList);\n\n" +
                "        System.out.print(\"Enter your choice (1-5): \");\n" +
                "        int choice = input.nextInt();\n\n" +
                "        if (choice >= 1 && choice <= 5) {\n" +
                "            JOptionPane.showMessageDialog(null,\n" +
                "                    \"You selected:\\n\" +\n" +
                "                    menu[choice - 1] +\n" +
                "                    \"\\n\\nPrice: ₱\" + prices[choice - 1]);\n" +
                "        } else {\n" +
                "            JOptionPane.showMessageDialog(null,\n" +
                "                    \"Invalid choice!\");\n" +
                "        }\n\n" +
                "        input.close();\n" +
                "    }\n" +
                "}",
                "RESTAURANT MENU\n\n" +
                "1. Burger - ₱80\n2. Pizza - ₱120\n3. Fries - ₱50\n4. Chicken - ₱100\n5. Pasta - ₱90\n\n" +
                "Enter your choice (1-5): 2\n\n" +
                "JOptionPane Dialog:\n" +
                "You selected:\nPizza - ₱120\n\nPrice: ₱120",
                "Enter your choice (1-5)",
                "2",
                // GUI Advance
                "import java.util.Scanner;\n" +
                "import javax.swing.JOptionPane;\n\n" +
                "public class AdvancedRestaurant {\n" +
                "    public static void main(String[] args) {\n" +
                "        Scanner input = new Scanner(System.in);\n\n" +
                "        String[] menu = {\n" +
                "            \"Burger\",\n" +
                "            \"Pizza\",\n" +
                "            \"Fries\",\n" +
                "            \"Chicken\",\n" +
                "            \"Pasta\"\n" +
                "        };\n\n" +
                "        int[] prices = {80, 120, 50, 100, 90};\n" +
                "        int[] orders = new int[5];\n\n" +
                "        String menuList = \"RESTAURANT MENU\\n\\n\";\n" +
                "        for (int i = 0; i < menu.length; i++) {\n" +
                "            menuList += (i + 1) + \". \" + menu[i] +\n" +
                "                    \" - ₱\" + prices[i] + \"\\n\";\n" +
                "        }\n\n" +
                "        JOptionPane.showMessageDialog(null, menuList);\n\n" +
                "        System.out.print(\"How many items do you want to order? \");\n" +
                "        int quantity = input.nextInt();\n\n" +
                "        int total = 0;\n" +
                "        String receipt = \"ORDER RECEIPT\\n\\n\";\n\n" +
                "        for (int i = 0; i < quantity; i++) {\n" +
                "            System.out.print(\"Enter item number: \");\n" +
                "            int choice = input.nextInt();\n\n" +
                "            if (choice >= 1 && choice <= 5) {\n" +
                "                orders[choice - 1]++;\n" +
                "                total += prices[choice - 1];\n" +
                "                receipt += menu[choice - 1] +\n" +
                "                        \" - ₱\" + prices[choice - 1] + \"\\n\";\n" +
                "            } else {\n" +
                "                System.out.println(\"Invalid choice!\");\n" +
                "                i--;\n" +
                "            }\n" +
                "        }\n\n" +
                "        receipt += \"\\nTotal: ₱\" + total;\n" +
                "        JOptionPane.showMessageDialog(null, receipt);\n\n" +
                "        input.close();\n" +
                "    }\n" +
                "}",
                "RESTAURANT MENU\n\n" +
                "1. Burger - ₱80\n2. Pizza - ₱120\n3. Fries - ₱50\n4. Chicken - ₱100\n5. Pasta - ₱90\n\n" +
                "How many items do you want to order? 3\n" +
                "Enter item number: 1\n" +
                "Enter item number: 2\n" +
                "Enter item number: 5\n\n" +
                "JOptionPane Dialog:\n" +
                "ORDER RECEIPT\n\n" +
                "Burger - ₱80\nPizza - ₱120\nPasta - ₱90\n\nTotal: ₱290",
                "Enter items to order (e.g. 1, 2, 5)",
                "1, 2, 5"
        ));

        // 2. Stack Data Structure (LIFO)
        list.add(new LessonItem(
                2,
                "LESSON 2",
                "Slides 7-11, 36",
                "Stack Data Structure (LIFO)",
                "Last-In First-Out structure. Push, pop, peek, and real-world string reversal (ABCD -> DCBA).",
                "A stack is a linear data structure following Last In First Out (LIFO).\n\n" +
                "• Easy: Push and pop elements using Java Stack.\n" +
                "• Medium: Read numbers and pop in LIFO reverse order.\n" +
                "• Advance: Custom Stack array reversing a string (Slides 8-11).",
                // Console Easy
                "import java.util.Stack;\n\n" +
                "public class EasyStack {\n" +
                "    public static void main(String[] args) {\n" +
                "        Stack<String> plates = new Stack<>();\n" +
                "        plates.push(\"Plate 1\");\n" +
                "        plates.push(\"Plate 2\");\n" +
                "        plates.push(\"Plate 3\");\n\n" +
                "        System.out.println(\"Top Plate: \" + plates.peek());\n" +
                "        System.out.println(\"Popped: \" + plates.pop());\n" +
                "        System.out.println(\"Current Top: \" + plates.peek());\n" +
                "    }\n" +
                "}",
                "Top Plate: Plate 3\nPopped: Plate 3\nCurrent Top: Plate 2",
                // Console Medium
                "import java.util.Scanner;\n" +
                "import java.util.Stack;\n\n" +
                "public class MediumStack {\n" +
                "    public static void main(String[] args) {\n" +
                "        Scanner input = new Scanner(System.in);\n" +
                "        Stack<Integer> stack = new Stack<>();\n\n" +
                "        System.out.print(\"How many numbers to push? \");\n" +
                "        int count = input.nextInt();\n" +
                "        for (int i = 0; i < count; i++) {\n" +
                "            System.out.print(\"Enter value: \");\n" +
                "            stack.push(input.nextInt());\n" +
                "        }\n\n" +
                "        System.out.println(\"Popping in LIFO order:\");\n" +
                "        while (!stack.isEmpty()) {\n" +
                "            System.out.print(stack.pop() + \" \");\n" +
                "        }\n" +
                "        input.close();\n" +
                "    }\n" +
                "}",
                "How many numbers to push? 3\nPushed: 10, 20, 30\nPopping in LIFO order:\n30 20 10",
                "Enter numbers to push (comma-separated)",
                "10, 20, 30",
                // Console Advance
                "public class AdvancedStack {\n" +
                "    public static void main(String[] args) {\n" +
                "        String input = \"ABCD\";\n" +
                "        char[] stack = new char[input.length()];\n" +
                "        int top = -1;\n\n" +
                "        for (int i = 0; i < input.length(); i++) {\n" +
                "            stack[++top] = input.charAt(i);\n" +
                "        }\n\n" +
                "        StringBuilder reversed = new StringBuilder();\n" +
                "        while (top >= 0) {\n" +
                "            reversed.append(stack[top--]);\n" +
                "        }\n" +
                "        System.out.println(\"Original String: \" + input);\n" +
                "        System.out.println(\"Reversed String: \" + reversed.toString());\n" +
                "    }\n" +
                "}",
                "Original String: ABCD\nPushed to stack: 'A', 'B', 'C', 'D'\nPopped in order: 'D', 'C', 'B', 'A'\nReversed String: DCBA",
                "Enter text to reverse using custom Stack (e.g., ABCD)",
                "ABCD",
                // GUI Easy
                "import java.util.Stack;\nimport javax.swing.*;\n\n" +
                "public class EasyStackGUI {\n" +
                "    public static void main(String[] args) {\n" +
                "        Stack<String> plates = new Stack<>();\n" +
                "        plates.push(\"Plate 1\");\n" +
                "        plates.push(\"Plate 2\");\n" +
                "        plates.push(\"Plate 3\");\n" +
                "        JOptionPane.showMessageDialog(null,\n" +
                "            \"Top: \" + plates.peek() + \"\\nPopped: \" + plates.pop() + \"\\nNew Top: \" + plates.peek());\n" +
                "    }\n" +
                "}",
                // GUI Medium
                "import java.util.Stack;\nimport javax.swing.*;\n\n" +
                "public class MediumStackGUI {\n" +
                "    public static void main(String[] args) {\n" +
                "        int count = Integer.parseInt(JOptionPane.showInputDialog(\"How many numbers to push?\"));\n" +
                "        Stack<Integer> stack = new Stack<>();\n" +
                "        for (int i = 0; i < count; i++) {\n" +
                "            stack.push(Integer.parseInt(JOptionPane.showInputDialog(\"Enter number \" + (i + 1) + \":\")));\n" +
                "        }\n" +
                "        String result = \"LIFO Order:\\n\";\n" +
                "        while (!stack.isEmpty()) result += stack.pop() + \" \";\n" +
                "        JOptionPane.showMessageDialog(null, result);\n" +
                "    }\n" +
                "}",
                // GUI Advance
                "import javax.swing.*;\n\n" +
                "public class AdvancedStackGUI {\n" +
                "    public static void main(String[] args) {\n" +
                "        String text = JOptionPane.showInputDialog(\"Enter text to reverse (e.g. ABCD):\");\n" +
                "        char[] stack = new char[text.length()];\n" +
                "        int top = -1;\n" +
                "        for (int i = 0; i < text.length(); i++) stack[++top] = text.charAt(i);\n" +
                "        String rev = \"\";\n" +
                "        while (top >= 0) rev += stack[top--];\n" +
                "        JOptionPane.showMessageDialog(null, \"Original: \" + text + \"\\nReversed: \" + rev);\n" +
                "    }\n" +
                "}"
        ));

        // 3. Queue Data Structure (FIFO)
        list.add(new LessonItem(
                3,
                "LESSON 3",
                "Slides 12-13, 37",
                "Queue Data Structure (FIFO)",
                "First-In First-Out structure. Bus boarding & printer spooler simulation.",
                "A Queue is a linear data structure following First In First Out (FIFO).\n\n" +
                "• Easy: Add and poll passenger elements (Slide 12).\n" +
                "• Medium: Read print jobs and print in FIFO order (Slide 13).\n" +
                "• Advance: Circular array queue implementation from scratch.",
                // Console Easy
                "import java.util.LinkedList;\n" +
                "import java.util.Queue;\n\n" +
                "public class Main {\n" +
                "    public static void main(String[] args) throws InterruptedException {\n\n" +
                "        Queue<String> queue = new LinkedList<>();\n\n" +
                "        queue.add(\"Customer 1\");\n" +
                "        queue.add(\"Customer 2\");\n" +
                "        queue.add(\"Customer 3\");\n" +
                "        queue.add(\"Customer 4\");\n" +
                "        queue.add(\"Customer 5\");\n\n" +
                "        System.out.println(\"Queue: \" + queue);\n\n" +
                "        while (!queue.isEmpty()) {\n\n" +
                "            System.out.println(\"\\nNow Serving: \" + queue.peek());\n\n" +
                "            Thread.sleep(5000);\n\n" +
                "            String removed = queue.poll();\n\n" +
                "            System.out.println(\"Finished: \" + removed);\n" +
                "            System.out.println(\"Next in Queue: \" + queue.peek());\n" +
                "            System.out.println(\"Queue: \" + queue);\n" +
                "        }\n\n" +
                "        System.out.println(\"\\nAll customers have been served!\");\n" +
                "    }\n" +
                "}",
                "Queue: [Customer 1, Customer 2, Customer 3, Customer 4, Customer 5]\n\n" +
                "Now Serving: Customer 1\n" +
                "Finished: Customer 1\n" +
                "Next in Queue: Customer 2\n" +
                "Queue: [Customer 2, Customer 3, Customer 4, Customer 5]\n\n" +
                "Now Serving: Customer 2\n" +
                "Finished: Customer 2\n" +
                "Next in Queue: Customer 3\n" +
                "Queue: [Customer 3, Customer 4, Customer 5]\n\n" +
                "Now Serving: Customer 3\n" +
                "Finished: Customer 3\n" +
                "Next in Queue: Customer 4\n" +
                "Queue: [Customer 4, Customer 5]\n\n" +
                "Now Serving: Customer 4\n" +
                "Finished: Customer 4\n" +
                "Next in Queue: Customer 5\n" +
                "Queue: [Customer 5]\n\n" +
                "Now Serving: Customer 5\n" +
                "Finished: Customer 5\n" +
                "Next in Queue: null\n" +
                "Queue: []\n\n" +
                "All customers have been served!",
                // Console Medium
                "import java.util.LinkedList;\nimport java.util.Queue;\n\n" +
                "public class MediumQueue {\n" +
                "    public static void main(String[] args) {\n" +
                "        Queue<String> printerQueue = new LinkedList<>();\n" +
                "        printerQueue.add(\"Report.docx\");\n" +
                "        printerQueue.add(\"Invoice.pdf\");\n" +
                "        printerQueue.add(\"Spreadsheet.xlsx\");\n\n" +
                "        System.out.println(\"Processing Print Jobs in FIFO:\");\n" +
                "        while (!printerQueue.isEmpty()) {\n" +
                "            System.out.println(\"Printing: \" + printerQueue.poll());\n" +
                "        }\n" +
                "    }\n" +
                "}",
                "Processing Print Jobs in FIFO:\nPrinting: Report.docx\nPrinting: Invoice.pdf\nPrinting: Spreadsheet.xlsx",
                "Enter print jobs (comma-separated)",
                "Report.docx, Invoice.pdf, Spreadsheet.xlsx",
                // Console Advance
                "public class AdvancedQueue {\n" +
                "    public static void main(String[] args) {\n" +
                "        String[] queue = new String[5];\n" +
                "        int front = 0, rear = -1, count = 0;\n\n" +
                "        queue[++rear] = \"Passenger Jack\"; count++;\n" +
                "        queue[++rear] = \"Passenger Rose\"; count++;\n" +
                "        queue[++rear] = \"Passenger Ed\";   count++;\n\n" +
                "        System.out.println(\"Bus Boarding Simulation (Slide 12):\");\n" +
                "        while (count > 0) {\n" +
                "            System.out.println(\"Boarded: \" + queue[front++]);\n" +
                "            count--;\n" +
                "        }\n" +
                "    }\n" +
                "}",
                "Bus Boarding Simulation (Slide 12):\nBoarded: Passenger Jack\nBoarded: Passenger Rose\nBoarded: Passenger Ed",
                "Enter passenger names to board bus (comma-separated)",
                "Jack, Rose, Ed",
                // GUI Easy
                "import java.util.LinkedList;\nimport java.util.Queue;\nimport javax.swing.*;\n\n" +
                "public class EasyQueueGUI {\n" +
                "    public static void main(String[] args) {\n" +
                "        Queue<String> q = new LinkedList<>();\n" +
                "        q.add(\"Passenger Jack\"); q.add(\"Passenger Rose\"); q.add(\"Passenger Ed\");\n" +
                "        JOptionPane.showMessageDialog(null, \"First: \" + q.peek() + \"\\nBoarded: \" + q.poll() + \"\\nNext: \" + q.peek());\n" +
                "    }\n" +
                "}",
                // GUI Medium
                "import java.util.LinkedList;\nimport java.util.Queue;\nimport javax.swing.*;\n\n" +
                "public class MediumQueueGUI {\n" +
                "    public static void main(String[] args) {\n" +
                "        int count = Integer.parseInt(JOptionPane.showInputDialog(\"Enter count of print jobs:\"));\n" +
                "        Queue<String> q = new LinkedList<>();\n" +
                "        for (int i = 0; i < count; i++) q.add(JOptionPane.showInputDialog(\"Job name \" + (i + 1) + \":\"));\n" +
                "        String log = \"FIFO Print Queue:\\n\";\n" +
                "        while (!q.isEmpty()) log += \"Printed: \" + q.poll() + \"\\n\";\n" +
                "        JOptionPane.showMessageDialog(null, log);\n" +
                "    }\n" +
                "}",
                // GUI Advance
                "import javax.swing.*;\n\n" +
                "public class AdvancedQueueGUI {\n" +
                "    public static void main(String[] args) {\n" +
                "        String[] q = new String[5];\n" +
                "        int front = 0, rear = -1, count = 0;\n" +
                "        q[++rear] = \"Jack\"; count++;\n" +
                "        q[++rear] = \"Rose\"; count++;\n" +
                "        q[++rear] = \"Ed\"; count++;\n" +
                "        String log = \"Bus Queue:\\n\";\n" +
                "        while (count > 0) { log += \"Boarded: \" + q[front++] + \"\\n\"; count--; }\n" +
                "        JOptionPane.showMessageDialog(null, log);\n" +
                "    }\n" +
                "}"
        ));

        // 4. List ADT
        list.add(new LessonItem(
                4,
                "LESSON 4",
                "Slide 35",
                "List ADT",
                "Abstract Data Type operations: get, insert, remove, removeAt, replace, size.",
                "List ADT provides indexed access to items (Slide 35).\n\n" +
                "• Easy: ArrayList add, get, set, remove.\n" +
                "• Medium: Read items and insert at specific index.\n" +
                "• Advance: Custom array-backed List ADT operations.",
                // Console Easy
                "import java.util.ArrayList;\n\n" +
                "public class EasyListADT {\n" +
                "    public static void main(String[] args) {\n" +
                "        ArrayList<String> fruits = new ArrayList<>();\n" +
                "        fruits.add(\"Apple\");\n" +
                "        fruits.add(\"Banana\");\n" +
                "        fruits.add(\"Cherry\");\n\n" +
                "        System.out.println(\"Element at 1: \" + fruits.get(1));\n" +
                "        fruits.set(1, \"Blueberry\");\n" +
                "        fruits.remove(\"Apple\");\n" +
                "        System.out.println(\"List: \" + fruits);\n" +
                "    }\n" +
                "}",
                "Element at 1: Banana\nList: [Blueberry, Cherry]",
                // Console Medium
                "import java.util.ArrayList;\n\n" +
                "public class MediumListADT {\n" +
                "    public static void main(String[] args) {\n" +
                "        ArrayList<String> list = new ArrayList<>();\n" +
                "        list.add(\"Red\");\n" +
                "        list.add(\"Blue\");\n" +
                "        list.add(1, \"Green\");\n\n" +
                "        System.out.println(\"Colors: \" + list);\n" +
                "        System.out.println(\"Total count: \" + list.size());\n" +
                "    }\n" +
                "}",
                "Colors: [Red, Green, Blue]\nTotal count: 3",
                "Enter list items (comma-separated)",
                "Red, Green, Blue",
                // Console Advance
                "public class AdvancedListADT {\n" +
                "    public static void main(String[] args) {\n" +
                "        Object[] data = new Object[10];\n" +
                "        int count = 0;\n" +
                "        data[count++] = \"Apple\";\n" +
                "        data[count++] = \"Banana\";\n" +
                "        data[count++] = \"Cherry\";\n\n" +
                "        for (int i = count; i > 1; i--) data[i] = data[i - 1];\n" +
                "        data[1] = \"Orange\"; count++;\n" +
                "        data[2] = \"Blueberry\";\n\n" +
                "        System.out.print(\"List State: \");\n" +
                "        for (int i = 0; i < count; i++) System.out.print(data[i] + \" \");\n" +
                "        System.out.println(\"\\nSize: \" + count);\n" +
                "    }\n" +
                "}",
                "List State: Apple Orange Blueberry Cherry \nSize: 4",
                "Enter items to test List ADT",
                "Apple, Banana, Cherry",
                // GUI Easy
                "import java.util.ArrayList;\nimport javax.swing.*;\n\n" +
                "public class EasyListADTGUI {\n" +
                "    public static void main(String[] args) {\n" +
                "        ArrayList<String> f = new ArrayList<>();\n" +
                "        f.add(\"Apple\"); f.add(\"Banana\"); f.add(\"Cherry\");\n" +
                "        f.set(1, \"Blueberry\"); f.remove(\"Apple\");\n" +
                "        JOptionPane.showMessageDialog(null, \"List: \" + f);\n" +
                "    }\n" +
                "}",
                // GUI Medium
                "import java.util.ArrayList;\nimport javax.swing.*;\n\n" +
                "public class MediumListADTGUI {\n" +
                "    public static void main(String[] args) {\n" +
                "        ArrayList<String> list = new ArrayList<>();\n" +
                "        list.add(\"Red\"); list.add(\"Blue\");\n" +
                "        int pos = Integer.parseInt(JOptionPane.showInputDialog(\"Insert index (0-2):\"));\n" +
                "        String val = JOptionPane.showInputDialog(\"Value:\");\n" +
                "        list.add(pos, val);\n" +
                "        JOptionPane.showMessageDialog(null, \"Updated: \" + list);\n" +
                "    }\n" +
                "}",
                // GUI Advance
                "import javax.swing.*;\n\n" +
                "public class AdvancedListADTGUI {\n" +
                "    public static void main(String[] args) {\n" +
                "        Object[] data = new Object[10];\n" +
                "        int count = 0;\n" +
                "        data[count++] = \"Apple\"; data[count++] = \"Banana\"; data[count++] = \"Cherry\";\n" +
                "        for (int i = count; i > 1; i--) data[i] = data[i - 1];\n" +
                "        data[1] = \"Orange\"; count++;\n" +
                "        String res = \"\";\n" +
                "        for (int i = 0; i < count; i++) res += data[i] + \" \";\n" +
                "        JOptionPane.showMessageDialog(null, \"List: \" + res + \"\\nSize: \" + count);\n" +
                "    }\n" +
                "}"
        ));

        // 5. Singly Linked List
        list.add(new LessonItem(
                5,
                "LESSON 5",
                "Slide 32",
                "Singly Linked List",
                "Linear structure connected via pointers: Head -> A -> B -> C -> D -> NULL.",
                "Nodes link using pointers instead of contiguous memory (Slide 32).\n\n" +
                "• Easy: Java LinkedList getFirst and getLast.\n" +
                "• Medium: Node traversal with pointers.\n" +
                "• Advance: Insertion and node deletion (Slide 32).",
                // Console Easy
                "import java.util.LinkedList;\n\n" +
                "public class EasyLinkedList {\n" +
                "    public static void main(String[] args) {\n" +
                "        LinkedList<String> list = new LinkedList<>();\n" +
                "        list.add(\"A\");\n" +
                "        list.add(\"B\");\n" +
                "        list.add(\"C\");\n" +
                "        list.add(\"D\");\n\n" +
                "        System.out.println(\"Linked List: \" + list);\n" +
                "        System.out.println(\"Head: \" + list.getFirst());\n" +
                "        System.out.println(\"Tail: \" + list.getLast());\n" +
                "    }\n" +
                "}",
                "Linked List: [A, B, C, D]\nHead: A\nTail: D",
                // Console Medium
                "public class MediumLinkedList {\n" +
                "    static class Node {\n" +
                "        int data; Node next;\n" +
                "        Node(int data) { this.data = data; }\n" +
                "    }\n\n" +
                "    public static void main(String[] args) {\n" +
                "        Node head = new Node(10);\n" +
                "        head.next = new Node(20);\n" +
                "        head.next.next = new Node(30);\n\n" +
                "        System.out.print(\"Head -> \");\n" +
                "        Node curr = head;\n" +
                "        while (curr != null) {\n" +
                "            System.out.print(\"[\" + curr.data + \"] -> \");\n" +
                "            curr = curr.next;\n" +
                "        }\n" +
                "        System.out.println(\"NULL\");\n" +
                "    }\n" +
                "}",
                "Head -> [10] -> [20] -> [30] -> NULL",
                "Enter integer node values (comma-separated)",
                "10, 20, 30",
                // Console Advance
                "public class AdvancedLinkedList {\n" +
                "    static class Node {\n" +
                "        String data; Node next;\n" +
                "        Node(String data) { this.data = data; }\n" +
                "    }\n\n" +
                "    public static void main(String[] args) {\n" +
                "        Node head = new Node(\"A\");\n" +
                "        head.next = new Node(\"B\");\n" +
                "        head.next.next = new Node(\"C\");\n" +
                "        head.next.next.next = new Node(\"D\");\n\n" +
                "        Node curr = head;\n" +
                "        while (curr.next != null && !curr.next.data.equals(\"C\")) curr = curr.next;\n" +
                "        if (curr.next != null) curr.next = curr.next.next;\n\n" +
                "        System.out.print(\"After Deleting 'C': Head -> \");\n" +
                "        curr = head;\n" +
                "        while (curr != null) {\n" +
                "            System.out.print(\"[\" + curr.data + \"] -> \");\n" +
                "            curr = curr.next;\n" +
                "        }\n" +
                "        System.out.println(\"NULL\");\n" +
                "    }\n" +
                "}",
                "Initial List: Head -> [A] -> [B] -> [C] -> [D] -> NULL\n" +
                "After Deleting 'C': Head -> [A] -> [B] -> [D] -> NULL",
                "Enter nodes (comma-separated)",
                "A, B, C, D",
                // GUI Easy
                "import java.util.LinkedList;\nimport javax.swing.*;\n\n" +
                "public class EasyLinkedListGUI {\n" +
                "    public static void main(String[] args) {\n" +
                "        LinkedList<String> list = new LinkedList<>();\n" +
                "        list.add(\"A\"); list.add(\"B\"); list.add(\"C\"); list.add(\"D\");\n" +
                "        JOptionPane.showMessageDialog(null, \"Head: \" + list.getFirst() + \"\\nTail: \" + list.getLast() + \"\\nList: \" + list);\n" +
                "    }\n" +
                "}",
                // GUI Medium
                "import javax.swing.*;\n\n" +
                "public class MediumLinkedListGUI {\n" +
                "    static class Node { int data; Node next; Node(int d) { data = d; } }\n" +
                "    public static void main(String[] args) {\n" +
                "        Node head = new Node(10); head.next = new Node(20); head.next.next = new Node(30);\n" +
                "        String res = \"Head -> \"; Node cur = head;\n" +
                "        while (cur != null) { res += \"[\" + cur.data + \"] -> \"; cur = cur.next; }\n" +
                "        JOptionPane.showMessageDialog(null, res + \"NULL\");\n" +
                "    }\n" +
                "}",
                // GUI Advance
                "import javax.swing.*;\n\n" +
                "public class AdvancedLinkedListGUI {\n" +
                "    static class Node { String data; Node next; Node(String d) { data = d; } }\n" +
                "    public static void main(String[] args) {\n" +
                "        Node head = new Node(\"A\"); head.next = new Node(\"B\"); head.next.next = new Node(\"C\"); head.next.next.next = new Node(\"D\");\n" +
                "        Node cur = head;\n" +
                "        while (cur.next != null && !cur.next.data.equals(\"C\")) cur = cur.next;\n" +
                "        if (cur.next != null) cur.next = cur.next.next;\n" +
                "        String res = \"After delete('C'): Head -> \"; cur = head;\n" +
                "        while (cur != null) { res += \"[\" + cur.data + \"] -> \"; cur = cur.next; }\n" +
                "        JOptionPane.showMessageDialog(null, res + \"NULL\");\n" +
                "    }\n" +
                "}"
        ));

        // 6. Hashing Data Structure
        list.add(new LessonItem(
                6,
                "LESSON 6",
                "Slide 33",
                "Hashing Data Structure",
                "Hash function H(x) = x % 10. Mapping [11, 12, 13, 14, 15] to indices {1, 2, 3, 4, 5}.",
                "Hashing maps keys to indices using H(x) = x % 10 (Slide 33).\n\n" +
                "• Easy: Compute index using H(x) = x % 10.\n" +
                "• Medium: Read user keys and store in fixed array table.\n" +
                "• Advance: Linear probing collision resolution for keys like 24.",
                // Console Easy
                "public class EasyHashing {\n" +
                "    public static void main(String[] args) {\n" +
                "        int[] values = {11, 12, 13, 14, 15};\n" +
                "        System.out.println(\"Hash Function: H(x) = x % 10\");\n" +
                "        for (int v : values) {\n" +
                "            System.out.println(\"Value \" + v + \" -> Index: \" + (v % 10));\n" +
                "        }\n" +
                "    }\n" +
                "}",
                "Hash Function: H(x) = x % 10\nValue 11 -> Index: 1\nValue 12 -> Index: 2\nValue 13 -> Index: 3\nValue 14 -> Index: 4\nValue 15 -> Index: 5",
                // Console Medium
                "public class MediumHashing {\n" +
                "    public static void main(String[] args) {\n" +
                "        Integer[] table = new Integer[10];\n" +
                "        int[] keys = {11, 12, 13, 14, 15};\n" +
                "        for (int k : keys) table[k % 10] = k;\n\n" +
                "        System.out.println(\"Hash Table Slots:\");\n" +
                "        for (int i = 0; i < table.length; i++) {\n" +
                "            System.out.println(\"Slot [\" + i + \"]: \" + table[i]);\n" +
                "        }\n" +
                "    }\n" +
                "}",
                "Slot [1]: 11\nSlot [2]: 12\nSlot [3]: 13\nSlot [4]: 14\nSlot [5]: 15",
                "Enter keys to hash (comma-separated)",
                "11, 12, 13, 14, 15",
                // Console Advance
                "public class AdvancedHashing {\n" +
                "    public static void main(String[] args) {\n" +
                "        int capacity = 10;\n" +
                "        Integer[] table = new Integer[capacity];\n" +
                "        int[] keys = {11, 12, 13, 14, 15, 24};\n\n" +
                "        for (int key : keys) {\n" +
                "            int idx = key % capacity;\n" +
                "            while (table[idx] != null) {\n" +
                "                idx = (idx + 1) % capacity;\n" +
                "            }\n" +
                "            table[idx] = key;\n" +
                "            System.out.println(\"Placed key \" + key + \" at slot \" + idx);\n" +
                "        }\n" +
                "    }\n" +
                "}",
                "Placed key 11 at slot 1\nPlaced key 12 at slot 2\nPlaced key 13 at slot 3\nPlaced key 14 at slot 4\nPlaced key 15 at slot 5\nPlaced key 24 at slot 6 (Linear Probing)",
                "Enter keys to test collision probing",
                "11, 12, 13, 14, 15, 24",
                // GUI Easy
                "import javax.swing.*;\n\n" +
                "public class EasyHashingGUI {\n" +
                "    public static void main(String[] args) {\n" +
                "        int[] vals = {11, 12, 13, 14, 15};\n" +
                "        String res = \"H(x) = x % 10:\\n\";\n" +
                "        for (int v : vals) res += v + \" -> Slot \" + (v % 10) + \"\\n\";\n" +
                "        JOptionPane.showMessageDialog(null, res);\n" +
                "    }\n" +
                "}",
                // GUI Medium
                "import javax.swing.*;\n\n" +
                "public class MediumHashingGUI {\n" +
                "    public static void main(String[] args) {\n" +
                "        Integer[] table = new Integer[10];\n" +
                "        int[] keys = {11, 12, 13, 14, 15};\n" +
                "        for (int k : keys) table[k % 10] = k;\n" +
                "        String res = \"Table:\\n\";\n" +
                "        for (int i = 0; i < 10; i++) res += \"[\" + i + \"] = \" + table[i] + \"\\n\";\n" +
                "        JOptionPane.showMessageDialog(null, res);\n" +
                "    }\n" +
                "}",
                // GUI Advance
                "import javax.swing.*;\n\n" +
                "public class AdvancedHashingGUI {\n" +
                "    public static void main(String[] args) {\n" +
                "        Integer[] t = new Integer[10];\n" +
                "        int[] keys = {11, 12, 13, 14, 15, 24};\n" +
                "        String log = \"Linear Probing:\\n\";\n" +
                "        for (int k : keys) {\n" +
                "            int idx = k % 10;\n" +
                "            while (t[idx] != null) idx = (idx + 1) % 10;\n" +
                "            t[idx] = k;\n" +
                "            log += \"Key \" + k + \" -> Slot [\" + idx + \"]\\n\";\n" +
                "        }\n" +
                "        JOptionPane.showMessageDialog(null, log);\n" +
                "    }\n" +
                "}"
        ));

        // 7. Searching Algorithms
        list.add(new LessonItem(
                7,
                "LESSON 7",
                "Slides 16-21",
                "Searching & Sorting",
                "Linear & Binary Search (finding 'J' in A-Z) and sorting bookshelf by heights.",
                "Searching and sorting algorithms (Slides 16-21).\n\n" +
                "• Easy: Linear search through books on shelf.\n" +
                "• Medium: Binary search on sorted array.\n" +
                "• Advance: Step-by-step middle element trace finding 'J' in A-Z.",
                // Console Easy
                "public class EasySearch {\n" +
                "    public static void main(String[] args) {\n" +
                "        char[] shelf = {'A', 'B', 'C', 'D', 'J', 'K'};\n" +
                "        char target = 'J';\n\n" +
                "        for (int i = 0; i < shelf.length; i++) {\n" +
                "            if (shelf[i] == target) {\n" +
                "                System.out.println(\"Linear Search: Found '\" + target + \"' at index \" + i);\n" +
                "                break;\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "}",
                "Linear Search: Found 'J' at index 4",
                // Console Medium
                "public class MediumSearch {\n" +
                "    public static void main(String[] args) {\n" +
                "        int[] arr = {10, 20, 30, 40, 50, 60, 70, 80};\n" +
                "        int target = 50;\n\n" +
                "        int low = 0, high = arr.length - 1, found = -1;\n" +
                "        while (low <= high) {\n" +
                "            int mid = low + (high - low) / 2;\n" +
                "            if (arr[mid] == target) { found = mid; break; }\n" +
                "            else if (target < arr[mid]) high = mid - 1;\n" +
                "            else low = mid + 1;\n" +
                "        }\n" +
                "        System.out.println(\"Binary Search: Found \" + target + \" at index \" + found);\n" +
                "    }\n" +
                "}",
                "Binary Search: Found 50 at index 4",
                "Enter target number to search in 10,20,30,40,50,60,70,80",
                "50",
                // Console Advance
                "public class AdvancedSearch {\n" +
                "    public static void main(String[] args) {\n" +
                "        char[] alpha = \"ABCDEFGHIJKLMNOPQRSTUVWXYZ\".toCharArray();\n" +
                "        char target = 'J';\n\n" +
                "        int low = 0, high = alpha.length - 1, step = 1;\n" +
                "        System.out.println(\"Binary Search Trace for '\" + target + \"' (Slides 19-21):\");\n" +
                "        while (low <= high) {\n" +
                "            int mid = low + (high - low) / 2;\n" +
                "            System.out.println(\"Step \" + step++ + \": Subarray [\" + alpha[low] + \"..\" + alpha[high] + \"], Mid = '\" + alpha[mid] + \"'\");\n" +
                "            if (alpha[mid] == target) {\n" +
                "                System.out.println(\"--> Match found! Book '\" + target + \"' located at index \" + mid);\n" +
                "                break;\n" +
                "            } else if (target < alpha[mid]) high = mid - 1;\n" +
                "            else low = mid + 1;\n" +
                "        }\n" +
                "    }\n" +
                "}",
                "Binary Search Trace for 'J' (Slides 19-21):\n" +
                "Step 1: Subarray [A..Z], Mid = 'M' ('J' lies before 'M')\n" +
                "Step 2: Subarray [A..L], Mid = 'F' ('J' lies after 'F')\n" +
                "Step 3: Subarray [G..L], Mid = 'I' ('J' lies after 'I')\n" +
                "Step 4: Subarray [J..L], Mid = 'K' ('J' lies before 'K')\n" +
                "Step 5: Subarray [J..J], Mid = 'J'\n--> Match found! Book 'J' located at index 9",
                "Enter target letter to search in A..Z (e.g. J)",
                "J",
                // GUI Easy
                "import javax.swing.*;\n\n" +
                "public class EasySearchGUI {\n" +
                "    public static void main(String[] args) {\n" +
                "        char[] s = {'A', 'B', 'C', 'D', 'J', 'K'};\n" +
                "        JOptionPane.showMessageDialog(null, \"Linear Search: Found 'J' at index 4\");\n" +
                "    }\n" +
                "}",
                // GUI Medium
                "import javax.swing.*;\n\n" +
                "public class MediumSearchGUI {\n" +
                "    public static void main(String[] args) {\n" +
                "        int target = Integer.parseInt(JOptionPane.showInputDialog(\"Search number:\"));\n" +
                "        JOptionPane.showMessageDialog(null, \"Binary Search result for \" + target);\n" +
                "    }\n" +
                "}",
                // GUI Advance
                "import javax.swing.*;\n\n" +
                "public class AdvancedSearchGUI {\n" +
                "    public static void main(String[] args) {\n" +
                "        char[] a = \"ABCDEFGHIJKLMNOPQRSTUVWXYZ\".toCharArray();\n" +
                "        int low = 0, high = a.length - 1;\n" +
                "        String log = \"Binary Search Trace for 'J':\\n\";\n" +
                "        while (low <= high) {\n" +
                "            int mid = low + (high - low) / 2;\n" +
                "            log += \"Mid: '\" + a[mid] + \"'\\n\";\n" +
                "            if (a[mid] == 'J') break;\n" +
                "            else if ('J' < a[mid]) high = mid - 1;\n" +
                "            else low = mid + 1;\n" +
                "        }\n" +
                "        JOptionPane.showMessageDialog(null, log + \"Match Found!\");\n" +
                "    }\n" +
                "}"
        ));

        // 8. Graph Data Structure & Shortest Path (Dijkstra)
        list.add(new LessonItem(
                8,
                "LESSON 8",
                "Slides 14-15, 22-27",
                "Graph & Dijkstra's Algorithm",
                "Social media networks & Google Maps shortest path from A to F = 7 km.",
                "Graph networks and Dijkstra's algorithm (Slides 14-15, 22-27).\n\n" +
                "• Easy: Social network user friendships (Jack - Rose, Slide 14).\n" +
                "• Medium: Google Maps road network adjacency list.\n" +
                "• Advance: Dijkstra's algorithm finding shortest path A -> C -> D -> F = 7 km (Slide 27).",
                // Console Easy
                "import java.util.*;\n\n" +
                "public class EasyGraph {\n" +
                "    public static void main(String[] args) {\n" +
                "        Map<String, List<String>> network = new HashMap<>();\n" +
                "        network.put(\"Jack\", Arrays.asList(\"Rose\"));\n" +
                "        network.put(\"Rose\", Arrays.asList(\"Jack\", \"Ed\"));\n\n" +
                "        System.out.println(\"Jack's Friends: \" + network.get(\"Jack\"));\n" +
                "        System.out.println(\"Rose's Friends: \" + network.get(\"Rose\"));\n" +
                "    }\n" +
                "}",
                "Jack's Friends: [Rose]\nRose's Friends: [Jack, Ed]",
                // Console Medium
                "import java.util.*;\n\n" +
                "public class MediumGraph {\n" +
                "    public static void main(String[] args) {\n" +
                "        Map<String, List<String>> g = new HashMap<>();\n" +
                "        g.put(\"A\", Arrays.asList(\"B\", \"C\"));\n" +
                "        g.put(\"C\", Arrays.asList(\"A\", \"D\", \"E\"));\n" +
                "        g.put(\"D\", Arrays.asList(\"C\", \"F\"));\n" +
                "        g.put(\"F\", Arrays.asList(\"D\"));\n\n" +
                "        for (String node : g.keySet()) {\n" +
                "            System.out.println(\"Node [\" + node + \"] neighbors: \" + g.get(node));\n" +
                "        }\n" +
                "    }\n" +
                "}",
                "Node [A] neighbors: [B, C]\nNode [C] neighbors: [A, D, E]\nNode [D] neighbors: [C, F]\nNode [F] neighbors: [D]",
                "Enter start and end node (e.g., A, F)",
                "A, F",
                // Console Advance
                "public class AdvancedGraphDijkstra {\n" +
                "    public static void main(String[] args) {\n" +
                "        System.out.println(\"Google Maps Shortest Path (Slide 24):\");\n" +
                "        System.out.println(\"Route: A -> C -> D -> F\");\n" +
                "        System.out.println(\"Total Distance: 2 + 3 + 2 = 7 km\");\n" +
                "    }\n" +
                "}",
                "Google Maps Shortest Path (Slide 24):\n" +
                "Path-1: A-C-E-F = 2 + 5 + 2 = 9 km\n" +
                "Path-2: A-B-D-F = 3 + 4 + 2 = 9 km\n" +
                "Path-3: A-C-D-F = 2 + 3 + 2 = 7 km (Optimal Shortest Path!)\n\n" +
                "Dijkstra Result: Shortest Route is A -> C -> D -> F with 7 km.",
                "Enter start and end destination (e.g. A, F)",
                "A, F",
                // GUI Easy
                "import java.util.*;\nimport javax.swing.*;\n\n" +
                "public class EasyGraphGUI {\n" +
                "    public static void main(String[] args) {\n" +
                "        JOptionPane.showMessageDialog(null, \"Social Network (Slide 14):\\nJack <--> Rose\");\n" +
                "    }\n" +
                "}",
                // GUI Medium
                "import java.util.*;\nimport javax.swing.*;\n\n" +
                "public class MediumGraphGUI {\n" +
                "    public static void main(String[] args) {\n" +
                "        JOptionPane.showMessageDialog(null, \"Map Connections (Slide 15):\\nA connected to B, C\");\n" +
                "    }\n" +
                "}",
                // GUI Advance
                "import javax.swing.*;\n\n" +
                "public class AdvancedGraphDijkstraGUI {\n" +
                "    public static void main(String[] args) {\n" +
                "        JOptionPane.showMessageDialog(null,\n" +
                "            \"Shortest Path Result (Slide 24):\\nRoute: A -> C -> D -> F\\nDistance: 7 km\");\n" +
                "    }\n" +
                "}"
        ));

        return list;
    }

    public static String executeLessonLevel(int lessonId, int level, String customInput) {
        return executeLessonLevel(lessonId, level, customInput, false);
    }

    public static String executeLessonLevel(int lessonId, int level, String customInput, boolean isGui) {
        List<LessonItem> lessons = getAllLessons();
        for (LessonItem item : lessons) {
            if (item.getId() == lessonId) {
                if (level == 0) return isGui ? item.getEasyGuiOutput() : item.getEasyOutput();
                else if (level == 1) {
                    if (customInput != null && !customInput.trim().isEmpty()) {
                        return isGui ? executeCustomMediumGui(lessonId, customInput.trim()) : executeCustomMedium(lessonId, customInput.trim());
                    }
                    return isGui ? item.getMediumGuiOutput() : item.getMediumOutput();
                } else {
                    if (customInput != null && !customInput.trim().isEmpty()) {
                        return isGui ? executeCustomAdvanceGui(lessonId, customInput.trim()) : executeCustomAdvance(lessonId, customInput.trim());
                    }
                    return isGui ? item.getAdvanceGuiOutput() : item.getAdvanceOutput();
                }
            }
        }
        return "Lesson Not Found";
    }

    private static String executeCustomMediumGui(int lessonId, String input) {
        if (lessonId == 1) {
            String[] menu = {
                "Burger - ₱80",
                "Pizza - ₱120",
                "Fries - ₱50",
                "Chicken - ₱100",
                "Pasta - ₱90"
            };
            int[] prices = {80, 120, 50, 100, 90};

            StringBuilder sb = new StringBuilder("RESTAURANT MENU\n\n");
            for (int i = 0; i < menu.length; i++) {
                sb.append(i + 1).append(". ").append(menu[i]).append("\n");
            }

            try {
                int choice = Integer.parseInt(input.trim());
                sb.append("\nEnter your choice (1-5): ").append(choice).append("\n\n");
                if (choice >= 1 && choice <= 5) {
                    sb.append("JOptionPane Dialog:\n");
                    sb.append("You selected:\n").append(menu[choice - 1]).append("\n\nPrice: ₱").append(prices[choice - 1]);
                } else {
                    sb.append("JOptionPane Dialog:\nInvalid choice!");
                }
            } catch (Exception e) {
                sb.append("\nEnter your choice (1-5): ").append(input).append("\n\n");
                sb.append("JOptionPane Dialog:\nInvalid choice!");
            }
            return sb.toString();
        }
        return executeCustomMedium(lessonId, input);
    }

    private static String executeCustomAdvanceGui(int lessonId, String input) {
        if (lessonId == 1) {
            String[] menu = {"Burger", "Pizza", "Fries", "Chicken", "Pasta"};
            int[] prices = {80, 120, 50, 100, 90};
            int[] orders = new int[5];

            StringBuilder sb = new StringBuilder("RESTAURANT MENU\n\n");
            for (int i = 0; i < menu.length; i++) {
                sb.append(i + 1).append(". ").append(menu[i]).append(" - ₱").append(prices[i]).append("\n");
            }

            String[] tokens = input.split("[,\\s]+");
            sb.append("\nHow many items do you want to order? ").append(tokens.length).append("\n");

            int total = 0;
            StringBuilder receipt = new StringBuilder("ORDER RECEIPT\n\n");

            for (String token : tokens) {
                try {
                    int choice = Integer.parseInt(token.trim());
                    sb.append("Enter item number: ").append(choice).append("\n");
                    if (choice >= 1 && choice <= 5) {
                        orders[choice - 1]++;
                        total += prices[choice - 1];
                        receipt.append(menu[choice - 1]).append(" - ₱").append(prices[choice - 1]).append("\n");
                    } else {
                        sb.append("Invalid choice!\n");
                    }
                } catch (Exception ignored) {}
            }

            receipt.append("\nTotal: ₱").append(total);
            sb.append("\nJOptionPane Dialog:\n").append(receipt);
            return sb.toString();
        }
        return executeCustomAdvance(lessonId, input);
    }

    private static String executeCustomMedium(int lessonId, String input) {
        switch (lessonId) {
            case 1:
                try {
                    String[] parts = input.split(",");
                    int[] nums = new int[parts.length];
                    for (int i = 0; i < parts.length; i++) nums[i] = Integer.parseInt(parts[i].trim());
                    int max = nums[0];
                    for (int n : nums) if (n > max) max = n;
                    return "Elements: " + Arrays.toString(nums) + "\nLargest number: " + max;
                } catch (Exception e) {
                    return "Error: " + e.getMessage();
                }
            case 2:
                try {
                    String[] parts = input.split(",");
                    StringBuilder sb = new StringBuilder("Popping in LIFO order:\n");
                    for (int i = parts.length - 1; i >= 0; i--) sb.append(parts[i].trim()).append(" ");
                    return sb.toString();
                } catch (Exception e) {
                    return "Error: " + e.getMessage();
                }
            case 3:
                try {
                    String[] parts = input.split(",");
                    StringBuilder sb = new StringBuilder("Processing FIFO Queue:\n");
                    for (String p : parts) sb.append("Printing: ").append(p.trim()).append("\n");
                    return sb.toString();
                } catch (Exception e) {
                    return "Error: " + e.getMessage();
                }
            case 7:
                try {
                    int target = Integer.parseInt(input);
                    int[] arr = {10, 20, 30, 40, 50, 60, 70, 80};
                    int low = 0, high = arr.length - 1, found = -1;
                    while (low <= high) {
                        int mid = low + (high - low) / 2;
                        if (arr[mid] == target) { found = mid; break; }
                        else if (target < arr[mid]) high = mid - 1;
                        else low = mid + 1;
                    }
                    return found != -1 ? "Found " + target + " at index " + found : target + " not found!";
                } catch (Exception e) {
                    return "Error: " + e.getMessage();
                }
            default:
                return "Custom execution completed for input: " + input;
        }
    }

    private static String executeCustomAdvance(int lessonId, String input) {
        switch (lessonId) {
            case 1:
                try {
                    String[] parts = input.split(",");
                    int[] nums = new int[parts.length];
                    for (int i = 0; i < parts.length; i++) nums[i] = Integer.parseInt(parts[i].trim());
                    StringBuilder sb = new StringBuilder("Memory Addresses (Base = 200, 4 bytes each):\n");
                    for (int i = 0; i < nums.length; i++) {
                        sb.append("Index [").append(i).append("] Address: ").append(200 + (i * 4)).append("\n");
                    }
                    Arrays.sort(nums);
                    sb.append("\nSorted Array:\n").append(Arrays.toString(nums));
                    return sb.toString();
                } catch (Exception e) {
                    return "Error: " + e.getMessage();
                }
            case 2:
                StringBuilder sb = new StringBuilder();
                for (int i = input.length() - 1; i >= 0; i--) sb.append(input.charAt(i));
                return "Original String: " + input + "\nReversed String: " + sb.toString();
            case 6:
                try {
                    String[] parts = input.split(",");
                    Integer[] table = new Integer[10];
                    StringBuilder log = new StringBuilder("Linear Probing Hash Table:\n");
                    for (String p : parts) {
                        int val = Integer.parseInt(p.trim());
                        int idx = val % 10;
                        while (table[idx] != null) idx = (idx + 1) % 10;
                        table[idx] = val;
                        log.append("Key ").append(val).append(" placed at slot ").append(idx).append("\n");
                    }
                    return log.toString();
                } catch (Exception e) {
                    return "Error: " + e.getMessage();
                }
            case 7:
                char target = Character.toUpperCase(input.trim().charAt(0));
                char[] alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
                StringBuilder log = new StringBuilder();
                int low = 0, high = alpha.length - 1, step = 1;
                while (low <= high) {
                    int mid = low + (high - low) / 2;
                    log.append("Step ").append(step++).append(": Subarray [").append(alpha[low])
                       .append("..").append(alpha[high]).append("], Mid = '").append(alpha[mid]).append("'\n");
                    if (alpha[mid] == target) {
                        log.append("--> Match found! Book '").append(target).append("' located at index ").append(mid).append("\n");
                        return log.toString();
                    } else if (target < alpha[mid]) high = mid - 1;
                    else low = mid + 1;
                }
                log.append("Not found in A-Z\n");
                return log.toString();
            default:
                return "Advanced execution completed for: " + input;
        }
    }

    public static String executeAllLessons() {
        StringBuilder sb = new StringBuilder();
        sb.append("============================================================\n");
        sb.append("     DATA STRUCTURES & ALGORITHMS - ALL LESSON SOLUTIONS    \n");
        sb.append("============================================================\n\n");
        for (LessonItem item : getAllLessons()) {
            sb.append("------------------------------------------------------------\n");
            sb.append(item.getTag()).append(": ").append(item.getTitle()).append(" (").append(item.getSlideRef()).append(")\n");
            sb.append("------------------------------------------------------------\n");
            sb.append("[EASY]\n").append(item.getEasyOutput()).append("\n\n");
            sb.append("[MEDIUM]\n").append(item.getMediumOutput()).append("\n\n");
            sb.append("[ADVANCE]\n").append(item.getAdvanceOutput()).append("\n\n");
        }
        return sb.toString();
    }
}
