package p.poll.activity;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import p.poll.R;
import p.poll.model.Notification;
import p.poll.model.User;

/**
 * Created by Nicolas on 03/05/2018.
 */

public class Menupoll extends AppCompatActivity implements
              NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;

    private Toolbar mToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_poll_creation);
        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                if(position == 0)
                {
                    Quizz();
                }
                else if(position == 1)
                {
                    Choise();
                }
                else if(position == 2)
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
        ImageView notif =(ImageView) findViewById(R.id.notification);
        mToolbar = (Toolbar) findViewById(R.id.nav_action);
        setSupportActionBar(mToolbar);
        mToolbar.setClickable(true);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);


        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView mNavigationView = (NavigationView) findViewById(R.id.navview);

        if (mNavigationView != null) {
            mNavigationView.setNavigationItemSelectedListener(this);
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_account) {
            Toast.makeText(Menupoll.this, "Compte",
                    Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(),Profile.class);
            startActivity(intent);
        }
        else if (id == R.id.new_quizz) {
            Toast.makeText(Menupoll.this, getString(R.string.create_quizz),
                    Toast.LENGTH_SHORT).show();
        }
        else if (id == R.id.new_survey) {
            Toast.makeText(Menupoll.this, getString(R.string.create_survey),
                    Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(),Sondage.class);
            startActivity(intent);
        }
        else if (id == R.id.new_help){
            Toast.makeText(Menupoll.this, getString(R.string.create_advice),
                    Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(),NewHelp.class);
            startActivity(intent);

        }
        else if (id == R.id.nav_modify_password){
            Toast.makeText(Menupoll.this, getString(R.string.modify_password),
                    Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(),ModifyPassword.class);
            startActivity(intent);
        }
        else if(id == R.id.nav_modify_username){
            Toast.makeText(Menupoll.this, getString(R.string.modify_username),
                    Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(),ModifyUsername.class);
            startActivity(intent);
        }
        else {
            Toast.makeText(Menupoll.this, getString(R.string.deco), Toast.LENGTH_SHORT).show();
            User.loggedUser=null;
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
                finish();
        }

        return true;
    }

    @Override
    public void onBackPressed() {
        //Nothing
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void profile(View v){
        Intent intent = new Intent(getApplicationContext(),Profile.class);
        startActivity(intent);
    }
    public void friends(View v) {
        seeFriendList(v);
    }
    public void Survey()
    {
        Toast.makeText(Menupoll.this,  getString(R.string.survey),
                Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(),ListHelp.class);
        startActivity(intent);
    }
    public void Notification(View v)
    {
        Toast.makeText(Menupoll.this, getString(R.string.notification),
                Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(),Notification2.class);
        startActivity(intent);
    }
    public void Quizz()
    {
        Toast.makeText(Menupoll.this,  getString(R.string.quizz),
                Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(),ListQuizz.class);
        startActivity(intent);
    }
    private void Choise()
    {
        Toast.makeText(Menupoll.this,  getString(R.string.choice),
                Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(),ListSurvey.class);
        startActivity(intent);
    }
    private void Newpoll()
    {
        Toast.makeText(Menupoll.this,  getString(R.string.new_poll),
                Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(),NewPoll.class);
        startActivity(intent);
    }
    public void seeFriendList(View v) {
        Intent intent = new Intent(getApplicationContext(),ScreenSlidePagerActivity.class);
        startActivity(intent);
    }
}
