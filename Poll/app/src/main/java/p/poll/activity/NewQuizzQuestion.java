package p.poll.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import p.poll.R;
import p.poll.model.User;

/**
 * Created by Nicolas on 10/05/2018.
 */

public class NewQuizzQuestion extends Activity {
    ListView list;
    CustomQuizzAnswerAdd listAdapter2;


    public List<String> reponsequestion1;
    public List<String> reponsequestion2;
    public List<String> reponsequestion3;
    public List<String> reponsequestion4;
    public List<String> goodanswer;
    int positionquestion;
    int numberquestion;

    public static List<String> current;

    String[] listereponse;
    String[] web = NewQuizz.web;
    String[] question = NewQuizz.ListePropositions.toArray(new String[NewQuizz.ListePropositions.size()]);
    Button bouton;
    Button bouton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_quizz);
        numberquestion = question.length;
        positionquestion = 1;
        current = new ArrayList<String>();
        reponsequestion1 = new ArrayList<String>();
        reponsequestion2 = new ArrayList<String>();
        reponsequestion3 = new ArrayList<String>();
        reponsequestion4 = new ArrayList<String>();
        goodanswer = new ArrayList<String>();
        for(int i =0; i<numberquestion -1; i++)
        {
            goodanswer.add(null);
        }

        bouton = (Button) findViewById(R.id.button);
        bouton2 = (Button) findViewById(R.id.button2);
        list = (ListView) findViewById(R.id.listView1);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                    TextView txt = (TextView) view.findViewById(R.id.txt);
                    current.remove(txt.getText().toString());
                    listereponse = current.toArray(new String[current.size()]);
                    listAdapter2 = new
                            CustomQuizzAnswerAdd(NewQuizzQuestion.this, listereponse);
                    list.setAdapter(listAdapter2);
            }
        });

        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                view.setBackgroundColor(getResources().getColor(android.R.color.holo_green_dark));
                TextView txt = (TextView) view.findViewById(R.id.txt);
                goodanswer.set(positionquestion,txt.getText().toString());
                return true;
            }
        });
        bouton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(current.size() <=5) {
                    EditText txt = (EditText) findViewById(R.id.editText2);
                    current.add(txt.getText().toString());
                    listereponse = current.toArray(new String[current.size()]);
                    listAdapter2 = new
                            CustomQuizzAnswerAdd(NewQuizzQuestion.this, listereponse);
                    list.setAdapter(listAdapter2);
                }
                else
                {
                    Toast.makeText(NewQuizzQuestion.this, "Vous avez atteint le maximum de réponse possible", Toast.LENGTH_SHORT).show();
                }
            }
        });

        bouton2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(positionquestion == 1)
                {
                    reponsequestion1 = current;
                }
                else if(positionquestion == 2)
                {
                    reponsequestion2 = current;
                }
                else if(positionquestion == 3)
                {
                    reponsequestion3 = current;
                }
                else if(positionquestion == 4)
                {
                    reponsequestion4 = current;
                }
            }
        });
    }
}