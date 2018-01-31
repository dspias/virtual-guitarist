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
    private  static int tempoValue,numberOfChord;
    private static double timeDifference, semiTimeDifference;
    private static String signatureValue, strummingName;
    private static String [][] chordListAndType;


    //------- create setter method ------//

    public static void setTempoValue(int tempo) {
        tempoValue = tempo;
        timeDifference = 60000/(double)(tempoValue);
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
        } else if(signatureValue.equalsIgnoreCase("6 / 4")){
            sixByEight();
        }

    }       /*-------cloase work method-------*/



    /*------------- One by One total work start -----------------*/
    public void oneByOne(){

        if(isRunning != true) return;

        if(strummingName.equalsIgnoreCase("Basic Down")){ oneByOne1();  }
        else if(strummingName.equalsIgnoreCase("Basic Down up")){ oneByOne2();  }
    }

    /*--------- OnyByOne1 Method start--------*/
    public void oneByOne1(){

        if(isRunning != true) return;

        for(int i=0;i<numberOfChord;i++){ if(isRunning != true) return; oneByOne1Cod(i,0); }


    }

    public void oneByOne1Cod(int i,int dbl){
        positionMainDown(chordListAndType[i],semiTimeDifference * 240 * 1);
    }
    /*--------- Three bye Four first strumming method closed----------*/



    /*--------- OnyByOne1 Method start--------*/
    public void oneByOne2(){

        if(isRunning != true) return;

        for(int i=0;i<numberOfChord;i++){ if(isRunning != true) return; oneByOne2Cod(i,0); }


    }

    public void oneByOne2Cod(int i,int dbl){
        positionMainDown(chordListAndType[i],semiTimeDifference * 240 / 2);
        positionMainUp(chordListAndType[i],semiTimeDifference * 240 / 2);
    }
    /*--------- Three bye Four first strumming method closed----------*/

    /*------------- One by One total work complete -----------------*/







    /*------------- Two by Two total work start -----------------*/
    public void twoByTwo(){
        if(isRunning != true) return;

        if(strummingName.equalsIgnoreCase("Common time")){ twoByTwo1(); }
    }


    /*------ twoByTwo1 method-----*/
    public void twoByTwo1(){
        if(isRunning != true) return;

        if(numberOfChord == 4){
            for(int i = 0;i<4;i+=2){
                twoByTwo1Cod(i,i+1);
            }
        } else {
            for(int i=0;i<numberOfChord;i++){ if(isRunning != true) return; twoByTwo1Cod(i,0); }
        }

    }
    public void twoByTwo1Cod(int i,int dbl){
        positionMainDown(chordListAndType[i],semiTimeDifference * 240 * 1);
        positionMainDown(chordListAndType[i+dbl],semiTimeDifference * 240 / 2);
        positionMainUp(chordListAndType[i+dbl],semiTimeDifference * 240 / 2);
    }

    /*------------- Two by Two total work complete -----------------*/





    /*------------- Three by Three total work start -----------------*/
    public void threeByeThree(){

        if(isRunning != true) return;

        if(strummingName.equalsIgnoreCase("Weird waltz")){
            threeByeThree1();
        } else if(strummingName.equalsIgnoreCase("Weird mute waltz")){
            threeByeThree2();
        }

    }

    /*--------- Three bye Four first strumming method start----------*/
    public void threeByeThree1(){
        if(isRunning != true) return;

        for(int i=0;i<numberOfChord;i++){ if(isRunning != true) return; threeByeFour1Cod(i,0); }
    }
    /*--------- Three bye Four first strumming method closed----------*/


    /*--------- Three bye Four first strumming method start----------*/
    public void threeByeThree2(){
        if(isRunning != true) return;

        for(int i=0;i<numberOfChord;i++){ if(isRunning != true) return; threeByeFour2Cod(i,0); }
    }
    /*--------- Three bye Four first strumming method closed----------*/


    /*------------- Three by Three total work complete -----------------*/







    /*------------- Three by Four total work start -----------------*/
    public void threeByFour(){

        if(isRunning != true) return;

        if(strummingName.equalsIgnoreCase("Common waltz")){
            threeByeFour1();
        } else if(strummingName.equalsIgnoreCase("Mute Waltz")){
            threeByeFour2();
        }

    }


    /*--------- Three bye Four first strumming method start----------*/
    public void threeByeFour1(){
        if(isRunning != true) return;

        if(numberOfChord == 2){
            for(int i = 0;i<4;i++){
                if(i==0 || i==1){ threeByeFour1Cod(0,0); }
                else { threeByeFour1Cod(1,0);}
            }
        } else {
            for(int i=0;i<numberOfChord;i++){ if(isRunning != true) return; threeByeFour1Cod(i,0); }
        }

    }

    public void threeByeFour1Cod(int i,int dbl){
        positionMainDown(chordListAndType[i],semiTimeDifference * 240 * 1);
        positionMainDown(chordListAndType[i],semiTimeDifference * 240 / 2);
        positionMainUp(chordListAndType[i+dbl],semiTimeDifference * 240 / 2);
        positionMainDown(chordListAndType[i+dbl],semiTimeDifference * 240 / 2);
        positionMainUp(chordListAndType[i+dbl],semiTimeDifference * 240 / 2);
    }
    /*--------- Three bye Four first strumming method closed----------*/


    /*--------- Three bye Four first strumming method start----------*/
    public void threeByeFour2(){
        if(isRunning != true) return;

        if(numberOfChord == 2){
            for(int i = 0;i<4;i++){
                if(i==0 || i==1){ threeByeFour2Cod(0,0); }
                else { threeByeFour2Cod(1,0);}
            }
        } else {
            for(int i=0;i<numberOfChord;i++){ if(isRunning != true) return; threeByeFour2Cod(i,0); }
        }

    }

    public void threeByeFour2Cod(int i,int dbl){
        positionMainDown(chordListAndType[i],semiTimeDifference * 240 * 1);
        positionMuteDown(chordListAndType[i],semiTimeDifference * 240 / 2);
        positionMainUp(chordListAndType[i+dbl],semiTimeDifference * 240 / 2);
        positionMuteDown(chordListAndType[i+dbl],semiTimeDifference * 240 / 2);
        positionMainUp(chordListAndType[i+dbl],semiTimeDifference * 240 / 2);
    }
    /*--------- Three bye Four first strumming method closed----------*/




    /*------------- Three by Four total work complete -----------------*/







    /*------------- Four by Four total work start -----------------*/
    public void fourByfour(){

        if(isRunning != true) return;

        if(strummingName.equalsIgnoreCase("Common 4 beat")){ kaharbadown(); }

        else if(strummingName.equalsIgnoreCase("Old faithful")){ fourbyeFour2(); }

        else if(strummingName.equalsIgnoreCase("Latin dude")){ fourbyeFour3(); }

        else if(strummingName.equalsIgnoreCase("Country")){ fourbyeFour4Country(); }

        else if(strummingName.equalsIgnoreCase("Modern rock")){ fourbyeFour5ModernRock(); }

        else if(strummingName.equalsIgnoreCase("Mute Lava")){ fourbyeFour6(); }

        else if(strummingName.equalsIgnoreCase("Four bye four-7")){   }

    }

    /*---------- fourByfour all strumming method-----------*/

    /*---------- kaharbadown strumming method start-----------*/
    public void kaharbadown(){

        if(isRunning != true) return;

        if(numberOfChord == 2){
            for(int i = 0;i<4;i++){
                if(i==0 || i==1){ kaharbadownCod(0,0); }
                else { kaharbadownCod(1,0);}
            }
        } else if(numberOfChord == 8){
            for(int i=0;i<8;i+=2){ kaharbadownCod(i,1); }
        } else {
            for(int i=0;i<numberOfChord;i++){ if(isRunning != true) return; kaharbadownCod(i,0); }
        }
    }
    public void kaharbadownCod(int i,int dbl){
        positionMainDown(chordListAndType[i],semiTimeDifference * 240 * 2);
        positionMainDown(chordListAndType[i],semiTimeDifference * 240 / 2);
        positionMainUp(chordListAndType[i+dbl],semiTimeDifference * 240 / 2);
        positionMainDown(chordListAndType[i+dbl],semiTimeDifference * 240 *1);
    }
    /*---------- kaharbadown strumming method Complete-----------*/

    /*---------- fourbyeFour2 strumming method start-----------*/
    public void fourbyeFour2(){

        if(isRunning != true) return;

        if(numberOfChord == 2){
            for(int i = 0;i<4;i++){
                if(i==0 || i==1){ fourbyeFour2Cod(0,0); }
                else { fourbyeFour2Cod(1,0);}
            }
        } else if(numberOfChord == 8){
            for(int i=0;i<8;i+=2){ fourbyeFour2Cod(i,1); }
        } else {
            for(int i=0;i<numberOfChord;i++){ if(isRunning != true) return; fourbyeFour2Cod(i,0); }
        }
    }
    public void fourbyeFour2Cod(int i,int dbl){
        positionMainDown(chordListAndType[i],semiTimeDifference * 240 * 1);
        positionMainDown(chordListAndType[i],semiTimeDifference * 240 / 2);
        positionMainUp(chordListAndType[i],semiTimeDifference * 240 * 1);
        positionMainUp(chordListAndType[i+dbl],semiTimeDifference * 240 / 2);
        positionMainDown(chordListAndType[i+dbl],semiTimeDifference * 240 / 2);
        positionMainUp(chordListAndType[i+dbl],semiTimeDifference * 240 / 2);
    }
    /*---------- fourbyeFour2 strumming method Complete-----------*/


    /*---------- fourbyeFour3 strumming method start-----------*/
    public void fourbyeFour3(){

        if(isRunning != true) return;

        if(numberOfChord == 2){
            for(int i = 0;i<4;i++){
                if(i==0 || i==1){ fourbyeFour3Cod(0,0); }
                else { fourbyeFour3Cod(1,0);}
            }
        } else if(numberOfChord == 8){
            for(int i=0;i<8;i+=2){ fourbyeFour3Cod(i,1); }
        } else {
            for(int i=0;i<numberOfChord;i++){ if(isRunning != true) return; fourbyeFour3Cod(i,0); }
        }
    }
    public void fourbyeFour3Cod(int i,int dbl){
        positionMainDown(chordListAndType[i],semiTimeDifference * 240 * 1);
        positionMuteDown(chordListAndType[i],semiTimeDifference * 240 / 2);
        positionMainDown(chordListAndType[i],semiTimeDifference * 240 / 4 * 3);
        positionMainUp(chordListAndType[i],semiTimeDifference * 240 / 4 * 3);
        positionMainUp(chordListAndType[i+dbl],semiTimeDifference * 240 / 2);
        positionMainDown(chordListAndType[i+dbl],semiTimeDifference * 240 / 4 * 3);
        positionMainUp(chordListAndType[i+dbl],semiTimeDifference * 240 / 4 * 3);
        positionMuteDown(chordListAndType[i+dbl],semiTimeDifference * 240 * 1);
    }
    /*---------- fourbyeFour3 strumming method Complete-----------*/



    /*---------- fourbyeFour4Country strumming method start-----------*/
    public void fourbyeFour4Country(){

        if(isRunning != true) return;

        if(numberOfChord == 2){
            for(int i = 0;i<4;i++){
                if(i==0 || i==1){ fourbyeFour4CountryCod(0,0); }
                else { fourbyeFour4CountryCod(1,0);}
            }
        } else if(numberOfChord == 8){
            for(int i=0;i<8;i+=2){ fourbyeFour4CountryCod(i,1); }
        } else {
            for(int i=0;i<numberOfChord;i++){ if(isRunning != true) return; fourbyeFour4CountryCod(i,0); }
        }
    }
    public void fourbyeFour4CountryCod(int i,int dbl){
        positionMainDown(chordListAndType[i],semiTimeDifference * 240 * 1);
        positionMainDown(chordListAndType[i],semiTimeDifference * 240 / 2);
        positionMainUp(chordListAndType[i],semiTimeDifference * 240 / 2);
        positionMainDown(chordListAndType[i+dbl],semiTimeDifference * 240 / 2);
        positionMainDown(chordListAndType[i+dbl],semiTimeDifference * 240 * 1);
        positionMainUp(chordListAndType[i+dbl],semiTimeDifference * 240 / 2);
    }
    /*---------- fourbyeFour4Country strumming method Complete-----------*/


    /*---------- fourbyeFour5ModernRock strumming method start-----------*/
    public void fourbyeFour5ModernRock(){

        if(isRunning != true) return;

        if(numberOfChord == 2){
            for(int i = 0;i<4;i++){
                if(i==0 || i==1){ fourbyeFour5ModernRockCod(0,0); }
                else { fourbyeFour5ModernRockCod(1,0);}
            }
        } else if(numberOfChord == 8){
            for(int i=0;i<8;i+=2){ fourbyeFour5ModernRockCod(i,1); }
        } else {
            for(int i=0;i<numberOfChord;i++){ if(isRunning != true) return; fourbyeFour5ModernRockCod(i,0); }
        }
    }
    public void fourbyeFour5ModernRockCod(int i,int dbl){
        positionMainDown(chordListAndType[i],semiTimeDifference * 240 * 1);
        positionMainDown(chordListAndType[i],semiTimeDifference * 240 / 2);
        positionMainUp(chordListAndType[i],semiTimeDifference * 240 / 2);
        positionMainDown(chordListAndType[i+dbl],semiTimeDifference * 240 / 4 * 3);
        positionMainUp(chordListAndType[i+dbl],semiTimeDifference * 240 / 4 * 3);
        positionMainDown(chordListAndType[i+dbl],semiTimeDifference * 240 / 2);
        positionMainDown(chordListAndType[i+dbl],semiTimeDifference * 240 / 4 * 3);
        positionMainDown(chordListAndType[i+dbl],semiTimeDifference * 240 / 4 * 3);
        positionMainUp(chordListAndType[i+dbl],semiTimeDifference * 240 / 2);
    }
    /*---------- fourbyeFour5ModernRock strumming method Complete-----------*/



    /*---------- fourbyeFour6strumming method start-----------*/
    public void fourbyeFour6(){

        if(isRunning != true) return;

        if(numberOfChord == 2){
            for(int i = 0;i<4;i++){
                if(i==0 || i==1){ fourbyeFour6Cod(0,0); }
                else { fourbyeFour6Cod(1,0);}
            }
        } else if(numberOfChord == 8){
            for(int i=0;i<8;i+=2){ fourbyeFour6Cod(i,1); }
        } else {
            for(int i=0;i<numberOfChord;i++){ if(isRunning != true) return; fourbyeFour6Cod(i,0); }
        }
    }
    public void fourbyeFour6Cod(int i,int dbl){
        positionMainDown(chordListAndType[i],semiTimeDifference * 240 / 2);
        positionMuteDown(chordListAndType[i],semiTimeDifference * 240 / 2);
        positionMainDown(chordListAndType[i],semiTimeDifference * 240 / 2);
        positionMainUp(chordListAndType[i+dbl],semiTimeDifference * 240 / 2 * 3);
        positionMainDown(chordListAndType[i+dbl],semiTimeDifference * 240 / 2);
        positionMainUp(chordListAndType[i+dbl],semiTimeDifference * 240 / 2);

    }
    /*---------- fourbyeFour5ModernRock strumming method Complete-----------*/




    /*------------- Four by Four total work complete -----------------*/






    /*------------- Six bye Eight total work start -----------------*/
    public void sixByEight(){

        if(isRunning != true) return;

        if(strummingName.equalsIgnoreCase("Double waltz")){  sixByFour1();  }

        else if(strummingName.equalsIgnoreCase("Double waltz mute")){ sixByFour2();  }

        else if(strummingName.equalsIgnoreCase("Funky mute waltz")){ sixByFour3();  }
    }


    /*---------- sixByeEight1 method start-----------*/
    public void sixByFour1(){

        if(isRunning != true) return;

        if(numberOfChord == 2){
            for(int i = 0;i<4;i++){
                if(i <= 1){ sixByFour1Cod(0,0); }
                else { sixByFour1Cod(1,0);}
            }
        } else if(numberOfChord == 8){
            for(int i=0;i<8;i+=2){
                    sixByFour1Cod(i,i+1);
            }
        } else {
            for(int i=0;i<numberOfChord;i++){  sixByFour1Cod(i,0); }
        }
    }
    public void sixByFour1Cod(int i,int dbl){
        positionMainDown(chordListAndType[i],semiTimeDifference * 240 * 1);
        positionMainDown(chordListAndType[i],semiTimeDifference * 240 / 2);
        positionMainUp(chordListAndType[i],semiTimeDifference * 240 / 2);
        positionMainDown(chordListAndType[i],semiTimeDifference * 240 / 2);
        positionMainUp(chordListAndType[i],semiTimeDifference * 240 / 2);
        positionMainDown(chordListAndType[i+dbl],semiTimeDifference * 240 * 1);
        positionMainDown(chordListAndType[i+dbl],semiTimeDifference * 240 / 2);
        positionMainUp(chordListAndType[i+dbl],semiTimeDifference * 240 / 2);
        positionMainDown(chordListAndType[i+dbl],semiTimeDifference * 240 / 2);
        positionMainUp(chordListAndType[i+dbl],semiTimeDifference * 240 / 2);

    }
    /*---------- sixByeEight1 strumming method Complete-----------*/



    /*---------- sixByeEight1 method start-----------*/
    public void sixByFour2(){

        if(isRunning != true) return;

        if(numberOfChord == 2){
            for(int i = 0;i<4;i++){
                if(i <= 3){ sixByFour2Cod(0,0); }
                else { sixByFour2Cod(1,0);}
            }
        } else if(numberOfChord == 8){
            for(int i=0;i<8;i+=2){
                    sixByFour2Cod(i,i+1);
            }
        } else {
            for(int i=0;i<numberOfChord;i++){  sixByFour2Cod(i,0); }
        }
    }
    public void sixByFour2Cod(int i,int dbl){
        positionMainDown(chordListAndType[i],semiTimeDifference * 240 * 1);
        positionMuteDown(chordListAndType[i],semiTimeDifference * 240 / 2);
        positionMainUp(chordListAndType[i],semiTimeDifference * 240 / 2);
        positionMuteDown(chordListAndType[i],semiTimeDifference * 240 / 2);
        positionMainUp(chordListAndType[i],semiTimeDifference * 240 / 2);
        positionMainDown(chordListAndType[i+dbl],semiTimeDifference * 240 * 1);
        positionMuteDown(chordListAndType[i+dbl],semiTimeDifference * 240 / 2);
        positionMainUp(chordListAndType[i+dbl],semiTimeDifference * 240 / 2);
        positionMuteDown(chordListAndType[i+dbl],semiTimeDifference * 240 / 2);
        positionMainUp(chordListAndType[i+dbl],semiTimeDifference * 240 / 2);

    }
    /*---------- sixByeEight1 strumming method Complete-----------*/



    /*---------- sixByeEight1 method start-----------*/
    public void sixByFour3(){

        if(isRunning != true) return;

        if(numberOfChord == 2){
            for(int i = 0;i<4 ;i++){
                if(i <= 1){ sixByFour3Cod(0,0); }
                else { sixByFour3Cod(1,0);}
            }
        } else if(numberOfChord == 8){
            for(int i=0;i<8;i+=2){
                    sixByFour3Cod(i,i+1);
            }
        } else {
            for(int i=0;i<numberOfChord;i++){  sixByFour3Cod(i,0); }
        }
    }
    public void sixByFour3Cod(int i,int dbl){
        positionMainDown(chordListAndType[i],semiTimeDifference * 240 * 1);
        positionMuteDown(chordListAndType[i],semiTimeDifference * 240 * 1);
        positionMainDown(chordListAndType[i],semiTimeDifference * 240 * 1);
        positionMainDown(chordListAndType[i+dbl],semiTimeDifference * 240 / 2);
        positionMainDown(chordListAndType[i+dbl],semiTimeDifference * 240 / 2);
        positionMainUp(chordListAndType[i+dbl],semiTimeDifference * 240 / 2);
        positionMuteDown(chordListAndType[i+dbl],semiTimeDifference * 240 / 2);
        positionMainDown(chordListAndType[i+dbl],semiTimeDifference * 240 * 1);

    }
    /*---------- sixByeEight1 strumming method Complete-----------*/






    /*------------- One by One total work complete -----------------*/











    /*------------- usable position Play style Mehtod ---------------*/
    public void positionMainDown(String[] chordName, double waitTime){

        int [] chord = new int[6];
        chord= findChordSerial(chordName);

        int [] chordF = new int[5];

        for(int i=0;i<4;i++){chordF[i] = chord[i+1];}
        try{
            if(isRunning != true) return;
            play4(chordF);
            Thread.sleep((int)waitTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void positionMainUp(String[] chordName, double waitTime){
        int [] chord = new int[6];
        chord= findChordSerial(chordName);
        //Collections.reverse(Arrays.asList(chord));
        int [] chordF = new int[5];
        for(int i=0;i<4;i++){chordF[i] = chord[i+2];}
        try{
            if(isRunning != true) return;
            play4(chordF);
            Thread.sleep((int)waitTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    public void positionMuteDown(String[] chordName, double waitTime){

        int [] chord = new int[6];
        chord= findChordSerial(chordName);

        int [] chordF = new int[5];

        for(int i=0;i<4;i++){chordF[i] = chord[i+1]+24;}
        try{
            if(isRunning != true) return;
            play4m(chordF);
            Thread.sleep((int)waitTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void positionMuteUp(String[] chordName, double waitTime){
        int [] chord = new int[6];
        chord= findChordSerial(chordName);
        //Collections.reverse(Arrays.asList(chord));
        int [] chordF = new int[5];
        for(int i=0;i<4;i++){chordF[i] = chord[i+2]+24;}
        try{
            if(isRunning != true) return;
            play4m(chordF);
            Thread.sleep((int)waitTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    public void positionSlowDown(String[] chordName, double waitTime){
        int [] chord = new int[6];
        chord= findChordSerial(chordName);
        int [] chordF = new int[5];
        for(int i=0;i<2;i++){chordF[i] = chord[i+3];}
        try{
            if(isRunning != true) return;
            play2(chordF);
            Thread.sleep((int)waitTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void positionSlowUp(String[] chordName, double waitTime){
        int [] chord = new int[6];
        chord= findChordSerial(chordName);
        //Collections.reverse(Arrays.asList(chord));
        int [] chordF = new int[5];
        for(int i=0;i<2;i++){chordF[i] = chord[i+3];}
        try{
            if(isRunning != true) return;
            play2(chordF);
            Thread.sleep((int)waitTime);
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
//        try{
            if(isRunning != true) return;
            GuitarSoundManager.playSound(a[0],1.5f);
            //Thread.sleep(semiTimeDifference * 1);

            if(isRunning != true) return;
            GuitarSoundManager.playSound(a[1],1.5f);
            //Thread.sleep(semiTimeDifference * 1);

            if(isRunning != true) return;
            GuitarSoundManager.playSound(a[2],1.5f);
            //Thread.sleep(semiTimeDifference * 1);

            if(isRunning != true) return;
            GuitarSoundManager.playSound(a[3],1.5f);

//        } catch(InterruptedException e) {
//            e.printStackTrace();
//        }

    }

    public void play4m(int[] a){
        //float x = tempoValue/80;
//       try{
        if(isRunning != true) return;
        GuitarSoundManager.playSound(a[0],1.5f);
//        Thread.sleep((int)semiTimeDifference  * 1);

        if(isRunning != true) return;
        GuitarSoundManager.playSound(a[1],1.5f);
//        Thread.sleep((int)semiTimeDifference * 1);

        if(isRunning != true) return;
        GuitarSoundManager.playSound(a[2],1.5f);
//        Thread.sleep((int)semiTimeDifference * 1);

        if(isRunning != true) return;
        GuitarSoundManager.playSound(a[3],1.5f);

//        } catch(InterruptedException e) {
//            e.printStackTrace();
//        }

    }

    public void play2(int[] a){
//        try{
            if(isRunning != true) return;
            GuitarSoundManager.playSound(a[0],1.5f);
            //Thread.sleep(semiTimeDifference * 6);

            if(isRunning != true) return;
            GuitarSoundManager.playSound(a[1],1.5f);

//        } catch(InterruptedException e) {
//            e.printStackTrace();
//        }

    }








}       //--------closed PlayingGuitarStrumming class --------//