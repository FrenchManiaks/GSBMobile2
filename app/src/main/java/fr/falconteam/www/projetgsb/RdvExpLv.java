package fr.falconteam.www.projetgsb;

import android.content.Context;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;

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


public class RdvExpLv extends Fragment {

    public RdvExpLv() {
        // Required empty public constructor
    }

    SingletonUser s1 = SingletonUser.getInstance();

    String rdv_URL = s1.getAdresseIP() +"getRdvByIdVisiteur.php";
    String userid;
    String descriptionRdv;
    private RequestQueue requestQueue;
    private StringRequest request;
    ArrayList <String> test = new ArrayList<>();
    ArrayList <String> test2 = new ArrayList<>();

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader = new ArrayList<String>();
    HashMap<String, List<String>> listDataChild = new HashMap<String, List<String>>();;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view =  inflater.inflate(R.layout.fragment_rdv_exp_lv, container, false);



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
                        ArrayList<String> TestList2 = new ArrayList<>();
                        ArrayList<String> RdvList = new ArrayList<>();


                        for (int i = 0; i < jsonArrayrv.length() ; i++){
                            JSONObject jsonObjectId = jsonArrayrv.getJSONObject(i);
                            idlist.add(jsonObjectId.getString("id"));
                            DateList.add(jsonObjectId.getString("date"));
                            s1.setRdvId(idlist);
                            s1.setRdvDate(DateList);

                            s1.setRdvInfo(jsonObjectrdv.getString("description"));
                            JSONObject jsonObjecttes = jsonArrayrv.getJSONObject(i);
                            TestList.add(jsonObjecttes.getString("praticien"));
                            TestList2.add(jsonObjecttes.getString("lieu"));

                            JSONArray jsonArrayPraticien = new JSONArray(TestList);
                            JSONArray jsonArrayLieu = new JSONArray(TestList2);

                            JSONObject jsonObject = new JSONObject(jsonArrayPraticien.getString(i));
                            JSONObject jsonObjectLieu = new JSONObject(jsonArrayLieu.getString(i));
                            NomList.add(jsonObject.getString("nom"));
                            PrenomList.add(jsonObject.getString("prenom"));



                            s1.setRdvPrenomPraticien(PrenomList);
                            s1.setRdvNomPraticien(NomList);

                            test.add("N°" + jsonObjectId.getString("id") + "_ Le " + jsonObjectId.getString("date") +
                                    "\n Avec Mr./Mme." + jsonObject.getString("nom") + " " + jsonObject.getString("prenom"));

                          test2.add("Lieu : " + jsonObjectLieu.getString("libelle") + "\nAdresse : "
                                  + jsonObjectLieu.getString("adresse")+ " "+ jsonObjectLieu.getString("cp")
                                  + " " + jsonObjectLieu.getString("ville"));


//                            // get the listview
//                            expListView = (ExpandableListView) view.findViewById(R.id.explvRdv);
//
//                            // preparing list data
//                            prepareListData();
//
//                            listAdapter = new ExpandableListAdapter(getActivity(), listDataHeader, listDataChild);
//
//                            // setting list adapter
//                            expListView.setAdapter(listAdapter);


                            ExpandableListView elv = (ExpandableListView) view.findViewById(R.id.explvRdv);
                            elv.setAdapter(new SavedTabsListAdapter());
                        }






                    } else {

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



        return view;
    }
    /***********************************************************/
    /***********************************************************/
    /***********************************************************/
    /***********************************************************/
    /***********************************************************/
    /***********************************************************/

//    private void prepareListData() {


//        listDataHeader = test;
        // Adding child data
//        listDataHeader.add(test.toString());
//        listDataHeader.add("Now Showing");
//        listDataHeader.add("Coming Soon..");

//        // Adding child data
//        List<String> top250 = new ArrayList<String>();
//        top250.add("The Shawshank Redemption");
//        top250.add("The Godfather");
//        top250.add("The Godfather: Part II");
//        top250.add("Pulp Fiction");
//        top250.add("The Good, the Bad and the Ugly");
//        top250.add("The Dark Knight");
//        top250.add("12 Angry Men");
//
//        List<String> nowShowing = new ArrayList<String>();
//        nowShowing.add("The Conjuring");
//        nowShowing.add("Despicable Me 2");
//        nowShowing.add("Turbo");
//        nowShowing.add("Grown Ups 2");
//        nowShowing.add("Red 2");
//        nowShowing.add("The Wolverine");
//
//        List<String> comingSoon = new ArrayList<String>();
//        comingSoon.add("2 Guns");
//        comingSoon.add("The Smurfs 2");
//        comingSoon.add("The Spectacular Now");
//        comingSoon.add("The Canyons");
//        comingSoon.add("Europa Report");
//
//        listDataChild.put(listDataHeader.get(0), top250); // Header, Child data
//        listDataChild.put(listDataHeader.get(1), nowShowing);
//        listDataChild.put(listDataHeader.get(2), comingSoon);
//    }

    /***********************************************************/
    /***********************************************************/
    /***********************************************************/
    /***********************************************************/
    /***********************************************************/
    /***********************************************************/
    /***********************************************************/

