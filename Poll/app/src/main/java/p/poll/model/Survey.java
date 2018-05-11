package p.poll.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import p.poll.MySQLiteHelper;
import p.poll.activity.Sondage;

/**
 * Created by Vahid Beyraghi on 22-04-18.
 */

public class Survey extends Poll {


    public static int fini = 0;

    private static final String DB_TABLE_POLL = "Poll";
    private static final String DB_COLUMN_OWNER = "username_proprietaire";
    private static final String DB_COLUMN_ID = "idpoll";
    private static final String DB_COLUMN_TITLE = "titre";
    private static final String DB_COLUMN_DESCRIPTION = "description";
    private static final String DB_COLUMN_TYPE = "types";
    private static final String DB_COLUMN_STATUS = "status_principal";

    private static final String DB_TABLE_SURVEY = "Survey";
    private static final String DB_COLUMN_DATA = "data_reponse";
    private static final String DB_COLUMN_POINTS = "points";

    private static final String DB_TABLE_SURVEY_ANSWER = "Survey_Answer";
    private static final String DB_COLUMN_USERNAME = "username";
    private static final String DB_COLUMN_ANSWER = "reponse";
    private static final String DB_COLUMN_ORDER = "ordre";

    List<Integer> resultat = new ArrayList<Integer>();


    //Attributs
    private final int NUMBER_PROPOSITION = 6;
    private ArrayList<Proposition> propositions;

    //Constructeurs
    public Survey() {
        super();
        propositions = new ArrayList<>();
    }

    public Survey(ArrayList<Proposition> propositions) {
        super();
        this.propositions = propositions;
    }

    public Survey(String title, String description,
                  char type, User owner) {
        super(title, description, type, owner);
        propositions = new ArrayList<>();
    }

    public Survey(int id, String title, String description, char type, User owner, ArrayList<Proposition> propositions) {
        super(id, title, description, type, owner);
        this.propositions = propositions;
    }

    //Getteurs et setteurs
    public ArrayList<Proposition> getPropostions() {
        return propositions;
    }

    public int getNUMBER_PROPOSITION() {
        return NUMBER_PROPOSITION;
    }

    //Adders
    public void addProposition(Proposition p) {
        propositions.add(p);
    }

    public void addProposition(String answer) {
        propositions.add(new Proposition(answer));
    }

    //Answer a survey
    public void answerProposition(Proposition p, int tag) {
        answerSurvey(this, p, tag);
    }

    //Database
    public static void modifySurvey(Survey poll) {
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        ContentValues values = new ContentValues();
        String title = poll.getTitle();
        String description = poll.getDescriptionQ();
        String status = String.valueOf(poll.getStatus());
        values.put(DB_COLUMN_STATUS, status);
        values.put(DB_COLUMN_TITLE, title);
        values.put(DB_COLUMN_DESCRIPTION, description);
        db.update(DB_TABLE_POLL, values, DB_COLUMN_ID + "=?", new String[]{String.valueOf(poll.getId())});
    }

    private static void answerSurvey(Survey poll, Proposition p, int tag) {
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        ContentValues values = new ContentValues();
        String id = String.valueOf(poll.getId());
        String answer = p.getAnswer();
        values.put(DB_COLUMN_USERNAME, User.loggedUser.getUsername());
        values.put(DB_COLUMN_ID, id);
        values.put(DB_COLUMN_ANSWER, answer);
        values.put(DB_COLUMN_ORDER, tag);
        db.insert(DB_TABLE_SURVEY_ANSWER, null, values);
        db.close();
    }

    public static void addSurvey(Survey poll) {
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        ContentValues values = new ContentValues();
        String id = String.valueOf(poll.getId());
        String title = poll.getTitle();
        String description = poll.getDescriptionQ();
        String type = String.valueOf(poll.getType());
        User owner = poll.getOwner();
        String status = String.valueOf(poll.getStatus());
        values.put(DB_COLUMN_TITLE, title);
        values.put(DB_COLUMN_DESCRIPTION, description);
        values.put(DB_COLUMN_TYPE, type);
        values.put(DB_COLUMN_ID, id);
        values.put(DB_COLUMN_OWNER, owner.getUsername());
        values.put(DB_COLUMN_STATUS, status);
        db.insert(DB_TABLE_POLL, null, values);

        ContentValues values2 = new ContentValues();
        ArrayList<Proposition> propositions = poll.getPropostions();
        values2.put(DB_COLUMN_ID, poll.getId());
        for (int i = 0; i < propositions.size(); i++) {
            values2.put(DB_COLUMN_DATA, propositions.get(i).getAnswer());
        }
        db.insert(DB_TABLE_SURVEY, null, values2);

        db.close();
    }


