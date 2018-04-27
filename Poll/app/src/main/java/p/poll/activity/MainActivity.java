package p.poll.activity;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle; /* utilisé pour transmettre des données entre les activités */
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
        setContentView(R.layout.activity_main);

        Button login=findViewById(R.id.loginbutton);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToLogin(view);
            }
        });

        Button SignInButton = findViewById(R.id.sign_in_button);
        SignInButton.setOnClickListener(new  View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToRegister(view);}
        });


    }

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
        Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
        startActivity(intent);
    }

    /** Lance le login. */
    public void goToLogin(View v) {
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
    }
}
