package com.example.pdspe.virtualguitarist;

import android.content.Context;
import android.content.ContextWrapper;
import android.app.Application;
import android.media.AudioManager;
import android.media.SoundPool;

import java.util.HashMap;

public class GuitarSoundManager {

    static private GuitarSoundManager _instance;
    private static SoundPool mSoundPool;
    private static HashMap<Integer, Integer> mSoundPoolMap;
    private static AudioManager mAudioManager;
    private static Context mContext;

    private GuitarSoundManager()
    {
    }

    /**
     * Requests the instance of the Sound Manager and creates it
     * if it does not exist.
     *
     * @return Returns the single instance of the SoundManager
     */
    static synchronized public GuitarSoundManager getInstance()
    {
        if (_instance == null)
            _instance = new GuitarSoundManager();
        return _instance;
    }

    /**
     * Initialises the storage for the sounds
     *
     * @param theContext The Application context
     */
    public static  void initSounds(Context theContext)
    {
        mContext = theContext;
        mSoundPool = new SoundPool(4, AudioManager.STREAM_MUSIC, 0);
        mSoundPoolMap = new HashMap<Integer, Integer>();
        mAudioManager = (AudioManager)mContext.getSystemService(Context.AUDIO_SERVICE);
    }

    /**
     * Add a new Sound to the SoundPool
     *
     * @param Index - The Sound Index for Retrieval
     * @param SoundID - The Android ID for the Sound asset.
     */
    public static void addSound(int Index,int SoundID)
    {
        mSoundPoolMap.put(Index, mSoundPool.load(mContext, SoundID, 1));
    }

    /**
     * Loads the various sound assets
     * Currently hardcoded but could easily be changed to be flexible.
     */
    public static void loadSounds()
    {
        mSoundPoolMap.put(1, mSoundPool.load(mContext, R.raw.e1, 1));
        mSoundPoolMap.put(2, mSoundPool.load(mContext, R.raw.f1, 1));
        mSoundPoolMap.put(3, mSoundPool.load(mContext, R.raw.f_1, 1));
        mSoundPoolMap.put(4, mSoundPool.load(mContext, R.raw.g1, 1));
        mSoundPoolMap.put(5, mSoundPool.load(mContext, R.raw.g_1, 1));
        mSoundPoolMap.put(6, mSoundPool.load(mContext, R.raw.a1, 1));
        mSoundPoolMap.put(7, mSoundPool.load(mContext, R.raw.a_1, 1));
        mSoundPoolMap.put(8, mSoundPool.load(mContext, R.raw.b1, 1));
        mSoundPoolMap.put(9, mSoundPool.load(mContext, R.raw.c2, 1));
        mSoundPoolMap.put(10, mSoundPool.load(mContext, R.raw.c_2, 1));
        mSoundPoolMap.put(11, mSoundPool.load(mContext, R.raw.d2, 1));
        mSoundPoolMap.put(12, mSoundPool.load(mContext, R.raw.d_2, 1));

        mSoundPoolMap.put(13, mSoundPool.load(mContext, R.raw.e2, 1));
        mSoundPoolMap.put(14, mSoundPool.load(mContext, R.raw.f2, 1));
        mSoundPoolMap.put(15, mSoundPool.load(mContext, R.raw.f_2, 1));
        mSoundPoolMap.put(16, mSoundPool.load(mContext, R.raw.g2, 1));
        mSoundPoolMap.put(17, mSoundPool.load(mContext, R.raw.g_2, 1));
        mSoundPoolMap.put(18, mSoundPool.load(mContext, R.raw.a2, 1));
        mSoundPoolMap.put(19, mSoundPool.load(mContext, R.raw.a_2, 1));
        mSoundPoolMap.put(20, mSoundPool.load(mContext, R.raw.b2, 1));
        mSoundPoolMap.put(21, mSoundPool.load(mContext, R.raw.c3, 1));
        mSoundPoolMap.put(22, mSoundPool.load(mContext, R.raw.c_3, 1));
        mSoundPoolMap.put(23, mSoundPool.load(mContext, R.raw.d3, 1));
        mSoundPoolMap.put(24, mSoundPool.load(mContext, R.raw.d_3, 1));


        mSoundPoolMap.put(25, mSoundPool.load(mContext, R.raw.e1m, 1));
        mSoundPoolMap.put(26, mSoundPool.load(mContext, R.raw.f1m, 1));
        mSoundPoolMap.put(27, mSoundPool.load(mContext, R.raw.f_1m, 1));
        mSoundPoolMap.put(28, mSoundPool.load(mContext, R.raw.g1m, 1));
        mSoundPoolMap.put(29, mSoundPool.load(mContext, R.raw.g_1m, 1));
        mSoundPoolMap.put(30, mSoundPool.load(mContext, R.raw.a1m, 1));
        mSoundPoolMap.put(31, mSoundPool.load(mContext, R.raw.a_1m, 1));
        mSoundPoolMap.put(32, mSoundPool.load(mContext, R.raw.b1m, 1));
        mSoundPoolMap.put(33, mSoundPool.load(mContext, R.raw.c2m, 1));
        mSoundPoolMap.put(34, mSoundPool.load(mContext, R.raw.c_2m, 1));
        mSoundPoolMap.put(35, mSoundPool.load(mContext, R.raw.d2m, 1));
        mSoundPoolMap.put(36, mSoundPool.load(mContext, R.raw.d_2m, 1));

        mSoundPoolMap.put(37, mSoundPool.load(mContext, R.raw.e2m, 1));
        mSoundPoolMap.put(38, mSoundPool.load(mContext, R.raw.f2m, 1));
        mSoundPoolMap.put(39, mSoundPool.load(mContext, R.raw.f_2m, 1));
        mSoundPoolMap.put(40, mSoundPool.load(mContext, R.raw.g2m, 1));
        mSoundPoolMap.put(41, mSoundPool.load(mContext, R.raw.g_2m, 1));
        mSoundPoolMap.put(42, mSoundPool.load(mContext, R.raw.a2m, 1));
        mSoundPoolMap.put(43, mSoundPool.load(mContext, R.raw.a_2m, 1));
        mSoundPoolMap.put(44, mSoundPool.load(mContext, R.raw.b2m, 1));
        mSoundPoolMap.put(45, mSoundPool.load(mContext, R.raw.c3m, 1));
        mSoundPoolMap.put(46, mSoundPool.load(mContext, R.raw.c_3m, 1));
        mSoundPoolMap.put(47, mSoundPool.load(mContext, R.raw.d3m, 1));
        mSoundPoolMap.put(48, mSoundPool.load(mContext, R.raw.d_3m, 1));
    }

    /**
     * Plays a Sound
     *
     * @param index - The Index of the Sound to be played
     * @param speed - The Speed to play not, not currently used but included for compatibility
     */
    public static void playSound(int index,float speed)
    {
        float streamVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        streamVolume = streamVolume / mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        mSoundPool.play(mSoundPoolMap.get(index), streamVolume, streamVolume, 1, 0, speed);
    }

    /**
     * Stop a Sound
     * @param index - index of the sound to be stopped
     */
    public static void stopSound(int index)
    {
        mSoundPool.stop(mSoundPoolMap.get(index));
    }

    /**
     * Deallocates the resources and Instance of SoundManager
     */
    public static void cleanup()
    {
        mSoundPool.release();
        mSoundPool = null;
        mSoundPoolMap.clear();
        mAudioManager.unloadSoundEffects();
        _instance = null;

    }

}