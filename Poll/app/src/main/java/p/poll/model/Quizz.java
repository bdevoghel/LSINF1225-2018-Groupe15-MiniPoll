package p.poll.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ConcurrentLinkedDeque;

import p.poll.MySQLiteHelper;

/**
 * Created by Vahid Beyraghi on 22-04-18.
 */

public class Quizz extends Poll {
    //Attributs
    private static final int NUMBER_QUESTIONS=6;
    private static final int NUMBER_PROPOSITIONS=6;
    private ArrayList<Question> questionList;

    //Constructeurs
    public Quizz(){
        super();
        questionList=new ArrayList<>();
    }
    public Quizz(Question question){
        super();
        questionList=new ArrayList<>();
        this.addQuestion(question);
    }
    public Quizz(String title, String description,
                  char type, User owner){
        super(title, description, type, owner);
        questionList=new ArrayList<>();
    }
    public Quizz(String title, String description,
                  char type, User owner, Question question){
        super(title, description, type, owner);
        questionList=new ArrayList<>();
        this.addQuestion(question);
    }
    public Quizz(String title, String description,
                 char type, User owner, ArrayList<Question> questions){
        super(title, description, type, owner);
        questionList=questions;
    }

    //Getteurs et setteurs
    public void setQuestionList(ArrayList<Question> questions){
        this.questionList=questions;
    }
    public ArrayList<Question> getQuestionList(){
        return questionList;
    }

    //Addeurs et removers
    public void addQuestion(Question question){
        questionList.add(question);
    }
    public void addQuestion(ArrayList<Question> questions){
        for(int i=0;i<questions.size();i++){
            this.addQuestion(questions.get(i));
        }
    }
    public void rmvQuestion(Question question){
        questionList.remove(question);
    }
    public void rmvQuestion(ArrayList<Question> questions){
        for(int i=0;i<questions.size();i++){
            this.rmvQuestion(questions.get(i));
        }
    }

    //DATABASE

    public static void createQuizz(String title, String description, ArrayList<User> userList, ArrayList<Question> questionList)
    {
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        ContentValues values = new ContentValues();
        String id=String.valueOf(Poll.setId());
        values.put("username_proprietaire", User.loggedUser.getUsername());
        values.put("idpoll", id);
        values.put("titre", title);
        values.put("description", description);
        values.put("types", "q");
        values.put("status_principal", 0);
        Log.i("test","insert");
        db.insert("Poll", null, values);
        Log.i("test","done");

        for(int i=0;i<userList.size();i++) {
            values = new ContentValues();
            values.put("idpoll", id);
            values.put("username", userList.get(i).getUsername());
            values.put("status_particulier", 0);
            db.insert("Poll_access", null, values);

            values = new ContentValues();
            values.put("username", userList.get(i).getUsername());
            values.put("etat", 0);
            values.put("message", User.loggedUser.getFirstName() + " " + User.loggedUser.getLastName() + " wants you to answer his new Quizz : "+title+"!");
            values.put("username_notif", User.loggedUser.getUsername());
            values.put("poll_notif", id);
            db.insert("Notifications", null, values);
        }

        for(int i=0;i<questionList.size();i++) {
            values = new ContentValues();
            String idQuestion = String.valueOf(Question.setIdQuestion(id));
            values.put("idpoll", id);
            values.put("idquestion", idQuestion);
            values.put("description_question", questionList.get(i).getTitle());
            db.insert("Question_list", null, values);

            for(int j=0;j<questionList.get(i).getPropositionList().size();j++) {
                Proposition proposition=questionList.get(i).getPropositionList().get(j);
                values = new ContentValues();
                values.put("idquestion", idQuestion);
                values.put("texte", proposition.getAnswer());
                values.put("reponse", proposition.getTag());
                db.insert("Questionnaire_and_Advice", null, values);
            }
        }
        db.close();
    }

    public static void answerQuestionQuizz(int idpoll, int idQuestion, Proposition p)
    {
        String answer=p.getAnswer();
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("username",User.loggedUser.getUsername());
        values.put("idquestion",String.valueOf(idQuestion));
        values.put("reponse_utilisateur",answer);
        db.insert("Questionnaire_and_Advice_Answer",null,values);
        db.close();
    }

