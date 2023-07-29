package com.example.minigame.controller;

import com.example.minigame.service.AfterCalService;
import com.example.minigame.service.ResultCalculService;
import com.example.minigame.request.GameUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


@RestController
public class BetController {

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

        int roundNum = gameUserList.get(0).getRoundNum();

        gameUserList = resultCalculService.calLev1(gameUserList);

        gameUserList = resultCalculService.calLev2(gameUserList);

        gameUserList = resultCalculService.calLev3(gameUserList);

        gameUserList = afterCalService.adjustProp(gameUserList);


        return new ResponseEntity<LinkedList<GameUser>>(gameUserList , HttpStatus.OK) ;
    }

    @PostMapping("/betting/com")
    public ResponseEntity<LinkedList<GameUser>> betting(@RequestBody LinkedList<GameUser> gameUserList)
    {

        int roundNum = gameUserList.get(0).getRoundNum();
        int comToken = 20-gameUserList.get(0).getToken();
        combetService.processComBet(comToken);

        gameUserList = resultCalculService.calLev1(gameUserList);

        gameUserList = resultCalculService.calLev2(gameUserList);

        gameUserList = resultCalculService.calLev3(gameUserList);

        gameUserList = afterCalService.adjustProp(gameUserList);


        return new ResponseEntity<LinkedList<GameUser>>(gameUserList , HttpStatus.OK) ;
    }




}
