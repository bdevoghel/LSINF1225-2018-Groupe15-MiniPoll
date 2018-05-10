package p.poll.activity;

import android.app.Activity;
import android.content.ContentResolver;
import android.os.Bundle;

/**
 * Created by Nicolas on 04/05/2018.
 */

public class Sondageprevue extends Activity{
    public static ContentResolver content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        content=getContentResolver();
    }
}
