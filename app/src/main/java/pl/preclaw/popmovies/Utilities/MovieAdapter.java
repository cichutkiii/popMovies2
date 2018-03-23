package pl.preclaw.popmovies.Utilities;


import com.squareup.picasso.Picasso;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;



import java.util.ArrayList;

import pl.preclaw.popmovies.R;

public class MovieAdapter extends BaseAdapter {

    private Context context;
    private final ArrayList<Movie> movieArrayList;



    public MovieAdapter(Context context, ArrayList<Movie> movies) {

        this.context = context;
        movieArrayList = movies;
    }

    @Override
    public int getCount() {
        return movieArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolderItem viewHolderItem;

        View gridView;
        if (convertView == null) {

            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.movie_item, parent, false);

            // get layout from mobile.xml
            viewHolderItem = new ViewHolderItem();

            // set value into textview
            viewHolderItem.textViewItem = (TextView) convertView
                    .findViewById(R.id.tv_title);
            viewHolderItem.imageView = (ImageView) convertView
                    .findViewById(R.id.iv_thumbnail);
            convertView.setTag(viewHolderItem);
        }  else {
            viewHolderItem = (ViewHolderItem) convertView.getTag();
        }




            viewHolderItem.textViewItem.setText(movieArrayList.get(position).getOriginalTitle());

            // set image based on selected text

            Picasso.with(context).load(movieArrayList.get(position).getThumbnail().toString()).into(viewHolderItem.imageView);

        return convertView;
    }
}
    class ViewHolderItem{
     TextView textViewItem;
     ImageView imageView;

    }
