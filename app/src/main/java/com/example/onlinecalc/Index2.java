package com.example.onlinecalc;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Index2 extends AppCompatActivity {

    EditText shagi;
    float IndexF;
    String Result;

    SharedPreferences sPref;
    public static final String APP_PREFERENCES = "MySettings";
    public static final String SAVED_INDEX = "INDEX2";
    public static final String SAVED_RESULT = "RESULT";
    public static final String SAVED_NUMBER = "NUMBER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index2);

        shagi = (EditText)findViewById(R.id.SHAGI);
        shagi.setInputType(2);
    }

    private void saveText()
    {
        sPref = getSharedPreferences(APP_PREFERENCES,MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putFloat(SAVED_INDEX, IndexF);
        ed.putString(SAVED_NUMBER, "2");
        ed.putString(SAVED_RESULT, Result);
        ed.commit();
    }

    public void onClickMe(View v)
    {
        if (!shagi.getText().toString().equals(""))
        {
            if (Integer.valueOf(shagi.getText().toString()) > 0)
            {
                float shag = Float.parseFloat(shagi.getText().toString());
                IndexF = shag/365;
                if (IndexF <= 5000) Result = "Сидячая работа";
                else if (IndexF >= 7500 && IndexF <= 9999) Result = "Несколько активная работа";
                else if (IndexF >= 10000 && IndexF <= 12000) Result = "Активный образ жизни";
                else if (IndexF >= 12500) Result = "Очень активный образ жизни";
                saveText();
                Intent i;
                i = new Intent(this, Result.class);
                startActivity(i);
            }
            else
            {
                Toast.makeText(getApplicationContext(), "Неверное кол-во шагов!", Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Введите кол-во шагов!", Toast.LENGTH_SHORT).show();
        }
    }
}