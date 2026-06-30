package com.example.leonteva_v_1;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        ImageView ivBack = findViewById(R.id.iv_back_settings);
        Button btnReset = findViewById(R.id.btn_reset_settings);
        Switch swLoc = findViewById(R.id.switch_location);
        Switch swNotif = findViewById(R.id.switch_notify);
        Switch swNews = findViewById(R.id.switch_news);

        // Кнопка назад
        ivBack.setOnClickListener(v -> finish());

        // Кнопка сброса настроек
        btnReset.setOnClickListener(v -> {
            swLoc.setChecked(true);
            swNotif.setChecked(true);
            swNews.setChecked(true);
            Toast.makeText(SettingsActivity.this, "Настройки сброшены", Toast.LENGTH_SHORT).show();
        });

        // Переключатель местоположения (используем обычный Toast, без лишних файлов)
        swLoc.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (!isChecked) {
                Toast.makeText(SettingsActivity.this, "Отключено отображение моего местоположения", Toast.LENGTH_LONG).show();
            }
        });
    }
}