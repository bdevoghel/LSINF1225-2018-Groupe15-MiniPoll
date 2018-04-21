package be.lsinf1225.PoolApp;

import android.content.Context;

import java.sql.Date;
import java.text.DateFormat;
import java.util.List;
import java.util.ArrayList;

import be.lsinf1225.PoolApp.database.Database;

/**
 * Created by Nicolas on 21/04/2018.
 */

public class Poll {
    String title;
    String description;
    Date deadLine;
    Character type;
    User proprietaire;
    ArrayList<User> listeAcces;
    int id;


    public Poll(String title, String description, Date deadline,
                    Character type, User proprietaire, int id) {
        this.setTitle(title);
        this.setDescription(description);
        this.setDeadLine(deadline);
        this.setType(type);
        this.setProprietaire(proprietaire);
        this.setID(id);
    }

    public int addAcces(User utilisateur)
    {
        if(!listeAcces.contains(utilisateur)){
            this.listeAcces.add(utilisateur);
            return 0;
        }
        else
        {
            return -1;
        }
    }

    public boolean equals(Object o) {
        if (o != null) {
            if (o instanceof Poll) {
                if(((Poll) o).getID()==(this.getID())){
                    return true;
                }
            }
        }
        return false;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Date getDeadLine() {
        return this.deadLine;
    }

    public void setDeadLine(Date date)
    {
        this.deadLine = date;
    }

    public Character getType()
    {
        return this.type;
    }

    public void setType(Character type)
    {
        this.type = type;
    }

    public User getProprietaire()
    {
        return proprietaire;
    }

    public void setProprietaire(User proprietaire)
    {
        this.proprietaire = proprietaire;
    }

    public ArrayList<User> getAccessList(){
        return listeAcces;
    }

    public int getID()
    {
        return id;
    }

    public void setID(int id)
    {
        this.id = id;
    }

    public void removeAcces(User utilisateur)
    {
        this.listeAcces.remove(utilisateur);
    }

    public void chooseBySliding()
    {

    }
}