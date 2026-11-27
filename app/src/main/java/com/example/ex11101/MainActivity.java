package com.example.ex11101;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

EditText etStep;
EditText etFirstTerm;
RadioGroup rgBases;
RadioButton rbBinary;
RadioButton rbDecimal;
RadioButton rbHex;
Button btnViewSeries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etStep = findViewById(R.id.etStep);
        etFirstTerm = findViewById(R.id.etFirstTerm);
        rgBases = findViewById(R.id.rgBases);
        rbBinary = findViewById(R.id.rbBinary);
        rbDecimal = findViewById(R.id.rbDecimal);
        rbHex = findViewById(R.id.rbHex);
        btnViewSeries = findViewById(R.id.btnViewSeries);

        btnViewSeries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String chosenBase = whichChecked();
                if(chosenBase.equals("NONE"))
                {
                    return;
                }

                int step, firstTerm;
                if(isFieldInvalid(etStep) || isFieldInvalid(etFirstTerm))
                {
                    return;
                }

                step = Integer.parseInt(etStep.getText().toString());
                firstTerm = Integer.parseInt(etFirstTerm.getText().toString());
            }
        });
    }

    private boolean isFieldInvalid(EditText et)
    {
        String t = et.getText().toString();
        try {
            int value = Integer.parseInt(t);
            return false;
        }
        catch (NumberFormatException e)
        {
            et.setHint("Please enter a valid number!");
            return true;
        }
    }

    private String whichChecked()
    {
        if(rbBinary.isChecked())
        {
            return "BINARY";
        }
        else if(rbDecimal.isChecked())
        {
            return "DECIMAL";
        }
        else if(rbHex.isChecked())
        {
            return "HEXADECIMAL";
        }
        return "NONE";
    }
}