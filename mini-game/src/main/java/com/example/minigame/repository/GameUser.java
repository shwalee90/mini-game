package com.example.minigame.repository;

import java.util.ArrayList;

public class GameUser {


    private int token = 10;
    private int roundNum = 1;
    private int thisRoundScore = 0;
    private int totalScore = 0;
    private String status = "EQUAL";

    private boolean [] banList = new boolean[3];

    private int betLev1 = 0;
    private int betLev2 = 0;
    private int betLev3 = 0;


    public int getToken() {
        return token;
    }

    public void setToken(int token) {
        this.token = token;
    }

    public int getRoundNum() {
        return roundNum;
    }

    public void setRoundNum(int roundNum) {
        this.roundNum = roundNum;
    }

    public int getThisRoundScore() {
        return thisRoundScore;
    }

    public void setThisRoundScore(int thisRoundScore) {
        this.thisRoundScore = thisRoundScore;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean[] getBanList() {
        return banList;
    }

    public void setBanList(boolean[] banList) {
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

}
