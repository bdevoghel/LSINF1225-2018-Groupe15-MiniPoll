package p.poll.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import p.poll.R;
import p.poll.model.User;

/**
 * Created by Vahid Beyraghi on 05-05-18.
 */
public class ActivityProfile extends AppCompatActivity {

    public static User loggedUser=LoginActivity.loggedUser;
    private View profileActivityView;
    private Button menu;
    private Button valider;
    private EditText profile_name;
    private EditText profile_first_name;
    private EditText profile_email;
    private ImageView profile_pic;
    private ImageButton btnImageGallery;
    private ImageButton btnImageCamera;
    private UserModifyTask mAuthTask=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compte);
        profileActivityView=findViewById(R.id.profileActivityForm);
        if(loggedUser==null)
        {
            goToLogin(profileActivityView);
            finish();
        }
        menu=findViewById(R.id.menu);
        valider=findViewById(R.id.valider);
        profile_name=findViewById(R.id.profile_name);
        profile_first_name=findViewById(R.id.profile_first_name);
        profile_email=findViewById(R.id.profile_email);
        profile_pic=findViewById(R.id.profile_pic);
        btnImageCamera= findViewById(R.id.btnImageCamera);
        btnImageGallery= findViewById(R.id.btnImageGallery);

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMenu(v);
            }
        });

        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attempModifyProfile();
            }
        });
    }

    protected void attempModifyProfile()
    {
        View focusView=null;
        boolean cancel=false;
        String firstName = this.profile_first_name.getText().toString();
        String lastName = this.profile_name.getText().toString();
        String eMail = this.profile_email.getText().toString();
        //TODO: ajouter la photo
        if (!TextUtils.isEmpty(firstName) && !TextUtils.isEmpty(lastName) && isEmailValid(eMail)) {
            loggedUser.setFirstName(firstName);
            loggedUser.setLastName(lastName);
            loggedUser.setMailAdress(eMail);
        }
        else {
            if(TextUtils.isEmpty(firstName))
            {
                profile_first_name.setError("@string/error_empty_fname");
                focusView=profile_first_name;
                cancel=true;
            }
            if(TextUtils.isEmpty(lastName))
            {
                profile_name.setError("@string/error_empty_lname");
                focusView=profile_name;
                cancel=true;
            }
            if(!isEmailValid(eMail))
            {
                profile_email.setError("@string/error_invalid_email");
                focusView=profile_email;
                cancel=true;
            }
        }
        if(cancel)
        {
            focusView.requestFocus();
        }
        else
        {
            mAuthTask = new ActivityProfile.UserModifyTask();
            mAuthTask.execute((Void) null);
        }
        //TODO: si l'image n'est pas vide, ajouter l'image, sinon ajouter l'image par défaut

        User.addUser(loggedUser);
    }



    public class UserModifyTask extends AsyncTask<Void, Void, Boolean> {

        private String error;
        private int flag;

        UserModifyTask() {
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            try {
                // Simulate network access.
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                return false;
            }
            return true;
        }


        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;
            //showProgress(false);

            if (success) {
                User.addUser(loggedUser);
                goToMenu(profileActivityView);
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
        }
    }



    private boolean isEmailValid(String email) {
        return email.contains("@")&& email.contains(".");
    }


    /** Lance le menu. */
    public void goToMenu(View v) {
        Intent intent = new Intent(getApplicationContext(), ChargingPage.class);
        startActivity(intent);
    }

    /** Lance le login. */
    public void goToLogin(View v) {
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
    }
}

