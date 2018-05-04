package p.poll.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import p.poll.R;

/**
 * Created by Nicolas on 03/05/2018.
 */

public class Helpprevue extends AppCompatActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        TextView desc = (TextView) findViewById(R.id.textView9);
        Intent intent= getIntent();
        Bundle b = intent.getExtras();
        desc.setText((String) b.get("desc"));
        ImageView proposition1 = (ImageView) findViewById(R.id.imageView3);
        proposition1.setImageDrawable(NewHelp.proposition1);
        ImageView proposition2 = (ImageView) findViewById(R.id.imageView4);
        proposition2.setImageDrawable(NewHelp.proposition2);
        Button but = (Button) findViewById(R.id.button4);

    }
}
