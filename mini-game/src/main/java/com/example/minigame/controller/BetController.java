package com.example.minigame.controller;

import com.example.minigame.service.AfterCalService;
import com.example.minigame.service.ComBetService;
import com.example.minigame.service.ResultCalculService;
import com.example.minigame.repository.GameUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.LinkedList;


@CrossOrigin(origins = "http://localhost:3000/", maxAge = 3600)
@RestController
public class BetController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private final ResultCalculService resultCalculService;

    @Autowired
    private final AfterCalService afterCalService;

    @Autowired
    private final ComBetService combetService;

    public BetController(ResultCalculService resultCalculService, AfterCalService afterCalService ,ComBetService combetService) {
        this.resultCalculService = resultCalculService;
        this.afterCalService = afterCalService;
        this.combetService  = combetService;
    }

    @PostMapping("/betting")
    public ResponseEntity<LinkedList<GameUser>> betting(@RequestBody LinkedList<GameUser> gameUserList)
    {

        log.info("!!!!!!!");
        int roundNum = gameUserList.get(0).getRound();

        gameUserList = resultCalculService.calLev1(gameUserList);

        gameUserList = resultCalculService.calLev2(gameUserList);

        gameUserList = resultCalculService.calLev3(gameUserList);

        gameUserList = afterCalService.adjustProp(gameUserList);


        return new ResponseEntity<LinkedList<GameUser>>(gameUserList , HttpStatus.OK) ;
    }

    @PostMapping("/betting/tutorial")
    public ResponseEntity<GameUser> bettingComputer(@RequestBody GameUser gameUser)
    {

        GameUser comUser = new GameUser();


        int roundNum = gameUser.getRound();
        int comToken = 20-gameUser.getTotalToken();
        comUser.setTotalToken(comToken);

        log.info(gameUser.getScore1Submit()+ " , " + gameUser.getScore2Submit()+ "," +gameUser.getScore3Submit());

        ArrayList<Integer> comSubmit = combetService.processComBet(comToken , roundNum);
        log.info(String.valueOf(comSubmit));

        comUser.setScore1Submit(comSubmit.get(0));
        comUser.setScore2Submit(comSubmit.get(1));
        comUser.setScore3Submit(comSubmit.get(2));

        LinkedList<GameUser> gameUserList = new LinkedList<>();
        gameUserList.add(gameUser);
        gameUserList.add(comUser);
        gameUserList = resultCalculService.calLev1(gameUserList);


        gameUserList = resultCalculService.calLev2(gameUserList);

        gameUserList = resultCalculService.calLev3(gameUserList);

        gameUserList = afterCalService.adjustProp(gameUserList);

        return new ResponseEntity<GameUser>( gameUserList.get(0) , HttpStatus.OK) ;
    }




}
