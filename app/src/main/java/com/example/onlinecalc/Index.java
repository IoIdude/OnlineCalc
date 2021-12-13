package com.example.onlinecalc;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Index extends AppCompatActivity {

    int check;
    EditText editText;

    SharedPreferences sPref;
    public static final String APP_PREFERENCES = "MySettings";
    public static final String SAVED_AGE = "AGE";
    public static final String SAVED_CHECK = "CHECK";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        check = 0;
        String[] male = { "Мужчина", "Женщина"};
        editText = (EditText)findViewById(R.id.age);
        Spinner spinner = findViewById(R.id.spinner);

        editText.setInputType(2);

        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, male);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        loadText();
    }

    private void saveText()
    {
        sPref = getSharedPreferences(APP_PREFERENCES,MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putString(SAVED_AGE, editText.getText().toString());
        ed.putInt(SAVED_CHECK, check);
        ed.commit();
    }

    private void loadText()
    {
        sPref = getSharedPreferences(APP_PREFERENCES,MODE_PRIVATE);
        String savedAge = sPref.getString(SAVED_AGE, "");
        editText.setText(savedAge);
    }

    public void onClickMe(View v)
    {
        if (!editText.getText().toString().equals(""))
        {
            if (Integer.valueOf(editText.getText().toString()) >= 16 && Integer.valueOf(editText.getText().toString()) <= 123)
            {
                saveText();
                Intent i;
                switch (v.getId())
                {
                    case R.id.index1:
                        i = new Intent(this, Index1.class);
                        break;

                    case R.id.index2:
                        i = new Intent(this, Index2.class);
                        break;

                    case R.id.index3:
                        i = new Intent(this, Index3.class);
                        break;

                    case R.id.index4:
                        i = new Intent(this, Index4.class);
                        break;

                    case R.id.index5:
                        i = new Intent(this, Index5.class);
                        break;

                    case R.id.index6:
                        i = new Intent(this, Index6.class);
                        break;

                    case R.id.index7:
                        i = new Intent(this, Index7.class);
                        break;

                    case R.id.index8:
                        i = new Intent(this, Index8.class);
                        break;

                    case R.id.home:
                        i = new Intent(this, MainActivity.class);
                        break;

                    case R.id.res:
                        i = new Intent(this, AllResult.class);
                        break;

                    case R.id.start:
                        check = 1;

                        sPref = getSharedPreferences(APP_PREFERENCES,MODE_PRIVATE);
                        SharedPreferences.Editor ed = sPref.edit();
                        ed.putInt(SAVED_CHECK, check);
                        ed.commit();

                        i = new Intent(this, Index1.class);
                        break;

                    default:
                        i = new Intent(this, Index.class);
                        break;
                }
                startActivity(i);
            }
            else
            {
                Toast.makeText(this, "Неверный возраст [16-123]", Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(this, "Введите возраст!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        saveText();
    }
}
