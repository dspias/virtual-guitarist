package com.example.pdspe.virtualguitarist;


import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
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
    TextView e1,a1,d2,g2,b2,e3,headerText;
    Button e1w,a1w,d2w,g2w,b2w,e3w;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        setTitle("VIRTUAL GUITARIST");

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
        headerText = (TextView) findViewById(R.id.header_text);

        e1w = (Button) findViewById(R.id.e1w);
        a1w = (Button) findViewById(R.id.a1w);
        d2w = (Button) findViewById(R.id.d2w);
        g2w = (Button) findViewById(R.id.g2w);
        b2w = (Button) findViewById(R.id.b2w);
        e3w = (Button) findViewById(R.id.e3w);

        headerText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GuitarSoundManager.playSound(50,1.03f);
            }
        });

        e1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play(1);
                allInvisible();
                e1w.setVisibility(View.VISIBLE);

            }

        });
        a1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play(6);
                allInvisible();
                a1w.setVisibility(View.VISIBLE);
            }
        });
        d2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play(11);
                allInvisible();
                d2w.setVisibility(View.VISIBLE);
            }
        });
        g2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play(16);
                allInvisible();
                g2w.setVisibility(View.VISIBLE);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play(20);
                allInvisible();
                b2w.setVisibility(View.VISIBLE);
            }
        });
        e3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play(49);
                allInvisible();
                e3w.setVisibility(View.VISIBLE);
            }
        });



    }       /*--------------closed onCreate Method---------------*/


    public static void play(int id){
            GuitarSoundManager.playSound(id,1.5f);

    }

    public void allInvisible(){

        e1w.setVisibility(View.INVISIBLE);
        a1w.setVisibility(View.INVISIBLE);
        d2w.setVisibility(View.INVISIBLE);
        g2w.setVisibility(View.INVISIBLE);
        b2w.setVisibility(View.INVISIBLE);
        e3w.setVisibility(View.INVISIBLE);
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
//            Intent home = new Intent(Home_Activity.this, Home_Activity.class);
//            startActivity(home);
        } else if(id == R.id.nav_chrod_guide){
            Intent chrodguide = new Intent(Home_Activity.this, ChordGuide.class);
            startActivity(chrodguide);
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