    public static List<Integer> getIdpoll() {
        List<Integer> idpoll = new ArrayList<Integer>();
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        //Les resultats de la requete sont mis dans un "curseur"

        Cursor c = db.query("\"Poll_access\"", // La table
                new String[]{"idpoll"},
                null,  // Colonnes pour la clause WHERE
                null, // Valeurs pour la clause WHERE
                null, // ne pas grouper les lignes
                null, // ne pas filtrer par goupe de ligne
                null  // pas d'ordre
        );

        if (c.moveToFirst()) {
            for (int i = 0; i < c.getCount(); i++) {
                int s = c.getInt(c.getColumnIndexOrThrow("idpoll"));
                idpoll.add(s);
                c.moveToNext();
            }
        }
        c.close();
        return idpoll;
    }

    public static int nouvIdpoll(List<Integer> list) {
        int i = 0;
        i = getIdpoll().size() + 1;
        return i;
    }


    public static void Modify(int a, String prop) {
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        ContentValues newValues1 = new ContentValues();
        ContentValues newValues2 = new ContentValues();
        ContentValues newValues3 = new ContentValues();
        ContentValues newValues4 = new ContentValues();
        newValues1.put("points", (Survey.getListPointsProposition(prop, Sondage.idpoll).get(0)) + (6 - a));
        newValues2.put("ordre", a);
        newValues3.put("statut_principal", etats(Sondage.idpoll).get(0) + (1 / Sondage.listfriendclick.size()));
        newValues4.put("statut_particulier", 1);
        db.update("Survey", newValues1, "idpoll=? AND data_reponse=?", new String[]{String.valueOf(Sondage.idpoll), prop});
        db.update("Survey_Answer", newValues2, "username=? AND idpoll=? AND reponse=?", new String[]{User.loggedUser.getUsername(), String.valueOf(Sondage.idpoll), prop});
        db.update("Poll", newValues3, "idpoll=?", new String[]{String.valueOf(Sondage.idpoll)});
        db.update("Poll_access", newValues4, "idpoll=? AND username=?", new String[]{String.valueOf(Sondage.idpoll), User.loggedUser.getUsername()});
        db.close();
    }


    public static List<String> getListSondageTriee(int points, int idpoll) {

        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        //Les resultats de la requete sont mis dans un "curseur"
        List<String> list = new ArrayList<String>();
        Cursor c = db.query("Survey", // La table
                new String[]{"data_reponse"},
                "idpoll =? AND points=?",
                new String[]{String.valueOf(idpoll), String.valueOf(points)},
                null,
                null,
                null

        );


        if (c.moveToFirst()) {
            for (int i = 0; i < c.getCount(); i++) {
                String s = c.getString(c.getColumnIndexOrThrow("data_reponse"));
                list.add(s);
                c.moveToNext();
            }
        }
        c.close();
        db.close();
        return list;
    }

    public static ArrayList<String> getListSondage(int idpoll) {

        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        //Les resultats de la requete sont mis dans un "curseur"
        ArrayList<String> list = new ArrayList<String>();
        Cursor c = db.query("Survey", new String[]{"data_reponse"}, "idpoll =?", new String[]{String.valueOf(idpoll)}, null, null, null);
        c.moveToFirst();
        while (!c.isAfterLast()) {
            list.add(c.getString(0));
            c.moveToNext();
        }
        c.close();
        db.close();
        return list;
    }


    public static List<Integer> etats(int idpolll) {

        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        //Les resultats de la requete sont mis dans un "curseur"
        List<Integer> list = new ArrayList<Integer>();
        Cursor c = db.query("\"Poll_access\"", // La table
                new String[]{"statut_particulier"},
                "idpoll",
                new String[]{String.valueOf(idpolll)},
                null,
                null,
                null

        );
        if (c.moveToFirst()) {
            for (int i = 0; i < c.getCount(); i++) {
                int s = c.getInt(c.getColumnIndexOrThrow("statut_particulier"));
                list.add(s);
                c.moveToNext();
            }
        }
        c.close();
        return list;
    }

