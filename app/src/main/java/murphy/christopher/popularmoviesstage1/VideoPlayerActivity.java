package murphy.christopher.popularmoviesstage1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;
import murphy.christopher.popularmoviesstage1.util.Constants;

public class VideoPlayerActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    @BindView(R.id.trailer_viewer)
    YouTubePlayerView player;

    @BindView(R.id.movie_title)
    Toolbar movieTitle;

    private String videoKey;
    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);

        ButterKnife.bind(this);
        player.initialize(BuildConfig.YOUTUBE_API_KEY, this);

        if(savedInstanceState != null){
            videoKey = savedInstanceState.getString(Constants.TRAILER_KEY);
            title = savedInstanceState.getString(Constants.MOVIE_TITLE);
        }
        else {
            Intent intent = getIntent();
            videoKey = intent.getStringExtra(Constants.TRAILER_KEY);
            title = intent.getStringExtra(Constants.MOVIE_TITLE);
        }
        movieTitle.setTitle(title);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        if(!b){
            youTubePlayer.cueVideo(videoKey);
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString(Constants.TRAILER_KEY, videoKey);
        outState.putString(Constants.MOVIE_TITLE, title);
    }
}
