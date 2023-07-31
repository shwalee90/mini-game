package com.example.minigame.service;

import com.example.minigame.service.ComBetService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class ServiceTest {

    @Autowired
    private MockMvc mockMvc ;
    @Autowired
    private ComBetService comBetService;
    @Autowired
    private ResultCalculService resultCalculService;


    @DisplayName("computer betting 테스트")
    @Test
    public void testCombet() throws Exception {

        //when(테스트실행)
        int token = 10;
        ArrayList<Integer> resultAction =  comBetService.processComBet(token);

        System.out.println(resultAction);

        assertThat(resultAction.size()).isEqualTo(3);


    }


}