//    public class ExpandableListAdapter extends BaseExpandableListAdapter {
//
//        private Context _context;
//        private List<String> _listDataHeader; // header titles
//        // child data in format of header title, child title
//        private HashMap<String, List<String>> _listDataChild;
//
//        public ExpandableListAdapter(Context context, List<String> listDataHeader,
//                                     HashMap<String, List<String>> listChildData) {
//            this._context = context;
//            this._listDataHeader = listDataHeader;
//            this._listDataChild = listChildData;
//        }
//
//        @Override
//        public Object getChild(int groupPosition, int childPosititon) {
//            return this._listDataChild.get(this._listDataHeader.get(groupPosition))
//                    .get(childPosititon);
//        }
//
//        @Override
//        public long getChildId(int groupPosition, int childPosition) {
//            return childPosition;
//        }
//
//        @Override
//        public View getChildView(int groupPosition, final int childPosition,
//                                 boolean isLastChild, View convertView, ViewGroup parent) {
//
//            final String childText = (String) getChild(groupPosition, childPosition);
//
//            if (convertView == null) {
//                LayoutInflater infalInflater = (LayoutInflater) this._context
//                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                convertView = infalInflater.inflate(R.layout.list_item, null);
//            }
//
//            TextView txtListChild = (TextView) convertView
//                    .findViewById(R.id.lblListItem);
//
//            txtListChild.setText(childText);
//            return convertView;
//        }
//
//        @Override
//        public int getChildrenCount(int groupPosition) {
//            return this._listDataChild.get(this._listDataHeader.get(groupPosition))
//                    .size();
//        }
//
//        @Override
//        public Object getGroup(int groupPosition) {
//            return this._listDataHeader.get(groupPosition);
//        }
//
//        @Override
//        public int getGroupCount() {
//            return this._listDataHeader.size();
//        }
//
//        @Override
//        public long getGroupId(int groupPosition) {
//            return groupPosition;
//        }
//
//        @Override
//        public View getGroupView(int groupPosition, boolean isExpanded,
//                                 View convertView, ViewGroup parent) {
//            String headerTitle = (String) getGroup(groupPosition);
//            if (convertView == null) {
//                LayoutInflater infalInflater = (LayoutInflater) this._context
//                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                convertView = infalInflater.inflate(R.layout.group_list, null);
//            }
//
//            TextView lblListHeader = (TextView) convertView
//                    .findViewById(R.id.lblListHeader);
//            lblListHeader.setTypeface(null, Typeface.BOLD);
//            lblListHeader.setText(headerTitle);
//
//            return convertView;
//        }
//
//        @Override
//        public boolean hasStableIds() {
//            return false;
//        }
//
//        @Override
//        public boolean isChildSelectable(int groupPosition, int childPosition) {
//            return true;
//        }
//    }



    public class SavedTabsListAdapter extends BaseExpandableListAdapter {

        private String[] groups = { "People Names", "Dog Names", "Cat Names", "Fish Names" };

        private String[][] children = {
                { "Arnold", "Barry", "Chuck", "David" },
                { "Ace", "Bandit", "Cha-Cha", "Deuce" },
                { "Fluffy", "Snuggles" },
                { "Goldy", "Bubbles" }
        };


        @Override
        public int getGroupCount() {
            return test.size();
        }

        @Override
        public int getChildrenCount(int i) {
            return test2.get(i).length();
        }

        @Override
        public Object getGroup(int i) {
            return test.get(i);
        }

        @Override
        public Object getChild(int i, int i1) {
            if (test2.get(i).charAt(i1) != 0) {
                descriptionRdv += test2.get(i).charAt(i1);
            }
            return descriptionRdv;
        }

        @Override
        public long getGroupId(int i) {
            return i;
        }

        @Override
        public long getChildId(int i, int i1) {
            return i1;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
            TextView textView = new TextView(RdvExpLv.this.getActivity());
            textView.setText(getGroup(i).toString());
            return textView;
        }

        @Override
        public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
            TextView textView = new TextView(RdvExpLv.this.getActivity());
            textView.setText(getChild(i, i1).toString());
            return textView;
        }

        @Override
        public boolean isChildSelectable(int i, int i1) {
            return true;
        }

    }

}
