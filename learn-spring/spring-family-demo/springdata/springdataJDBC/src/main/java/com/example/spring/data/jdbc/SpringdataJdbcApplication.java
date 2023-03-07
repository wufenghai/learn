package com.example.spring.data.jdbc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *     简单总结就是:  没有拦截在和缓存，每次执行完的SQL得到的就是一个指定想要的完整实体，没有关联的查询或更新操作 ，没有session, 更简单、高效。
 * mybatis:   sql与mapper层分离，开发者可以编写复杂的SQL以及动态SQL，
 *          适合查询复杂的场景，能够在xml文件里统一管理SQL文件，缺点是有些场景下需要编写复杂的SQL。
 * spring-data-jdbc:  无须配置和加载xml映射文件, 简化开发，对于单表查询多的场景下，使用spring-boot-data-jdbc会比mybatis更加简单，
 *          另外spring-boot-data-jdbc能够与mybatis做集成，使用更加灵活，支持配置SqlSessionFactoryBean。缺点是: 复杂的业务系统和场景下，复杂的SQL难以维护。
 *
 *
 *
 *      其实这个功能我们可以不用详细的了解，除非是要解决一些非常简单的操作，到那时mp其实已经解决了  他的功能与mp类似
 *      总的来说还是比较方便，定义一个实体类，写个接口，就能实现CRUD了， 没有spring data jpa复杂的生命周期， 没有mybatis那么复杂的代码（个人感觉使用mybatis generator也不是很方便）。
 *      但还有许多不足之处， 比如不能使用spring data jpa那样使用方法名称查询， 而@Param标记也不能省略， 还有很多可以优化的地方
 */
@SpringBootApplication
public class SpringdataJdbcApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringdataJdbcApplication.class, args);
    }

}
