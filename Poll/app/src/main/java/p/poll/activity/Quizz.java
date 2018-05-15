package p.poll.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.app.Activity;
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

public class Quizz extends Activity{
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    public static List<String> listdescription;
    Button bouton;
    public static List<String> choix;
    HashMap<String, List<String>> listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_quizz_response);
        bouton = (Button) findViewById(R.id.Valider);
        choix = new ArrayList<String>();
        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.lvExp);

        // preparing list data
        prepareListData();

        for(int i =0; i<listDataHeader.size(); i++)
        {
            choix.add(null);
        }

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listdescription, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);

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

            }
        });

        // Listview Group collasped listener
        expListView.setOnGroupCollapseListener(new OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {


            }
        });

        // Listview on child click listener
        expListView.setOnChildClickListener(new OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                // TODO Auto-generated method stub
                TextView txt = (TextView) v.findViewById(R.id.lblListItem);
                if(choix.get(groupPosition) == null)
                {
                    choix.set(groupPosition,txt.getText().toString());
                    v.setBackgroundColor(getResources().getColor(android.R.color.holo_green_dark));
                }
                else if(choix.get(groupPosition).toString().compareTo(txt.getText().toString()) == 0)
                {
                    Toast.makeText(
                            getApplicationContext(),choix.get(groupPosition).toString()
                            , Toast.LENGTH_SHORT)
                            .show();
                    choix.set(groupPosition,null);
                    v.setBackgroundColor(getResources().getColor(android.R.color.white));
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Vous avez déjà choisi une réponse. Recliquer dessus pour supprimer",
                            Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });

        Button valider = (Button) findViewById(R.id.Valider);
        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    boolean valid = true;
                    if(choix.size() != listDataHeader.size())
                    {
                        Toast.makeText(getApplicationContext(),"Vous n'avez pas répondu à toutes les questions.",
                                Toast.LENGTH_SHORT).show();
                    }
                    for(int i =0; i<choix.size() && valid;i++)
                    {
                        if(choix.get(i) == null)
                        {
                            valid = false;
                        }
                    }
                    if(valid)
                    {
                        Intent intent = new Intent(getApplicationContext(), resultatquizz.class);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Vous n'avez pas répondu à toutes les questions.",
                                Toast.LENGTH_SHORT).show();
                    }

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
    }
}
