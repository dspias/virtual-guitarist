package com.example.pdspe.virtualguitarist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class SongListWithInfo extends AppCompatActivity {
    int[] sigAndStrum;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_list_with_info);

        setTitle("Song List With Strumming");

        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

        }


        sigAndStrum = new int[3];

        Button fourByFour1 = (Button) findViewById(R.id.FourByFour_1);
        fourByFour1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sigAndStrum[0] = 4;
                sigAndStrum[1] = 0;
                playGuitar.setData(sigAndStrum,true);
                Intent home = new Intent(SongListWithInfo.this, playGuitar.class);
                startActivity(home);
            }
        });




    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
