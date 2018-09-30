package murphy.christopher.popularmoviesstage1.fragments;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import org.parceler.Parcels;
import butterknife.BindView;
import butterknife.ButterKnife;
import murphy.christopher.popularmoviesstage1.BuildConfig;
import murphy.christopher.popularmoviesstage1.R;
import murphy.christopher.popularmoviesstage1.adapters.ReviewAdapter;
import murphy.christopher.popularmoviesstage1.interfaces.GetMovieDataService;
import murphy.christopher.popularmoviesstage1.model.MovieReviews;
import murphy.christopher.popularmoviesstage1.model.ReviewResults;
import murphy.christopher.popularmoviesstage1.util.Constants;
import murphy.christopher.popularmoviesstage1.util.RetrofitUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MovieReviewsFragment extends Fragment {

    private int movieId;

    private MovieReviews reviews;
    private ReviewAdapter rAdapter;

    @BindView(R.id.ReviewRecycler)
    RecyclerView mReviewRecylcer;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = this.getArguments();
        if((args != null) && (savedInstanceState == null)){
            this.movieId = args.getInt(Constants.MOVIE_ID);
        }else if(savedInstanceState != null){
            this.movieId = savedInstanceState.getInt(Constants.MOVIE_ID);
            this.reviews = Parcels.unwrap(savedInstanceState.getParcelable(Constants.MOVIE_REVIEWS));
            rAdapter = Parcels.unwrap(savedInstanceState.getParcelable(Constants.REVIEW_ADAPTER));
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View reviewView = inflater.inflate(R.layout.fragment_movie_reviews,container,false);
        ButterKnife.bind(this, reviewView);

        return reviewView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        LinearLayoutManager layoutmanager = new LinearLayoutManager(getContext());
        mReviewRecylcer.setLayoutManager(layoutmanager);
        mReviewRecylcer.setHasFixedSize(true);

        if(rAdapter != null){
            mReviewRecylcer.setAdapter(rAdapter);
        }else{
            mReviewRecylcer.setAdapter(new ReviewAdapter());
            getMovieReviews(this.movieId);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(Constants.MOVIE_ID, movieId);
        outState.putParcelable(Constants.MOVIE_REVIEWS, Parcels.wrap(reviews));
        outState.putParcelable(Constants.REVIEW_ADAPTER , Parcels.wrap(rAdapter));
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

                    if(reviews.getResults().size() == 0){
                        ReviewResults noResults = new ReviewResults(Constants.NO_REVIEWS);
                        reviews.getResults().add(noResults);
                    }
                    rAdapter = new ReviewAdapter(reviews);
                    mReviewRecylcer.setAdapter(rAdapter);
                }

                @Override
                public void onFailure(Call<MovieReviews> call, Throwable t) {

                }
            });
        }
    }

    public MovieReviews getReviews() {
        return reviews;
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