package p.poll.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;     /* utilisé pour transmettre des données entre les activités */
import android.content.Intent;/*  Intent (='inter' en anglais) permet l'intéraction entre les activités */
import android.view.View;
import android.view.Window;
import android.widget.Button;

import p.poll.R;


public class MainActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 4000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main2);

        Button login=findViewById(R.id.loginbutton);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToMenu(view);
            }
        });

        Button RegisterButton = findViewById(R.id.register_button);
        RegisterButton.setOnClickListener(new  View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToRegister(view);}
        });


    }

    /** Lance l'activité register. */
    public void goToRegister(View v) {
        Intent intent = new Intent(this,RegisterActivity.class);
        startActivity(intent);
    }

    /** Lance l'activité ajouter un ami. */
    public void addFriend(View v) {
        Intent intent = new Intent(this,AddFriendActivity.class);
        startActivity(intent);
    }

    /** Lance le menu. */
    public void goToMenu(View v) {
        Intent intent = new Intent(getApplicationContext(), MenuActivity2.class);
        startActivity(intent);
    }

    /** Lance le login. */
    public void goToLogin(View v) {
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
    }
}
