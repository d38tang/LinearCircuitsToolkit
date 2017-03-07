package com.tangd.linearcircuitstoolkit;

import android.content.DialogInterface;
import android.content.Intent;
import android.icu.text.DecimalFormat;
import android.icu.text.NumberFormat;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

/**
 * Created by tangd on 2016-11-19.
 */

@RequiresApi(api = Build.VERSION_CODES.N)
public class ohms extends AppCompatActivity {

    // Boolean for validity of inputs

    private boolean valid_v=true;
    private boolean valid_c=true;
    private boolean valid_r=true;

    // double values for V, I, R

    double d_voltage;
    double d_current;
    double d_resistance;
    double temp;

    // Option to display value in decimal or scientific

    NumberFormat scientific = new DecimalFormat("0.#####E0");
    NumberFormat decimal = new DecimalFormat("#.#####");

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.ohms_law);

        // EditText boxes to display voltage, current, or resistance

        final EditText voltage = (EditText)findViewById(R.id.editText);
        final EditText current = (EditText)findViewById(R.id.editText2);
        final EditText resistance = (EditText)findViewById(R.id.editText3);

        // Calculate value or reset boxes

        Button calculate = (Button)findViewById(R.id.button5);
        Button reset = (Button)findViewById(R.id.button6);

        // Back button goes back to the main activity

        ImageButton back = (ImageButton)findViewById(R.id.imageButton);

        back.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(ohms.this,MainActivity.class);
                startActivity(intent);
            }
        });

        // Calculate voltage, current, or resistance

        calculate.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            public void onClick(View v) {

                String s_voltage = voltage.getText().toString().trim();
                String s_current = current.getText().toString().trim();
                String s_resistance = resistance.getText().toString().trim();

                // Check if input is empty

                emptyCheck(s_voltage, s_current, s_resistance);
                Log.d("valid", String.valueOf(valid_v)+String.valueOf(valid_c)+String.valueOf(valid_r));

                // Calculation using Ohm's Law

                calculateValue(s_voltage, s_current, s_resistance, voltage, current, resistance);


            }
        });

        // Resets the values of voltage, current, and resistance

        reset.setOnClickListener(new View.OnClickListener(){
            public void onClick (View v){
                resetValue(voltage, current, resistance);
            }
        });

    }
    private void emptyCheck(String s_voltage, String s_current, String s_resistance) {
        // boolean is set to false for the EditText boxes that have no user input

        if (s_voltage.isEmpty()) {
            valid_v = false;
        }
        else {
            valid_v =true;
        }
        if (s_current.isEmpty()) {
            valid_c = false;
        }
        else {
            valid_c = true;
        }
        if (s_resistance.isEmpty()) {
            valid_r = false;
        }
        else {
            valid_r = true;
        }
    }

    // reset value of input boxes

    private void resetValue(EditText voltage, EditText current, EditText resistance){
        voltage.setText("");
        current.setText("");
        resistance.setText("");
    }

    // calculate V, I, or R

    private void calculateValue(String s_voltage, String s_current, String s_resistance, EditText voltage,
                                EditText current, EditText resistance){

        // If voltage and current are entered, calculate resistance
        // R = V/I

        if (valid_v && valid_c && !valid_r) {
            d_current = Double.parseDouble(s_current);
            d_voltage = Double.parseDouble(s_voltage);
            temp = d_voltage/d_current;

            // If the answer is between 1 and 10000, display the value according to the format
            // defined above. Else, use scientific notation
            if (temp>1 && temp<10000){
                resistance.setText(String.valueOf(decimal.format(temp)));
            }
            else{
                resistance.setText(String.valueOf(scientific.format(temp)));
            }

        }

        // If resistance and voltage are entered, calculate current
        // I = V/R

        else if (valid_r && valid_v && !valid_c) {
            d_resistance = Double.parseDouble(s_resistance);
            d_voltage = Double.parseDouble(s_voltage);
            temp = d_voltage/d_resistance;

            if (temp>1 && temp<10000){
                current.setText(String.valueOf(decimal.format(temp)));
            }
            else{
                current.setText(String.valueOf(scientific.format(temp)));
            }
        }

        // If current and resistance are entered, calculate voltage
        // V = IR

        else if (valid_c && valid_r && !valid_v) {
            d_current = Double.parseDouble(s_current);
            d_resistance = Double.parseDouble(s_resistance);
            temp = d_resistance*d_current;

            if (temp>1 && temp<10000){
                voltage.setText(String.valueOf(decimal.format(temp)));
            }
            else{
                voltage.setText(String.valueOf(scientific.format(temp)));
            }
        }

        // If not exactly two inputs are entered, generate error message

        else {

            AlertDialog.Builder error = new AlertDialog.Builder(ohms.this);
            error.setMessage("Please input two values!").setCancelable(false);
            error.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            AlertDialog error2 = error.create();
            error2.setTitle("Error");
            error2.show();
        }
    }
}
