package com.dsa.app;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.dsa.master.R;
import com.google.android.material.button.MaterialButton;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView rvLessons = findViewById(R.id.rvLessons);
        MaterialButton btnRunAll = findViewById(R.id.btnRunAll);
        MaterialButton btnOpenBook = findViewById(R.id.btnOpenBook);

        List<LessonItem> lessons = LessonRepository.getAllLessons();
        rvLessons.setLayoutManager(new LinearLayoutManager(this));
        rvLessons.setAdapter(new LessonAdapter(this, lessons));

        btnRunAll.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, LessonDetailActivity.class);
            intent.putExtra(LessonDetailActivity.EXTRA_LESSON_ID, 0);
            startActivity(intent);
        });

        if (btnOpenBook != null) {
            btnOpenBook.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity.this, BookActivity.class);
                startActivity(intent);
            });
        }

        if (getIntent().getBooleanExtra("scroll_to_bottom", false)) {
            rvLessons.post(() -> rvLessons.scrollToPosition(lessons.size()));
        }
    }
}
