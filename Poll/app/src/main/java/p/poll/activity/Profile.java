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

import p.poll.R;
import p.poll.model.User;

import static p.poll.activity.RegisterActivity.CAMERA_PERMISSION_REQUEST_CODE;
import static p.poll.activity.RegisterActivity.CAMERA_REQUEST_CODE;
import static p.poll.activity.RegisterActivity.IMAGE_GALLERY_REQUEST;

/**
 * Created by Vahid Beyraghi on 05-05-18.
 */
public class Profile extends AppCompatActivity {

    private View profileActivityView;
    private Button menu;
    private Button valider;
    private EditText profile_name;
    private EditText profile_first_name;
    private EditText profile_email;
    private ImageView profile_pic;
    private ImageButton btnImageGallery;
    private ImageButton btnImageCamera;
    private UserModifyTask mAuthTask = null;
    private String imagePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        User.refreshLoggedUser();
        if (User.loggedUser == null) {
            goToLogin(profileActivityView);
            finish();
        }
        menu = findViewById(R.id.menu);
        valider = findViewById(R.id.valider);
        profile_name = findViewById(R.id.profile_name);
        profile_first_name = findViewById(R.id.profile_first_name);
        profile_email = findViewById(R.id.profile_email);
        profile_pic = findViewById(R.id.profile_pic);
        btnImageCamera = findViewById(R.id.btnImageCamera);
        btnImageGallery = findViewById(R.id.btnImageGallery);
        profile_name.setText(User.loggedUser.getLastName());
        profile_first_name.setText((User.loggedUser.getFirstName()));
        profile_email.setText(User.loggedUser.getMailAdress());
        if ((User.loggedUser.getProfilePic() != null)) {
            Log.i("display","setting bitmap picture");
            profile_pic.setImageBitmap(User.toBitmap(User.loggedUser.getProfilePic(), getContentResolver()));
        }
        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attempModifyProfile();
            }
        });
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMenu(v);
            }
        });
    }

    protected void attempModifyProfile() {
        View focusView = null;
        boolean cancel = false;
        String firstName = this.profile_first_name.getText().toString();
        String lastName = this.profile_name.getText().toString();
        String eMail = this.profile_email.getText().toString();
        //TODO: ajouter la photo

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
            User.loggedUser.setProfilePic(imagePath);
        } else {
            if (TextUtils.isEmpty(firstName)) {
                profile_first_name.setError("@string/error_empty_fname");
                focusView = profile_first_name;
                cancel = true;
            }
            if (TextUtils.isEmpty(lastName)) {
                profile_name.setError("@string/error_empty_lname");
                focusView = profile_name;
                cancel = true;
            }
            if (!isEmailValid(eMail)) {
                profile_email.setError("@string/error_invalid_email");
                focusView = profile_email;
                cancel = true;
            }
        }
        if (cancel) {
            focusView.requestFocus();
        } else {
            mAuthTask = new Profile.UserModifyTask();
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
                Log.i("display","modifying user");
                User.modifyUser(User.loggedUser);
                goToMenu(profileActivityView);
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
        }
    }


    private boolean isEmailValid(String email) {
        return email.contains("@") && email.contains(".");
    }


    /**
     * Lance le menu.
     */
    public void goToMenu(View v) {
        Intent intent = new Intent(getApplicationContext(), Menupoll.class);
        startActivity(intent);
        finish();
    }

    /**
     * Lance le login.
     */
    public void goToLogin(View v) {
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
    }


    public void resetimage(View v) {
        profile_pic.setImageResource(R.drawable.userimage);
    }

    public void onTakePhotoClicked(View v) {
        if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            invokeCamera();
        } else {
            // let's request permission.
            String[] permissionRequest = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
            requestPermissions(permissionRequest, CAMERA_PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAMERA_PERMISSION_REQUEST_CODE) {
            // we have heard back from our request for camera and write external storage.
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                invokeCamera();
            } else {
                Toast.makeText(this, R.string.cannotopencamera, Toast.LENGTH_LONG).show();
            }
        }
    }

    private void invokeCamera() {

        // get a file reference
        Uri pictureUri = FileProvider.getUriForFile(this, getApplicationContext().getPackageName() + ".provider", createImageFile());

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        // tell the camera where to save the image.
        intent.putExtra(MediaStore.EXTRA_OUTPUT, pictureUri);

        // tell the camera to request WRITE permission.
        intent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);

        startActivityForResult(intent, CAMERA_REQUEST_CODE);

    }

    private File createImageFile() {
        // the public picture director
        File picturesDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

        // timestamp makes unique name.
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String timestamp = sdf.format(new Date());

        // put together the directory and the timestamp to make a unique image location.
        File imageFile = new File(picturesDirectory, "picture" + timestamp + ".jpg");

        return imageFile;
    }

    /**
     * This method will be invoked when the user clicks a button
     *
     * @param v
     */
    public void onImageGalleryClicked(View v) {
        // invoke the image gallery using an implict intent.
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);

        // where do we want to find the data?
        File pictureDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        String pictureDirectoryPath = pictureDirectory.getPath();
        // finally, get a URI representation
        Uri data = Uri.parse(pictureDirectoryPath);

        // set the data and type.  Get all image types.
        photoPickerIntent.setDataAndType(data, "image/*");

        // we will invoke this activity, and get something back from it.
        startActivityForResult(photoPickerIntent, IMAGE_GALLERY_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == CAMERA_REQUEST_CODE) {
                Toast.makeText(this, "Image Saved.", Toast.LENGTH_LONG).show();
            }
            // if we are here, everything processed successfully.
            if (requestCode == IMAGE_GALLERY_REQUEST) {
                // if we are here, we are hearing back from the image gallery.

                // the address of the image on the SD Card.
                Uri imageUri = data.getData();

                // declare a stream to read the image data from the SD Card.
                InputStream inputStream;

                // we are getting an input stream, based on the URI of the image.
                try {
                    inputStream = getContentResolver().openInputStream(imageUri);

                    // get a bitmap from the stream.
                    Bitmap image = BitmapFactory.decodeStream(inputStream);


                    // show the image to the user
                    profile_pic.setImageBitmap(image);
                    imagePath = String.valueOf(imageUri);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    // show a message to the user indictating that the image is unavailable.
                    Toast.makeText(this, "Unable to open image", Toast.LENGTH_LONG).show();
                }

            }
        }

    }
}

