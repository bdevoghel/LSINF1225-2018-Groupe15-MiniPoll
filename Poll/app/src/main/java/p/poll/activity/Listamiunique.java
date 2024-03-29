package p.poll.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import p.poll.R;
import p.poll.model.User;

/**
 * Created by Nicolas on 10/05/2018.
 */

public class Listamiunique extends Activity{

    public User Queryfriend=ScreenSlidePageFriendListFragment.Queryfriend;
    private View view;
    private Button myButton;
    private Switch mySwitch;
    private TextView textView;
    private TextView textView2;
    private TextView textView3;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_amis_unique);
        textView=findViewById(R.id.nomFriendUnique);
        textView2=findViewById(R.id.prenomFriendUnique);
        textView3=findViewById(R.id.emailinfoFriendUnique);
        mySwitch=findViewById(R.id.switch2);
        myButton=findViewById(R.id.buttonexit2);
        imageView=findViewById(R.id.imageViewFriendUnique);
        textView.setText("Nom : "+" "+Queryfriend.getLastName());
        textView2.setText("Prenom : "+" "+Queryfriend.getFirstName());
        textView3.setText("Email : "+" "+Queryfriend.getMailAdress());
        if(Queryfriend.getProfilePic()!=null)
        {
            imageView.setImageBitmap(User.toBitmap(Queryfriend.getProfilePic(),getContentResolver()));
        }
        else
        {
            imageView.setImageResource(R.drawable.default_pic);
        }
        if(Queryfriend.getUsername().equals(User.loggedUser.getBestFriend()))
        {
            mySwitch.setChecked(true);
        }
        mySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    User.loggedUser.setBestFriend(Queryfriend.getUsername());
                    User.modifyUser(User.loggedUser);
                    Toast.makeText(getApplicationContext(),Queryfriend.getFirstName()+" is now your best friend!",Toast.LENGTH_SHORT).show();
                }
                else{
                    User.loggedUser.setBestFriend(null);
                    User.modifyUser(User.loggedUser);
                    Toast.makeText(getApplicationContext(),Queryfriend.getFirstName()+" is not your best friend anymore!",Toast.LENGTH_SHORT).show();
                }
            }
        });
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Menupoll.class);
                startActivity(intent);
            }
        });
    }
}