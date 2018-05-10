package p.poll.activity;

/**
 * Created by Nicolas on 10/05/2018.
 */

import android.app.Activity;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import p.poll.R;

public class CustomSondageResult extends ArrayAdapter<String>{
    private final Activity context;
    private final String[] web;
    private final String[] phrase;
    private final Integer[] pourcent;
    private final int max;
    public CustomSondageResult(Activity context,
                               String[] web, String[] phrase, Integer[] pourcent, int max) {
        super(context, R.layout.row_sondage_answer, web);
        this.context = context;
        this.web = web;
        this.phrase = phrase;
        this.pourcent = pourcent;
        this.max = max;
    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.row_sondageresult, null, true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.txt);
        txtTitle.setText(web[position]);

        TextView txtDescription = (TextView) rowView.findViewById(R.id.txt2);
        txtDescription.setText(phrase[position]);

        TextView pourcentage = (TextView) rowView.findViewById(R.id.txt3);
        pourcentage.setText(pourcent[position].toString() + " %");

        ProgressBar mProgressBar = (ProgressBar) rowView.findViewById(R.id.pBAsync);
        mProgressBar.setProgressTintList(ColorStateList.valueOf(Color.RED));
        mProgressBar.setProgress(pourcent[position]);
        if(max == pourcent[position])
        {
            rowView.setBackgroundColor(getContext().getResources().getColor(android.R.color.holo_green_dark));
        }

        return rowView;
    }
}
