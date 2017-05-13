package fr.falconteam.www.projetgsb;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by Matias on 04/05/2017.
 */

class SingletonParcAuto {
    private static final SingletonParcAuto ourInstance = new SingletonParcAuto();

    static SingletonParcAuto getInstance() {
        return ourInstance;
    }
    ArrayList<String> TypeVehicule;
    ArrayList <String> Dispo;
    ArrayList<String> Type;

    private SingletonParcAuto() {
    }

    public void setTypeVehicule(ArrayList setter){
        TypeVehicule = setter;
    }

    public ArrayList getTypeVehicule(){
        return TypeVehicule;
    }

    public void setDispo(ArrayList setter){
        Dispo = setter;
    }

    public ArrayList getDispo(){
        return Dispo;
    }

    public void setType(ArrayList setter){
        Type = setter;
    }

    public ArrayList getType(){
        return Type;
    }
}
