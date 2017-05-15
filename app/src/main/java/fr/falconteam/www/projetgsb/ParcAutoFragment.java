package fr.falconteam.www.projetgsb;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

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
import java.util.List;
import java.util.Map;


public class ParcAutoFragment extends Fragment {

    public ParcAutoFragment() {
        // Required empty public constructor
    }

    Spinner spinnerParc;
    SingletonUser s1 = SingletonUser.getInstance();
    SingletonParcAuto s2 = SingletonParcAuto.getInstance();
    private RequestQueue requestQueue;
    private StringRequest request;
    String userid;
    String Parc_URL = s1.getAdresseIP() +"getVehiculeByParcAutoId.php";
    String i;
    ListView listView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_parc_auto, container, false);


        Spinner mySpinner=(Spinner) view.findViewById(R.id.spinner);
        listView = (ListView) view.findViewById(R.id.lvParcAuto);



        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, final View view, int pos, long id) {
                Object item = parent.getItemAtPosition(pos);
                if(item.equals("Lyon - PartDieu")){

                    i = "1";

                }else if (item.equals("Atoz - Christianne")){

                    i = "2";

                }else if (item.equals("Oullins - Avicenne")){

                    i = "3";

                }
                if(i != "0"){

                    requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());

                    request = new StringRequest(Request.Method.POST, Parc_URL, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            try {
                                JSONArray jsonArrayParc = new JSONArray(response);

                                if (!jsonArrayParc.equals(null)){
                                    JSONObject jsonObjectParc = jsonArrayParc.getJSONObject(0);
                                }
                                JSONObject jsonObjectParc = jsonArrayParc.getJSONObject(0);
                                if (jsonObjectParc.has("immatricule")) {

                                    ArrayList <String> Type = new ArrayList<String>();
                                    ArrayList <String> dispo = new ArrayList<String>();
                                    ArrayList <String> TypeVehicle = new ArrayList<String>();
                                    ArrayList <String> TypeEnergie = new ArrayList<String>();
                                    ArrayList <String>  Energie = new ArrayList<String>();
                                    ArrayList <String> Matricule = new ArrayList<String>();

                                    ArrayList <String> remplissagelist = new ArrayList<String>();
                                    for (int i = 0; i < jsonArrayParc.length();i++){
                                        JSONObject jsonObjectId = jsonArrayParc.getJSONObject(i);
                                        dispo.add(jsonObjectId.getString("disponible"));
                                        s2.setDispo(dispo);
                                        TypeVehicle.add(jsonObjectId.getString("type_vehicule"));
                                        Matricule.add(jsonObjectId.getString("immatricule"));

                                        s2.setTypeVehicule(TypeVehicle);
                                        TypeEnergie.add(jsonObjectId.getString("energie"));


                                    JSONArray jsonArrayType = new JSONArray(s2.getTypeVehicule());

                                        JSONObject jsonObjectType = new JSONObject(jsonArrayType.getString(i));
                                        Type.add(jsonObjectType.getString("libelle"));
                                        s2.setType(Type);

                                    JSONArray jsonArrayEnergie = new JSONArray(TypeEnergie);
                                        JSONObject jsonObjectEnergie = new JSONObject(jsonArrayEnergie.getString(i));
                                        Energie.add(jsonObjectEnergie.getString("libelle"));

                                        remplissagelist.add(jsonObjectId.getString("immatricule") + " "+
                                                jsonObjectType.getString("libelle")+ " "+ jsonObjectEnergie.getString("libelle"));

                                        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                                                android.R.layout.simple_list_item_1, remplissagelist);
                                        listView.setAdapter(adapter);

                                    }



                                } else {

                                    String[] menuItems = {"Il n'y a pas de véhicules disponnibles dans ce Parc."};
//
                                    ListView listView = (ListView) view.findViewById(R.id.lvParcAuto);
//
                                    final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                                    android.R.layout.simple_list_item_1, menuItems);
                                    listView.setAdapter(adapter);
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
                            hashmap.put("id_parc_automobile", i);

                            return hashmap;


                        }
                    };
                    requestQueue.add(request);


                }
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        return view;
    }

}
