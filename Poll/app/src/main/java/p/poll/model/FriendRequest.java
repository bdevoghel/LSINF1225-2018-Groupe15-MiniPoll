package p.poll.model;

/**
 * Created by Vahid Beyraghi on 21-04-18.
 */

public class FriendRequest extends p.poll.model.Notification {

    //Attributs
    private User friend;

    //Constructeurs
    public FriendRequest() {
        super();
    }
    public FriendRequest(String text, int state, User user){
        super(text, state);
        this.friend=user;
    }

    //Getteurs et setteurs
    public User getUser(){
        return friend;
    }
    public void setUser(User user){
        friend=user;
    }
}
