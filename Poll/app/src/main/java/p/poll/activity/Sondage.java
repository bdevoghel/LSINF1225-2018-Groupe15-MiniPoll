/* package p.poll.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import p.poll.R;


  Created by Nicolas on 04/05/2018.


public class Sondage extends Activity {
    public static int idpoll = p.poll.model.Survey.nouvIdpoll(p.poll.model.Survey.getIdpoll());
    ListView list;
    public static List<String> listfriendclick;
    Button bouton;
    public static List<View> listproposition;
    public static String[] web = {
            "Proposition1",
            "Proposition2",
            "Proposition3",
            "Proposition4",
            "Proposition5",
            "Proposition6"
    } ;
    public static List<String> ListePropositions = new ArrayList<String>();
    public List<String> MakeListePropositions (){
        for (int i = 0; i < 7; i++){
            ListePropositions.add(this.web[i]);
        }
        return ListePropositions;
    }
    public static int joueurs = 0;
    public static int total = 0;

    public static Boolean flag[ ]= {
            false,
            false,
            false,
            false,
            false,
            false,
    } ;

    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_sondage);
        listfriendclick = new ArrayList<String>();
        listproposition = new ArrayList<View>();
        for(int i=0;i<7;i++)
        {
            listproposition.add(null);
        }
        final CustomSondage listAdapter = new
                CustomSondage(Sondage.this, web);
        list=(ListView)findViewById(R.id.listView1);
        bouton = (Button) findViewById(R.id.button);
        list.setAdapter(listAdapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                listproposition.set(position,view);
                Toast.makeText(Sondage.this, "You Clicked at " +web[position]+" value = ", Toast.LENGTH_SHORT).show();
            }
        });

        mListView = (ListView) findViewById(R.id.listView2);

        List<PollModel> polls = genererAmi();

        PollAdapterSondage adapter = new PollAdapterSondage(Sondage.this, polls);
        mListView.setAdapter(adapter);
        mListView.setClickable(true);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                Object o = mListView.getItemAtPosition(position);
                PollModel j = (PollModel)o;
                if( !listfriendclick.contains(j.getPseudo()) && !listfriendclick.isEmpty())
                {
                    arg1.setBackgroundColor(getResources().getColor(android.R.color.holo_green_dark));

                    listfriendclick.add(j.getPseudo());
                    if(listfriendclick.isEmpty()) {
                        bouton.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
                    }
                    Toast.makeText(p.poll.activity.Sondage.this, "Vous avez ajouté : " + j.getPseudo(),
                            Toast.LENGTH_SHORT).show();
                }
                else if(listfriendclick.contains(j.getPseudo()))
                {
                    arg1.setBackgroundColor(getResources().getColor(android.R.color.white));
                    listfriendclick.remove(j.getPseudo());
                    if(listfriendclick.isEmpty()) {
                        bouton.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
                    }
                }
                else {
                    bouton.setBackgroundColor(getResources().getColor(android.R.color.holo_green_dark));
                    arg1.setBackgroundColor(getResources().getColor(android.R.color.holo_green_dark));
                    listfriendclick.add(j.getPseudo());
                    Toast.makeText(p.poll.activity.Sondage.this, j.getText(),
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        bouton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(listfriendclick.isEmpty())
                {
                    Toast.makeText(p.poll.activity.Sondage.this,"Aucun ami sélectionné",
                            Toast.LENGTH_SHORT).show();
                }
                else
                {
                    View vv = listproposition.get(0);
                    EditText edit = (EditText) vv.findViewById(R.id.editText6);
                    Toast.makeText(p.poll.activity.Sondage.this,edit.getText(),
                            Toast.LENGTH_SHORT).show();
                    // Go to Sondage Prevue
                }
            }
        });
    }


    private List<PollModel> genererAmi(){
        List<PollModel> tweets = new ArrayList<PollModel>();
        tweets.add(new PollModel(Color.BLACK, "Florent", "Mon premier tweet !"));
        tweets.add(new PollModel(Color.BLUE, "Kevin", "C'est ici que ça se passe !"));
        tweets.add(new PollModel(Color.GREEN, "Logan", "Que c'est beau..."));
        tweets.add(new PollModel(Color.RED, "Mathieu", "Il est quelle heure ??"));
        tweets.add(new PollModel(Color.GRAY, "Willy", "On y est presque"));
        tweets.add(new PollModel(Color.BLACK, "Florent", "Mon premier tweet !"));
        tweets.add(new PollModel(Color.BLUE, "Kevin", "C'est ici que ça se passe !"));
        tweets.add(new PollModel(Color.GREEN, "Logan", "Que c'est beau..."));
        tweets.add(new PollModel(Color.RED, "Mathieu", "Il est quelle heure ??"));
        tweets.add(new PollModel(Color.GRAY, "Willy", "On y est presque"));
        tweets.add(new PollModel(Color.BLACK, "Florent", "Mon premier tweet !"));
        tweets.add(new PollModel(Color.BLUE, "Kevin", "C'est ici que ça se passe !"));
        tweets.add(new PollModel(Color.GREEN, "Logan", "Que c'est beau..."));
        tweets.add(new PollModel(Color.RED, "Mathieu", "Il est quelle heure ??"));
        tweets.add(new PollModel(Color.GRAY, "Willy", "On y est presque"));
        tweets.add(new PollModel(Color.BLACK, "Florent", "Mon premier tweet !"));
        tweets.add(new PollModel(Color.BLUE, "Kevin", "C'est ici que ça se passe !"));
        tweets.add(new PollModel(Color.GREEN, "Logan", "Que c'est beau..."));
        tweets.add(new PollModel(Color.RED, "Mathieu", "Il est quelle heure ??"));
        tweets.add(new PollModel(Color.GRAY, "Willy", "On y est presque"));
        tweets.add(new PollModel(Color.BLACK, "Florent", "Mon premier tweet !"));
        tweets.add(new PollModel(Color.BLUE, "Kevin", "C'est ici que ça se passe !"));
        tweets.add(new PollModel(Color.GREEN, "Logan", "Que c'est beau..."));
        tweets.add(new PollModel(Color.RED, "Mathieu", "Il est quelle heure ??"));
        tweets.add(new PollModel(Color.GRAY, "Willy", "On y est presque"));
        return tweets;
    }
}


*/



