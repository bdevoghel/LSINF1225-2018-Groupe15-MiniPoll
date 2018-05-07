package p.poll.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.Toast;

import p.poll.R;

/**
 * Created by Nicolas on 04/05/2018.
 */

public class Help extends Activity{

    private boolean isTouch = false;
    public ImageView img1;
    public ImageView img2;
    int[]  img1location= new int[2];
    int[]  img2location= new int[2];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        img1 = (ImageView) findViewById(R.id.imageView3);
        img2 = (ImageView) findViewById(R.id.imageView4);
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
                int xx = img1location[0];
                int yy = img1location[1];
                int height = img1.getHeight();
                if(yy<Y && Y<(yy+height))
                {
                    Toast.makeText(this, "ACTION_DOWN AT COORDS "+"X: "+X+" Y: "+Y+"DEDANS" + "height = " + height, Toast.LENGTH_SHORT).show();
                }

                //Toast.makeText(this, "ACTION_DOWN AT COORDS "+"X: "+xx+" Y: "+yy+"PAS DEDANS", Toast.LENGTH_SHORT).show();
                isTouch = true;
                break;

            case MotionEvent.ACTION_MOVE:
                //Toast.makeText(this, "MOVE "+"X: "+X+" Y: "+Y, Toast.LENGTH_SHORT).show();
                break;

            case MotionEvent.ACTION_UP:
                //Toast.makeText(this, "ACTION_UP "+"X: "+X+" Y: "+Y, Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }
}
