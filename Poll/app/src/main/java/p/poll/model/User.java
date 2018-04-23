package p.poll.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;
import android.util.SparseArray;

import p.poll.MySQLiteHelper;

public class User {

    //Map des instances des Users
    private static Map<String,User> userMap = new HashMap<>();

    //Noms des colonnes de la database
    private static final String DB_COLUMN_USERNAME = "u_username";
    private static final String DB_COLUMN_FNAME = "u_fname";
    private static final String DB_COLUMN_LNAME = "u_lname";
    private static final String DB_COLUMN_PASSWORD = "u_password";
    private static final String DB_TABLE = "users";

    //Attributs de la classe
    private String username;
    private String lastName;
    private String firstName;
    private String mailAdress;
    private String password;
    private Bitmap profilePic;
    private User bestFriend;
    private SparseArray<Notification> notificationList;
    private SparseArray<Poll> pollList;
    private HashMap<String,User> friendList;

    //Constructeurs
    public User(){
        friendList=new HashMap<>();
        notificationList=new SparseArray<>();
        pollList=new SparseArray<>();
    }
    public User(String uUsername,String uFName,String uLName, String uPassword){
        username=uUsername;
        firstName=uFName;
        lastName=uLName;
        password=uPassword;
        friendList=new HashMap<>();
        notificationList=new SparseArray<>();
        pollList=new SparseArray<>();
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
    public void setProfilePic(Bitmap p){
        this.profilePic=p;
    }
    public HashMap<String,User> getFriendList(){
        return friendList;
    }
    public SparseArray<Poll> getPollList() {
        return pollList;
    }
    public SparseArray<Notification> getNotificationList() {
        return notificationList;
    }
    public Bitmap getProfilePic() {
        return this.profilePic;
    }

    //Addeurs et removeurs
    public void addFriend(User friend) {
        if(!friendList.containsKey(friend.getUsername()))
        {
            friendList.put(friend.getUsername(),friend);
        }
    }
    public void addFriend(HashMap<String,User> users){
        friendList.putAll(users);
    }
    public void rmvFriend(User friend) {
        this.friendList.remove(friend.getUsername());
    }
    public void addPoll(Poll p) {
        if(pollList.indexOfKey(p.getId())<0)
        {
            this.pollList.append(p.getId(),p);
        }
    }
    public void rmvPoll(Poll p) {
        this.pollList.remove(p.getId());
    }
    public void addNotification(Notification n) {
        if(this.notificationList.indexOfKey(n.getId())<0) {
            notificationList.append(n.getId(),n);
        }
    }
    public void rmvNotification(Notification n) {
        this.notificationList.remove(n.getId());
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
        String[] colonnes = {DB_COLUMN_USERNAME, DB_COLUMN_FNAME, DB_COLUMN_LNAME, DB_COLUMN_PASSWORD};

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
            User user = userMap.get(uUsername);
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
    /**
     * Transforme un fichier en Bitmap
     */
    public Bitmap upLoadPicture(String filePath){
        Bitmap bitmap = BitmapFactory.decodeFile(filePath);
        this.setProfilePic(bitmap);
        return bitmap;
    }
}
