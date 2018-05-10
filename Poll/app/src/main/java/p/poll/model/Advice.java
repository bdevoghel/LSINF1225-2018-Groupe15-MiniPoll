package p.poll.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import p.poll.MySQLiteHelper;

/**
 * Created by Vahid Beyraghi on 21-04-18.
 */

public class Advice extends Poll {

    //Attributs
    private String imagePath1;
    private String imagePath2;
    private String description;

    //Constructeurs
    public Advice(){
        super();
    }
    public Advice(String title, String description, Character type, User owner, String imagePath1, String imagePath2, String descriptionQ) {
        super(title, description, type, owner);
        this.imagePath1=imagePath1;
        this.imagePath2 =imagePath2;
        this.description=descriptionQ;
    }
    //Getteurs et setteurs
    public String getDescription()
    {
        return description;
    }
    public String getImagePath1()
    {
        return imagePath1;
    }
    public String getImagePath2()
    {
        return imagePath2;
    }
    public void setImagePath1(String imagePath1)
    {
        this.imagePath1=imagePath1;
    }
    public void setImagePath2(String imagePath2)
    {
        this.imagePath2=imagePath2;
    }
    public void setDescription(String description)
    {
        this.description=description;
    }

    //DataBase

    public static void createAdvice(User user, String imagePath1, String imagePath2, String description){
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        ContentValues values = new ContentValues();
        String username=user.getUsername();
        String id=String.valueOf(Poll.setId());
        values.put("username_proprietaire", User.loggedUser.getUsername());
        values.put("idpoll", id);
        values.put("titre", "Help me out!");
        values.put("description", "Help me making a choice!");
        values.put("types", "a");
        values.put("status_principal", 0);
        Log.i("test","insert");
        db.insert("Poll", null, values);
        Log.i("test","done");

        values=new ContentValues();
        values.put("idpoll",id);
        values.put("username",username);
        values.put("status_particulier",0);
        db.insert("Poll_access",null,values);

        values=new ContentValues();
        values.put("username",username);
        values.put("etat",0);
        values.put("message",User.loggedUser.getFirstName()+" "+User.loggedUser.getLastName()+" want you to help him out for a choice!");
        values.put("username_notif",User.loggedUser.getUsername());
        values.put("poll_notif",id);
        db.insert("Notifications",null,values);

        values=new ContentValues();
        String idQuestion=String.valueOf(Question.setIdQuestion(id));
        values.put("idpoll",id);
        values.put("idquestion",idQuestion);
        values.put("description_question",description);
        db.insert("Question_list",null,values);

        values=new ContentValues();
        values.put("idquestion",idQuestion);
        values.put("texte",imagePath1);
        values.put("reponse",String.valueOf(0));
        db.insert("Questionnaire_and_Advice",null,values);
        values=new ContentValues();
        values.put("idquestion",idQuestion);
        values.put("texte",imagePath2);
        values.put("reponse",String.valueOf(0));
        db.insert("Questionnaire_and_Advice",null,values);

        db.close();
    }

    public static ArrayList<Advice> getAdvice()
    {
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        ArrayList<Advice> advices = new ArrayList<>();

        String[] colonnes = {"idpoll"};
        Cursor cursor = db.query("Poll_access", colonnes, "username=? AND status_particulier=?", new String[]{User.loggedUser.getUsername(),String.valueOf(0)}, null, null, null);
        cursor.moveToFirst();
        ArrayList<Integer> idpoll = new ArrayList<>();
        while (!cursor.isAfterLast()) {
            idpoll.add(Integer.valueOf(cursor.getString(0)));
            cursor.moveToNext();
        }
        cursor.close();
        for(int i=0;i<idpoll.size();i++) {
            String idQuestion=null;
            String description=null;
            String[] colonnes2 = {"idquestion", "description_question"};
            cursor = db.query("Question_list", colonnes2, "idpoll=?", new String[]{idpoll.get(i).toString()}, null, null, null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                idQuestion=cursor.getString(0);
                description=cursor.getString(1);
                cursor.moveToNext();
            }
            cursor.close();

            String[] colonnes3 = {"texte"};
            cursor = db.query("Questionnaire_and_Advice", colonnes3, "idquestion=?", new String[]{idQuestion}, null, null, null);
            cursor.moveToFirst();
            String[] imagePath = new String[2];
            for (int j=0;!cursor.isAfterLast();j++) {
                imagePath[j]=cursor.getString(0);
                cursor.moveToNext();
            }
            cursor.close();

            User owner=null;
            String[] colonnes4 = {"username_proprietaire"};
            cursor = db.query("Poll", colonnes4, "idpoll=? AND status_principal=?", new String[]{String.valueOf(idpoll),String.valueOf(0)}, null, null, null);
            cursor.moveToFirst();
            for (int j=0;!cursor.isAfterLast();j++) {
                owner=User.getUser(cursor.getString(0));
                cursor.moveToNext();
            }
            cursor.close();

            if(owner!=null) {
                advices.add(new Advice("Help me out!", "Help me making a choice!", 'a', owner,imagePath[0],imagePath[1],description));
            }
        }
        db.close();
        return advices;
    }

    public static void answer(int idpoll, int choice)
    {
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();

        String idQuestion=null;
        String[] colonnes = {"idquestion"};
        Cursor cursor = db.query("Question_list", colonnes, "idpoll=?", new String[]{String.valueOf(idpoll)}, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            idQuestion=cursor.getString(0);
            cursor.moveToNext();
        }
        cursor.close();

        if(idQuestion!=null) {
            ContentValues values = new ContentValues();
            values.put("username", User.loggedUser.getUsername());
            values.put("idquestion", idQuestion);
            values.put("reponse_utilisateur", choice);
            Log.i("test", "insert");
            db.insert("Questionnaire_and_Advice_Answer", null, values);
            Log.i("test", "done");
        }
        Poll.setUserDone(idpoll,User.loggedUser);
        db.close();
    }

    public static int getAnswer(int idpoll)
    {
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();

        String idQuestion=null;
        String[] colonnes = {"idquestion"};
        Cursor cursor = db.query("Question_list", colonnes, "idpoll=?", new String[]{String.valueOf(idpoll)}, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            idQuestion=cursor.getString(0);
            cursor.moveToNext();
        }
        cursor.close();

        String[] colonnes2 = {"reponse_utilisateur"};
        cursor=db.query("Questionnaire_and_Advice_Answer",colonnes2,"idquestion=?",new String[]{idQuestion},null,null,null);
        cursor.moveToFirst();
        int answer=0;
        while (!cursor.isAfterLast()) {
            answer=Integer.parseInt(cursor.getString(0));
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return answer;
    }
}
