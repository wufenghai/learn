package com.example.spring.data.jdbc.repository;

import com.example.spring.data.jdbc.model.Account;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * @author wfh
 * @create 2023/3/1 17:14
 */
//继承CrudRepository， 第一个模板参数是实体类， 第二个参数是主键对应的数据类型
public interface AccountRepository extends CrudRepository<Account, Long> {
    @Query("select * from account where login_name=:loginName")
    Optional<Account> getByLoginName(@Param("loginName") String loginName);
}

