package murphy.christopher.popularmoviesstage1.fragments;
import android.content.Context;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.parceler.Parcels;

import java.text.SimpleDateFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import murphy.christopher.popularmoviesstage1.R;
import murphy.christopher.popularmoviesstage1.model.Movie;
import murphy.christopher.popularmoviesstage1.util.Constants;

public class MovieDetailFragment extends Fragment {

    private boolean isHorizontal;
    private Movie movieDetails;

    @BindView(R.id.movieDetailPoster)
    ImageView detailPoster;

    @BindView(R.id.rating)
    TextView rating;

    @BindView(R.id.release_date)
    TextView releaseDate;

    @BindView(R.id.synopsis)
    TextView synopsis;

    public MovieDetailFragment(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = this.getArguments();
        if((args != null) && (savedInstanceState == null)){
            //this.isHorizontal = args.getBoolean(Constants.SCREEN_POSITION_KEY);
            movieDetails = Parcels.unwrap(args.getParcelable(Constants.MOVIE_KEY));
        }else if(savedInstanceState != null){
            //this.isHorizontal = savedInstanceState.getBoolean(Constants.SCREEN_POSITION_KEY);
            movieDetails = Parcels.unwrap(savedInstanceState.getParcelable(Constants.MOVIE_KEY));
        }

        if(getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            this.isHorizontal = true;
        }
        else{
            this.isHorizontal = false;
        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View movieView;

        if(isHorizontal){
            movieView = inflater.inflate(R.layout.fragment_movie_detail_horizontal,container,false);
        }
        else{
            movieView = inflater.inflate(R.layout.fragment_movie_detail,container,false);
        }
        ButterKnife.bind(this, movieView);

        return movieView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT);

        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        if(activeNetwork != null && activeNetwork.isAvailable() == true
                && activeNetwork.isConnected()){
            Glide.with(this)
                    .load(Constants.MOVIE_URL_W185 + movieDetails.getPoster_path())
                    .into(detailPoster);
        }

        rating.setText(movieDetails.getVote_average()+"");
        releaseDate.setText( sdf.format(movieDetails.getRelease_date()));
        synopsis.setText(movieDetails.getOverview());
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelable(Constants.MOVIE_KEY, Parcels.wrap(movieDetails));
    }
}
