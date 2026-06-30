package com.example.leonteva_v_1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        DBHelper dbHelper = new DBHelper(this);
        TextInputEditText etLogin = findViewById(R.id.et_login);
        TextInputEditText etPassword = findViewById(R.id.et_password);
        Button btnLogin = findViewById(R.id.btn_login);
        TextView tvRegister = findViewById(R.id.tvRegister);

        btnLogin.setOnClickListener(v -> {
            String login = etLogin.getText().toString().trim();
            String pass = etPassword.getText().toString().trim();

            try {
                if (dbHelper.checkUser(login, pass)) {
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                } else {
                    Toast.makeText(LoginActivity.this, "Неверный логин или пароль", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                Toast.makeText(LoginActivity.this, "Ошибка БД: " + e.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        tvRegister.setOnClickListener(v -> {
            String login = etLogin.getText().toString().trim();
            String pass = etPassword.getText().toString().trim();

            if (login.isEmpty() || pass.isEmpty()) {
                Toast.makeText(LoginActivity.this, "Введите логин и пароль", Toast.LENGTH_SHORT).show();
                return;
            }
            if (dbHelper.registerUser(login, pass)) {
                Toast.makeText(LoginActivity.this, "Пользователь зарегистрирован!", Toast.LENGTH_SHORT).show();
                // После регистрации можно сразу авторизоваться
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
            } else {
                Toast.makeText(LoginActivity.this, "Ошибка регистрации (возможно, логин уже занят)", Toast.LENGTH_SHORT).show();
            }
        });
    }
}