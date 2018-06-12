package com.example.country.countrypicker;

import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.example.country.jflibrary.CountryLayout;
import com.example.country.jflibrary.CountryPicker;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        String[] countryName = getResources().getStringArray(R.array.country_name);
        String[] countryCode = getResources().getStringArray(R.array.country_code);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.select_dialog_singlechoice, countryName);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        AutoCompleteTextView acTextView = findViewById(R.id.languages);

        CountryLayout countryLayout = findViewById(R.id.languages);
        countryLayout.setThreshold(1);
        countryLayout.setAdapter(adapter);
    }


}
