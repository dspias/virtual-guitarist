package com.example.pdspe.virtualguitarist;

import android.app.Dialog;
import android.media.AudioManager;
import android.net.Uri;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Spinner;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

public class playGuitar extends AppCompatActivity {

    protected DrawerLayout mDrawerLayout;
    protected ActionBarDrawerToggle mToggle;

    //------Initial tempo variable ----//
    protected Button mTempo;

    //--------- Initial playGuitar Signature variable --------//
    private Spinner mSpinner;
    private ArrayAdapter<CharSequence> adapter;
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

        //-----------Tempo input start -------//
        mTempo = (Button) findViewById(R.id.tempo_button);

        mTempo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                setTempo();
            }
        });     //----------Tempo input Complete ---------//

        //----- Set Signature value -------//
        mSpinner = (Spinner) findViewById(R.id.signature_spinner);
        adapter = ArrayAdapter.createFromResource(this, R.array.playGuitar_Signature, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }       //------ Closed onCreate Method -----//


    private void nevigation() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.activity_play_guitar_DrawarLayout);
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
    }


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
                d.dismiss();
            }
        });
        d.show();


    }       //-------closed Tempo Method-----//


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
