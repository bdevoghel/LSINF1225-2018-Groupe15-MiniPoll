package p.poll.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import p.poll.R;


public class ScreenSlidePageFragment extends Fragment {
    int pos;
    public ScreenSlidePageFragment(int pos)
    {
        super();
        this.pos = pos;
    }
    public ScreenSlidePageFragment()
    {
        super();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.activity_scroll, container, false);
        View view = inflater.inflate(R.layout.activity_scroll, container, false);
        TextView textView = (TextView) view.findViewById(R.id.text);
        textView.setText("Update your text Here"+" "+this.pos);
        ScreenSlidePagerActivity.pos ++;
        return view;
    }
}
