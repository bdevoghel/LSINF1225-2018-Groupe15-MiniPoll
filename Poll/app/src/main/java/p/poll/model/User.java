package p.poll.model;

import java.util.ArrayList;
import java.util.Collection;
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
    private static HashMap<String,User> userMap = new HashMap<>();

    //Noms des colonnes de la database
    private static final String DB_COLUMN_USERNAME = "identifiant";
    private static final String DB_COLUMN_FNAME = "prenom";
    private static final String DB_COLUMN_LNAME = "nom";
    private static final String DB_COLUMN_PASSWORD = "mdp";
    private static final String DB_TABLE = "profil";

    //Attributs de la classe
    private String username;
    private String lastName;
    private String firstName;
    private String mailAdress;
    private String password;
    private Bitmap profilePic;
    private User bestFriend;
    private ArrayList<Notification> notificationList;
    private ArrayList<Poll> pollList;
    private ArrayList<User> friendList;

    //Constructeurs
    public User(){
        friendList=new ArrayList<>();
        notificationList=new ArrayList<>();
        pollList=new ArrayList<>();
    }

    /** Ce constructeur ne peut qu'être utilisé pour faire des comparations */
    public User(String username){
        this.username=username;
    }

    public User(String uUsername,String uFName,String uLName, String uPassword){
        username=uUsername;
        firstName=uFName;
        lastName=uLName;
        password=uPassword;
        friendList=new ArrayList<>();
        notificationList=new ArrayList<>();
        pollList=new ArrayList<>();
        userMap.put(uUsername,this);
    }
    public User(String uUsername,String uFName,String uLName, String uPassword, String profilePicFile){
        username=uUsername;
        firstName=uFName;
        lastName=uLName;
        password=uPassword;
        friendList=new ArrayList<>();
        notificationList=new ArrayList<>();
        pollList=new ArrayList<>();
        userMap.put(uUsername,this);
        this.setProfilePic(upLoadPicture(profilePicFile));
    }
    public User(String uUsername,String uFName,String uLName, String uPassword, Bitmap profilePic){
        username=uUsername;
        firstName=uFName;
        lastName=uLName;
        password=uPassword;
        friendList=new ArrayList<>();
        notificationList=new ArrayList<>();
        pollList=new ArrayList<>();
        userMap.put(uUsername,this);
        this.setProfilePic((profilePic));
    }

    //Getteurs et setteurs
    public void setUsername(String name){
        this.username=name;
        if(!userMap.containsKey((username))){
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
    public ArrayList<User> getFriendList(){
        return friendList;
    }
    public ArrayList<Poll> getPollList() {
        return pollList;
    }
    public ArrayList<Notification> getNotificationList() {
        return notificationList;
    }
    public Bitmap getProfilePic() {
        return this.profilePic;
    }

    //Addeurs et removeurs
    public void addFriend(User friend) {
        if(!friendList.contains(friend))
        {
            friendList.add(friend);
        }
    }
    public void addFriend(ArrayList<User> users){
        friendList.addAll(users);
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
        if(this.notificationList.contains(n)) {
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

    public static String[] getFName(ArrayList<User> userList){
        String[] infoList=new String[userList.size()];
        for(int i=0;i<userList.size();i++)
        {
            infoList[i]=(userList.get(i)).getFirstName();
        }
        return infoList;
    }

    public static String[] getLName(ArrayList<User> userList){
        String[] infoList=new String[userList.size()];
        for(int i=0;i<userList.size();i++)
        {
            infoList[i]=(userList.get(i)).getLastName();
        }
        return infoList;
    }

    public static String[] getUsername(ArrayList<User> userList){
        String[] infoList=new String[userList.size()];
        for(int i=0;i<userList.size();i++)
        {
            infoList[i]=(userList.get(i)).getUsername();
        }
        return infoList;
    }

    public static String[] getEmail(ArrayList<User> userList){
        String[] infoList=new String[userList.size()];
        for(int i=0;i<userList.size();i++)
        {
            infoList[i]=(userList.get(i)).getMailAdress();
        }
        return infoList;
    }

    public static Bitmap[] getPicture(ArrayList<User> userList){
        Bitmap[] infoList=new Bitmap[userList.size()];
        for(int i=0;i<userList.size();i++)
        {
            infoList[i]=(userList.get(i)).getProfilePic();
        }
        return infoList;
    }

    /**
     * Fournit la liste des utilisateurs.
     */

    public static ArrayList<User> getUsers() {
        //ArrayList<User> michel = new ArrayList<>();
        //michel.add(new User("michel","michel","dupond","12345"));
        //return michel;

        // Récupération du  SQLiteHelper et de la base de données.

        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        //ArrayList<User> users = new ArrayList<>();

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

    public static HashMap<String,User> toHashMap(ArrayList<User> users){
        HashMap<String,User> hash = new HashMap<>();
        for(int i=0;i<users.size();i++){
            hash.put((users.get(i)).getUsername(),users.get(i));
        }
        return hash;
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
