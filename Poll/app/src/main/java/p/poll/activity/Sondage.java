package p.poll.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import p.poll.R;

/**
 * Created by Nicolas on 04/05/2018.
 */

public class Sondage extends Activity {
    ListView list;
    String[] web = {
            "Proposition1",
            "Proposition2",
            "Proposition3",
            "Proposition4",
            "Proposition5",
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
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        final CustomList listAdapter = new
                CustomList(Sondage.this, web, imageId);
        list=(ListView)findViewById(R.id.list);
        list.setAdapter(listAdapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(Sondage.this, "You Clicked at " +web[+ position]+" value = ", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
