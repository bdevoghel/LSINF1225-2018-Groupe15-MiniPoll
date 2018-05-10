package p.poll.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import p.poll.R;

/**
 * Created by Nicolas on 10/05/2018.
 */

public class Ajoutamiunique extends Activity{
    String strnom;
    String strprenom;
    String stremail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ajout_amis_unique);

        TextView nom = (TextView) findViewById(R.id.text3);
        nom.setText(strnom);
        TextView prenom = (TextView) findViewById(R.id.text);
        prenom.setText(strprenom);
        TextView email = (TextView) findViewById(R.id.emailinfo);
        email.setText(stremail);

        Button menu = (Button) findViewById(R.id.buttonexit);
        menu.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
            }
        });
    }
}
