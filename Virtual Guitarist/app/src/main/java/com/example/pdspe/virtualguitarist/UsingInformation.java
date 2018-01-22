package com.example.pdspe.virtualguitarist;

import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class UsingInformation extends YouTubeBaseActivity {
//youtube api serial
    //  AIzaSyBcBPTWiqjUCQzglE2ttnk9g-qN-YoNZaA

    TextView detaisView;
    boolean isAlive;
    LinearLayout detailsLayout;
    Button back,details,playdemoVideo;
    YouTubePlayerView youTubePlayerView;
    YouTubePlayer.OnInitializedListener onInitializedListener;
    public static final String KEY = "AIzaSyBcBPTWiqjUCQzglE2ttnk9g-qN-YoNZaA";

    ImageView back_icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_using_information);

        setTitle("How to use our App");

//        if(getSupportActionBar() != null){
//            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//            getSupportActionBar().setDisplayShowHomeEnabled(true);
//
//        }


        TextView tv=(TextView)findViewById(R.id.shortInfo);
        tv.setText(Html.fromHtml(getString(R.string.using_play_guitar)));

        playdemoVideo = (Button) findViewById(R.id.play_youtube);
        youTubePlayerView = (YouTubePlayerView) findViewById(R.id.youtubelink);

        onInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {

                youTubePlayer.loadVideo("_TDJQ3c_Wl4");
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };
        youTubePlayerView.initialize(KEY,onInitializedListener);


        playdemoVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (playdemoVideo.getText().toString().equalsIgnoreCase("play demo video")){
                    youTubePlayerView.setVisibility(View.VISIBLE);
                    playdemoVideo.setText("close");
                } else if (playdemoVideo.getText().toString().equalsIgnoreCase("close")){
                    youTubePlayerView.setVisibility(View.GONE);
                    playdemoVideo.setText("play demo video");
                }
            }
        });



        isAlive = true;
        detailsLayout = (LinearLayout) findViewById(R.id.detalis_Layout);



        details = (Button) findViewById(R.id.detailsButton);
        details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isAlive == true){
                    detailsLayout.setVisibility(View.VISIBLE);
                    details.setText("<< close");
                } else {
                    detailsLayout.setVisibility(View.GONE);
                    details.setText("Details >>");
                }
                isAlive =! isAlive;
            }
        });

        detaisView = (TextView) findViewById(R.id.Detalis);
        detaisView.setText(Html.fromHtml(getString(R.string.details_using_info)));
        back = (Button) findViewById(R.id.back_button);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detailsLayout.setVisibility(View.GONE);
                details.setText("Details >>");
            }
        });


        back_icon = (ImageView) findViewById(R.id.back_button_icon);
        back_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



    }   /*close on ctreate method*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
