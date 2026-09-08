package com.dsa.app;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.dsa.master.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class JavaIdeActivity extends AppCompatActivity implements DialogBridge.Listener {

    private EditText etJavaCode;
    private TextInputEditText etStdin;
    private TextView tvTerminalOutput;
    private TextView tvIdeStatus;
    private MaterialButton btnRun;
    private MaterialButton btnTemplates;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private final Handler mainHandler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java_ide);

        etJavaCode = findViewById(R.id.etJavaCode);
        etStdin = findViewById(R.id.etStdin);
        tvTerminalOutput = findViewById(R.id.tvTerminalOutput);
        tvIdeStatus = findViewById(R.id.tvIdeStatus);
        btnRun = findViewById(R.id.btnRun);
        btnTemplates = findViewById(R.id.btnTemplates);
        ImageButton btnBack = findViewById(R.id.btnBack);
        TextView tvClearCode = findViewById(R.id.tvClearCode);
        TextView tvClearOutput = findViewById(R.id.tvClearOutput);
        TextView tvCopyOutput = findViewById(R.id.tvCopyOutput);

        btnBack.setOnClickListener(v -> finish());
        tvClearCode.setOnClickListener(v -> etJavaCode.setText(""));
        tvClearOutput.setOnClickListener(v -> tvTerminalOutput.setText("// Terminal cleared. Press 'Run' to execute."));
        tvCopyOutput.setOnClickListener(v -> {
            CharSequence text = tvTerminalOutput.getText();
            if (text != null && text.length() > 0) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("Execution Output", text);
                clipboard.setPrimaryClip(clip);
                Toast.makeText(this, "Output copied to clipboard", Toast.LENGTH_SHORT).show();
            }
        });

        // Initialize with Restaurant GUI Template as default
        loadTemplate(0);

        btnRun.setOnClickListener(v -> runCode());
        btnTemplates.setOnClickListener(v -> showTemplatesDialog());

        setupQuickKeys();
    }

    private void setupQuickKeys() {
        bindKey(R.id.keyBraces, "{\n    \n}");
        bindKey(R.id.keyParens, "()");
        bindKey(R.id.keySemi, ";");
        bindKey(R.id.keyQuotes, "\"\"");
        bindKey(R.id.keySout, "System.out.println(\"\");");
        bindKey(R.id.keyScanner, "Scanner input = new Scanner(System.in);\n");
        bindKey(R.id.keyFor, "for (int i = 0; i < n; i++) {\n    \n}");
        bindKey(R.id.keyJOptionPane, "JOptionPane.showMessageDialog(null, \"\");");
    }

    private void bindKey(int buttonId, String insertText) {
        Button btn = findViewById(buttonId);
        if (btn != null) {
            btn.setOnClickListener(v -> {
                int start = Math.max(etJavaCode.getSelectionStart(), 0);
                int end = Math.max(etJavaCode.getSelectionEnd(), 0);
                Editable editable = etJavaCode.getText();
                editable.replace(Math.min(start, end), Math.max(start, end), insertText, 0, insertText.length());
            });
        }
    }

    private void runCode() {
        String code = etJavaCode.getText() != null ? etJavaCode.getText().toString() : "";
        String stdin = etStdin.getText() != null ? etStdin.getText().toString() : "";

        if (code.trim().isEmpty()) {
            Toast.makeText(this, "Please write or select Java code to run", Toast.LENGTH_SHORT).show();
            return;
        }

        btnRun.setEnabled(false);
        tvIdeStatus.setText("● Compiling & Executing...");
        tvTerminalOutput.setText("Compiling code...\nExecuting runtime...\n");

        executor.execute(() -> {
            JavaRunner.ExecutionResult result = JavaRunner.execute(code, stdin, this);

            mainHandler.post(() -> {
                btnRun.setEnabled(true);
                if (result.success) {
                    tvIdeStatus.setText("● Build & Run Successful (" + result.executionTimeMs + "ms)");
                    tvTerminalOutput.setText(result.output.isEmpty() ? "// Program finished with no output." : result.output);
                } else {
                    tvIdeStatus.setText("● Runtime / Syntax Error");
                    tvTerminalOutput.setText(result.output + "\n\nError:\n" + result.error);
                }
            });
        });
    }

    private void showTemplatesDialog() {
        String[] templates = {
                "Restaurant Ordering (JOptionPane GUI)",
                "Scanner & Array Elements (Console)",
                "Java Collections (Stack, Queue, HashMap)",
                "Math & BigInteger Factorial",
                "Clean Blank Class with main()"
        };

        new MaterialAlertDialogBuilder(this)
                .setTitle("Select Java Template")
                .setItems(templates, (dialog, which) -> loadTemplate(which))
                .setNegativeButton("Cancel", null)
                .show();
    }

    private void loadTemplate(int index) {
        switch (index) {
            case 0:
                etJavaCode.setText(
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
                        "        };\n" +
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
                        "            JOptionPane.showMessageDialog(null, \"Invalid choice!\");\n" +
                        "        }\n" +
                        "    }\n" +
                        "}"
                );
                etStdin.setText("2");
                break;
            case 1:
                etJavaCode.setText(
                        "import java.util.Scanner;\n" +
                        "import java.util.Arrays;\n\n" +
                        "public class ArrayScannerDemo {\n" +
                        "    public static void main(String[] args) {\n" +
                        "        Scanner sc = new Scanner(System.in);\n" +
                        "        System.out.print(\"Enter size: \");\n" +
                        "        int n = sc.nextInt();\n" +
                        "        int[] arr = new int[n];\n" +
                        "        for (int i = 0; i < n; i++) {\n" +
                        "            arr[i] = sc.nextInt();\n" +
                        "        }\n" +
                        "        System.out.println(\"Array: \" + Arrays.toString(arr));\n" +
                        "        int sum = 0;\n" +
                        "        for (int x : arr) sum += x;\n" +
                        "        System.out.println(\"Sum = \" + sum);\n" +
                        "    }\n" +
                        "}"
                );
                etStdin.setText("5\n10 20 30 40 50");
                break;
            case 2:
                etJavaCode.setText(
                        "import java.util.*;\n\n" +
                        "public class CollectionsDemo {\n" +
                        "    public static void main(String[] args) {\n" +
                        "        // 1. Stack (LIFO)\n" +
                        "        Stack<String> stack = new Stack<>();\n" +
                        "        stack.push(\"First\");\n" +
                        "        stack.push(\"Second\");\n" +
                        "        stack.push(\"Third\");\n" +
                        "        System.out.println(\"Popped from Stack: \" + stack.pop());\n\n" +
                        "        // 2. HashMap\n" +
                        "        Map<String, Integer> scores = new HashMap<>();\n" +
                        "        scores.put(\"Alice\", 95);\n" +
                        "        scores.put(\"Bob\", 88);\n" +
                        "        System.out.println(\"Scores: \" + scores);\n" +
                        "    }\n" +
                        "}"
                );
                etStdin.setText("");
                break;
            case 3:
                etJavaCode.setText(
                        "import java.math.BigInteger;\n\n" +
                        "public class MathDemo {\n" +
                        "    public static void main(String[] args) {\n" +
                        "        BigInteger factorial = BigInteger.ONE;\n" +
                        "        int number = 25;\n" +
                        "        for (int i = 1; i <= number; i++) {\n" +
                        "            factorial = factorial.multiply(BigInteger.valueOf(i));\n" +
                        "        }\n" +
                        "        System.out.println(number + \"! = \" + factorial);\n" +
                        "    }\n" +
                        "}"
                );
                etStdin.setText("");
                break;
            case 4:
                etJavaCode.setText(
                        "public class Main {\n" +
                        "    public static void main(String[] args) {\n" +
                        "        System.out.println(\"Hello from Android Java IDE!\");\n" +
                        "    }\n" +
                        "}"
                );
                etStdin.setText("");
                break;
        }
    }

    @Override
    public void onMessage(String message) {
        mainHandler.post(() -> {
            new MaterialAlertDialogBuilder(this)
                    .setTitle("JOptionPane.showMessageDialog")
                    .setMessage(message)
                    .setPositiveButton("OK", null)
                    .show();
        });
    }

    @Override
    public String onInput(String prompt) {
        // Returns stdin text if provided
        return etStdin.getText() != null ? etStdin.getText().toString() : "";
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        executor.shutdownNow();
    }
}
