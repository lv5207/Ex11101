package com.example.ex11101;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

/**
 * @author Lavi Vainer
 * @version 1.0
 * @since 27/11/2026
 *
 * An activity for receiving series data from the user.
 */
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

            /**
             * Get the data and send it to the other activity.
             * @param view The view which was clicked
             */
            public void onClick(View view) {

                // Which base was chosen by the user? If none, don't send the data
                int chosenBase = whichChecked();
                if(chosenBase == -1)
                {
                    return;
                }

                // If the numbers for the series are invalid, don't send the data
                int step, firstTerm;
                if(isFieldInvalid(etStep) || isFieldInvalid(etFirstTerm))
                {
                    return;
                }

                step = Integer.parseInt(etStep.getText().toString());
                firstTerm = Integer.parseInt(etFirstTerm.getText().toString());

                Intent si = new Intent(MainActivity.this, SeriesActivity.class);
                si.putExtra("base", chosenBase);
                si.putExtra("first", firstTerm);
                si.putExtra("step", step);
                startActivity(si);

                // After the user decides to go back to this activity, clear their previous choices
                rgBases.clearCheck();
                etStep.setText("");
                etFirstTerm.setText("");
            }
        });
    }

    /**
     * Check if a user's input (which should be an integer) is invalid
     * @param et The EditText containing the input
     * @return True if the field is invalid, false if valid
     */
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

    /**
     * Get the user's chosen base
     * @return The user's chosen base
     */
    private int whichChecked()
    {
        if(rbBinary.isChecked()) return 2;
        if(rbDecimal.isChecked()) return 10;
        if(rbHex.isChecked()) return 16;
        return -1;
    }
}