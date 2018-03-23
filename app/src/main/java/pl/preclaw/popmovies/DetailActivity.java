package pl.preclaw.popmovies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.preclaw.popmovies.Utilities.Movie;

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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Movie movie = new Movie();
        movie = (Movie) getIntent().getSerializableExtra(MainActivity.MOVIE);
        ButterKnife.bind(this);

        titleTv.setText(movie.getOriginalTitle());
        plotTv.setText(movie.getPlot());
        rateTv.setText(movie.getVoteAverage());
        releaseDateTv.setText(movie.getReleaseDate());
        Picasso.with(this).load(movie.getThumbnail().toString()).into(movieIv);

    }
}
