package murphy.christopher.popularmoviesstage1;

import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import murphy.christopher.popularmoviesstage1.adapters.MovieDetailsPagerAdapter;
import murphy.christopher.popularmoviesstage1.database.PopularMovieDB;
import murphy.christopher.popularmoviesstage1.database.entities.MovieEntity;
import murphy.christopher.popularmoviesstage1.database.utilities.dbExecutor;
import murphy.christopher.popularmoviesstage1.fragments.MovieDetailFragment;
import murphy.christopher.popularmoviesstage1.model.Movie;
import murphy.christopher.popularmoviesstage1.util.Constants;
import murphy.christopher.popularmoviesstage1.util.ConversionTools;

public class MovieDetailActivity extends AppCompatActivity {

    private Movie movieDetails;

    @BindView(R.id.movieDetailsTabLayout)
    TabLayout movieDetailsTabLayout;

    @BindView(R.id.movieDetailPager)
    ViewPager movieDetailPager;

    private MovieDetailsPagerAdapter adapter;
    private PopularMovieDB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        db = PopularMovieDB.getInstance(getApplicationContext());

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.favoriteButton) {
            if (item.isChecked()) {
                item.setChecked(false);
                item.setIcon(android.R.drawable.btn_star_big_off);
                deleteMovieData();
            } else {
                item.setChecked(true);
                item.setIcon(android.R.drawable.btn_star_big_on);
                saveMovieData();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveMovieData(){
        FragmentManager fm = getSupportFragmentManager();
        List<Fragment> movieDetailFragments = fm.getFragments();

        for(int i = 0; i < movieDetailFragments.size(); i++ ) {
            if (movieDetailFragments.get(i) instanceof MovieDetailFragment) {
                final MovieEntity mEntity = ConversionTools.convertToMovieEntity(((MovieDetailFragment) movieDetailFragments.get(i)).getMovieDetails());

                dbExecutor.getInstance().getDbThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            db.movieDao().insertMovie(mEntity);
                        }catch(SQLiteConstraintException e){
                            System.err.println(Constants.Favorite_ERROR);
                        }
                    }
                });
            }
        }
    }

    private void deleteMovieData(){
        FragmentManager fm = getSupportFragmentManager();
        List<Fragment> movieDetailFragments = fm.getFragments();

        for(int i = 0; i < movieDetailFragments.size(); i++ ) {
            if (movieDetailFragments.get(i) instanceof MovieDetailFragment) {
                final MovieEntity mEntity = ConversionTools.convertToMovieEntity(((MovieDetailFragment) movieDetailFragments.get(i)).getMovieDetails());

                dbExecutor.getInstance().getDbThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        db.movieDao().deleteMovie(mEntity);
                    }
                });
            }
        }
    }
}
