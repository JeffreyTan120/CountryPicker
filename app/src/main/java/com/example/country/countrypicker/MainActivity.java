package com.example.country.countrypicker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private PleaseDisplay pleaseDisplay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View v = new TestClass(this);
        pleaseDisplay = new PleaseDisplay();
        setContentView(v);
//        setContentView(R.layout.activity_main);
    }
}
