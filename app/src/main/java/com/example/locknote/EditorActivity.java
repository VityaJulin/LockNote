package com.example.locknote;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;

public class EditorActivity extends AppCompatActivity {
    private final Calendar todayCalendar = Calendar.getInstance();
    private DatePickerDialog datePickerDialog;
    private TextView deadline;
    private TextInputEditText title;
    private TextInputEditText body;
    private String noteTitle = "";
    private String noteBody = "";
    private String noteDeadline = "";
    StorageComponent component;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        component = App.getComponent();

        deadline = findViewById(R.id.text_deadline);
        title = findViewById(R.id.text_note_title);
        body = findViewById(R.id.text_note_body);
        body.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        assert imm != null;
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);

        BottomAppBar barEditor = findViewById(R.id.barEditor);
        FloatingActionButton fabConfirm = findViewById(R.id.fabConfirm);
        setSupportActionBar(barEditor);

        noteTitle = title.getText().toString();
        noteBody = body.getText().toString();
        noteDeadline = deadline.getText().toString();

        fabConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNote();
                Toast.makeText(EditorActivity.this, R.string.toast_note_saved, Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_back:
                finish();
                return true;
            case R.id.menu_alert:
                showCalendar();
                return true;
            case R.id.menu_delete_alert:
                deadline.setText("");
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
        DatePickerDialog.OnDateSetListener onDateSet = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                todayCalendar.set(Calendar.YEAR, year);
                todayCalendar.set(Calendar.MONTH, month);
                todayCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                setInitialDateTime();
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

    private void setInitialDateTime() {
        deadline.setText(DateUtils.formatDateTime(this,
                todayCalendar.getTimeInMillis(),
                DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR));
    }

    private void addNote() {
        if (body.equals("")) {
            Toast.makeText(this, R.string.toast_add_your_note, Toast.LENGTH_SHORT).show();
            return;
        }

        Executor.IOThread(new Runnable() {
            @Override
            public void run() {
                Note note = new Note(noteTitle, noteBody, noteDeadline);
                component.getStorage().getNoteDao().insertNote(note);
            }
        });
    }
}