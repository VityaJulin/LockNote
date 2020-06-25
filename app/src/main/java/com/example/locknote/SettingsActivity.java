package com.example.locknote;

import android.content.Context;
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

import static com.example.locknote.LoginActivity.savedPin;

public class SettingsActivity extends AppCompatActivity {
    private static final String PREF_PIN = "pref_pin";
    private EditText pin;
    private int progress = 0;

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
                if (s.equals("\\d")) {
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
                    String newPin = pin.getText().toString();
                    savedPin.edit()
                            .putString(PREF_PIN, newPin)
                            .apply();
                    Toast.makeText(SettingsActivity.this, newPin, Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(SettingsActivity.this, R.string.toast_enter4num, Toast.LENGTH_SHORT).show();
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
                finish();
                return true;
            case R.id.menu_show_pin:
                pin.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                pin.setSelection(pin.getText().length());
                return true;
            case R.id.menu_hide_pin:
                pin.setTransformationMethod(PasswordTransformationMethod.getInstance());
                pin.setSelection(pin.getText().length());
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
