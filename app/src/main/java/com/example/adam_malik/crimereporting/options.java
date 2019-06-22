package com.example.adam_malik.crimereporting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class options extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        Button OpCrime = findViewById(R.id.opcrime);
        OpCrime.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {
                                           startActivity(new Intent(options.this, CrimeReporting.class));
                                       }
                                   });

                Button OpLostPerson = findViewById(R.id.oplostperson);
                OpLostPerson.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(options.this, LostPersonActivity.class));


                    }
                });
            }
        }

