package com.example.adam_malik.crimereporting;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


//new imports

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SignupActivity extends AppCompatActivity  {

    String URL= "http://localhost/crimereport/index.php";

    JSONParser jsonParser=new JSONParser();


    private static final String TAG = "SignupActivity";
    public TextView DisplayDate;
    private DatePickerDialog.OnDateSetListener DateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        DisplayDate = findViewById(R.id.Selectdate);
        DisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        SignupActivity.this,
                        android.R.style.Theme_Holo_Dialog_MinWidth,
                        DateSetListener,
                        year, month, day
                );
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        DateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int day, int month, int year) {
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd/yyy: " + day + "/" + month + "/" + year);
                String date = day + "/" + month + "/" + year;
                DisplayDate.setText(date);
            }
        };

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button

                if (v.getId() == R.id.button) {
                    EditText Fname = findViewById(R.id.txtFname);
                    EditText Mname = findViewById(R.id.txtMname);
                    EditText Lname = findViewById(R.id.txtLname);
                    EditText phoneno = findViewById(R.id.txtMobno);
                    EditText password1 = findViewById(R.id.txtpassword);
                    EditText confpassword = findViewById(R.id.txtConfpassword);


                    String Fnamestr = Fname.getText().toString();
                    String Mnamestr = Mname.getText().toString();
                    String Lnamestr = Lname.getText().toString();
                    String phonenostr = phoneno.getText().toString();
                    String passwordstr = password1.getText().toString();
                    String confpasswordstr = confpassword.getText().toString();
                    String type = "signup";

                    if (Fnamestr.equals("") | Mnamestr.equals("") | Lnamestr.equals("") | phonenostr.equals("") | passwordstr.equals("") | confpasswordstr.equals("")) {

                        Toast signupfields = Toast.makeText(SignupActivity.this, "Fill all the fields!", Toast.LENGTH_SHORT);
                        signupfields.show();

                    }

                    if (!passwordstr.equals(confpasswordstr)) {

                        Toast password = Toast.makeText(SignupActivity.this, "Password don't match", Toast.LENGTH_SHORT);
                        password.show();
                    } else {
                        //insert the details in database
                        JSONParser attemptLogin= new JSONParser();
                        attemptLogin.execute(type,Fnamestr,Mnamestr,Lnamestr,phonenostr,passwordstr);



                    }

                }


            }
        });



    }

    private class JSONParser extends AsyncTask<String, String, JSONObject> {


        @Override

        protected void onPreExecute() {

            super.onPreExecute();

        }

        @Override

        protected JSONObject doInBackground(String... args) {



            String Fname = args[0];
            String Mname = args[1];
            String Lname= args[2];
            String phoneno= args[3];
            String password= args[4];



            ArrayList params = new ArrayList();
            params.add(new BasicNameValuePair("Fname", Fname));
            params.add(new BasicNameValuePair("Mname", Mname));
            params.add(new BasicNameValuePair("Lname", Lname));
            params.add(new BasicNameValuePair("phoneno", phoneno));
            params.add(new BasicNameValuePair("password", password));

            JSONObject json = jsonParser.makeHttpRequest(URL, "POST", params);

            return json;

        }

        private JSONObject makeHttpRequest(String url, String post, ArrayList params) {

            return null;
        }

        protected void onPostExecute(JSONObject result) {

            // dismiss the dialog once product deleted
            //Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();

            try {
                if (result != null) {
                    Toast.makeText(getApplicationContext(),result.getString("message"),Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Unable to retrieve any data from server", Toast.LENGTH_LONG).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }

    }


    }


