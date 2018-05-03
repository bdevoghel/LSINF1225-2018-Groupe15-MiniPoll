package p.poll.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import p.poll.R;

/**
 * Created by Nicolas on 03/05/2018.
 */

public class NewHelp extends AppCompatActivity{
    Spinner spinner;
    Spinner spinner2;
    Spinner spinner3;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_new_help);

        //Récupération du Spinner déclaré dans le fichier main.xml de res/layout
        spinner = (Spinner) findViewById(R.id.spinner);
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        spinner3 = (Spinner) findViewById(R.id.spinner3);
        //Création d'une liste d'élément à mettre dans le Spinner(pour l'exemple)
        List semaine = new ArrayList();
        semaine.add("Semaine");
        for(int i =0; i<4;i++)
        {
            semaine.add(Integer.toString(i));
        }
        List jour = new ArrayList();
        jour.add("Jour");
        for(int i =0; i<7;i++)
        {
            jour.add(Integer.toString(i));
        }
        List heures = new ArrayList();
        heures.add("Heures");
        for(int i =0; i<24;i++)
        {
            heures.add(Integer.toString(i));
        }

		/*Le Spinner a besoin d'un adapter pour sa presentation alors on lui passe le context(this) et
                un fichier de presentation par défaut( android.R.layout.simple_spinner_item) -> systeme
		Avec la liste des elements */
        ArrayAdapter adapter = new ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                semaine        );
        ArrayAdapter adapter2 = new ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                jour       );
        ArrayAdapter adapter3 = new ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                heures        );


               /* On definit une présentation du spinner quand il est déroulé         (android.R.layout.simple_spinner_dropdown_item) */
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Enfin on passe l'adapter au Spinner
        spinner.setAdapter(adapter);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Enfin on passe l'adapter au Spinner
        spinner2.setAdapter(adapter2);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Enfin on passe l'adapter au Spinner
        spinner3.setAdapter(adapter3);
    }
}
