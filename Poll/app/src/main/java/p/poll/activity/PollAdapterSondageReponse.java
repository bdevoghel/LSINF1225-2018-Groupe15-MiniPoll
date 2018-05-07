package p.poll.activity;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import p.poll.R;

/**
 * Created by Antoine on 03/05/2018.
 */

public class PollAdapterSondageReponse extends ArrayAdapter<String> {

    //tweets est la liste des models à afficher
    public PollAdapterSondageReponse(Context context, List<String> tweets) {
        super(context, 0, tweets);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_sondage,parent, false);
        }

        PollViewHolder viewHolder = (PollViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new PollViewHolder();
            viewHolder.pseudo = (TextView) convertView.findViewById(R.id.pseudo);
            viewHolder.text = (TextView) convertView.findViewById(R.id.text);
            convertView.setTag(viewHolder);
        }

        //getItem(position) va récupérer l'item [position] de la List<Tweet> tweets
        String description = getItem(position);

        //il ne reste plus qu'à remplir notre vue
        viewHolder.pseudo.setText(Sondage.web[position]);
        viewHolder.text.setText(description);


        return convertView;
    }

    private class PollViewHolder{
        public TextView pseudo;
        public TextView text;
        public ImageView avatar;
    }
}