package fr.falconteam.www.projetgsb;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class CptRenduFragment extends Fragment {

    String URL= "http://192.168.43.224:80/apigsb/getUtilisateurById.php";



    public CptRenduFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cpt_rendu, container, false);
    }

}
