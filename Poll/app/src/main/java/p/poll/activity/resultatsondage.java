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
import p.poll.model.Survey;
import p.poll.model.User;

/**
 * Created by Nicolas on 10/05/2018.
 */

public class resultatsondage extends Activity {
    ListView list;
    String nomdusondage;
    List<String> webliste;
    String[] web;
    String[] web2 = {
            "Proposition1",
            "Proposition2",
            "Proposition3",
            "Proposition4",
            "Proposition5",
            "Proposition6"
    };
    List<String> phrase;
    String[] phrase2;
    List<Integer> pourcent;
    Integer[] pourcent2;
    public int max;

    Button bouton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activityresultsondage);
        webliste = new ArrayList<String>();
        nomdusondage = Survey.getTitre(0).get(0);
        phrase =  Survey.getListProposition(0);
        phrase2 = phrase.toArray(new String[phrase.size()]);
        for(int i = 0; i<phrase.size();i++)
        {
            webliste.add(web2[i]);
        }
        web = webliste.toArray(new String[webliste.size()]);
        pourcent = Survey.moyenne(Survey.getListPointsSondage(Sondage.idpoll));
        pourcent2 = pourcent.toArray(new Integer[pourcent.size()]);
        max  = 0;
        for(int i =0;i<pourcent2.length;i++)
        {
            if(max < pourcent2[i])
            {
                max = pourcent2[i];
            }
        }
        TextView titresondage = (TextView) findViewById(R.id.TitreSondage);
        titresondage.setText(nomdusondage);

        CustomSondageResult listAdapter = new CustomSondageResult(resultatsondage.this, web, phrase2, pourcent2, max);
        bouton = (Button) findViewById(R.id.button);
        list = (ListView) findViewById(R.id.listView1);
        list.setAdapter(listAdapter);


        bouton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

            }
        });
    }
}
