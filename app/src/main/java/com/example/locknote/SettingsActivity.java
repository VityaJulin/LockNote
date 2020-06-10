package com.example.locknote;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class SettingsActivity extends AppCompatActivity {
    private EditText pin;
    private int progress = 0;
    private String savedPin = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        pin = findViewById(R.id.edTxt_save_pinCode);
        final ProgressBar saveProgressBar = findViewById(R.id.bar_save_progress);
        FloatingActionButton fabSavePin = findViewById(R.id.fabSave);
        BottomAppBar barSettings = findViewById(R.id.barSettings);
        setSupportActionBar(barSettings);

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        assert imm != null;
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);

        pin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.equals("\\d")) {
                    Toast.makeText(SettingsActivity.this, R.string.toast_numbers_only, Toast.LENGTH_SHORT).show();
                }
                switch (before) {
                    case 0:
                        progress += 1;
                        break;
                    case 1:
                        progress -= 1;
                        break;
                }
                saveProgressBar.setProgress(progress);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        fabSavePin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isProgressDone()) {
                    savedPin = pin.getText().toString();
                    Toast.makeText(SettingsActivity.this, savedPin, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SettingsActivity.this, "4", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean isProgressDone() {
        return (progress == 4);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_back:
                Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                return true;
            case R.id.menu_show_pin:
                pin.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                return true;
            case R.id.menu_hide_pin:
                pin.setTransformationMethod(PasswordTransformationMethod.getInstance());
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bottom_navigation_menu_settings, menu);
        return true;
    }
}
