package murphy.christopher.popularmoviesstage1;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.bumptech.glide.Glide;

import org.parceler.Parcels;

import java.text.SimpleDateFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import murphy.christopher.popularmoviesstage1.adapters.MovieDetailsPagerAdapter;
import murphy.christopher.popularmoviesstage1.fragments.MovieDetailFragment;
import murphy.christopher.popularmoviesstage1.fragments.MovieReviewsFragment;
import murphy.christopher.popularmoviesstage1.fragments.MovieTrailersFragment;
import murphy.christopher.popularmoviesstage1.model.Movie;
import murphy.christopher.popularmoviesstage1.util.Constants;

public class MovieDetailActivity extends AppCompatActivity {

    private Movie movieDetails;

    @BindView(R.id.movieDetailsTabLayout)
    TabLayout movieDetailsTabLayout;

    @BindView(R.id.movieDetailPager)
    ViewPager movieDetailPager;

    private MovieDetailsPagerAdapter adapter;

    private boolean isHorizontal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        ButterKnife.bind(this);

        if(this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            isHorizontal = true;
        }

        Intent intent = getIntent();
        movieDetails = Parcels.unwrap(intent.getParcelableExtra(Constants.MOVIE_KEY));

        //Set the title of the activity screen
        this.setTitle(movieDetails.getOriginal_title());

        Bundle arguements = new Bundle();
        arguements.putParcelable(Constants.MOVIE_KEY, Parcels.wrap(movieDetails));
        arguements.putBoolean(Constants.SCREEN_POSITION_KEY, isHorizontal);

        MovieDetailFragment movieDetailFragment = new MovieDetailFragment();
        movieDetailFragment.setArguments(arguements);

        adapter = new MovieDetailsPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(movieDetailFragment);
        adapter.addFragment(new MovieReviewsFragment());
        adapter.addFragment(new MovieTrailersFragment());
        movieDetailPager.setAdapter(adapter);
        movieDetailsTabLayout.setupWithViewPager(movieDetailPager);

        //Setup Tabs
        movieDetailsTabLayout.getTabAt(0).setText(R.string.movieDetailTab);
        movieDetailsTabLayout.getTabAt(1).setText(R.string.movieReviewTab);
        movieDetailsTabLayout.getTabAt(2).setText(R.string.movieTrailerTab);
    }
}
