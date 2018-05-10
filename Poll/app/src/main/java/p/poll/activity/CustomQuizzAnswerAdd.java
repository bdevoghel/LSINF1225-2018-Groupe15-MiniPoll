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

public class CustomQuizzAnswerAdd extends ArrayAdapter<String>{
    private final Activity context;
    private final String[] web;
    public CustomQuizzAnswerAdd(Activity context,
                                  String[] web) {
        super(context, R.layout.row_quizz_answer, web);
        this.context = context;
        this.web = web;
    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.row_quizz_answer, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.txt);
        if(NewQuizzQuestion.goodanswer.get(NewQuizzQuestion.positionquestion-1) == null)
        {

        }
        else if(NewQuizzQuestion.goodanswer.get(NewQuizzQuestion.positionquestion-1).compareTo(web[position]) == 0)
        {
            rowView.setBackgroundColor(context.getResources().getColor(android.R.color.holo_green_dark));
        }
        else
        {
            rowView.setBackgroundColor(context.getResources().getColor(android.R.color.white));
        }
        txtTitle.setText(web[position]);
        return rowView;
    }
}
