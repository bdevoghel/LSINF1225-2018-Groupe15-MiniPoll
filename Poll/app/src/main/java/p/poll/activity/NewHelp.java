package p.poll.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import p.poll.R;

/**
 * Created by Nicolas on 03/05/2018.
 */

public class NewHelp extends AppCompatActivity implements Serializable{
    public static String imagePath1=null;
    public static String imagePath2=null;
    public static String description=null;
    public static final int IMAGE_GALLERY_REQUEST = 20;
    public static final int CAMERA_REQUEST_CODE = 228;
    public static final int CAMERA_PERMISSION_REQUEST_CODE = 4192;
    private ImageView img1;
    private ImageView img2;
    View v;
    public static Drawable proposition1;
    public static Drawable proposition2;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_new_help);


        img1 = (ImageView) findViewById(R.id.img1);
        img2 = (ImageView) findViewById(R.id.img2);
        Button menu=findViewById(R.id.menu);
        menu.setOnClickListener(new  View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Menu(view);}
        });
        ImageButton annuler=findViewById(R.id.annuler);
        annuler.setOnClickListener(new  View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Menu(view);}
        });
        ImageButton prevue=findViewById(R.id.prevue);
        prevue.setOnClickListener(new  View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Prevue(view);}
        });
        Button photo1=findViewById(R.id.but1);
        menu.setOnClickListener(new  View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Menu(view);}
        });
        Button photo2=findViewById(R.id.but3);
        menu.setOnClickListener(new  View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Menu(view);}
        });
        Button gallerie1=findViewById(R.id.but2);
        menu.setOnClickListener(new  View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Menu(view);}
        });
        Button gallerie2=findViewById(R.id.but4);
        menu.setOnClickListener(new  View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Menu(view);}
        });
    }

    public void Menu(View v)
    {
        Intent intent = new Intent(getApplicationContext(),Menupoll.class);
        startActivity(intent);
    }

    public void Prevue(View v)
    {
        EditText tp = (EditText) findViewById (R.id.editText);
        description = tp.getText().toString();
        proposition1 = img1.getDrawable();
        proposition2 = img2.getDrawable();
        Intent intent = new Intent(this, Helpprevue.class);
        intent.putExtra("desc", description);
        //intent.putExtra("img1", proposition1);
        //intent.putExtra("img2", (Serializable)proposition2);
        startActivity(intent);
    }

    public void resetimage2(View v)
    {
        switch (v.getId()) {
            case R.id.img1:
                img1.setImageResource(R.drawable.proposition1);
                break;
            case R.id.img2:
                img2.setImageResource(R.drawable.proposition2);
                break;
        }
    }

    public void onTakePhotoClicked2(View v) {
        if(checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            this.v = v;
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
     * @param v
     */
    public void onImageGalleryClicked2(View v) {
        this.v = v;
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
                    if(v.getId() == R.id.but1) {
                        img1.setImageBitmap(image);
                        imagePath1 = imageUri.toString();
                    }
                    else if(v.getId() == R.id.but2) {
                        img1.setImageBitmap(image);
                        imagePath1 = imageUri.toString();
                    }
                    else if(v.getId() == R.id.but3) {
                        img2.setImageBitmap(image);
                        imagePath2 = imageUri.toString();
                    }
                    else if(v.getId() == R.id.but4) {
                        img2.setImageBitmap(image);
                        imagePath2 = imageUri.toString();
                    }
                    else
                    {

                    }

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    // show a message to the user indictating that the image is unavailable.
                    Toast.makeText(this, "Unable to open image", Toast.LENGTH_LONG).show();
                }

            }
        }
    }
}
