package murphy.christopher.popularmoviesstage1.interfaces;

import murphy.christopher.popularmoviesstage1.model.MovieReviews;
import murphy.christopher.popularmoviesstage1.model.Page;
import murphy.christopher.popularmoviesstage1.model.MovieTrailer;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GetMovieDataService{

    //This will get a single page worth of results
    //for popular movies
    @GET("/3/movie/popular")
    Call<Page> getPopularMovies(@Query(value="api_key") String key);

    //This will get a single page worth of results
    //for top movies
    @GET("/3/movie/top_rated")
    Call<Page> getTopRatedMovies(@Query(value="api_key") String key);

    //This will get the trailers for the movies
    @GET("/3/movie/{id}/videos")
    Call<MovieTrailer> getMovieTrailer(@Path("id") int id, @Query(value="api_key") String key);

    //This will get the reviews for the movie
    @GET("/3/movie/{id}/reviews")
    Call<MovieReviews> getMovieReviews(@Path("id") int id, @Query(value="api_key") String key);
}