package com.example.onlinecalc;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Index1 extends AppCompatActivity {

    EditText vesik;
    EditText rostik;
    float IndexF;
    String result;

    SharedPreferences sPref;
    public static final String APP_PREFERENCES = "MySettings";
    public static final String SAVED_INDEX = "INDEX1";
    public static final String SAVED_RESULT = "RESULT";
    public static final String SAVED_NUMBER = "NUMBER";
    public static final String SAVED_VES = "VES";
    public static final String SAVED_ROST = "ROST";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index1);

        vesik = (EditText)findViewById(R.id.VES);
        vesik.setInputType(2);
        rostik = (EditText)findViewById(R.id.ROST);
        rostik.setInputType(2);

        Button prod = (Button)findViewById(R.id.prod);
        prod.setOnClickListener(this::onClickMe);
    }

    private void saveText()
    {
        sPref = getSharedPreferences(APP_PREFERENCES,MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putFloat(SAVED_INDEX, IndexF);
        ed.putFloat(SAVED_VES, Float.parseFloat(vesik.getText().toString()));
        ed.putFloat(SAVED_ROST, Float.parseFloat(rostik.getText().toString()));
        ed.putString(SAVED_NUMBER, "1");
        ed.putString(SAVED_RESULT, result);
        ed.commit();
    }

    public void onClickMe(View v)
    {
        if (!vesik.getText().toString().equals("") && !rostik.getText().toString().equals(""))
        {
            if (Float.parseFloat(vesik.getText().toString()) > 0 && Float.parseFloat(rostik.getText().toString()) > 0)
            {
                float ves = Float.parseFloat(vesik.getText().toString());
                float rost = Float.parseFloat(rostik.getText().toString());
                IndexF = ves/(rost*rost);
                if (IndexF < 18.5) result = "Наблюдается недостаток массы тела";
                else if (IndexF >= 18.5 && IndexF < 25) result = "Норма";
                else if (IndexF >= 25 && IndexF < 30) result = "Избыточная масса тела";
                else if (IndexF >= 30 && IndexF < 35) result = "Первая степень ожирения";
                else if (IndexF >= 35 && IndexF < 40) result = "Вторая степень ожирения";
                else if (IndexF >= 40) result = "Третья степень ожирения";
                saveText();
                Intent i;
                i = new Intent(this, Result.class);
                startActivity(i);
            }
            else
            {
                Toast.makeText(getApplicationContext(), "Неверный вес или рост!", Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Введите вес и рост!", Toast.LENGTH_SHORT).show();
        }
    }
}