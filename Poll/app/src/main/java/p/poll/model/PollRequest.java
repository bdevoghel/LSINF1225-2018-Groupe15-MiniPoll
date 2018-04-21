package p.poll.model;

/**
 * Created by Vahid Beyraghi on 21-04-18.
 */

public class PollRequest extends Notification {
    //Attributs
    private int id;

    //Constructeurs
    public PollRequest() {
        super();
    }
    public PollRequest(String text, int state, int id){
        super(text, state);
        this.id=id;
    }

    //Getteurs et setteurs
    public int getId(){
        return id;
    }
    public void setFriendUsername(int id){
        this.id=id;
    }
}
