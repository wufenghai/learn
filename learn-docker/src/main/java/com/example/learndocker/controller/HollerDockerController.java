package com.example.learndocker.controller;

import com.example.learndocker.enums.EnumTest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wfh
 * @create 2023/4/14 16:19
 */
@Slf4j
@RestController
@RequestMapping("/docker")
public class HollerDockerController {

    @GetMapping
    public String getWolrd() {
        log.info(EnumTest.OK.getMessage() + " " + EnumTest.OK.getCode());
        return "Holler Docker !";
    }
}
