package murphy.christopher.popularmoviesstage1.view_holders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;

import butterknife.BindView;
import butterknife.ButterKnife;
import murphy.christopher.popularmoviesstage1.BuildConfig;
import murphy.christopher.popularmoviesstage1.R;
import murphy.christopher.popularmoviesstage1.model.TrailerResults;

public class TrailerViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.trailerText)
    TextView trailerDescription;

    @BindView(R.id.trailerThumbnail)
    ImageView thumbnail;

    private Context context;

    public TrailerViewHolder(View itemView) {
        super(itemView);
        context = itemView.getContext();
        ButterKnife.bind(this, itemView);
    }

    public void bind( final TrailerResults results) {
        trailerDescription.setText(results.getName());

    }
}