package p.poll.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import p.poll.R;
import android.view.View;
import android.view.WindowManager;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class LoadScreen extends AppCompatActivity {
    private ImageView image;
    private TextView title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Cette directive enlève la barre de titre
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
// Cette directive permet d'enlever la barre de notifications pour afficher l'application en plein écran
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//On définit le contenu de la vue APRES les instructions précédentes pour éviter un crash
        this.setContentView(R.layout.activity_load_screen);
        image = (ImageView)findViewById(R.id.image);
        title = (TextView)findViewById(R.id.title);
        Animation animload = AnimationUtils.loadAnimation(this,R.anim.loadanimation);
        image.startAnimation(animload);
        title.startAnimation(animload);
        Thread process = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(3000);
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                }
                catch(InterruptedException e)
                {
                    e.printStackTrace();
                }
                finally {
                    finish();
                }
            }
        };
        process.start();
    }
}
