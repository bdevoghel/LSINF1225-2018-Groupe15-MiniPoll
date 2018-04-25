package p.poll.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.util.HashMap;
import p.poll.R;
import p.poll.model.User;


public class AddFriendActivity extends AppCompatActivity {

    ImageView user_image;
    User person_object;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addfriend);
        //Getting the user list
        HashMap<String,User> userList=User.getUsers();
        // ImageViews
        user_image = (ImageView) findViewById(R.id.userImageProfile);

        Bundle data = getIntent().getExtras();
        person_object = data.getParcelable("person_object");
        // getPhoto() function returns a Base64 String
        Bitmap bmp   = person_object.getProfilePic();
        int size     = bmp.getRowBytes() * bmp.getHeight();
        ByteBuffer b = ByteBuffer.allocate(size);

        bmp.copyPixelsToBuffer(b);

        byte[] bytes = new byte[size];

        try {
            b.get(bytes, 0, bytes.length);
        } catch (BufferUnderflowException e) {
            // always happens
        }
        byte[] decodedString = Base64.decode(bytes, Base64.NO_WRAP);
        InputStream inputStream  = new ByteArrayInputStream(decodedString);
        Bitmap bitmap  = BitmapFactory.decodeStream(inputStream);
        user_image.setImageBitmap(bitmap);

        final Button button = findViewById(R.id.buttonAddFriend);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                userLogged.addFriend(userFriend);
            }
        });
    }
}