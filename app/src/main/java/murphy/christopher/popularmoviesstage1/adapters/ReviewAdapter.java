package murphy.christopher.popularmoviesstage1.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import org.parceler.Parcel;

import murphy.christopher.popularmoviesstage1.R;
import murphy.christopher.popularmoviesstage1.model.MovieReviews;
import murphy.christopher.popularmoviesstage1.view_holders.ReviewViewHolder;

@Parcel(Parcel.Serialization.BEAN)
public class ReviewAdapter extends RecyclerView.Adapter<ReviewViewHolder> {

    private MovieReviews reviews;

    public ReviewAdapter(){
        reviews = new MovieReviews();
    }

    public ReviewAdapter(MovieReviews result) {
        this.reviews = result;
    }

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater mInflater = LayoutInflater.from(context);

        return new ReviewViewHolder(mInflater.inflate(R.layout.fragment_reviews, parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder holder, int position) {
        holder.bind(reviews.getResults().get(position));
    }

    @Override
    public int getItemCount() {
        return (reviews.getResults()).size();
    }
}
