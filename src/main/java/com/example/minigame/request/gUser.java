package com.example.minigame.request;

import java.util.ArrayList;

public class gUser {


    int thisRoundScore = 0;

    String status = "EQUAL";

    ArrayList<Integer> banList = new ArrayList<>();

    int betLev1 = 0;
    int betLev2 = 0;
    int betLev3 = 0;
    int betLev4 = 0;
    int betLev5 = 0;
    int betLev6 = 0;

    public int getThisRoundScore() {
        return thisRoundScore;
    }

    public void setThisRoundScore(int thisRoundScore) {
        this.thisRoundScore = thisRoundScore;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<Integer> getBanList() {
        return banList;
    }

    public void setBanList(ArrayList<Integer> banList) {
        this.banList = banList;
    }

    public int getBetLev1() {
        return betLev1;
    }

    public void setBetLev1(int betLev1) {
        this.betLev1 = betLev1;
    }

    public int getBetLev2() {
        return betLev2;
    }

    public void setBetLev2(int betLev2) {
        this.betLev2 = betLev2;
    }

    public int getBetLev3() {
        return betLev3;
    }

    public void setBetLev3(int betLev3) {
        this.betLev3 = betLev3;
    }

    public int getBetLev4() {
        return betLev4;
    }

    public void setBetLev4(int betLev4) {
        this.betLev4 = betLev4;
    }

    public int getBetLev5() {
        return betLev5;
    }

    public void setBetLev5(int betLev5) {
        this.betLev5 = betLev5;
    }

    public int getBetLev6() {
        return betLev6;
    }

    public void setBetLev6(int betLev6) {
        this.betLev6 = betLev6;
    }
}
