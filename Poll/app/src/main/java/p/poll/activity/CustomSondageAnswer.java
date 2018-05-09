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

public class CustomSondageAnswer extends ArrayAdapter<String>{
    private final Activity context;
    private final String[] web;
    private final String[] phrase;
    public CustomSondageAnswer(Activity context,
                         String[] web, String[] phrase) {
        super(context, R.layout.row_sondage_answer, web);
        this.context = context;
        this.web = web;
        this.phrase = phrase;
    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.row_sondage_answer, null, true);
        //View view2 = Sondage.listproposition.get((position));
        //if(view2 == null) {
            TextView txtTitle = (TextView) rowView.findViewById(R.id.txt);
            txtTitle.setText(web[position]);
            if(SondageReponse.listenumero.contains(web[position]))
            {
                rowView.setBackgroundColor(getContext().getResources().getColor(android.R.color.holo_green_dark));
            }
            TextView txtDescription = (TextView) rowView.findViewById(R.id.txt2);
            txtDescription.setText(phrase[position]);
            //Sondage.listproposition.set(position,rowView);
        //}
        //else
        //{
            //edit.setText(((EditText)view2.findViewById(R.id.editText6)).getText());
            //((TextView)view2.findViewById(R.id.txt)).setText("Proposition"+position+1);
        //    return view2;
        //}
        return rowView;
    }
}
