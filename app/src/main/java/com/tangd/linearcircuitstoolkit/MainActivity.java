package com.tangd.linearcircuitstoolkit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_main);

        Button ohm = (Button)findViewById(R.id.button);

        ohm.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ohms.class);
                startActivity(intent);

            }
        });
        Button rb = (Button)findViewById(R.id.button2);

        rb.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Resistor_Band.class);
                startActivity(intent);
            }
        });
        Button Req= (Button)findViewById(R.id.button3);

        Req.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this,equivalent_resistor.class);
                startActivity(intent);
            }
        });
    }
}
