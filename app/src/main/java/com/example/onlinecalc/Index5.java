package com.example.onlinecalc;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Index5 extends AppCompatActivity {

    EditText vess;
    EditText jel;
    float IndexF;
    String Result;
    float savedVES;

    SharedPreferences sPref;
    public static final String APP_PREFERENCES = "MySettings";
    public static final String SAVED_INDEX = "INDEX5";
    public static final String SAVED_RESULT = "RESULT";
    public static final String SAVED_NUMBER = "NUMBER";
    public static final String SAVED_VES = "VES";
    public static final String SAVED_JEL = "JEL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index5);

        vess = (EditText)findViewById(R.id.ves);
        vess.setInputType(2);
        jel = (EditText)findViewById(R.id.jel);
        jel.setInputType(2);
    }

    private void saveText()
    {
        sPref = getSharedPreferences(APP_PREFERENCES,MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putFloat(SAVED_INDEX, IndexF);
        ed.putFloat(SAVED_JEL, Float.parseFloat(jel.getText().toString()));
        ed.putFloat(SAVED_VES, Float.parseFloat(vess.getText().toString()));
        ed.putString(SAVED_NUMBER, "5");
        ed.putString(SAVED_RESULT, Result);
        ed.commit();
    }

    public void onClickMe(View v) {
        if (!vess.getText().toString().equals("") && !jel.getText().toString().equals(""))
        {
            if (Float.parseFloat(vess.getText().toString()) > 0 && Float.parseFloat(jel.getText().toString()) > 0)
            {
                float ves = Float.parseFloat(vess.getText().toString());
                float Jel = Float.parseFloat(jel.getText().toString());
                IndexF = Jel/ves;
                if (IndexF < 50) Result = "Недостаточность кислорода обеспечения организма, недостаточной жизненной емкости легких, либо избыточной массе тела";
                else if (IndexF >= 50 && IndexF <= 61) Result = "Норма";
                else if (IndexF >= 62) Result = "Выше нормы";
                saveText();
                Intent i;
                i = new Intent(this, Result.class);
                startActivity(i);
            }
            else
            {
                Toast.makeText(getApplicationContext(), "Неверная ёмкость или сердцебиение!", Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Введите ёмкость и сердцебиение!", Toast.LENGTH_SHORT).show();
        }
    }
}