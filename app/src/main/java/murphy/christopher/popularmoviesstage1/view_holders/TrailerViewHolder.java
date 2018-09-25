package murphy.christopher.popularmoviesstage1.view_holders;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import murphy.christopher.popularmoviesstage1.BuildConfig;
import murphy.christopher.popularmoviesstage1.R;
import murphy.christopher.popularmoviesstage1.VideoPlayerActivity;
import murphy.christopher.popularmoviesstage1.model.TrailerResults;
import murphy.christopher.popularmoviesstage1.util.Constants;

public class TrailerViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.trailerText)
    TextView trailerDescription;

    @BindView(R.id.trailerThumbnail)
    YouTubeThumbnailView thumbnail;

    private Context context;
    private TrailerResults results;

    public TrailerViewHolder(View itemView) {
        super(itemView);
        context = itemView.getContext();
        ButterKnife.bind(this, itemView);
    }

    public void bind( final TrailerResults r) {
        this.results = r;
        trailerDescription.setText(results.getName());

        //See youtube api for more details
        //https://developers.google.com/youtube/android/player/reference/com/google/android/youtube/player/YouTubeThumbnailLoader
        thumbnail.initialize(BuildConfig.YOUTUBE_API_KEY, new YouTubeThumbnailView.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubeThumbnailView youTubeThumbnailView, final YouTubeThumbnailLoader youTubeThumbnailLoader) {
                youTubeThumbnailLoader.setVideo(results.getKey());

                //release the thumbnail to prevent errors
                youTubeThumbnailLoader.setOnThumbnailLoadedListener(new YouTubeThumbnailLoader.OnThumbnailLoadedListener() {
                    @Override
                    public void onThumbnailLoaded(YouTubeThumbnailView youTubeThumbnailView, String s) {
                        youTubeThumbnailLoader.release();
                    }

                    @Override
                    public void onThumbnailError(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader.ErrorReason errorReason) {

                    }
                });
            }

            @Override
            public void onInitializationFailure(YouTubeThumbnailView youTubeThumbnailView, YouTubeInitializationResult youTubeInitializationResult) {

            }
        });

    }

     @OnClick(R.id.trailerThumbnail)
     public void onThumbnailClick(){
        Intent intent = new Intent(context, VideoPlayerActivity.class);
        intent.putExtra(Constants.TRAILER_KEY , results.getKey());
        intent.putExtra(Constants.MOVIE_TITLE, results.getName());
        context.startActivity(intent);
     }
}
