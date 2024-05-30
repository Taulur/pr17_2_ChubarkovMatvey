package com.example.pr17_1_chubarkov

import android.content.Intent
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity;




class MainScreen : AppCompatActivity() {


    private lateinit var login:EditText
    private lateinit var pass:EditText
    private lateinit var pref:SharedPreferences
    private lateinit var text1:TextView
    private lateinit var text2:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)

        login = findViewById(R.id.login);
        pass = findViewById(R.id.pass);
        text1 = findViewById(R.id.text1);
        text2 = findViewById(R.id.text2);


    }

    fun handler(v: View) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Выбор Действия")
            .setMessage("Выберите действие.")
            .setCancelable(true)

            .setPositiveButton("Сохранить") { _, _ ->
                pref = getPreferences(MODE_PRIVATE)
                val ed = pref.edit()
                ed.putString("login", login.text.toString())
                ed.putString("password", pass.text.toString())
                ed.apply()

                text1.text = (pref.getString("login", ""))
                text2.text = (pref.getString("password", ""))

               val alert = AlertDialog.Builder(this)
              .setTitle("Успешно")
               .setMessage("Введенные данные сохранены.")
               .setPositiveButton("Продолжить",null)
                    .create()
              .show()
            }

            .setNegativeButton("Загрузить") { _,_ ->
                pref=getPreferences(MODE_PRIVATE)
                login.setText(pref.getString("login",""))
                pass.setText(pref.getString("password",""))
                val alert = AlertDialog.Builder(this)
                .setTitle("Успешно")
                .setMessage("Сохранненые данные загружены.")
                .setPositiveButton("Продолжить",null)
                .create()
                .show()
            }
        builder.create()
        builder.show()

    }

    fun next(view: View) {
        if (login.text.toString().isNotEmpty() && pass.text.toString().isNotEmpty())
        {
            val intent = Intent(this,GeometryScreen::class.java)
            startActivity(intent)
        }
        else
        {
            val alert = AlertDialog.Builder(this)
                .setTitle("Ошибка")
                .setMessage("Введите логин или пароль")
                .setPositiveButton("Продолжить",null)
                .create()
                .show()

        }


    }
}