    public static void answerQuestionQuizz(int idpoll, int idQuestion, int answer)
    {
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("username",User.loggedUser.getUsername());
        values.put("idquestion",String.valueOf(idQuestion));
        values.put("reponse_utilisateur",String.valueOf(answer));
        db.insert("Questionnaire_and_Advice_Answer",null,values);
        db.close();
    }

    public static ArrayList<Quizz> getQuizz(){
        ArrayList<Quizz> quizzes = new ArrayList<>();
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
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
            ArrayList<Question> questions=new ArrayList<>();
            ArrayList<String> idQuestion=new ArrayList<>();
            ArrayList<String> description=new ArrayList<>();
            String[] colonnes2 = {"idquestion", "description_question"};
            cursor = db.query("Question_list", colonnes2, "idpoll=?", new String[]{idpoll.get(i).toString()}, null, null, null);
            cursor.moveToFirst();
            for (int j=0;!cursor.isAfterLast();j++) {
                idQuestion.add(cursor.getString(0));
                description.add(cursor.getString(1));
                cursor.moveToNext();
            }
            cursor.close();
            for(int k=0;k<idQuestion.size();k++) {
                String[] colonnes3 = {"texte", "reponse"};
                cursor = db.query("Questionnaire_and_Advice", colonnes3, "idquestion=?", new String[]{idQuestion.get(k)}, null, null, null);
                cursor.moveToFirst();
                ArrayList<Proposition> propositions = new ArrayList<>();
                for (int j = 0; !cursor.isAfterLast(); j++) {
                    String text = cursor.getString(0);
                    int tag = Integer.parseInt(cursor.getString(0));
                    propositions.add(new Proposition(tag,text));
                    cursor.moveToNext();
                }
                questions.add(new Question(description.get(k),propositions,Integer.parseInt(idQuestion.get(k))));
                cursor.close();
            }
            User owner=null;
            String title=null;
            String descriptionP=null;
            String[] colonnes4 = {"username_proprietaire","titre","description"};
            cursor = db.query("Poll", colonnes4, "idpoll=? AND status_principal=? AND types=?", new String[]{String.valueOf(idpoll),String.valueOf(0),"q"}, null, null, null);
            cursor.moveToFirst();
            for (int j=0;!cursor.isAfterLast();j++) {
                owner=User.getUser(cursor.getString(0));
                title=cursor.getString(1);
                descriptionP=cursor.getString(2);
                cursor.moveToNext();
            }
            cursor.close();

            if(owner!=null) {
                quizzes.add(new Quizz(title,descriptionP,'q',owner,questions));
            }
        }
        db.close();
        return quizzes;
    }

    public static ArrayList<Integer> getAnswers(int idpoll, User user)
    {
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        ArrayList<Integer> answerInt=new ArrayList<>();
        ArrayList<String> idQuestion=new ArrayList<>();
        String[] colonnes = {"idquestion"};
        Cursor cursor = db.query("Question_list", colonnes, "idpoll=?", new String[]{String.valueOf(idpoll)}, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            idQuestion.add(cursor.getString(0));
            cursor.moveToNext();
        }
        cursor.close();
        for(int i=0;i<idQuestion.size();i++) {
            String[] colonnes2 = {"reponse_utilisateur"};
            cursor = db.query("Questionnaire_and_Advice_Answer", colonnes2, "username=? AND idquestion=?", new String[]{user.getUsername(),idQuestion.get(i)}, null, null, null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                answerInt.add(Integer.valueOf(cursor.getString(0)));
                cursor.moveToNext();
            }
            cursor.close();
        }
        db.close();
        return answerInt;
    }

    public static int getAnswerOfQuestion(int idpoll, int idquestion, User user){
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        int answer=0;
            String[] colonnes2 = {"reponse_utilisateur"};
            Cursor cursor = db.query("Questionnaire_and_Advice_Answer", colonnes2, "username=? AND idquestion=?", new String[]{user.getUsername(),String.valueOf(idquestion)}, null, null, null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                answer=(Integer.valueOf(cursor.getString(0)));
                cursor.moveToNext();
            }
            cursor.close();
        db.close();
        return answer;
    }
}
