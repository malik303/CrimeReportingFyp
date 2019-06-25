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
import java.util.HashMap;
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

import com.android.volley.AuthFailureError;
import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.StringRequest;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

public class SignupActivity extends AppCompatActivity  {

    private final String REGISTER_URL ="http://10.0.2.2/crimereport/registration.php";
    private final String LOGIN_URL ="http://192.168.43.100/culture/login.php";
    private String TAG_SUCCESS="success";


    String URL= "http://localhost/crimereport/registration.php";

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


                    String FirstName = Fname.getText().toString();
                    String MiddleName = Mname.getText().toString();
                    String LastName = Lname.getText().toString();
                    String PhoneNo = phoneno.getText().toString();
                    String Password = password1.getText().toString();
                    String Confpassword = confpassword.getText().toString();
                    String type = "signup";

                    if (FirstName.equals("") | MiddleName.equals("") | LastName.equals("") | PhoneNo.equals("") | Password.equals("") | Confpassword.equals("")) {

                        Toast signupfields = Toast.makeText(SignupActivity.this, "Fill all the fields!", Toast.LENGTH_SHORT);
                        signupfields.show();

                    }

                    if (!Password.equals(Confpassword)) {

                        Toast password = Toast.makeText(SignupActivity.this, "Password don't match", Toast.LENGTH_SHORT);
                        password.show();
                    } else {
                        //insert the details in database
                        //JSONParser attemptLogin= new JSONParser();
                       // attemptLogin.execute(type,FirstName,MiddleName,LastName,PhoneNo,Password,Confpassword);

                        doRegister(FirstName, MiddleName, LastName, PhoneNo, Password, Confpassword);



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



            String FirstName = args[0];
            String MiddleName = args[1];
            String LastName = args[2];
            String PhoneNo= args[3];
            String Password = args[4];
            String Confpassword = args[5];




            ArrayList params = new ArrayList();
            params.add(new BasicNameValuePair("FirstName", FirstName));
            params.add(new BasicNameValuePair("MiddleName", MiddleName));
            params.add(new BasicNameValuePair("LastName", LastName));
            params.add(new BasicNameValuePair("PhoneNo", PhoneNo));
            params.add(new BasicNameValuePair("Password", Password));
            params.add(new BasicNameValuePair("Confpassword", Confpassword));

            JSONObject json = jsonParser.makeHttpRequest(URL, "POST", params);
            Toast.makeText(getApplicationContext(), "sign up", Toast.LENGTH_LONG).show();
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

    public void doRegister(final String firstName, final String middleName, final String lastName, final String mobileNo, final String password, final String confPassword){


        RequestQueue mRequestQueue;

// Instantiate the cache
        Cache cache = new DiskBasedCache(this.getCacheDir(), 1024 * 1024); // 1MB cap

// Set up the network to use HttpURLConnection as the HTTP client.
        Network network = new BasicNetwork(new HurlStack());

// Instantiate the RequestQueue with the cache and network.
        mRequestQueue = new RequestQueue(cache, network);

// Start the queue
        mRequestQueue.start();

        //change this IP 192.168.56.1 to your IP


        //access online scripts,using this url you can even run to your mobile
        //String url ="http://coict.alfadroid.com/BuyandSell/register.php";

// Formulate the request and handle the response.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, REGISTER_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.i("Success", response);
                        // Do something with the response
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Integer success=jsonObject.getInt(TAG_SUCCESS);
                            if(success==1){
                                //tumeregister freshi
                                Toast.makeText(SignupActivity.this, "We've registered", Toast.LENGTH_LONG).show();
                            }else{

                                  //tumefeli
                                Toast.makeText(SignupActivity.this, "Tumefeli", Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.d("Error",e.toString());
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error
                        Toast.makeText(SignupActivity.this,"Error " + error, Toast.LENGTH_SHORT).show();
                    }
                }){

            @Override
            protected Map<String ,String> getParams() throws AuthFailureError {

                Map<String,String> params=new HashMap<String,String>();
                params.put("FirstName",firstName);
                params.put("MiddleName",middleName);
                params.put("LastName",lastName);
                params.put("PhoneNo",mobileNo);
                params.put("Password",password);
                params.put("Confpassword",password);


                return params;
            }};

        //stringRequest.setShouldCache(false); //Use this line if dont want to save in cache
// Add the request to the RequestQueue.
        stringRequest.setShouldCache(false);
        mRequestQueue.add(stringRequest);

    }

    public void doLogin(final String username, final String password){

        Log.i("DB.LOGIN","We get here");

        RequestQueue mRequestQueue;

// Instantiate the cache
        Cache cache = new DiskBasedCache(this.getCacheDir(), 1024 * 1024); // 1MB cap

// Set up the network to use HttpURLConnection as the HTTP client.
        Network network = new BasicNetwork(new HurlStack());

// Instantiate the RequestQueue with the cache and network.
        mRequestQueue = new RequestQueue(cache, network);

// Start the queue
        mRequestQueue.start();

        //change this IP 192.168.56.1 to your IP


        //access online scripts,using this url you can even run to your mobile
        //String url ="http://coict.alfadroid.com/BuyandSell/login.php";

// Formulate the request and handle the response.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, LOGIN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        // Do something with the response
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Integer success=jsonObject.getInt(TAG_SUCCESS);
                            if(success==1){
                                //tumelogin
                            }else{

                                //tumefeli

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.d("Error",e.toString());
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error
                        Toast.makeText(SignupActivity.this,"Error"+error,Toast.LENGTH_SHORT).show();
                    }
                }){

            @Override
            protected Map<String ,String> getParams() throws AuthFailureError {

                Map<String,String> params=new HashMap<String,String>();
                params.put("username",username);
                params.put("password",password);

                return params;
            }};

        //stringRequest.setShouldCache(false); //Use this line if dont want to save in cache
// Add the request to the RequestQueue.
        mRequestQueue.add(stringRequest);

    }


}


