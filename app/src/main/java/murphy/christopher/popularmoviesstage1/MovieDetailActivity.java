package murphy.christopher.popularmoviesstage1;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;

import org.parceler.Parcels;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        ButterKnife.bind(this);

        if(savedInstanceState != null){
            movieDetails = Parcels.unwrap(savedInstanceState.getParcelable(Constants.MOVIE_KEY));
        }else {
            Intent intent = getIntent();
            movieDetails = Parcels.unwrap(intent.getParcelableExtra(Constants.MOVIE_KEY));
        }

        //Set the title of the activity screen
        this.setTitle(movieDetails.getOriginal_title());

        adapter = new MovieDetailsPagerAdapter(getSupportFragmentManager(),  movieDetails);
        movieDetailPager.setOffscreenPageLimit(3);
        movieDetailPager.setAdapter(adapter);
        movieDetailsTabLayout.setupWithViewPager(movieDetailPager);

        //Setup Tabs
        movieDetailsTabLayout.getTabAt(0).setText(R.string.movieDetailTab);
        movieDetailsTabLayout.getTabAt(1).setText(R.string.movieReviewTab);
        movieDetailsTabLayout.getTabAt(2).setText(R.string.movieTrailerTab);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(Constants.MOVIE_KEY, Parcels.wrap(movieDetails));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_detail_button, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
