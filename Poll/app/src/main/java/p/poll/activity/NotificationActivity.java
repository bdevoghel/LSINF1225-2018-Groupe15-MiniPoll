package p.poll.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.app.Activity;

import java.util.ArrayList;

import p.poll.R;
import p.poll.model.Notification;
import p.poll.model.Poll;
import p.poll.model.User;

/**
 * Created by Nicolas on 03/05/2018.
 */

public class NotificationActivity extends Activity {
    public ArrayList<Notification> notifications;
    public static Notification currentNotification=null;
    public static int currentPoll=0;
    public static String userNotif=null;
        ListView list;
        String[] web ;
        Integer[] imageId;

        @Override
        public void onBackPressed(){
            Intent intent = new Intent(getApplicationContext(), Menupoll.class);
            startActivity(intent);
            finish();
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_notification);
            Log.i("test","getNotifications");
            notifications=Notification.getNotifications();
            if(notifications.size()==0){
                Intent intent = new Intent(getApplicationContext(),Menupoll.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(),"You have no notifications", Toast.LENGTH_LONG).show();
                finish();
            }
            web= Notification.getText(notifications);
            imageId = Notification.getImage(notifications);
            final CustomList listAdapter = new
                    CustomList(NotificationActivity.this, web, imageId);
            list=(ListView)findViewById(R.id.list);
            list.setAdapter(listAdapter);
            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    Log.i("test","0");
                    currentNotification=notifications.get(position);
                    if(currentNotification.getPoll()==0) {
                        Log.i("test","1");
                        Intent intent = new Intent(getApplicationContext(), NotificationShow.class);
                        startActivity(intent);
                        finish();
                    }
                    else
                    {
                        Log.i("test","2");
                        currentPoll=currentNotification.getPoll();
                        Log.i("display",String.valueOf(currentPoll));
                        Log.i("display",Poll.getType(currentPoll));
                        if(Poll.getType(currentPoll).equals("a")) {
                            if(Poll.getOwner(currentPoll).equals(User.loggedUser)) {
                                userNotif=currentNotification.getUsername();
                                Intent intent = new Intent(getApplicationContext(), resultathelp.class);
                                startActivity(intent);
                                Notification.setDone(currentNotification);
                                finish();
                            }
                            else {

                                Log.i("display", Poll.getType(currentPoll));
                                Intent intent = new Intent(getApplicationContext(), Help.class);
                                startActivity(intent);
                                Notification.setDone(currentNotification);
                                finish();
                            }
                        }
                        else if(Poll.getType(currentPoll).equals("q"))
                        {
                            if(Poll.getOwner(currentPoll).equals(User.loggedUser)) {
                            }
                            else {

                            }
                        }
                        else
                        {
                            if(Poll.getOwner(currentPoll).equals(User.loggedUser)) {
                            }
                            else {
                                Intent intent = new Intent(getApplicationContext(),SondageReponse.class);
                                startActivity(intent);
                                Notification.setDone(currentNotification);
                                finish();
                            }
                        }
                    }
                    //TextView textview = (TextView) view.findViewById(R.id.txt);
                    //CharSequence t = textview.getText();
                    //Toast.makeText(NotificationActivity.this, "You Clicked at " +web[+ position]+" value = ", Toast.LENGTH_SHORT).show();
                    //Toast.makeText(NotificationActivity.this, "You Clicked at " +web[+ position]+" value = "+ t.toString(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
