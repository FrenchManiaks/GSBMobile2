package fr.falconteam.www.projetgsb;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import org.json.JSONStringer;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class CptRenduFragment extends Fragment {

    SingletonUser s1 = SingletonUser.getInstance();

    String URL_CptRendu = s1.getAdresseIP() +"getCompteRenduById.php";
    EditText CptRenduID;
    Button SendCptRendu;
    private RequestQueue requestQueue;
    private StringRequest request;



    public CptRenduFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_cpt_rendu, container, false);


        CptRenduID = (EditText) view.findViewById(R.id.etCptRendu);
        SendCptRendu = (Button) view.findViewById(R.id.bCptRendu);

        final SingletonUser s1 = SingletonUser.getInstance();


        requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());

        SendCptRendu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                request = new StringRequest(Request.Method.POST, URL_CptRendu, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
//                            JSONArray jsonArrayrcpt= new JSONArray(response);
//                            JSONObject jsonObjectCpt = jsonArrayrcpt.getJSONObject(0);
                            JSONObject jsonObjectCpt = new JSONObject(response);
                            if (jsonObjectCpt.has("id")) {
                                Toast.makeText(getActivity(), "Le compte rendu est en train de charger.", Toast.LENGTH_LONG).show();
                                SingleTonCptRendu s2 = SingleTonCptRendu.getInstance();
                                s2.setCptId(jsonObjectCpt.getString("id"));
                                s2.setCptDate(jsonObjectCpt.getString("date"));
                                s2.setCptbilan(jsonObjectCpt.getString("bilan"));
                                s2.setCptCoefConfiance(jsonObjectCpt.getString("coef_confiance"));
                                s2.setCptCoefNoto(jsonObjectCpt.getString("coef_notoriete"));
                                s2.setCptCoefPrescription(jsonObjectCpt.getString("coef_prescription"));
                                s2.setCptMotif(jsonObjectCpt.getString("motif"));
                                JSONObject jsonObjectMotif = new JSONObject(s2.getCptMotif());
                                    if (jsonObjectMotif.has("id")){
                                        s2.setMotifLibelle(jsonObjectMotif.getString("libelle"));
                                        s2.setMotifDescription(jsonObjectMotif.getString("description"));
                                    }
                                s2.setCptEchantillon(jsonObjectCpt.getString("echantillons"));
                                JSONArray jsonArrayEchantillon = new JSONArray(s2.getCptEchantillon());
                                JSONObject jsonObjectEchantillon = jsonArrayEchantillon.getJSONObject(0);
                                    if (jsonObjectEchantillon.has("id")){
                                        s2.setEchantillonId(jsonObjectEchantillon.getString("id"));
                                        s2.setEchantillonQte(jsonObjectEchantillon.getString("qte"));
                                    }
                                Intent intent = new Intent(getActivity(), AffichageCompteRendu.class);
                                startActivity(intent);

                            }
                            if (jsonObjectCpt.getString("message").equals("Ce numero de compte rendu n'existe pas.")){
                                Toast.makeText(getActivity(), jsonObjectCpt.getString("message"), Toast.LENGTH_LONG).show();

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
                        hashmap.put("compte_rendu_id", CptRenduID.getText().toString());
                        return hashmap;


                    }
                };
                requestQueue.add(request);
            }
        });






        return view;
    }

}
