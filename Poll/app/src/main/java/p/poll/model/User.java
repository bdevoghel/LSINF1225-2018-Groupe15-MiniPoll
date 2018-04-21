package p.poll.model;

import java.util.ArrayList;
import java.util.Date;
import android.graphics.Picture;

public class User {
    private String username;
    private String lastName;
    private String firstName;
    private String mailAdress;
    private String password;
    private Picture profilePic;
    private User bestFriend;
    private ArrayList<Notification> notificationList;
    private ArrayList<Poll> pollList;
    private ArrayList<User> friendList;
    public User(){
        friendList=new ArrayList();
        notificationList=new ArrayList();
        pollList=new ArrayList();
    }
    public void setUsername(String name){
        this.username=name;
    }
    public String getUsername(){
        return username;
    }
    public void setFirstName(String name){
        this.firstName=name;
    }
    public String getFirstName(){
        return firstName;
    }
    public void setLastName(String name){
        this.lastName=name;
    }
    public String getLastName(){
        return lastName;
    }
    public void setMailAdress(String name){
        this.mailAdress=name;
    }
    public String getMailAdress(){
        return mailAdress;
    }
    public void setPassword(String name){
        this.password=name;
    }
    public String getPassword(){
        return password;
    }
    public void setBestFriend(User bff){
        this.bestFriend=bff;
    }
    public User getBestFriend(){
        return bestFriend;
    }
    public void addFriend(User friend) {
        if(!friendList.contains(friend))
        {
            friendList.add(friend);
        }
    }
    public void rmvFriend(User friend) {
        this.friendList.remove(friend);
    }
    public void addPoll(Poll p) {
        if(!pollList.contains(p))
        {
            this.pollList.add(p);
        }
    }
    public void rmvPoll(Poll p) {
        this.pollList.remove(p);
    }
    public void addNotification(Notification n) {
        if(!this.notificationList.contains(n)) {
            notificationList.add(n);
        }
    }
    public void rmvNotification(Notification n) {
        this.notificationList.remove(n);
    }
    public void setProfilePic(Picture p){
        this.profilePic=p;
    }
    public ArrayList<User> getFriendList(){
        return friendList;
    }
    public ArrayList<Poll> getPollList() {
        return pollList;
    }
    public ArrayList<Notification> getNotificationList() {
        return notificationList;
    }
    //Je ne sais pas pourquoi ça marche pas
/*public void setProfilePic(String filename) {
    this.profilePic=new Picture(filename);
}*/
    public Picture getProfilePic() {
        return this.profilePic;
    }
    public boolean equals(Object o) {
        if (o instanceof User) {
            if(((User) o).getUsername().equals(this.getUsername())){
                return true;
            }
        }
        return false;
    }
}
