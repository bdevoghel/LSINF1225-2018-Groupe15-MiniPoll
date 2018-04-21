package p.poll.model;

import java.util.ArrayList;
import java.sql.Date;

import p.poll.model.struc.LimitedNumber;
import p.poll.model.struc.ListNumber;

public class Poll {

    //Constantes
    public static final int MAX_POLL = 1000;

    //Structures utilisees pour l affectation des id
    private static ListNumber number = new ListNumber();
    private static LimitedNumber currentNumber = new LimitedNumber(MAX_POLL);

    //Attributs
    private String title;
    private String description;
    private Date deadLine;
    private char type;
    private User owner;
    private ArrayList<User> accessList;
    private int id;

    //Constructeurs
    public Poll(){
        this.setID();
    }
    public Poll(String title, String description, java.sql.Date deadline,
                Character type, User owner) {
        this.setTitle(title);
        this.setDescription(description);
        this.setDeadLine(deadline);
        this.setType(type);
        this.setOwner(owner);
        this.setID();
    }

    //Getteurs et setteurs
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

    public java.sql.Date getDeadLine() {
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

    public User getOwner()
    {
        return owner;
    }

    public void setOwner(User owner)
    {
        this.owner = owner;
    }

    public ArrayList<User> getAccessList(){
        return accessList;
    }

    public int getID()
    {
        return id;
    }

    //Addeurs et removers
    public int addAccess(User user)
    {
        if(!accessList.contains(user)){
            this.accessList.add(user);
            return 0;
        }
        else
        {
            return -1;
        }
    }

    public void removeAcces(User user)
    {
        this.accessList.remove(user);
    }

    //Redefinition de la methode equals
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

    //Methode d affectation de l id
    public void setID()
    {
        if(number.isEmpty()) {
            this.id = currentNumber.get();
            currentNumber.increment();
        }
        //Normalement on a meme pas besoin de else pcq la methode setId est seulement utilisee dans
        //le constructeur
        /*
        else
        {
            this.id = number.remove();
        }
        */
    }

    //Methodes
    public void chooseBySliding()
    {
        //a completer
    }
}

