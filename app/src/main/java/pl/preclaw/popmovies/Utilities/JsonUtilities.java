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

public class JsonUtilities {

    private static final String STATIC_THUMBNAIL_URL =
            "http://image.tmdb.org/t/p/w185";


    public static String buildThumbnailUrl(String imageName) {

        Uri builtUri = Uri.parse(STATIC_THUMBNAIL_URL).buildUpon()
                .appendPath(imageName.substring(1))
                .build();

        String url;

            url = builtUri.toString();

        return url;
    }

}
