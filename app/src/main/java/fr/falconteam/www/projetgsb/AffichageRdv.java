package fr.falconteam.www.projetgsb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class AffichageRdv extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affichage_rdv);



        TextView id,date,bilan,coefconf,coefnoto,coefpres,motifdescr;

        id = (TextView) findViewById(R.id.tvRdvId);
        date = (TextView) findViewById(R.id.tvRdvDate);
        bilan = (TextView) findViewById(R.id.tvRdvLieu);
        coefconf = (TextView) findViewById(R.id.tvRdvAdresse);
        coefnoto = (TextView) findViewById(R.id.tvRdvCP);
        coefpres = (TextView) findViewById(R.id.tvRdvVille);
        motifdescr = (TextView) findViewById(R.id.tvRdvDesc);


        SingletonUser s1 = SingletonUser.getInstance();

        id.setText("N° RDV: "+ s1.getResult());
        date.setText("Date : "+ s1.getResultDate());
        bilan.setText("Lieu : "+ s1.getRdvLieuLibelle());
        coefconf.setText("Adresse : "+ s1.getRdvLieuAdresse());
        coefnoto.setText("Code Postal : "+ s1.getRdvLieuCP());
        coefpres.setText("Ville : "+ s1.getRdvLieuVille());
        motifdescr.setText(s1.getRdvInfo());

    }
}
