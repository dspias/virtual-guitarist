package com.example.pdspe.virtualguitarist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SongListWithInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_list_with_info);

        setTitle("Song List With Information");
    }
}
