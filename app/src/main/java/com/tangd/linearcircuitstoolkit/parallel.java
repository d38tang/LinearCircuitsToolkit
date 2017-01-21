package com.tangd.linearcircuitstoolkit;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by tangd on 2016-11-19.
 */

public class parallel extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);

        setContentView(R.layout.parallel_layout);

        final EditText r1 = (EditText)findViewById(R.id.r1);
        final EditText r2 = (EditText)findViewById(R.id.r2);
        final EditText r3 = (EditText)findViewById(R.id.r3);
        final EditText r4 = (EditText)findViewById(R.id.r4);
        final TextView eq = (TextView)findViewById(R.id.eq);
        Button reset = (Button)findViewById(R.id.reset);
        Button calculate = (Button)findViewById(R.id.calculate);


        reset.setOnClickListener(new View.OnClickListener(){
            public void onClick (View v) {
                r1.setText("");
                r2.setText("");
                r3.setText("");
                r4.setText("");
                eq.setText("");
            }
        });

        calculate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String s_r1 = r1.getText().toString().trim();
                String s_r2 = r2.getText().toString().trim();
                String s_r3 = r3.getText().toString().trim();
                String s_r4 = r4.getText().toString().trim();

                float f_r1 = 0;
                float f_r2 = 0;
                float f_r3 = 0;
                float f_r4 = 0;
                float equiv = 0;
                boolean one = false;
                boolean two = false;
                boolean three = false;
                boolean four = false;
                boolean empty = false;


                if (!s_r1.isEmpty()){
                    f_r1 += Float.parseFloat(s_r1);
                    one = true;
                }
                if (!s_r2.isEmpty()){
                    f_r2 += Float.parseFloat(s_r2);
                    two = true;
                }
                if (!s_r3.isEmpty()){
                    f_r3 += Float.parseFloat(s_r3);
                    three = true;
                }
                if (!s_r4.isEmpty()){
                    f_r4 += Float.parseFloat(s_r4);
                    four = true;
                }
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

                if (!empty) {
                    String output = "The equivalent resistance is " + String.valueOf(equiv) + "Ω";
                    eq.setText(output);
                }



            }
        });


    }
}