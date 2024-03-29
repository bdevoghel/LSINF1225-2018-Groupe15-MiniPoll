package p.poll.activity;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import p.poll.MySQLiteHelper;
import p.poll.R;
import p.poll.model.Poll;
import p.poll.model.Survey;
import p.poll.model.User;

/**
 * Created by Nicolas on 04/05/2018.
 */


/**
 * Created by Nicolas on 04/05/2018.
 */

public class Sondageprevue extends Activity {
    public static ContentResolver content;
    ListView list;
    String[] listadd;
    CustomSondagePreviewAdd listAdapter2;
    public static List<View> liste;
    public static List<String> listenumero;
    public static List<String> listedescription;
    public static String[] listenumeroliste;
    public static String[] listedescriptionliste;

    String[] web;
    int size;
    String[] phrase;
    String[] web2 = {
            "Proposition1", // First == celui choisi
            "Proposition2",
            "Proposition3",
            "Proposition4",
            "Proposition5",
            "Proposition6"
    };


    /*
    String[] phrase = {
            "Texte Proposition1",
            "Texte Proposition2",
            "Texte Proposition3",
            "Texte Proposition4",
            "Texte Proposition5",
            "Texte Proposition6"
    };
    */
    private int idpoll;
    private ListView mListView;
    Button bouton;
    public static List<String> pourcent = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sondage_reponse);
        idpoll= Poll.setId();
        web = new String[Sondage.listPropositions.size()];
        phrase = Sondage.listPropositions.toArray(new String[Sondage.listproposition.size()]);
        size = Sondage.listPropositions.size();
        for(int i =0; i<size ; i++)
        {
            web[i] = web2[i];
        }
        content = getContentResolver();
        liste = new ArrayList<View>();
        listenumero = new ArrayList<String>();
        listedescription = new ArrayList<String>();
        for (int i = 0; i < 7; i++) {
            liste.add(null);
        }
        CustomSondagePrevue listAdapter = new
                CustomSondagePrevue(Sondageprevue.this, web, phrase);
        TextView titre = (TextView) findViewById(R.id.Title);
        titre.setText(Sondage.title);
        bouton = (Button) findViewById(R.id.button);
        bouton.setText("Sauvegarder");
        list = (ListView) findViewById(R.id.listView2);
        list.setAdapter(listAdapter);
        mListView = (ListView) findViewById(R.id.listView1);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                String propo = ((TextView) view.findViewById(R.id.txt)).getText().toString();
                String descr = ((TextView) view.findViewById(R.id.txt2)).getText().toString();
                if (listenumero.contains(propo)) {
                    view.setBackgroundColor(getResources().getColor(android.R.color.white));
                    listenumero.remove(propo);
                    listedescription.remove(descr);
                    listenumeroliste = listenumero.toArray(new String[listenumero.size()]);
                    listedescriptionliste = listedescription.toArray(new String[listedescription.size()]);
                    listAdapter2 = new
                            CustomSondagePreviewAdd(Sondageprevue.this, listenumeroliste);
                    mListView.setAdapter(listAdapter2);
                    Toast.makeText(Sondageprevue.this, "You Clicked at " + web[+position] + " value = ", Toast.LENGTH_SHORT).show();

                } else {
                    view.setBackgroundColor(getResources().getColor(android.R.color.holo_green_dark));
                    listenumero.add(propo);
                    listedescription.add(descr);
                    listenumeroliste = listenumero.toArray(new String[listenumero.size()]);
                    listedescriptionliste = listedescription.toArray(new String[listedescription.size()]);
                    listAdapter2 = new
                    CustomSondagePreviewAdd(Sondageprevue.this, listenumeroliste);
                    mListView.setAdapter(listAdapter2);
                    Toast.makeText(Sondageprevue.this, "You Clicked at " + listenumeroliste[listenumero.size() - 1] + " value = ", Toast.LENGTH_SHORT).show();
                }
            }
        });

        bouton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int m = 0;
                while (m<Sondage.listproposition.size()){

                    ContentValues newValues = new ContentValues();
                    View vv = Sondage.listproposition.get(m);
                    if (vv != null ) {
                        EditText edit = (EditText) vv.findViewById(R.id.editText6);
                        String prop = edit.getText().toString();
                        if (prop.compareTo("") != 0) {
                            Sondage.p ++;
                            newValues.put("idpoll", idpoll);
                            newValues.put("data_reponse", prop);
                            newValues.put("point",0);

                            SQLiteDatabase db = MySQLiteHelper.get().getWritableDatabase();
                            db.insert("Survey", null, newValues);
                            db.close();
                        }
                    }
                    m++;
                }

                for (int i = 0; i < Sondage.listfriendclick.size(); i++){
                    for (int k = 0; k < 7; k++){
                        ContentValues newValues1 = new ContentValues();
                        View vv = Sondage.listproposition.get(k);
                        if (vv != null ) {
                            EditText edit = (EditText) vv.findViewById(R.id.editText6);
                            String prop = edit.getText().toString();
                            if (prop.compareTo("") != 0) {
                                prop = edit.getText().toString();
                                newValues1.put("idpoll", idpoll);
                                newValues1.put("username", Sondage.listfriendclick.get(i));
                                newValues1.put("reponse", prop);
                                newValues1.put("ordre", 0);


                                SQLiteDatabase db = MySQLiteHelper.get().getWritableDatabase();
                                db.insert("Survey_Answer", null, newValues1);
                                db.close();
                            }
                        }
                    }
                }

                int x = 0;
                while (x < Sondage.listfriendclick.size()) {
                    ContentValues newValues1 = new ContentValues();
                    newValues1.put("username", Sondage.listfriendclick.get(x));
                    newValues1.put("idpoll", idpoll);
                    newValues1.put("statut_particulier", 0);
                    SQLiteDatabase db = MySQLiteHelper.get().getWritableDatabase();
                    db.insert("Poll_access", null, newValues1);
                    db.close();
                    x++;
                }

                ContentValues newValues2 = new ContentValues();

                newValues2.put("username_proprietaire", User.loggedUser.getUsername());
                newValues2.put("idpoll", idpoll);
                newValues2.put("titre", Sondage.title);
                newValues2.put("status_principal", 0);
                newValues2.put("types","s");
                SQLiteDatabase db1 = MySQLiteHelper.get().getWritableDatabase();
                db1.insert("Poll", null, newValues2);
                db1.close();

                db1 = MySQLiteHelper.get().getWritableDatabase();
                for(int i=0;i<Sondage.listfriendclick.size();i++) {
                    ContentValues values = new ContentValues();
                    values.put("username",Sondage.listfriendclick.get(i));
                    values.put("etat", String.valueOf(0));
                    values.put("username_notif", User.loggedUser.getUsername());
                    values.put("message", User.loggedUser.getFirstName() + " " + User.loggedUser.getLastName() + " invited you to answer his survey!");
                    values.put("poll_notif", String.valueOf(idpoll));
                    db1.insert("Notification", null, values);
                }
                db1.close();

                Intent intent = new Intent(getApplicationContext(), Menupoll.class);
                startActivity(intent);

            }

        });
    }
}
