package p.poll.model;

public class Notification{
    private static int ID;
    private int id;
    private String text;
    private int state;
    public Notification(){
        this.id=ID;
        ID++;
    }
    //Ca ne marche pas comme ça mais en gros il va falloir trouver un moyen de reset les id parfois
    //sinon ça va monter jusque l'infini.
/*public void rmv() {
    ID--;
}*/
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
}