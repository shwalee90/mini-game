package com.example.minigame.service;

import com.example.minigame.repository.GameUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.LinkedList;

@Service
public class AfterCalService {

    private final Logger log = LoggerFactory.getLogger(getClass());
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
            user1.setTotalToken(user1.getTotalToken()+1);
            user2.setTotalToken(user2.getTotalToken()-1);


            if(user1.getTotalToken()>user2.getTotalToken()){
                user1.setStatus("WINNER");
                user2.setStatus("LOSER");
            }else if(user2.getTotalToken() > user1.getTotalToken()){
                user1.setStatus("LOSER");
                user2.setStatus("WINNER");
            }else{
                user1.setStatus("EQUAL");
                user2.setStatus("EQUAL");
            }

        } else if (val2>val1) {
            log.info("2");
            user1.setTotalToken(user1.getTotalToken()-1);
            user2.setTotalToken(user2.getTotalToken()+1);

            if(user1.getTotalToken()>user2.getTotalToken()){
                user1.setStatus("WINNER");
                user2.setStatus("LOSER");
            }else if(user2.getTotalToken() > user1.getTotalToken()){
                user1.setStatus("LOSER");
                user2.setStatus("WINNER");
            }else{
                user1.setStatus("EQUAL");
                user2.setStatus("EQUAL");
            }


        }else{
            log.info("3");
            if(user1.getTotalToken()>user2.getTotalToken()){
                user1.setStatus("WINNER");
                user2.setStatus("LOSER");
            }else if(user2.getTotalToken() > user1.getTotalToken()){
                user1.setStatus("LOSER");
                user2.setStatus("WINNER");
            }else{
                user1.setStatus("EQUAL");
                user2.setStatus("EQUAL");
            }

        }

        log.info(String.valueOf(user1.getTotalToken()));
        log.info(String.valueOf(user2.getTotalToken()));

        user1.setRound(user1.getRound()+1);
        user2.setRound(user2.getRound()+1);

        userList.set(0 , user1);
        userList.set(1 , user2);

        return userList;
    }





}
