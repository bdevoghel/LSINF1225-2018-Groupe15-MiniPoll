package p.poll.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
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

import p.poll.R;
import p.poll.model.Notification;
import p.poll.model.Proposition;
import p.poll.model.Survey;

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
    public static Survey current;
    private Notification notification;
    String[] web2 = {
            "Proposition1", // First == celui choisi
            "Proposition2",
            "Proposition3",
            "Proposition4",
            "Proposition5",
            "Proposition6"
    };
    String[] web;
    List<String> proposition;
    List<String> proptitre;
    String[] phrase;
    String title;
    private ListView mListView;
    Button bouton;
    private static int currentPoll;
    public static List<Integer> pourcent = new ArrayList<Integer>();
    int flag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sondage_reponse);
        currentPoll= NotificationActivity.currentPoll;
        notification=NotificationActivity.currentNotification;
        if(NotificationActivity.currentPoll==0)
        {
            currentPoll=ListSurvey.current.getId();
            current = ListSurvey.current;
            if(current==null)
            {
                Log.i("null","null");
            }
            Log.i("Notif_current",String.valueOf(NotificationActivity.currentPoll));
            NotificationActivity.currentPoll=0;
            Log.i("currentPoll",String.valueOf(currentPoll));
        }
        else
        {
            flag=1;
            current=Survey.getSurveyFromId(currentPoll);
            NotificationActivity.currentPoll=0;
        }
        Log.i("display idpoll",String.valueOf(current.getId()));
        title = current.getTitle();
        TextView titre = (TextView) findViewById(R.id.Title);
        titre.setText(title);
        proposition = new ArrayList<String>();
        proposition = Proposition.getAnswers((current.getPropostions()));
        proptitre = new ArrayList<String>();
        Log.i("size propositions",String.valueOf(proposition.size()));
        for(int i=0; i<proposition.size();i++)
        {
            proptitre.add(web2[i]);
        }
        web = proptitre.toArray(new String[proptitre.size()]);
        phrase = proposition.toArray(new String[proposition.size()]);
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
                Log.i("descr",descr);
                if (listenumero.contains(propo)){
                    view.setBackgroundColor(getResources().getColor(android.R.color.white));
                    listenumero.remove(propo);
                    listedescription.remove(descr);
                    listenumeroliste = listenumero.toArray(new String[listenumero.size()]);
                    listedescriptionliste = listedescription.toArray(new String[listedescription.size()]);
                    listAdapter2 = new
                            CustomSondageAnswerAdd(SondageReponse.this, listenumeroliste);
                    mListView.setAdapter(listAdapter2);

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
                }
            }
        });

        bouton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(listenumero.size()!= web.length)
                {
                    Toast.makeText(p.poll.activity.SondageReponse.this," Vous n'avez pas sélectionné toutes les propositions",
                            Toast.LENGTH_SHORT).show();
                }
                else
                {
                    //Quand j'appuie sur valider :
                    for (int i = 1; i <= listedescription.size(); i++){
                        Survey.Modify(i, listedescription.get(i-1));
                    }
                    //Quand tout le monde a fini de repondre
                    if (Survey.SondageFini(Survey.etats(current.getId()))== 1){
                        pourcent = Survey.moyenne(Survey.getListPointsSondage(current.getId()));
                    }
                    if(flag==1) {
                        Notification.setDone(notification);
                    }
                    Intent intent = new Intent(getApplicationContext(),Menupoll.class);
                    startActivity(intent);
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
