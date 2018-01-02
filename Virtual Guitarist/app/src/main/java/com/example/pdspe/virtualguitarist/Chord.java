package com.example.pdspe.virtualguitarist;

import java.util.Arrays;

/**
 * Created by pdspe on 02-Jan-18.
 */

public class Chord extends Note {

    private String [] chordSerial;

    //-----added Constructor ----//
    public Chord() {
        this.chordSerial = new String[13];
        this.chordSerial[0] = "A";
        this.chordSerial[1] = "A#";
        this.chordSerial[2] = "B";
        this.chordSerial[3] = "C";
        this.chordSerial[4] = "C#";
        this.chordSerial[5] = "D";
        this.chordSerial[6] = "D#";
        this.chordSerial[7] = "E";
        this.chordSerial[8] = "F";
        this.chordSerial[9] = "F#";
        this.chordSerial[10] = "G";
        this.chordSerial[11] = "G#";
    }



    //-------  Major method start------//
    public int[] major(String a) {
        Note note = new Note();
        int position = chordCheck(a);
        int [] statePosition = {
                0+position,
                9+position,
                5+position,
                12+position,
                17+position,
                21+position
        };

        for(int i=0; i<6; i++) { if(statePosition[i] > 23) statePosition[i] -=24; statePosition[i]++; }
        Arrays.sort(statePosition);

        return statePosition;

    }		//----------  Major Method Closed----//







    //------------ Minor Method start ------//
    public int[] minor(String a) {
        Note note = new Note();
        int position = chordCheck(a);

        int [] statePosition = {
                0+position,
                5+position,
                8+position,
                12+position,
                17+position,
                20+position
        };

        for(int i=0; i<6; i++) { if(statePosition[i] > 23) statePosition[i] -=24; statePosition[i]++; }
        Arrays.sort(statePosition);

        return statePosition;

    }		//------  Minor Method Closed-----//







    //------- Sus2 Method Start-------//
    public int[] sus2(String a) {
        Note note = new Note();
        int position = chordCheck(a);

        int [] statePosition = {
                5+position,
                7+position,
                0+position,
                17+position,
                19+position,
                12+position
        };

        for(int i=0; i<6; i++) { if(statePosition[i] > 23) statePosition[i] -=24; statePosition[i]++; }
        Arrays.sort(statePosition);

        return statePosition;

    }		// ---------- Sus2 Method Closed -----//







    // ---------- Sus4 Method Start -----//
    public int[] sus4(String a) {
        Note note = new Note();
        int position = chordCheck(a);

        int [] statePosition = {
                5+position,
                10+position,
                0+position,
                17+position,
                22+position,
                12+position
        };

        for(int i=0; i<6; i++) { if(statePosition[i] > 23) statePosition[i] -=24; statePosition[i]++; }
        Arrays.sort(statePosition);

        return statePosition;

    } 		// ------- Sus4 Method Closed-----//




    //----- Chord Distance Method Start ----//
    public int chordCheck(String a) {
        int position = 0;
        for(int i=0;i<=11;i++) {
            if(a.equals(this.chordSerial[i])) {
                position = i;
                break;
            }
        }
        return position;
    }		// ------- Chord Distance Method Closed ---- //










}