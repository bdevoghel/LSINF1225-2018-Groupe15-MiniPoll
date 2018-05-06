package p.poll.activity;

/**
 * Created by Nicolas on 03/05/2018.
 */

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import p.poll.R;

public class CustomSondage extends ArrayAdapter<String>{
    private final Activity context;
    private final String[] web;
    public CustomSondage(Activity context,
                      String[] web) {
        super(context, R.layout.activity_screen_notification, web);
        this.context = context;
        this.web = web;
    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.activity_screen_sondage, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.txt);
        txtTitle.setText(web[position]);
        return rowView;
    }
}
