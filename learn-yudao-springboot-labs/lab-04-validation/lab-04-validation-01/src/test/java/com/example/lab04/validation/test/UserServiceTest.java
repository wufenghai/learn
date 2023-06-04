package com.example.lab04.validation.test;

import com.example.lab04.validation.Lab04Validation01Application;
import com.example.lab04.validation.dto.UserAddDTO;
import com.example.lab04.validation.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@SpringBootTest(classes = Lab04Validation01Application.class)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void testGet() {
        userService.get(-1);
    }

    @Test
    public void testAdd() {
        UserAddDTO addDTO = new UserAddDTO();
        userService.add(addDTO);
    }

    @Test
    public void testAdd01() {
        UserAddDTO addDTO = new UserAddDTO();
        userService.add01(addDTO);
    }

    @Test
    public void testAdd02() {
        UserAddDTO addDTO = new UserAddDTO();
        userService.add02(addDTO);
    }

    @Autowired // <1.1>
    private Validator validator;

    @Test
    public void testValidator() {
        // 打印，查看 validator 的类型 // <1.2>
        System.out.println(validator);

        // 创建 UserAddDTO 对象 // <2>
        UserAddDTO addDTO = new UserAddDTO();
        // 校验 // <3>
        Set<ConstraintViolation<UserAddDTO>> result = validator.validate(addDTO);
        // 打印校验结果 // <4>
        for (ConstraintViolation<UserAddDTO> constraintViolation : result) {
            // 属性:消息
            System.out.println(constraintViolation.getPropertyPath() + ":" + constraintViolation.getMessage());
        }
    }

}