package com.example.pdspe.virtualguitarist;

import android.app.Dialog;
import android.content.ClipData;
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
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.Layout;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

public class playGuitar extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    protected DrawerLayout mDrawerLayout;
    protected ActionBarDrawerToggle mToggle;

    //------Initial tempo variable ----//
    protected Button mTempo;

    //--------- Initial playGuitar Signature variable --------//
    private Spinner signatureSpinner;
    private ArrayAdapter<CharSequence> signatureAdapter;



    //--------- Initial playGuitar strumming variable --------//
    private Spinner strummingSpinner;
    private ArrayAdapter<CharSequence> strummingAdapter;

    //--------- Initial playGuitar Chords variable --------//
    private Button chordButton;

    private Spinner numberofChord;
    private ArrayAdapter<CharSequence> numberOfChordAddapter;

    private Spinner chordNameSpinner1,chordNameSpinner2,chordNameSpinner3,chordNameSpinner4,chordNameSpinner5,chordNameSpinner6,
            chordNameSpinner7,chordNameSpinner8,    chordTypeSpinner1,chordTypeSpinner2,chordTypeSpinner3,chordTypeSpinner4,
            chordTypeSpinner5,chordTypeSpinner6,chordTypeSpinner7,chordTypeSpinner8;

    private ArrayAdapter<CharSequence> chordNameAddapter, chordTypeAddapter;

    //------------ Initial media opition --------------//
    boolean isPlay = false;
    ImageButton playGuitarImageButton;
    private int numberOfChord;
    private String [][] chordListAndType;
    private Intent intent;

    //----------- set chord setup or not check-------------//
    private boolean chrodIsSetup;


    TextView a;




    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_guitar);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);

        setTitle("Play Guitar");        //----Set title on Play Guitar Page----//
        nevigation();

        //-------- Init sound Manager---------//
        GuitarSoundManager.getInstance();
        GuitarSoundManager.initSounds(this);
        GuitarSoundManager.loadSounds();



        //-----------Tempo input start -------//
        mTempo = (Button) findViewById(R.id.tempo_button);

        mTempo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                setTempo();
            }
        });     //----------Tempo input Complete ---------//



        //----- Set Signature value with spinner-------//
        signatureSpinner = (Spinner) findViewById(R.id.signature_spinner);
        signatureAdapter = ArrayAdapter.createFromResource(this, R.array.playGuitar_Signature, android.R.layout.simple_spinner_item);
        signatureAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        signatureSpinner.setAdapter(signatureAdapter);
        signatureSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String name= null;
                if(signatureSpinner != null && signatureSpinner.getSelectedItem() !=null ) {
                    name = signatureSpinner.getSelectedItem().toString();
                    if(name.matches("1 / 1")){
                        strummingAdapter = ArrayAdapter.createFromResource(playGuitar.this, R.array.playGuitar_strumming_1_1, android.R.layout.simple_spinner_item);
                    }else if(name.matches("2 / 2")){
                        strummingAdapter = ArrayAdapter.createFromResource(playGuitar.this, R.array.playGuitar_strumming_2_2, android.R.layout.simple_spinner_item);
                    } else if(name.matches("3 / 3")) {
                        strummingAdapter = ArrayAdapter.createFromResource(playGuitar.this, R.array.playGuitar_strumming_3_3, android.R.layout.simple_spinner_item);
                    } else if(name.matches("3 / 4")){
                        strummingAdapter = ArrayAdapter.createFromResource(playGuitar.this, R.array.playGuitar_strumming_3_4, android.R.layout.simple_spinner_item);
                    } else if(name.matches("4 / 4")){
                        strummingAdapter = ArrayAdapter.createFromResource(playGuitar.this, R.array.playGuitar_strumming_4_4, android.R.layout.simple_spinner_item);
                    } else if(name.matches("6 / 4")){
                        strummingAdapter = ArrayAdapter.createFromResource(playGuitar.this, R.array.playGuitar_strumming_6_8, android.R.layout.simple_spinner_item);
                    }
                    strummingAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    strummingSpinner.setAdapter(strummingAdapter);

                    if(name.matches("1 / 1")){
                        numberOfChordAddapter = ArrayAdapter.createFromResource(playGuitar.this, R.array.playGuitar_Number_Of_Chord_1_1, android.R.layout.simple_spinner_item);
                    } else if(name.matches("2 / 2")){
                        numberOfChordAddapter = ArrayAdapter.createFromResource(playGuitar.this, R.array.playGuitar_Number_Of_Chord_2_2, android.R.layout.simple_spinner_item);
                    } else if(name.matches("3 / 3")){
                        numberOfChordAddapter = ArrayAdapter.createFromResource(playGuitar.this, R.array.playGuitar_Number_Of_Chord_3_3, android.R.layout.simple_spinner_item);
                    } else if(name.matches("3 / 4")){
                        numberOfChordAddapter = ArrayAdapter.createFromResource(playGuitar.this, R.array.playGuitar_Number_Of_Chord_3_4, android.R.layout.simple_spinner_item);
                    } else if(name.matches("4 / 4")){
                        numberOfChordAddapter = ArrayAdapter.createFromResource(playGuitar.this, R.array.playGuitar_Number_Of_Chord_4_4, android.R.layout.simple_spinner_item);
                    } else if(name.matches("6 / 4")){
                        numberOfChordAddapter = ArrayAdapter.createFromResource(playGuitar.this, R.array.playGuitar_Number_Of_Chord_4_4, android.R.layout.simple_spinner_item);
                    }

                } else  {
                    Toast.makeText(getApplicationContext(), "First Set Signature.", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getApplicationContext(), "First Set Signature.", Toast.LENGTH_SHORT).show();
            }
        });

        /*------------ complete Signature ------------*/




        /*------------ Set Strumming name Using Spinner------------*/
        strummingSpinner = (Spinner) findViewById(R.id.srumming_spinner);

        /*---------- Complete set strumming ---------*/



        /*----------- chord setup start-----------*/
        chordButton = (Button) findViewById(R.id.setup_usable_chord);

        chordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setupChord();
            }
        });

        /*------------ Chord setup complete--------------*/



        //------- minus button working code -----//
        final ImageButton minus = (ImageButton) findViewById(R.id.negative_buttton);
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                int oldVal = 0;
                try {
                    oldVal = Integer.parseInt(String.valueOf(mTempo.getText()));
                } catch (NumberFormatException nfe) {}
                if(oldVal > 40) oldVal--;
                mTempo.setText(String.valueOf(oldVal));
                setPlayGuitarValue();

            }
        });     //----------minus botton code complete -------//

        //------- plus button working code -----//
        final ImageButton plus = (ImageButton) findViewById(R.id.positive_button);
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int oldVal = 0;
                try {
                    oldVal = Integer.parseInt(String.valueOf(mTempo.getText()));
                } catch (NumberFormatException nfe) {}
                if(oldVal < 240) oldVal++;
                mTempo.setText(String.valueOf(oldVal));
                setPlayGuitarValue();

            }
        });     //----------plus botton code complete -------//



        /*---------------- media play pause start ------------------*/
        playGuitarImageButton = (ImageButton) findViewById(R.id.media_button);
        intent = new Intent(playGuitar.this, PlayingGuitarStrumming.class);

        chrodIsSetup = false;
        playGuitarImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (chrodIsSetup == true) {
                    isPlay = !isPlay; // reverse
                    if(isPlay){
                        playGuitarImageButton.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.stop_btn));
                        int size = getResources().getDimensionPixelOffset(R.dimen.thirty);
                        playGuitarImageButton.setPadding(size,size,size,size);

                        setPlayGuitarValue();
                        startService(intent);
                    } else {
                        playGuitarImageButton.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.play_btn));
                        int topbottom = getResources().getDimensionPixelOffset(R.dimen.thirty);
                        int right = getResources().getDimensionPixelOffset(R.dimen.towentysix);
                        int left = getResources().getDimensionPixelOffset(R.dimen.thirty);
                        playGuitarImageButton.setPadding(left, topbottom, right, topbottom);

                        stopService(intent);
                    }

                } else {
                    Toast.makeText(playGuitar.this, "Setup Chord First than play.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        /*--------- set chord value to last layout---------*/
        a = (TextView) findViewById(R.id.show_chords);
        a.setText(setChordValue());


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }       //------ Closed onCreate Method -----//

    @Override
    public void onBackPressed() {
        stopService(intent);
        finish();
    }



    /*--------------- Set Guitar Value Method --------------*/
    public void setPlayGuitarValue(){
        int tempoValue = Integer.parseInt(String.valueOf(mTempo.getText()));

        String signatureValue = signatureSpinner.getSelectedItem().toString().trim();
        String strummingValue = strummingSpinner.getSelectedItem().toString().trim();

        PlayingGuitarStrumming.setTempoValue(tempoValue);
        PlayingGuitarStrumming.setSignatureValue(signatureValue);
        PlayingGuitarStrumming.setStrummingName(strummingValue);
        PlayingGuitarStrumming.setNumberOfChord(numberOfChord);
        PlayingGuitarStrumming.setChordListAndType(chordListAndType);

    }


    /*------------ set chrod Value in last layout method ------------*/
    public String setChordValue(){
        String chord = new String();
        for(int i=0;i<numberOfChord;i++){
            if(numberOfChord-1 == i){
                chord+=chordListAndType[i][0]+"-"+chordListAndType[i][1]+".";
            }else {
                chord+=chordListAndType[i][0]+"-"+chordListAndType[i][1]+", ";
            }
        }
        return chord;
    }



    /*----------- nevigation method start------------*/
    private void nevigation() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.activity_play_guitar_DrawarLayout);
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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.actionhomebutton, menu);

