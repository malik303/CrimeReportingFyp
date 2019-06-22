package com.example.adam_malik.crimereporting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.text.InputType;


public class Login extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button Loginbutton = findViewById(R.id.button2);
        Loginbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button

                if (v.getId() == R.id.button2) {
                    EditText MobNoLog = findViewById(R.id.mobno);
                    MobNoLog.setInputType(InputType.TYPE_CLASS_NUMBER);
                    String str = MobNoLog.getText().toString();

                    EditText pwd = findViewById(R.id.passwordLog);
                    String pass =  pwd.getText().toString();

                 if(str.equals("") | pass.equals("")){
                     Toast loginfields = Toast.makeText(Login.this, "make sure you've filled all fields!", Toast.LENGTH_SHORT);
                     loginfields.show();
                 }

                 else {
                     startActivity(new Intent(Login.this, options.class));
                 }
                    }


                }

        });

        Button reglnk = findViewById(R.id.reglink);
        reglnk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, SignupActivity.class));
            }
        });



    }
}