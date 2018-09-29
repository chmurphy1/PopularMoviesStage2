package murphy.christopher.popularmoviesstage1.database.dao.interfaces;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import java.util.List;
import murphy.christopher.popularmoviesstage1.database.entities.MovieEntity;

@Dao
public interface MovieDao {

    @Query("Select * FROM Movie")
    List<MovieEntity> getAllMovies();

    @Insert
    void insertMovie(MovieEntity movie);

    @Delete
    void deleteMovie(MovieEntity movie);

    @Query("Select * FROM MOVIE where id = :id")
    MovieEntity isFavorite(int id);
}
