package com.example.minigame.repository;

public class GameUser {


    private int totalToken = 10;
    private int round = 1;
    private int thisRoundScore = 0;
    private int totalScore = 0;
    private String status = "EQUAL";

    private boolean [] banList = {false, false ,false};

    private int score1Submit = 1;
    private int score2Submit = 1;
    private int score3Submit = 1;


    public int getTotalToken() {
        return totalToken;
    }

    public void setTotalToken(int totalToken) {
        this.totalToken = totalToken;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
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

    public int getScore1Submit() {
        return score1Submit;
    }

    public void setScore1Submit(int score1Submit) {
        this.score1Submit = score1Submit;
    }

    public int getScore2Submit() {
        return score2Submit;
    }

    public void setScore2Submit(int betLev2) {
        this.score2Submit = betLev2;
    }

    public int getScore3Submit() {
        return score3Submit;
    }

    public void setScore3Submit(int score3Submit) {
        this.score3Submit = score3Submit;
    }

}
