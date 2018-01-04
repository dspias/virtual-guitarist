package com.example.pdspe.virtualguitarist;

import android.app.Dialog;
import android.content.Intent;
import android.media.AudioManager;
import android.net.Uri;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;


public class Metronome extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    protected DrawerLayout mDrawerLayout;
    protected ActionBarDrawerToggle mToggle;

    //----set signature value----//
    String value1 = "", value2 = "";
    TextView first, second;

    //------set tempo value ----//
    Button tempo;

    //---- set media option ------//
    boolean isPlay = false;
    PlayMetronome mPlayMetronome = new PlayMetronome();
    Intent intent;

    //------------ set Metronome Light --------//
    LinearLayout beatPaturn1, beatPaturn2;





    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metronome);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);

        //-------- Init sound Manager---------//
        SoundManager.getInstance();
        SoundManager.initSounds(this);
        SoundManager.loadSounds();

        beatPaturn1 = (LinearLayout) findViewById(R.id.setColorBeatPatturn1);
        beatPaturn2 = (LinearLayout) findViewById(R.id.setColorBeatPatturn2);


        setTitle("Metronome");      //----Set title on Metronome Page----//
        nevigation();               //-----call nevigation draware method-----//

        //-------- Signature input start -------//
        first = (TextView) findViewById(R.id.beat_pattern1);
        second = (TextView) findViewById(R.id.beat_pattern2);

        LinearLayout Singnature = (LinearLayout) findViewById(R.id.input_signature);
        Singnature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSignature();
                setMetronome();

            }
        });     //----- Signature input complete------//

        //-----------Tempo input start -------//
        tempo = (Button) findViewById(R.id.tempo_input_value);

        tempo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                setTempo();
                setMetronome();
            }
        });     //----------Tempo input Complete ---------//

        //------- minus button working code -----//
        final ImageButton minus = (ImageButton) findViewById(R.id.negative_buttton);
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                int oldVal = 0;
                try {
                    oldVal = Integer.parseInt(String.valueOf(tempo.getText()));
                } catch (NumberFormatException nfe) {}
                if(oldVal > 40) oldVal--;
                tempo.setText(String.valueOf(oldVal));
                setMetronome();

            }
        });     //----------minus botton code complete -------//

        //------- plus button working code -----//
        final ImageButton plus = (ImageButton) findViewById(R.id.positive_button);
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int oldVal = 0;
                try {
                    oldVal = Integer.parseInt(String.valueOf(tempo.getText()));
                } catch (NumberFormatException nfe) {}
                if(oldVal < 240) oldVal++;
                tempo.setText(String.valueOf(oldVal));
                setMetronome();

            }
        });     //----------plus botton code complete -------//


        //------ Working Media Button code ------- //

        final ImageButton media = (ImageButton) findViewById(R.id.media_button);

        intent = new Intent(Metronome.this, PlayMetronome.class);
        media.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Metronome.this, PlayMetronome.class);

                isPlay = !isPlay; // reverse
                if (isPlay) {
                    media.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.stop_btn));
                    int size = getResources().getDimensionPixelOffset(R.dimen.thirty);
                    media.setPadding(size,size,size,size);

                    setMetronome();
                    startService(intent);


                } else {
                    media.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.play_btn));
                    int topbottom = getResources().getDimensionPixelOffset(R.dimen.thirty);
                    int right = getResources().getDimensionPixelOffset(R.dimen.towentysix);
                    int left = getResources().getDimensionPixelOffset(R.dimen.thirty);
                    media.setPadding(left, topbottom, right, topbottom);

                    stopService(intent);

                }


            }
        });


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

    }       //------Closed onCreate Method-----//

    @Override
    public void onBackPressed() {
        stopService(intent);
        finish();
    }





    /*----------start setMetronome Method -----*/
    public void setMetronome(){
            /*---------- Create set Metronome value --------*/
        int fsignature = Integer.parseInt(String.valueOf(first.getText()));
        int ssignature = Integer.parseInt(String.valueOf(second.getText()));
        int tempoValue = Integer.parseInt(String.valueOf(tempo.getText()));

        PlayMetronome.setFirstSignature(fsignature);
        PlayMetronome.setSecondSignature(ssignature);
        PlayMetronome.setTempo(tempoValue);
        PlayMetronome.setBeatptn1(beatPaturn1);
        PlayMetronome.setBeatptn2(beatPaturn2);

    }       //-------closed setMetronome Metod--------//




    //-------------start setSignature method -----//
    public void setSignature() {
        final Dialog d = new Dialog(Metronome.this);
        d.setContentView(R.layout.numberpicker);
        final NumberPicker np1 = (NumberPicker) d.findViewById(R.id.numberPicker1);
        np1.setMaxValue(20); // max value 20

        int oldVal1 = 0;
        try {
            oldVal1 = Integer.parseInt(String.valueOf(first.getText()));
        } catch (NumberFormatException nfe) {
        }
        np1.setValue(oldVal1);

        np1.setMinValue(1);   // min value 0
        np1.setWrapSelectorWheel(false);
        np1.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                //value1 += newVal;
                first.setText(String.valueOf(np1.getValue()));

            }
        });
        final NumberPicker np2 = (NumberPicker) d.findViewById(R.id.numberPicker2);
        np2.setMaxValue(20); // max value 20

        int oldVal2 = 0;
        try {
            oldVal2 = Integer.parseInt(String.valueOf(second.getText()));
        } catch (NumberFormatException nfe) {
        }
        np2.setValue(oldVal2);

        np2.setMinValue(1);   // min value 0
        np2.setWrapSelectorWheel(false);
        np2.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                //value2 += newVal;
                second.setText(String.valueOf(np2.getValue()));
            }

        });

        Button okay = (Button) d.findViewById(R.id.okayButton);
        okay.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //first.setText(String.valueOf(np1.getValue()));
                //second.setText(String.valueOf(np2.getValue()));
                setMetronome();
                d.dismiss();
            }
        });
        d.show();

    }   //----------- Closed setSignature method----//






    //-----------start setTempo method--------//

    protected void setTempo() {
        final Dialog d = new Dialog(Metronome.this);
        d.setContentView(R.layout.inputtempovalue);
        final NumberPicker np = (NumberPicker) d.findViewById(R.id.numberPicker_tempo);
        np.setMaxValue(240);

        int oldVal = 0;
        try {
            oldVal = Integer.parseInt(String.valueOf(tempo.getText()));
        } catch (NumberFormatException nfe) {
        }
        np.setValue(oldVal);

        np.setMinValue(40);
        np.setWrapSelectorWheel(false);
        np.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                //value1 += newVal;
                tempo.setText(String.valueOf(np.getValue()));

            }
        });

        Button okay = (Button) d.findViewById(R.id.okayButton);
        okay.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                setMetronome();
                d.dismiss();
            }
        });
        d.show();


    }       //-------closed Tempo Method-----//





    //---- create Nevigation method ---/
    private void nevigation() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.activity_metronome_drawarLayout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation);
        navigationView.setNavigationItemSelectedListener(this);

    }   //----Closed nevigation method--//

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }       //-------Closed onOptionsItemSelected Method---//

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.actionhomebutton, menu);

//        Intent playGuitar = new Intent(Metronome.this, Home_Activity.class);
//        startActivity(playGuitar);
        return true;
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_metronome) {
            Intent playGuitar = new Intent(Metronome.this, Metronome.class);
            startActivity(playGuitar);
        } else if (id == R.id.nav_playGuitar) {
            Intent playGuitar = new Intent(Metronome.this, playGuitar.class);
            startActivity(playGuitar);

        } else if (id == R.id.nav_info) {

        } else if (id == R.id.nav_songList_with_informtion) {

        } else if (id == R.id.nav_setting) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.activity_home_drawarLayout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }




    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Metronome Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }




}      //------Closed Metronome class----//




