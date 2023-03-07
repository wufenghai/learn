package com.example.spring.data.jdbc;

import com.example.spring.data.jdbc.model.Account;
import com.example.spring.data.jdbc.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.Optional;

@Slf4j
@SpringBootTest
class SpringdataJdbcApplicationTests {

    @Test
    void contextLoads() {
    }


    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void testSave() {
        Account account = new Account(null, "test", "123456");
        account = accountRepository.save(account);
        log.info("save ok , id is {}", account.getId());
        Optional<Account> dbAccountOptional = accountRepository.findById(account.getId());
//        Assert.assertTrue(dbAccountOptional.isPresent());
        Account dbAccount = dbAccountOptional.get();
        log.info("get login account: {}", dbAccount);
//        Assert.assertEquals("test", dbAccount.getLoginName());
//        Assert.assertEquals("123456", dbAccount.getPassword());
    }

    @Test
    public void testQuery() {
        Optional<Account> optionalAccount = accountRepository.getByLoginName("test");
//        Assert.assertTrue(optionalAccount.isPresent());
        Account dbAccount = optionalAccount.get();
        log.info("get login account: {}", dbAccount);
//        Assert.assertEquals("test", dbAccount.getLoginName());
//        Assert.assertEquals("123456", dbAccount.getPassword());
    }

}
