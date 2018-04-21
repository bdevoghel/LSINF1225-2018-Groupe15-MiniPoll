package p.poll.model;

import p.poll.model.struc.LimitedNumber;
import p.poll.model.struc.ListNumber;

public class Notification{

    //Constantes
    private static final int MAX_POLL = Poll.MAX_POLL;

    //Structures utilisees pour l affectation des id
    private static ListNumber number = new ListNumber();
    private static LimitedNumber currentNumber = new LimitedNumber(MAX_POLL);

    //Attributs
    private int id;
    private String text;
    private int state;

    //Constructeurs
    public Notification(){
        this.setID();
    }
    public Notification(String text, int state){
        this.setID();
        this.text=text;
        this.state=state;
    }

    //Redefinition de la methode equals
    public boolean equals(Object o) {
        if (o != null) {
            if (o instanceof Notification) {
                if (((Notification) o).getId() == (this.getId())) {
                    return true;
                }
            }
        }
        return false;
    }

    //Getteurs et setteurs
    public int getId(){
        return this.id;
    }
    public int getState(){
        return this.state;
    }
    public void setState(int i){
        this.state=i;
    }
    public String getText(){
        return this.text;
    }
    public void setText(String text){
        this.text=text;
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
}