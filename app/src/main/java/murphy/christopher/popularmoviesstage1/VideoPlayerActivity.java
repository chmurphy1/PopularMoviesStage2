package murphy.christopher.popularmoviesstage1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;
import murphy.christopher.popularmoviesstage1.util.Constants;

public class VideoPlayerActivity extends AppCompatActivity implements YouTubePlayer.OnInitializedListener {

    private String videoKey;
    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);

        if(savedInstanceState != null){
            videoKey = savedInstanceState.getString(Constants.TRAILER_KEY);
            title = savedInstanceState.getString(Constants.MOVIE_TITLE);
        }
        else {
            Intent intent = getIntent();
            videoKey = intent.getStringExtra(Constants.TRAILER_KEY);
            title = intent.getStringExtra(Constants.MOVIE_TITLE);
        }

        this.setTitle(title);

        YouTubePlayerFragment player = (YouTubePlayerFragment) getFragmentManager().findFragmentById(R.id.trailer_fragment);
        player.initialize(BuildConfig.YOUTUBE_API_KEY, this);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        if(!b){
            youTubePlayer.cueVideo(videoKey);
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        Toast.makeText(this, R.string.videoErrorMessage, Toast.LENGTH_LONG).show();
    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString(Constants.TRAILER_KEY, videoKey);
        outState.putString(Constants.MOVIE_TITLE, title);
    }

}
