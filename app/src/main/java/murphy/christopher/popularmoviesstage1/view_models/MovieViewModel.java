package murphy.christopher.popularmoviesstage1.view_models;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import java.util.List;
import murphy.christopher.popularmoviesstage1.database.PopularMovieDB;
import murphy.christopher.popularmoviesstage1.database.entities.MovieEntity;

public class MovieViewModel extends AndroidViewModel {
    private LiveData<List<MovieEntity>> movies;

    public MovieViewModel(@NonNull Application application) {
        super(application);

        PopularMovieDB db = PopularMovieDB.getInstance(application.getApplicationContext());
        movies = db.movieDao().getAllMovies();
    }

    public LiveData<List<MovieEntity>> getMovies() {
        return movies;
    }
}
