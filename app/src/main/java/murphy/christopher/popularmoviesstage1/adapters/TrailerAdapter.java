package murphy.christopher.popularmoviesstage1.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import org.parceler.Parcel;

import murphy.christopher.popularmoviesstage1.R;
import murphy.christopher.popularmoviesstage1.model.MovieTrailer;
import murphy.christopher.popularmoviesstage1.view_holders.ReviewViewHolder;
import murphy.christopher.popularmoviesstage1.view_holders.TrailerViewHolder;

@Parcel(Parcel.Serialization.BEAN)
public class TrailerAdapter extends RecyclerView.Adapter<TrailerViewHolder> {

    private MovieTrailer trailers;

    public TrailerAdapter(){
        this.trailers = new MovieTrailer();
    }

    public TrailerAdapter(MovieTrailer result) {
        this.trailers = result;
    }

    @NonNull
    @Override
    public TrailerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater mInflater = LayoutInflater.from(context);

        return new TrailerViewHolder(mInflater.inflate(R.layout.fragment_video, parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull TrailerViewHolder holder, int position) {
        holder.bind(trailers.getResults().get(position));
    }

    @Override
    public int getItemCount() {
        return trailers.getResults().size();
    }
}
