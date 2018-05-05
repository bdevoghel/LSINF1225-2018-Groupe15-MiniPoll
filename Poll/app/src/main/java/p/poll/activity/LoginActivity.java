package p.poll.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.os.AsyncTask;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;

import p.poll.R;
import p.poll.model.User;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {


    public static User loggedUser;
    /**
     * Id to identity READ_CONTACTS permission request.
     */
    private static final int REQUEST_READ_CONTACTS = 0;

    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello", "bar@example.com:world"
    };
    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    private UserLoginTask mAuthTask = null;

    // UI references.
    private AutoCompleteTextView mUsername;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        mUsername = (AutoCompleteTextView) findViewById(R.id.usernameRegister);

        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
    }


    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {
        if (mAuthTask != null) {
            return;
        }

        // Reset errors.
        mUsername.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String username = mUsername.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(username)) {
            mUsername.setError(getString(R.string.error_field_required));
            focusView = mUsername;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            //showProgress(true);
            mAuthTask = new UserLoginTask(username, password);
            mAuthTask.execute((Void) null);
        }
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    /*
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }
*/
    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */

    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private final String mUsername;
        private final String mPassword;

        UserLoginTask(String username, String password) {
            mUsername = username;
            mPassword = password;
            HashMap<String, User> users = User.toHashMap(User.getUsers());
            loggedUser = users.get(mUsername);
            /*
            if(loggedUser!=null)
            {
                if(loggedUser.getPassword().equals(mPassword))
                {
                    onPostExecute(true);
                }
            }
            onPostExecute(false);
            */
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
            /*
            for (String credential : DUMMY_CREDENTIALS) {
                String[] pieces = credential.split(":");
                if (pieces[0].equals(mUsername)) {
                    // Account exists, return true if the password matches.
                    return pieces[1].equals(mPassword);
                }
            }
            */
            if(loggedUser!=null) {
                if (mUsername.equals(loggedUser.getUsername())) {
                    return mPassword.equals(loggedUser.getPassword());
                }
            }
            return false;
        }


        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;
            //showProgress(false);

            if (success) {
                goToMenu(mLoginFormView);
            } else {
                mPasswordView.setError(getString(R.string.error_incorrect_password));
                mPasswordView.requestFocus();
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            //showProgress(false);
        }
    }
    /** Lance le menu. */
    public void goToMenu(View v) {
        Intent intent = new Intent(getApplicationContext(), ChargingPage.class);
        startActivity(intent);
    }
}

/*

package be.lsinf1252.myfirstappication;

/**
 * Created by Antoine on 22-04-18.
 *

public class test1 {

    public void CreationCompte(){
        String nom;
        String identifiant;
        String prenom;
        String AdresseMail;
        String MotDePasse1;
        String MotDePasse2;
        jpeg photo;

    public void setNom(String identifiant){

        String identifiant = identifiant;
    }
    public String getIdentifiant(){

        return identifiant;
    }

    public void setNom(String){
        String nom = nom;
    }
    public String getNom(){
        return nom;
    }
    public void setPrenom(String prenom){
        this.prenom = prenom;
    }
    public String getPrenom(){
        return prenom;
    }
    public void setAdressMail(String AdressMail){
        this.AdressMail = AdressMail;
    }
    public String getAdressMail(){
        return AdressMail;
    }
    public void setMotDePasse1(String MotDePasse1) {
        this.MotDePasse1 = MotDePasse1;
    }
    public String getMotDePasse1(){
        return MotDePasse1;
    }
    public void setMotDePasse2(String MotDePasse2){
        this.MotDePasse2 = MotDePasse2;
    }
    public String getMotDePasse2(){
        return MotDePasse2;
    }
    public void setPhoto(jpeg photo){
        this.photo = photo;
    }
    public void getPhoto(){
        return photo;
    }
    public boolean samePassword(){
        return getMotDePasse1() == getMotDePasse2();
    }
    public boolean identifiantExist(){
        return Exist();
    }


    /**
     * Renvoie si l'identifiant est dans la base de donnée. On va cherecher une valeur obligatoire du tableau comme ça on voit si il y en a un ou pas
     * @return null si requete sans resultat, une liste de strings contenant
     * les langages presents dans la base de donnees.
     *
    public String getLanguages() {
        SQLiteDatabase db = getReadableDatabase();
        // Les resultats de la requête sont mis dans un "curseur"
        Cursor c = db.query("\"Utilisateur\"", // La table
                new String[]{"\"nom\""},
                "\"identifiant\"", // Colonnes pour la clause WHERE // Je ne comprend pas très bien comment ca marche
                this.getIdentifiant(), // Valeurs pour la clause WHERE
                null, // ne pas grouper les lignes
                null, // ne pas filtrer par goupe de ligne
                null  // pas d'ordre
        );

        c.close();
        return Utilisateur;
    }
} // TODO : vérifier exist




    if (!samePassWord()) CreationCompte();
            if (identifiantExist()) CreationCompte();
            }




@Override
public void onCreate(SQLiteDatabase db) {

        //db.execSQL("DROP TABLE IF EXISTS \"Utilisateur\";");
        //db.execSQL("CREATE TABLE \"Utilisateur\" (\"identifiant\" TEXT , \"nom\" TEXT, \"prenom\" TEXT, \"AdressMail\" TEXT, \"MotDePasse\" TEXT, \"photo\" JPEG);");
//Deja fait normalement donc pas necessaire.
        db.execSQL("INSERT INTO \"languages\" VALUES(CreationCompte().getIdentifiant(), CreationCompte().getNOm(), CreationCompte().getprenom(), CreationCompte().AdressMail, CreationCompte().getMotDePasse1(), CreationCompte().getPhoto();");

        }

        }


  */
