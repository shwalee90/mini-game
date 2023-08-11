package com.example.minigame.service;

import com.example.minigame.repository.GameUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

@Service
public class ResultCalculService {
    private final Logger log = LoggerFactory.getLogger(getClass());

    Map<GameUser, Integer> userScoreMap = new HashMap<GameUser, Integer>();

    LinkedList<GameUser> guList = new LinkedList<>();

    public LinkedList<GameUser> calLev1 (LinkedList<GameUser> userList ){
        guList = new LinkedList<>();
        int thisScore = 1;
        GameUser user1 = userList.get(0);
        GameUser user2 = userList.get(1);

        int val1 = userList.get(0).getScore1Submit();
        int val2 = userList.get(1).getScore1Submit();

        String[] rstMsg1 = user1.getResultMsg();
        String[] rstMsg2 = user2.getResultMsg();

        String status1 = user1.getStatus();

        if(val1 == 0 || val2 == 0) {

            rstMsg1[0] =  "+0   0 배팅으로 무효";
            rstMsg2[0] =  "+0   0 배팅으로 무효";

            if(status1.equals("WINNER")){
                if(val1 == 0 && val2 == 0){
                    user2.setThisRoundScore(user2.getThisRoundScore() + thisScore);
                    rstMsg1[0] =  "+0 "   + "  둘다 0을 냈으나 LOSER 어드벤티지로  상대방 득점";
                    rstMsg2[0] =  "+" + thisScore + "  둘다 0을 냈으나 LOSER 어드벤티지로 득점";

                    user2.setResultMsg(rstMsg2);
                    user1.setResultMsg(rstMsg1);
                }
            }
            else if (status1.equals("LOSER")){
                if(val1 == 0 && val2 == 0){
                    user1.setThisRoundScore(user1.getThisRoundScore() + thisScore);

                    rstMsg1[0] =  "+" + thisScore + "  둘다 0을 냈으나 LOSER 어드벤티지로 득점";
                    rstMsg2[0] =  "+0 "   + "  둘다 0을 냈으나 LOSER 어드벤티지로 상대방 득점";

                    user1.setResultMsg(rstMsg1);
                    user2.setResultMsg(rstMsg2);
                }
            }
            guList.add(user1);
            guList.add(user2);
            return guList;
        }

        else{
            if(status1.equals("WINNER")){
                if(val1 > val2){

                    rstMsg1[0] =  "+ 1 "   + "(SCORE1 승리 조건) 상대보다 클 때 득점 " ;
                    rstMsg2[0] =  "+ 0 "   ;

                    if(val1 > val2 + 1 ){
                        thisScore = thisScore*2;
                        rstMsg1[0] =  "+ 1  X 2 " + "  (SCORE1 x2 조건) 상대보다 2 이상 클 때 두배 점수 " ;
                        rstMsg2[0] =  "+ 0 "   ;
                    }
                    user1.setThisRoundScore(user1.getThisRoundScore() + thisScore);

                    user1.setResultMsg(rstMsg1);
                    user2.setResultMsg(rstMsg2);

                    guList.add(user1);
                    guList.add(user2);
                    return guList;
                }

                if(val1 < val2){

                    rstMsg1[0] =  "+ 0 "  ;
                    rstMsg2[0] =  "+ 1 "   + "(SCORE1 승리 조건) 상대보다 클 때 득점 " ;

                    if(val2 > val1 + 1){
                        thisScore = thisScore*2;
                        rstMsg1[0] =  "+ 0 "   ;
                        rstMsg2[0] =  "+ 1  X 2 " + "  (SCORE1 x2 조건) 상대보다 2 이상 클 때 두배 점수 " ;

                    }
                    user2.setThisRoundScore(user2.getThisRoundScore()+thisScore);

                    user1.setResultMsg(rstMsg1);
                    user2.setResultMsg(rstMsg2);

                    guList.add(user1);
                    guList.add(user2);
                    return guList;
                }
                if(val1 == val2){
                    user2.setThisRoundScore(user2.getThisRoundScore()+thisScore);
                    guList.add(user1);
                    guList.add(user2);

                    rstMsg1[0] =  "+0 "   + "  같은 숫자 냈으나 LOSER 어드벤티지로 상대방 득점";
                    rstMsg2[0] =  "+" + thisScore + "  같은 숫자 냈으나 LOSER 어드벤티지로 득점";

                    user2.setResultMsg(rstMsg2);
                    user1.setResultMsg(rstMsg1);

                    return guList;
                }
            }

            if(status1.equals("EQUAL")){
                if(val1 > val2){
                    rstMsg1[0] =  "+ 1 "  + "(SCORE1 승리 조건) 상대보다 클 때 득점 " ;
                    rstMsg2[0] =  "+ 0 "  ;
                    if(val1 > val2 +1 ){
                        thisScore = thisScore*2;
                        rstMsg1[0] = "+ 1  X 2 " + "  (SCORE1 x2 조건) 상대보다 2 이상 클 때 두배 점수 " ;
                        rstMsg2[0] = "+ 0 "   ;
                    }
                    user1.setThisRoundScore(user1.getThisRoundScore() + thisScore);
                    user2.setResultMsg(rstMsg2);
                    user1.setResultMsg(rstMsg1);


                    guList.add(user1);
                    guList.add(user2);
                    return guList;
                }
                if(val1 < val2){
                    rstMsg1[0] =  "+ 0 "  ;
                    rstMsg2[0] =  "+ 1 "  + "(SCORE1 승리 조건) 상대보다 클 때 득점 " ;
                    if(val2 > val1 +1 ){
                        thisScore = thisScore*2;
                        rstMsg1[0] = "+ 0 "   ;
                        rstMsg2[0] = "+ 1  X 2 " + "  (SCORE1 x2 조건) 상대보다 2 이상 클 때 두배 점수 " ;
                    }
                    user2.setThisRoundScore(user2.getThisRoundScore()+thisScore);
                    user1.setResultMsg(rstMsg1);
                    user2.setResultMsg(rstMsg2);


                    guList.add(user1);
                    guList.add(user2);
                    return guList;
                }
                if(val1== val2){
                    rstMsg1[0] =  "+ 0 (EQUAL 상태) 같은 숫자 배팅"  ;
                    rstMsg2[0] =  "+ 0 (EQUAL 상태) 같은 숫자 배팅"  ;
                    user1.setResultMsg(rstMsg1);
                    user2.setResultMsg(rstMsg2);

                    guList.add(user1);
                    guList.add(user2);
                    return guList;
                }
            }

            if(status1.equals("LOSER")){
                if(val1 > val2){

                    rstMsg1[0] =  "+ 1 "  + "(SCORE1 승리 조건) 상대보다 클 때 득점 " ;
                    rstMsg2[0] =  "+ 0 "  ;

                    if(val1 > val2 + 1){
                        thisScore = thisScore*2;
                        rstMsg1[0] = "+ 1  X 2 " + "  (SCORE1 x2 조건) 상대보다 2 이상 클 때 두배 점수 " ;
                        rstMsg2[0] = "+ 0 "   ;
                    }
                    user1.setThisRoundScore(user1.getThisRoundScore() + thisScore);
                    user1.setResultMsg(rstMsg1);
                    user2.setResultMsg(rstMsg2);
                    guList.add(user1);
                    guList.add(user2);
                    return guList;
                }
                if(val1 < val2){
                    rstMsg1[0] =  "+ 0 "  ;
                    rstMsg2[0] =  "+ 1 " + "(SCORE1 승리 조건) 상대보다 클 때 득점 "  ;
                    if(val2 > val1 + 1){
                        thisScore = thisScore*2;
                        rstMsg1[0] = "+ 0 "   ;
                        rstMsg2[0] = "+ 1  X 2 " + "  (SCORE1 x2 조건) 상대보다 2 이상 클 때 두배 점수 " ;
                    }
                    user2.setThisRoundScore(user2.getThisRoundScore()+thisScore);
                    user1.setResultMsg(rstMsg1);
                    user2.setResultMsg(rstMsg2);
                    guList.add(user1);
                    guList.add(user2);
                    return guList;
                }
                if(val1 == val2){
                    user1.setThisRoundScore(user1.getThisRoundScore()+thisScore);
                    rstMsg1[0] =   "+" + thisScore + "  같은 숫자 냈으나 LOSER 어드벤티지로 득점";
                    rstMsg2[0] =   "+0 "   + "  같은 숫자 냈으나 LOSER 어드벤티지로 상대방 득점";

                    user1.setResultMsg(rstMsg1);
                    user2.setResultMsg(rstMsg2);
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

        String[] rstMsg1 = user1.getResultMsg();
        String[] rstMsg2 = user2.getResultMsg();

        int val1 = user1.getScore2Submit();
        int val2 = user2.getScore2Submit();

        String status1 = user1.getStatus();

        if(val1 == 0 || val2 == 0) {

            rstMsg1[1] =  "+0   0 배팅으로 무효";
            rstMsg2[1] =  "+0   0 배팅으로 무효";


            if(status1.equals("WINNER")){
                if(val1 == 0 && val2 == 0){
                    rstMsg1[1] =  "+0 "   + "  둘다 0을 냈으나 LOSER 어드벤티지로  상대방 득점";
                    rstMsg2[1] =  "+" + thisScore + "  둘다 0을 냈으나 LOSER 어드벤티지로 득점";
                    user2.setThisRoundScore(user2.getThisRoundScore() + thisScore);
                }
            }
            else if (status1.equals("LOSER")){
                if(val1 == 0 && val2 == 0){
                    rstMsg1[1] =  "+0 "   + "  둘다 0을 냈으나 LOSER 어드벤티지로  상대방 득점";
                    rstMsg2[1] =  "+" + thisScore + "  둘다 0을 냈으나 LOSER 어드벤티지로 득점";
                    user1.setThisRoundScore(user1.getThisRoundScore() + thisScore);
                }
            }


            user1.setResultMsg(rstMsg1);
            user2.setResultMsg(rstMsg2);

            guList.add(user1);
            guList.add(user2);
            return guList;
        }

        else{
            if(status1.equals("WINNER")){
                if(val1 > val2){

                    rstMsg1[1] =  "+2  "+ "(SCORE2 승리 조건) 상대보다 클 때 득점 " ;
                    rstMsg2[1] =  "+0  ";

                    if(val1 >= val2*2){
                        thisScore = thisScore*2;
                        rstMsg1[1] =  "+2 X 2  " + "(SCORE2 x2 조건) 상대보다 2배 이상 클 때 두배 점수 " ;
                        rstMsg2[1] =  "+0  ";
                    }
                    user1.setThisRoundScore(user1.getThisRoundScore() + thisScore);

                    user1.setResultMsg(rstMsg1);
                    user2.setResultMsg(rstMsg2);

                    guList.add(user1);
                    guList.add(user2);
                    return guList;
                }
                if(val1 < val2){

                    rstMsg1[1] =   "+0  ";
                    rstMsg2[1] =   "+2  "+ "(SCORE2 승리 조건) 상대보다 클 때 득점 " ;

                    if(val2 >= val1*2 ){
                        thisScore = thisScore*2;
                        rstMsg1[1] =  "+0  ";
                        rstMsg2[1] =  "+2 X 2  " + "(SCORE2 x2 조건) 상대보다 2배 이상 클 때 두배 점수 " ;
                    }
                    user2.setThisRoundScore(user2.getThisRoundScore()+thisScore);
                    user1.setResultMsg(rstMsg1);
                    user2.setResultMsg(rstMsg2);

                    guList.add(user1);
                    guList.add(user2);
                    return guList;
                }
                if(val1 == val2){
                    rstMsg1[1] =   "+0 "   + "  같은 숫자 냈으나 LOSER 어드벤티지로 상대방 득점";
                    rstMsg2[1] =   "+" + thisScore + "  같은 숫자 냈으나 LOSER 어드벤티지로 득점";
                    user2.setThisRoundScore(user2.getThisRoundScore()+thisScore);
                    user1.setResultMsg(rstMsg1);
                    user2.setResultMsg(rstMsg2);
                    guList.add(user1);
                    guList.add(user2);
                    return guList;
                }
            }

            if(status1.equals("EQUAL")){
                if(val1 > val2){

                    rstMsg1[1] =  "+2  "+ "(SCORE2 승리 조건) 상대보다 클 때 득점 " ;
                    rstMsg2[1] =  "+0  ";

                    if(val1 >= val2*2){
                        rstMsg1[1] =  "+2 X 2  " + "(SCORE2 x2 조건) 상대보다 2배 이상 클 때 두배 점수 " ;
                        rstMsg2[1] =  "+0  ";

                        thisScore = thisScore*2;
                    }
                    user1.setThisRoundScore(user1.getThisRoundScore() + thisScore);
                    user1.setResultMsg(rstMsg1);
                    user2.setResultMsg(rstMsg2);
                    guList.add(user1);
                    guList.add(user2);
                    return guList;
                }
                if(val1 < val2){
                    rstMsg1[1] =   "+0  ";
                    rstMsg2[1] =   "+2  "+ "(SCORE2 승리 조건) 상대보다 클 때 득점 " ;
                    if(val2 >= val1*2){
                        thisScore = thisScore*2;
                        rstMsg1[1] =  "+0  ";
                        rstMsg2[1] =  "+2 X 2  " + "(SCORE2 x2 조건) 상대보다 2배 이상 클 때 두배 점수 " ;
                    }
                    user2.setThisRoundScore(user2.getThisRoundScore()+ thisScore);
                    user1.setResultMsg(rstMsg1);
                    user2.setResultMsg(rstMsg2);
                    guList.add(user1);
                    guList.add(user2);
                    return guList;
                }
                if(val1== val2){
                    rstMsg1[1] =  "+ 0 (EQUAL 상태) 같은 숫자 배팅"  ;
                    rstMsg2[1] =  "+ 0 (EQUAL 상태) 같은 숫자 배팅"  ;
                    user1.setResultMsg(rstMsg1);
                    user2.setResultMsg(rstMsg2);

                    guList.add(user1);
                    guList.add(user2);
                    return guList;
                }
            }

            if(status1.equals("LOSER")){
                if(val1 > val2){
                    rstMsg1[1] =   "+2  "+ "(SCORE2 승리 조건) 상대보다 클 때 득점 " ;
                    rstMsg2[1] =   "+0  ";
                    if(val1 >= val2*2 ){
                        rstMsg1[1] =  "+2 X 2  " + "(SCORE2 x2 조건) 상대보다 2배 이상 클 때 두배 점수 " ;
                        rstMsg2[1] =  "+0  ";
                        thisScore = thisScore*2;
                    }
                    user1.setThisRoundScore(user1.getThisRoundScore() + thisScore);
                    user1.setResultMsg(rstMsg1);
                    user2.setResultMsg(rstMsg2);
                    guList.add(user1);
                    guList.add(user2);
                    return guList;
                }
                if(val1 < val2){
                    rstMsg1[1] =   "+0  ";
                    rstMsg2[1] =   "+2  "+ "(SCORE2 승리 조건) 상대보다 클 때 득점 " ;
                    if(val2 >= val1*2){
                        thisScore = thisScore*2;
                        rstMsg1[1] =  "+0  ";
                        rstMsg2[1] =  "+2 X 2  " + "(SCORE2 x2 조건) 상대보다 2배 이상 클 때 두배 점수 " ;
                    }
                    user2.setThisRoundScore(user2.getThisRoundScore()+thisScore);
                    user1.setResultMsg(rstMsg1);
                    user2.setResultMsg(rstMsg2);
                    guList.add(user1);
                    guList.add(user2);
                    return guList;
                }
                if(val1 == val2){

                    rstMsg1[1] =   "+" + thisScore + "  같은 숫자 냈으나 LOSER 어드벤티지로 득점";
                    rstMsg2[1] =   "+0 "   + "  같은 숫자 냈으나 LOSER 어드벤티지로 상대방 득점";

                    user1.setThisRoundScore(user1.getThisRoundScore()+thisScore);
                    user1.setResultMsg(rstMsg1);
                    user2.setResultMsg(rstMsg2);
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

        int val1 = user1.getScore3Submit();
        int val2 = user2.getScore3Submit();

        String[] rstMsg1 = user1.getResultMsg();
        String[] rstMsg2 = user2.getResultMsg();


        String status1 = user1.getStatus();

        if(val1 == 0 || val2 == 0) {

            rstMsg1[2] =  "+0   0 배팅으로 무효";
            rstMsg2[2] =  "+0   0 배팅으로 무효";


            if(status1.equals("WINNER")){
                if(val1 == 0 && val2 == 0){
                    rstMsg1[2] =  "+0 "   + "  둘다 0을 냈으나 LOSER 어드벤티지로  상대방 득점";
                    rstMsg2[2] =  "+" + thisScore + "  둘다 0을 냈으나 LOSER 어드벤티지로 득점";
                    user2.setThisRoundScore(user2.getThisRoundScore() + thisScore);
                }
            }
            else if (status1.equals("LOSER")){
                if(val1 == 0 && val2 == 0){
                    rstMsg1[2] =  "+" + thisScore + "  둘다 0을 냈으나 LOSER 어드벤티지로 득점";
                    rstMsg2[2] =  "+0 "   + "  둘다 0을 냈으나 LOSER 어드벤티지로  상대방 득점";
                    user1.setThisRoundScore(user1.getThisRoundScore() + thisScore);
                }
            }

            user1.setResultMsg(rstMsg1);
            user2.setResultMsg(rstMsg2);

            guList.add(user1);
            guList.add(user2);
            return guList;
        }


        else{
            if(status1.equals("WINNER")){
                if(val1 > val2){

                    if(val1 >= val2*3){
                        thisScore = thisScore*2;
                        rstMsg1[2] =  "+3 X 2  "+ "(SCORE3 X 2 조건) 상대보다 3배 이상 클 때 " ;
                        rstMsg2[2] =  "+0  ";
                        user1.setThisRoundScore(user1.getThisRoundScore() + thisScore);
                    }
                    else {
                        rstMsg1[2] =  "+0  ";
                        rstMsg2[2] =  "+3  "+ "(SCORE3 승리 조건) 상대보다 작을 때 득점 " ;;
                        user2.setThisRoundScore(user2.getThisRoundScore() + thisScore);
                    }

                    user1.setResultMsg(rstMsg1);
                    user2.setResultMsg(rstMsg2);

                    guList.add(user1);
                    guList.add(user2);
                    return guList;
                }
                if(val1 < val2){
                    if(val2 >= val1*3){
                        thisScore = thisScore*2;
                        rstMsg1[2] =  "+0  ";
                        rstMsg2[2] =  "+3 X 2  "+ "(SCORE3 X 2 조건) 상대보다 3배 이상 클 때 " ;
                        user2.setThisRoundScore(user2.getThisRoundScore()+thisScore);
                    }
                    else {
                        rstMsg1[2] =   "+3  "+ "(SCORE3 승리 조건) 상대보다 작을 때 득점 " ;
                        rstMsg2[2] =   "+0  ";
                        user1.setThisRoundScore(user1.getThisRoundScore()+thisScore);
                    }

                    user1.setResultMsg(rstMsg1);
                    user2.setResultMsg(rstMsg2);
                    guList.add(user1);
                    guList.add(user2);
                    return guList;
                }
                if(val1 == val2){
                    user2.setThisRoundScore(user2.getThisRoundScore()+thisScore);
                    rstMsg1[2] =  "+0 "   + "  같은 숫자 냈으나 LOSER 어드벤티지로 상대방 득점";
                    rstMsg2[2] =  "+" + thisScore + "  같은 숫자 냈으나 LOSER 어드벤티지로 득점";

                    user1.setResultMsg(rstMsg1);
                    user2.setResultMsg(rstMsg2);
                    guList.add(user1);
                    guList.add(user2);
                    return guList;
                }
            }

            if(status1.equals("EQUAL")){
                if(val1 > val2){
                    if(val1 >= val2*3){
                        rstMsg1[2] =  "+3 X 2  "+ "(SCORE3 X 2 조건) 상대보다 3배 이상 클 때 " ;
                        rstMsg2[2] =  "+0  ";
                        thisScore = thisScore*2;
                        user1.setThisRoundScore(user1.getThisRoundScore() + thisScore);
                    }
                    else {
                        rstMsg1[2] =  "+0  ";
                        rstMsg2[2] =  "+3  "+ "(SCORE3 승리 조건) 상대보다 작을 때 득점. 단, 상대숫자의 1/3보다는 커야한다. " ;;
                        user2.setThisRoundScore(user2.getThisRoundScore() + thisScore);
                    }

                    user1.setResultMsg(rstMsg1);
                    user2.setResultMsg(rstMsg2);
                    guList.add(user1);
                    guList.add(user2);
                    return guList;
                }
                if(val1 < val2){
                    if(val2 >= val1*3){
                        rstMsg1[2] =  "+0  ";
                        rstMsg2[2] =  "+3 X 2  "+ "(SCORE3 X 2 조건) 상대보다 3배 이상 클 때 " ;
                        thisScore = thisScore*2;
                        user2.setThisRoundScore(user2.getThisRoundScore()+thisScore);
                    }
                    else {
                        rstMsg1[2] =   "+3  "+ "(SCORE3 승리 조건) 상대보다 작으면 득점.  단, 상대숫자의 1/3보다는 커야한다. " ;
                        rstMsg2[2] =   "+0  ";;
                        user1.setThisRoundScore(user1.getThisRoundScore()+thisScore);
                    }
                 
                    guList.add(user1);
                    guList.add(user2);
                    return guList;
                }
                if(val1== val2){
                    rstMsg1[2] =  "+ 0 (EQUAL 상태) 같은 숫자 배팅"  ;
                    rstMsg2[2] =  "+ 0 (EQUAL 상태) 같은 숫자 배팅"  ;
                    user1.setResultMsg(rstMsg1);
                    user2.setResultMsg(rstMsg2);

                    guList.add(user1);
                    guList.add(user2);
                    return guList;
                }


            }

            if(status1.equals("LOSER")){
                if(val1 > val2){
                    if(val1 >= val2*3){
                        thisScore = thisScore*2;
                        rstMsg1[2] =  "+3 X 2  "+ "(SCORE3 X 2 조건) 상대보다 3배 이상 클 때 ";
                        rstMsg2[2] =  "+0 ";
                        user1.setThisRoundScore(user1.getThisRoundScore() + thisScore);
                    }
                    else {
                        rstMsg1[2] =  "+0  ";
                        rstMsg2[2] =  "+3  "+ "(SCORE3 승리 조건) 상대보다 작을 때 득점. 단, 상대숫자의 1/3보다는 커야한다. " ;;
                        user2.setThisRoundScore(user2.getThisRoundScore() + thisScore);
                    }
                    user1.setResultMsg(rstMsg1);
                    user2.setResultMsg(rstMsg2);
                    guList.add(user1);
                    guList.add(user2);
                    return guList;
                }
                if(val1 < val2){
                    if(val2 >= val1*3){
                        thisScore = thisScore*2;
                        rstMsg1[2] =  "+0  ";
                        rstMsg2[2] =  "+3 X 2  "+ "(SCORE3 X 2 조건) 상대보다 3배 이상 클 때 " ;
                        user2.setThisRoundScore(user2.getThisRoundScore()+thisScore);
                    }
                    else {
                        rstMsg1[2] =   "+3  "+ "(SCORE3 승리 조건) 상대보다 작으면 득점.  단, 상대숫자의 1/3보다는 커야한다. " ;
                        rstMsg2[2] =   "+0  ";;
                        user1.setThisRoundScore(user1.getThisRoundScore()+thisScore);
                    }
                    user1.setResultMsg(rstMsg1);
                    user2.setResultMsg(rstMsg2);
                    guList.add(user1);
                    guList.add(user2);
                    return guList;
                }
                if(val1 == val2){
                    rstMsg1[2] =  "+" + thisScore + "  같은 숫자 냈으나 LOSER 어드벤티지로 득점";
                    rstMsg2[2] =  "+0 "   + "  같은 숫자 냈으나 LOSER 어드벤티지로 상대방 득점";

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

