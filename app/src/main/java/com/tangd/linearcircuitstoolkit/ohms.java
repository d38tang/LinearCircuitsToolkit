package com.tangd.linearcircuitstoolkit;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;

/**
 * Created by tangd on 2016-11-19.
 */

public class ohms extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.ohms_law);

        final EditText voltage = (EditText)findViewById(R.id.editText);
        final EditText current = (EditText)findViewById(R.id.editText2);
        final EditText resistance = (EditText)findViewById(R.id.editText3);
        Button calculate = (Button)findViewById(R.id.button5);
        Button reset = (Button)findViewById(R.id.button6);


        calculate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String s_voltage = voltage.getText().toString().trim();
                String s_current = current.getText().toString().trim();
                String s_resistance = resistance.getText().toString().trim();

                boolean valid_v=true;
                boolean valid_c=true;
                boolean valid_r=true;


                if (s_voltage.isEmpty()){
                    valid_v=false;
                }
                if (s_current.isEmpty()){
                    valid_c=false;
                }
                if (s_resistance.isEmpty()){
                    valid_r=false;
                }

                if (valid_v && valid_c && !valid_r) {
                    double d_current = Double.parseDouble(s_current);
                    double d_voltage = Double.parseDouble(s_voltage);
                    resistance.setText(String.valueOf(d_voltage/d_current));
                }
                else if (valid_r && valid_v && !valid_c) {
                    double d_resistance = Double.parseDouble(s_resistance);
                    double d_voltage = Double.parseDouble(s_voltage);
                    current.setText(String.valueOf(d_voltage/d_resistance));
                }
                else if (valid_c && valid_r && !valid_v) {
                    double d_current = Double.parseDouble(s_current);
                    double d_resistance = Double.parseDouble(s_resistance);
                    voltage.setText(String.valueOf(d_resistance*d_current));
                }
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

        reset.setOnClickListener(new View.OnClickListener(){
            public void onClick (View v){
                voltage.setText("");
                current.setText("");
                resistance.setText("");

            }
        });

    }
}
