package com.dsa.app;

import android.os.Bundle;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import com.dsa.master.R;

public class BookActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        ImageButton btnBack = findViewById(R.id.btnBackBook);
        if (btnBack != null) {
            btnBack.setOnClickListener(v -> finish());
        }
    }
}