package com.example.onlinecalc;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Index7 extends AppCompatActivity {

    EditText DAD;
    EditText CSS;
    float IndexF;
    String Result;

    SharedPreferences sPref;
    public static final String APP_PREFERENCES = "MySettings";
    public static final String SAVED_INDEX = "INDEX7";
    public static final String SAVED_RESULT = "RESULT";
    public static final String SAVED_NUMBER = "NUMBER";
    public static final String SAVED_DAD = "DAD";
    public static final String SAVED_CSS = "CSS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index7);

        DAD = (EditText)findViewById(R.id.dad);
        DAD.setInputType(2);
        CSS = (EditText)findViewById(R.id.css);
        CSS.setInputType(2);
    }

    private void saveText()
    {
        sPref = getSharedPreferences(APP_PREFERENCES,MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putFloat(SAVED_INDEX, IndexF);
        ed.putString(SAVED_NUMBER, "7");
        ed.putString(SAVED_RESULT, Result);
        ed.putFloat(SAVED_DAD, Float.parseFloat(DAD.getText().toString()));
        ed.putFloat(SAVED_CSS, Float.parseFloat(CSS.getText().toString()));
        ed.commit();
    }

    public void onClickMe(View view) {
        if (!CSS.getText().toString().equals("") && !DAD.getText().toString().equals(""))
        {
            if (Float.parseFloat(CSS.getText().toString()) > 0 && Float.parseFloat(DAD.getText().toString()) > 0)
            {
                float Css = Float.parseFloat(CSS.getText().toString());
                float Dad = Float.parseFloat(DAD.getText().toString());
                IndexF = 100*(1-(Dad/Css));
                if (IndexF == 0) Result = "Норма равна 0 усл. ед., что демонстрирует оптимальный уровень вегетативной регуляции сердечно-сосудистой системы";
                else if (IndexF >= 31) Result = "Выраженная симпатикотония";
                else if (IndexF >= 16 && IndexF <= 30) Result = "Симпатикотония";
                else if (IndexF >= -15 && IndexF <= 15) Result = "Уравновешенность симпатических и парасимпатических влияний";
                else if (IndexF <= -31) Result = "Выраженная парасимпатикотония";
                else if (IndexF <= -16 && IndexF >= -30) Result = "Парасимпатикотония";
                saveText();
                Intent i;
                i = new Intent(this, Result.class);
                startActivity(i);
            }
            else
            {
                Toast.makeText(getApplicationContext(), "Неверное сердцебиение или давление!", Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Введите сердцебиение и давление!", Toast.LENGTH_SHORT).show();
        }
    }
}