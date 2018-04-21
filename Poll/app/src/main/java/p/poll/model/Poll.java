package p.poll.model;

import java.util.ArrayList;
import java.util.Date;

public class Poll {
    private static int ID;
    private int id;
    private String title;
    private String description;
    private Date deadline;
    private char type;
    private User owner;
    private ArrayList<User> accessList;
    public Poll(){
        this.id=ID;
        ID++;
        accessList=new ArrayList();
    }
    //Ca ne marche pas comme ça mais en gros il va falloir trouver un moyen de reset les id parfois
    //sinon ça va monter jusque l'infini.
/*public void rmv() {
ID--;
}*/
    public int getId(){
        return this.id;
    }
    public boolean equals(Object o) {
        if (o != null) {
            if (o instanceof p.poll.model.Poll) {
                if(((p.poll.model.Poll) o).getId()==(this.getId())){
                    return true;
                }
            }
        }
        return false;
    }
    public void setTitle(String title){
        this.title=title;
    }
    public String getTitle(){
        return this.title;
    }
    public void setDescription(String description){
        this.description=description;
    }
    public String getDescription(){
        return this.description;
    }
    public void setDeadline(Date date){
        this.deadline=date;
    }
    public Date getDeadline(){
        return this.deadline;
    }
    /*
    @input type={c,s,q}
    */
    public void setType(char a){
        this.type=a;
    }
    public char getType(){
        return this.type;
    }
    public void setOwner(User u){
        this.owner=u;
    }
    public User getOwner(){
        return this.owner;
    }
    public void addAccess(User u){
        if(!accessList.contains(u)){
            this.accessList.add(u);
        }
    }
    public void rmvAccess(User u){
        this.accessList.remove(u);
    }
    public ArrayList<User> getAccessList(){
        return accessList;
    }
}
