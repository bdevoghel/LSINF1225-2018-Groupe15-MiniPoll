package p.poll.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.ImageView;
import android.util.SparseArray;
import android.support.v4.content.FileProvider;
import p.poll.MySQLiteHelper;

public class User {

    //Map des instances des Users
    private static HashMap<String,User> userMap = new HashMap<>();
    public static User loggedUser;
    //Noms des colonnes de la database
    private static final String DB_COLUMN_USERNAME = "Username";
    private static final String DB_COLUMN_FNAME = "firstname";
    private static final String DB_COLUMN_LNAME = "name";
    private static final String DB_COLUMN_EMAIL = "mail";
    private static final String DB_COLUMN_PASSWORD = "password";
    private static final String DB_TABLE = "User";
    private static final String DB_COLUMN_PIC = "photo";
    private static final String DB_COLUMN_FAV = "favori";


    //Attributs de la classe
    private String username;
    private String lastName;
    private String firstName;
    private String mailAdress;
    private String password;
    private String profilePic;
    private String bestFriend;
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

    public User(String username, String password){
            this.username=username;
            this.password=password;
            friendList=new ArrayList<>();
            notificationList=new ArrayList<>();
            pollList=new ArrayList<>();
            userMap.put(username,this);
        }
    public User(String uUsername,String uFName,String uLName, String uPassword, String email, String bestfriend, String profilePicFile){
        username=uUsername;
        firstName=uFName;
        lastName=uLName;
        password=uPassword;
        bestFriend=bestfriend;
        mailAdress=email;
        friendList=new ArrayList<>();
        notificationList=new ArrayList<>();
        pollList=new ArrayList<>();
        userMap.put(uUsername,this);
        profilePic=profilePicFile;
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
    public void setBestFriend(String bestFriend){
        this.bestFriend=bestFriend;
    }
    public String getBestFriend(){
        return bestFriend;
    }
    public void setProfilePic(String p){
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
    public String getProfilePic() {
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

    /**
     * Fournit la liste des utilisateurs.
     */

    public static ArrayList<User> getUsers() {
        //ArrayList<User> michel = new ArrayList<>();
        //michel.add(new User("michel","michel","dupond","12345"));
        //return michel;

        // Récupération du  SQLiteHelper et de la base de données.

        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        ArrayList<User> users = new ArrayList<>();
        //users.add(new User("michel","michel","dupond","12345"));

        // Colonnes à récupérer
        String[] colonnes = {DB_COLUMN_USERNAME, DB_COLUMN_FNAME, DB_COLUMN_LNAME, DB_COLUMN_PASSWORD, DB_COLUMN_EMAIL, DB_COLUMN_PIC, DB_COLUMN_FAV};

        // Requête de selection (SELECT)
        Cursor cursor = db.query(DB_TABLE, colonnes, null, null, null, null, null);

        // Placement du curseur sur la première ligne.
        cursor.moveToFirst();


        // Tant qu'il y a des lignes.
        while (!cursor.isAfterLast()) {
            // Récupération des informations de l'utilisateur pour chaque ligne.
            String uUsername = cursor.getString(0);
            String uFName = cursor.getString(1);
            String uLName = cursor.getString(2);
            String uPassword = cursor.getString(3);
            String email = cursor.getString(4);
            String pic = cursor.getString(5);
            String bestfriend = cursor.getString(6);
/*
            // Vérification pour savoir s'il y a déjà une instance de cet utilisateur.
            User user = userMap.get(uUsername);
            if (user == null) {
                // Si pas encore d'instance, création d'une nouvelle instance.
                */
                User user= new User(uUsername,uFName,uLName,uPassword,email,bestfriend,pic);


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

    public static ArrayList<User> getFriends() {
        ArrayList<User> users = new ArrayList<>();
        if(loggedUser.getBestFriend()!=null) {
            User bff = User.getUser(loggedUser.getBestFriend());
            Log.i("add",loggedUser.getBestFriend());
            users.add(bff);
        }
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        String[] colonnes = {DB_COLUMN_USERNAME, "username_amis", "etat"};
        Cursor cursor = db.query("Friend_list", colonnes, DB_COLUMN_USERNAME+"=? AND etat=?", new String[] {loggedUser.getUsername(),String.valueOf(1)}, null, null, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            String uUsername = cursor.getString(0);
            String uFriendUsername = cursor.getString(1);
            if(!uFriendUsername.equals(loggedUser.getBestFriend())) {

                User user = getUser(uFriendUsername);
                Log.i("add",uFriendUsername);
                users.add(user);
            }
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        //loggedUser.addFriend(users);
        return users;
    }

    public static User getUser(String username) {
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        User user = null;
        String[] colonnes = {DB_COLUMN_USERNAME, DB_COLUMN_FNAME, DB_COLUMN_LNAME, DB_COLUMN_PASSWORD, DB_COLUMN_EMAIL, DB_COLUMN_PIC, DB_COLUMN_FAV};
        Cursor cursor = db.query(DB_TABLE, colonnes, DB_COLUMN_USERNAME+"=?", new String[]{username}, null, null, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            String uUsername = cursor.getString(0);
            String uFName = cursor.getString(1);
            String uLName = cursor.getString(2);
            String uPassword = cursor.getString(3);
            String email = cursor.getString(4);
            String pic = cursor.getString(5);
            String bestfriend = cursor.getString(6);
            user=new User(uUsername,uFName,uLName,uPassword,email,bestfriend,pic);
            cursor.moveToNext();
        }
        cursor.close();
        db.close();

        return user;
    }

    public static void writeInUser(User user, String usernameArg)
    {
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        ContentValues values = new ContentValues();
        String username=user.getUsername();
        String firstname=user.getFirstName();
        String lastname=user.getLastName();
        String password=user.getPassword();
        String mail=user.getMailAdress();
        String bff=user.getBestFriend();
        String picture=user.getProfilePic();
        values.put(DB_COLUMN_USERNAME, username);
        values.put(DB_COLUMN_FNAME, firstname);
        values.put(DB_COLUMN_LNAME, lastname);
        values.put(DB_COLUMN_PASSWORD, password);
        values.put(DB_COLUMN_EMAIL, mail);
        values.put(DB_COLUMN_PIC, picture);
        values.put(DB_COLUMN_FAV, bff);
        Log.i("test","update");
        db.update(DB_TABLE, values, DB_COLUMN_USERNAME+"=?", new String[]{usernameArg});
        Log.i("test","done");
        loggedUser=(User.toHashMap(User.getUsers())).get(username);
        //Log.i("Display",loggedUser.getProfilePic());
        db.close();
    }

    public static void modifyUser(User user)
    {
        userMap.remove(user.getUsername());
        userMap.put(user.getUsername(),user);
        writeInUser(user, user.getUsername());
    }

    public static void modifyUserWithUsername(User user, String username)
    {
        userMap.remove(username);
        userMap.put(user.getUsername(),user);
        writeInUser(user, username);
    }

    public static void addUser(User user)
    {
        userMap.put(user.getUsername(),user);
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        ContentValues values = new ContentValues();
        String username=user.getUsername();
        String firstname=user.getFirstName();
        String lastname=user.getLastName();
        String password=user.getPassword();
        String mail=user.getMailAdress();
        String bff=user.getBestFriend();
        String picture=user.getProfilePic();
        values.put(DB_COLUMN_USERNAME, username);
        values.put(DB_COLUMN_FNAME, firstname);
        values.put(DB_COLUMN_LNAME, lastname);
        values.put(DB_COLUMN_PASSWORD, password);
        values.put(DB_COLUMN_EMAIL, mail);
        values.put(DB_COLUMN_PIC, picture);
        values.put(DB_COLUMN_FAV, bff);

        Log.i("test","insert");
        db.insert(DB_TABLE, null, values);

        Log.i("test","done");
        db.close();
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
    public static Bitmap toBitmap(String filePath, ContentResolver content){
        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(content, Uri.parse(filePath));
            return bitmap;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public static void refreshLoggedUser()
    {
        User.loggedUser=(User.toHashMap(User.getUsers())).get(User.loggedUser.getUsername());
    }
}
