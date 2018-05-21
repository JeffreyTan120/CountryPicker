package com.example.country.countrypicker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.country.jflibrary.PleaseDisplay;

public class MainActivity extends AppCompatActivity {
    public PleaseDisplay pleaseDisplay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View v = new TestClass(this);
        pleaseDisplay = new PleaseDisplay();
        setContentView(v);
//        setContentView(R.layout.activity_main);
    }
}
