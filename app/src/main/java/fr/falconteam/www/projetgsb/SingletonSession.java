package fr.falconteam.www.projetgsb;

/**
 * Created by Matias on 13/02/2017.
 */
public class SingletonSession {
    private static SingletonSession ourInstance = new SingletonSession();

    private String valeur;

    public static SingletonSession getInstance() {
        return ourInstance;
    }

    private SingletonSession() {
        valeur = "default";
    }

    public String getString(){
        return this.valeur;
    }

    public void setString(String value){
        valeur = value;
    }
}
