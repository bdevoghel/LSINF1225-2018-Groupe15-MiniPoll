package p.poll.model;

import android.content.ContentResolver;
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
    public Advice(int id, String title, String description, Character type, User owner, String imagePath1, String imagePath2, String descriptionQ) {
        super(id, title, description, type, owner);
        this.imagePath1=imagePath1;
        this.imagePath2 =imagePath2;
        this.description=descriptionQ;
    }
    //Getteurs et setteurs
    public String getDescriptionQ()
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
        ContentValues values = new ContentValues();
        String username=user.getUsername();
        String id=String.valueOf(Poll.setId());
        String idQuestion=String.valueOf(Question.setIdQuestion(id));
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        values.put("username_proprietaire", User.loggedUser.getUsername());
        values.put("idpoll", id);
        values.put("titre", "Help me out!");
        values.put("description", "Help me making a choice!");
        values.put("types", "a");
        values.put("status_principal", String.valueOf(0));
        Log.i("test","insert");
        db.insert("Poll", null, values);
        Log.i("test","done");

        values=new ContentValues();
        values.put("idpoll",id);
        values.put("username",username);
        values.put("statut_particulier",String.valueOf(0));
        db.insert("Poll_access",null,values);

        values=new ContentValues();
        values.put("username",username);
        values.put("etat",0);
        values.put("message",User.loggedUser.getFirstName()+" "+User.loggedUser.getLastName()+" wants you to help him out for a choice!");
        values.put("username_notif",User.loggedUser.getUsername());
        values.put("poll_notif",id);
        db.insert("Notification",null,values);

        values=new ContentValues();
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

    public static Advice getMyAdviceFromId(int id){
        return getAdviceFromId(id,null);
    }

    public static Advice getAdviceFromId(int id,User user){
        ArrayList<Advice> advices=null;
        if(user==null) {
            advices = getAdvicesOf();
        }
        else
        {
            advices = getAdvice();
        }
        Advice advice=null;
        Log.i("Search id",String.valueOf(id));
        for(int i=0;i<advices.size();i++){
            if(advices.get(i).getId()==id)
                advice=advices.get(i);
        }
        return advice;
    }

    public static ArrayList<Advice> getAdvice()
    {
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        ArrayList<Advice> advices = new ArrayList<>();

        String[] colonnes = {"idpoll"};
        Cursor cursor = db.query("Poll_access", colonnes, "username=? AND statut_particulier=?", new String[]{User.loggedUser.getUsername(),String.valueOf(0)}, null, null, null);
        cursor.moveToFirst();
        ArrayList<Integer> idpoll = new ArrayList<>();
        while (!cursor.isAfterLast()) {
            idpoll.add(Integer.valueOf(cursor.getString(0)));
            Log.i("display",(cursor.getString(0)));
            cursor.moveToNext();
        }
        cursor.close();
        Log.i("display size",String.valueOf(idpoll.size()));
        Log.i("test",String.valueOf(0));
        db.close();
        for(int i=0;i<idpoll.size();i++) {
            db = MySQLiteHelper.get().getReadableDatabase();
            String idQuestion=null;
            String description=null;
            String[] colonnes2 = {"idquestion", "description_question"};
            Log.i("display",idpoll.get(i).toString());
            cursor = db.query("Question_list", colonnes2, "idpoll=?", new String[]{idpoll.get(i).toString()}, null, null, null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                idQuestion=cursor.getString(0);
                Log.i("test","idQuestion");
                description=cursor.getString(1);
                cursor.moveToNext();
            }
            cursor.close();
            if(idQuestion!=null) {


                String[] colonnes3 = {"texte"};
                cursor = db.query("Questionnaire_and_Advice", colonnes3, "idquestion=?", new String[]{idQuestion}, null, null, null);
                cursor.moveToFirst();
                String[] imagePath = new String[2];
                for (int j = 0; !cursor.isAfterLast(); j++) {
                    Log.i("test", "imagepath : " + j);
                    imagePath[j] = cursor.getString(0);
                    cursor.moveToNext();
                }
                cursor.close();

                User owner = null;
                Log.i("display", String.valueOf(idpoll));
                String[] colonnes4 = {"username_proprietaire"};
                cursor = db.query("Poll", colonnes4, "idpoll=? AND status_principal=? AND types=?", new String[]{String.valueOf(idpoll.get(i)), String.valueOf(0),"a"}, null, null, null);
                cursor.moveToFirst();
                for (int j = 0; !cursor.isAfterLast(); j++) {
                    Log.i("test", "owner");
                    owner = User.getUser(cursor.getString(0));
                    cursor.moveToNext();
                }
                cursor.close();
                Log.i("ids", String.valueOf(idpoll.get(i)));

                if (owner != null) {
                    advices.add(new Advice(idpoll.get(i), "Help me out!", "Help me making a choice!", 'a', owner, imagePath[0], imagePath[1], description));
                }
            }
            db.close();
        }
        return advices;
    }

    public static ArrayList<Advice> getAdvicesOf()
    {
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        ArrayList<Advice> advices = new ArrayList<>();

        String[] colonnes = {"idpoll"};
        Cursor cursor = db.query("Poll", colonnes, "username_proprietaire=? AND types=?", new String[]{User.loggedUser.getUsername(),"a"}, null, null, null);
        cursor.moveToFirst();
        ArrayList<Integer> idpoll = new ArrayList<>();
        while (!cursor.isAfterLast()) {
            idpoll.add(Integer.valueOf(cursor.getString(0)));
            Log.i("display",(cursor.getString(0)));
            cursor.moveToNext();
        }
        cursor.close();
        Log.i("display size",String.valueOf(idpoll.size()));
        Log.i("test",String.valueOf(0));
        for(int i=0;i<idpoll.size();i++) {
            String idQuestion=null;
            String description=null;
            String[] colonnes2 = {"idquestion", "description_question"};
            Log.i("display",idpoll.get(i).toString());
            cursor = db.query("Question_list", colonnes2, "idpoll=?", new String[]{idpoll.get(i).toString()}, null, null, null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                idQuestion=cursor.getString(0);
                Log.i("test","idQuestion");
                description=cursor.getString(1);
                cursor.moveToNext();
            }
            cursor.close();
            if(idQuestion==null){
                db.close();
                return advices;
            }

            String[] colonnes3 = {"texte"};
            cursor = db.query("Questionnaire_and_Advice", colonnes3, "idquestion=?", new String[]{idQuestion}, null, null, null);
            cursor.moveToFirst();
            String[] imagePath = new String[2];
            for (int j=0;!cursor.isAfterLast();j++) {
                Log.i("test","imagepath : "+j);
                imagePath[j]=cursor.getString(0);
                cursor.moveToNext();
            }
            cursor.close();
            advices.add(new Advice(idpoll.get(i),"Help me out!", "Help me making a choice!", 'a', User.loggedUser,imagePath[0],imagePath[1],description));

        }
        db.close();
        return advices;
    }

    public static void answer(int idpoll, int choice)
    {
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        Log.i("test", String.valueOf(idpoll));
        String idQuestion=null;
        String[] colonnes = {"idquestion"};
        Cursor cursor = db.query("Question_list", colonnes, "idpoll=?", new String[]{String.valueOf(idpoll)}, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Log.i("test","check");
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

        ContentValues values = new ContentValues();
        db.close();

        User owner = Poll.getOwner(idpoll);

        db = MySQLiteHelper.get().getReadableDatabase();

        values.put("username",owner.getUsername());
        values.put("etat",String.valueOf(0));
        values.put("username_notif",User.loggedUser.getUsername());
        values.put("message",User.loggedUser.getFirstName()+" "+User.loggedUser.getLastName()+" answered to your help request!");
        values.put("poll_notif",String.valueOf(idpoll));
        db.insert("Notification",null,values);
        Notification n =Notification.getNotification(idpoll);
        if(n==null) {
            Log.i("NULL", "n=NULL");
        }
        else {
            Notification.setDone(n);
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
