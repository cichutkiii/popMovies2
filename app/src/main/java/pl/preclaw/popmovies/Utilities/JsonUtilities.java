package pl.preclaw.popmovies.Utilities;

import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import static android.content.ContentValues.TAG;

public class JsonUtilities {
    private static final String STATIC_MOVIES_URL =
            "http://api.themoviedb.org/3/movie";
    private static final String STATIC_THUMBNAIL_URL =
            "http://image.tmdb.org/t/p/w185";

    private static final String API_KEY = "api_key";
    private static final String RESULTS = "results";
    private static final String ID = "id";
    private static final String VOTE_AVERAGE = "vote_average";
    private static final String ORIGINAL_TITLE = "original_title";
    private static final String POSTER_PATH = "poster_path";
    private static final String RELEASE_DATE = "release_date";
    private static final String OVERVIEW = "overview";


    public static URL buildJsonUrl(String path) {

        Uri builtUri = Uri.parse(STATIC_MOVIES_URL).buildUpon()
                .appendPath(path)
                .appendQueryParameter(API_KEY, StaticData.API_KEY)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

//        Log.v(TAG, "Built URI " + url);

        return url;
    }
    public static URL buildThumbnailUrl(String imageName) {

        Uri builtUri = Uri.parse(STATIC_THUMBNAIL_URL).buildUpon()
                .appendPath(imageName.substring(1))
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

//        Log.v(TAG, "Built URI " + url);

        return url;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }

    public static ArrayList<Movie> parseMovieJson(String json) throws JSONException{
        ArrayList<Movie> results = new ArrayList<Movie>();
        String jsonString = json;
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray jsonArray = (JSONArray) jsonObject.get(RESULTS);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonSingleObject = jsonArray.optJSONObject(i);
                Movie movie = new Movie();
                movie.setId(jsonSingleObject.optString(ID));
                movie.setOriginalTitle(jsonSingleObject.optString(ORIGINAL_TITLE));
                movie.setPlot(jsonSingleObject.optString(OVERVIEW));
                movie.setReleaseDate(jsonSingleObject.optString(RELEASE_DATE));
                movie.setThumbnail(buildThumbnailUrl(jsonSingleObject.optString(POSTER_PATH)));
                movie.setVoteAverage(jsonSingleObject.optString(VOTE_AVERAGE));
                results.add(movie);
            }
        } catch (JSONException e){
            System.err.println(e);
        }

        return results;
    }
}
