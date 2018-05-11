package p.poll.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import p.poll.R;
import p.poll.model.Advice;
import p.poll.model.User;

/**
 * Created by Nicolas on 04/05/2018.
 */

public class resultathelp extends Activity{

    public ImageView img1;
    public ImageView img2;
    int i = 0;
    private Advice advice;
    private int currentPoll= NotificationActivity.currentPoll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        advice=Advice.getAdviceFromId(currentPoll);
        img1 = (ImageView) findViewById(R.id.imageView3);
        img2 = (ImageView) findViewById(R.id.imageView4);
        if(Advice.getAnswer(currentPoll) == 0)
        {
            img1.setBackgroundColor(getResources().getColor(android.R.color.holo_green_dark));
            img2.setBackgroundColor(getResources().getColor(android.R.color.holo_red_dark));
        }
        else {
            img1.setBackgroundColor(getResources().getColor(android.R.color.holo_red_dark));
            img2.setBackgroundColor(getResources().getColor(android.R.color.holo_green_dark));
        }
        Button bouton = (Button) findViewById(R.id.button5);
        bouton.setText("Menu");
        img1.setImageBitmap(User.toBitmap(advice.getImagePath1(),getContentResolver()));
        img2.setImageBitmap(User.toBitmap(advice.getImagePath2(),getContentResolver()));
        TextView titre = (TextView) findViewById(R.id.textView7);
        titre.setText(advice.getOwner().getFirstName() + " vous a suggéré");
        TextView description = (TextView) findViewById(R.id.textView9);
        description.setText(advice.getDescriptionQ());
        bouton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Valider
                Intent intent = new Intent(getApplicationContext(),Menu.class);
                startActivity(intent);
            }
        });
    }

}
