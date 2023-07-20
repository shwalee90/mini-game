package com.example.minigame.service;

import com.example.minigame.request.GameUser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class ResultCalculService {

    Map<GameUser, Integer> userScoreMap = new HashMap<GameUser, Integer>();

    LinkedList<GameUser> guList = new LinkedList<>();

    public LinkedList<GameUser> calLev1 (LinkedList<GameUser> userList ){
        guList = new LinkedList<>();
        int thisScore = 1;
        GameUser user1 = userList.get(0);
        GameUser user2 = userList.get(1);

        int val1 = userList.get(0).getBetLev1();
        int val2 = userList.get(1).getBetLev1();

        ArrayList<Integer> banList1 = user1.getBanList();
        ArrayList<Integer> banList2 = user2.getBanList();

        String status1 = user1.getStatus();
        String status2 = user2.getStatus();

        if(val1 == 0 || val2 == 0) {
            if (val1 == 0) {
                banList1.add(1);
                user1.setBanList(banList1);
            }

            if (val2 == 0) {
                banList2.add(1);
                user2.setBanList(banList2);
            }
        }

        else{
            if(status1.equals("WINNER")){
                if(val1 > val2){
                    if(val1 > val2 +1 ){
                        thisScore = thisScore*2;
                    }
                    user1.setThisRoundScore(user1.getThisRoundScore() + thisScore);

                    guList.add(user1);
                    guList.add(user2);
                    return guList;
                }

                if(val1 < val2){
                    thisScore = thisScore *2;
                    if(val2 > val1 + 1){
                        thisScore = thisScore*2;
                    }
                    user2.setThisRoundScore(user2.getThisRoundScore()+thisScore);
                    guList.add(user1);
                    guList.add(user2);
                    return guList;
                }
                if(val1 == val2){
                    user2.setThisRoundScore(user2.getThisRoundScore()+thisScore);
                    guList.add(user1);
                    guList.add(user2);
                    return guList;
                }
            }

            if(status1.equals("EQUAL")){
                if(val1 > val2){
                    if(val1 > val2 +1 ){
                        thisScore = thisScore*2;
                    }
                    user1.setThisRoundScore(user1.getThisRoundScore() + thisScore);
                    guList.add(user1);
                    guList.add(user2);
                    return guList;
                }
                if(val1 < val2){
                    if(val2 > val1 +1 ){
                        thisScore = thisScore*2;
                    }
                    user2.setThisRoundScore(user2.getThisRoundScore()+thisScore);
                    guList.add(user1);
                    guList.add(user2);
                    return guList;
                }
            }

            if(status1.equals("LOSER")){
                if(val1 > val2){
                    thisScore = thisScore *2;
                    if(val1 > val2 + 1){
                        thisScore = thisScore*2;
                    }
                    user1.setThisRoundScore(user1.getThisRoundScore() + thisScore);
                    guList.add(user1);
                    guList.add(user2);
                    return guList;
                }
                if(val1 < val2){
                    if(val2 > val1 + 1){
                        thisScore = thisScore*2;
                    }
                    user2.setThisRoundScore(user2.getThisRoundScore()+thisScore);
                    guList.add(user1);
                    guList.add(user2);
                    return guList;
                }
                if(val1 == val2){
                    user1.setThisRoundScore(user1.getThisRoundScore()+thisScore);
                    guList.add(user1);
                    guList.add(user2);
                    return guList;
                }
            }

        }

        guList.add(user1);
        guList.add(user2);
        return guList;

    }

    public LinkedList<GameUser> calLev2 (LinkedList<GameUser> userList ){
        guList = new LinkedList<>();
        GameUser user1 = userList.get(0);
        GameUser user2 = userList.get(1);

        int thisScore = 2;

        int val1 = user1.getBetLev2();
        int val2 = user2.getBetLev2();

        ArrayList<Integer> banList1 = user1.getBanList();
        ArrayList<Integer> banList2 = user2.getBanList();

        String status1 = user1.getStatus();

        if(val1 == 0 || val2 == 0) {

            if (val1 == 0) {
                banList1.add(2);
                user1.setBanList(banList1);
            }

            if (val2 == 0) {
                banList2.add(2);
                user2.setBanList(banList2);
            }

            guList.add(user1);
            guList.add(user2);
            return guList;
        }

        else{
            if(status1.equals("WINNER")){
                if(val1 > val2){
                    if(val1 >= val2*2){
                        thisScore = thisScore*2;
                    }
                    user1.setThisRoundScore(user1.getThisRoundScore() + thisScore);
                    guList.add(user1);
                    guList.add(user2);
                    return guList;
                }
                if(val1 < val2){
                    if(val2 >= val1*2 ){
                        thisScore = thisScore*2;
                    }
                    user2.setThisRoundScore(user2.getThisRoundScore()+thisScore);
                    guList.add(user1);
                    guList.add(user2);
                    return guList;
                }
                if(val1 == val2){
                    user2.setThisRoundScore(user2.getThisRoundScore()+thisScore);
                    guList.add(user1);
                    guList.add(user2);
                    return guList;
                }
            }

            if(status1.equals("EQUAL")){
                if(val1 > val2){
                    if(val1 >= val2*2){
                        thisScore = thisScore*2;
                    }
                    user1.setThisRoundScore(user1.getThisRoundScore() + thisScore);
                    guList.add(user1);
                    guList.add(user2);
                    return guList;
                }
                if(val1 < val2){
                    if(val2 >= val1*2){
                        thisScore = thisScore*2;
                    }
                    user2.setThisRoundScore(user2.getThisRoundScore()+ thisScore);
                    guList.add(user1);
                    guList.add(user2);
                    return guList;
                }
            }

            if(status1.equals("LOSER")){
                if(val1 > val2){
                    if(val1 >= val2*2 ){
                        thisScore = thisScore*2;
                    }
                    user1.setThisRoundScore(user1.getThisRoundScore() + thisScore);
                    guList.add(user1);
                    guList.add(user2);
                    return guList;
                }
                if(val1 < val2){
                    if(val2 >= val1*2){
                        thisScore = thisScore*2;
                    }
                    user2.setThisRoundScore(user2.getThisRoundScore()+thisScore);
                    guList.add(user1);
                    guList.add(user2);
                    return guList;
                }
                if(val1 == val2){
                    user1.setThisRoundScore(user1.getThisRoundScore()+thisScore);
                    guList.add(user1);
                    guList.add(user2);
                    return guList;
                }
            }
        }

        guList.add(user1);
        guList.add(user2);
        return guList;

    }

    public LinkedList<GameUser> calLev3 (LinkedList<GameUser> userList ){
        guList = new LinkedList<>();
        GameUser user1 = userList.get(0);
        GameUser user2 = userList.get(1);
        int thisScore = 3;

        int val1 = user1.getBetLev3();
        int val2 = user2.getBetLev3();

        ArrayList<Integer> banList1 = user1.getBanList();
        ArrayList<Integer> banList2 = user2.getBanList();

        String status1 = user1.getStatus();

        if(val1 == 0 || val2 == 0) {

            if (val1 == 0) {
                banList1.add(3);
                user1.setBanList(banList1);
            }

            if (val2 == 0) {
                banList2.add(3);
                user2.setBanList(banList2);
            }

            guList.add(user1);
            guList.add(user2);
            return guList;
        }

        else{
            if(status1.equals("WINNER")){
                if(val1 > val2){
                    if(val1 >= val2*3){
                        thisScore = thisScore*2;
                        user1.setThisRoundScore(user1.getThisRoundScore() + thisScore);
                    }
                    else {
                        user2.setThisRoundScore(user2.getThisRoundScore() + thisScore);
                    }
                    guList.add(user1);
                    guList.add(user2);
                    return guList;
                }
                if(val1 < val2){
                    if(val2 >= val1*3){
                        thisScore = thisScore*2;
                        user2.setThisRoundScore(user2.getThisRoundScore()+thisScore);
                    }
                    else {
                        user1.setThisRoundScore(user1.getThisRoundScore()+thisScore);
                    }
                 
                    guList.add(user1);
                    guList.add(user2);
                    return guList;
                }
                if(val1 == val2){
                    user2.setThisRoundScore(user2.getThisRoundScore()+thisScore);
                    guList.add(user1);
                    guList.add(user2);
                    return guList;
                }
            }

            if(status1.equals("EQUAL")){
                if(val1 > val2){
                    if(val1 >= val2*3){
                        thisScore = thisScore*2;
                        user1.setThisRoundScore(user1.getThisRoundScore() + thisScore);
                    }
                    else {
                        user2.setThisRoundScore(user2.getThisRoundScore() + thisScore);
                    }
                    guList.add(user1);
                    guList.add(user2);
                    return guList;
                }
                if(val1 < val2){
                    if(val2 >= val1*3){
                        thisScore = thisScore*2;
                        user2.setThisRoundScore(user2.getThisRoundScore()+thisScore);
                    }
                    else {
                        user1.setThisRoundScore(user1.getThisRoundScore()+thisScore);
                    }
                 
                    guList.add(user1);
                    guList.add(user2);
                    return guList;
                }
            }

            if(status1.equals("LOSER")){
                if(val1 > val2){
                    if(val1 >= val2*3){
                        thisScore = thisScore*2;
                        user1.setThisRoundScore(user1.getThisRoundScore() + thisScore);
                    }
                    else {
                        user2.setThisRoundScore(user2.getThisRoundScore() + thisScore);
                    }
                    guList.add(user1);
                    guList.add(user2);
                    return guList;
                }
                if(val1 < val2){
                    if(val2 >= val1*3){
                        thisScore = thisScore*2;
                        user2.setThisRoundScore(user2.getThisRoundScore()+thisScore);
                    }
                    else {
                        user1.setThisRoundScore(user1.getThisRoundScore()+thisScore);
                    }
                 
                    guList.add(user1);
                    guList.add(user2);
                    return guList;
                }
                if(val1 == val2){
                    user1.setThisRoundScore(user1.getThisRoundScore()+thisScore);
                    guList.add(user1);
                    guList.add(user2);
                    return guList;
                }
            }

        }
        guList.add(user1);
        guList.add(user2);
        return guList;
    }

  

   

    }

