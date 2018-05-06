package p.poll.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import p.poll.R;
import p.poll.model.FriendRequest;
import p.poll.model.Notification;
import p.poll.model.User;


public class AddFriendActivity extends AppCompatActivity {

    ImageView user_image;
    User userFriend;
    ArrayList<User> userList=User.getUsers();
    String[] fname = User.getFName(userList);
    String[] lname = User.getLName(userList);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addfriend);


        user_image = (ImageView) findViewById(R.id.userImageProfile);
/*
        Bundle data = getIntent().getExtras();
        int i=0;
        userFriend = userList.get(i);
        // getPhoto() function returns a Base64 String
        Bitmap bmp   = userFriend.getProfilePic();
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
*/
        final Button button = findViewById(R.id.buttonAddFriend);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                addFriendProcess(userFriend);
            }
        });

    }

    protected void addFriendProcess(User friend){
        FriendRequest notification= new FriendRequest(User.loggedUser.getFirstName()+" "+User.loggedUser.getLastName()+" would like to add you in his friendlist",0,friend);
        friend.addNotification(notification);
    }
}