package p.poll.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.sql.Date;
import java.util.HashMap;

import p.poll.MySQLiteHelper;
import p.poll.model.struc.LimitedNumber;
import p.poll.model.struc.ListNumber;

public class Poll {

    //Constantes
    public static final int MAX_POLL = 1000;
    private static final String DB_TABLE_POLL_ACCESS = "Poll_access";
    private static final String DB_COLUMN_PARTICULAR_STATUS = "statut_particulier";
    private static final String DB_COLUMN_ID = "idpoll";
    private static final String DB_COLUMN_USERNAME = "username";


    //Structures utilisees pour l affectation des id
    protected static ListNumber number = new ListNumber();
    protected static LimitedNumber currentNumber = new LimitedNumber(MAX_POLL);

    //Attributs
    protected String title;
    protected String description;
    protected char type;
    protected User owner;
    protected int id;
    protected int status;

    //Constructeurs
    public Poll(){
        this.setId();
    }
    public Poll(String title, String description, char type, User owner) {
        this.setTitle(title);
        this.setDescription(description);
        this.setType(type);
        this.setOwner(owner);
        this.setId();
        this.status=0;
    }

    //Getteurs et setteurs
    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public char getType()
    {
        return this.type;
    }

    public void setType(char type)
    {
        this.type = type;
    }

    public User getOwner()
    {
        return owner;
    }

    public void setOwner(User owner)
    {
        this.owner = owner;
    }


    public int getId()
    {
        return id;
    }

    public void setDone(){
        status=1;
    }

    public int getStatus(){
        return status;
    }

    //Adders et getters
    public void addAccess(User user)
    {
        addAccess(this,user);
    }

    public ArrayList<String> getAccess()
    {
        return getAccess(this);
    }

    //Redefinition de la methode equals
    @Override
    public boolean equals(Object o) {
        if (o != null) {
            if (o instanceof Poll) {
                if(((Poll) o).getId()==(this.getId())){
                    return true;
                }
            }
        }
        return false;
    }

    //Methodes
    public void chooseBySliding()
    {
        //a completer
    }

    //Database
    private static void addAccess(Poll poll, User user)
    {
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        ContentValues values = new ContentValues();
        String id = String.valueOf(poll.getId());
        String username = user.getUsername();
        String status = String.valueOf(0);
        values.put(DB_COLUMN_USERNAME, username);
        values.put(DB_COLUMN_ID,id);
        values.put(DB_COLUMN_PARTICULAR_STATUS, status);
        db.insert(DB_TABLE_POLL_ACCESS, null, values);
        db.close();
    }

    //TODO: il faudra juste faire une fonction pour get les sondages auquels on a acces

    private static ArrayList<String> getAccess(Poll poll)
    {
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        ArrayList<String> users = new ArrayList<>();
        String[] colonnes = {DB_COLUMN_USERNAME};
        String id = String.valueOf(poll.getId());
        Cursor cursor = db.query(DB_TABLE_POLL_ACCESS, colonnes, DB_COLUMN_ID+"=?", new String[]{id}, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String uUsername = cursor.getString(0);
            users.add(uUsername);
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return users;
    }

    public static int setId()
    {
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        int id = 0;
        String[] colonnes = {"idpoll"};
        Cursor cursor = db.query("Poll", colonnes, null, null, null, null, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            String idnew = cursor.getString(0);
            if(Integer.parseInt(idnew)>id)
            {
                id=Integer.parseInt(idnew);
            }
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return id+1;
    }

    public static void setDone(int id)
    {
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("status_principal",1);
        db.update("Poll", values,"idpoll = ?",new String[] {String.valueOf(id)});
        db.close();
    }

    public static void setUserDone(int id, User user)
    {
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("statut_particulier",String.valueOf(1));
        db.update("Poll_access",values,"idpoll=? AND username=?",new String[]{String.valueOf(id),user.getUsername()});
        db.close();
    }
}

