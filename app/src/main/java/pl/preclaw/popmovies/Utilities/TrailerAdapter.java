package pl.preclaw.popmovies.Utilities;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import pl.preclaw.popmovies.R;


public class TrailerAdapter extends RecyclerView.Adapter<TrailerAdapter.TrailerViewHolder> implements AdapterView.OnItemClickListener{


    final private TrailerAdapter.ListItemClickListener mOnClickListener;
    private static int viewHolderCount;
    private List<TrailersResults.ResultsList> trailerList;
    private Context context;
    private Activity activity;

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(context,Integer.toString(position),Toast.LENGTH_LONG).show();

    }


    public interface ListItemClickListener {
        void onTrailerItemClick(int clickedItemIndex, long id, View v);
    }


    public TrailerAdapter(List<TrailersResults.ResultsList> trailerLists, TrailerAdapter.ListItemClickListener listener) {
        trailerList = trailerLists;
        mOnClickListener = listener;
        viewHolderCount = 0;
    }


    @Override
    public TrailerAdapter.TrailerViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        context = viewGroup.getContext();

        int layoutIdForListItem = R.layout.trailer_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        TrailerViewHolder viewHolder = new TrailerAdapter.TrailerViewHolder(view);

        viewHolderCount++;

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(TrailerViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return trailerList.size();
    }

    class TrailerViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        TextView trailerView;
        ImageView imageView;
        TextView shareItem;

        public TrailerViewHolder(View itemView) {
            super(itemView);

            trailerView = itemView.findViewById(R.id.youtube_title);
            imageView = itemView.findViewById(R.id.youtube_thumbnail);
            shareItem = itemView.findViewById(R.id.share_iv);
            itemView.setOnClickListener(this);
            shareItem.setOnClickListener(this);
        }

        void bind(final int listIndex) {
            trailerView.setText(trailerList.get(listIndex).getName());
            Picasso.with(context).load(trailerList.get(listIndex).getYoutubeImageUrl()).into(imageView);


        }


        @Override
        public void onClick(View v) {



                int clickedPosition = getAdapterPosition();
                long id = getItemId();
                mOnClickListener.onTrailerItemClick(clickedPosition, id,v);


        }



    }
}

