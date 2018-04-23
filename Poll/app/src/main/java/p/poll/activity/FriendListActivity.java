package p.poll.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import android.graphics.Picture;

import p.poll.R;
import p.poll.model.User;

public class FriendListActivity extends AppCompatActivity {

    private Map<String,User> userList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend);
    }
}