package p.poll.activity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import p.poll.MySQLiteHelper;
import p.poll.R;
import p.poll.model.Poll;
import p.poll.model.User;
import p.poll.model.Survey;

/**
 * Created by Nicolas on 04/05/2018.
 */

public class Sondage extends Activity {
    public static int idpoll;
    ListView list;
    public static List<String> listfriendclick;
    Button bouton;
    public static List<View> listproposition;
    public static List<String> listPropositions;
    public static String[] web = {
            "Proposition1",
            "Proposition2",
            "Proposition3",
            "Proposition4",
            "Proposition5",
            "Proposition6"
    } ;
    public static String title;
    EditText titre;
    public static List<String> ListePropositions = new ArrayList<String>();
    public List<String> MakeListePropositions (){
        listPropositions = new ArrayList<String>();
        for (int i = 0; i < 7; i++){
            View vv = listproposition.get(i);
            if(vv != null) {
                EditText edit = (EditText) vv.findViewById(R.id.editText6);
                String prop = edit.getText().toString();
                if(prop.compareTo("")!=0) {
                    listPropositions.add(prop);
                }
            }
        }
        return ListePropositions;
    }
    //public static int idpoll = 3;
    public static int p = 0;


    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        idpoll = Poll.setId();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_sondage);
        p = 0;
        title = "";
        titre = (EditText) findViewById(R.id.editText3);
        title = titre.getText().toString();
        listPropositions = new ArrayList<String>();
        listfriendclick = new ArrayList<String>();
        listproposition = new ArrayList<View>();
        for(int i=0;i<7;i++)
        {
            listproposition.add(null);
        }
        final CustomSondage listAdapter = new
                CustomSondage(Sondage.this, web);
        list=(ListView)findViewById(R.id.listView1);
        bouton = (Button) findViewById(R.id.button);
        list.setAdapter(listAdapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                listproposition.set(position,view);
                Toast.makeText(Sondage.this, "You Clicked at " +web[position]+" value = ", Toast.LENGTH_SHORT).show();
            }
        });

        mListView = (ListView) findViewById(R.id.listView2);

        List<PollModel> polls = genererAmi();

        PollAdapterSondage adapter = new PollAdapterSondage(Sondage.this, polls);
        mListView.setAdapter(adapter);
        mListView.setClickable(true);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                Object o = mListView.getItemAtPosition(position);
                PollModel j = (PollModel)o;
                if( !listfriendclick.contains(j.getPseudo()) && !listfriendclick.isEmpty())
                {
                    arg1.setBackgroundColor(getResources().getColor(android.R.color.holo_green_dark));

                    listfriendclick.add(j.getPseudo());
                    if(listfriendclick.isEmpty()) {
                        bouton.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
                    }
                    Toast.makeText(p.poll.activity.Sondage.this, "Vous avez ajouté : " + j.getPseudo(),
                            Toast.LENGTH_SHORT).show();
                }
                else if(listfriendclick.contains(j.getPseudo()))
                {
                    arg1.setBackgroundColor(getResources().getColor(android.R.color.white));
                    listfriendclick.remove(j.getPseudo());
                    if(listfriendclick.isEmpty()) {
                        bouton.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
                    }
                }
                else {
                    bouton.setBackgroundColor(getResources().getColor(android.R.color.holo_green_dark));
                    arg1.setBackgroundColor(getResources().getColor(android.R.color.holo_green_dark));
                    listfriendclick.add(j.getPseudo());
                    Toast.makeText(p.poll.activity.Sondage.this, j.getText(),
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        bouton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                title = titre.getText().toString();
                if(title.compareTo("") == 0)
                {
                    Toast.makeText(p.poll.activity.Sondage.this,"Vous n'avez pas choisi de titre",
                            Toast.LENGTH_SHORT).show();
                }
                else if(listfriendclick.isEmpty())
                {
                    Toast.makeText(p.poll.activity.Sondage.this,"Aucun ami sélectionné",
                            Toast.LENGTH_SHORT).show();
                }
                else {
                    MakeListePropositions();
                    Intent intent = new Intent(getApplicationContext(), Sondageprevue.class);
                    startActivity(intent);
                }
            }
        });
    }


    private List<PollModel> genererAmi(){
        List<User> users = User.getFriends();
        List<PollModel> tweets = new ArrayList<PollModel>();
        for(int i =0; i<users.size();i++)
        {
            tweets.add(new PollModel(users.get(i).getProfilePic(), users.get(i).getUsername(),users.get(i).getMailAdress()));
        }
        /*
        tweets.add(new PollModel(Color.BLACK, "Florent", "Mon premier tweet !"));
        tweets.add(new PollModel(Color.BLUE, "Kevin", "C'est ici que ça se passe !"));
        tweets.add(new PollModel(Color.GREEN, "Logan", "Que c'est beau..."));
        tweets.add(new PollModel(Color.RED, "Mathieu", "Il est quelle heure ??"));
        tweets.add(new PollModel(Color.GRAY, "Willy", "On y est presque"));
        tweets.add(new PollModel(Color.BLACK, "Florent", "Mon premier tweet !"));
        tweets.add(new PollModel(Color.BLUE, "Kevin", "C'est ici que ça se passe !"));
        tweets.add(new PollModel(Color.GREEN, "Logan", "Que c'est beau..."));
        tweets.add(new PollModel(Color.RED, "Mathieu", "Il est quelle heure ??"));
        tweets.add(new PollModel(Color.GRAY, "Willy", "On y est presque"));
        tweets.add(new PollModel(Color.BLACK, "Florent", "Mon premier tweet !"));
        tweets.add(new PollModel(Color.BLUE, "Kevin", "C'est ici que ça se passe !"));
        tweets.add(new PollModel(Color.GREEN, "Logan", "Que c'est beau..."));
        tweets.add(new PollModel(Color.RED, "Mathieu", "Il est quelle heure ??"));
        tweets.add(new PollModel(Color.GRAY, "Willy", "On y est presque"));
        tweets.add(new PollModel(Color.BLACK, "Florent", "Mon premier tweet !"));
        tweets.add(new PollModel(Color.BLUE, "Kevin", "C'est ici que ça se passe !"));
        tweets.add(new PollModel(Color.GREEN, "Logan", "Que c'est beau..."));
        tweets.add(new PollModel(Color.RED, "Mathieu", "Il est quelle heure ??"));
        tweets.add(new PollModel(Color.GRAY, "Willy", "On y est presque"));
        tweets.add(new PollModel(Color.BLACK, "Florent", "Mon premier tweet !"));
        tweets.add(new PollModel(Color.BLUE, "Kevin", "C'est ici que ça se passe !"));
        tweets.add(new PollModel(Color.GREEN, "Logan", "Que c'est beau..."));
        tweets.add(new PollModel(Color.RED, "Mathieu", "Il est quelle heure ??"));
        tweets.add(new PollModel(Color.GRAY, "Willy", "On y est presque"));*/
        return tweets;
    }
}