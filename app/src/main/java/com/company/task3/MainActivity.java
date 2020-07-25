package com.company.task3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button Enter;
    EditText Login, Password;


    String log = "admin", pas = "1234"; //аккаунт для входа

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Enter = findViewById(R.id.EntegBT);
        Login = findViewById(R.id.LoginET);
        Password = findViewById(R.id.PasswordET);

        SharedPreferences settings = getPreferences(0);
        String login = settings.getString("Login" , "");
        Login.setText(login); // вставляем в текстовое поле загруженные настройки
        String password = settings.getString("Password" , "");
        Password.setText(password);



        Enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (log.equals(Login.getText().toString()) && pas.equals(Password.getText().toString()) )
                {
                    Intent intent = new Intent(MainActivity.this, PersonalArea.class);
                    startActivity(intent);
                }
                else
                    Toast.makeText(MainActivity.this, "Неверный логин или пароль", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        //получаем содержимое текстового поля
        String login_str = Login.getText().toString();
        String password_str = Password.getText().toString();
        // загружаем редактор настроек
        SharedPreferences settings = getPreferences(0);
        SharedPreferences.Editor editor = settings.edit();
        //записывем в редактор настройки
        editor.putString("Login", login_str);
        editor.putString("Password", password_str);
        // сохраняем данные из редактора
        editor.apply();

    }
}