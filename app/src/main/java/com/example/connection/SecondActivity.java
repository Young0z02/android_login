package com.example.connection;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Button btnMedication1 = findViewById(R.id.btn_medication1);
        Button btnMedication2 = findViewById(R.id.btn_medication2);
        Button btnMedication3 = findViewById(R.id.btn_medication3);

        btnMedication1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 복약기 1과 medication1.json 파일 매핑
                String json = readJsonFile("medication1.json");
                parseJsonAndMapToMedication(json, 1);
            }
        });

        btnMedication2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 복약기 2과 medication2.json 파일 매핑
                String json = readJsonFile("medication2.json");
                parseJsonAndMapToMedication(json, 2);
            }
        });

        btnMedication3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 복약기 3과 medication3.json 파일 매핑
                String json = readJsonFile("medication3.json");
                parseJsonAndMapToMedication(json, 3);
            }
        });
    }

    private String readJsonFile(String fileName) {
        AssetManager assetManager = getAssets();
        String json = null;
        try {
            InputStream is = assetManager.open(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }

    private void parseJsonAndMapToMedication(String json, int medicationNumber) {
        try {
            JSONObject jsonObject = new JSONObject(json);

            // "medicationName" 키를 사용
            String medicineName = jsonObject.optString("medicationName", "알 수 없음");
            String dosage = jsonObject.getString("dosage");
            String schedule = jsonObject.getString("schedule");

            String message = String.format("복약기 %d와 매핑되었습니다.\n약 이름: %s\n용량: %s\n일정: %s",
                    medicationNumber, medicineName, dosage, schedule);
            showToast(message);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
