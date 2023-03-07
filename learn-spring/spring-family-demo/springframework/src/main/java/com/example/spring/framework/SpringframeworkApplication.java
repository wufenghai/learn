package com.example.spring.framework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * Spring 是一款主流的 Java EE 轻量级开源框架 ，Spring 由“Spring 之父”Rod Johnson 提出并创立，
 * 其目的是用于简化 Java 企业级应用的开发难度和开发周期。
 * Spring的用途不仅限于服务器端的开发。从简单性、可测试性和松耦合的角度而言，
 * 任何Java应用都可以从Spring中受益。Spring 框架除了自己提供功能外，还提供整合其他技术和框架的能力。
 * <p>
 * <p>
 * <p>
 * <p>
 * 单独使用@Autowired注解，默认根据类型装配。【默认是byType】
 *   ①场景一：属性注入
 *       @Autowired
 *       private UserDao userDao;
 *   ②场景二：set注入
 *       private UserDao userDao;
 *       @Autowired
 *       public void setUserDao(UserDao userDao) {
 *           this.userDao = userDao;
 *       }
 *   ③场景三：构造方法注入
 *      private UserDao userDao;
 *      @Autowired
 *      public UserServiceImpl(UserDao userDao) {
 *          this.userDao = userDao;
 *      }
 *   ④场景四：形参上注入
 *      private UserDao userDao;
 *      public UserServiceImpl(@Autowired UserDao userDao) {
 *          this.userDao = userDao;
 *      }
 *    ⑤场景五：只有一个构造函数，无注解
 *      @Autowired
 *      private UserDao userDao;
 *      public UserServiceImpl(UserDao userDao) {
 *          this.userDao = userDao;
 *      }
 *      ⑥场景六：@Autowired注解和@Qualifier注解联合
 *      @Autowired
 *      @Qualifier("userDaoImpl") // 指定bean的名字
 *      private UserDao userDao;
 *
 *
 *@Resource注解也可以完成属性注入。那它和@Autowired注解有什么区别？
 *      @Resource注解是JDK扩展包中的，也就是说属于JDK的一部分。所以该注解是标准注解，更加具有通用性。(JSR-250标准中制定的注解类型。JSR是Java规范提案。)
 *      @Autowired注解是Spring框架自己的。
 *      @Resource注解默认根据名称装配byName，未指定name时，使用属性名作为name。通过name找不到的话会自动启动通过类型byType装配。
 *      @Autowired注解默认根据类型装配byType，如果想根据名称装配，需要配合@Qualifier注解一起用。
 *      @Resource注解用在属性上、setter方法上。
 *      @Autowired注解用在属性上、setter方法上、构造方法上、构造方法参数上。
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */
@SpringBootApplication
public class SpringframeworkApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringframeworkApplication.class, args);
    }

}
