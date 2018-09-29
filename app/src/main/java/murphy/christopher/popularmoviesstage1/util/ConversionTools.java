package murphy.christopher.popularmoviesstage1.util;

import java.util.ArrayList;
import java.util.List;

import murphy.christopher.popularmoviesstage1.database.entities.MovieEntity;
import murphy.christopher.popularmoviesstage1.model.Movie;

public class ConversionTools {

    public static MovieEntity convertToMovieEntity(Movie details){
        return new MovieEntity( details.getVote_count(),
                details.getId(),
                details.isVideo(),
                details.getVote_average(),
                details.getTitle(),
                details.getPopularity(),
                details.getPoster_path(),
                details.getOriginal_language(),
                details.getOriginal_title(),
                details.getBackdrop_path(),
                details.isAdult(),
                details.getOverview(),
                details.getRelease_date());
    }

    public static ArrayList<Movie> convertToMovies(List<MovieEntity> moviesToConvert){
        ArrayList<Movie> movies = new ArrayList<>();

        for(MovieEntity m : moviesToConvert){
            movies.add(new Movie(m.getVote_count(),
                    m.getId(),
                    m.isVideo(),
                    m.getVote_average(),
                    m.getTitle(),
                    m.getPopularity(),
                    m.getPoster_path(),
                    m.getOriginal_language(),
                    m.getOriginal_title(),
                    m.getBackdrop_path(),
                    m.isAdult(),
                    m.getOverview(),
                    m.getRelease_date()));
        }

        return movies;
    }

}
