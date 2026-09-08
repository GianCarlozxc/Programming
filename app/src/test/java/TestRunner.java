import org.junit.Test;
import static org.junit.Assert.*;
import com.dsa.app.JavaRunner;

public class TestRunner {

    @Test
    public void testJavaRunnerRestaurant() {
        String code = 
            "import java.util.Scanner;\n" +
            "import javax.swing.JOptionPane;\n\n" +
            "public class MediumRestaurant {\n" +
            "    public static void main(String[] args) {\n" +
            "        Scanner input = new Scanner(System.in);\n" +
            "        String[] menu = {\n" +
            "            \"Burger - ₱80\",\n" +
            "            \"Pizza - ₱120\",\n" +
            "            \"Fries - ₱50\",\n" +
            "            \"Chicken - ₱100\",\n" +
            "            \"Pasta - ₱90\"\n" +
            "        };\n" +
            "        int[] prices = {80, 120, 50, 100, 90};\n" +
            "        int choice = input.nextInt();\n" +
            "        System.out.println(\"Selected: \" + menu[choice - 1]);\n" +
            "    }\n" +
            "}";

        JavaRunner.ExecutionResult result = JavaRunner.execute(code, "2\n", null);
        assertTrue("Execution failed: " + result.error, result.success);
        assertTrue("Output: " + result.output, result.output.contains("Selected: Pizza - ₱120"));
    }

    @Test
    public void testJavaRunnerGenerics() {
        String code = 
            "import java.util.*;\n" +
            "List<String> list = new ArrayList<>();\n" +
            "list.add(\"DSA Master\");\n" +
            "System.out.println(list.get(0));\n";

        JavaRunner.ExecutionResult result = JavaRunner.execute(code, "", null);
        assertTrue("Execution failed: " + result.error, result.success);
        assertEquals("DSA Master", result.output.trim());
    }
}
