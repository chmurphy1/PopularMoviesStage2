package murphy.christopher.popularmoviesstage1.adapters;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import org.parceler.Parcels;
import murphy.christopher.popularmoviesstage1.fragments.MovieDetailFragment;
import murphy.christopher.popularmoviesstage1.fragments.MovieReviewsFragment;
import murphy.christopher.popularmoviesstage1.fragments.MovieTrailersFragment;
import murphy.christopher.popularmoviesstage1.model.Movie;
import murphy.christopher.popularmoviesstage1.util.Constants;

public class MovieDetailsPagerAdapter extends FragmentPagerAdapter {
    private Movie movieDetails;

    public MovieDetailsPagerAdapter(FragmentManager manager, Movie details){
        super(manager);
        this.movieDetails = details;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment returnFragment = null;

        Bundle arguements = new Bundle();

        switch(position){
            case 0:
                returnFragment = new MovieDetailFragment();
                arguements.putParcelable(Constants.MOVIE_KEY, Parcels.wrap(movieDetails));
                returnFragment.setArguments(arguements);
                break;
            case 1:
                returnFragment = new MovieReviewsFragment();
                arguements.putInt(Constants.MOVIE_ID, movieDetails.getId());
                returnFragment.setArguments(arguements);
                break;
            case 2:
                returnFragment = new MovieTrailersFragment();
                arguements.putInt(Constants.MOVIE_ID, movieDetails.getId());
                returnFragment.setArguments(arguements);
                break;
        }

        return returnFragment;
    }

    @Override
    public int getCount() {
        return Constants.NUM_MOVIE_DETAIL_TABS;
    }

    @Override
    public Parcelable saveState() {
        return super.saveState();
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
        super.restoreState(state, loader);
    }
}
