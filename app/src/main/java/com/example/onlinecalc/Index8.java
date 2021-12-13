package com.example.onlinecalc;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Index8 extends AppCompatActivity {

    EditText DAD;
    EditText CSS;
    EditText SAD;
    EditText ROST;
    EditText VES;
    EditText AGE;
    float IndexF;
    String Result;

    SharedPreferences sPref;
    public static final String APP_PREFERENCES = "MySettings";
    public static final String SAVED_INDEX = "INDEX8";
    public static final String SAVED_RESULT = "RESULT";
    public static final String SAVED_NUMBER = "NUMBER";
    public static final String SAVED_DAD = "DAD";
    public static final String SAVED_CSS = "CSS";
    public static final String SAVED_SAD = "SAD";
    public static final String SAVED_VES = "VES";
    public static final String SAVED_ROST = "ROST";
    public static final String SAVED_AGE = "AGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index8);

        DAD = (EditText)findViewById(R.id.dad);
        DAD.setInputType(2);
        SAD = (EditText)findViewById(R.id.sad);
        SAD.setInputType(2);
        CSS = (EditText)findViewById(R.id.css);
        CSS.setInputType(2);
        VES = (EditText)findViewById(R.id.ves);
        VES.setInputType(2);
        ROST = (EditText)findViewById(R.id.rost);
        ROST.setInputType(2);
        AGE = (EditText)findViewById(R.id.age);
        AGE.setInputType(2);
    }

    private void saveText()
    {
        sPref = getSharedPreferences(APP_PREFERENCES,MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putFloat(SAVED_INDEX, IndexF);
        ed.putString(SAVED_NUMBER, "8");
        ed.putString(SAVED_RESULT, Result);
        ed.putFloat(SAVED_DAD, Float.parseFloat(DAD.getText().toString()));
        ed.putFloat(SAVED_CSS, Float.parseFloat(CSS.getText().toString()));
        ed.putFloat(SAVED_SAD, Float.parseFloat(SAD.getText().toString()));
        ed.putFloat(SAVED_VES, Float.parseFloat(VES.getText().toString()));
        ed.putFloat(SAVED_ROST, Float.parseFloat(ROST.getText().toString()));
        ed.putString(SAVED_AGE, AGE.getText().toString());
        ed.commit();
    }

    public void onClickMe(View v) {
        if (!CSS.getText().toString().equals("") && !DAD.getText().toString().equals("") && !SAD.getText().toString().equals("") && !VES.getText().toString().equals("") &&
                !ROST.getText().toString().equals("") && !AGE.getText().toString().equals(""))
        {
            if (Float.parseFloat(CSS.getText().toString()) > 0 && Float.parseFloat(DAD.getText().toString()) > 0 &&
                    Float.parseFloat(SAD.getText().toString()) > 0 && Float.parseFloat(VES.getText().toString()) > 0 &&
                    Float.parseFloat(ROST.getText().toString()) > 0 && Float.parseFloat(AGE.getText().toString()) > 0)
            {
                float Css = Float.parseFloat(CSS.getText().toString());
                float Dad = Float.parseFloat(DAD.getText().toString());
                float Sad = Float.parseFloat(SAD.getText().toString());
                float Ves = Float.parseFloat(VES.getText().toString());
                float Rost = Float.parseFloat(ROST.getText().toString());
                float Age = Float.parseFloat(AGE.getText().toString());
                IndexF = (float) (0.011*Css + 0.014*Sad + 0.008*Dad + 0.014*Age + 0.009*Ves - 0.009*Rost - 0.27);
                if (IndexF < 2.6) Result = "Функциональные возможности системы кровообращения хорошие";
                if (IndexF >= 2.6 && IndexF <= 3.09) Result = "Удовлетворительные функциональные возможности системы кровообращения с умеренным напряжением механизмов регуляции";
                if (IndexF > 3.09) Result = "Сниженные, недостаточные возможности системы кровообращения, наличие выраженных нарушений процессов адаптации";
                saveText();
                Intent i;
                i = new Intent(this, Result.class);
                startActivity(i);
            }
            else
            {
                Toast.makeText(getApplicationContext(), "Неверные данные!", Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Введите все данные!", Toast.LENGTH_SHORT).show();
        }
    }
}