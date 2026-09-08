package com.dsa.app;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.dsa.master.R;
import com.google.android.material.button.MaterialButton;
import java.util.ArrayList;
import java.util.List;

public class ReviewerActivity extends AppCompatActivity {

    private RecyclerView rvReviewer;
    private TextView tvReviewerSubtitle;
    private TextView tvItemCountBadge;
    private MaterialButton btnModeAll, btnModeConcept, btnModeFormula, btnModeQuiz;
    private LinearLayout layoutLessonFilters;

    private int selectedLessonFilter = 0; // 0 means All lessons
    private ReviewerItem.Type selectedType = null; // null means All types
    private final List<ReviewerItem> currentDisplayList = new ArrayList<>();
    private ReviewerAdapter adapter;

    private final String[] lessonLabels = {
        "All Lessons",
        "L1: Array",
        "L2: Stack",
        "L3: Queue",
        "L4: List ADT",
        "L5: Linked List",
        "L6: Hashing",
        "L7: Search & Sort",
        "L8: Graph & Dijkstra",
        "Swing GUI"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviewer);

        ImageButton btnBack = findViewById(R.id.btnBackReviewer);
        if (btnBack != null) {
            btnBack.setOnClickListener(v -> finish());
        }

        rvReviewer = findViewById(R.id.rvReviewer);
        tvReviewerSubtitle = findViewById(R.id.tvReviewerSubtitle);
        tvItemCountBadge = findViewById(R.id.tvItemCountBadge);

        btnModeAll = findViewById(R.id.btnModeAll);
        btnModeConcept = findViewById(R.id.btnModeConcept);
        btnModeFormula = findViewById(R.id.btnModeFormula);
        btnModeQuiz = findViewById(R.id.btnModeQuiz);
        layoutLessonFilters = findViewById(R.id.layoutLessonFilters);

        rvReviewer.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ReviewerAdapter(this, currentDisplayList);
        rvReviewer.setAdapter(adapter);

        setupModeButtons();
        setupLessonFilterChips();
        applyFilter();
    }

    private void setupModeButtons() {
        btnModeAll.setOnClickListener(v -> {
            selectedType = null;
            highlightModeButton(btnModeAll);
            applyFilter();
        });

        btnModeConcept.setOnClickListener(v -> {
            selectedType = ReviewerItem.Type.CONCEPT;
            highlightModeButton(btnModeConcept);
            applyFilter();
        });

        btnModeFormula.setOnClickListener(v -> {
            selectedType = ReviewerItem.Type.FORMULA;
            highlightModeButton(btnModeFormula);
            applyFilter();
        });

        btnModeQuiz.setOnClickListener(v -> {
            selectedType = ReviewerItem.Type.QUIZ;
            highlightModeButton(btnModeQuiz);
            applyFilter();
        });
    }

    private void highlightModeButton(MaterialButton selected) {
        MaterialButton[] buttons = {btnModeAll, btnModeConcept, btnModeFormula, btnModeQuiz};
        int activeColor = ContextCompat.getColor(this, R.color.primary);
        int surfaceColor = ContextCompat.getColor(this, R.color.surface);
        int textSecondary = ContextCompat.getColor(this, R.color.text_secondary);

        for (MaterialButton btn : buttons) {
            if (btn == selected) {
                btn.setBackgroundTintList(ColorStateList.valueOf(activeColor));
                btn.setTextColor(Color.WHITE);
            } else {
                btn.setBackgroundTintList(ColorStateList.valueOf(surfaceColor));
                btn.setTextColor(textSecondary);
            }
        }
    }

    private void setupLessonFilterChips() {
        layoutLessonFilters.removeAllViews();
        for (int i = 0; i < lessonLabels.length; i++) {
            final int lessonIndex = i;
            MaterialButton chip = new MaterialButton(this);
            chip.setText(lessonLabels[i]);
            chip.setTextSize(11);
            chip.setAllCaps(false);
            chip.setCornerRadius(16);
            chip.setElevation(0);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    dpToPx(34)
            );
            params.setMarginEnd(dpToPx(6));
            chip.setLayoutParams(params);

            updateChipStyle(chip, lessonIndex == selectedLessonFilter);

            chip.setOnClickListener(v -> {
                selectedLessonFilter = lessonIndex;
                refreshChipStyles();
                applyFilter();
            });

            layoutLessonFilters.addView(chip);
        }
    }

    private void refreshChipStyles() {
        for (int i = 0; i < layoutLessonFilters.getChildCount(); i++) {
            MaterialButton chip = (MaterialButton) layoutLessonFilters.getChildAt(i);
            updateChipStyle(chip, i == selectedLessonFilter);
        }
    }

    private void updateChipStyle(MaterialButton chip, boolean isSelected) {
        if (isSelected) {
            chip.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.secondary)));
            chip.setTextColor(Color.WHITE);
        } else {
            chip.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.surface)));
            chip.setTextColor(ContextCompat.getColor(this, R.color.text_secondary));
        }
    }

    private void applyFilter() {
        currentDisplayList.clear();
        currentDisplayList.addAll(ReviewerRepository.getItemsByFilter(selectedLessonFilter, selectedType));
        adapter.notifyDataSetChanged();

        tvItemCountBadge.setText(currentDisplayList.size() + " ITEMS");

        String lessonName = lessonLabels[selectedLessonFilter];
        String modeName = selectedType == null ? "All" : selectedType.name();
        tvReviewerSubtitle.setText(lessonName + " • " + modeName + " Mode");
    }

    private int dpToPx(int dp) {
        float density = getResources().getDisplayMetrics().density;
        return Math.round(dp * density);
    }
}
