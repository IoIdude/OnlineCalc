package com.example.onlinecalc;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Index3 extends AppCompatActivity {

    EditText css;
    EditText sad;
    EditText dad;
    float IndexF;
    String Result;

    SharedPreferences sPref;
    public static final String APP_PREFERENCES = "MySettings";
    public static final String SAVED_INDEX = "INDEX3";
    public static final String SAVED_RESULT = "RESULT";
    public static final String SAVED_NUMBER = "NUMBER";
    public static final String SAVED_CSS = "CSS";
    public static final String SAVED_SAD = "SAD";
    public static final String SAVED_DAD = "DAD";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index3);

        css = (EditText)findViewById(R.id.css);
        css.setInputType(2);
        sad = (EditText)findViewById(R.id.sad);
        sad.setInputType(2);
        dad = (EditText)findViewById(R.id.dad);
        dad.setInputType(2);
    }

    private void saveText()
    {
        sPref = getSharedPreferences(APP_PREFERENCES,MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putFloat(SAVED_INDEX, IndexF);
        ed.putFloat(SAVED_CSS, Float.parseFloat(css.getText().toString()));
        ed.putFloat(SAVED_SAD, Float.parseFloat(sad.getText().toString()));
        ed.putFloat(SAVED_DAD, Float.parseFloat(dad.getText().toString()));
        ed.putString(SAVED_NUMBER, "3");
        ed.putString(SAVED_RESULT, Result);
        ed.commit();
    }

    public void onClickMe(View v)
    {
        if (!css.getText().toString().equals("") && !sad.getText().toString().equals("") && !dad.getText().toString().equals("") && Float.parseFloat(sad.getText().toString()) > Float.parseFloat(dad.getText().toString()))
        {
            if (Float.parseFloat(css.getText().toString()) > 0 && Float.parseFloat(sad.getText().toString()) > 0 && Float.parseFloat(dad.getText().toString()) > 0)
            {
                float CSS = Float.parseFloat(css.getText().toString());
                float SAD = Float.parseFloat(sad.getText().toString());
                float DAD = Float.parseFloat(dad.getText().toString());
                IndexF = (CSS*10)/(SAD-DAD);
                if (IndexF < 16) Result = "Усиление кардиореспираторной системы, также при занятиях спортом коэффициент выносливости может быть ниже";
                else if (IndexF == 16) Result = "Норма";
                else if (IndexF >= 16) Result = "слабление деятельности сердечно-сосудистой системы или детренированности обследуемого";
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
        else
        {
            Toast.makeText(getApplicationContext(), "Укажите нормальные данные!", Toast.LENGTH_SHORT).show();
        }
    }
}