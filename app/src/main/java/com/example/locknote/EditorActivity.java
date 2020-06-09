package com.example.locknote;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;

public class EditorActivity extends AppCompatActivity {
    private TextInputEditText title;
    private TextInputEditText body;
    private BottomAppBar barEditor;
    private FloatingActionButton fabConfirm;
    private DatePickerDialog datePickerDialog;
    private TextView deadline;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        deadline = findViewById(R.id.text_deadline);
        title = findViewById(R.id.text_note_title);
        body = findViewById(R.id.text_note_body);
        body.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);

        barEditor = findViewById(R.id.barEditor);
        fabConfirm = findViewById(R.id.fabConfirm);
        setSupportActionBar(barEditor);


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_back:
                Intent intent = new Intent(EditorActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                return true;
            case R.id.menu_alert:
                showCalendar();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bottom_navigation_menu_editor, menu);
        return true;
    }

    public void showCalendar() {
        final Calendar todayCalendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener onDateSet = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                deadline.setText("42");
                datePickerDialog.dismiss();
            }
        };

        datePickerDialog = new DatePickerDialog(
                this,
                onDateSet,
                todayCalendar.get(Calendar.YEAR),
                todayCalendar.get(Calendar.MONTH),
                todayCalendar.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();

    }
}