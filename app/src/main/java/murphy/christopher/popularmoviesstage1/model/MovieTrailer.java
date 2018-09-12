package murphy.christopher.popularmoviesstage1.model;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

import java.util.ArrayList;

@Parcel(Parcel.Serialization.BEAN)
public class MovieTrailer {

    @SerializedName("id")
    private int id;

    @SerializedName("results")
    private ArrayList<TrailerResults> results;

    public MovieTrailer(){
        this.id = 0;
        this.results = new ArrayList<>();
    }

    @ParcelConstructor
    public MovieTrailer(int id, ArrayList<TrailerResults> results){
        this.id = id;
        this.results = results;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<TrailerResults> getResults() {
        return results;
    }

    public void setResults(ArrayList<TrailerResults> results) {
        this.results = results;
    }
}
