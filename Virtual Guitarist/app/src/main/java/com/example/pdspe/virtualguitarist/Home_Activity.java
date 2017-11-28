package com.example.pdspe.virtualguitarist;


import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;



public class Home_Activity extends AppCompatActivity {

    //------------- Create Instant variable---------//
    public Button playGuitar;
    public Button metornome;

    //---------------- Use DrawerLayout Instance Variable-----//
    protected DrawerLayout mDrawerLayout;
    protected ActionBarDrawerToggle mToggle;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_);
        setTitle("");
        nevigation();


        goPlayGuitarActivity();     //-----goPlayGuitarActivity Method are called---.//
        goMetronomeActivity();      //-----goMetronomeActivity Method are Called----//
    }









}
