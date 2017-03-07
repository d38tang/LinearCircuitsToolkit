package com.tangd.linearcircuitstoolkit;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by tangd on 2016-11-19.
 */

public class series extends AppCompatActivity{

    // Float values of input resistances

    float f_r1 = 0.0f;
    float f_r2 = 0.0f;
    float f_r3 = 0.0f;
    float f_r4 = 0.0f;
    float equiv = 0.0f;

    boolean empty = false; // boolean for empty inputs

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);

        setContentView(R.layout.series_layout);

        // User can input up to four resistance values

        final EditText r1 = (EditText)findViewById(R.id.r1);
        final EditText r2 = (EditText)findViewById(R.id.r2);
        final EditText r3 = (EditText)findViewById(R.id.r3);
        final EditText r4 = (EditText)findViewById(R.id.r4);

        // Equivalent resistance

        final TextView eq = (TextView)findViewById(R.id.eq);

        Button reset = (Button)findViewById(R.id.reset);

        Button calculate = (Button)findViewById(R.id.calculate);

        // go back to previus Activity

        ImageButton back = (ImageButton)findViewById(R.id.imageButton5);

        back.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(series.this,equivalent_resistor.class);
                startActivity(intent);
            }
        });

        // Resets the four resistance values and the equivalent resistance value

        reset.setOnClickListener(new View.OnClickListener(){
            public void onClick (View v) {

                resetValue(r1, r2, r3, r4, eq);

            }
        });

        // Calculating equivalent resistance

        calculate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                // Convert EditText values to String

                String s_r1;
                String s_r2;
                String s_r3;
                String s_r4;


                s_r1 = r1.getText().toString().trim();
                s_r2 = r2.getText().toString().trim();
                s_r3 = r3.getText().toString().trim();
                s_r4 = r4.getText().toString().trim();

                // Convert input to float

                convertFloat(s_r1, s_r2, s_r3, s_r4);

                Log.d("float", String.valueOf(f_r1) + " " + String.valueOf(f_r2) + " " +
                String.valueOf(f_r3) + " " + String.valueOf(f_r4));

                emptyError(s_r1, s_r2, s_r3, s_r4); // If there is no user input, generate error message

                calculation(); // Calculate resistance in series

                if (!empty) {
                    String output = "The equivalent resistance is " + String.valueOf(equiv) + "Ω";
                    eq.setText(output);
                }



            }
        });


    }

    // Reset resistances

    private void resetValue(EditText r1, EditText r2, EditText r3, EditText r4, TextView eq){
        r1.setText("");
        r2.setText("");
        r3.setText("");
        r4.setText("");
        eq.setText("");
    }

    // Convert String to float if input is not empty

    private void convertFloat(String s_r1, String s_r2, String s_r3, String s_r4){

        // Reset resistance values before every calculation

        f_r1 = 0.0f;
        f_r2 = 0.0f;
        f_r3 = 0.0f;
        f_r4 = 0.0f;

        // If there is user input, convert the inputted string to a float

        if (!s_r1.isEmpty()){
            f_r1 = Float.parseFloat(s_r1);
            empty = false;
        }

        if (!s_r2.isEmpty()){
            f_r2 = Float.parseFloat(s_r2);
            empty = false;
        }

        if (!s_r3.isEmpty()){
            f_r3 = Float.parseFloat(s_r3);
            empty = false;
        }

        if (!s_r4.isEmpty()){
            f_r4 = Float.parseFloat(s_r4);
            empty = false;
        }
    }

    // Generate error message if there is no input

    private void emptyError(String s_r1, String s_r2, String s_r3, String s_r4){
        if (s_r1.isEmpty() && s_r2.isEmpty()&&s_r3.isEmpty()&&s_r4.isEmpty()) {

            empty = true;
            AlertDialog.Builder error = new AlertDialog.Builder(series.this);
            error.setMessage("Please input at least one value!").setCancelable(false);
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

    // calculate equivalent resistance

    private void calculation(){

        // Add all the resistance values to get the equivalent resistance in series

        equiv = f_r1 + f_r2 + f_r3 + f_r4;
    }
}
