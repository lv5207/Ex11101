package com.example.ex11101;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SeriesActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
ListView lvSeries;
TextView tvInfo;
Button btnBack;
String[] series;
int base;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_series);

        lvSeries = findViewById(R.id.lvSeries);
        tvInfo = findViewById(R.id.tvInfo);
        btnBack = findViewById(R.id.btnBack);

        Intent gi = getIntent();
        String baseStr = gi.getStringExtra("base");
        int firstTerm = gi.getIntExtra("first", 0);
        int step = gi.getIntExtra("step", 1);
        base = getBaseFromString(baseStr);

        series = generateSeries(firstTerm, step, base);

        lvSeries.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        lvSeries.setOnItemClickListener(this);

        ArrayAdapter<String> adp = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, series);
        lvSeries.setAdapter(adp);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private int getBaseFromString(String base)
    {
        if(base.equals("BINARY")) return 2;
        if(base.equals("DECIMAL")) return 10;
        return 16;
    }

    private String[] generateSeries(int first, int step, int base)
    {
        String[] series = new String[20];
        for(int i = 0; i < 20; i++)
        {
            series[i] = Integer.toString(first + step * i, base);
        }
        return series;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        String info = "";
        int n = Integer.parseInt(series[i], base);

        info += "Binary: " + Integer.toString(n, 2);
        info += "\nDecimal: " + Integer.toString(n, 10);
        info += "\nHexadecimal: " + Integer.toString(n, 16);
        info += "\nThe term is " + (n % 2 == 0 ? "even" : "odd");
        info += "\nNumber of set bits: " + Integer.bitCount(n);
        tvInfo.setText(info);
    }
}