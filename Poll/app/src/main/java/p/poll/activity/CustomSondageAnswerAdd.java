package p.poll.activity;

/**
 * Created by Nicolas on 03/05/2018.
 */

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import p.poll.R;

public class CustomSondageAnswerAdd extends ArrayAdapter<String>{
    private final Activity context;
    private final String[] web;
    public CustomSondageAnswerAdd(Activity context,
                               String[] web) {
        super(context, R.layout.row_sondage_answer, web);
        this.context = context;
        this.web = web;
    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.row_sondage_answer, null, true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.txt);
        txtTitle.setText(position+1 + " : " + web[position]);
        TextView txtDescription = (TextView) rowView.findViewById(R.id.txt2);
        txtDescription.setText(SondageReponse.listedescriptionliste[position]);

        return rowView;
    }
}

