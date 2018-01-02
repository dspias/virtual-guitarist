package com.example.pdspe.virtualguitarist;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Collections;

/**
 * Created by pdspe on 01-Jan-18.
 */


//----- Class PlayingGuitarStrumming----//

public class PlayingGuitarStrumming extends Service {

    private static final String TAG = "MyService";
    private boolean isRunning  = false;
    private Looper looper;
    private MyServiceHandler myServiceHandler;

    /*------------ Init userValue variable------------*/
    private  static int tempoValue,numberOfChord, timeDifference, semiTimeDifference;
    private static String signatureValue, strummingName;
    private static String [][] chordListAndType;


    //------- create setter method ------//

    public static void setTempoValue(int tempo) {
        tempoValue = tempo;
        timeDifference = 60000/tempoValue;
        semiTimeDifference = timeDifference/240;
    }

    public static void setSignatureValue(String signature) {
        signatureValue = signature;
    }

    public static void setNumberOfChord(int number_of_chrod) {
        numberOfChord = number_of_chrod;
    }

    public static void setStrummingName(String strummingValue) {
        strummingName = strummingValue;
    }
    public static void setChordListAndType(String [][] chrodList) {
        chordListAndType = chrodList;
    }


    @Override
    public void onCreate() {
        HandlerThread handlerthread = new HandlerThread("MyThread", Process.THREAD_PRIORITY_BACKGROUND);
        handlerthread.start();
        looper = handlerthread.getLooper();
        myServiceHandler = new MyServiceHandler(looper);
        isRunning = true;
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Message msg = myServiceHandler.obtainMessage();
        msg.arg1 = startId;
        myServiceHandler.sendMessage(msg);
        Toast.makeText(this, "Started selectable strumming", Toast.LENGTH_SHORT).show();
        //If service is killed while starting, it restarts.
        return START_STICKY;
    }
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public void onDestroy() {
        isRunning = false;
        Toast.makeText(this, "stop running patturn", Toast.LENGTH_SHORT).show();
    }
    private final class MyServiceHandler extends Handler {
        public MyServiceHandler(Looper looper) {
            super(looper);
        }
        @Override
        public void handleMessage(Message msg) {
            synchronized (this) {
                while(isRunning != false){

                    work();         /*----------call metronome method ------*/

                }
            }
            stopSelfResult(msg.arg1);
        }
    }

    /*---------- create metronome method -----*/

    public  void work(){
        if(isRunning != true) return;
        if(signatureValue.equalsIgnoreCase("1 / 1")){
            oneByOne();
        } else if(signatureValue.equalsIgnoreCase("2 / 2")){
            twoByTwo();
        } else if(signatureValue.equalsIgnoreCase("3 / 3")){
            threeByeThree();
        } else if (signatureValue.equalsIgnoreCase("3 / 4")){
            threeByFour();
        } else if(signatureValue.equalsIgnoreCase("4 / 4")){
            fourByfour();
        }

    }       /*-------cloase work method-------*/



    /*------------- One by One total work start -----------------*/
    public void oneByOne(){

        if(strummingName.equalsIgnoreCase("One Bye One-1")){

        } else if(strummingName.equalsIgnoreCase("One Bye One-2")){

        } else if(strummingName.equalsIgnoreCase("One Bye One-3")){

        } else if(strummingName.equalsIgnoreCase("One Bye One-4")){

        } else if(strummingName.equalsIgnoreCase("One Bye One-5")){

        }
    }

    /*------------- One by One total work complete -----------------*/







    /*------------- Two by Two total work start -----------------*/
    public void twoByTwo(){

        if(strummingName.equalsIgnoreCase("Two Bye Two-1")){

        } else if(strummingName.equalsIgnoreCase("Two Bye Two-2")){

        } else if(strummingName.equalsIgnoreCase("Two Bye Two-3")){

        } else if(strummingName.equalsIgnoreCase("Two Bye Two-4")){

        } else if(strummingName.equalsIgnoreCase("Two Bye Two-5")){

        }
    }

    /*------------- Two by Two total work complete -----------------*/





    /*------------- Three by Three total work start -----------------*/
    public void threeByeThree(){

        if(strummingName.equalsIgnoreCase("Three Bye Three-1")){

        } else if(strummingName.equalsIgnoreCase("Three Bye Three-2")){

        } else if(strummingName.equalsIgnoreCase("Three Bye Three-3")){

        } else if(strummingName.equalsIgnoreCase("Three Bye Three-4")){

        } else if(strummingName.equalsIgnoreCase("Three Bye Three-5")){

        }

    }

