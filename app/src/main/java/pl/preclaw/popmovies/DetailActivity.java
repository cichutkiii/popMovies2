package pl.preclaw.popmovies;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.constraint.Guideline;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.preclaw.popmovies.Utilities.MovieResults;
import pl.preclaw.popmovies.Utilities.ReviewAdapter;
import pl.preclaw.popmovies.Utilities.ReviewResults;
import pl.preclaw.popmovies.Utilities.StaticData;
import pl.preclaw.popmovies.Utilities.TmdbInterfaces;
import pl.preclaw.popmovies.Utilities.TrailerAdapter;
import pl.preclaw.popmovies.Utilities.TrailersResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailActivity extends AppCompatActivity implements TrailerAdapter.ListItemClickListener,ReviewAdapter.ListItemClickListener {
    @BindView(R.id.movie_iv)
    ImageView movieIv;
    @BindView(R.id.title_tv)
    TextView titleTv;
    @BindView(R.id.release_date_tv)
    TextView releaseDateTv;
    @BindView(R.id.rate_tv)
    TextView rateTv;
    @BindView(R.id.plot_tv)
    TextView plotTv;
    @BindView(R.id.guideline)
    Guideline guideline;
    @BindView(R.id.vote_average)
    TextView voteAverage;
    @BindView(R.id.rv_trailers)
    RecyclerView rvTrailers;
    @BindView(R.id.trailer_loader)
    ProgressBar trailerLoader;
    @BindView(R.id.rv_reviews)
    RecyclerView rvReviews;
    @BindView(R.id.review_loader)
    ProgressBar reviewLoader;
    private MovieResults.ResultsBean movieDetails;
    private TrailerAdapter trailerAdapter;
    private ReviewAdapter reviewAdapter;
    public static String MOVIE = "Movie";
    private List<TrailersResults.ResultsList> trailers;
    private List<ReviewResults.Reviews> reviews;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        movieDetails = (MovieResults.ResultsBean) getIntent().getParcelableExtra(MOVIE);
        ButterKnife.bind(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager reviewManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        titleTv.setText(movieDetails.getOriginal_title());
        plotTv.setText(movieDetails.getOverview());
        rateTv.setText(Double.toString(movieDetails.getVote_average()));
        releaseDateTv.setText(movieDetails.getRelease_date());
        Picasso.with(this).load(movieDetails.getPoster_path()).into(movieIv);
        rvTrailers.setHasFixedSize(true);
        rvTrailers.setLayoutManager(layoutManager);
        rvReviews.setHasFixedSize(true);
        rvReviews.setLayoutManager(reviewManager);
        getTrailers();
        getReviews();


    }

    private void getTrailers() {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MainActivity.STATIC_MOVIES_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        TmdbInterfaces myInterface = retrofit.create(TmdbInterfaces.class);
        Call<TrailersResults> call = myInterface.getMovieTrailers(Integer.toString(movieDetails.getId()), StaticData.API_KEY);
        call.enqueue(new Callback<TrailersResults>() {
            @Override
            public void onResponse(Call<TrailersResults> call, Response<TrailersResults> response) {
                hideDialog();
                rvReviews.setVisibility(View.VISIBLE);
                TrailersResults trailersResults = response.body();
                trailers = trailersResults.getResults();
                setTrailerAdapter();
            }

            @Override
            public void onFailure(Call<TrailersResults> call, Throwable t) {

            }
        });
    }
    private void getReviews() {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MainActivity.STATIC_MOVIES_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        TmdbInterfaces myInterface = retrofit.create(TmdbInterfaces.class);
        Call<ReviewResults> call = myInterface.getMovieReviews(Integer.toString(movieDetails.getId()), StaticData.API_KEY);
        call.enqueue(new Callback<ReviewResults>() {
            @Override
            public void onResponse(Call<ReviewResults> call, Response<ReviewResults> response) {
                hideDialog();
                rvReviews.setVisibility(View.VISIBLE);
                ReviewResults reviewResults= response.body();
                reviews = reviewResults.getReviews();
                setReviewAdapter();
            }

            @Override
            public void onFailure(Call<ReviewResults> call, Throwable t) {

            }
        });
    }
    public void setReviewAdapter() {
        reviewAdapter = new ReviewAdapter(reviews, this);
        rvReviews.setAdapter(reviewAdapter);
    }
    public void setTrailerAdapter() {
        trailerAdapter = new TrailerAdapter(trailers, this);
        rvTrailers.setAdapter(trailerAdapter);
    }

    public void showDialog() {

        trailerLoader.setVisibility(View.VISIBLE);
    }

    public void hideDialog() {
        reviewLoader.setVisibility(View.INVISIBLE);
        trailerLoader.setVisibility(View.INVISIBLE);

    }

    @Override
    public void onTrailerItemClick(int clickedItemIndex, View v) {
        if(v.getId() == R.id.share_iv){
            Toast.makeText(this, "test", Toast.LENGTH_LONG).show();
        }else{
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube://" + trailers.get(clickedItemIndex).getKey()));
            startActivity(intent);
        }

    }

    @Override
    public void onListItemClick(int clickedItemIndex) {

    }

}
