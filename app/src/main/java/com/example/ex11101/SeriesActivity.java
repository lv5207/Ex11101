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

/**
 * @author Lavi Vainer
 * @version 1.0
 * @since 27/11/2026
 *
 * An activity for displaying the series built on the user's input along with each term's information
 */
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
        base = gi.getIntExtra("base", 10);
        int firstTerm = gi.getIntExtra("first", 0);
        int step = gi.getIntExtra("step", 1);

        series = generateSeries(firstTerm, step, base);

        lvSeries.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        lvSeries.setOnItemClickListener(this);

        ArrayAdapter<String> adp = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, series);
        lvSeries.setAdapter(adp);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override

            /**
             * Finish the intent and go back to the previous activity once the button is clicked
             * @param view The view which was clicked
             */
            public void onClick(View view) {
                finish();
            }
        });

    }

    /**
     * Generate a series of 20 elements based on the user's input
     * @param first The first term in the series
     * @param step The step between each term
     * @param base The base in which the numbers are displayed
     * @return A string array containing said 20 elements
     */
    private String[] generateSeries(int first, int step, int base)
    {
        String[] series = new String[20];
        for(int i = 0; i < 20; i++)
        {
            series[i] = Integer.toString(first + step * i, base);
        }
        return series;
    }

    /**
     * Display the chosen term's information
     * @param adapterView The ListView which was clicked
     * @param view The row which was clicked
     * @param i The index of the chosen term
     * @param l The ID of the row in the list
     */
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