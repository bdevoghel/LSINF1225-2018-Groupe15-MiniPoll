package p.poll.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import p.poll.R;
import p.poll.model.FriendRequest;
import p.poll.model.Notification;
import p.poll.model.User;

/**
 * Created by Vahid Beyraghi on 25-04-18.
 */
public class NotificationActivity extends AppCompatActivity {
    //User hostUser=LoginActivity.loggedUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        int i=0; //TODO: Gerer sur quelle notif on est
        TextView notification = findViewById(R.id.notification1);
        Button accept = findViewById(R.id.acceptnotif);
        Button remove = findViewById(R.id.removenotif);
        /*notification.append(hostUser.getNotificationList().get(i).getText());
        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int i=0; //TODO: Gerer sur quelle notif on est
                Notification notification = hostUser.getNotificationList().get(i);
                if(notification instanceof FriendRequest){
                    FriendRequest friendRequest = (FriendRequest) notification;
                    friendRequest.getUser().addFriend(hostUser);
                    hostUser.addFriend(friendRequest.getUser());
                    hostUser.rmvNotification(notification);
                }
                else
                {
                    //TODO: Gerer les notifs de polls
                }
            }
        });
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int i=0; //TODO: Gerer sur quelle notif on est
                hostUser.rmvNotification(hostUser.getNotificationList().get(i));
            }
        });*/
    }
}
