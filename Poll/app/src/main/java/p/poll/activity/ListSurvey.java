package p.poll.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import p.poll.R;
import p.poll.model.Survey;
import p.poll.model.User;


/**
 * Created by Nicolas on 03/05/2018.
 */

public class ListSurvey extends AppCompatActivity {
    private ListView mListView;
    private ArrayList<Survey> Sondage;
    public static Survey current;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Sondage= Survey.getSurvey();
        Log.i("display","size survey : "+Sondage.size());
        setContentView(R.layout.activity_listhelp);
        TextView texte =(TextView) findViewById(R.id.Title);
        texte.setText("Liste des sondages");
        mListView = (ListView) findViewById(R.id.listView);

        List<PollModel> polls = genererPoll();

        PollAdapter adapter = new PollAdapter(ListSurvey.this, polls);
        mListView.setAdapter(adapter);
        mListView.setClickable(true);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                Object o = mListView.getItemAtPosition(position);
                PollModel j = (PollModel)o;
                Toast.makeText(ListSurvey.this, j.getText(),
                        Toast.LENGTH_SHORT).show();
                current = Sondage.get(position);
                Intent intent = new Intent(getApplicationContext(),SondageReponse.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private List<PollModel> genererPoll(){
        List<PollModel> tweets = new ArrayList<PollModel>();
        for(int i =0; i<Sondage.size();i++)
        {
            Log.i("display title :",Sondage.get(i).getTitle());
            tweets.add(new PollModel(null, Sondage.get(i).getOwner().getFirstName(),Sondage.get(i).getTitle()));
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
