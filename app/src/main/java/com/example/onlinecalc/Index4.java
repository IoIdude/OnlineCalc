package com.example.onlinecalc;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Index4 extends AppCompatActivity {

    EditText CSS;
    EditText SAD;
    float IndexF;
    String Result;

    SharedPreferences sPref;
    public static final String APP_PREFERENCES = "MySettings";
    public static final String SAVED_INDEX = "INDEX4";
    public static final String SAVED_RESULT = "RESULT";
    public static final String SAVED_NUMBER = "NUMBER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index4);

        CSS = (EditText)findViewById(R.id.css);
        CSS.setInputType(2);
        SAD = (EditText)findViewById(R.id.sad);
        SAD.setInputType(2);
    }

    private void saveText()
    {
        sPref = getSharedPreferences(APP_PREFERENCES,MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putFloat(SAVED_INDEX, IndexF);
        ed.putString(SAVED_NUMBER, "4");
        ed.putString(SAVED_RESULT, Result);
        ed.commit();
    }

    public void onClickMe(View v)
    {
        if (!CSS.getText().toString().equals("") && !SAD.getText().toString().equals(""))
        {
            if (Float.parseFloat(CSS.getText().toString()) > 0 && Float.parseFloat(SAD.getText().toString()) > 0)
            {
                float css = Float.parseFloat(CSS.getText().toString());
                float sad = Float.parseFloat(SAD.getText().toString());
                IndexF = (css*sad)/100;
                if (IndexF <= 74) Result = "Высокий уровень регуляции сердечно-сосудистой системы";
                else if (IndexF >= 75 && IndexF <= 80) Result = "Выше среднего";
                else if (IndexF >= 81 && IndexF <= 90) Result = "Средний";
                else if (IndexF >= 91 && IndexF <= 100) Result = "Ниже среднего";
                else if (IndexF >= 101) Result = "Низкое значение регуляции";
                saveText();
                Intent i;
                i = new Intent(this, Result.class);
                startActivity(i);
            }
            else
            {
                Toast.makeText(getApplicationContext(), "Неверное давление или сердцебиение!", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(getApplicationContext(), "Укажите нормальные данные!", Toast.LENGTH_SHORT).show();
        }
    }
}