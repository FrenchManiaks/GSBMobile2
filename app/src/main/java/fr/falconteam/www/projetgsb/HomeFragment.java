package fr.falconteam.www.projetgsb;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment  {


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_home, container, false);

        TabHost host = (TabHost)view.findViewById(R.id.tabHost);
        host.setup();

        //Tab 1
        TabHost.TabSpec spec = host.newTabSpec("Tab One");
        spec.setContent(R.id.tab1);
        spec.setIndicator("Activités");
        host.addTab(spec);

        //Tab 2
        spec = host.newTabSpec("Tab Two");
        spec.setContent(R.id.tab2);
        spec.setIndicator("P. Auto");
        host.addTab(spec);

        //Tab 3
        spec = host.newTabSpec("Tab Three");
        spec.setContent(R.id.tab3);
        spec.setIndicator("RDV Perso");
        host.addTab(spec);


        //ListView

        //String URL ="http://10.0.2.2/android/players.php";

        /*****************************ListView Activités*****************************/



        String[] menuItems = {"Do something",
                                "Do another thing",
                                "Fuck this"};

        ListView listView = (ListView) view.findViewById(R.id.mainLv);

        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                menuItems
        );

        listView.setAdapter(listViewAdapter);



        /*****************************ListView Parc Auto*****************************/

        String[] menuItems2 = {"Do something2",
                "Do another thing2",
                "Fuck this2"};

        ListView listView2 = (ListView) view.findViewById(R.id.mainLv2);

        ArrayAdapter<String> listViewAdapter2 = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                menuItems2
        );

        listView2.setAdapter(listViewAdapter2);



        /*****************************ListView RDV Perso*****************************/

        TextView newtext = (TextView) view.findViewById(R.id.UserID);
        String IDUtilisateur = getArguments().getString("UserId");
        newtext.setText(IDUtilisateur);

        return view;
        }


    // Inflate the layout for this fragment
       //return inflater.inflate(R.layout.fragment_home, container, false);
    }


//}