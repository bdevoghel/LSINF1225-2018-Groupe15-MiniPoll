package p.poll.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Map;

import android.view.View;
import android.widget.Button;

import p.poll.R;
import p.poll.model.User;

public class FriendListActivity extends AppCompatActivity {

    private Map<String,User> userList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friendlist);
        final Button button = findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            }
        });
    }
}