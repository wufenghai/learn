package com.example.lab04.validation.controller;

import com.example.lab04.validation.dto.UserAddDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

/**
 * @author wfh
 * @create 2023/6/2 16:55
 */
@RestController
@RequestMapping("/users")
@Validated//在类上，添加 @Validated 注解，表示 UserController 是所有接口都需要进行参数校验。
@Valid
public class UserController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping("/get")
    public void get(@RequestParam("id") @Min(value = 1L, message = "编号必须大于 0") Integer id) {
        logger.info("[get][id: {}]", id);
    }

    @PostMapping("/add")
    public void add(@Valid UserAddDTO addDTO) {
        logger.info("[add][addDTO: {}]", addDTO);
    }

}
