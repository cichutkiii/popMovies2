package pl.preclaw.popmovies.Utilities;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TmdbInterfaces {

    @GET("/3/movie/{category}")
    Call<MovieResults> getDataMovies(
      @Path("category") String category,
      @Query("api_key") String api_key
    );

    @GET("/3/movie/{id}/videos")
    Call<TrailersResults> getMovieTrailers(
            @Path("id") String id,
            @Query("api_key") String api_key
    );

}
