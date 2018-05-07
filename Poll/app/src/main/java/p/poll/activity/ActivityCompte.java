package p.poll.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import p.poll.R;
import p.poll.model.User;

import static p.poll.activity.RegisterActivity.CAMERA_PERMISSION_REQUEST_CODE;
import static p.poll.activity.RegisterActivity.CAMERA_REQUEST_CODE;
import static p.poll.activity.RegisterActivity.IMAGE_GALLERY_REQUEST;

/**
 * Created by Vahid Beyraghi on 05-05-18.
 */
public class ActivityCompte extends AppCompatActivity {

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
        if(User.loggedUser==null)
        {
            goToLogin(profileActivityView);
            finish();
        }
        valider=findViewById(R.id.valider);
        profile_name=findViewById(R.id.profile_name);
        profile_first_name=findViewById(R.id.profile_first_name);
        profile_email=findViewById(R.id.profile_email);
        profile_pic=findViewById(R.id.profile_pic);
        btnImageCamera= findViewById(R.id.btnImageCamera);
        btnImageGallery= findViewById(R.id.btnImageGallery);

        profile_pic.setImageBitmap(User.toBitmap(RegisterActivity.imagePath,getContentResolver()));

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

        if (mAuthTask != null) {
            return;
        }

        // Reset errors.
        profile_first_name.setError(null);
        profile_name.setError(null);
        profile_email.setError(null);

        if (!TextUtils.isEmpty(firstName) && !TextUtils.isEmpty(lastName) && isEmailValid(eMail)) {
            User.loggedUser.setFirstName(firstName);
            User.loggedUser.setLastName(lastName);
            User.loggedUser.setMailAdress(eMail);
            User.loggedUser.setProfilePic(RegisterActivity.imagePath);
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
            mAuthTask = new ActivityCompte.UserModifyTask();
            mAuthTask.execute((Void) null);
        }
        //TODO: si l'image n'est pas vide, ajouter l'image, sinon ajouter l'image par défaut
    }



    public class UserModifyTask extends AsyncTask<Void, Void, Boolean> {

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
                User.addUser(User.loggedUser);
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
        finish();
    }

    /** Lance le login. */
    public void goToLogin(View v) {
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
    }
}

