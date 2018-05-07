package p.poll.activity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import p.poll.MySQLiteHelper;

/**
 * Created by Antoine on 05-05-18.
 */
// va chercher tous les sondages de l'utilisateur
public class LienSondageDatabase {
    //
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

    public List<String> getListProposition (String PollIdentifiant){
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
            for (int i = 0; i < c.getCount(); i++) {
                String s = c.getString(c.getColumnIndexOrThrow("data_reponse"));
                proposition.add(s);
                c.moveToNext();
            }
        }
        c.close();
        return proposition;
    }

    //Ici on va recuperer la valeur de la proposition pour l'incrémenter
    public List<Integer> getListValeurProposition(String proposition){
        List<Integer> sondages = new ArrayList<Integer>();
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        //Les resultats de la requete sont mis dans un "curseur"

        Cursor c = db.query("\"languages\"", // La table
                new String[]{"ordre"},
                "reponse=?", // Colonnes pour la clause WHERE
                new String[]{proposition}, // Valeurs pour la clause WHERE
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


    // Quand on place une proposition dans la liste
    // A la première place


/*
    // A la première place
    public void Increment1 (String proposition){
        int p = getListProposition(String proposition)[0];
        p = p+5;
        ContentValues newValues = new ContentValues();
        newValues.put("ordre", p);

        MySQLiteHelper.get().update("reponse_sondage", newValues, "id = "+proposition, null);
    }

    // A la deuxième place
    public void Increment2 (String proposition){
        int p = getListProposition(String proposition)[0];
        p = p+4;
        ContentValues newValues = new ContentValues();
        newValues.put("ordre", p);

        MySQLiteHelper.get().update("reponse_sondage", newValues, "id = "+proposition, null);
    }

    // A la troisième place
    public void Increment3 (String proposition){
        int p = getListProposition(String proposition)[0];
        p = p+3;
        ContentValues newValues = new ContentValues();
        newValues.put("ordre", p);

        MySQLiteHelper.get().update("reponse_sondage", newValues, "id = "+proposition, null);
    }

    // A la quatrième place
    public void Increment4 (String proposition){
        int p = getListProposition(String proposition)[0];
        p = p+2;
        ContentValues newValues = new ContentValues();
        newValues.put("ordre", p);

        MySQLiteHelper.get().update("reponse_sondage", newValues, "id = "+proposition, null);
    }

    // A la cinquième place
    public void Increment5 (String proposition){
        int p = getListProposition(String proposition)[0];
        p = p+1;
        ContentValues newValues = new ContentValues();
        newValues.put("ordre", p);

        MySQLiteHelper.get().update("reponse_sondage", newValues, "id = "+proposition, null);
    }

    // A la sixième  place on ne modifie rien

*/



// retourne la proposition qui a eu le plus de succes

    //retourne la liste de tous les ordres du poll
    public List<Integer> getListOrdre (String pollIdentifiant){
        List<Integer> ordre = new ArrayList<Integer>();
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        //Les resultats de la requete sont mis dans un "curseur"

        Cursor c = db.query("\"sondage\"", // La table
                new String[]{"\"ordre\""},
                "\"idpoll=?\"",
                new String[]{pollIdentifiant},
                null,
                null,
                null
                );
        if (c.moveToFirst()) {
            for (int i = 0; i < c.getCount(); i++) {
                int s = c.getInt(c.getColumnIndexOrThrow("ordre"));
                ordre.add(s);
                c.moveToNext();
            }
        }
        c.close();
        return ordre;
    }

    // On retourne le plus grand nombre
    public int premiers (List<Integer> tab){
        int max = 0;
        for (int i = 0; i < tab.size(); i++){
            if (tab.get(i)>max){
                max = tab.get(i);
            }
        }
        return max;
    }

    // On va retourner le/les premiere(s) reponses du sondage
    public List<Integer> getGagnant (String pollIdentifiant){
        int max = premiers(getListOrdre(pollIdentifiant));

        List<Integer> resultat = new ArrayList<Integer>();
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        //Les resultats de la requete sont mis dans un "curseur"
        Cursor c = db.query("sondage", // La table
                new String[]{"titre"},
                "ordre=? AND idpoll",
                new String[] {String.valueOf(max), pollIdentifiant},
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