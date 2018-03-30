package pl.preclaw.popmovies;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
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

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.gridview)
    GridView gridview;
    @BindView(R.id.pb_loader)
    ProgressBar pbLoader;
    @BindView(R.id.tv_error)
    TextView tvError;
    private String jsonResponse;
    public static String MOVIE = "Movie";
    private static final String TOP_RATED = "top_rated";
    private static final String POPULAR = "popular";
    private static final String STATIC_MOVIES_URL =
            "http://api.themoviedb.org/";
    private List<MovieResults.ResultsBean> movieList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
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
                gridview.setAdapter(new MovieAdapter(getApplicationContext(), movieList));

                gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> parent, View v,
                                            int position, long id) {
//                Movie tempMovie = movieArrayList[position];
//                Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
//                intent.putExtra(MOVIE, tempMovie);
//                startActivity(intent);

                    }
                });

            }

            @Override
            public void onFailure(Call<MovieResults> call, Throwable t) {
                t.printStackTrace();
            }
        });

        loadMovieData();
    }
    private void loadMovieData(){
//        showMovieDataView();
//        new FetchMoviesData().execute(TOP_RATED);
    }

    private void showMovieDataView(){
        tvError.setVisibility(View.INVISIBLE);
        gridview.setVisibility(View.VISIBLE);
    }
    private void showErrorMessage(){
        gridview.setVisibility(View.INVISIBLE);
        tvError.setVisibility(View.VISIBLE);
    }

    public class FetchMoviesData extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pbLoader.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... params) {
            if (params.length == 0) {
                return null;
            }
            String whatMovies = params[0];
            URL moviesRequestUrl = JsonUtilities.buildJsonUrl(whatMovies);

            try {
//                jsonResponse = JsonUtilities.getResponseFromHttpUrl(moviesRequestUrl);


                return jsonResponse;

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String s) {
            pbLoader.setVisibility(View.INVISIBLE);
            if (s != null) {

//                try {
//                    movieList = JsonUtilities.parseMovieJson(jsonResponse);
////                    Log.d("dane", movieArrayList[0].getPlot());
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                gridview.setAdapter(new MovieAdapter(getApplicationContext(), movieArrayList));
//
//                gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                    public void onItemClick(AdapterView<?> parent, View v,
//                                            int position, long id) {
//                        Movie tempMovie = movieArrayList[position];
//                        Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
//                        intent.putExtra(MOVIE, tempMovie);
//                        startActivity(intent);
//
//                    }
//                });
            } else{
                showErrorMessage();
            }
        }

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
            new FetchMoviesData().execute(POPULAR);
            return true;
        }
        if (id == R.id.action_top_rated) {
            new FetchMoviesData().execute(TOP_RATED);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
