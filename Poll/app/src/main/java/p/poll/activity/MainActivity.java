package p.poll;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle; /* utilisé pour transmettre des données entre les activités */
import android.content.Intent;/*  Intent (='inter' en anglais) permet l'intéraction entre les activités */
import android.view.View;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }




     /** Lance l'activité ajouter un ami. */
     public void addFriend(View v) {
         Intent intent = new Intent(this, AddFriendActivity.class);
         startActivity(intent);
     }
}