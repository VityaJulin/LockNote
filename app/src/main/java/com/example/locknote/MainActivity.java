package com.example.locknote;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {
    private FloatingActionButton fab;
    private BottomAppBar bar;
    private NotesDataAdapter adapter;
    private TextInputEditText search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        search = findViewById(R.id.edTxt_search);
        fab = findViewById(R.id.fab);
        bar = findViewById(R.id.bar);
        setSupportActionBar(bar);

        ListView listView = findViewById(R.id.listView);

        adapter = new NotesDataAdapter(this, null);
        listView.setAdapter(adapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_language:
                Toast.makeText(this, "Language", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menu_password:
                Toast.makeText(this, "Lock", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menu_share:
                Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menu_search:
                generateNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bottom_navigation_menu, menu);
        return true;
    }

    private void generateNote() {
        adapter.addNote(new NoteData(
                "Note №" + adapter.getCount(),
                "ellipsize - место в котором можно будет поставить ... если заголовок окажется супердлинным. Может быть в начале, в середине и в конце.",
                ""));
    }

    private void showNote(int position) {
        NoteData itemData = adapter.getItem(position);
        Toast.makeText(MainActivity.this,
                itemData.getNoteTitle(),
                Toast.LENGTH_SHORT).show();
    }
}
