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


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_parc_auto, container, false);


        Spinner mySpinner=(Spinner) view.findViewById(R.id.spinner);


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
                                JSONObject jsonObjectParc = jsonArrayParc.getJSONObject(0);
                                if (jsonObjectParc.has("immatricule")) {

                                    ArrayList <String> Type = new ArrayList<String>();
                                    ArrayList <String> dispo = new ArrayList<String>();
                                    ArrayList <String> TypeVehicle = new ArrayList<String>();
                                    for (int i = 0; i < jsonArrayParc.length();i++){
                                        JSONObject jsonObjectId = jsonArrayParc.getJSONObject(i);
                                        dispo.add(jsonObjectId.getString("disponible"));
                                        s2.setDispo(dispo);
                                        TypeVehicle.add(jsonObjectId.getString("type_vehicule"));
                                        s2.setTypeVehicule(TypeVehicle);
                                    }



                                    JSONArray jsonArrayType = new JSONArray(s2.getTypeVehicule());
                                    for (int i = 0; i < jsonArrayType.length(); i++){
                                        JSONObject jsonObjectType = new JSONObject(jsonArrayType.getString(i));
                                        Type.add(jsonObjectType.getString("libelle"));
                                        s2.setType(Type);
                                    }


//                                    String[] menuItems = {"TEST."};

                                    ListView listView = (ListView) view.findViewById(R.id.lvParcAuto);

//                                    ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
//                                            getActivity(),
//                                            android.R.layout.simple_list_item_1,
//                                            menuItems
//                                    );

                                    String[] values = new String[] { "Message1", "Message2", "Message3" };
                                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                                            android.R.layout.simple_list_item_1, values);
                                    listView.setAdapter(adapter);

//                                    listView.setAdapter(listViewAdapter);
                                } else {

                                    String[] menuItems = {"Vous n'avez pas de rendez-vous actuellement."};

                                    ListView listView = (ListView) view.findViewById(R.id.lvParcAuto);

                                    ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
                                            getActivity(),
                                            android.R.layout.simple_list_item_1,
                                            menuItems
                                    );

//                                    listView.setAdapter(listViewAdapter);
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
