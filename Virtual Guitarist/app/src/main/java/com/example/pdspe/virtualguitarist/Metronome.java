package com.example.pdspe.virtualguitarist;

import android.app.Dialog;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.text.method.NumberKeyListener;
import android.util.Log;
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

import java.io.File;
import java.io.IOException;

import static android.media.AudioManager.*;

public class Metronome extends AppCompatActivity {

    protected DrawerLayout mDrawerLayout;
    protected ActionBarDrawerToggle mToggle;

    //----set signature value----//
    String value1 = "", value2 = "";
    TextView first,second;

    //------set tempo value ----//
    Button tempo;

    //---- set media option ------//
    static boolean isPlay=false;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metronome);

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

            }
        });     //----- Signature input complete------//

        //-----------Tempo input start -------//
        tempo = (Button) findViewById(R.id.tempo_input_value);

        tempo.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                setTempo();
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
                } catch(NumberFormatException nfe) {}
                oldVal--;
                tempo.setText(String.valueOf(oldVal));
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
                } catch(NumberFormatException nfe) {}
                oldVal++;
                tempo.setText(String.valueOf(oldVal));
            }
        });     //----------plus botton code complete -------//


        //------ Working Media Button code ------- //
        final ImageButton media = (ImageButton) findViewById(R.id.media_button);
        media.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                isPlay = !isPlay; // reverse

                int fsignature = Integer.parseInt(String.valueOf(first.getText()));
                int ssignature = Integer.parseInt(String.valueOf(second.getText()));
                int tempoValue = Integer.parseInt(String.valueOf(tempo.getText()));
                PlayMetronome a = new PlayMetronome(fsignature,ssignature,tempoValue,isPlay);

                if(isPlay){

                    media.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.pause_button));
                   // a.run();
                }else{
                    media.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.play_button));
                    //a.stopit();
                }
            }
        });






    }       //------Closed onCreate Method-----//





    //-------------start setSignature method -----//
    public void setSignature() {
        final Dialog d = new Dialog(Metronome.this);
        d.setContentView(R.layout.numberpicker);
        final NumberPicker np1 = (NumberPicker) d.findViewById(R.id.numberPicker1);
        np1.setMaxValue(20); // max value 20

        int oldVal1 = 0;
        try {
            oldVal1 = Integer.parseInt(String.valueOf(first.getText()));
        } catch(NumberFormatException nfe) {}
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
        } catch(NumberFormatException nfe) {}
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
        okay.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                //first.setText(String.valueOf(np1.getValue()));
                //second.setText(String.valueOf(np2.getValue()));
                d.dismiss();
            }
        });
        d.show();

    }   //----------- Closed setSignature method----//


    //-----------start setTempo method--------//

    protected void setTempo(){
        final Dialog d = new Dialog(Metronome.this);
        d.setContentView(R.layout.inputtempovalue);
        final NumberPicker np = (NumberPicker) d.findViewById(R.id.numberPicker_tempo);
        np.setMaxValue(240);

        int oldVal = 0;
        try {
            oldVal = Integer.parseInt(String.valueOf(tempo.getText()));
        } catch(NumberFormatException nfe) {}
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
        okay.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
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

    }   //----Closed nevigation method--//

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }       //-------Closed onOptionsItemSelected Method---//






}      //------Closed Metronome class----//



//----- Class PlayMetronome----//

class PlayMetronome extends AppCompatActivity implements Runnable {

    static int firstSignature;
    static int secondSignature;
    static int tempo;
    static int timeDifference = (60000/tempo);
    static boolean isPlay;

    //--- create constructor ----//
    PlayMetronome(){ }
    PlayMetronome(int firstSignature, int secondSignature, int tempo, boolean isPlay){
        this.firstSignature = firstSignature;
        this.secondSignature = secondSignature;
        this.tempo = tempo;
        this.isPlay = isPlay;
    }


    @Override
    public void run() {
        while(isPlay != true) {		//! Thread.currentThread().isInterrupted()
            try {
                work();
                Thread.sleep(0);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public  void work(){
        if(isPlay != true) return;
        for(int i=0;i<secondSignature;i++) {
            try {
                if(isPlay != true) return;
                tok();
                Thread.sleep(timeDifference);
            } catch(InterruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            for(int j=0;j<firstSignature-1;j++) {
                try {
                    if(isPlay != true) return;
                    tik();
                    Thread.sleep(timeDifference);
                } catch(InterruptedException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void tok() throws IOException {
        Uri myTok = Uri.parse(Environment.getExternalStorageDirectory().getPath()+ "/raw/tok.wav");
        MediaPlayer mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(STREAM_MUSIC);
        mediaPlayer.setDataSource(getApplicationContext(), myTok);
        mediaPlayer.prepare();
        mediaPlayer.start();


    }
    public void tik() throws IOException {

        Uri myTik = Uri.parse(Environment.getExternalStorageDirectory().getPath()+ "/raw/tik.wav");
        MediaPlayer mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(STREAM_MUSIC);
        mediaPlayer.setDataSource(getApplicationContext(), myTik);
        mediaPlayer.prepare();
        mediaPlayer.start();
    }

    public static void stopit(){
        isPlay = false;
    }


}
