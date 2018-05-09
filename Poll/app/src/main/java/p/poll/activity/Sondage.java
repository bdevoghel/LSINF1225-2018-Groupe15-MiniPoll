package p.poll.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import p.poll.R;

/**
 * Created by Nicolas on 04/05/2018.
 */

public class Sondage extends Activity {
    ListView list;
    List<String> listfriendclick = new ArrayList<String>();
    Button bouton;
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
        for (int i = 0; i < 6; i++){
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
        final CustomSondage listAdapter = new
                CustomSondage(Sondage.this, web);
        list=(ListView)findViewById(R.id.listView1);
        bouton = (Button) findViewById(R.id.button);
        list.setAdapter(listAdapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(Sondage.this, "You Clicked at " +web[+ position]+" value = ", Toast.LENGTH_SHORT).show();
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
