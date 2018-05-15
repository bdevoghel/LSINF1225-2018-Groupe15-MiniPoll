package p.poll.activity;

import android.app.Activity;
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

import p.poll.R;
import p.poll.model.User;

/**
 * Created by Nicolas on 04/05/2018.
 */

public class NewQuizz extends Activity {
    ListView list;
    public static List<String> listfriendclick;
    Button bouton;
    public static List<View> listproposition;
    public static String[] web = {
            "Question 1",
            "Question 2",
            "Question 3",
            "Question 4"
    } ;
    public static List<String> ListePropositions = new ArrayList<String>();
    public void MakeListePropositions (){
        ListePropositions.clear();
        for (int i = 0; i < 5; i++){
            View vv = listproposition.get(i);
            if(vv != null) {
                EditText edit = (EditText) vv.findViewById(R.id.editText6);
                if(edit.getText().toString().compareTo("") != 0)
                {
                    ListePropositions.add(edit.getText().toString());
                }
            }
        }
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
        setContentView(R.layout.activity_new_quizz);
        listfriendclick = new ArrayList<String>();
        listproposition = new ArrayList<View>();
        for(int i=0;i<7;i++)
        {
            listproposition.add(null);
        }
        ListePropositions = new ArrayList<String>();
        final CustomQuizz listAdapter = new
                CustomQuizz(NewQuizz.this, web);
        list=(ListView)findViewById(R.id.listView1);
        bouton = (Button) findViewById(R.id.button);
        list.setAdapter(listAdapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                listproposition.set(position,view);
                Toast.makeText(NewQuizz.this, "You Clicked at " +web[position]+" value = ", Toast.LENGTH_SHORT).show();
            }
        });

        mListView = (ListView) findViewById(R.id.listView2);

        List<PollModel> polls = genererAmi();

        PollAdapterQuizz adapter = new PollAdapterQuizz(NewQuizz.this, polls);
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
                    Toast.makeText(p.poll.activity.NewQuizz.this, "Vous avez ajouté : " + j.getPseudo(),
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
                    Toast.makeText(p.poll.activity.NewQuizz.this, j.getText(),
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        bouton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(listfriendclick.isEmpty())
                {
                    Toast.makeText(p.poll.activity.NewQuizz.this,"Aucun ami sélectionné",
                            Toast.LENGTH_SHORT).show();
                }
                else
                {
                    MakeListePropositions();
                    Intent intent = new Intent(getApplicationContext(),NewQuizzQuestion.class);
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
        tweets.add(new PollModel(null, "Florent", "Mon premier tweet !"));
        tweets.add(new PollModel(null, "Kevin", "C'est ici que ça se passe !"));
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