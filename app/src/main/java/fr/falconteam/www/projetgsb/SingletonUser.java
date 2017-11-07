package fr.falconteam.www.projetgsb;

import java.util.ArrayList;

/**
 * Created by Matias on 22/03/2017.
 */
public class SingletonUser {
    private static SingletonUser ourInstance = new SingletonUser();

    public static SingletonUser getInstance() { return ourInstance; }

    public String idUser;
    public ArrayList idRdv;
    public ArrayList RdvDate;
    public String Rdvinfo;
    public ArrayList RdvNomPraticien;
    public ArrayList RdvPrenomPraticien;
    public ArrayList RdvPraticien;
    public String RdvPTelFixe;
    public String RdvPTelPortable;
    public String RdvLieu;
    public String RdvLieuLibelle;
    public String RdvLieuAdresse;
    public String RdvLieuCP;
    public String RdvLieuVille;
    public String RdvPMail;
    public String AdresseIP = "http://10.10.6.39/api/";
    public String result;
    public String resultDate;


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

    public void setRdvId(ArrayList setter)
    {
        idRdv = setter;
    }

    public ArrayList getRdvId()
    {
        return idRdv;
    }

    public void setRdvDate(ArrayList setter){
        RdvDate = setter;

    }

    public ArrayList getRdvDate(){
        return RdvDate;
    }

    public void setRdvInfo(String setter){
        Rdvinfo = setter;

    }

    public String getRdvInfo(){
        return Rdvinfo;
    }

    public void setRdvNomPraticien(ArrayList setter){
        RdvNomPraticien = setter;

    }

    public ArrayList getRdvNomPraticien(){
        return RdvNomPraticien;
    }

    public void setRdvPrenomPraticien(ArrayList setter){
        RdvPrenomPraticien = setter;

    }

    public ArrayList getRdvPrenomPraticien(){
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

    public void setRdvPraticien(ArrayList setter){
        RdvPraticien = setter;

    }

    public ArrayList getRdvPraticien(){
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

    public void setResult(String setter){
        result = setter;
    }

    public String getResult() {
        return result;
    }

    public void setResultDate(String setter){
        resultDate = setter;
    }

    public String getResultDate() {
        return resultDate;
    }

}
