package fr.falconteam.www.projetgsb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class AffichageCompteRendu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affichage_compte_rendu);


        TextView id,date,bilan,coefconf,coefnoto,coefpres,motiflibelle,motifdescr;

        id = (TextView) findViewById(R.id.tvRdvId);
        date = (TextView) findViewById(R.id.tvRdvDate);
        bilan = (TextView) findViewById(R.id.tvRdvLieu);
        coefconf = (TextView) findViewById(R.id.tvRdvAdresse);
        coefnoto = (TextView) findViewById(R.id.tvRdvCP);
        coefpres = (TextView) findViewById(R.id.tvRdvVille);
        motiflibelle = (TextView) findViewById(R.id.praticien);
        motifdescr = (TextView) findViewById(R.id.tvCptDescription);

        SingleTonCptRendu s2 = SingleTonCptRendu.getInstance();

        id.setText("N° Compte rendu : "+ s2.getCptId());
        date.setText("Date : "+ s2.getCptDate());
        bilan.setText("Bilan : "+ s2.getCptbilan());
        coefconf.setText("Coef Confiance : "+ s2.getCptCoefConfiance());
        coefnoto.setText("Coef Notoriété : "+ s2.getCptCoefNoto());
        coefpres.setText("Coef Prescription : "+ s2.getCptCoefPrescription());
        motiflibelle.setText("Motif : "+ s2.getMotifLibelle());
        motifdescr.setText(s2.getMotifDescription());




    }
}
