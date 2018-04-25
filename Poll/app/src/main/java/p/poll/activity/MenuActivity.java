package p.poll.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import p.poll.R;

/**
 * Created by Vahid Beyraghi on 25-04-18.
 */
public class MenuActivity extends AppCompatActivity {

    private View view;
    private Button survey;
    private Button quizz;
    private Button choice;
    private Button newpoll;
    private Button profile;
    private Button friends;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        survey=findViewById(R.id.survey);
        quizz=findViewById(R.id.quizz);
        choice=findViewById(R.id.choice);
        newpoll=findViewById(R.id.newpoll);
        profile=findViewById(R.id.profile);
        friends=findViewById(R.id.friends);
    }
}
