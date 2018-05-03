package p.poll.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.app.Activity;

import p.poll.R;

/**
 * Created by Nicolas on 03/05/2018.
 */

public class Notification2 extends Activity {

        ListView list;
        String[] web = {
                "Java",
                "C++",
                "C#",
                "HTML",
                "CSS",
                "Java",
                "C++",
                "C#",
                "HTML",
                "CSS",
                "Java",
                "C++",
                "C#",
                "HTML",
                "CSS",
                "Java",
                "C++",
                "C#",
                "HTML",
                "CSS",
                "HTML",
                "CSS"
        } ;
        Integer[] imageId = {
                R.drawable.notification,
                R.drawable.notification,
                R.drawable.notification,
                R.drawable.notification,
                R.drawable.notification,
                R.drawable.notification,
                R.drawable.notification,
                R.drawable.notification,
                R.drawable.notification,
                R.drawable.notification,
                R.drawable.notification,
                R.drawable.notification,
                R.drawable.survey,
                R.drawable.choice,
                R.drawable.friends,
                R.drawable.userimage,
                R.drawable.michel,
                R.drawable.survey,
                R.drawable.choice,
                R.drawable.friends,
                R.drawable.userimage
        };
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_notification);
            final CustomList listAdapter = new
                    CustomList(Notification2.this, web, imageId);
            list=(ListView)findViewById(R.id.list);
            list.setAdapter(listAdapter);
            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    Toast.makeText(Notification2.this, "You Clicked at " +web[+ position]+" value = ", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
