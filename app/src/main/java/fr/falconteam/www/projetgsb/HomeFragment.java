package fr.falconteam.www.projetgsb;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

//        TabHost host = (TabHost)view.findViewById(R.id.tabHost);
//        host.setup();
        SingletonUser s1 = SingletonUser.getInstance();
//
//        //Tab 1
//        TabHost.TabSpec spec = host.newTabSpec("Tab One");
//        spec.setContent(R.id.tab1);
//        spec.setIndicator("RDV Perso");
//        host.addTab(spec);

//        //Tab 2
//        spec = host.newTabSpec("Tab Two");
//        spec.setContent(R.id.tab2);
//        spec.setIndicator("");
//        host.addTab(spec);

        //Tab 3
//        spec = host.newTabSpec("Tab Three");
//        spec.setContent(R.id.tab3);
//        spec.setIndicator("RDV Perso");
//        host.addTab(spec);



        //ListView

        /*****************************ListView Activités*****************************/







        /*****************************ListView Parc Auto*****************************/

//        String[] menuItems2 = {"Affiche quelque chose 2",
//                "Affiche quelque chose d'autre 2",
//                "Bon, maintenant il faut afficher les info de la BDD"};
//
//        ListView listView2 = (ListView) view.findViewById(R.id.mainLv2);
//
//        ArrayAdapter<String> listViewAdapter2 = new ArrayAdapter<String>(
//                getActivity(),
//                android.R.layout.simple_list_item_1,
//                menuItems2
//        );
//
//        listView2.setAdapter(listViewAdapter2);



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

                        for (int i = 0; i < jsonArrayrv.length() ; i++){
                            JSONObject jsonObjectId = jsonArrayrv.getJSONObject(i);
                            idlist.add(jsonObjectId.getString("id"));
                            DateList.add(jsonObjectId.getString("date"));
                            s1.setRdvId(idlist);
                            s1.setRdvDate(DateList);
                        }
                        s1.setRdvInfo(jsonObjectrdv.getString("description"));

                        for (int i = 0; i < jsonArrayrv.length(); i++){
                            JSONObject jsonObjecttes = jsonArrayrv.getJSONObject(i);
                            TestList.add(jsonObjecttes.getString("praticien"));
//                            s1.setRdvPraticien(TestList);

                        }
//                        JSONArray jsonArrayPraticien = new JSONArray(TestList);

//                        if (jsonObjectPraticien.has("id")){

//                        for (int j = 0 ; j < TestList.size(); j++){
//                            NomList.add(jsonObjectPraticien.getString("nom"));
//                            PrenomList.add(jsonObjectPraticien.getString("prenom"));
//                            s1.setRdvPrenomPraticien(PrenomList);
//                            s1.setRdvNomPraticien(NomList);
//                        }

//                            s1.setRdvPTelFixe(jsonObjectPraticien.getString("telephone_fixe"));
//                            s1.setRdvPTelPortable(jsonObjectPraticien.getString("telephone_portable"));
//                            s1.setRdvPMail(jsonObjectPraticien.getString("mail"));
//                        }
//                        s1.setRdvLieu(jsonObjectrdv.getString("lieu"));
//                        JSONObject jsonObjectlieu = new JSONObject(s1.getRdvLieu());
//                        if (jsonObjectlieu.has("libelle")){
//                            s1.setRdvLieuLibelle(jsonObjectlieu.getString("libelle"));
//                            s1.setRdvLieuAdresse(jsonObjectlieu.getString("adresse"));
//                            s1.setRdvLieuCP(jsonObjectlieu.getString("cp"));
//                            s1.setRdvLieuCP(jsonObjectlieu.getString("ville"));
//                        }

                        String[] menuItems = {"Rendez-vous avec " +s1.getRdvNomPraticien() + " " +s1.getRdvPrenomPraticien()+ " le "+ s1.getRdvDate(),
                                "Affiche quelque chose d'autre",
                                "Bon, maintenant il faut afficher les info de la BDD"};

                        ListView listView = (ListView) view.findViewById(R.id.RdvListView);

                        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
                                getActivity(),
                                android.R.layout.simple_list_item_1,
                                menuItems
                        );

                        listView.setAdapter(listViewAdapter);

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