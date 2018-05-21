package com.example.country.countrypicker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.country.jflibrary.CountryPicker;

public class MainActivity extends AppCompatActivity {
    public CountryPicker pleaseDisplay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View v = new TestClass(this);
        pleaseDisplay = new CountryPicker();
        setContentView(v);
//        setContentView(R.layout.activity_main);
    }
}
