package pl.preclaw.popmovies.Utilities;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import pl.preclaw.popmovies.R;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder> {


final private ReviewAdapter.ListItemClickListener mOnClickListener;
private static int viewHolderCount;
private List<ReviewResults.Reviews> ReviewList;
private Context context;



public interface ListItemClickListener {
    void onListItemClick(int clickedItemIndex);
}


    public ReviewAdapter(List<ReviewResults.Reviews> ReviewLists, ReviewAdapter.ListItemClickListener listener) {
        ReviewList = ReviewLists;
        mOnClickListener = listener;
        viewHolderCount = 0;
    }


    @Override
    public ReviewAdapter.ReviewViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.review_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        ReviewAdapter.ReviewViewHolder viewHolder = new ReviewAdapter.ReviewViewHolder(view);

        viewHolderCount++;

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ReviewAdapter.ReviewViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return ReviewList.size();
    }

class ReviewViewHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener {

    TextView reviewAuthorView;
    TextView reviewContentView;

    public ReviewViewHolder(View itemView) {
        super(itemView);

        reviewAuthorView = (TextView) itemView.findViewById(R.id.author);
        reviewContentView = (TextView) itemView.findViewById(R.id.content);
        itemView.setOnClickListener(this);
    }

    void bind(int listIndex) {
        reviewAuthorView.setText(ReviewList.get(listIndex).getAuthor());
        reviewContentView.setText(ReviewList.get(listIndex).getContent());

    }


    @Override
    public void onClick(View v) {
        int clickedPosition = getAdapterPosition();
        mOnClickListener.onListItemClick(clickedPosition);

    }
}
}