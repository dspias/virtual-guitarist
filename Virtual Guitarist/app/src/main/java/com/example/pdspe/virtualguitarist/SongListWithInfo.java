package com.example.pdspe.virtualguitarist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

public class SongListWithInfo extends AppCompatActivity {
    int[] sigAndStrum;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_list_with_info);

        setTitle("Song List With Strumming");

        sigAndStrum = new int[3];

        LinearLayout fourByFour = (LinearLayout) findViewById(R.id.FourByFour_1);
        fourByFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sigAndStrum[0] = 3;
                sigAndStrum[1] = 0;
                playGuitar.setData(sigAndStrum,true);
                Intent home = new Intent(SongListWithInfo.this, playGuitar.class);
                startActivity(home);
            }
        });




    }
}
