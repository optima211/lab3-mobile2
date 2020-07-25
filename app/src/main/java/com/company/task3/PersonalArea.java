package com.company.task3;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PersonalArea extends AppCompatActivity {

    Button exitBt;
    EditText eText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_area);

        Toast.makeText(PersonalArea.this, "Добро пожаловать!", Toast.LENGTH_LONG).show();
        exitBt = findViewById(R.id.exit);
        eText = findViewById(R.id.EText);

        SharedPreferences settings = getPreferences(0);
        String lasttext = settings.getString("LastText" , "");
        eText.setText(lasttext); //вставляе в поле последний текст


        exitBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onPause() { //для сохранения только что введенного текста
        super.onPause();

        //получаем содержимое текстового поля
        String updateText = eText.getText().toString();
        // загружаем редактор настроек
        SharedPreferences settings = getPreferences(0);
        SharedPreferences.Editor edit = settings.edit();
        //записывем в редактор настройки
        edit.putString("LastText", updateText);
        // сохраняем данные из редактора
        edit.apply();
    }
}