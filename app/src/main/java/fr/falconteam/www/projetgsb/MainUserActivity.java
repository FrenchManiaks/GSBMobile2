package fr.falconteam.www.projetgsb;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class MainUserActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    NavigationView navigationView = null;
    Toolbar toolbar = null;
    String rdv_URL = "http://192.168.43.224:80/apigsb/getRdvByIdVisiteur.php";
    String userid;
    Button getdata;
    Object success;
    private RequestQueue requestQueue;
    private StringRequest request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_user);

        /**START of :
         * Fetch data from getRdvByIdVisiteur
         */

        SingletonUser s1 = SingletonUser.getInstance();
        userid = s1.getUserId();

//        getdata = (Button) findViewById(R.id.buttontest);

        requestQueue = Volley.newRequestQueue(this);

        request = new StringRequest(Request.Method.POST, rdv_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray jsonArrayrv = new JSONArray(response);
                    JSONObject jsonObjectrdv = jsonArrayrv.getJSONObject(0);
                    if (jsonObjectrdv.has("id")) {
                        Toast.makeText(getApplicationContext(), jsonObjectrdv.getString("date"), Toast.LENGTH_LONG).show();
                        SingletonUser s1 = SingletonUser.getInstance();
                        s1.setRdvId(jsonObjectrdv.getString("id"));
                        s1.setRdvDate(jsonObjectrdv.getString("date"));
                        s1.setRdvInfo(jsonObjectrdv.getString("description"));
                        s1.setRdvPraticien(jsonObjectrdv.getString("praticien"));
                        JSONObject jsonObjectPraticien = new JSONObject(s1.getRdvPraticien());
                            if (jsonObjectPraticien.has("id")){

                                s1.setRdvPrenomPraticien(jsonObjectPraticien.getString("prenom"));
                                s1.setRdvNomPraticien(jsonObjectPraticien.getString("nom"));
                                s1.setRdvPTelFixe(jsonObjectPraticien.getString("telephone_fixe"));
                                s1.setRdvPTelPortable(jsonObjectPraticien.getString("telephone_portable"));
                            }else{
                                Toast.makeText(getApplicationContext(), "ERROR Get Praticien", Toast.LENGTH_LONG).show();
                            }
                        s1.setRdvLieu(jsonObjectrdv.getString("lieu"));
                        JSONObject jsonObjectlieu = new JSONObject(s1.getRdvLieu());
                            if (jsonObjectlieu.has("libelle")){
                                s1.setRdvLieuLibelle(jsonObjectlieu.getString("libelle"));
                                s1.setRdvLieuAdresse(jsonObjectlieu.getString("adresse"));
                                s1.setRdvLieuCP(jsonObjectlieu.getString("cp"));
                                s1.setRdvLieuCP(jsonObjectlieu.getString("ville"));
                            }else{
                                Toast.makeText(getApplicationContext(), "ERROR Get Lieu", Toast.LENGTH_LONG).show();
                            }

                    } else {
                        Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_LONG).show();
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
                hashmap.put("id_visiteur", userid.toString());

                return hashmap;


            }
        };
        requestQueue.add(request);


        /**END of :
         * Fetch data from getRdvByIdVisiteur
         */


    }

    @Override
    protected void onStart() {
        super.onStart();

        //Set the fragment init
        HomeFragment fragment = new HomeFragment();
        android.support.v4.app.FragmentTransaction fragmentTransaction =
                getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_user, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_acceuil) {

            //Set the fragment init
            HomeFragment fragment = new HomeFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();

        } else if (id == R.id.nav_gestionfrais) {

            //Set the fragment init
            GestionFraisFragment fragment = new GestionFraisFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();

        } else if (id == R.id.nav_cptrendu) {

            //Set the fragment init
            CptRenduFragment fragment = new CptRenduFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();

        } else if (id == R.id.nav_agcommun) {

            //Set the fragment init
            AgdCommunFragment fragment = new AgdCommunFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();

        } else if (id == R.id.nav_assistancetel) {

            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:0622822897"));
            startActivity(intent);

        } else if (id == R.id.nav_assistancemail) {

            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("plain/text");
            intent.putExtra(Intent.EXTRA_EMAIL, new String[] { "support@gsb.fr" });
            intent.putExtra(Intent.EXTRA_SUBJECT, "");
            intent.putExtra(Intent.EXTRA_TEXT, "");
            startActivity(Intent.createChooser(intent, ""));

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
