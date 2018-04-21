package be.lsinf1225.PoolApp;

import android.content.Context;

import java.sql.Date;
import java.text.DateFormat;

/**
 * Created by Nicolas on 21/04/2018.
 */

public class Menupoll {
    String title;
    String description;
    Date deadLine;
    Character type;
    User proprietaire;
    List<User> listeAcces;
    int id;


    public Menupoll(String title, String description, Date deadline,
                    Character type, User proprietaire, int id) {
        this.setTitle(title);
        this.setDescription(description);
        this.setDeadLine(deadline);
        this.setType(type);
        this.setProprietaire(proprietaire);
        this.setID(id);
    }

    public int addAcces(User utilisateur) {

    }

    public String getTitle() {
        return title;
    }

    public int setTitle(String title);

    public String getDescription() {
        return description;
    }

    public String setDescription(String Description);

    public Date getDeadLine() {
        return deadLine;
    }

    public int setDeadLine(Date date);

    public Character getType()
    {
        return type;
    }

    public int setType(Character Type);

    public User getProprietaire()
    {
        return proprietaire;
    }

    public int setProprietaire(User Proprietaire);

    public List<User> getListeAcces();

    public int getID()
    {
        return id;
    }

    public int setID(int Id);

    public int removeAcces(User utilisateur);

    public void chooseBySliding();
}