package fr.falconteam.www.projetgsb;

/**
 * Created by Matias on 22/03/2017.
 */
public class SingletonUser {
    private static SingletonUser ourInstance = new SingletonUser();

    public static SingletonUser getInstance() { return ourInstance; }

    public String idUser;

    private SingletonUser() {

    }

    public String getUserId()
    {
        return idUser;
    }

    public void setUserId(String setter)
    {
        idUser = setter;
    }
}
