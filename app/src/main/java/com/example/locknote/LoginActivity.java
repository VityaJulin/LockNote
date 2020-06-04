package com.example.locknote;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {
    private int progress = 0;
    private String pin = "";
    private SharedPreferences savedPin;
    private static final String PREF_USER_PIN = "pref_user_pin";
    private static final String PREF_PIN = "pref_pin";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button num1 = findViewById(R.id.btn_1);
        Button delete = findViewById(R.id.btn_delete);
        final ProgressBar progressBar = findViewById(R.id.bar_progress);

        final EditText edTxt = findViewById(R.id.edTxt_pinCode);

        /*edTxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                progress += 1;
                progressBar.setProgress(progress);
                if (progress == 4) {
                    finish();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });*/

        edTxt.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == 66) {
                    progress += 1;
                    progressBar.setProgress(progress);
                    return true;
                }
                if (keyCode == 4) {
                    progress -= 1;
                    progressBar.setProgress(progress);
                }
                return false;
            }
        });

        num1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pin += "1";
                progress += 1;
                progressBar.setProgress(progress);
                if (isProgressDone()) {
                    return;
                }
                if (ifPinSaved()) {
                    checkPin();
                }
                savePin();
                toLogin();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (progress > 0) {
                    progress -= 1;
                    progressBar.setProgress(progress);
                }
                return;
            }
        });
    }

    private void toLogin() {
        Intent toLogin = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(toLogin);
    }

    private boolean isProgressDone() {
        return (progress < 4);
    }

    private boolean ifPinSaved() {
        return (savedPin != null);
    }

    private void checkPin() {
        if (pin.equals(savedPin.getString(PREF_PIN, ""))) {

        }
        Toast.makeText(this, "Wrong pin", Toast.LENGTH_SHORT).show();
    }

    private void savePin() {
        savedPin = getSharedPreferences(PREF_USER_PIN, MODE_PRIVATE);
        savedPin.edit()
                .putString(PREF_PIN, pin)
                .apply();

    }
}