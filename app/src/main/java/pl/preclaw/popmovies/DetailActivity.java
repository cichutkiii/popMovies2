package pl.preclaw.popmovies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.preclaw.popmovies.Utilities.MovieResults;

public class DetailActivity extends AppCompatActivity {
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
    private MovieResults.ResultsBean movieDetails;
    public static String MOVIE = "Movie";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        movieDetails = (MovieResults.ResultsBean) getIntent().getParcelableExtra(MOVIE);
        ButterKnife.bind(this);

        titleTv.setText(movieDetails.getOriginal_title());
        plotTv.setText(movieDetails.getOverview());
        rateTv.setText(Double.toString(movieDetails.getVote_average()));
        releaseDateTv.setText(movieDetails.getRelease_date());
        Picasso.with(this).load(movieDetails.getPoster_path()).into(movieIv);

    }
}
