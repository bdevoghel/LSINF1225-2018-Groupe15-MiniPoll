package p.poll.activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

import p.poll.R;
/**
 * Created by Nicolas on 01/05/2018.
 */

public class ChargingPage extends AppCompatActivity {
    private VideoView videoView;
    MediaController m;
    int i =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.charging_page);
        videoView = (VideoView) findViewById(R.id.videoView);
        m = new MediaController(this);
        String path = "android.resource://p.poll/" + R.raw.ring2;
        Uri uu = Uri.parse(path);
        videoView.setVideoURI(uu);
        videoView.start();
        videoView.setOnCompletionListener ( new MediaPlayer.OnCompletionListener() {

            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                i++;
                videoView.start();
                if(i>2)
                {
                    //Intent intent = new Intent(getApplicationContext(),MenuActivity.class);
                    Intent intent = new Intent(getApplicationContext(),Menupoll.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

    }
}

