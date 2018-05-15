package p.poll.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.TextView;
import android.widget.Toast;

import p.poll.R;

/**
 * Created by Nicolas on 04/05/2018.
 */

public class resultatquizz extends Activity{
    public static ContentResolver content;
    ExpandableListAdapterresultat listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    List<String> listdescription;
    public static int scorenumber;
    public static List<String> choix;
    public static List<String> good;
    HashMap<String, List<String>> listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        content=getContentResolver();
        setContentView(R.layout.activty_quizz_response);
        scorenumber = 0;
        choix = new ArrayList<String>();
        //choix = NewQuizzQuestion.goodanswer;
        choix = Quizz.choix;
        good = new ArrayList<String>();
        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.lvExp);

        // preparing list data
        prepareListData();

        for(int i = 0; i<good.size(); i++)
        {
            if(good.get(i).compareTo(choix.get(i))== 0)
            {
                scorenumber++;
            }
        }
        listAdapter = new ExpandableListAdapterresultat(this, listDataHeader, listdescription, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);
        TextView score = (TextView) findViewById(R.id.textView30);
        score.setText("Vous avec "+ scorenumber + "/"+ good.size());
        // Listview Group click listener
        expListView.setOnGroupClickListener(new OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                // Toast.makeText(getApplicationContext(),
                // "Group Clicked " + listDataHeader.get(groupPosition),
                // Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        // Listview Group expanded listener
        expListView.setOnGroupExpandListener(new OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        listDataHeader.get(groupPosition) + " Expanded",
                        Toast.LENGTH_SHORT).show();
            }
        });

        // Listview Group collasped listener
        expListView.setOnGroupCollapseListener(new OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        listDataHeader.get(groupPosition) + " Collapsed",
                        Toast.LENGTH_SHORT).show();

            }
        });



        Button valider = (Button) findViewById(R.id.Valider);
        valider.setText("Sauvegarder");
        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Quizz sauvegardé",
                        Toast.LENGTH_SHORT).show();
                //List<String> choix; contient la liste des réponse choisie par l'utilisateur
                Intent intent = new Intent(getApplicationContext(), Menupoll.class);
                startActivity(intent);
            }
        });
    }

    /*
     * Preparing the list data
     */
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listdescription = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        good.add("The Godfather");
        good.add("The Conjuring");
        good.add("Europa Report");

        // Adding child data
        listDataHeader.add("Question 1");
        listDataHeader.add("Question 2");
        listDataHeader.add("Question 3");


        //List description des questions
        listdescription.add("Quel jour sommes nous ?");
        listdescription.add("Qui est John ?");
        listdescription.add("Suis-je gentil ?");

        // Adding child data
        List<String> Question1 = new ArrayList<String>();
        Question1.add("The Shawshank Redemption");
        Question1.add("The Godfather");
        Question1.add("The Godfather: Part II");
        Question1.add("Pulp Fiction");
        Question1.add("The Good, the Bad and the Ugly");
        Question1.add("The Dark Knight");

        List<String> Question2 = new ArrayList<String>();
        Question2.add("The Conjuring");
        Question2.add("Despicable Me 2");
        Question2.add("Turbo");
        Question2.add("Grown Ups 2");
        Question2.add("Red 2");
        Question2.add("The Wolverine");

        List<String> Question3 = new ArrayList<String>();
        Question3.add("2 Guns");
        Question3.add("The Smurfs 2");
        Question3.add("The Spectacular Now");
        Question3.add("The Canyons");
        Question3.add("Europa Report");

        listDataChild.put(listDataHeader.get(0), Question1); // Header, Child data
        listDataChild.put(listDataHeader.get(1), Question2);
        listDataChild.put(listDataHeader.get(2), Question3);





        /*
        int numberquestion = NewQuizzQuestion.numberquestion;


        // Adding child data

        if(numberquestion == 1) {
            listDataHeader.add("Question 1");
            List<String> Question1 = NewQuizzQuestion.reponsequestion1;
            listDataChild.put(listDataHeader.get(0), Question1); // Header, Child data
            return;
        }
        else if(numberquestion == 2) {
            listDataHeader.add("Question 1");
            listDataHeader.add("Question 2");
            List<String> Question1 = NewQuizzQuestion.reponsequestion1;
            List<String> Question2 = NewQuizzQuestion.reponsequestion2;
            listDataChild.put(listDataHeader.get(0), Question1); // Header, Child data
            listDataChild.put(listDataHeader.get(1), Question2);
        }
        else if(numberquestion == 3) {
            listDataHeader.add("Question 1");
            listDataHeader.add("Question 2");
            listDataHeader.add("Question 3");
            List<String> Question1 = NewQuizzQuestion.reponsequestion1;
            List<String> Question2 = NewQuizzQuestion.reponsequestion2;
            List<String> Question3 = NewQuizzQuestion.reponsequestion3;
            listDataChild.put(listDataHeader.get(0), Question1); // Header, Child data
            listDataChild.put(listDataHeader.get(1), Question2);
            listDataChild.put(listDataHeader.get(2), Question3);
        }
        else
        {
            listDataHeader.add("Question 1");
            listDataHeader.add("Question 2");
            listDataHeader.add("Question 3");
            listDataHeader.add("Question 4");
            List<String> Question1 = NewQuizzQuestion.reponsequestion1;
            List<String> Question2 = NewQuizzQuestion.reponsequestion2;
            List<String> Question3 = NewQuizzQuestion.reponsequestion3;
            List<String> Question4 = NewQuizzQuestion.reponsequestion4;
            listDataChild.put(listDataHeader.get(0), Question1); // Header, Child data
            listDataChild.put(listDataHeader.get(1), Question2);
            listDataChild.put(listDataHeader.get(2), Question3);
            listDataChild.put(listDataHeader.get(3), Question4);
        }

        //listdescription = NewQuizz.ListePropositions;
        //List description des questions
        listdescription = Quizz.listdescription;
        //listdescription.add("Qui est John ?");
        //listdescription.add("Suis-je gentil ?");

        // Adding child data
        /*
        List<String> Question1 = new ArrayList<String>();
        Question1.add("The Shawshank Redemption");
        Question1.add("The Godfather");
        Question1.add("The Godfather: Part II");
        Question1.add("Pulp Fiction");
        Question1.add("The Good, the Bad and the Ugly");
        Question1.add("The Dark Knight");

        List<String> Question2 = new ArrayList<String>();
        Question2.add("The Conjuring");
        Question2.add("Despicable Me 2");
        Question2.add("Turbo");
        Question2.add("Grown Ups 2");
        Question2.add("Red 2");
        Question2.add("The Wolverine");

        List<String> Question3 = new ArrayList<String>();
        Question3.add("2 Guns");
        Question3.add("The Smurfs 2");
        Question3.add("The Spectacular Now");
        Question3.add("The Canyons");
        Question3.add("Europa Report");
        */

    }
}
