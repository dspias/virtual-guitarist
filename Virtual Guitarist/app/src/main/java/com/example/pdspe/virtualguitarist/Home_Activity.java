package com.example.pdspe.virtualguitarist;


import android.content.Intent;
import android.media.AudioManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class Home_Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener  {

    //------------- Create Instant variable---------//
    public Button playGuitar;
    public Button metornome;

    //---------------- Use DrawerLayout Instance Variable-----//
    protected DrawerLayout mDrawerLayout;
    protected ActionBarDrawerToggle mToggle;

    /*-----------Init Sound TextView------------*/
    TextView e1,a1,d2,g2,b2,e3;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        setTitle("");

        nevigation();


        goPlayGuitarActivity();     //-----goPlayGuitarActivity Method are called---.//
        goMetronomeActivity();      //-----goMetronomeActivity Method are Called----//

        //-------- Init sound Manager---------//
        GuitarSoundManager.getInstance();
        GuitarSoundManager.initSounds(this);
        GuitarSoundManager.loadSounds();

        /*-------- Call soundTextView Id------*/

        e1 = (TextView) findViewById(R.id.e1);
        a1 = (TextView) findViewById(R.id.a1);
        d2 = (TextView) findViewById(R.id.d2);
        g2 = (TextView) findViewById(R.id.g2);
        b2 = (TextView) findViewById(R.id.b2);
        e3 = (TextView) findViewById(R.id.e3);

        e1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play(1);
            }
        });

        a1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play(6);
            }
        });
        d2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play(11);
            }
        });
        g2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play(16);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play(20);
            }
        });
        e3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play(13);
            }
        });



    }       /*--------------closed onCreate Method*/


    public static void play(int id){
            GuitarSoundManager.playSound(id,1.5f);

    }





    private void nevigation(){
        mDrawerLayout = (DrawerLayout) findViewById(R.id.activity_home_drawarLayout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation);
        navigationView.setNavigationItemSelectedListener(this);

    }   //----Closed nevigation method--//

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.actionhomebutton, menu);

        return true;
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_metronome) {
            Intent metroNome = new Intent(Home_Activity.this, Metronome.class);
            startActivity(metroNome);
        } else if (id == R.id.nav_playGuitar) {
            Intent playGuitar = new Intent(Home_Activity.this, playGuitar.class);
            startActivity(playGuitar);

        } else if (id == R.id.nav_info) {

            Intent usingInfo = new Intent(Home_Activity.this, UsingInformation.class);
            startActivity(usingInfo);

        } else if (id == R.id.nav_songList_with_informtion) {
            Intent songlist = new Intent(Home_Activity.this, SongListWithInfo.class);
            startActivity(songlist);
        } else if (id == R.id.developerInfo) {
            Intent developers = new Intent(Home_Activity.this, developers.class);
            startActivity(developers);
        } else if(id == R.id.nav_Home){
            Intent home = new Intent(Home_Activity.this, Home_Activity.class);
            startActivity(home);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.activity_home_drawarLayout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
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

}