    /*------------- Three by Three total work complete -----------------*/


    /*------------- Three by Four total work start -----------------*/
    public void threeByFour(){

        if(strummingName.equalsIgnoreCase("Three Bye four-1")){

        } else if(strummingName.equalsIgnoreCase("Three Bye four-2")){

        } else if(strummingName.equalsIgnoreCase("Three Bye four-3")){

        } else if(strummingName.equalsIgnoreCase("Three Bye four-4")){

        } else if(strummingName.equalsIgnoreCase("Three Bye four-5")){

        }

    }


    /*------------- Three by Four total work complete -----------------*/







    /*------------- Four by Four total work start -----------------*/
    public void fourByfour(){
        if(strummingName.equalsIgnoreCase("Four bye four-1")){
            kaharbadown();
        } else if(strummingName.equalsIgnoreCase("Four bye four-2")){

        } else if(strummingName.equalsIgnoreCase("Four bye four-3")){

        } else if(strummingName.equalsIgnoreCase("Four bye four-4")){

        } else if(strummingName.equalsIgnoreCase("Four bye four-5")){

        }

    }

    /*---------- fourByfour all strumming method-----------*/
    public void kaharbadown(){
        if(numberOfChord == 2){

        } else if(numberOfChord == 8){

        } else {
            for(int i=0;i<numberOfChord;i++){


                positionMainDown(chordListAndType[i],240 * 2);
                positionMainDown(chordListAndType[i],240 / 2);
                positionMainUp(chordListAndType[i],240 / 2);
                positionMainDown(chordListAndType[i],240 /2);
                positionMainUp(chordListAndType[i],240 /2);
            }
        }
    }


    /*------------- Four by Four total work complete -----------------*/




    /*------------- usable position Play style Mehtod ---------------*/
    public void positionMainDown(String[] chordName, int waitTime){

        int [] chord = new int[6];
        chord= findChordSerial(chordName);

        for(int i=0;i<4;i++){chord[i] = chord[i+1];}
        try{

            play4(chord);
            Thread.sleep(waitTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void positionMainUp(String[] chordName, int waitTime){
        int [] chord = new int[6];
        chord= findChordSerial(chordName);
        Collections.reverse(Arrays.asList(chord));
        for(int i=0;i<4;i++){chord[i] = chord[i+1];}
        try{
            play4(chord);
            Thread.sleep(waitTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void positionSlowDown(String[] chordName, int waitTime){
        int [] chord = new int[6];
        chord= findChordSerial(chordName);
        for(int i=0;i<2;i++){chord[i] = chord[i+3];}
        try{
            play2(chord);
            Thread.sleep(waitTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void positionSlowUp(String[] chordName, int waitTime){
        int [] chord = new int[6];
        chord= findChordSerial(chordName);
        Collections.reverse(Arrays.asList(chord));
        for(int i=0;i<2;i++){chord[i] = chord[i+3];}
        try{
            play2(chord);
            Thread.sleep(waitTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    public int [] findChordSerial(String[] chordName) {
        int [] b = new int[6];
        Chord a = new Chord();

        if(chordName[1].equals("Major")) {
            b = a.major(chordName[0]);
        }
        else if(chordName[1].equals("Minor")) {
            b = a.minor(chordName[0]);
        }
        else if(chordName[1].equals("Sus2")) {
            b = a.sus2(chordName[0]);
        }
        else if(chordName[1].equals("Sus4")) {
            b = a.sus4(chordName[0]);
        }
        return b;
    }

    public void play4(int[] a){
        //float x = tempoValue/80;
        try{
            if(isRunning != true) return;
            GuitarSoundManager.playSound(a[0],1.5f);
            Thread.sleep(semiTimeDifference * 1);

            if(isRunning != true) return;
            GuitarSoundManager.playSound(a[1],1.5f);
            Thread.sleep(semiTimeDifference * 1);

            if(isRunning != true) return;
            GuitarSoundManager.playSound(a[2],1.5f);
            Thread.sleep(semiTimeDifference * 1);

            if(isRunning != true) return;
            GuitarSoundManager.playSound(a[3],1.5f);

        } catch(InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void play2(int[] a){
        try{
            if(isRunning != true) return;
            GuitarSoundManager.playSound(a[0],1.0f);
            Thread.sleep(semiTimeDifference * 6);

            if(isRunning != true) return;
            GuitarSoundManager.playSound(a[1],1.0f);

        } catch(InterruptedException e) {
            e.printStackTrace();
        }

    }








}       //--------closed PlayingGuitarStrumming class --------//