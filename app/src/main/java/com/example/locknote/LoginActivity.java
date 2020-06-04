package com.example.locknote;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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