package p.poll.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import p.poll.MySQLiteHelper;

/**
 * Created by Vahid Beyraghi on 22-04-18.
 */

public class Survey extends Poll {




    private static final String DB_TABLE_POLL = "Poll";
    private static final String DB_COLUMN_OWNER = "username_proprietaire";
    private static final String DB_COLUMN_ID = "idpoll";
    private static final String DB_COLUMN_TITLE = "titre";
    private static final String DB_COLUMN_DESCRIPTION = "description";
    private static final String DB_COLUMN_TYPE = "types";
    private static final String DB_COLUMN_STATUS = "status_principal";

    private static final String DB_TABLE_SURVEY = "Survey";
    private static final String DB_COLUMN_DATA = "data_reponse";

    private static final String DB_TABLE_SURVEY_ANSWER = "Survey_Answer";
    private static final String DB_COLUMN_USERNAME = "username";
    private static final String DB_COLUMN_ANSWER = "reponse";
    private static final String DB_COLUMN_ORDER = "ordre";





    //Attributs
    private final int NUMBER_PROPOSITION=5;
    private ArrayList<Proposition> propositions;

    //Constructeurs
    public Survey(){
        super();
        propositions=new ArrayList<>();
    }
    public Survey(ArrayList<Proposition> propositions){
        super();
        this.propositions=propositions;
    }
    public Survey(String title, String description,
                  char type, User owner){
        super(title, description, type, owner);
        propositions=new ArrayList<>();
    }
    public Survey(String title, String description, char type, User owner, ArrayList<Proposition> propositions){
        super(title, description,type, owner);
        this.propositions=propositions;
    }

    //Getteurs et setteurs
    public ArrayList<Proposition> getPropostions(){
        return propositions;
    }
    public int getNUMBER_PROPOSITION(){
        return NUMBER_PROPOSITION;
    }

    //Adders
    public void addProposition(Proposition p){
        propositions.add(p);
    }

    public void addProposition(String answer){
        propositions.add(new Proposition(answer));
    }

    //Answer a survey
    public void answerProposition(Proposition p,int tag){
        answerSurvey(this,p,tag);
    }

    //Database
    public static void modifySurvey(Survey poll)
    {
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        ContentValues values = new ContentValues();
        String title=poll.getTitle();
        String description=poll.getDescription();
        String status=String.valueOf(poll.getStatus());
        values.put(DB_COLUMN_STATUS, status);
        values.put(DB_COLUMN_TITLE, title);
        values.put(DB_COLUMN_DESCRIPTION, description);
        db.update(DB_TABLE_POLL, values, DB_COLUMN_ID+"=?", new String[]{String.valueOf(poll.getId())});
    }

    private static void answerSurvey(Survey poll,Proposition p, int tag)
    {
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        ContentValues values = new ContentValues();
        String id = String.valueOf(poll.getId());
        String answer = p.getAnswer();
        values.put(DB_COLUMN_USERNAME, User.loggedUser.getUsername());
        values.put(DB_COLUMN_ID,id);
        values.put(DB_COLUMN_ANSWER, answer);
        values.put(DB_COLUMN_ORDER, tag);
        db.insert(DB_TABLE_SURVEY_ANSWER, null, values);
        db.close();
    }

    public static void addSurvey(Survey poll)
    {
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        ContentValues values = new ContentValues();
        String id=String.valueOf(poll.getId());
        String title=poll.getTitle();
        String description=poll.getDescription();
        String type=String.valueOf(poll.getType());
        User owner=poll.getOwner();
        String status=String.valueOf(poll.getStatus());
        values.put(DB_COLUMN_TITLE, title);
        values.put(DB_COLUMN_DESCRIPTION, description);
        values.put(DB_COLUMN_TYPE, type);
        values.put(DB_COLUMN_ID, id);
        values.put(DB_COLUMN_OWNER, owner.getUsername());
        values.put(DB_COLUMN_STATUS,status);
        db.insert(DB_TABLE_POLL, null, values);

        ContentValues values2 = new ContentValues();
        ArrayList<Proposition> propositions=poll.getPropostions();
        values2.put(DB_COLUMN_ID,poll.getId());
        for(int i=0;i<propositions.size();i++)
        {
            values2.put(DB_COLUMN_DATA,propositions.get(i).getAnswer());
        }
        db.insert(DB_TABLE_SURVEY, null, values2);

        db.close();
    }

    private ArrayList<Integer> getListValueProposition(Proposition proposition){
        ArrayList<Integer> sondages = new ArrayList<Integer>();
        String sProposition = proposition.getAnswer();
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        //Les resultats de la requete sont mis dans un "curseur"

        Cursor c = db.query("Survey_Answer", // La table
                new String[]{"ordre"},
                "reponse=?", // Colonnes pour la clause WHERE
                new String[]{sProposition}, // Valeurs pour la clause WHERE
                null, // ne pas grouper les lignes
                null, // ne pas filtrer par goupe de ligne
                null  // pas d'ordre
        );

        if (c.moveToFirst()) {
            for (int i = 0; i < c.getCount(); i++) {
                int s = c.getInt(c.getColumnIndexOrThrow("ordre"));
                sondages.add(s);
                c.moveToNext();
            }
        }
        c.close();
        return sondages;
    }

