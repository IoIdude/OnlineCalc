package com.example.onlinecalc;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Index6 extends AppCompatActivity {

    EditText SHTANGE;
    EditText JEL;
    EditText CSS;
    float IndexF;
    String Result;

    SharedPreferences sPref;
    public static final String APP_PREFERENCES = "MySettings";
    public static final String SAVED_INDEX = "INDEX6";
    public static final String SAVED_RESULT = "RESULT";
    public static final String SAVED_NUMBER = "NUMBER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index6);

        SHTANGE = (EditText)findViewById(R.id.shtange);
        SHTANGE.setInputType(2);
        JEL = (EditText)findViewById(R.id.jel);
        JEL.setInputType(2);
        CSS = (EditText)findViewById(R.id.css);
        CSS.setInputType(2);
    }

    private void saveText()
    {
        sPref = getSharedPreferences(APP_PREFERENCES,MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putFloat(SAVED_INDEX, IndexF);
        ed.putString(SAVED_NUMBER, "6");
        ed.putString(SAVED_RESULT, Result);
        ed.commit();
    }

    public void onClickMe(View v) {
        if (!CSS.getText().toString().equals("") && !JEL.getText().toString().equals("") && !SHTANGE.getText().toString().equals(""))
        {
            if (Float.parseFloat(CSS.getText().toString()) > 0 && Float.parseFloat(JEL.getText().toString()) > 0 && Float.parseFloat(SHTANGE.getText().toString()) > 0)
            {
                float Css = Float.parseFloat(CSS.getText().toString());
                float Jel = Float.parseFloat(JEL.getText().toString());
                float Shtange = Float.parseFloat(SHTANGE.getText().toString());
                IndexF = ((Jel/100)*Shtange)/Css;
                if (IndexF < 5) Result = "Очень плохо (низкий уровень выносливости сердечно-сосудистой и дыхательной систем)";
                else if (IndexF >= 5 && IndexF < 10) Result = "Неудовлетворительно";
                else if (IndexF >= 10 && IndexF < 30) Result = "Удовлетворительно";
                else if (IndexF >= 30 && IndexF < 60) Result = "Хорошо";
                else if (IndexF >= 60) Result = "Очень хорошо (высокий уровень выносливости)";
                saveText();
                Intent i;
                i = new Intent(this, Result.class);
                startActivity(i);
            }
            else
            {
                Toast.makeText(getApplicationContext(), "Неверная ёмкость, сердцебиение или проба!", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(getApplicationContext(), "Введите ёмкость, сердцебиение и пробу!", Toast.LENGTH_SHORT).show();
        }
    }
}