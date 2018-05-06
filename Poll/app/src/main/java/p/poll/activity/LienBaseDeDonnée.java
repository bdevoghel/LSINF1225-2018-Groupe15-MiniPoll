package p.poll.activity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Antoine on 05-05-18.
 */
// va chercher tous les sondages de l'utilisateur
public class LienBaseDeDonnée {
    //
    public List<String> getListSondage(String identifiant) {
        List<String> sondages = new ArrayList<String>();
        SQLiteDatabase db = getReadableDatabase();
        //Les resultats de la requete sont mis dans un "curseur"
        Cursor c = db.query("\"poll\"", // La table
                "\"titre\"",
                new String[]{"\"id\"", "\"type\""},
                new String[]{identifiant, "\"S\""},
                null,
                null,
                );
        if (c.moveToFirst()) {
            for (int i = 0; i < c.getCount(); i++) {
                String s = c.getString(c.getColumnIndexOrThrow("titre"));
                sondages.add(s);
                c.moveToNext();
            }
        }
        c.close();
        return sondages;
    }
    //Ici on va recuperer les propositions du sondage

    public List<String> getListProposition(String PollIdentifiant){
    List<String> proposition = new ArrayList<String>();
    SQLiteDatabase db = getReadableDatabase();
    //Les resultats de la requete sont mis dans un "curseur"
    Cursor c = db.query("\"sondage\"", // La table
            "\"data_reponse\"",
            "\"idpoll\"",
            PollIdentifiant,
            null,
            null,
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
        public int getListProposition(String proposition){
        List<int> sondages = new ArrayList<int>();
        SQLiteDatabase db = getReadableDatabase();
        //Les resultats de la requete sont mis dans un "curseur"
        Cursor c = db.query("\"reponse_sondage\"", // La table
        "\"ordre\"",
        "\"reponse\"",
        proposition,
        null,
        null,
        );
        if (c.moveToFirst()) {
        for (int i = 0; i < c.getCount(); i++) {
        String s = c.getString(c.getColumnIndexOrThrow("ordre"));
        sondages.add(s);
        c.moveToNext();
        }
        }
        c.close();
        return sondages;
        }
}

// Quand on place une proposition dans la liste
    // A la première place
public int Increment5 (String proposition){
    int p = getListProposition(String proposition)[0];
    p = p+5;
    ContentValues newValues = new ContentValues();
    newValues.put("ordre", p);

    baseCorrect.sql.update("reponse_sondage", newValues, "id = "+proposition, null);
}
    // A la première place
    public int Increment5 (String proposition){
        int p = getListProposition(String proposition)[0];
        p = p+5;
        ContentValues newValues = new ContentValues();
        newValues.put("ordre", p);

        baseCorrect.sql.update("reponse_sondage", newValues, "id = "+proposition, null);
    }

    // A la première place
    public int Increment1 (String proposition){
        int p = getListProposition(String proposition)[0];
        p = p+5;
        ContentValues newValues = new ContentValues();
        newValues.put("ordre", p);

        baseCorrect.sql.update("reponse_sondage", newValues, "id = "+proposition, null);
    }

    // A la deuxième place
    public int Increment2 (String proposition){
        int p = getListProposition(String proposition)[0];
        p = p+4;
        ContentValues newValues = new ContentValues();
        newValues.put("ordre", p);

        baseCorrect.sql.update("reponse_sondage", newValues, "id = "+proposition, null);
    }

    // A la troisième place
    public int Increment3 (String proposition){
        int p = getListProposition(String proposition)[0];
        p = p+3;
        ContentValues newValues = new ContentValues();
        newValues.put("ordre", p);

        baseCorrect.sql.update("reponse_sondage", newValues, "id = "+proposition, null);
    }

    // A la quatrième place
    public int Increment4 (String proposition){
        int p = getListProposition(String proposition)[0];
        p = p+2;
        ContentValues newValues = new ContentValues();
        newValues.put("ordre", p);

        baseCorrect.sql.update("reponse_sondage", newValues, "id = "+proposition, null);
    }

    // A la cinquième place
    public int Increment5 (String proposition){
        int p = getListProposition(String proposition)[0];
        p = p+1;
        ContentValues newValues = new ContentValues();
        newValues.put("ordre", p);

        baseCorrect.sql.update("reponse_sondage", newValues, "id = "+proposition, null);
    }

    // A la sixième  place on ne modifie rien





// retourne la proposition qui a eu le plus de succes

//retourne la liste de tous les ordres du poll
public List<String> getListOrdre (String pollIdentifiant){
    List<String> ordre = new ArrayList<String>();
    SQLiteDatabase db = getReadableDatabase();
    //Les resultats de la requete sont mis dans un "curseur"
    Cursor c = db.query("\"sondage\"", // La table
            "\"ordre\"",
            "\"idpoll\"",
            pollIdentifiant,
            null,
            null,
            );
    if (c.moveToFirst()) {
        for (int i = 0; i < c.getCount(); i++) {
            String s = c.getString(c.getColumnIndexOrThrow("ordre"));
            ordre.add(s);
            c.moveToNext();
        }
    }
    c.close();
    return ordre;
}

// On retourne le plus grand nombre
public int premiers (List<int> tab){
    int max = 0;
    for (int i = 0; i < tab.size(); i++){
        if (tab[i]>max){
            max = tab[i];
        }
    }
    return max;
}

// On va retourner le/les premiere(s) reponses du sondage
 public List<int> getResultat (String pollIdentifiant){
    int max = premiers(getListOrdre(pollIdentifiant));

    List<String> resultat = new ArrayList<String>();
     SQLiteDatabase db = getReadableDatabase();
     //Les resultats de la requete sont mis dans un "curseur"
     Cursor c = db.query("\"sondage\"", // La table
             "\"ordre\"",
             max,
             null,
             null,
             null,
             );
     if (c.moveToFirst()) {
         for (int i = 0; i < c.getCount(); i++) {
             String s = c.getString(c.getColumnIndexOrThrow("ordre"));
             resultat.add(s);
             c.moveToNext();
         }
     }
     c.close();
     return resultat;
 }

