package murphy.christopher.popularmoviesstage1.database;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import murphy.christopher.popularmoviesstage1.database.converter.DateConverter;
import murphy.christopher.popularmoviesstage1.database.dao.interfaces.MovieDao;
import murphy.christopher.popularmoviesstage1.database.entities.MovieEntity;
import murphy.christopher.popularmoviesstage1.util.Constants;

@Database(entities = {MovieEntity.class}, version = 1, exportSchema = false)
@TypeConverters(DateConverter.class)
public abstract class PopularMovieDB extends RoomDatabase {
    private static PopularMovieDB dbInstance;

    public static PopularMovieDB getInstance(Context context){
        if(dbInstance == null){
            dbInstance = Room.databaseBuilder(context.getApplicationContext(),
                                              PopularMovieDB.class,
                                              Constants.DB_NAME).build();
        }
        return dbInstance;
    }

    public abstract MovieDao movieDao();
}