    public static int SondageFini(List<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) != 1) {
                return 0;
            }
        }
        return 1;
    }


    //Ici on va recuperer les points du poll pour l'incrémenter ou la diminuer.
    public static List<Double> getListPointsSondage(int idpoll) {
        List<Double> sondages = new ArrayList<Double>();
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        //Les resultats de la requete sont mis dans un "curseur"

        Cursor c = db.query("\"Survey\"", // La table
                new String[]{"points"},
                "idpoll", // Colonnes pour la clause WHERE
                new String[]{String.valueOf(idpoll)}, // Valeurs pour la clause WHERE
                null, // ne pas grouper les lignes
                null, // ne pas filtrer par goupe de ligne
                null  // pas d'ordre
        );

        if (c.moveToFirst()) {
            for (int i = 0; i < c.getCount(); i++) {
                double s = c.getDouble(c.getColumnIndexOrThrow("points"));
                sondages.add(s);
                c.moveToNext();
            }
        }
        c.close();
        return sondages;
    }

    //Ici on va recuperer les points de la proposition pour l'incrémenter ou la diminuer.
    public static List<Integer> getListPointsProposition(String proposition, int idpoll) {
        List<Integer> sondages = new ArrayList<Integer>();
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        //Les resultats de la requete sont mis dans un "curseur"

        Cursor c = db.query("\"Survey\"", // La table
                new String[]{"points"},
                "reponse=? AND idpoll", // Colonnes pour la clause WHERE
                new String[]{proposition, String.valueOf(idpoll)}, // Valeurs pour la clause WHERE
                null, // ne pas grouper les lignes
                null, // ne pas filtrer par goupe de ligne
                null  // pas d'ordre
        );

        if (c.moveToFirst()) {
            for (int i = 0; i < c.getCount(); i++) {
                int s = c.getInt(c.getColumnIndexOrThrow("points"));
                sondages.add(s);
                c.moveToNext();
            }
        }
        c.close();
        return sondages;
    }

    public static List<String> moyenne(List<Double> points) {
        double Somme = 0;
        List<String> pourcent = new ArrayList<String>();
        for (int i = 0; i < points.size(); i++) {
            Somme = Somme + points.get(i);
        }
        for (int i = 0; i < points.size(); i++) {
            Double pourc = (points.get(i) / Somme) * 100;
            pourcent.add(pourc.toString());
        }
        return pourcent;
    }

    public static ArrayList<Survey> getSurvey() {
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        ArrayList<Survey> surveys = new ArrayList<>();

        String[] colonnes = {"idpoll"};
        Cursor cursor = db.query("Poll_access", colonnes, "username=? AND statut_particulier=?", new String[]{User.loggedUser.getUsername(), String.valueOf(0)}, null, null, null);
        cursor.moveToFirst();
        ArrayList<Integer> idpoll = new ArrayList<>();
        while (!cursor.isAfterLast()) {
            idpoll.add(Integer.valueOf(cursor.getString(0)));
            Log.i("display", (cursor.getString(0)));
            cursor.moveToNext();
        }
        cursor.close();
        Log.i("display size", String.valueOf(idpoll.size()));
        Log.i("test", String.valueOf(0));
        db.close();
        for (int i = 0; i < idpoll.size(); i++) {
            db = MySQLiteHelper.get().getReadableDatabase();
            String idQuestion = null;
            String description = null;
            String[] colonnes2 = {"idquestion", "description_question"};
            Log.i("display", idpoll.get(i).toString());
            cursor = db.query("Question_list", colonnes2, "idpoll=?", new String[]{idpoll.get(i).toString()}, null, null, null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                idQuestion = cursor.getString(0);
                Log.i("test", "idQuestion");
                description = cursor.getString(1);
                cursor.moveToNext();
            }
            cursor.close();
            if (idQuestion != null) {

                String[] colonnes3 = {"texte"};
                cursor = db.query("Questionnaire_and_Advice", colonnes3, "idquestion=?", new String[]{idQuestion}, null, null, null);
                cursor.moveToFirst();
                ArrayList<Proposition> proposisiton = new ArrayList<>();
                for (int j = 0; !cursor.isAfterLast(); j++) {
                    Log.i("test", "proposition : " + j);
                    proposisiton.add(new Proposition(cursor.getString(0)));
                    cursor.moveToNext();
                }
                cursor.close();

                User owner = null;
                String title = null;
                Log.i("display", String.valueOf(idpoll));
                String[] colonnes4 = {"username_proprietaire","titre"};
                cursor = db.query("Poll", colonnes4, "idpoll=? AND status_principal=? AND types=?", new String[]{String.valueOf(idpoll.get(i)), String.valueOf(0), "s"}, null, null, null);
                cursor.moveToFirst();
                for (int j = 0; !cursor.isAfterLast(); j++) {
                    Log.i("test", "owner");
                    owner = User.getUser(cursor.getString(0));
                    title = cursor.getString(0);
                    cursor.moveToNext();
                }
                cursor.close();
                Log.i("ids", String.valueOf(idpoll.get(i)));

                if (owner != null) {
                    surveys.add(new Survey(idpoll.get(i), title, null, 'a', owner, proposisiton));
                }
            }
            db.close();
        }
        return surveys;
    }
}