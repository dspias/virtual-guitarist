package com.example.pdspe.virtualguitarist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class SongListWithInfo extends AppCompatActivity {
    int[] sigAndStrum;
    String [][] chordListAndType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_list_with_info);

        setTitle("Song List With Strumming");

        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

        }
        chordListAndType = new String[8][3];


        sigAndStrum = new int[3];

        Button jothodure = (Button) findViewById(R.id.common_4_beat);
        jothodure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sigAndStrum[0] = 4;
                sigAndStrum[1] = 0;

                chordListAndType[0][0] = "G";
                chordListAndType[0][1] = "Major";

                chordListAndType[1][0] = "E";
                chordListAndType[1][1] = "Minor";

                chordListAndType[2][0] = "C";
                chordListAndType[2][1] = "Major";

                chordListAndType[3][0] = "D";
                chordListAndType[3][1] = "Major";


                playGuitar.setData(sigAndStrum,true,chordListAndType,4,130);
                Intent home = new Intent(SongListWithInfo.this, playGuitar.class);
                startActivity(home);
            }
        });


        Button colocolo = (Button) findViewById(R.id.common_4_beat1);
        colocolo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sigAndStrum[0] = 4;
                sigAndStrum[1] = 1;

                chordListAndType[0][0] = "E";
                chordListAndType[0][1] = "Major";

                chordListAndType[1][0] = "A";
                chordListAndType[1][1] = "Major";


                playGuitar.setData(sigAndStrum,true,chordListAndType,2,150);
                Intent home = new Intent(SongListWithInfo.this, playGuitar.class);
                startActivity(home);
            }
        });


        Button three = (Button) findViewById(R.id.Common_waltz);
        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sigAndStrum[0] = 3;
                sigAndStrum[1] = 0;

                chordListAndType[0][0] = "G";
                chordListAndType[0][1] = "Major";

                chordListAndType[1][0] = "E";
                chordListAndType[1][1] = "Minor";

                chordListAndType[2][0] = "C";
                chordListAndType[2][1] = "Major";

                chordListAndType[3][0] = "D";
                chordListAndType[3][1] = "Major";


                playGuitar.setData(sigAndStrum,true,chordListAndType,4,130);
                Intent home = new Intent(SongListWithInfo.this, playGuitar.class);
                startActivity(home);
            }
        });

        Button four = (Button) findViewById(R.id.oldfaithful2);
        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sigAndStrum[0] = 5;
                sigAndStrum[1] = 1;

                chordListAndType[0][0] = "D";
                chordListAndType[0][1] = "Major";

                chordListAndType[1][0] = "B";
                chordListAndType[1][1] = "Minor";

                chordListAndType[2][0] = "D";
                chordListAndType[2][1] = "Sus4";

                chordListAndType[3][0] = "A";
                chordListAndType[3][1] = "Major";


                playGuitar.setData(sigAndStrum,true,chordListAndType,4,170);
                Intent home = new Intent(SongListWithInfo.this, playGuitar.class);
                startActivity(home);
            }
        });

        Button five = (Button) findViewById(R.id.oldfaithful3);
        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sigAndStrum[0] = 5;
                sigAndStrum[1] = 1;

                chordListAndType[0][0] = "D";
                chordListAndType[0][1] = "Major";

                chordListAndType[1][0] = "B";
                chordListAndType[1][1] = "Minor";

                chordListAndType[2][0] = "G";
                chordListAndType[2][1] = "Major";

                chordListAndType[3][0] = "A";
                chordListAndType[3][1] = "Major";


                playGuitar.setData(sigAndStrum,true,chordListAndType,4,200);
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
