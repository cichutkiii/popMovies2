package pl.preclaw.popmovies;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.net.URL;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.preclaw.popmovies.Utilities.JsonUtilities;
import pl.preclaw.popmovies.Utilities.MovieAdapter;
import pl.preclaw.popmovies.Utilities.MovieResults;
import pl.preclaw.popmovies.Utilities.StaticData;
import pl.preclaw.popmovies.Utilities.TmdbInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements MovieAdapter.ListItemClickListener{


    @BindView(R.id.pb_loader)
    ProgressBar pbLoader;
    @BindView(R.id.tv_error)
    TextView tvError;
    @BindView(R.id.rv_movies)
    RecyclerView rvMovies;

    private GridLayoutManager lLayout;

    public static String MOVIE = "Movie";
    private static final String TOP_RATED = "top_rated";
    private static final String POPULAR = "popular";
    private static final String STATIC_MOVIES_URL =
            "http://api.themoviedb.org/";
    private List<MovieResults.ResultsBean> movieList;
    private MovieAdapter mAdapter;
    private MovieResults.ResultsBean movieDetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvMovies.setHasFixedSize(true);
        lLayout = new GridLayoutManager(MainActivity.this, 2);
        rvMovies.setLayoutManager(lLayout);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(STATIC_MOVIES_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        TmdbInterface myInterface = retrofit.create(TmdbInterface.class);
        Call<MovieResults> call = myInterface.getDataMovies(TOP_RATED, StaticData.API_KEY);
        call.enqueue(new Callback<MovieResults>() {


            @Override
            public void onResponse(Call<MovieResults> call, Response<MovieResults> response) {
                MovieResults movieResults = response.body();
                movieList = movieResults.getResults();
               loadMovieData();

            }

            @Override
            public void onFailure(Call<MovieResults> call, Throwable t) {
                t.printStackTrace();
                showErrorMessage();
            }
        });

    }

    private void loadMovieData() {
        mAdapter = new MovieAdapter(movieList,this);
        rvMovies.setAdapter(mAdapter);
    }

    private void showMovieDataView() {
        tvError.setVisibility(View.INVISIBLE);
        rvMovies.setVisibility(View.VISIBLE);
    }

    private void showErrorMessage() {
        rvMovies.setVisibility(View.INVISIBLE);
        tvError.setVisibility(View.VISIBLE);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_popular) {
            return true;
        }
        if (id == R.id.action_top_rated) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        movieDetails = movieList.get(clickedItemIndex);
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(MOVIE,movieDetails);
        startActivity(intent);

    }
}
