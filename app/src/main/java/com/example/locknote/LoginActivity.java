package com.example.locknote;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    private int progress = 0;
    private String pin = "";
    private SharedPreferences savedPin;
    private static final String PREF_USER_PIN = "pref_user_pin";
    private static final String PREF_PIN = "pref_pin";
    private EditText edTxt;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        progressBar = findViewById(R.id.bar_progress);
        edTxt = findViewById(R.id.edTxt_pinCode);
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        assert imm != null;
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);

        edTxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                switch (before) {
                    case 0:
                        progress += 1;
                        break;
                    case 1:
                        progress -= 1;
                        break;
                }
                progressBar.setProgress(progress);
                if (isProgressDone()) {
                    pin = edTxt.getText().toString();
                    if (isPinSaved()) {
                        checkPin();
                    } else {
                        savePin();
                        toLogin();
                    }
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });


    }

    private void toLogin() {
        Intent toLogin = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(toLogin);
        finish();
    }

    private boolean isProgressDone() {
        return (progress == 4);
    }

    private boolean isPinSaved() {
        return (savedPin != null);
    }

    private void checkPin() {
        if (pin.equals(savedPin.getString(PREF_PIN, ""))) {
            toLogin();
        } else {
            edTxt.setText("");
            progress = 0;
            progressBar.setProgress(progress);
            Toast.makeText(this, R.string.toast_wrong_pin, Toast.LENGTH_SHORT).show();
        }
    }

    private void savePin() {
        savedPin = getSharedPreferences(PREF_USER_PIN, MODE_PRIVATE);
        savedPin.edit()
                .putString(PREF_PIN, pin)
                .apply();

    }
}