package p.poll.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.HashMap;

import p.poll.R;
import p.poll.model.User;


//import android.support.v7.app.ActionBarActivity;

public class ModifyPassword extends AppCompatActivity{

    private EditText mUsername;
    private EditText mPassword;
    private EditText mPasswordC;
    private Button changePassword;
    private UserModifyTask mAuthTask = null;
    private View mRegisterFormView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_password);
        mUsername = findViewById(R.id.username);
        mPassword = (EditText) findViewById(R.id.register_password2);
        mPasswordC = (EditText) findViewById(R.id.confirm_password);
        changePassword = (Button) findViewById(R.id.register_button);
        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attempRegister();
            }
        });

    }

    public void attempRegister()
    {
        if (mAuthTask != null) {
            return;
        }

        // Reset errors.
        mUsername.setError(null);
        mPassword.setError(null);
        mPasswordC.setError(null);

        // Store values at the time of the login attempt.
        String username = this.mUsername.getText().toString();
        String password = this.mPassword.getText().toString();
        String passwordC = this.mPasswordC.getText().toString();

        boolean cancel = false;
        View focusView = null;

        if(TextUtils.isEmpty(username))
        {
            this.mUsername.setError(getString(R.string.error_field_required));
            focusView = this.mUsername;
            cancel=true;
        }

        if(TextUtils.isEmpty(password))
        {
            this.mPassword.setError(getString(R.string.error_field_required));
            focusView = this.mPassword;
            cancel=true;
        }

        if(TextUtils.isEmpty(passwordC))
        {
            this.mPasswordC.setError(getString(R.string.error_field_required));
            focusView = this.mPasswordC;
            cancel=true;
        }

        if(!TextUtils.isEmpty(username) && !isPasswordValid(username))
        {
            this.mUsername.setError(getString(R.string.error_invalid_password));
            focusView = this.mUsername;
            cancel=true;
        }

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            this.mPassword.setError(getString(R.string.error_invalid_password));
            focusView = this.mPassword;
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
            mAuthTask = new UserModifyTask(username, password, passwordC);
            mAuthTask.execute((Void) null);
        }
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }



    public class UserModifyTask extends AsyncTask<Void, Void, Boolean> {

        private User existingUser;
        private final String username;
        private final String password;
        private final String passwordC;
        private int flag = 0;
        private String error = null;

        UserModifyTask(String username, String password, String passwordC) {
            this.username = username;
            this.password = password;
            this.passwordC = passwordC;
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

            if (User.loggedUser.getPassword().equals(password)) {
                if (this.password.equals(this.passwordC)) {
                    User.loggedUser.setPassword(this.username);
                    User.modifyUser(User.loggedUser);
                    return true;
                }
                flag = 1;
                error = getString(R.string.error_password_match);
                return false;
            }

            error = getString(R.string.error_incorrect_password);
            return false;
        }


        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;
            //showProgress(false);

            if (success) {
                goToMenu(mRegisterFormView);
            } else {
                if (flag == 1) {
                    mPasswordC.setError(error);
                    mPassword.requestFocus();
                    mPasswordC.requestFocus();
                } else {
                    mPassword.setError(error);
                    mPassword.requestFocus();
                }
            }
        }


        /**
         * Lance le menu.
         */
        public void goToMenu(View v) {
            Intent intent = new Intent(getApplicationContext(), Menupoll.class);
            startActivity(intent);
            finish();
        }
    }
}
