package pl.preclaw.popmovies.Utilities;

import android.net.Uri;
import android.util.Log;

import com.google.gson.Gson;

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

public class ParsingUtilities {

    private static final String STATIC_THUMBNAIL_URL =
            "http://image.tmdb.org/t/p/w185";
    private static final String STATIC_YOUTUBE_URL =
            "https://img.youtube.com/vi/";
    private static final String STATIC_YOUTUBE_ADDRESS_URL =
            "https://www.youtube.com/watch";
    public static String buildThumbnailUrl(String imageName) {

        Uri builtUri = Uri.parse(STATIC_THUMBNAIL_URL).buildUpon()
                .appendPath(imageName.substring(1))
                .build();

        String url;

            url = builtUri.toString();

        return url;
    }

    public static String buildYoutubeUrl(String imageName) {

        Uri builtUri = Uri.parse(STATIC_YOUTUBE_URL).buildUpon()
                .appendPath(imageName)
                .appendPath("mqdefault.jpg")
                .build();

        String url;

        url = builtUri.toString();

        return url;
    }
    public static String buildYoutubeAddressUrl(String key) {

        Uri builtUri = Uri.parse(STATIC_YOUTUBE_ADDRESS_URL).buildUpon()

                .appendQueryParameter("v",key)
                .build();

        String url;

        url = builtUri.toString();

        return url;
    }

}
