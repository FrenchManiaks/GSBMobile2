package fr.falconteam.www.projetgsb;

/**
 * Created by Matias on 28/04/2017.
 */

class SingleTonCptRendu {
    private static SingleTonCptRendu ourInstance = new SingleTonCptRendu();

    static SingleTonCptRendu getInstance() {
        return ourInstance;
    }

    String CptDate;
    String CptId;
    String Cptbilan;
    String CptCoefConfiance;
    String CptCoefNoto;
    String CptCoefPrescription;
    String CptMotif;
    String MotifLibelle;
    String MotifDescription;
    String CptEchantillon;
    String EchantillonId;
    String EchantillonQte;
    String CptProduit;
    String ProduitId;


    private SingleTonCptRendu() {
    }

    public void setCptId (String setter){
        CptId = setter;
    }

    public String getCptId(){

        return CptId;
    }

    public void setCptDate (String setter){
        CptDate = setter;
    }

    public String getCptDate(){

        return CptDate;
    }

    public void setCptbilan (String setter){
        Cptbilan = setter;
    }

    public String getCptbilan(){

        return Cptbilan;
    }

    public void setCptCoefConfiance (String setter){
        CptCoefConfiance = setter;
    }

    public String getCptCoefConfiance(){

        return CptCoefConfiance;
    }

    public void setCptCoefNoto (String setter){
        CptCoefNoto = setter;
    }

    public String getCptCoefNoto(){

        return CptCoefNoto;
    }

    public void setCptCoefPrescription (String setter){
        CptCoefPrescription = setter;
    }

    public String getCptCoefPrescription(){

        return CptCoefPrescription;
    }

    public void setCptMotif (String setter){
        CptMotif = setter;
    }

    public String getCptMotif(){

        return CptMotif;
    }

    public void setMotifLibelle (String setter){
        MotifLibelle = setter;
    }

    public String getMotifLibelle(){

        return MotifLibelle;
    }

    public void setMotifDescription (String setter){
        MotifDescription = setter;
    }

    public String getMotifDescription(){

        return MotifDescription;
    }

    public void setCptEchantillon (String setter){
        CptEchantillon = setter;
    }

    public String getCptEchantillon(){

        return CptEchantillon;


    }

    public void setEchantillonId (String setter){
        EchantillonId = setter;
    }

    public String getEchantillonId(){

        return EchantillonId;


    }

    public void setEchantillonQte (String setter){
        EchantillonQte = setter;
    }

    public String getEchantillonQte(){

        return EchantillonQte;


    }
}
