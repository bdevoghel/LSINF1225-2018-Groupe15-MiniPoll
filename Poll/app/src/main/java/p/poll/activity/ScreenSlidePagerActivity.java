package p.poll.activity;

/**
 * Created by Nicolas on 02/05/2018.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.Toast;

import java.util.ArrayList;

import p.poll.R;
import p.poll.model.User;

public class ScreenSlidePagerActivity extends FragmentActivity {
    /**
     * The number of pages (wizard steps) to show in this demo.
     */
    public static int NUM_PAGES;
    public  static int pos =0;
    public static ArrayList<User> userList = User.getNotFriends();
    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private ViewPager mPager;
    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private PagerAdapter mPagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_slide);
        // Instantiate a ViewPager and a PagerAdapter.
        NUM_PAGES=User.getNotFriends().size();
        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(),ScreenSlidePagerFriendListActivity.class);
        startActivity(intent);
    }

    /**
     * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
     * sequence.
     */
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter{
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if(NUM_PAGES==0){
                Intent intent = new Intent(getApplicationContext(),Menupoll.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "There is no one left to add!", Toast.LENGTH_LONG).show();
                finish();
            }
            ScreenSlidePageFragment newFrag=new ScreenSlidePageFragment();
            if(position<User.getNotFriends().size()) {
                newFrag = new ScreenSlidePageFragment(position, User.getNotFriends(), getContentResolver());
            }
            return newFrag ;
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
}