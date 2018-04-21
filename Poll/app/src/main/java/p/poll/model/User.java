package p.poll.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Picture;

import p.poll.MySQLiteHelper;

public class User {

    //Map des instances des Users
    private static Map<String,User> userMap = new HashMap<>();

    //Noms des colonnes de la database
    private static final String DB_COLUMN_USERNAME = "u_username";
    private static final String DB_COLUMN_NAME = "u_name";
    private static final String DB_COLUMN_PASSWORD = "u_password";
    private static final String DB_TABLE = "users";

    //Attributs de la classe
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

    //Constructeurs
    public User(){
        friendList=new ArrayList();
        notificationList=new ArrayList();
        pollList=new ArrayList();
    }
    public User(String uUsername,String uFName,String uLName, String uPassword){
        username=uUsername;
        firstName=uFName;
        lastName=uLName;
        password=uPassword;
        friendList=new ArrayList();
        notificationList=new ArrayList();
        pollList=new ArrayList();
        userMap.put(username,this);
    }

    //Getteurs et setteurs
    public void setUsername(String name){
        this.username=name;
        if(!userMap.containsKey(username)){
            userMap.put(username,this);
        }
    }
    public String getUsername(){
        return username;
    }
    public void setFirstName(String firstName){
        this.firstName=firstName;
    }
    public String getFirstName(){
        return firstName;
    }
    public void setLastName(String lastName){
        this.lastName=lastName;
    }
    public String getLastName(){
        return lastName;
    }
    public void setMailAdress(String mailAdress){
        this.mailAdress=mailAdress;
    }
    public String getMailAdress(){
        return mailAdress;
    }
    public void setPassword(String password){
        this.password=password;
    }
    public String getPassword(){
        return password;
    }
    public void setBestFriend(User bestFriend){
        this.bestFriend=bestFriend;
    }
    public User getBestFriend(){
        return bestFriend;
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

    //Addeurs et removeurs
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

    //Redefinition de la methode equals
    public boolean equals(Object o) {
        if (o instanceof User) {
            if(((User) o).getUsername().equals(this.getUsername())){
                return true;
            }
        }
        return false;
    }
    /**
     * Fournit la liste des utilisateurs.
     */
    public static ArrayList<User> getUsers() {
        // Récupération du  SQLiteHelper et de la base de données.
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();

        // Colonnes à récupérer
        String[] colonnes = {DB_COLUMN_USERNAME, DB_COLUMN_NAME, DB_COLUMN_PASSWORD};

        // Requête de selection (SELECT)
        Cursor cursor = db.query(DB_TABLE, colonnes, null, null, null, null, null);

        // Placement du curseur sur la première ligne.
        cursor.moveToFirst();

        // Initialisation la liste des utilisateurs.
        ArrayList<User> users = new ArrayList<>();

        // Tant qu'il y a des lignes.
        while (!cursor.isAfterLast()) {
            // Récupération des informations de l'utilisateur pour chaque ligne.
            String uUsername = cursor.getString(0);
            String uFName = cursor.getString(1);
            String uLName = cursor.getString(2);
            String uPassword = cursor.getString(3);

            // Vérification pour savoir s'il y a déjà une instance de cet utilisateur.
            User user = User.userMap.get(uUsername);
            if (user == null) {
                // Si pas encore d'instance, création d'une nouvelle instance.
                user= new User(uUsername,uFName,uLName,uPassword);
            }

            // Ajout de l'utilisateur à la liste.
            users.add(user);

            // Passe à la ligne suivante.
            cursor.moveToNext();
        }

        // Fermeture du curseur et de la base de données.
        cursor.close();
        db.close();

        return users;
    }
}
