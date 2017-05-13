package fr.falconteam.www.projetgsb;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    EditText login, password;
    Button btnSignIn;
    private RequestQueue requestQueue;
    private StringRequest request;


SingletonUser s1 = SingletonUser.getInstance();

    String URL = s1.getAdresseIP() + "login.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        login = (EditText) findViewById(R.id.login);
        password = (EditText) findViewById(R.id.password);


        btnSignIn = (Button) findViewById(R.id.btnSignIn);

        requestQueue = Volley.newRequestQueue(this);


        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject.getString("message").equals("Bienvenue")) {
                                Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(getApplicationContext(), MainUserActivity.class);
                                SingletonUser s1 = SingletonUser.getInstance();
                                s1.setUserId(jsonObject.getString("id"));
                                startActivity(intent);
                            } else {
                                Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        HashMap<String, String> hashmap = new HashMap<String, String>();
                        hashmap.put("login", login.getText().toString());
                        hashmap.put("mdp", password.getText().toString());

                        return hashmap;


                    }
                };
                requestQueue.add(request);
            }
        });

    }

}
