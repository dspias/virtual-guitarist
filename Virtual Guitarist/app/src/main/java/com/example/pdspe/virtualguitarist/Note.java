package com.example.pdspe.virtualguitarist;

public class Note {

    int[] OneByOne ,twoByTwo,threeByThree,ThreeByFour,fourByFour,sixByFour;

    public Note(){
        OneByOne = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
        twoByTwo = new int[]{1,2,4};
        threeByThree = new int[]{1,3};
        ThreeByFour = new int[]{1,2,4};
        fourByFour = new int[]{1,2,4,8};
        sixByFour = new int[]{1,2,4,8};
    }

    public int position(String a,int targetCod){
        int positoin=0;
        if(a.equalsIgnoreCase("1 / 1")){  positoin = poss(OneByOne,targetCod);  }
        if(a.equalsIgnoreCase("2 / 2")){  positoin = poss(twoByTwo,targetCod);  }
        if(a.equalsIgnoreCase("3 / 3")){  positoin = poss(threeByThree,targetCod);  }
        if(a.equalsIgnoreCase("3 / 4")){  positoin = poss(ThreeByFour,targetCod);  }
        if(a.equalsIgnoreCase("4 / 4")){  positoin = poss(fourByFour,targetCod);  }
        if(a.equalsIgnoreCase("6 / 4")){  positoin = poss(sixByFour,targetCod);  }

        return positoin;
    }

    public int poss(int[] targetchod,int checkpos){
        int pos =0;
        for(int i=0;i<targetchod.length;i++){
            if(checkpos == targetchod[i]){ pos = i;break;}
        }
        return pos;
    }

}
