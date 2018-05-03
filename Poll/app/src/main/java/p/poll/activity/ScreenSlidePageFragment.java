package p.poll.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import p.poll.Pollapp;
import p.poll.R;


public class ScreenSlidePageFragment extends Fragment implements View.OnClickListener{
    int pos;
    View view;
    public Button myButton;
    public Button myButton2;
    TextView textView2;
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
        view = inflater.inflate(R.layout.activity_scroll, container, false);
        TextView textView = (TextView) view.findViewById(R.id.text);
        textView.setText("Nom : "+" "+this.pos);
        textView2 = (TextView) view.findViewById(R.id.text3);
        textView2.setText("Prenom : "+" "+this.pos);
        ImageView image = (ImageView) view.findViewById(R.id.imageView6);
        image.setImageResource(R.drawable.survey);
        myButton = (Button) view.findViewById(R.id.button3);
        myButton.setOnClickListener(this);
        myButton2 = (Button) view.findViewById(R.id.buttonexit);
        myButton2.setOnClickListener(this);
        ScreenSlidePagerActivity.pos ++;
        return view;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button3:
                Toast.makeText(getActivity(), "Ami ajouté", Toast.LENGTH_SHORT).show();
                //textView2 = (TextView) v.findViewById(R.id.text3);
                textView2.setText("PrenomHello :");
                break;
            case R.id.buttonexit:
                Intent intent = new Intent(getActivity(), MenuActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
        Intent intent = new Intent(getActivity(), MenuActivity.class);
        startActivity(intent);
    }


}
