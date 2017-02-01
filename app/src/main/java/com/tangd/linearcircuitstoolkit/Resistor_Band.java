package com.tangd.linearcircuitstoolkit;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import java.lang.Math;
import android.widget.TextView;
import android.widget.ArrayAdapter;



public class Resistor_Band extends AppCompatActivity {

    String one="";
    String two="";
    String three="";
    String four="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_colorband);

        // four spinners for the four colors of a resistor

        final Spinner spinner1=(Spinner)findViewById(R.id.spinner);
        final Spinner spinner2=(Spinner)findViewById(R.id.spinner2);
        final Spinner spinner3=(Spinner)findViewById(R.id.spinner3);
        final Spinner spinner4=(Spinner)findViewById(R.id.spinner4);

        final TextView result =(TextView)findViewById(R.id.textView9);

        ImageButton back = (ImageButton)findViewById(R.id.imageButton2);

        back.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(Resistor_Band.this,MainActivity.class);
                startActivity(intent);
            }
        });

        // Create dropdown menu for the spinners to select colors already defined in XML file

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,R.array.band_1,android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner1.setAdapter(adapter1);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,R.array.band_2,android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner2.setAdapter(adapter2);

        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,R.array.band_3,android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner3.setAdapter(adapter3);

        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(this,R.array.band_4,android.R.layout.simple_spinner_item);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner4.setAdapter(adapter4);

        // Convert selected item in dropdown menu to string

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                one = parent.getItemAtPosition(position).toString();
                TextView tempView = (TextView) spinner1.getSelectedView().findViewById(android.R.id.text1);
                tempView.setTextColor(Color.BLACK);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                two = parent.getItemAtPosition(position).toString();
                TextView tempView = (TextView) spinner2.getSelectedView().findViewById(android.R.id.text1);
                tempView.setTextColor(Color.BLACK);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                three = parent.getItemAtPosition(position).toString();
                TextView tempView = (TextView) spinner3.getSelectedView().findViewById(android.R.id.text1);
                tempView.setTextColor(Color.BLACK);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                four = parent.getItemAtPosition(position).toString();
                TextView tempView = (TextView) spinner4.getSelectedView().findViewById(android.R.id.text1);
                tempView.setTextColor(Color.BLACK);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // Calculating the resistance from color bands

        Button calculate = (Button)findViewById(R.id.button7);

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                float resistance = 0;
                float tolerance = 0;

                // Start with the second color band. Add resistance based on the selected color.

                if (two.equals("Black")){
                    resistance+=0;
                }
                else if (two.equals("Brown")){
                    resistance+=1;
                }
                else if (two.equals("Red")){
                    resistance+=2;
                }
                else if (two.equals("Orange")){
                    resistance+=3;
                }
                else if (two.equals("Yellow")){
                    resistance+=4;
                }
                else if (two.equals("Green")){
                    resistance+=5;
                }
                else if (two.equals("Blue")){
                    resistance+=6;
                }
                else if (two.equals("Violet")){
                    resistance+=7;
                }
                else if (two.equals("Grey")){
                    resistance+=8;
                }
                else if (two.equals("White")){
                    resistance+=9;
                }

                // Add the resistance values for the first color band to the total resistance
                // The first color band is a 10s value while the second color band is a 1s value

                if (one.equals("Black")){
                    resistance+=10*0;
                }
                else if (one.equals("Brown")){
                    resistance+=10*1;
                }
                else if (one.equals("Red")){
                    resistance+=10*2;
                }
                else if (one.equals("Orange")){
                    resistance+=10*3;
                }
                else if (one.equals("Yellow")){
                    resistance+=10*4;
                }
                else if (one.equals("Green")){
                    resistance+=10*5;
                }
                else if (one.equals("Blue")){
                    resistance+=10*6;
                }
                else if (one.equals("Violet")){
                    resistance+=10*7;
                }
                else if (one.equals("Grey")){
                    resistance+=10*8;
                }
                else if (one.equals("White")){
                    resistance+=10*9;
                }

                // The third color band is a multiplier dependant on the color
                // Multiply the total resistance from the first two color bands by a factor of 10

                if (three.equals("Black")){
                    resistance*=Math.pow(10,0);
                }
                else if (three.equals("Brown")){
                    resistance*=Math.pow(10,1);
                }
                else if (three.equals("Red")){
                    resistance*=Math.pow(10,2);
                }
                else if (three.equals("Orange")){
                    resistance*=Math.pow(10,3);
                }
                else if (three.equals("Yellow")){
                    resistance*=Math.pow(10,4);
                }
                else if (three.equals("Green")){
                    resistance*=Math.pow(10,5);
                }
                else if (three.equals("Blue")){
                    resistance*=Math.pow(10,6);
                }
                else if (three.equals("Violet")){
                    resistance*=Math.pow(10,7);
                }
                else if (three.equals("Gold")){
                    resistance*=Math.pow(10,-1);
                }
                else if (three.equals("Silver")){
                    resistance*=Math.pow(10,-2);
                }

                // Fourth color band is a tolerance (error) value

                if (four.equals("Brown")){
                    tolerance+=1;
                }
                else if (four.equals("Red")){
                    tolerance+=2;
                }
                else if (four.equals("Green")){
                    tolerance+=0.5;
                }
                else if (four.equals("Blue")){
                    tolerance+=0.25;
                }
                else if (four.equals("Violet")){
                    tolerance+=0.10;
                }
                else if (four.equals("Grey")){
                    tolerance+=0.05;
                }
                else if (four.equals("Gold")){
                    tolerance+=5;
                }
                else if (four.equals("Silver")){
                    tolerance+=10;
                }

                // Output the resistance and tolerance values in a string

                String output = "The resistance is " + String.valueOf(resistance)+"Ω" + " ±" +
                        String.valueOf(tolerance)+"%";
                result.setText(output);
            }
        });
    }
}
