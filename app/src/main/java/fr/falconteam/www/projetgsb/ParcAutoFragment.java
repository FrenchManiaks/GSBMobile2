package fr.falconteam.www.projetgsb;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class ParcAutoFragment extends Fragment {

    public ParcAutoFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_parc_auto, container, false);


        String[] menuItems = {"Parc auto 1",
                "Affiche quelque chose d'autre",
                "Bon, maintenant il faut afficher les info de la BDD"};

        ListView listView = (ListView) view.findViewById(R.id.lvParcAuto);

        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                menuItems
        );

        listView.setAdapter(listViewAdapter);


        return view;
    }

}
