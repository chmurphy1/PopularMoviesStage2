package murphy.christopher.popularmoviesstage1.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import murphy.christopher.popularmoviesstage1.R;
import murphy.christopher.popularmoviesstage1.model.Page;
import murphy.christopher.popularmoviesstage1.view_holders.MovieViewHolder;

@Parcel(Parcel.Serialization.BEAN)
public class PageAdapter extends RecyclerView.Adapter<MovieViewHolder> {
    @SerializedName("mPage")
    private Page mPage;

    @SerializedName("isHorizontal")
    private boolean isHorizontal;

    public PageAdapter(){
        this.isHorizontal = false;
        mPage = new Page();
    }

    public PageAdapter(boolean isHorizontal){
        this.isHorizontal = isHorizontal;
        mPage = new Page();
    }

    public PageAdapter(Page result, boolean isHorizontal) {
        this.isHorizontal = isHorizontal;
        this.mPage = result;
    }
    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater mInflater = LayoutInflater.from(context);
        View v;

        if(isHorizontal){
            v = mInflater.inflate(R.layout.images_horizontal, viewGroup,false);
        }
        else{
            v = mInflater.inflate(R.layout.images, viewGroup,false);
        }

        return new MovieViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder movieViewHolder, int i) {
        movieViewHolder.bind(mPage.getResults().get(i));
    }

    @Override
    public int getItemCount() {
        return mPage.getResults().size();
    }

    public void setHorizontal(boolean horizontal) {
        isHorizontal = horizontal;
    }
}
