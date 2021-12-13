package com.example.onlinecalc;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AllResult extends AppCompatActivity {

    float res1;
    float res2;
    float res3;
    float res4;
    float res5;
    float res6;
    float res7;
    float res8;
    String date1;
    String date2;
    String date3;
    String date4;
    String date5;
    String date6;
    String date7;
    String date8;

    SharedPreferences sPref;

    public ArrayList<ResModel> resModel = new ArrayList<ResModel>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allresult);

        sPref = getSharedPreferences("MySettings",MODE_PRIVATE);
        res1 = sPref.getFloat("INDEX1", 0);
        res2 = sPref.getFloat("INDEX2", 0);
        res3 = sPref.getFloat("INDEX3", 0);
        res4 = sPref.getFloat("INDEX4", 0);
        res5 = sPref.getFloat("INDEX5", 0);
        res6 = sPref.getFloat("INDEX6", 0);
        res7 = sPref.getFloat("INDEX7", 0);
        res8 = sPref.getFloat("INDEX8", 0);
        date1 = sPref.getString("DATE1", "");
        date2 = sPref.getString("DATE2", "");
        date3 = sPref.getString("DATE3", "");
        date4 = sPref.getString("DATE4", "");
        date5 = sPref.getString("DATE5", "");
        date6 = sPref.getString("DATE6", "");
        date7 = sPref.getString("DATE7", "");
        date8 = sPref.getString("DATE8", "");

        // начальная инициализация списка
        setInitialData();
        RecyclerView recyclerView = findViewById(R.id.LIST);
        // создаем адаптер
        ResAdapter adapter = new ResAdapter(this, resModel);
        // устанавливаем для списка адаптер
        recyclerView.setAdapter(adapter);
    }

    private void setInitialData()
    {
        resModel.add(new ResModel ("Индекс массы тела\n"+"Резуьтат: "+res1+"\nНорма 18,5-25\n"+"\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t"+"Дата: "+date1));
        resModel.add(new ResModel ("Жизненный индекс\n"+"Резуьтат: "+res5+"\nНорма 50-61\n"+"\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t"+"Дата: "+date5));
        resModel.add(new ResModel ("Коэффициент выносливости\n"+"Резуьтат: "+res3+"\nНорма 16\n"+"\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t"+"Дата: "+date3));
        resModel.add(new ResModel ("Уровень регуляции сердечно-сосудистой системы\n"+"Резуьтат: "+res4+"\n81-90 средний уровень\n91-100 – ниже среднего\n"+"\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t"+"Дата: "+date4));
        resModel.add(new ResModel ("Циркулярно-респираторный коэффициент Скибински\n"+"Резуьтат: "+res6+"\n30-60 – хорошо\n10-30  - удовлетворительно\n"+"\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t"+"Дата: "+date6));
        resModel.add(new ResModel ("Вегетативный индекс Кердо\n"+"Резуьтат: "+res7+"\nНорма 0\n"+"\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t"+"Дата: "+date7));
        resModel.add(new ResModel ("Индекс функциональных изменений системы кровообращения\n"+"Резуьтат: "+res8+"\nМенее 2,6 — функциональные возможности системы кровообращения хорошие\n2,6—3,09 — удовлетворительные возможности\n"+"\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t"+"Дата: "+date8));
        resModel.add(new ResModel ("Уровень двигательный активности\n"+"Резуьтат: "+res2+"\n10-12 тыс. шагов – «активный образ жизни»\n"+"\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t"+"Дата: "+date2));
    }

    public void onClickMe(View v)
    {
        Intent i;

        switch (v.getId()) {
            case R.id.home:
                i = new Intent(this, MainActivity.class);
                break;

            case R.id.index:
                i = new Intent(this, Index.class);
                break;

            default:
                i = new Intent(this, AllResult.class);
                break;
        }
        startActivity(i);
    }
}