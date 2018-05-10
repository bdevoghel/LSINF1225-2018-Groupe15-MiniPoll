package p.poll.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import p.poll.R;
import p.poll.model.User;

import static p.poll.activity.ScreenSlidePagerActivity.NUM_PAGES;

/**
 * Created by Nicolas on 10/05/2018.
 */

public class Ajoutamiunique extends Activity{
    public User Queryfriend=ScreenSlidePageFragment.Queryuser;
    private View view;
    private Button myButton;
    private Button myButton2;
    private TextView textView;
    private TextView textView2;
    private TextView textView3;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ajout_amis_unique);
        textView=findViewById(R.id.nomFriendUnique);
        textView2=findViewById(R.id.prenomFriendUnique);
        textView3=findViewById(R.id.emailinfoFriendUnique);
        myButton2=findViewById(R.id.buttonAddFriend);
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
        myButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(User.addFriend(Queryfriend)) {
                    Log.i("test","friend added true");
                    Intent intent = new Intent(getApplicationContext(), ScreenSlidePagerActivity.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "You added " + Queryfriend.getFirstName() + "!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Intent intent = new Intent(getApplicationContext(), ScreenSlidePagerActivity.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(),"You are already friend with"+Queryfriend.getFirstName(),Toast.LENGTH_SHORT).show();
                }
            }
        });
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ScreenSlidePagerFriendListActivity.class);
                startActivity(intent);
            }
        });
    }
}
