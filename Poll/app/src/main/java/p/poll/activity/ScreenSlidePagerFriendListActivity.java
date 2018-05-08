package p.poll.activity;

/**
 * Created by Nicolas on 02/05/2018.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;

import p.poll.R;
import p.poll.model.User;

public class ScreenSlidePagerFriendListActivity extends FragmentActivity {
    /**
     * The number of pages (wizard steps) to show in this demo.
     */
    public static int NUM_PAGES;
    private  static int pos =0;
    public static ArrayList<User> userList = User.getFriends();
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
        NUM_PAGES=User.getFriends().size();
        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerFriendListAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
    }

    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            pos--;
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }

    /**
     * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
     * sequence.
     */
    private class ScreenSlidePagerFriendListAdapter extends FragmentStatePagerAdapter{
        public ScreenSlidePagerFriendListAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            ScreenSlidePageFriendListFragment newFrag=new ScreenSlidePageFriendListFragment();
            if(position<userList.size()) {
                newFrag = new ScreenSlidePageFriendListFragment(position, User.getFriends(), getContentResolver());
            }
            return newFrag ;
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
}