package p.poll.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import p.poll.R;
import p.poll.model.Notification;
import p.poll.model.User;

import static p.poll.activity.Notification2.currentNotification;

/**
 * Created by Vahid Beyraghi on 25-04-18.
 */
public class NotificationActivity extends AppCompatActivity {
    private TextView description;
    private Button accept;
    private Button refuse;
    private TextView from;
    private ImageView photo;
    private View view;
    public Notification notification= currentNotification;
    private User userNotif=User.getUser(notification.getUsername());

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(getApplicationContext(), Notification2.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_simple);
        description=findViewById(R.id.description);
        accept=findViewById(R.id.accept);
        refuse=findViewById(R.id.refuse);
        from=findViewById(R.id.from);
        photo=findViewById(R.id.photo);

        description.setText(notification.getText());
        from.setText(userNotif.getFirstName()+" "+userNotif.getLastName());
        if(userNotif.getProfilePic()!=null) {
            photo.setImageBitmap(User.toBitmap(userNotif.getProfilePic(), getContentResolver()));
        }
        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User.acceptFriend(userNotif);
                Notification.setDone(notification);
                Intent intent = new Intent(getApplicationContext(), Notification2.class);
                startActivity(intent);
                finish();
            }
        });
        refuse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Notification.setDone(notification);
                Intent intent = new Intent(getApplicationContext(), Notification2.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
