package p.poll.activity;

import android.content.Intent;
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
        Button survey;
        Button quizz;
        Button choice;
        Button newpoll;
        Button profile;
        Button friends;
        Button notification;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        survey=findViewById(R.id.survey);
        quizz=findViewById(R.id.quizz);
        choice=findViewById(R.id.choice);
        newpoll=findViewById(R.id.newpoll);
        profile=findViewById(R.id.profile);
        friends=findViewById(R.id.friends);
        notification=findViewById(R.id.notification);
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
        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                seeNotifications(view);
            }
        });
    }
    public void seeFriendList(View v) {
        Intent intent = new Intent(this,FriendListActivity.class);
        startActivity(intent);
    }
    public void seeNotifications(View v){
        Intent intent = new Intent(this,NotificationActivity.class);
        startActivity(intent);
    }
}
