package p.poll.model;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import p.poll.MySQLiteHelper;

/**
 * Created by Vahid Beyraghi on 21-04-18.
 */

public class Advice extends Poll {

    //Attributs
    private Question question;

    //Constructeurs
    public Advice(){
        super();
    }
    public Advice(String title, String description, java.sql.Date deadline,
                  Character type, User owner) {
        super(title, description, type, owner);
    }
    public Advice(String title, String description, java.sql.Date deadline,
                  Character type, User owner, Question question){
        super(title, description, type, owner);
        this.question=question;
    }
    public Advice(Question question){
        super();
        this.question=question;
    }

    //Getteurs et setteurs
    public void setQuestion(Question question){
        this.question=question;
    }
    public Question getQuestion(){
        return question;
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
        

        db.close();
    }
}
