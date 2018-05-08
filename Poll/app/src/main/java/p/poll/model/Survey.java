package p.poll.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import p.poll.MySQLiteHelper;
import p.poll.activity.NewPoll;
import p.poll.activity.Sondage;

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

    public List<String> getListSondage(String identifiant) {
        List<String> sondages = new ArrayList<String>();
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        //Les resultats de la requete sont mis dans un "curseur"

        Cursor c = db.query("\"poll\"", // La table
                new String[]{"identifiant_proprietaire", "titre"},
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
                sondages.add(s);
                sondages.add(x);
                c.moveToNext();
            }
        }
        c.close();
        return sondages;
    }

    //Ici on va recuperer les propositions du sondage pour que l'utilisateur puisse jouer
    public List<String> getListProposition(String PollIdentifiant) {
        List<String> proposition = new ArrayList<String>();
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        //Les resultats de la requete sont mis dans un "curseur"
        Cursor c = db.query("\"sondage\"", // La table
                new String[]{"\"data_reponse\""},
                "\"idpoll=?\"",
                new String[]{PollIdentifiant},
                null,
                null,
                null
        );
        if (c.moveToFirst()) {
            for (int i = 0; i < 6; i++) {
                if (i >= c.getCount()) {
                    proposition.add(null);
                    c.moveToNext();
                }
                String s = c.getString(c.getColumnIndexOrThrow("data_reponse"));
                proposition.add(s);
                c.moveToNext();
            }
        }
        c.close();
        return proposition;
    }

    //Ici on va recuperer les points de la proposition pour l'incrémenter ou la diminuer.
    public List<Integer> getListPointsProposition(String proposition, String user, String idpoll) {
        List<Integer> sondages = new ArrayList<Integer>();
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        //Les resultats de la requete sont mis dans un "curseur"

        Cursor c = db.query("\"languages\"", // La table
                new String[]{"points"},
                "reponse=? AND identifaint=? AND idpoll", // Colonnes pour la clause WHERE
                new String[]{proposition, user, idpoll}, // Valeurs pour la clause WHERE
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

    // retourne l'ordre d'une proposition
    public List<Integer> getListOrdreProposition(String proposition, String user, String idpoll) {
        List<Integer> sondages = new ArrayList<Integer>();
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        //Les resultats de la requete sont mis dans un "curseur"

        Cursor c = db.query("\"languages\"", // La table
                new String[]{"ordre"},
                "reponse=? AND identifaint=? AND idpoll", // Colonnes pour la clause WHERE
                new String[]{proposition, user, idpoll}, // Valeurs pour la clause WHERE
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


// retourne la proposition qui a eu le plus de succes

    //retourne la liste de tous les points du poll dans l'ordre dans lequel on a mis les propositions
    public List<Integer> getListPoints(String pollIdentifiant) {
        List<Integer> points = new ArrayList<Integer>();
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        //Les resultats de la requete sont mis dans un "curseur"

        Cursor c = db.query("\"sondage\"", // La table
                new String[]{"\"points\""},
                "\"idpoll=?\"",
                new String[]{pollIdentifiant},
                null,
                null,
                null
        );
        if (c.moveToFirst()) {
            for (int i = 0; i < c.getCount(); i++) {
                int s = c.getInt(c.getColumnIndexOrThrow("points"));
                points.add(s);
                c.moveToNext();
            }
        }
        c.close();
        return points;
    }

    // On retourne le plus grand nombre
    public int premiers(List<Integer> tab) {
        int max = 0;
        for (int i = 0; i < tab.size(); i++) {
            if (tab.get(i) > max) {
                max = tab.get(i);
            }
        }
        return max;
    }

    // On va retourner le/les premiere(s) reponses du sondage
    public List<Integer> getGagnant(String pollIdentifiant) {
        int max = premiers(getListPoints(pollIdentifiant));

        List<Integer> resultat = new ArrayList<Integer>();
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        //Les resultats de la requete sont mis dans un "curseur"
        Cursor c = db.query("sondage", // La table
                new String[]{"titre"},
                "points=? AND idpoll",
                new String[]{String.valueOf(max), pollIdentifiant},
                null,
                null,
                null
        );

        if (c.moveToFirst()) {
            for (int i = 0; i < c.getCount(); i++) {
                int s = c.getInt(c.getColumnIndexOrThrow("ordre"));
                resultat.add(s);
                c.moveToNext();
            }
        }
        c.close();
        return resultat;

    }





/*

    //Mainteneant si on veut créer un poll
// Avec des Texte Vieuw

    //On incrémente un int pour l'idPoll
    int i = 1;
    //Description du Sondage : Titre
    public void CreateSondage (String titreFromVieuw){
        db.insert ("poll", "titre", titreFromVieuw);
        MySQLiteHelper.get().insert ("poll", "type", "S");
        MySQLiteHelper.get().insert ("poll", "description", "Répondez ci-dessous");

    }
    */

/*//Initialiser la deadline

    public void InitialisationDeadLine (bouton DeadLine){
        MySQLiteHelper.get().insert ("poll", "deadline", "bouton jour + 7*bouton semaines + 30*bouton mois ");
    }

    //Initialisation des propostions
    public void InitialisationDeadLine (TextView Proposition1){
        MySQLiteHelper.insert ("sondage", "data_reponse", Proposition1);
        MySQLiteHelper.get().insert ("sondage", "idpoll", i);
    }
    public void InitialisationDeadLine (TextView Proposition2){
        MySQLiteHelper.get().insert ("sondage", "data_reponse", Proposition2);
        MySQLiteHelper.get().insert ("sondage", "idpoll", i);
    }
    public void InitialisationDeadLine (TextView Proposition3){
        MySQLiteHelper.get().insert ("sondage", "data_reponse", Proposition3);
        MySQLiteHelper.get().insert ("sondage", "idpoll", i);
    }
    public void InitialisationDeadLine (TextView Proposition4){
        MySQLiteHelper.get().insert ("sondage", "data_reponse", Proposition4);
        MySQLiteHelper.get().insert ("sondage", "idpoll", i);
    }
    public void InitialisationDeadLine (TextView Proposition5){
        MySQLiteHelper.get().insert ("sondage", "data_reponse", Proposition5);
        MySQLiteHelper.get().insert ("sondage", "idpoll", i);
    }
    public void InitialisationDeadLine (TextView Proposition6){
        MySQLiteHelper.get().insert ("sondage", "data_reponse", Proposition6);
        MySQLiteHelper.get().insert ("sondage", "idpoll", i);
    }
  */
//POur réaliser cette fonction, on doit pouvoir connaitre le nombre de points de cette proposition

    // On modifie les points et les positions si le drapeau est false

    public void ModifyF(String proposition) {
        c = listePos.get(0);
        listePos.remove(0);
        b = getListPointsProposition(proposition, User.loggedUser.getUsername(), NewPoll.idpoll).get(0) + (6 - c);
        ContentValues newValues1 = new ContentValues();
        ContentValues newValues2 = new ContentValues();
        newValues1.put("points", b);
        newValues1.put("ordre", c);

        MySQLiteHelper.get().getReadableDatabase().update("reponse_sondage", newValues1, "id = ?", new String[]{proposition});
        MySQLiteHelper.get().getReadableDatabase().update("reponse_sondage", newValues2, "id = ?", new String[]{proposition});
    }
    // On modifie les points et les positions si le drapeau est true

    public void ModifyT(String proposition) {
        c = getListOrdreProposition(proposition, User.loggedUser.getUsername(), NewPoll.idpoll).get(0);
        b = getListPointsProposition(proposition, User.loggedUser.getUsername(), NewPoll.idpoll).get(0) - (6 - c);
        listePos.set(0, c);
        ContentValues newValues1 = new ContentValues();
        ContentValues newValues2 = new ContentValues();
        newValues1.put("points", b);
        newValues2.put("ordre", 0);
        MySQLiteHelper.get().getReadableDatabase().update("reponse_sondage", newValues1, "id = ?", new String[]{proposition});
        MySQLiteHelper.get().getReadableDatabase().update("reponse_sondage", newValues2, "id = ?", new String[]{proposition});
    }


    //technique pour le poll, on met des drapeaux pour voir si on a déjà cliqué dessus.
    //On fait une liste numérotée de 1 à 6 pour mettre leur positions
    //On met une variable globale qu'on va modifier à chaque fois
    int a = 0; //sert à avoir l'inderx de la proposition dans le tableau des propositions
    int b = 0; //sert pour calculer les points.
    int c = 0; //sert à avoir la position
    List<Integer> listePos = new ArrayList<Integer>();

    public void initList() {
        for (int i = 1; i < 7; i++) {
            listePos.add(i);
        }
    }

    public void Classer(String proposition) {
        a = Sondage.ListePropositions.indexOf(proposition);
        if (Sondage.flag[a] == false) {
            ModifyF(proposition);
            Sondage.flag[a] = true;
        } else {
            ModifyT(proposition);
            Sondage.flag[a] = false;
        }
    }

    // quand un joueur appuie sur valider;
    public void valider() {
        Sondage.total++;
    }
    //quand on ajoute un ami dans le sondage
    public void ajouterAmi(){
        Sondage.joueurs ++;
    }
}
//il faut faire le même systeme que pour incrémenter les poll avec les flags.


