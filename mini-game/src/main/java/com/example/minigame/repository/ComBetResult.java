package com.example.minigame.repository;

import java.util.ArrayList;

public class ComBetResult {
    private ArrayList<Integer> pick;
    private boolean[] banArr;

    public ComBetResult(ArrayList<Integer> pick, boolean[] banArr){
        this.pick = pick;
        this.banArr = banArr;
    }

    public ArrayList<Integer> getPick() {
        return pick;
    }

    public void setPick(ArrayList<Integer> pick) {
        this.pick = pick;
    }

    public boolean[] getBanArr() {
        return banArr;
    }

    public void setBanArr(boolean[] banArr) {
        this.banArr = banArr;
    }
}
