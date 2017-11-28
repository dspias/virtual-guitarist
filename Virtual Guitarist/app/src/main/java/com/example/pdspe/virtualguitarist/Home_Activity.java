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




<<<<<<< HEAD
    private void nevigation(){
        mDrawerLayout = (DrawerLayout) findViewById(R.id.activity_home_drawarLayout);
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



    // ------------- Create Method Onclick Play Guitar Button than go Play Guitar Activity And Open------------//
    public void goPlayGuitarActivity(){
        playGuitar = (Button) findViewById(R.id.button_id_P_guitarist);
        playGuitar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent playGuitar = new Intent(Home_Activity.this, playGuitar.class);
                startActivity(playGuitar);
            }
        });
    }   //----goPlayGuitarAcivity Method are Closed---//




    // ------------- Create Method Onclick Metronome Button than go Metronome Activity And Open------------//
    public void goMetronomeActivity(){
        metornome = (Button) findViewById(R.id.button_id_metronome);
        metornome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent metronome = new Intent(Home_Activity.this, Metronome.class);
                startActivity(metronome);
            }
        });
    }   //------ goMetronomeActivity Method are Closed------//


=======
>>>>>>> 59233d3aa969261342f38aff5c86d3b7e5c31a09





}