//        Intent playGuitar = new Intent(playGuitar.this, Home_Activity.class);
//        startActivity(playGuitar);
        return true;
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_metronome) {
            Intent playGuitar = new Intent(playGuitar.this, Metronome.class);
            startActivity(playGuitar);
        } else if (id == R.id.nav_playGuitar) {
            Intent playGuitar = new Intent(playGuitar.this, playGuitar.class);
            startActivity(playGuitar);

        } else if (id == R.id.nav_info) {

        } else if (id == R.id.nav_songList_with_informtion) {

        } else if (id == R.id.nav_setting) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.activity_home_drawarLayout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    /*-------- create setTempo method --------*/
    protected void setTempo() {
        final Dialog d = new Dialog(playGuitar.this);
        d.setContentView(R.layout.inputtempovalue);
        final NumberPicker np = (NumberPicker) d.findViewById(R.id.numberPicker_tempo);
        np.setMaxValue(240);

        int oldVal = 0;
        try {
            oldVal = Integer.parseInt(String.valueOf(mTempo.getText()));
        } catch (NumberFormatException nfe) {
        }
        np.setValue(oldVal);

        np.setMinValue(40);
        np.setWrapSelectorWheel(false);
        np.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                //value1 += newVal;
                mTempo.setText(String.valueOf(np.getValue()));

            }
        });

        Button okay = (Button) d.findViewById(R.id.okayButton);
        okay.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                setPlayGuitarValue(); d.dismiss();
            }
        });
        d.show();


    }       //-------closed Tempo Method-----//





    /*-------- create setTempo method --------*/
    protected void setupChord() {
        final Dialog d = new Dialog(playGuitar.this);
        d.setContentView(R.layout.choose_chords);

        final LinearLayout two,three,four,five, six,seven,eight;
        two = (LinearLayout) d.findViewById(R.id.inputChord_2);
        three = (LinearLayout) d.findViewById(R.id.inputChord_3);
        four = (LinearLayout) d.findViewById(R.id.inputChord_4);
        five = (LinearLayout) d.findViewById(R.id.inputChord_5);
        six = (LinearLayout) d.findViewById(R.id.inputChord_6);
        seven = (LinearLayout) d.findViewById(R.id.inputChord_7);
        eight = (LinearLayout) d.findViewById(R.id.inputChord_8);



        numberofChord = (Spinner) d.findViewById(R.id.numberOfchords_spinner);
        numberOfChordAddapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        numberofChord.setAdapter(numberOfChordAddapter);
        numberofChord.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            String name = null;
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                name = numberofChord.getSelectedItem().toString();
                if(name.trim().equalsIgnoreCase("2")){
                    two.setVisibility(View.VISIBLE);

                    three.setVisibility(View.GONE);
                    four.setVisibility(View.GONE);
                    five.setVisibility(View.GONE) ;
                    six.setVisibility(View.GONE);
                    seven.setVisibility(View.GONE);
                    eight.setVisibility(View.GONE);
                } else if(name.trim().equalsIgnoreCase("3")){
                    two.setVisibility(View.VISIBLE);
                    three.setVisibility(View.VISIBLE);

                    four.setVisibility(View.GONE);
                    five.setVisibility(View.GONE) ;
                    six.setVisibility(View.GONE);
                    seven.setVisibility(View.GONE);
                    eight.setVisibility(View.GONE);

                } else if(name.trim().equalsIgnoreCase("4")){
                    two.setVisibility(View.VISIBLE);
                    three.setVisibility(View.VISIBLE);
                    four.setVisibility(View.VISIBLE);

                    five.setVisibility(View.GONE) ;
                    six.setVisibility(View.GONE);
                    seven.setVisibility(View.GONE);
                    eight.setVisibility(View.GONE);
                } else if(name.trim().equalsIgnoreCase("5")){
                    two.setVisibility(View.VISIBLE);
                    three.setVisibility(View.VISIBLE);
                    four.setVisibility(View.VISIBLE);
                    five.setVisibility(View.VISIBLE) ;

                    six.setVisibility(View.GONE);
                    seven.setVisibility(View.GONE);
                    eight.setVisibility(View.GONE);
                } else if(name.trim().equalsIgnoreCase("6")){
                    two.setVisibility(View.VISIBLE);
                    three.setVisibility(View.VISIBLE);
                    four.setVisibility(View.VISIBLE);
                    five.setVisibility(View.VISIBLE) ;
                    six.setVisibility(View.VISIBLE);

                    seven.setVisibility(View.GONE);
                    eight.setVisibility(View.GONE);
                } else if(name.trim().equalsIgnoreCase("7")){
                    two.setVisibility(View.VISIBLE);
                    three.setVisibility(View.VISIBLE);
                    four.setVisibility(View.VISIBLE);
                    five.setVisibility(View.VISIBLE) ;
                    six.setVisibility(View.VISIBLE);
                    seven.setVisibility(View.VISIBLE);

                    eight.setVisibility(View.GONE);
                } else if (name.trim().equalsIgnoreCase("8")){
                    two.setVisibility(View.VISIBLE);
                    three.setVisibility(View.VISIBLE);
                    four.setVisibility(View.VISIBLE);
                    five.setVisibility(View.VISIBLE) ;
                    six.setVisibility(View.VISIBLE);
                    seven.setVisibility(View.VISIBLE);
                    eight.setVisibility(View.VISIBLE);
                } else if (name.trim().equalsIgnoreCase("1")){
                    two.setVisibility(View.GONE);

                    three.setVisibility(View.GONE);
                    four.setVisibility(View.GONE);
                    five.setVisibility(View.GONE) ;
                    six.setVisibility(View.GONE);
                    seven.setVisibility(View.GONE);
                    eight.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        chordNameSpinner1 = (Spinner) d.findViewById(R.id.chord_Sl_1);
        chordNameSpinner2 = (Spinner) d.findViewById(R.id.chord_Sl_2);
        chordNameSpinner3 = (Spinner) d.findViewById(R.id.chord_Sl_3);
        chordNameSpinner4 = (Spinner) d.findViewById(R.id.chord_Sl_4);
        chordNameSpinner5 = (Spinner) d.findViewById(R.id.chord_Sl_5);
        chordNameSpinner6 = (Spinner) d.findViewById(R.id.chord_Sl_6);
        chordNameSpinner7 = (Spinner) d.findViewById(R.id.chord_Sl_7);
        chordNameSpinner8 = (Spinner) d.findViewById(R.id.chord_Sl_8);

        chordTypeSpinner1 = (Spinner) d.findViewById(R.id.chord_Type_1);
        chordTypeSpinner2 = (Spinner) d.findViewById(R.id.chord_Type_2);
        chordTypeSpinner3 = (Spinner) d.findViewById(R.id.chord_Type_3);
        chordTypeSpinner4 = (Spinner) d.findViewById(R.id.chord_Type_4);
        chordTypeSpinner5 = (Spinner) d.findViewById(R.id.chord_Type_5);
        chordTypeSpinner6 = (Spinner) d.findViewById(R.id.chord_Type_6);
        chordTypeSpinner7 = (Spinner) d.findViewById(R.id.chord_Type_7);
        chordTypeSpinner8 = (Spinner) d.findViewById(R.id.chord_Type_8);

        chordNameAddapter = ArrayAdapter.createFromResource(playGuitar.this, R.array.playGuitar_Chord_Name, android.R.layout.simple_spinner_item);
        chordTypeAddapter = ArrayAdapter.createFromResource(playGuitar.this, R.array.playGuitar_Chord_Type, android.R.layout.simple_spinner_item);

        chordNameAddapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        chordTypeAddapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        chordNameSpinner1.setAdapter(chordNameAddapter);
        chordNameSpinner2.setAdapter(chordNameAddapter);
        chordNameSpinner3.setAdapter(chordNameAddapter);
        chordNameSpinner4.setAdapter(chordNameAddapter);
        chordNameSpinner5.setAdapter(chordNameAddapter);
        chordNameSpinner6.setAdapter(chordNameAddapter);
        chordNameSpinner7.setAdapter(chordNameAddapter);
        chordNameSpinner8.setAdapter(chordNameAddapter);

        chordTypeSpinner1.setAdapter(chordTypeAddapter);
        chordTypeSpinner2.setAdapter(chordTypeAddapter);
        chordTypeSpinner3.setAdapter(chordTypeAddapter);
        chordTypeSpinner4.setAdapter(chordTypeAddapter);
        chordTypeSpinner5.setAdapter(chordTypeAddapter);
        chordTypeSpinner6.setAdapter(chordTypeAddapter);
        chordTypeSpinner7.setAdapter(chordTypeAddapter);
        chordTypeSpinner8.setAdapter(chordTypeAddapter);



        Button okay = (Button) d.findViewById(R.id.okayButton);
        okay.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                numberOfChord = Integer.parseInt(String.valueOf(numberofChord.getSelectedItem()).trim());
                chordListAndType = new String[9][3];
                for(int i=0; i< numberOfChord; i++){
                    if(i==0){
                        chordListAndType[i][0] = chordNameSpinner1.getSelectedItem().toString().trim();
                        chordListAndType[i][1] = chordTypeSpinner1.getSelectedItem().toString().trim();
                    }
                    if(i==1){
                        chordListAndType[i][0] = chordNameSpinner2.getSelectedItem().toString().trim();
                        chordListAndType[i][1] = chordTypeSpinner2.getSelectedItem().toString().trim();
                    }
                    if(i==2){
                        chordListAndType[i][0] = chordNameSpinner3.getSelectedItem().toString().trim();
                        chordListAndType[i][1] = chordTypeSpinner3.getSelectedItem().toString().trim();
                    }
                    if(i==3){
                        chordListAndType[i][0] = chordNameSpinner4.getSelectedItem().toString().trim();
                        chordListAndType[i][1] = chordTypeSpinner4.getSelectedItem().toString().trim();
                    }
                    if(i==4){
                        chordListAndType[i][0] = chordNameSpinner5.getSelectedItem().toString().trim();
                        chordListAndType[i][1] = chordTypeSpinner5.getSelectedItem().toString().trim();
                    }
                    if(i==5){
                        chordListAndType[i][0] = chordNameSpinner6.getSelectedItem().toString().trim();
                        chordListAndType[i][1] = chordTypeSpinner6.getSelectedItem().toString().trim();
                    }
                    if(i==6){
                        chordListAndType[i][0] = chordNameSpinner7.getSelectedItem().toString().trim();
                        chordListAndType[i][1] = chordTypeSpinner7.getSelectedItem().toString().trim();
                    }
                    if(i==7){
                        chordListAndType[i][0] = chordNameSpinner8.getSelectedItem().toString().trim();
                        chordListAndType[i][1] = chordTypeSpinner8.getSelectedItem().toString().trim();
                    }

                }
                a.setText(setChordValue());
                chrodIsSetup = true;
                setPlayGuitarValue();
                d.dismiss();
            }
        });
        d.show();


    }       /*-------closed Tempo Method-----*/






    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("playGuitar Page") // TODO: Define a title for the content shown.
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
}       //-------- Closed PlayGuitar Class -----//
