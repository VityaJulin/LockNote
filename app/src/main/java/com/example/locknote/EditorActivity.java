package com.example.locknote;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.CheckBox;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class EditorActivity extends AppCompatActivity {
    private TextInputEditText title;
    private TextInputEditText body;
    private CheckBox hasDeadline;
    private CalendarView calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        title = findViewById(R.id.text_note_title);
        body = findViewById(R.id.text_note_body);
        hasDeadline = findViewById(R.id.chBox_deadline);
        calendar = findViewById(R.id.calendar);
    }
}