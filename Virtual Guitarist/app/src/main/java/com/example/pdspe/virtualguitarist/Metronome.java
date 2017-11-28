package com.example.pdspe.virtualguitarist;

import android.app.Dialog;
import android.net.Uri;
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











    }       //------Closed onCreate Method-----//






}      //------Closed Metronome class----//


