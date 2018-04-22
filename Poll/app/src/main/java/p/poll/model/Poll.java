package p.poll.model;

import java.util.ArrayList;
import java.sql.Date;
import java.util.HashMap;

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
    private HashMap<String,User> accessList;
    private int id;

    //Constructeurs
    public Poll(){
        this.setId();
        accessList=new HashMap<>();
    }
    public Poll(String title, String description, java.sql.Date deadline,
                char type, User owner) {
        this.setTitle(title);
        this.setDescription(description);
        this.setDeadLine(deadline);
        this.setType(type);
        this.setOwner(owner);
        this.setId();
        accessList=new HashMap<>();
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

    public char getType()
    {
        return this.type;
    }

    public void setType(char type)
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

    public HashMap<String,User> getAccessList(){
        return accessList;
    }

    public int getId()
    {
        return id;
    }

    //Addeurs et removers
    public void addAccess(User user)
    {
        if(!accessList.containsKey(user.getUsername())){
            this.accessList.put(user.getUsername(),user);
        }
    }
    public void addAccess(HashMap<String,User> users){
        accessList.putAll(users);
    }

    public void removeAcces(User user)
    {
        this.accessList.remove(user.getUsername());
    }

    //Redefinition de la methode equals
    public boolean equals(Object o) {
        if (o != null) {
            if (o instanceof Poll) {
                if(((Poll) o).getId()==(this.getId())){
                    return true;
                }
            }
        }
        return false;
    }

    //Methode d affectation de l id
    public void setId()
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

