package com.dsa.app;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import com.dsa.master.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class LessonDetailActivity extends AppCompatActivity {

    public static final String EXTRA_LESSON_ID = "extra_lesson_id";

    private ImageButton btnBack;
    private TextView tvDetailTitle;
    private TextView tvDetailBadge;
    private TextView tvTheoryContent;

    // Mode Selector (Console vs GUI)
    private LinearLayout layoutModeSelector;
    private MaterialButton btnModeConsole;
    private MaterialButton btnModeGui;
    private boolean isGuiMode = false;

    // Level Selector (Easy, Medium, Advance)
    private LinearLayout layoutLevelSelector;
    private MaterialButton btnLevelEasy;
    private MaterialButton btnLevelMedium;
    private MaterialButton btnLevelAdvance;

    private TextInputLayout tilInput;
    private TextInputEditText etInput;
    private MaterialButton btnRunCustom;
    private MaterialButton btnRunDefault;
    private TextView tvTerminalOutput;
    private TextView tvCodeContent;

    private int lessonId = 1;
    private int currentLevel = 0; // 0 = Easy, 1 = Medium, 2 = Advance
    private LessonItem currentLesson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_detail);

        btnBack = findViewById(R.id.btnBack);
        tvDetailTitle = findViewById(R.id.tvDetailTitle);
        tvDetailBadge = findViewById(R.id.tvDetailBadge);
        tvTheoryContent = findViewById(R.id.tvTheoryContent);

        layoutModeSelector = findViewById(R.id.layoutModeSelector);
        btnModeConsole = findViewById(R.id.btnModeConsole);
        btnModeGui = findViewById(R.id.btnModeGui);

        layoutLevelSelector = findViewById(R.id.layoutLevelSelector);
        btnLevelEasy = findViewById(R.id.btnLevelEasy);
        btnLevelMedium = findViewById(R.id.btnLevelMedium);
        btnLevelAdvance = findViewById(R.id.btnLevelAdvance);

        tilInput = findViewById(R.id.tilInput);
        etInput = findViewById(R.id.etInput);
        btnRunCustom = findViewById(R.id.btnRunCustom);
        btnRunDefault = findViewById(R.id.btnRunDefault);
        tvTerminalOutput = findViewById(R.id.tvTerminalOutput);
        tvCodeContent = findViewById(R.id.tvCodeContent);

        btnBack.setOnClickListener(v -> finish());

        lessonId = getIntent().getIntExtra(EXTRA_LESSON_ID, 1);

        if (lessonId == 0) {
            setupMasterView();
        } else {
            setupSingleLessonView(lessonId);
        }
    }

    private void setupMasterView() {
        if (layoutModeSelector != null) layoutModeSelector.setVisibility(View.GONE);
        if (layoutLevelSelector != null) layoutLevelSelector.setVisibility(View.GONE);

        tvDetailTitle.setText("Master Report (All Lessons)");
        tvDetailBadge.setText("FULL REPORT");
        tvTheoryContent.setText("Executing all 8 DSA Week 2 lessons across Easy, Medium, and Advance levels:\n" +
                "1. Array Data Structure\n" +
                "2. Stack Data Structure (LIFO)\n" +
                "3. Queue Data Structure (FIFO)\n" +
                "4. List ADT Operations\n" +
                "5. Singly Linked List\n" +
                "6. Hashing Data Structure\n" +
                "7. Searching & Sorting\n" +
                "8. Graph & Dijkstra Shortest Path");
        tilInput.setVisibility(View.GONE);
        btnRunCustom.setVisibility(View.GONE);
        btnRunDefault.setText("Re-run All Lessons");
        tvCodeContent.setText("// Check individual lessons to view Console and GUI (JOptionPane) source codes.\n" +
                "// All lesson files are located in package com.dsa.lessons and desktop_gui_lessons.");

        btnRunDefault.setOnClickListener(v -> {
            tvTerminalOutput.setText(LessonRepository.executeAllLessons());
        });

        tvTerminalOutput.setText(LessonRepository.executeAllLessons());
    }

    private void setupSingleLessonView(int id) {
        for (LessonItem item : LessonRepository.getAllLessons()) {
            if (item.getId() == id) {
                currentLesson = item;
                break;
            }
        }

        if (currentLesson == null) {
            tvDetailTitle.setText("Lesson Not Found");
            return;
        }

        tvDetailTitle.setText(currentLesson.getTitle());
        tvTheoryContent.setText(currentLesson.getTheory());

        btnModeConsole.setOnClickListener(v -> selectMode(false));
        btnModeGui.setOnClickListener(v -> selectMode(true));

        btnLevelEasy.setOnClickListener(v -> selectLevel(0));
        btnLevelMedium.setOnClickListener(v -> selectLevel(1));
        btnLevelAdvance.setOnClickListener(v -> selectLevel(2));

        btnRunDefault.setOnClickListener(v -> {
            String output = LessonRepository.executeLessonLevel(currentLesson.getId(), currentLevel, null, isGuiMode);
            tvTerminalOutput.setText(output);
            if (isGuiMode) {
                showGuiDialog(output);
            }
        });

        btnRunCustom.setOnClickListener(v -> {
            String input = etInput.getText() != null ? etInput.getText().toString().trim() : "";
            String output = LessonRepository.executeLessonLevel(currentLesson.getId(), currentLevel, input, isGuiMode);
            tvTerminalOutput.setText(output);
            if (isGuiMode) {
                showGuiDialog(output);
            }
        });

        // Initialize with Intent extras or default to Console Mode, Easy level
        boolean initialGui = getIntent().getBooleanExtra("is_gui", false);
        currentLevel = getIntent().getIntExtra("initial_level", 0);
        selectMode(initialGui);
    }

    private void selectMode(boolean guiMode) {
        isGuiMode = guiMode;

        int activeColor = ContextCompat.getColor(this, R.color.primary);
        int inactiveColor = ContextCompat.getColor(this, R.color.surface);
        int activeTextColor = ContextCompat.getColor(this, android.R.color.white);
        int inactiveTextColor = ContextCompat.getColor(this, R.color.text_secondary);

        btnModeConsole.setBackgroundTintList(ColorStateList.valueOf(!isGuiMode ? activeColor : inactiveColor));
        btnModeConsole.setTextColor(!isGuiMode ? activeTextColor : inactiveTextColor);

        btnModeGui.setBackgroundTintList(ColorStateList.valueOf(isGuiMode ? activeColor : inactiveColor));
        btnModeGui.setTextColor(isGuiMode ? activeTextColor : inactiveTextColor);

        selectLevel(currentLevel);
    }

    private void selectLevel(int level) {
        currentLevel = level;

        int activeColor = ContextCompat.getColor(this, R.color.secondary);
        int inactiveColor = ContextCompat.getColor(this, R.color.surface);
        int activeTextColor = ContextCompat.getColor(this, android.R.color.white);
        int inactiveTextColor = ContextCompat.getColor(this, R.color.text_secondary);

        btnLevelEasy.setBackgroundTintList(ColorStateList.valueOf(level == 0 ? activeColor : inactiveColor));
        btnLevelEasy.setTextColor(level == 0 ? activeTextColor : inactiveTextColor);

        btnLevelMedium.setBackgroundTintList(ColorStateList.valueOf(level == 1 ? activeColor : inactiveColor));
        btnLevelMedium.setTextColor(level == 1 ? activeTextColor : inactiveTextColor);

        btnLevelAdvance.setBackgroundTintList(ColorStateList.valueOf(level == 2 ? activeColor : inactiveColor));
        btnLevelAdvance.setTextColor(level == 2 ? activeTextColor : inactiveTextColor);

        String modeTag = isGuiMode ? "GUI" : "CONSOLE";
        String levelTag = level == 0 ? "EASY" : (level == 1 ? "MEDIUM" : "ADVANCE");
        tvDetailBadge.setText(currentLesson.getTag() + " • " + modeTag + " • " + levelTag);

        // Update Code Display based on Mode & Level
        if (isGuiMode) {
            if (level == 0) tvCodeContent.setText(currentLesson.getEasyGuiCode());
            else if (level == 1) tvCodeContent.setText(currentLesson.getMediumGuiCode());
            else tvCodeContent.setText(currentLesson.getAdvanceGuiCode());
        } else {
            if (level == 0) tvCodeContent.setText(currentLesson.getEasyCode());
            else if (level == 1) tvCodeContent.setText(currentLesson.getMediumCode());
            else tvCodeContent.setText(currentLesson.getAdvanceCode());
        }

        // Configure Input & Output
        if (level == 0) {
            tvTerminalOutput.setText(isGuiMode ? currentLesson.getEasyGuiOutput() : currentLesson.getEasyOutput());
            tilInput.setVisibility(View.GONE);
            btnRunCustom.setVisibility(View.GONE);
            btnRunDefault.setText(isGuiMode ? "Show JOptionPane Dialog" : "Run Easy Demo");
        } else if (level == 1) {
            tvTerminalOutput.setText(isGuiMode ? currentLesson.getMediumGuiOutput() : currentLesson.getMediumOutput());
            tilInput.setVisibility(View.VISIBLE);
            btnRunCustom.setVisibility(View.VISIBLE);
            tilInput.setHint(isGuiMode ? currentLesson.getMediumGuiInputHint() : currentLesson.getMediumInputHint());
            etInput.setText(isGuiMode ? currentLesson.getMediumGuiDefaultInput() : currentLesson.getMediumDefaultInput());
            btnRunDefault.setText(isGuiMode ? "Show JOptionPane Dialog" : "Run Medium Demo");
        } else {
            tvTerminalOutput.setText(isGuiMode ? currentLesson.getAdvanceGuiOutput() : currentLesson.getAdvanceOutput());
            tilInput.setVisibility(View.VISIBLE);
            btnRunCustom.setVisibility(View.VISIBLE);
            tilInput.setHint(isGuiMode ? currentLesson.getAdvanceGuiInputHint() : currentLesson.getAdvanceInputHint());
            etInput.setText(isGuiMode ? currentLesson.getAdvanceGuiDefaultInput() : currentLesson.getAdvanceDefaultInput());
            btnRunDefault.setText(isGuiMode ? "Show JOptionPane Dialog" : "Run Advance Demo");
        }
    }

    private void showGuiDialog(String message) {
        String dialogMsg = message;
        if (message != null && message.contains("JOptionPane Dialog:\n")) {
            dialogMsg = message.substring(message.indexOf("JOptionPane Dialog:\n") + "JOptionPane Dialog:\n".length());
        }
        new MaterialAlertDialogBuilder(this)
                .setTitle("JOptionPane.showMessageDialog")
                .setMessage(dialogMsg)
                .setPositiveButton("OK", null)
                .show();
    }
}
