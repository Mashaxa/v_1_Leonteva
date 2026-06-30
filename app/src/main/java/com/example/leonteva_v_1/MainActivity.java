package com.example.leonteva_v_1;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {
    private static final int CAMERA_REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Берем ID прямо из твоего XML
        Button btnExit = findViewById(R.id.btn_exit);
        Button btnOnline = findViewById(R.id.btn_online);
        Button btnCamera = findViewById(R.id.btn_camera);
        ImageView ivSettings = findViewById(R.id.iv_settings);

        // 1. Выход
        btnExit.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
        });

        // 2. Онлайн (Toast)
        btnOnline.setOnClickListener(v -> {
            Toast.makeText(MainActivity.this, "Вы онлайн", Toast.LENGTH_SHORT).show();
        });

        // 3. Системная камера
        btnCamera.setOnClickListener(v -> {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, CAMERA_REQUEST_CODE);
            } else {
                startActivity(new Intent(MediaStore.ACTION_IMAGE_CAPTURE));
            }
        });

        // 4. Переход в настройки
        ivSettings.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, SettingsActivity.class));
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAMERA_REQUEST_CODE && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            startActivity(new Intent(MediaStore.ACTION_IMAGE_CAPTURE));
        } else {
            Toast.makeText(this, "Нет разрешения на камеру", Toast.LENGTH_SHORT).show();
        }
    }
}