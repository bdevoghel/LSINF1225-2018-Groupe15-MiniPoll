package p.poll.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import p.poll.R;

/**
 * Created by Vahid Beyraghi on 05-05-18.
 */
public class ActivityCompte extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("test","setcontent");
        setContentView(R.layout.activity_compte);
    }
}
