package murphy.christopher.popularmoviesstage1.model;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

import java.util.ArrayList;

@Parcel(Parcel.Serialization.BEAN)
public class MovieReviews {

    @SerializedName("id")
    private int id;

    @SerializedName("page")
    private int page;

    @SerializedName("results")
    private ArrayList<TrailerResults> results;

    @SerializedName("total_pages")
    private int total_pages;

    @SerializedName("total_results")
    private int total_results;

    public MovieReviews(){
        this.id = 0;
        this.page = 0;
        this.results = new ArrayList<>();
        this.total_pages = 0;
        this.total_results = 0;
    }

    @ParcelConstructor
    public MovieReviews(int id,
                        int page,
                        ArrayList<TrailerResults> results,
                        int total_pages,
                        int total_results){
        this.id = id;
        this.page = page;
        this.results = results;
        this.total_pages = total_pages;
        this.total_results = total_results;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public ArrayList<TrailerResults> getResults() {
        return results;
    }

    public void setResults(ArrayList<TrailerResults> results) {
        this.results = results;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public int getTotal_results() {
        return total_results;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }
}
