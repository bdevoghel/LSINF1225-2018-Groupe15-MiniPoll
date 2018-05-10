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
 * Created by Nicolas on 10/05/2018.
 */

public class resultatsondage extends Activity {
    ListView list;
    String nomdusondage = "Un sondage";
    String[] web = {
            "Proposition1",
            "Proposition2",
            "Proposition3",
            "Proposition4",
            "Proposition5",
            "Proposition6"
    };
    String[] phrase = {
            "Texte Proposition1",
            "Texte Proposition2",
            "Texte Proposition3",
            "Texte Proposition4",
            "Texte Proposition5",
            "Texte Proposition6"
    };

    Integer[] pourcent = { // de 0 à 100 !!!
            10,
            20,
            80,
            5,
            65,
            40
    };

    public int max;

    Button bouton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activityresultsondage);
        max  = 80;
        TextView titresondage = (TextView) findViewById(R.id.TitreSondage);
        titresondage.setText(nomdusondage);

        CustomSondageResult listAdapter = new CustomSondageResult(resultatsondage.this, web, phrase, pourcent, max);
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
