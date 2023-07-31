package com.example.minigame.service;

import com.example.minigame.repository.GameUser;
import org.springframework.stereotype.Service;

import java.util.LinkedList;

@Service
public class AfterCalService {


    public LinkedList<GameUser> adjustProp(LinkedList<GameUser> userList ) {
        GameUser user1 = userList.get(0);
        GameUser user2 = userList.get(1);


        int val1 = user1.getThisRoundScore();
        int val2 = user2.getThisRoundScore();

        user1.setThisRoundScore(0);
        user2.setThisRoundScore(0);

        user1.setTotalScore(user1.getTotalScore()+val1);
        user2.setTotalScore(user2.getTotalScore()+val2);

        if(val1>val2){
            user1.setToken(user1.getToken()+1);
            user2.setToken(user2.getToken()-1);


            if(user1.getToken()>user2.getToken()){
                user1.setStatus("WINNER");
                user2.setStatus("LOSER");
            }else if(user2.getToken() > user1.getToken()){
                user1.setStatus("LOSER");
                user2.setStatus("WINNER");
            }else{
                user1.setStatus("EQUAL");
                user2.setStatus("EQUAL");
            }





        } else if (val2>val1) {
            user1.setToken(user1.getToken()-1);
            user2.setToken(user2.getToken()+1);

            if(user1.getToken()>user2.getToken()){
                user1.setStatus("WINNER");
                user2.setStatus("LOSER");
            }else if(user2.getToken() > user1.getToken()){
                user1.setStatus("LOSER");
                user2.setStatus("WINNER");
            }else{
                user1.setStatus("EQUAL");
                user2.setStatus("EQUAL");
            }


        }else{
            if(user1.getToken()>user2.getToken()){
                user1.setStatus("WINNER");
                user2.setStatus("LOSER");
            }else if(user2.getToken() > user1.getToken()){
                user1.setStatus("LOSER");
                user2.setStatus("WINNER");
            }else{
                user1.setStatus("EQUAL");
                user2.setStatus("EQUAL");
            }

        }

        user1.setRoundNum(user1.getRoundNum()+1);
        user2.setRoundNum(user2.getRoundNum()+1);

        userList.set(0 , user1);
        userList.set(1 , user2);

        return userList;
    }





}
