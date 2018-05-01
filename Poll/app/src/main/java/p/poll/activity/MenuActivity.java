package p.poll.activity;

import android.app.LoaderManager;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.Toast;

import p.poll.R;

/**
 * Created by Vahid Beyraghi on 25-04-18.
 */
public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu2);

        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                if(id == 0)
                {
                    Quizz();
                }
                else if(id == 1)
                {
                    Choise();
                }
                else if(id == 2)
                {
                    Newpoll();
                }
                else
                {
                    Survey();
                }
            }
        });



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
        friends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                seeFriendList(view);
            }
        });

    }
    public void Survey()
    {
        Toast.makeText(MenuActivity.this, "Survey",
                Toast.LENGTH_SHORT).show();
    }
    public void Quizz()
    {
        Toast.makeText(MenuActivity.this, "Quizz",
                Toast.LENGTH_SHORT).show();
    }
    private void Choise()
    {
        Toast.makeText(MenuActivity.this, "choise",
                Toast.LENGTH_SHORT).show();
    }
    private void Newpoll()
    {
        Toast.makeText(MenuActivity.this, "New Poll",
                Toast.LENGTH_SHORT).show();
    }
    public void seeFriendList(View v) {
        Intent intent = new Intent(getApplicationContext(),FriendListActivity.class);
        startActivity(intent);
    }
}
