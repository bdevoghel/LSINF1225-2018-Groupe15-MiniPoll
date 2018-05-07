package p.poll.activity;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.Toast;

import p.poll.R;

/**
 * Created by Nicolas on 04/05/2018.
 */

public class Help extends Activity{

    private boolean isIn = false;
    public ImageView img1;
    public ImageView img2;
    int widthscreen;
    int heightscreen;
    int i = 0;
    int[]  img1location= new int[2];
    int[]  img2location= new int[2];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        img1 = (ImageView) findViewById(R.id.imageView3);
        img2 = (ImageView) findViewById(R.id.imageView4);
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        widthscreen = size.x;
        heightscreen = size.y;
    }

    @Override
    public void onWindowFocusChanged (boolean hasFocus) {
        img1.getLocationOnScreen(img1location);
        img2.getLocationOnScreen(img2location);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int Xbegin = 0;
        int Ybegin = 0;
        int X = (int) event.getX();
        int Y = (int) event.getY();
        int eventaction = event.getAction();

        switch (eventaction) {
            case MotionEvent.ACTION_DOWN:
                Xbegin = X;
                Ybegin = Y;
                int xx = img1location[0];
                int yy = img1location[1];
                int height = img1.getHeight();
                if(yy<Y && Y<(yy+height))
                {
                    Toast.makeText(this, "ACTION_DOWN AT COORDS "+"X: "+X+" Y: "+Y+"DEDANS" + "height = " + height, Toast.LENGTH_SHORT).show();
                }

                //Toast.makeText(this, "ACTION_DOWN AT COORDS "+"X: "+xx+" Y: "+yy+"PAS DEDANS", Toast.LENGTH_SHORT).show();
                isIn = true;

                break;

            case MotionEvent.ACTION_MOVE:
                if(isIn && X<Xbegin-widthscreen/3) {
                    Toast.makeText(this, "Slide left", Toast.LENGTH_SHORT).show();
                }
                else if(isIn && X>Xbegin+widthscreen/3)
                {
                    Toast.makeText(this, "Slide right", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    i++;
                    Toast.makeText(this, "Pas bon mouvement : "+i, Toast.LENGTH_SHORT).show();
                }
                break;

            case MotionEvent.ACTION_UP:
                //Toast.makeText(this, "ACTION_UP "+"X: "+X+" Y: "+Y, Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }
}
