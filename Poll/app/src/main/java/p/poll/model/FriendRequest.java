package p.poll.model;

/**
 * Created by Vahid Beyraghi on 21-04-18.
 */

public class FriendRequest extends p.poll.model.Notification {

    //Attributs
    private String friendUsername;

    //Constructeurs
    public FriendRequest() {
        super();
    }
    public FriendRequest(String text, int state, String username){
        super(text, state);
        this.friendUsername=username;
    }

    //Getteurs et setteurs
    public String getFriendUsername(){
        return friendUsername;
    }
    public void setFriendUsername(String username){
        friendUsername=username;
    }
}
