package p.poll.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.Toast;

import p.poll.R;

/**
 * Created by Nicolas on 04/05/2018.
 */

public class Quizz extends Activity{
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    List<String> listdescription;
    HashMap<String, List<String>> listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_quizz_response);

        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.lvExp);

        // preparing list data
        prepareListData();

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

        // Listview on child click listener
        expListView.setOnChildClickListener(new OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                // TODO Auto-generated method stub
                Toast.makeText(
                        getApplicationContext(),
                        listDataHeader.get(groupPosition)
                                + " : "
                                + listDataChild.get(
                                listDataHeader.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT)
                        .show();
                return false;
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
        listDataHeader.add("Quel jour sommes nous ?");
        listDataHeader.add("Qui est John ?");
        listDataHeader.add("Suis-je gentil ?");

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
