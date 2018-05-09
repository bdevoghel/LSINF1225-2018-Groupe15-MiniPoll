package p.poll.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import p.poll.R;
import p.poll.model.User;

/**
 * Created by Nicolas on 04/05/2018.
 */

public class SondageReponse extends Activity {
    ListView list;
    String[] listadd;
    CustomSondageAnswerAdd listAdapter2;
    public static List<View> liste;
    public static List<String> listenumero;
    public static List<String> listedescription;
    public static String[] listenumeroliste;
    public static String[] listedescriptionliste;
    String[] web = {
            "Proposition1",
            "Proposition2",
            "Proposition3",
            "Proposition4",
            "Proposition5",
            "Proposition6"
    } ;
    String[] phrase = {
            "Texte Proposition1",
            "Texte Proposition2",
            "Texte Proposition3",
            "Texte Proposition4",
            "Texte Proposition5",
            "Texte Proposition6"
    } ;

    private ListView mListView;
    Button bouton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sondage_reponse);
        liste = new ArrayList<View>();
        listenumero = new ArrayList<String>();
        listedescription = new ArrayList<String>();
        for(int i=0;i<7;i++)
        {
            liste.add(null);
        }
        CustomSondageAnswer listAdapter = new
                CustomSondageAnswer(SondageReponse.this, web, phrase);
        bouton = (Button) findViewById(R.id.button);
        list = (ListView) findViewById(R.id.listView2);
        list.setAdapter(listAdapter);
        mListView = (ListView) findViewById(R.id.listView1);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                String propo = ((TextView) view.findViewById(R.id.txt)).getText().toString();
                String descr = ((TextView) view.findViewById(R.id.txt2)).getText().toString();
                if (listenumero.contains(propo)){
                    view.setBackgroundColor(getResources().getColor(android.R.color.white));
                    listenumero.remove(propo);
                    listedescription.remove(descr);
                    listenumeroliste = listenumero.toArray(new String[listenumero.size()]);
                    listedescriptionliste = listedescription.toArray(new String[listedescription.size()]);
                    listAdapter2 = new
                            CustomSondageAnswerAdd(SondageReponse.this, listenumeroliste);
                    mListView.setAdapter(listAdapter2);
                    Toast.makeText(SondageReponse.this, "You Clicked at " + web[+position] + " value = ", Toast.LENGTH_SHORT).show();

                }
                else
                {
                    view.setBackgroundColor(getResources().getColor(android.R.color.holo_green_dark));
                    listenumero.add(propo);
                    listedescription.add(descr);
                    listenumeroliste = listenumero.toArray(new String[listenumero.size()]);
                    listedescriptionliste = listedescription.toArray(new String[listedescription.size()]);
                    listAdapter2 = new
                            CustomSondageAnswerAdd(SondageReponse.this, listenumeroliste);
                    mListView.setAdapter(listAdapter2);
                    Toast.makeText(SondageReponse.this, "You Clicked at " + listenumeroliste[listenumero.size()-1] + " value = ", Toast.LENGTH_SHORT).show();
                }
            }
        });

        bouton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(listenumero.size()!= web.length)
                {
                    Toast.makeText(p.poll.activity.SondageReponse.this,"Aucun ami sélectionné",
                            Toast.LENGTH_SHORT).show();
                }
                else
                {
                    // Go to Sondage Prevue
                }
            }
        });
    }
/*
        List<String> polls = genererSondage();

        PollAdapterSondageReponse adapter = new PollAdapterSondageReponse(SondageReponse.this, polls);
        mListView.setAdapter(adapter);
        mListView.setClickable(true);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                Object o = mListView.getItemAtPosition(position);

                String j = (String) o;
                Toast.makeText(SondageReponse.this, j,
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
    */

    /*
    private List<String> genererSondage() {
    LienSondageDatabase lien = new LienSondageDatabase();
    return lien.getListSondage(User.loggedUser.getUsername());
    }
    */

}
