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

public class parallel extends AppCompatActivity{

    // Float values of input resistances

    float f_r1 = 0.0f;
    float f_r2 = 0.0f;
    float f_r3 = 0.0f;
    float f_r4 = 0.0f;
    float equiv = 0.0f;

    // Boolean for empty input for one of the fields

    boolean one = false;
    boolean two = false;
    boolean three = false;
    boolean four = false;

    boolean empty = false; // boolean for all inputs empty

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);

        setContentView(R.layout.parallel_layout);

        // User can input up to four resistance values

        final EditText r1 = (EditText)findViewById(R.id.r1);
        final EditText r2 = (EditText)findViewById(R.id.r2);
        final EditText r3 = (EditText)findViewById(R.id.r3);
        final EditText r4 = (EditText)findViewById(R.id.r4);

        final TextView eq = (TextView)findViewById(R.id.eq);

        Button reset = (Button)findViewById(R.id.reset);

        Button calculate = (Button)findViewById(R.id.calculate);

        // go back to previous Activity

        ImageButton back = (ImageButton)findViewById(R.id.imageButton4);

        back.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(parallel.this,equivalent_resistor.class);
                startActivity(intent);
            }
        });

        // reset resistance values

        reset.setOnClickListener(new View.OnClickListener(){
            public void onClick (View v) {
                resetValue(r1, r2, r3, r4, eq);
            }
        });

        // Calculating equivalent resistance in parallel

        calculate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                // convert EditText value to String

                String s_r1 = r1.getText().toString().trim();
                String s_r2 = r2.getText().toString().trim();
                String s_r3 = r3.getText().toString().trim();
                String s_r4 = r4.getText().toString().trim();

                // convert String to float

                convertFloat(s_r1, s_r2, s_r3, s_r4);

                Log.d("float", String.valueOf(f_r1) + " " + String.valueOf(f_r2) + " " +
                        String.valueOf(f_r3) + " " + String.valueOf(f_r4));

                // calculate equivalent resistance in parallel

                calculation();

                // If there is input, output the equivalent resistance in a string

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

        // Set input as empty before every calculation

        one = false;
        two = false;
        three = false;
        four = false;

        // If there is user input, convert the inputted string to a float
        // If the EditText box is not empty, set boolean to true

        if (!s_r1.isEmpty()){
            f_r1 = Float.parseFloat(s_r1);
            one = true;
        }

        if (!s_r2.isEmpty()){
            f_r2 = Float.parseFloat(s_r2);
            two = true;
        }

        if (!s_r3.isEmpty()){
            f_r3 = Float.parseFloat(s_r3);
            three = true;
        }

        if (!s_r4.isEmpty()){
            f_r4 = Float.parseFloat(s_r4);
            four = true;
        }
    }

    private void calculation(){

        empty = false;

        // Equivalent resistance in parallel changes depending on which/how many inputs were entered
        // Different cases for different inputs

        if (one && !two && !three && !four){
            equiv = f_r1;
        }
        else if (!one && two && !three && !four){
            equiv = f_r2;
        }
        else if (!one && !two && three && !four){
            equiv = f_r3;
        }
        else if (!one && !two && !three && four){
            equiv = f_r4;
        }
        else if (one && two && !three && !four){
            equiv = 1/((1/f_r1)+(1/f_r2));
        }
        else if (one && !two && three && !four){
            equiv = 1/((1/f_r1)+(1/f_r3));
        }
        else if (one && !two && !three && four){
            equiv = 1/((1/f_r1)+(1/f_r4));
        }
        else if (!one && two && three && !four){
            equiv = 1/((1/f_r2)+(1/f_r3));
        }
        else if (!one && two && !three && four){
            equiv = 1/((1/f_r2)+(1/f_r4));
        }
        else if (!one && !two && three && four){
            equiv = 1/((1/f_r3)+(1/f_r4));
        }
        else if (one && two && three && !four){
            equiv = 1/((1/f_r1)+(1/f_r2)+(1/f_r3));
        }
        else if (one && !two && three && four){
            equiv = 1/((1/f_r1)+(1/f_r4)+(1/f_r3));
        }
        else if (one && two && !three && four){
            equiv = 1/((1/f_r1)+(1/f_r2)+(1/f_r4));
        }
        else if (!one && two && three && four){
            equiv = 1/((1/f_r3)+(1/f_r2)+(1/f_r4));
        }
        else if (one && two && three && four){
            equiv = 1/((1/f_r3)+(1/f_r2)+(1/f_r4)+(1/f_r1));
        }

        // If there is no user input, generate error message

        else {
            empty = true;
            AlertDialog.Builder error = new AlertDialog.Builder(parallel.this);
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
}
