package com.example.smartandsafekitchen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    CardView btnWater, btnSmoke, btnGas, btnTemp, btnMap, btnRecipe;


                      //FireBase ID : smartsafekitchen@gmail.com
                         //Password : Tech1234


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnWater = findViewById(R.id.cardWaterSensor);
        btnSmoke = findViewById(R.id.cardSmokeSensor);
        btnGas = findViewById(R.id.cardGasSensor);
        btnTemp = findViewById(R.id.cardTempSensor);
        btnMap = findViewById(R.id.cardmaps);
        btnRecipe = findViewById(R.id.cardRecipe);


        btnWater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, WaterSensorActivity.class));
            }
        });
        btnSmoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SmokeSensorActivity.class));
            }
        });
        btnGas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, GasSensorActivity.class));
            }
        });
        btnTemp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TemperatureSensorActivity.class));
            }
        });

        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MapsActivity.class));
            }
        });

        btnRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, IngrListActivity.class));
            }
        });
    }
}
