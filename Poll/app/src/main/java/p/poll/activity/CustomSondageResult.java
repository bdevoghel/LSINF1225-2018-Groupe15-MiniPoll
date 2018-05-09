package p.poll.activity;

/**
 * Created by Nicolas on 10/05/2018.
 */

import android.app.Activity;
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
    public CustomSondageResult(Activity context,
                               String[] web, String[] phrase, Integer[] pourcent) {
        super(context, R.layout.row_sondage_answer, web);
        this.context = context;
        this.web = web;
        this.phrase = phrase;
        this.pourcent = pourcent;
    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.row_sondage_answer, null, true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.txt);
        txtTitle.setText(web[position]);

        TextView txtDescription = (TextView) rowView.findViewById(R.id.txt2);
        txtDescription.setText(phrase[position]);

        TextView pourcentage = (TextView) rowView.findViewById(R.id.txt3);
        txtDescription.setText(pourcent[position]);

        ProgressBar mProgressBar = (ProgressBar) rowView.findViewById(R.id.pBAsync);
        mProgressBar.setProgress(pourcent[position]);

        return rowView;
    }
}
