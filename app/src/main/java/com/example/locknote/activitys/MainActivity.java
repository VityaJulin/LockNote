package com.example.locknote.activitys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.locknote.App;
import com.example.locknote.Executor;
import com.example.locknote.Note;
import com.example.locknote.adapter.NotesDataAdapter;
import com.example.locknote.R;
import com.example.locknote.database.StorageComponent;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Note> notes;
    private NotesDataAdapter adapter;
    private TextInputEditText search;
    private Intent intentEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final StorageComponent component = App.getComponent();

        generateNotes(component);
        intentEditor = new Intent(MainActivity.this, EditorActivity.class);

        search = findViewById(R.id.edTxt_search);
        FloatingActionButton fabAdd = findViewById(R.id.fabConfirm);
        BottomAppBar barMain = findViewById(R.id.barEditor);
        setSupportActionBar(barMain);

        ListView listView = findViewById(R.id.listView);

        adapter = new NotesDataAdapter(this, null);
        listView.setAdapter(adapter);

        Toast.makeText(this, R.string.toast_edit_note_click, Toast.LENGTH_SHORT).show();

        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentEditor.removeExtra("noteIndex");
                startActivity(intentEditor);
                finish();
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                int noteIndex = position;
                intentEditor.putExtra("noteIndex", noteIndex);
                startActivity(intentEditor);
                finish();
                return true;
            }
        });
    }

    private void generateNotes(final StorageComponent component) {
        Executor.IOThread(new Runnable() {
            @Override
            public void run() {
                notes = component.getStorage().getNoteDao().getAllNote();
                for (Note note : notes) {
                    adapter.addNote(note);
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_settings:
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
                return true;
            case R.id.menu_search:
                search.requestFocus();
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                assert imm != null;
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
            case R.id.menu_share:
                Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bottom_navigation_menu_main, menu);
        return true;
    }
}
