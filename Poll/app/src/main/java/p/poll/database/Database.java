package p.poll.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;



/**
 * @author Damien Mercier
 * @version 1
 * @see <a href="http://d.android.com/reference/android/database/sqlite/SQLiteOpenHelper.html">SQLiteOpenHelper</a>
 */
public class Database extends SQLiteOpenHelper {

    /**
     * Nom du fichier de la base de donnees.
     */
    private static final String DATABASE_NAME = "database.sqlite";

    /**
     * Version de la base de donnees (à incrementer en cas de modification de celle-ci afin que la
     * methode onUpgrade soit appelee).
     *
     * @note Le numero de version doit changer de maniere monotone.
     */
    private static final int DATABASE_VERSION = 1;

    /**
     * Le contexte de l'application
     */
    private static Context context;

    /**
     * Constructeur. Instancie l'utilitaire de gestion de la base de donnees.
     *
     * @param context Contexte de l'application.
     */
    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }
    /**
     * Methode d'initialisation appelee lors de la creation de la base de donnees.
     * Ici on y cree les tables de la base de donnees et les remplit.
     *
     * @param db Base de donnees à initialiser.
     * @param db Base de donnees à initialiser
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS \"languages\";");
        db.execSQL("CREATE TABLE \"languages\" (\"l_id\" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL , \"l_name\" TEXT);");
        db.execSQL("DROP TABLE IF EXISTS \"languages\";");
        db.execSQL("CREATE TABLE \"languages\" (\"l_id\" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL , \"l_name\" TEXT);");

        db.execSQL("INSERT INTO \"languages\" VALUES(1,'java');");
        db.execSQL("INSERT INTO \"languages\" VALUES(2,'scala');");
        db.execSQL("INSERT INTO \"languages\" VALUES(3,'prolog');");
        db.execSQL("INSERT INTO \"languages\" VALUES(4,'smaltalk');");
    }

    /**
     * Ouvre la base de donnees en ecriture
     */
    public boolean open() {
        try {
            getWritableDatabase();
            return true;
        } catch(Throwable t) {
            return false;
        }
    }

    /**
     * Supprime la table dans la base de donnees.
     *
     * @param db Base de donnees.
     *
     * @post La tables de la base de donnees passee en argument est effacee.
     */
    private void deleteDatabase(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS \"languages\";");
    }

    /**
     * Methode de mise à jour lors du changement de version de la base de donnees.
     *
     * @param db         Base de donnees à mettre à jour.
     * @param oldVersion Numero de l'ancienne version.
     * @param newVersion Numero de la nouvelle version.
     * @pre La base de donnees est dans la version oldVersion.
     * @post La base de donnees a ete mise à jour vers la version newVersion.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        deleteDatabase(db);
        onCreate(db);
    }
    /**
     * Renvoie la liste les langages contenus dans la base de donnees
     * @return null si requete sans resultat, une liste de strings contenant
     * les langages presents dans la base de donnees.
     */
    public List<String> getLanguages() {
        List<String> languages = new ArrayList<String>();
        SQLiteDatabase db = getReadableDatabase();
        // Les resultats de la requête sont mis dans un "curseur"
        Cursor c = db.query("\"languages\"", // La table
                new String[]{"\"l_id\"", "\"l_name\""},
                null, // Colonnes pour la clause WHERE
                null, // Valeurs pour la clause WHERE
                null, // ne pas grouper les lignes
                null, // ne pas filtrer par goupe de ligne
                null  // pas d'ordre
        );
        if (c.moveToFirst()) {
            for (int i = 0; i < c.getCount(); i++) {
                String s = c.getString(c.getColumnIndexOrThrow("l_name"));
                languages.add(s);
                c.moveToNext();
            }
        }
        c.close();
        return languages;
    }
}