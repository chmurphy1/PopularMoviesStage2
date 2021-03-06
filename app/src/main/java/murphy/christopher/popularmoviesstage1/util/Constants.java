package murphy.christopher.popularmoviesstage1.util;

/*
 * The purpose of this class is for the
 * creation of constants.  Anything language
 * dependent should not goi in here.
 */
public class Constants {

    public static final String BASE_URL = "http://api.themoviedb.org/";
    public static final String MOVIE_URL_W185 = "http://image.tmdb.org/t/p/w185/";
    public static final String MOVIE_KEY = "movie_details";
    public static final String PAGE_ADAPTER = "page_adapter";
    public static final String REVIEW_ADAPTER = "review_adapter";
    public static final String TRAILER_ADAPTER = "trailer_adapter";
    public static final String SPINNER_POSITION = "spinner_position";
    public static final String MOVIE_ID = "movie_id";
    public static final String MOVIE_REVIEWS = "movie_reviews";
    public static final String MOVIE_TRAILERS = "movie_trailers";
    public static final String NO_REVIEWS = "No available reviews";
    public static final String NO_TRAILERS = "No available trailers";
    public static final String TRAILER_KEY = "movie_trailer";
    public static final String MOVIE_TITLE = "title";
    public static final String BLANKS = "";
    public static final String DB_NAME = "Popular Movie DB";

    public static final int FAVORITES = 2;
    public static final int TOP_RATED = 1;
    public static final int POPULAR_MOVIES = 0;
    public static final int NUM_MOVIE_DETAIL_TABS = 3;

    //This should be moved into a property file later for
    //localization
    public static final String DATE_FORMAT = "MMMM d, yyyy";
    public static final String Favorite_ERROR = "The movie is already in your favorites.";
}
