package fr.falconteam.www.projetgsb;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TabHost;
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

import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment  {


    public HomeFragment() {
        // Required empty public constructor
    }

    String rdv_URL = "http://192.168.43.224:80/apigsb/getRdvByIdVisiteur.php";
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
                            s1.setRdvPMail(jsonObjectPraticien.getString("mail"));
                        }else{
//                            Toast.makeText(getApplicationContext(), "ERROR Get Praticien", Toast.LENGTH_LONG).show();
                        }
                        s1.setRdvLieu(jsonObjectrdv.getString("lieu"));
                        JSONObject jsonObjectlieu = new JSONObject(s1.getRdvLieu());
                        if (jsonObjectlieu.has("libelle")){
                            s1.setRdvLieuLibelle(jsonObjectlieu.getString("libelle"));
                            s1.setRdvLieuAdresse(jsonObjectlieu.getString("adresse"));
                            s1.setRdvLieuCP(jsonObjectlieu.getString("cp"));
                            s1.setRdvLieuCP(jsonObjectlieu.getString("ville"));
                        }else{
//                            Toast.makeText(getApplicationContext(), "ERROR Get Lieu", Toast.LENGTH_LONG).show();
                        }

                    } else {
//                        Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_LONG).show();
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