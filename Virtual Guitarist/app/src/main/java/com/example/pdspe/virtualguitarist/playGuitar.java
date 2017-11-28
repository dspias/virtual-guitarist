package com.example.pdspe.virtualguitarist;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class playGuitar extends AppCompatActivity {

    protected DrawerLayout mDrawerLayout;
    protected ActionBarDrawerToggle mToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_guitar);

        setTitle("Play Guitar");        //----Set title on Play Guitar Page----//
        nevigation();



    }       //------ Closed onCreate Method -----//


    private void nevigation(){
        mDrawerLayout = (DrawerLayout) findViewById(R.id.activity_play_guitar_DrawarLayout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



    }   //----Closed nevigation method--//

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }











}       //-------- Closed PlayGuitar Class -----//