    public ArrayList<Proposition> getListProposition (Survey poll){
        ArrayList<Proposition> proposition = new ArrayList<>();
        int pollIdentifiant=poll.getId();
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        //Les resultats de la requete sont mis dans un "curseur"
        Cursor c = db.query("Survey", // La table
                new String[]{"data_reponse"},
                "idpoll=?",
                new String[]{String.valueOf(pollIdentifiant)},
                null,
                null,
                null
        );
        if (c.moveToFirst()) {
            for (int i = 0; i < 6; i++) {
                if (i >= c.getCount()){
                    proposition.add(null);
                    c.moveToNext();
                }
                Proposition s = new Proposition(c.getString(c.getColumnIndexOrThrow("data_reponse")));
                proposition.add(s);
                c.moveToNext();
            }
        }
        c.close();
        return proposition;
    }

    public ArrayList<Survey> getYourListSurvey(User user) {
        ArrayList<Survey> sondages = new ArrayList<>();
        String identifiant = user.getUsername();
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        //Les resultats de la requete sont mis dans un "curseur"

        Cursor c = db.query("Poll", // La table
                new String[]{"identifiant_proprietaire", "titre", "description",DB_COLUMN_STATUS},
                "id =? AND type=?",
                new String[]{identifiant, "S"},
                null,
                null,
                null

        );
        if (c.moveToFirst()) {
            for (int i = 0; i < c.getCount(); i++) {
                String s = c.getString(c.getColumnIndexOrThrow("titre"));
                String x = c.getString(c.getColumnIndexOrThrow("identifiant_proprietaire"));
                String d = c.getString(c.getColumnIndexOrThrow("description"));
                Survey sondage=new Survey(s,d,'s',user);
                sondage.setId();
                sondages.add(sondage);
                c.moveToNext();
            }
        }
        c.close();
        return sondages;

    }
/*
    // A la première place
    public void increment1 (Proposition proposition){
        int p = getListValueProposition(proposition).get(0);
        p = p+5;
        ContentValues newValues = new ContentValues();
        newValues.put("ordre", p);

        MySQLiteHelper.get().getReadableDatabase().update("reponse_sondage", newValues, "id = "+proposition, null);
    }

    // A la deuxième place
    public void increment2 (Proposition proposition){
        int p = getListValueProposition(proposition).get(0);
        p = p+4;
        ContentValues newValues = new ContentValues();
        newValues.put("ordre", p);

        MySQLiteHelper.get().getReadableDatabase().update("reponse_sondage", newValues, "id = "+proposition, null);
    }

    // A la troisième place
    public void increment3 (Proposition proposition){
        int p = getListValueProposition(proposition).get(0);
        p = p+3;
        ContentValues newValues = new ContentValues();
        newValues.put("ordre", p);

        //MySQLiteHelper.get().getReadableDatabase().update("reponse_sondage", newValues, "id = ?", neproposition);
    }

    // A la quatrième place
    public void increment4 (Proposition proposition){
        int p = getListValueProposition(proposition).get(0);
        p = p+2;
        ContentValues newValues = new ContentValues();
        newValues.put("ordre", p);

        MySQLiteHelper.get().getReadableDatabase().update("reponse_sondage", newValues, "id = "+proposition, null);
    }

    // A la cinquième place
    public void increment5 (Proposition proposition){
        int p = getListValueProposition(proposition).get(0);
        p = p+1;
        ContentValues newValues = new ContentValues();
        newValues.put("ordre", p);

        MySQLiteHelper.get().getReadableDatabase().update("reponse_sondage", newValues, "id = "+proposition, null);
    }

*/


    private Proposition getListOrdre (Proposition p){
        ArrayList<Proposition> ordre = new ArrayList<>();
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        //Les resultats de la requete sont mis dans un "curseur"

        Cursor c = db.query(DB_TABLE_SURVEY, // La table
                new String[]{DB_COLUMN_ORDER},
                DB_COLUMN_ID+"=? AND "+DB_COLUMN_ANSWER+"=?",
                new String[]{String.valueOf(this.getId()),p.getAnswer()},
                null,
                null,
                null
        );
        if (c.moveToFirst()) {
            for (int i = 0; i < c.getCount(); i++) {
                int s = c.getInt(c.getColumnIndexOrThrow(DB_COLUMN_ORDER));
                ordre.add(new Proposition(s,p.getAnswer()));
                c.moveToNext();
            }
        }
        c.close();
        return new Proposition(getOrder(ordre),p.getAnswer());
    }

    private int getOrder(ArrayList<Proposition> p)
    {
        int count=0;
        for(int i=0;i<p.size();i++)
        {
            count=+p.get(i).getTag();
        }
        return count;
    }

    private Proposition premiers (ArrayList<Proposition> tab){
        int max = 0;
        Proposition p=null;
        for (int i = 0; i < tab.size(); i++){
            if (tab.get(i).getTag()>max){
                max = tab.get(i).getTag();
                p=tab.get(i);
            }
        }
        return p;
    }

    public Proposition getGagnant(){
        ArrayList<Proposition> propositions=this.getPropostions();
        this.setDone();
        Survey.modifySurvey(this);
        return premiers(propositions);
    }
}
