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
    static ListNumber number = new ListNumber();
    static int currentnumber;
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
        if(number.isEmpty()) {
            this.id = currentnumber;
            currentnumber ++;
        }
        else
        {
            this.id = number.remove();
        }
    }

    public void removeAcces(User utilisateur)
    {
        this.listeAcces.remove(utilisateur);
    }

    public void chooseBySliding()
    {

    }
}

class ListNumber
{
    Node first;
    public ListNumber()
    {
        first = null;
    }

    int remove()
    {
        if(first == null)
        {
            return -1;
        }
        else
        {
            int value = first.number;
            first = first.next;
            return value;
        }
    }

    boolean isEmpty()
    {
        if(first == null) {
            return true;
        }
        else
        {
            return false;
        }
    }

    void insert(int number)
    {
        Node current = first;
        if(first == null)
        {
            first = new Node(number);
        }
        else
        {
            if(first.next == null)
            {
                if(first.number < number)
                {
                    Node ajout = new Node(number);
                    first.next = ajout;
                    return;
                }
                else
                {
                    Node ajout = new Node(number);
                    ajout.next = first;
                    first = ajout;
                    return;
                }
            }
            if(first.number > number)
            {
                Node ajout = new Node(number);
                ajout.next = first;
                first = ajout;
            }
            else
            {
                while(current.next != null)
                {
                    if(current.next.number < number)
                    {
                        current = current.next;
                    }
                    else
                    {
                        Node ajout = new Node(number);
                        ajout.next = current.next;
                        current.next = ajout;
                        return;
                    }
                }
                Node ajout = new Node(number);
                current.next = ajout;
            }
        }
    }
}


class Node
{
    int number;
    Node next;
    public Node(int num)
    {
        this.number = num;
        next = null;
    }
}
