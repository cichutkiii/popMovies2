package pl.preclaw.popmovies.Utilities;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TmdbInterface {

    @GET("/3/movie/{category}")
    Call<MovieResults> getDataMovies(
      @Path("category") String category,
      @Query("api_key") String api_key
    );

}
