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


    public static List<String> reponsequestion1;
    public static List<String> reponsequestion2;
    public static List<String> reponsequestion3;
    public static List<String> reponsequestion4;
    public static List<String> goodanswer;
    int positionquestion;
    public static int numberquestion;

    public List<String> current;

    String[] listereponse;
    String[] question;
    Button bouton;
    Button bouton2;
    TextView titre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_quizz);
        titre = (TextView) findViewById(R.id.textView22);
        question = NewQuizz.ListePropositions.toArray(new String[NewQuizz.ListePropositions.size()]);
        titre.setText("Question 1: "+question[0]);
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
        bouton2.setText("Question suivante");
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

                TextView txt = (TextView) view.findViewById(R.id.txt);
                if(goodanswer.get(positionquestion) == null)
                {
                    view.setBackgroundColor(getResources().getColor(android.R.color.holo_green_dark));
                    goodanswer.set(positionquestion, txt.getText().toString());
                    return true;
                }
                if(goodanswer.get(positionquestion).compareTo(txt.getText().toString()) == 0)
                {
                    goodanswer.set(positionquestion,null);
                    view.setBackgroundColor(getResources().getColor(android.R.color.white));
                }
                else if(goodanswer.get(positionquestion) != null)
                {
                    Toast.makeText(NewQuizzQuestion.this, "Vous avez déjà choisi une réponse. Désélectionné là pour la retirer", Toast.LENGTH_SHORT).show();
                }
                else {
                    view.setBackgroundColor(getResources().getColor(android.R.color.holo_green_dark));
                    goodanswer.set(positionquestion, txt.getText().toString());
                }
                return true;
            }
        });
        bouton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(current.size() <=5) {
                    EditText txt = (EditText) findViewById(R.id.editText2);
                    if(current.contains(txt.getText().toString()))
                    {
                        Toast.makeText(NewQuizzQuestion.this, "Cette réponse existe déjà", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        current.add(txt.getText().toString());
                        listereponse = current.toArray(new String[current.size()]);
                        listAdapter2 = new
                                CustomQuizzAnswerAdd(NewQuizzQuestion.this, listereponse);
                        list.setAdapter(listAdapter2);
                    }
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
                if(bouton2.getText().toString().compareTo("Valider le Quizz") == 0)
                {
                    Intent intent = new Intent(getApplicationContext(),Quizzprevue.class);
                    startActivity(intent);
                    ///////////////////////////////////////////////////////////////////////////////
                    //Base de données
                    ///////////////////////////////////////////////////////////////////////////////
                    //Mettre la base de donnée ici;
                    //reponsequestion1 Contient la liste des réponse pour la premiere question
                    //reponsequestion2
                    //reponsequestion3
                    //reponsequestion4 -> si 3 question, alors pas forcement rempli
                    //Question est le titre des question (attention pas forcement 4 question
                    //goodanswer  = liste des bonne réponse pour caque question (dans le même ordre)
                    return ;
                }
                if(positionquestion == 1)
                {
                    reponsequestion1 = current;
                    current = new ArrayList<String>();
                    listAdapter2 = new
                            CustomQuizzAnswerAdd(NewQuizzQuestion.this, listereponse);
                    list.setAdapter(listAdapter2);
                    positionquestion++;
                    if(numberquestion == 1)
                    {
                        Intent intent = new Intent(getApplicationContext(),Quizzprevue.class);
                        startActivity(intent);
                        return;
                    }
                    if(numberquestion == 2)
                    {
                        bouton2.setText("Valider le Quizz");
                    }
                    titre.setText("Question "+ positionquestion +": "+question[1]);

                }
                else if(positionquestion == 2)
                {
                    reponsequestion2 = current;
                    current = new ArrayList<String>();
                    listAdapter2 = new
                            CustomQuizzAnswerAdd(NewQuizzQuestion.this, listereponse);
                    list.setAdapter(listAdapter2);
                    positionquestion++;
                    if(numberquestion == 2)
                    {
                        Intent intent = new Intent(getApplicationContext(),NewQuizzQuestion.class);
                        startActivity(intent);
                        return;
                    }
                    if(numberquestion == 3)
                    {
                        bouton2.setText("Valider le Quizz");
                    }
                    titre.setText("Question "+ positionquestion +": "+question[2]);
                }
                else if(positionquestion == 3)
                {
                    reponsequestion3 = current;
                    current = new ArrayList<String>();
                    listAdapter2 = new
                            CustomQuizzAnswerAdd(NewQuizzQuestion.this, listereponse);
                    list.setAdapter(listAdapter2);
                    positionquestion++;
                    if(numberquestion == 3)
                    {
                        Intent intent = new Intent(getApplicationContext(),Quizzprevue.class);
                        startActivity(intent);
                        return;
                    }
                    if(numberquestion == 4)
                    {
                        bouton2.setText("Valider le Quizz");
                    }
                    titre.setText("Question "+ positionquestion +": "+question[3]);
                }
                else if(positionquestion == 4)
                {
                    reponsequestion4 = current;
                    current = new ArrayList<String>();
                    Intent intent = new Intent(getApplicationContext(),Quizzprevue.class);
                    startActivity(intent);
                    return;
                }
            }
        });


    }
}