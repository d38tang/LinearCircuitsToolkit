package com.tangd.linearcircuitstoolkit;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;

/**
 * Created by tangd on 2016-11-18.
 */

public class equivalent_resistor extends AppCompatActivity {
    @Override
        protected void onCreate(Bundle savedInstanceState){

            super.onCreate(savedInstanceState);

            setContentView(R.layout.series_parallel);

            Button series = (Button)findViewById(R.id.series);

            series.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(equivalent_resistor.this,series.class);
                    startActivity(intent);

                }
            });

            Button parallel = (Button)findViewById(R.id.parallel);

            parallel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                    Intent intent = new Intent(equivalent_resistor.this,parallel.class);
                    startActivity(intent);

            }
        });
    }

}
