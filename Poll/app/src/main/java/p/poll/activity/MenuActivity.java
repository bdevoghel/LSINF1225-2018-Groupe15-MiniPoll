package p.poll.activity;

import android.app.LoaderManager;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import p.poll.R;

/**
 * Created by Vahid Beyraghi on 25-04-18.
 */
public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button survey=findViewById(R.id.survey);
        Button quizz=findViewById(R.id.quizz);
        Button choice=findViewById(R.id.choice);
        Button newpoll=findViewById(R.id.newpoll);
        Button profile=findViewById(R.id.profile);
        Button friends=findViewById(R.id.friends);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: Go to profileActivity
            }
        });
        friends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                seeFriendList(view);
            }
        });
        survey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: Go to surveyList
            }
        });
        quizz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: Go to quizzList
            }
        });
        choice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: Go to choiceList
            }
        });
        newpoll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: Go to pollCreation
            }
        });
    }
    public void seeFriendList(View v) {
        Intent intent = new Intent(getApplicationContext(),FriendListActivity.class);
        startActivity(intent);
    }
}
