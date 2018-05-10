package p.poll.activity;

import android.content.ContentResolver;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import p.poll.R;
import p.poll.model.User;

import static p.poll.activity.ScreenSlidePagerActivity.NUM_PAGES;


public class ScreenSlidePageFragment extends Fragment implements View.OnClickListener{
    public User Queryuser=null;
    private int pos;
    private User oneuser;
    private View view;
    private ContentResolver content;
    private Button myButton;
    private Button addButton;
    private ImageButton imageButton;
    private TextView textView2;
    private SearchView searchView;
    public ScreenSlidePageFragment(int pos, ArrayList<User> userList, ContentResolver content)
    {
        super();
        this.pos = pos;
        this.oneuser = userList.get(pos);
        this.content=content;
    }
    public ScreenSlidePageFragment()
    {
        super();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.friendlist, container, false);
        view = inflater.inflate(R.layout.userlist, container, false);
        TextView textView = (TextView) view.findViewById(R.id.text);
        textView.setText("Nom : "+" "+oneuser.getLastName());
        textView2 = (TextView) view.findViewById(R.id.text3);
        textView2.setText("Prenom : "+" "+oneuser.getFirstName());
        searchView = view.findViewById(R.id.search2);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Queryuser=User.toHashMap(User.getFriends()).get(query);
                if(Queryuser!=null)
                {
                    Intent intent = new Intent(getContext(),Ajoutamiunique.class);
                    startActivity(intent);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });
        ImageView image = (ImageView) view.findViewById(R.id.imageView6);
        if(oneuser.getProfilePic()!=null) {
            Log.i("display",oneuser.getProfilePic());
            image.setImageBitmap(User.toBitmap(oneuser.getProfilePic(), content));
        }
        else{
            image.setImageResource(R.drawable.default_pic);
        }
        myButton = (Button) view.findViewById(R.id.buttonexit);
        myButton.setOnClickListener(this);
        addButton = view.findViewById(R.id.button3);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(User.addFriend(oneuser)) {
                    Log.i("test","friend added true");
                    ScreenSlidePagerActivity.userList.remove(oneuser);
                    NUM_PAGES--;
                    if(NUM_PAGES!=0) {
                        Intent intent = new Intent(getContext(), ScreenSlidePagerActivity.class);
                        startActivity(intent);
                        Toast.makeText(getContext(), "You added " + oneuser.getFirstName() + "!", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Intent intent = new Intent(getContext(),Menupoll.class);
                        startActivity(intent);
                        Toast.makeText(getContext(), "There is no one left to add!", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    ScreenSlidePagerActivity.userList.remove(oneuser);
                    NUM_PAGES--;
                    Intent intent = new Intent(getContext(), ScreenSlidePagerActivity.class);
                    startActivity(intent);
                    Toast.makeText(getContext(),"You are already friend with"+oneuser.getFirstName(),Toast.LENGTH_SHORT).show();
                }
            }
        });
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
