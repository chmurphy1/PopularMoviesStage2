package murphy.christopher.popularmoviesstage1;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.drawable.GradientDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.parceler.Parcels;

import java.text.SimpleDateFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import murphy.christopher.popularmoviesstage1.model.Movie;
import murphy.christopher.popularmoviesstage1.util.Constants;

public class MovieDetailActivity extends AppCompatActivity {

    private Movie movieDetails;

    @BindView(R.id.movieDetailPoster)
    ImageView detailPoster;

    @BindView(R.id.rating)
    TextView rating;

    @BindView(R.id.release_date)
    TextView releaseDate;

    @BindView(R.id.synopsis)
    TextView synopsis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            setContentView(R.layout.activity_movie_detail_horizontal);
        }
        else if(this.getResources().getConfiguration().orientation==Configuration.ORIENTATION_PORTRAIT){
            setContentView(R.layout.activity_movie_detail);
        }

        SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT);

        if(savedInstanceState != null){
            movieDetails = Parcels.unwrap(savedInstanceState.getParcelable(Constants.MOVIE_KEY));
        }
        else {
            Intent intent = getIntent();
            movieDetails = Parcels.unwrap(intent.getParcelableExtra(Constants.MOVIE_KEY));
        }

        if(movieDetails == null){
            //display message to user
        }
        //Set the title of the activity screen
        this.setTitle(movieDetails.getOriginal_title());

        ButterKnife.bind(this);

        ConnectivityManager cm = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        if(activeNetwork != null && activeNetwork.isAvailable() == true
                && activeNetwork.isConnected()){
        Glide.with(this)
                .load(Constants.MOVIE_URL_W185 + movieDetails.getPoster_path())
                .into(detailPoster);
        }
        else{
            Toast.makeText(this,R.string.network_error,Toast.LENGTH_LONG);
        }
        rating.setText(movieDetails.getVote_average()+"");
        releaseDate.setText( sdf.format(movieDetails.getRelease_date()));
        synopsis.setText(movieDetails.getOverview());
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(Constants.MOVIE_KEY, Parcels.wrap(movieDetails));
    }
}
