package fr.falconteam.www.projetgsb;


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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class GestionFraisFragment extends Fragment {


    public GestionFraisFragment() {
        // Required empty public constructor
    }

    SingletonUser s1 = SingletonUser.getInstance();

    String URL_Frais = s1.getAdresseIP() +"addfrais.php";
    String userid;
    Button sendData;
    EditText TypeFrais, DateFrais, MontantFrais, CommentaireFrais;
    private RequestQueue requestQueue;
    private StringRequest request;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_gestion_frais, container, false);

        TypeFrais = (EditText) view.findViewById(R.id.edTypeFrais);
        DateFrais = (EditText) view.findViewById(R.id.etDateFrais);
        MontantFrais = (EditText) view.findViewById(R.id.etMontantFrais);
        CommentaireFrais = (EditText) view.findViewById(R.id.etCommentaireFrais);
        sendData = (Button) view.findViewById(R.id.bEnvoyerFrais);

        final SingletonUser s1 = SingletonUser.getInstance();


        requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());

        sendData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                request = new StringRequest(Request.Method.POST, URL_Frais, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject != null) {
                                Toast.makeText(getActivity(), jsonObject.getString("Le frais a bien été ajouté."), Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(getActivity(), jsonObject.getString("Erreur, le frais n'as pas pu être envoyé."), Toast.LENGTH_LONG).show();
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
                        hashmap.put("date", DateFrais.getText().toString());
                        hashmap.put("commentaire", CommentaireFrais.getText().toString());
                        hashmap.put("montant", MontantFrais.getText().toString());
                        hashmap.put("id_utilisateur", s1.getUserId().toString());
                        hashmap.put("id_type_frais", TypeFrais.getText().toString());

                        return hashmap;


                    }
                };
                requestQueue.add(request);
            }
        });


        return view;
//        return inflater.inflate(R.layout.fragment_gestion_frais, container, false);
    }

}
