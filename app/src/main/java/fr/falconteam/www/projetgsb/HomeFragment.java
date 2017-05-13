package fr.falconteam.www.projetgsb;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import junit.framework.Test;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment  {


    public HomeFragment() {
        // Required empty public constructor
    }
    SingletonUser s1 = SingletonUser.getInstance();

    String rdv_URL = s1.getAdresseIP() +"getRdvByIdVisiteur.php";
    String userid;
    private RequestQueue requestQueue;
    private StringRequest request;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_home, container, false);

        SingletonUser s1 = SingletonUser.getInstance();


        /*****************************ListView RDV Perso*****************************/


        userid = s1.getUserId();

//        getdata = (Button) findViewById(R.id.buttontest);

        requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());

        request = new StringRequest(Request.Method.POST, rdv_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray jsonArrayrv = new JSONArray(response);
                    JSONObject jsonObjectrdv = jsonArrayrv.getJSONObject(0);
                    if (jsonObjectrdv.has("id")) {
                        SingletonUser s1 = SingletonUser.getInstance();
                        ArrayList<String> idlist = new ArrayList<>();
                        ArrayList<String> DateList = new ArrayList<>();
                        ArrayList<String> NomList = new ArrayList<>();
                        ArrayList<String> PrenomList = new ArrayList<>();
                        ArrayList<String> TestList = new ArrayList<>();
                        ArrayList<String> RdvList = new ArrayList<>();

                        ArrayList <String> test = new ArrayList<>();

                        for (int i = 0; i < jsonArrayrv.length() ; i++){
                            JSONObject jsonObjectId = jsonArrayrv.getJSONObject(i);
                            idlist.add(jsonObjectId.getString("id"));
                            DateList.add(jsonObjectId.getString("date"));
                            s1.setRdvId(idlist);
                            s1.setRdvDate(DateList);




                            /**TEST
                             *
                             */

                            s1.setRdvInfo(jsonObjectrdv.getString("description"));
                                JSONObject jsonObjecttes = jsonArrayrv.getJSONObject(i);
                                TestList.add(jsonObjecttes.getString("praticien"));

                            JSONArray jsonArrayPraticien = new JSONArray(TestList);

                                    JSONObject jsonObject = new JSONObject(jsonArrayPraticien.getString(i));
                                    NomList.add(jsonObject.getString("nom"));
                                    PrenomList.add(jsonObject.getString("prenom"));

                                    RdvList.add(DateList + " Avec "+ NomList + " " + PrenomList);

                                    s1.setRdvPrenomPraticien(PrenomList);
                                    s1.setRdvNomPraticien(NomList);

                            test.add("N°" + jsonObjectId.getString("id") + "_ Le " + jsonObjectId.getString("date") +
                            "\n Avec Mr./Mme." + jsonObject.getString("nom") + " " + jsonObject.getString("prenom"));

//                                }

                        }


                        final ListView listView = (ListView) view.findViewById(R.id.RdvListView);


                        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
                                getActivity(),
                                android.R.layout.simple_list_item_1,
                                test
                        );

                        listView.setAdapter(listViewAdapter);

                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            public void onItemClick(AdapterView<?> myAdapter, View myView, int myItemInt, long mylng) {
                                String selectedFromList =(String) (listView.getItemAtPosition(myItemInt));

                            }
                        });

                    } else {

                        String[] menuItems = {"Vous n'avez pas de rendez-vous actuellement."};

                        ListView listView = (ListView) view.findViewById(R.id.RdvListView);

                        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
                                getActivity(),
                                android.R.layout.simple_list_item_1,
                                menuItems
                        );

                        listView.setAdapter(listViewAdapter);
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



        /*****************************/



//            TextView newtext = (TextView) view.findViewById(R.id.UserID);
//            newtext.setText(s1.getRdvId());


        return view;
        }
    // Inflate the layout for this fragment
       //return inflater.inflate(R.layout.fragment_home, container, false);
    }


//}