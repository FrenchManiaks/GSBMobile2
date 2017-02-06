package fr.falconteam.www.projetgsb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static android.R.id.message;

public class LoginActivity extends AppCompatActivity {

    EditText editPassword, editName;
    Button btnSignIn;

    String URL= "http://apheliotropic-cell.000webhostapp.com/index.php";

    JSONParser jsonParser=new JSONParser();

    int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editName=(EditText)findViewById(R.id.editName);
        editPassword=(EditText)findViewById(R.id.editPassword);
        final TextView fpasswordLink = (TextView) findViewById(R.id.tvFPassword);

        btnSignIn=(Button)findViewById(R.id.btnSignIn);

        fpasswordLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent fpasswordIntent = new Intent(LoginActivity.this, fPasswordActivity.class);
                LoginActivity.this.startActivity(fpasswordIntent);
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AttemptLogin attemptLogin= new AttemptLogin();
                attemptLogin.execute(editName.getText().toString(),editPassword.getText().toString(),"");
            }
        });

    }

    private class AttemptLogin extends AsyncTask<String, String, JSONObject> {

        @Override

        protected void onPreExecute() {

            super.onPreExecute();

        }

        @Override

        protected JSONObject doInBackground(String... args) {



            String email = args[2];
            String password = args[1];
            String name= args[0];

            ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("username", name));
            params.add(new BasicNameValuePair("password", password));
            if(email.length()>0)
                params.add(new BasicNameValuePair("email",email));

            JSONObject json = jsonParser.makeHttpRequest(URL, "POST", params);


            return json;

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

                if (result.getString("message").equals("Successfully logged in"))
                {
                    Toast.makeText(getApplicationContext(),result.getString("message"),Toast.LENGTH_LONG).show();
                    // Open MainUserActivity
                    Intent intent = new Intent(getApplicationContext(), MainUserActivity.class);
                    startActivity(intent);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }

    }

}
