package murphy.christopher.popularmoviesstage1.fragments;

import android.content.Context;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Parcel;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import org.parceler.Parcels;

import murphy.christopher.popularmoviesstage1.BuildConfig;
import murphy.christopher.popularmoviesstage1.interfaces.GetMovieDataService;
import murphy.christopher.popularmoviesstage1.model.MovieReviews;
import murphy.christopher.popularmoviesstage1.util.Constants;
import murphy.christopher.popularmoviesstage1.util.RetrofitUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MovieReviewsFragment extends Fragment {

    private boolean isHorizontal;
    private int movieId;
    private MovieReviews reviews;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = this.getArguments();
        if((args != null) && (savedInstanceState == null)){
            this.isHorizontal = args.getBoolean(Constants.SCREEN_POSITION_KEY);
            this.movieId = args.getInt(Constants.MOVIE_ID);
            getMovieReviews(this.movieId);
        }else if(savedInstanceState != null){
            this.isHorizontal = savedInstanceState.getBoolean(Constants.SCREEN_POSITION_KEY);
            this.movieId = savedInstanceState.getInt(Constants.MOVIE_ID);
            this.reviews = Parcels.unwrap(savedInstanceState.getParcelable(Constants.MOVIE_REVIEWS));
        }
    }
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putBoolean(Constants.SCREEN_POSITION_KEY, isHorizontal);
        outState.putInt(Constants.MOVIE_ID, movieId);
        outState.putParcelable(Constants.MOVIE_REVIEWS, Parcels.wrap(reviews));
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE){
            isHorizontal = true;
        }
        else{
            isHorizontal = false;
        }
    }

    public void getMovieReviews(int id){
        //Get a retrofit instance for this task
        Retrofit retrofitInstance = RetrofitUtil.getRetrofitInstance();

        //Create the service to get the movie data
        GetMovieDataService service = retrofitInstance.create(GetMovieDataService.class);

        Call<MovieReviews> reviewsAsync = service.getMovieReviews(id, BuildConfig.MOVIE_DB_API_KEY);

        if(hasInternetService()) {
            reviewsAsync.enqueue(new Callback<MovieReviews>() {
                @Override
                public void onResponse(Call<MovieReviews> call, Response<MovieReviews> response) {
                    reviews = response.body();
                }

                @Override
                public void onFailure(Call<MovieReviews> call, Throwable t) {

                }
            });
        }
    }

    public boolean hasInternetService(){
        boolean hasService = false;

        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        if(activeNetwork != null && activeNetwork.isAvailable() == true
                && activeNetwork.isConnected()){
            hasService = true;
        }

        return hasService;
    }
}