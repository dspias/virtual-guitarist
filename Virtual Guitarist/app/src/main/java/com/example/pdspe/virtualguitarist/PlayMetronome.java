package com.example.pdspe.virtualguitarist;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
        import android.os.Handler;
        import android.os.HandlerThread;
        import android.os.IBinder;
        import android.os.Looper;
        import android.os.Message;
        import android.os.Process;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import static com.example.pdspe.virtualguitarist.R.color.AliceBlue;
import static com.example.pdspe.virtualguitarist.R.color.dark_red;
import static com.example.pdspe.virtualguitarist.R.color.red;

/**
 * The metronome works like this:
 *
 * |---------------|---------------|---------------|---------------|
 *
 *  \_____________/
 *         |
 *         |
 *     one cycle
 *
 * One cycle is normalized to [0..1]. Since the metronome is swing based, there are *two* beats per cycle.
 *
 */


//----- Class PlayMetronome----//

public class PlayMetronome extends Service  {

    private static final String TAG = "MyService";
    private boolean isRunning  = false;
    private Looper looper;
    private MyServiceHandler myServiceHandler;


    private static int firstSignature, secondSignature, tempo, timeDifference;
    private static LinearLayout beatptn1,beatptn2;


    //------- create setter method ------//

    public static void setFirstSignature(int beats) {
        firstSignature = beats;
    }

    public static void setSecondSignature(int noteValue) {
        secondSignature = noteValue;
    }

    public static void setTempo(int tempoV) {
        tempo = tempoV;
        timeDifference = (60000/tempo);
    }
    public static void setBeatptn1(LinearLayout beat1) {
        beatptn1 = beat1;
    }
    public static void setBeatptn2(LinearLayout beat2) {
        beatptn2 = beat2;
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
//        Toast.makeText(this, "Metronome Started.", Toast.LENGTH_SHORT).show();
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
//        Toast.makeText(this, "Metronome is Stopped.", Toast.LENGTH_SHORT).show();
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
        for(int i=0; i < secondSignature; i++) {
            try {
                if(isRunning != true) return;

                play(1);

                Thread.sleep(timeDifference);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
            for(int j = 0; j < firstSignature - 1; j++) {
                try {
                    if(isRunning != true) return;

                    play(2);

                    Thread.sleep(timeDifference);
                } catch(InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }       /*-------cloase work method-------*/


    /*------- create play method ------*/
    public static void play(int id){
        SoundManager.playSound(id,3.0f);
    }


}       //--------closed PlayMetronome class --------//