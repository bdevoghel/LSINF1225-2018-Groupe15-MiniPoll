package p.poll.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import p.poll.R;
import p.poll.activity.CustomSondage;
import p.poll.activity.PollAdapterSondage;
import p.poll.activity.PollModel;
import p.poll.model.Advice;
import p.poll.model.User;

/**
 * Created by Nicolas on 04/05/2018.
 */

public class listamihelp extends Activity {
    public static String userselect="";
    private ListView mListView;
    private Button bouton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choixamishelp);
        mListView = (ListView) findViewById(R.id.listView2);
        bouton = (Button) findViewById(R.id.button);
        List<PollModel> polls = genererAmi();

        PollAdapterHelp adapter = new PollAdapterHelp(p.poll.activity.listamihelp.this, polls);
        mListView.setAdapter(adapter);
        mListView.setClickable(true);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                Object o = mListView.getItemAtPosition(position);
                PollModel j = (PollModel)o;
                if( !j.getPseudo().equals(userselect) && !userselect.equals(""))
                {
                    Toast.makeText(p.poll.activity.listamihelp.this, "Vous avez déjà sélectionné un ami",
                            Toast.LENGTH_SHORT).show();
                }
                else if(j.getPseudo().equals(userselect))
                {
                    bouton.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
                    arg1.setBackgroundColor(getResources().getColor(android.R.color.white));
                    userselect = "";
                }
                else {
                    bouton.setBackgroundColor(getResources().getColor(android.R.color.holo_green_dark));
                    arg1.setBackgroundColor(getResources().getColor(android.R.color.holo_green_dark));
                    userselect = j.getPseudo();
                    Toast.makeText(p.poll.activity.listamihelp.this, j.getText(),
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        bouton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(userselect.equals(""))
                {
                    Toast.makeText(p.poll.activity.listamihelp.this,"Aucun ami sélectionné",
                            Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Log.i("test","createAdvice");
                    Advice.createAdvice(User.getUser(userselect),NewHelp.imagePath1,NewHelp.imagePath2,NewHelp.description);
                    Toast.makeText(p.poll.activity.listamihelp.this, "L'ami(e) sélectionné est : "+ userselect,
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

