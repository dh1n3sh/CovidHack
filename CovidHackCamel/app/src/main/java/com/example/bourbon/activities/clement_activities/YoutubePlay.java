package com.example.bourbon.activities.clement_activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.bourbon.R;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class YoutubePlay extends YouTubeBaseActivity {

    Button play ;
    private YouTubePlayerView youTubePlayerView ;
    private YouTubePlayer.OnInitializedListener onInitializedListener;
    String video_id  = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube_play);

        youTubePlayerView = findViewById(R.id.view);

        Bundle b = getIntent().getExtras();
        video_id = b.getString("videoid");

        onInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideo(video_id);


            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };

        youTubePlayerView.initialize("AIzaSyCgMfU9aS6h8qjAF8NxzBHhTS84palI7UY",onInitializedListener);



    }
}
