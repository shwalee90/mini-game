package com.example.minigame.component;

import com.example.minigame.request.gUser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class result {

    Map<gUser, Integer> userScoreMap = new HashMap<gUser , Integer>();

    public void calLev1 (gUser user1 , gUser user2 ){

        int thisScore = 1;

        int val1 = user1.getBetLev1();
        int val2 = user2.getBetLev1();

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

            return;
        }

        else{
            if(status1.equals("WINNER")){
                if(val1 > val2){
                    if(val1 >= 1){
                        thisScore = thisScore*2;
                    }
                    user1.setThisRoundScore(user1.getThisRoundScore() + thisScore);
                    return;
                }
                if(val1 < val2){
                    thisScore = thisScore *2;
                    if(val2 >= 1){
                        thisScore = thisScore*2;
                    }
                    user2.setThisRoundScore(user2.getThisRoundScore()+thisScore);
                    return;
                }
                if(val1 == val2){
                    if(val2 >=1){
                        thisScore = thisScore*2;
                    }
                    user2.setThisRoundScore(user2.getThisRoundScore()+thisScore);
                    return;
                }
            }

            if(status1.equals("EQUAL")){
                if(val1 > val2){
                    if(val1 >= 1){
                        thisScore = thisScore*2;
                    }
                    user1.setThisRoundScore(user1.getThisRoundScore() + thisScore);
                    return;
                }
                if(val1 < val2){
                    if(val2 >= 1){
                        thisScore = thisScore*2;
                    }
                    user2.setThisRoundScore(user2.getThisRoundScore()+thisScore);
                    return;
                }
            }

            if(status1.equals("LOSER")){
                if(val1 > val2){
                    thisScore = thisScore *2;
                    if(val1 >= 1){
                        thisScore = thisScore*2;
                    }
                    user1.setThisRoundScore(user1.getThisRoundScore() + thisScore);
                    return;
                }
                if(val1 < val2){
                    if(val2 >= 1){
                        thisScore = thisScore*2;
                    }
                    user2.setThisRoundScore(user2.getThisRoundScore()+thisScore);
                    return;
                }
                if(val1 == val2){
                    if(val1 >=1){
                        thisScore = thisScore*2;
                    }
                    user1.setThisRoundScore(user1.getThisRoundScore()+thisScore);
                    return;
                }
            }

        }

    }
    public void calLev2 (gUser user1 , gUser user2 ){

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

            return;
        }

        else{
            if(status1.equals("WINNER")){
                if(val1 > val2+1){
                    if(val1 >= 2){
                        thisScore = thisScore*2;
                    }
                    user1.setThisRoundScore(user1.getThisRoundScore() + thisScore);
                    return;
                }
                if(val1+1 < val2){
                    thisScore = thisScore *2;
                    if(val2 >= 2){
                        thisScore = thisScore*2;
                    }
                    user2.setThisRoundScore(user2.getThisRoundScore()+thisScore);
                    return;
                }
                if(val1 <= val2){
                    if(val2 >= 2){
                        thisScore = thisScore*2;
                    }
                    user2.setThisRoundScore(user2.getThisRoundScore()+thisScore);
                    return;
                }
            }

            if(status1.equals("EQUAL")){
                if(val1 > val2+1){
                    if(val1 >= 2){
                        thisScore = thisScore*2;
                    }
                    user1.setThisRoundScore(user1.getThisRoundScore() + thisScore);
                    return;
                }
                if(val1+1 < val2){
                    if(val2 >= 2){
                        thisScore = thisScore*2;
                    }
                    user2.setThisRoundScore(user2.getThisRoundScore()+ thisScore);
                    return;
                }
            }

            if(status1.equals("LOSER")){
                if(val1 > val2+1){
                    thisScore = thisScore *2;
                    if(val1 >= 2){
                        thisScore = thisScore*2;
                    }
                    user1.setThisRoundScore(user1.getThisRoundScore() + thisScore);
                    return;
                }
                if(val1+1 < val2){
                    if(val2 >= 2){
                        thisScore = thisScore*2;
                    }
                    user2.setThisRoundScore(user2.getThisRoundScore()+thisScore);
                    return;
                }
                if(val1 >= val2){
                    if(val2 >= 2){
                        thisScore = thisScore*2;
                    }
                    user1.setThisRoundScore(user1.getThisRoundScore()+thisScore);
                    return;
                }
            }

        }

    }

    public void calLev3 (gUser user1 , gUser user2 ){

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

            return;
        }

        else{
            if(status1.equals("WINNER")){
                if(val1 > val2+2){
                    if(val1 >= 3){
                        thisScore = thisScore*2;
                    }
                    user1.setThisRoundScore(user1.getThisRoundScore() + thisScore);
                    return;
                }
                if(val1+2 < val2){
                    thisScore = thisScore *2;
                    if(val2 >= 3){
                        thisScore = thisScore*2;
                    }
                    user2.setThisRoundScore(user2.getThisRoundScore()+thisScore);
                    return;
                }
                if(val1 <= val2){
                    if(val2 >= 3){
                        thisScore = thisScore*2;
                    }
                    user2.setThisRoundScore(user2.getThisRoundScore()+thisScore);
                    return;
                }
            }

            if(status1.equals("EQUAL")){
                if(val1 > val2+2){
                    if(val1 >= 3){
                        thisScore = thisScore*2;
                    }
                    user1.setThisRoundScore(user1.getThisRoundScore() + thisScore);
                    return;
                }
                if(val1+2 < val2){
                    if(val2 >= 3){
                        thisScore = thisScore*2;
                    }
                    user2.setThisRoundScore(user2.getThisRoundScore()+ thisScore);
                    return;
                }
            }

            if(status1.equals("LOSER")){
                if(val1 > val2+2){
                    thisScore = thisScore *2;
                    if(val1 >= 3){
                        thisScore = thisScore*2;
                    }
                    user1.setThisRoundScore(user1.getThisRoundScore() + thisScore);
                    return;
                }
                if(val1+2 < val2){
                    if(val2 >= 3){
                        thisScore = thisScore*2;
                    }
                    user2.setThisRoundScore(user2.getThisRoundScore()+thisScore);
                    return;
                }
                if(val1 >= val2){
                    if(val2 >= 3){
                        thisScore = thisScore*2;
                    }
                    user1.setThisRoundScore(user1.getThisRoundScore()+thisScore);
                    return;
                }
            }

        }

    }

    public void calLev4 (gUser user1 , gUser user2 ){

        int thisScore = 4;

        int val1 = user1.getBetLev4();
        int val2 = user2.getBetLev4();

        ArrayList<Integer> banList1 = user1.getBanList();
        ArrayList<Integer> banList2 = user2.getBanList();

        String status1 = user1.getStatus();

        if(val1 == 0 || val2 == 0) {

            if (val1 == 0) {
                banList1.add(4);
                user1.setBanList(banList1);
            }

            if (val2 == 0) {
                banList2.add(4);
                user2.setBanList(banList2);
            }

            return;
        }

        else{
            if(status1.equals("WINNER")){
                if(val1 >= (val2*1.5)){
                    if(val1 >= 4){
                        thisScore = thisScore*2;
                    }
                    user1.setThisRoundScore(user1.getThisRoundScore() + thisScore);
                    return;
                }
                if((val1*1.5) <= val2){
                    thisScore = thisScore *2;
                    if(val2 >= 4){
                        thisScore = thisScore*2;
                    }
                    user2.setThisRoundScore(user2.getThisRoundScore()+thisScore);
                    return;
                }
                if(val1 <= val2){
                    if(val2 >= 4){
                        thisScore = thisScore*2;
                    }
                    user2.setThisRoundScore(user2.getThisRoundScore()+thisScore);
                    return;
                }
            }

            if(status1.equals("EQUAL")){
                if(val1 >= (val2*1.5)){
                    if(val1 >= 4){
                        thisScore = thisScore*2;
                    }
                    user1.setThisRoundScore(user1.getThisRoundScore() + thisScore);
                    return;
                }
                if((val1*1.5) <= val2){
                    if(val2 >= 4){
                        thisScore = thisScore*2;
                    }
                    user2.setThisRoundScore(user2.getThisRoundScore()+ thisScore);
                    return;
                }
            }

            if(status1.equals("LOSER")){
                if(val1 >= (val2*1.5)){
                    thisScore = thisScore *2;
                    if(val1 >= 4){
                        thisScore = thisScore*2;
                    }
                    user1.setThisRoundScore(user1.getThisRoundScore() + thisScore);
                    return;
                }
                if(val1*(1.5) <= val2){
                    if(val2 >= 4){
                        thisScore = thisScore*2;
                    }
                    user2.setThisRoundScore(user2.getThisRoundScore()+thisScore);
                    return;
                }
                if(val1 >= val2){
                    if(val2 >= 4){
                        thisScore = thisScore*2;
                    }
                    user1.setThisRoundScore(user1.getThisRoundScore()+thisScore);
                    return;
                }
            }

        }

    }

    public void calLev5 (gUser user1 , gUser user2 ){

        int thisScore = 5;

        int val1 = user1.getBetLev5();
        int val2 = user2.getBetLev5();

        ArrayList<Integer> banList1 = user1.getBanList();
        ArrayList<Integer> banList2 = user2.getBanList();

        String status1 = user1.getStatus();

        if(val1 == 0 || val2 == 0) {

            if (val1 == 0) {
                banList1.add(5);
                user1.setBanList(banList1);
            }

            if (val2 == 0) {
                banList2.add(5);
                user2.setBanList(banList2);
            }

            return;
        }

        else{
            if(status1.equals("WINNER")){
                if(val1 >= (val2*2)){
                    if(val1 >= 5){
                        thisScore = thisScore*2;
                    }
                    user1.setThisRoundScore(user1.getThisRoundScore() + thisScore);
                    return;
                }
                if((val1*2) <= val2){
                    thisScore = thisScore *2;
                    if(val2 >= 5){
                        thisScore = thisScore*2;
                    }
                    user2.setThisRoundScore(user2.getThisRoundScore()+thisScore);
                    return;
                }
                if(val1 <= val2){
                    if(val2 >= 5){
                        thisScore = thisScore*2;
                    }
                    user2.setThisRoundScore(user2.getThisRoundScore()+thisScore);
                    return;
                }
            }

            if(status1.equals("EQUAL")){
                if(val1 >= (val2*2)){
                    if(val1 >= 5){
                        thisScore = thisScore*2;
                    }
                    user1.setThisRoundScore(user1.getThisRoundScore() + thisScore);
                    return;
                }
                if((val1*2) <= val2){
                    if(val2 >= 5){
                        thisScore = thisScore*2;
                    }
                    user2.setThisRoundScore(user2.getThisRoundScore()+ thisScore);
                    return;
                }
            }

            if(status1.equals("LOSER")){
                if(val1 >= (val2*2)){
                    thisScore = thisScore *2;
                    if(val1 >= 5){
                        thisScore = thisScore*2;
                    }
                    user1.setThisRoundScore(user1.getThisRoundScore() + thisScore);
                    return;
                }
                if((val1*2) <= val2){
                    if(val2 >= 5){
                        thisScore = thisScore*2;
                    }
                    user2.setThisRoundScore(user2.getThisRoundScore()+thisScore);
                    return;
                }
                if(val1 >= val2){
                    if(val2 >= 5){
                        thisScore = thisScore*2;
                    }
                    user1.setThisRoundScore(user1.getThisRoundScore()+thisScore);
                    return;
                }
            }

        }

    }

    public void calLev6 (gUser user1 , gUser user2 ){

        int thisScore = 6;

        int val1 = user1.getBetLev6();
        int val2 = user2.getBetLev6();

        String status1 = user1.getStatus();

        if(status1.equals("WINNER")){
            if(val1 >= (val2*3)){
                thisScore = thisScore*3;

                user1.setThisRoundScore(user1.getThisRoundScore() + thisScore);
                return;
            }
            if((val1*3) <= val2){
                thisScore = thisScore *3;

                user2.setThisRoundScore(user2.getThisRoundScore()+thisScore);
                return;
            }
            if(val1 <= val2){
                user1.setThisRoundScore(user1.getThisRoundScore()+thisScore);
                return;
            }
            if(val1 > val2){
                user2.setThisRoundScore(user2.getThisRoundScore()+thisScore);
                return;
            }
        }

        if(status1.equals("EQUAL")){
            if(val1 >= (val2*3)){
                thisScore = thisScore*3;

                user1.setThisRoundScore(user1.getThisRoundScore() + thisScore);
                return;
            }
            if((val1*3) <= val2){

                thisScore = thisScore*3;

                user2.setThisRoundScore(user2.getThisRoundScore()+ thisScore);
                return;
            }
            if(val1 > val2){
                user2.setThisRoundScore(user2.getThisRoundScore()+thisScore);
                return;
            }
            if(val1 < val2){
                user1.setThisRoundScore(user1.getThisRoundScore()+thisScore);
                return;
            }
        }

        if(status1.equals("LOSER")){
            if(val1 >= (val2*3)){
                thisScore = thisScore *3;

                user1.setThisRoundScore(user1.getThisRoundScore() + thisScore);
                return;
            }
            if((val1*3) <= val2){
                thisScore = thisScore*3;
                user2.setThisRoundScore(user2.getThisRoundScore()+thisScore);
                return;
            }
            if(val1 >= val2){

                user2.setThisRoundScore(user2.getThisRoundScore()+thisScore);
                return;
            }
            if(val1 < val2){

                user1.setThisRoundScore(user1.getThisRoundScore()+thisScore);
                return;
            }


        }
        }

    }

