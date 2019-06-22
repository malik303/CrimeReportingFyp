package com.example.adam_malik.crimereporting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class CrimeReporting extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime_reporting);

        Button button = findViewById(R.id.btnReportCrime);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button

                if (v.getId() == R.id.button) {
//                    Spinner spinner = findViewById(R.id.spinner);
                    EditText reportdesc = findViewById(R.id.reportdesc);
                    EditText location = findViewById(R.id.location);



//                    String spinner_str = spinner_strzpinner.getSelectedItem().toString();
                    String reportdesc_str  = reportdesc.getText().toString();
                    String location_str = location.getText().toString();

                }
            }
        });

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);

        List<String> categories = new ArrayList<String>();
        categories.add("Theft");
        categories.add("robery");
        categories.add("Drug Abuse");
        categories.add("Rape");
        categories.add("Prostitution");
        categories.add("Home/Work invasion");
        categories.add("Threatening");
        categories.add("Others");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
