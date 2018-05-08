package p.poll.activity;

import android.content.ContentResolver;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import p.poll.R;
import p.poll.model.User;


public class ScreenSlidePageFriendListFragment extends Fragment implements View.OnClickListener{
    private int pos;
    private User oneuser;
    private View view;
    private ContentResolver content;
    private Button myButton;
    private Switch mySwitch;
    TextView textView2;
    public ScreenSlidePageFriendListFragment(int pos, ArrayList<User> userList, ContentResolver content)
    {
        super();
        this.pos = pos;
        this.oneuser = userList.get(pos);
        this.content=content;
    }
    public ScreenSlidePageFriendListFragment()
    {
        super();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.friendlist, container, false);
        view = inflater.inflate(R.layout.friendlist, container, false);
        TextView textView = (TextView) view.findViewById(R.id.text);
        textView.setText("Nom : "+" "+oneuser.getLastName());
        textView2 = (TextView) view.findViewById(R.id.text3);
        textView2.setText("Prenom : "+" "+oneuser.getFirstName());
        ImageView image = (ImageView) view.findViewById(R.id.imageView6);
        image.setImageBitmap(User.toBitmap(oneuser.getProfilePic(),content));
        mySwitch = view.findViewById(R.id.switch1);
        if(oneuser.getUsername().equals(User.loggedUser.getBestFriend()))
        {
            mySwitch.setChecked(true);
        }
        mySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    User.loggedUser.setBestFriend(oneuser.getUsername());
                    User.modifyUser(User.loggedUser);
                    Intent intent = new Intent(getContext(),ScreenSlidePagerFriendListActivity.class);
                    startActivity(intent);
                    Toast.makeText(getContext(),oneuser.getFirstName()+" is now your best friend!",Toast.LENGTH_LONG).show();
                }
                else{
                    User.loggedUser.setBestFriend(null);
                    User.modifyUser(User.loggedUser);
                    ScreenSlidePagerFriendListActivity.userList.remove(User.loggedUser.getBestFriend());
                    ScreenSlidePagerFriendListActivity.NUM_PAGES--;
                    Intent intent = new Intent(getContext(),ScreenSlidePagerFriendListActivity.class);
                    startActivity(intent);
                    Toast.makeText(getContext(),oneuser.getFirstName()+" is not your best friend anymore!",Toast.LENGTH_LONG).show();


                }
            }
        });
        myButton = (Button) view.findViewById(R.id.buttonexit);
        myButton.setOnClickListener(this);
        ScreenSlidePagerActivity.pos ++;
        return view;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonexit:
                Intent intent = new Intent(getActivity(), Menupoll.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }


}
