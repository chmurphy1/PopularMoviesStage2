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
import murphy.christopher.popularmoviesstage1.adapters.TrailerAdapter;
import murphy.christopher.popularmoviesstage1.interfaces.GetMovieDataService;
import murphy.christopher.popularmoviesstage1.model.MovieTrailer;
import murphy.christopher.popularmoviesstage1.util.Constants;
import murphy.christopher.popularmoviesstage1.util.RetrofitUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MovieTrailersFragment extends Fragment {
    private int movieId;
    private MovieTrailer trailers;

    @BindView(R.id.TrailerRecycler)
    RecyclerView mTrailerRecylcer;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = this.getArguments();
        if((args != null) && (savedInstanceState == null)){
            this.movieId = args.getInt(Constants.MOVIE_ID);
            getMovieTrailers(this.movieId);
        }else if(savedInstanceState != null){
            this.movieId = savedInstanceState.getInt(Constants.MOVIE_ID);
            this.trailers = Parcels.unwrap(savedInstanceState.getParcelable(Constants.MOVIE_TRAILERS));
        }
    }
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(Constants.MOVIE_ID, movieId);
        outState.putParcelable(Constants.MOVIE_TRAILERS, Parcels.wrap(trailers));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View trailerView = inflater.inflate(R.layout.fragment_movie_trailers,container,false);
        ButterKnife.bind(this, trailerView);

        return trailerView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        LinearLayoutManager layoutmanager = new LinearLayoutManager(getContext());
        mTrailerRecylcer.setLayoutManager(layoutmanager);
        mTrailerRecylcer.setHasFixedSize(true);
        mTrailerRecylcer.setAdapter(new TrailerAdapter());

        getMovieTrailers(this.movieId);
    }


    public void getMovieTrailers(int id){
        //Get a retrofit instance for this task
        Retrofit retrofitInstance = RetrofitUtil.getRetrofitInstance();

        //Create the service to get the movie data
        GetMovieDataService service = retrofitInstance.create(GetMovieDataService.class);

        Call<MovieTrailer> trailersAsync = service.getMovieTrailer(id, BuildConfig.MOVIE_DB_API_KEY);

        if(hasInternetService()) {
            trailersAsync.enqueue(new Callback<MovieTrailer>() {
                @Override
                public void onResponse(Call<MovieTrailer> call, Response<MovieTrailer> response) {
                    trailers = response.body();

                    if(trailers != null){
                        TrailerAdapter ta = new TrailerAdapter(trailers);
                        mTrailerRecylcer.setAdapter(ta);
                    }
                }

                @Override
                public void onFailure(Call<MovieTrailer> call, Throwable t) {

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
