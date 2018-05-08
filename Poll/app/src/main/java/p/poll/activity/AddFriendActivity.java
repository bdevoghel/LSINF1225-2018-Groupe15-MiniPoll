package p.poll.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import java.util.ArrayList;

import p.poll.R;
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

        final Button button = findViewById(R.id.buttonAddFriend);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                addFriendProcess(userFriend);
            }
        });
*/
    }
}