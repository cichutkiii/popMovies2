package pl.preclaw.popmovies;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.preclaw.popmovies.Utilities.MovieAdapter;
import pl.preclaw.popmovies.Utilities.MovieResults;
import pl.preclaw.popmovies.Utilities.MoviesContract;
import pl.preclaw.popmovies.Utilities.StaticData;
import pl.preclaw.popmovies.Utilities.TmdbInterfaces;
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
    private static final String FAVOURITES = "favourites";
    public static final String STATIC_MOVIES_URL =
            "http://api.themoviedb.org/";
    private List<MovieResults.ResultsBean> movieList;
    private MovieAdapter mAdapter;
    private MovieResults.ResultsBean movieDetails;
    private MovieResults.ResultsBean movieItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        rvMovies.setHasFixedSize(true);
        lLayout = new GridLayoutManager(MainActivity.this, 2);
        rvMovies.setLayoutManager(lLayout);
        getMovieData(TOP_RATED);

    }
    private void getMovieData(String category){
        if(category == FAVOURITES){

            movieList.clear();
            Cursor mCursor = getContentResolver().query(MoviesContract.MovieEntry.CONTENT_URI,
                    null,
                    null,null,null);
            for(mCursor.moveToFirst(); !mCursor.isAfterLast(); mCursor.moveToNext()) {
                // The Cursor is now set to the right position
                movieItem = new MovieResults.ResultsBean();
                movieItem.setId(mCursor.getInt(1));
                movieItem.setVote_average(mCursor.getDouble(2));
                movieItem.setPoster_path(mCursor.getString(3));
                movieItem.setOriginal_title(mCursor.getString(4));
                movieItem.setOverview(mCursor.getString(5));
                movieItem.setRelease_date(mCursor.getString(6));
                movieList.add(movieItem);
            }
            loadMovieData();
        } else {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(STATIC_MOVIES_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            TmdbInterfaces myInterface = retrofit.create(TmdbInterfaces.class);
            Call<MovieResults> call = myInterface.getDataMovies(category, StaticData.API_KEY);
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
            getMovieData(POPULAR);

            return true;
        }
        if (id == R.id.action_top_rated) {
            getMovieData(TOP_RATED);

            return true;
        }
        if (id == R.id.action_favourites) {
            getMovieData(FAVOURITES);

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
