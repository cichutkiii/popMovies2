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

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    final private ListItemClickListener mOnClickListener;
    private static int viewHolderCount;
    private List<MovieResults.ResultsBean> movieList;
    private Context context;



        public interface ListItemClickListener {
            void onListItemClick(int clickedItemIndex);
        }


    public MovieAdapter(List<MovieResults.ResultsBean> movieLists, ListItemClickListener listener) {
        movieList = movieLists;
            mOnClickListener = listener;
            viewHolderCount = 0;
        }


        @Override
        public MovieViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            context = viewGroup.getContext();
            int layoutIdForListItem = R.layout.movie_item;
            LayoutInflater inflater = LayoutInflater.from(context);
            boolean shouldAttachToParentImmediately = false;

            View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
            MovieViewHolder viewHolder = new MovieViewHolder(view);

            viewHolder.movieTitleView.setText("ViewHolder index: " + viewHolderCount);



            viewHolderCount++;

            return viewHolder;
        }

        @Override
        public void onBindViewHolder(MovieViewHolder holder, int position) {
            holder.bind(position);
        }

        @Override
        public int getItemCount() {
            return movieList.size();
        }

        class MovieViewHolder extends RecyclerView.ViewHolder
                implements View.OnClickListener {

            TextView movieTitleView;
            ImageView imageView;

            public MovieViewHolder(View itemView) {
                super(itemView);

                movieTitleView = (TextView) itemView.findViewById(R.id.title);
                imageView = (ImageView) itemView.findViewById(R.id.thumbnail);
                itemView.setOnClickListener(this);
            }

            void bind(int listIndex) {
                movieTitleView.setText(movieList.get(listIndex).getOriginal_title());
                Picasso.with(context).load(movieList.get(listIndex).getPoster_path()).into(imageView);            }


            @Override
            public void onClick(View v) {
                int clickedPosition = getAdapterPosition();
                mOnClickListener.onListItemClick(clickedPosition);

            }
        }
    }

