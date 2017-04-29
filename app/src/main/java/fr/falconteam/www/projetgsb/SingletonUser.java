package fr.falconteam.www.projetgsb;

/**
 * Created by Matias on 22/03/2017.
 */
public class SingletonUser {
    private static SingletonUser ourInstance = new SingletonUser();

    public static SingletonUser getInstance() { return ourInstance; }

    public String idUser;
    public String idRdv;
    public String RdvDate;
    public String Rdvinfo;
    public String RdvNomPraticien;
    public String RdvPrenomPraticien;
    public String RdvPraticien;
    public String RdvPTelFixe;
    public String RdvPTelPortable;
    public String RdvLieu;
    public String RdvLieuLibelle;
    public String RdvLieuAdresse;
    public String RdvLieuCP;
    public String RdvLieuVille;
    public String RdvPMail;
    public String AdresseIP = "http://192.168.43.224/apigsb/";


    private SingletonUser() {

    }

    public String getAdresseIP(){
        return AdresseIP;
    }

    public String getUserId()
    {
        return idUser;
    }

    public void setUserId(String setter)
    {
        idUser = setter;
    }

    public void setRdvId(String setter)
    {
        idRdv = setter;
    }

    public String getRdvId()
    {
        return idRdv;
    }

    public void setRdvDate(String setter){
        RdvDate = setter;

    }

    public String getRdvDate(){
        return RdvDate;
    }

    public void setRdvInfo(String setter){
        Rdvinfo = setter;

    }

    public String gerRdvInfo(){
        return Rdvinfo;
    }

    public void setRdvNomPraticien(String setter){
        RdvNomPraticien = setter;

    }

    public String getRdvNomPraticien(){
        return RdvNomPraticien;
    }

    public void setRdvPrenomPraticien(String setter){
        RdvPrenomPraticien = setter;

    }

    public String getRdvPrenomPraticien(){
        return RdvPrenomPraticien;
    }

    public void setRdvPTelFixe(String setter){
        RdvPTelFixe = setter;

    }

    public String getRdvPTelFixe(){
        return RdvPTelFixe;
    }

    public void setRdvPTelPortable(String setter){
        RdvPTelPortable = setter;

    }

    public String getRdvPTelPortable(){
        return RdvPTelPortable;
    }

    public void setRdvPMail(String setter){
        RdvPMail = setter;

    }

    public String GetRdvPMail(){
        return RdvPMail;
    }

    public void setRdvPraticien(String setter){
        RdvPraticien = setter;

    }

    public String getRdvPraticien(){
        return RdvPraticien;
    }

    public void setRdvLieu(String setter){
        RdvLieu = setter;

    }

    public String getRdvLieu(){
        return RdvLieu;
    }

    public void setRdvLieuLibelle(String setter){
        RdvLieuLibelle = setter;

    }

    public String getRdvLieuLibelle(){
        return RdvLieuLibelle;
    }

    public void setRdvLieuAdresse(String setter){
        RdvLieuAdresse = setter;

    }

    public String getRdvLieuAdresse(){
        return RdvLieuAdresse;
    }

    public void setRdvLieuCP(String setter){
        RdvLieuCP = setter;

    }

    public String getRdvLieuCP(){
        return RdvLieuCP;
    }

    public void setRdvLieuVille(String setter){
        RdvLieuVille = setter;
    }

    public String getRdvLieuVille(){
        return RdvLieuVille;
    }

}
