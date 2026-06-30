package com.example.leonteva_v_1;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class PersonalAreaActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_area);

        Button btnExit = findViewById(R.id.btn_exit);
        Button btnOnline = findViewById(R.id.btn_online);
        Button btnCamera = findViewById(R.id.btn_camera);
        ImageView ivSettings = findViewById(R.id.iv_settings);

        // Выход
        btnExit.setOnClickListener(v -> {
            startActivity(new Intent(PersonalAreaActivity.this, LoginActivity.class));
            finish();
        });

        // Онлайн (Toast)
        btnOnline.setOnClickListener(v -> {
            Toast.makeText(PersonalAreaActivity.this, "Вы онлайн", Toast.LENGTH_SHORT).show();
        });

        // Системная камера
        btnCamera.setOnClickListener(v -> {
            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivity(cameraIntent);
        });

        // Переход в настройки
        ivSettings.setOnClickListener(v -> {
            startActivity(new Intent(PersonalAreaActivity.this, SettingsActivity.class));
        });
    }
}