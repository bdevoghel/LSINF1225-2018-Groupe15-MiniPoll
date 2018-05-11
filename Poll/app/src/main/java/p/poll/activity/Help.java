package p.poll.activity;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import p.poll.R;
import p.poll.model.Advice;

/**
 * Created by Nicolas on 04/05/2018.
 */

public class Help extends Activity{

    private boolean isInimg1 = false;
    private boolean isInimg2 = false;
    public ImageView img1;
    public ImageView img2;
    int widthscreen;
    int heightscreen;
    int i = 0;
    int[]  img1location= new int[2];
    int[]  img2location= new int[2];
    int Xbegin = 0;
    int Ybegin = 0;
    private Advice advice = ListHelp.advice;
    private int currentPoll= NotificationActivity.currentPoll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        if(NotificationActivity.currentPoll==0)
        {
            currentPoll=ListHelp.advice.getId();
        }
        else
        {
            advice=Advice.getAdviceFromId(currentPoll);
        }
        img1 = (ImageView) findViewById(R.id.imageView3);
        img2 = (ImageView) findViewById(R.id.imageView4);
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        widthscreen = size.x;
        heightscreen = size.y;
        TextView titre = (TextView) findViewById(R.id.textView7);
        titre.setText(advice.getOwner().getUsername() + " a besoin de vos conseils");
        TextView description = (TextView) findViewById(R.id.textView9);
        description.setText(advice.getDescriptionQ());
    }

    @Override
    public void onWindowFocusChanged (boolean hasFocus) {
        img1.getLocationOnScreen(img1location);
        img2.getLocationOnScreen(img2location);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int X = (int) event.getX();
        int Y = (int) event.getY();
        int eventaction = event.getAction();

        switch (eventaction) {
            case MotionEvent.ACTION_DOWN:
                Xbegin = X;
                Ybegin = Y;
                int yy2 = img2location[1];
                int yy1 = img1location[1];
                int height1 = img1.getHeight();
                int height2 = img2.getHeight();
                if(yy1<Y && Y<(yy1+height1))
                {
                    isInimg1 = true;
                    isInimg2 = false;
                }
                else if(yy2<Y && Y<(yy2+height2))
                {
                    isInimg2 = true;
                    isInimg1 = false;
                }
                else
                {
                    isInimg1 = false;
                    isInimg2= false;
                }


                break;

            case MotionEvent.ACTION_MOVE:
                if(isInimg1 && X<Xbegin-widthscreen/3) {
                    Toast.makeText(this, "Slide left image1", Toast.LENGTH_SHORT).show();
                    img1.setBackgroundColor(getResources().getColor(android.R.color.holo_red_dark));
                    img2.setBackgroundColor(getResources().getColor(android.R.color.holo_green_dark));
                    Advice.answer(currentPoll,1);

                }
                else if(isInimg1 && X>Xbegin+widthscreen/3)
                {
                    Toast.makeText(this, "Slide right image1", Toast.LENGTH_SHORT).show();
                    img2.setBackgroundColor(getResources().getColor(android.R.color.holo_red_dark));
                    img1.setBackgroundColor(getResources().getColor(android.R.color.holo_green_dark));
                    Advice.answer(currentPoll,0);
                }
                else if(isInimg2 && X<Xbegin-widthscreen/3)
                {
                    Toast.makeText(this, "Slide left image2", Toast.LENGTH_SHORT).show();
                    img2.setBackgroundColor(getResources().getColor(android.R.color.holo_red_dark));
                    img1.setBackgroundColor(getResources().getColor(android.R.color.holo_green_dark));
                    Advice.answer(currentPoll,0);
                }
                else if(isInimg2 && X>Xbegin+widthscreen/3)
                {
                    Toast.makeText(this, "Slide right image2", Toast.LENGTH_SHORT).show();
                    img1.setBackgroundColor(getResources().getColor(android.R.color.holo_red_dark));
                    img2.setBackgroundColor(getResources().getColor(android.R.color.holo_green_dark));
                    Advice.answer(currentPoll,1);
                }
                else
                {
                   // i++;
                   // Toast.makeText(this, "Pas bon mouvement : "+i, Toast.LENGTH_SHORT).show();
                }
                break;

            case MotionEvent.ACTION_UP:
                //Toast.makeText(this, "ACTION_UP "+"X: "+X+" Y: "+Y, Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }
}
