package fr.falconteam.www.projetgsb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AffichageParcAuto extends AppCompatActivity {


    private RequestQueue requestQueue;
    private StringRequest request;
    SingletonUser s1 = SingletonUser.getInstance();
    String URL = s1.getAdresseIP() + "getvehicule.php";
    SingletonParcAuto s2 = SingletonParcAuto.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affichage_parc_auto);

        final TextView immatricule, marque, modele, energie, km, info, equip,type;

        immatricule = (TextView) findViewById(R.id.tvMatricule);
        marque = (TextView) findViewById(R.id.tvMarque);
        modele = (TextView) findViewById(R.id.tvModele);
        energie = (TextView) findViewById(R.id.tvEnergie);
        km = (TextView) findViewById(R.id.tvKm);
        info = (TextView) findViewById(R.id.tvInfo);
        equip = (TextView) findViewById(R.id.tvEquip);
        type = (TextView) findViewById(R.id.tvType);





        requestQueue = Volley.newRequestQueue(this);
        request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    info.setText(jsonObject.getString("description"));
                    km.setText("Kilométrage : "+jsonObject.getString("kilometrage"));
                    equip.setText(jsonObject.getString("equipement"));




                    JSONObject jsonObjectMarque = new JSONObject(jsonObject.getString("marque"));
                    JSONObject jsonObjectModele = new JSONObject(jsonObject.getString("modele"));
                    JSONObject jsonObjectEnergie = new JSONObject(jsonObject.getString("energie"));
                    JSONObject jsonObjectType = new JSONObject(jsonObject.getString("type_vehicule"));

                    marque.setText("Marque : "+jsonObjectMarque.getString("libelle"));
                    modele.setText("Modele : "+jsonObjectModele.getString("libelle"));
                    energie.setText("Energie : "+jsonObjectEnergie.getString("libelle"));
                    type.setText("Type Vehicule : "+jsonObjectType.getString("libelle"));

                    immatricule.setText("Immatricule : " + s2.getMatriculeResult());
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
                hashmap.put("immatricule_vehicule", s2.getMatriculeResult().toString());

                return hashmap;


            }
        };
        requestQueue.add(request);

    }
}
