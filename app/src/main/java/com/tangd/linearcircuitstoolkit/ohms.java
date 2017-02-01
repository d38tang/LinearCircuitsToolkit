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
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

/**
 * Created by tangd on 2016-11-19.
 */

public class ohms extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.ohms_law);

        // EditText boxes to display voltage, current, or resistance

        final EditText voltage = (EditText)findViewById(R.id.editText);
        final EditText current = (EditText)findViewById(R.id.editText2);
        final EditText resistance = (EditText)findViewById(R.id.editText3);

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

                boolean valid_v=true;
                boolean valid_c=true;
                boolean valid_r=true;

                // format for displayed values

                NumberFormat scientific = new DecimalFormat("0.#####E0");
                NumberFormat decimal = new DecimalFormat("#.#####");


                // boolean is set to false for the EditText boxes that have no user input

                if (s_voltage.isEmpty()){
                    valid_v=false;
                }
                if (s_current.isEmpty()){
                    valid_c=false;
                }
                if (s_resistance.isEmpty()){
                    valid_r=false;
                }

                // User inputs into two of the three EditText boxes
                // These two inputs are used to calculate the missing third input according to V = IR
                // Error message if only zero, one or all three inputs are entered

                // If voltage and current are entered, calculate resistance
                if (valid_v && valid_c && !valid_r) {
                    double d_current = Double.parseDouble(s_current);
                    double d_voltage = Double.parseDouble(s_voltage);
                    double temp = d_voltage/d_current;

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

                else if (valid_r && valid_v && !valid_c) {
                    double d_resistance = Double.parseDouble(s_resistance);
                    double d_voltage = Double.parseDouble(s_voltage);
                    double temp = d_voltage/d_resistance;

                    if (temp>1 && temp<10000){
                        current.setText(String.valueOf(decimal.format(temp)));
                    }
                    else{
                        current.setText(String.valueOf(scientific.format(temp)));
                    }
                }

                // If current and resistance are entered, calculate voltage

                else if (valid_c && valid_r && !valid_v) {
                    double d_current = Double.parseDouble(s_current);
                    double d_resistance = Double.parseDouble(s_resistance);
                    double temp = d_resistance*d_current;

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
        });

        // Resets the values of voltage, current, and resistance

        reset.setOnClickListener(new View.OnClickListener(){
            public void onClick (View v){
                voltage.setText("");
                current.setText("");
                resistance.setText("");

            }
        });

    }
}
