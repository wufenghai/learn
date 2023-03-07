package com.example.spring.circular.dependency;

import com.example.spring.circular.dependency.model.ClassA;
import com.example.spring.circular.dependency.model.ClassB;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootTest
class SpringCircularDependencyApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void test() {
        // 创建IOC容器 并且进行初始化
        String resource = "spring/spring-ioc-circular-dependency.xml";
        ApplicationContext context = new ClassPathXmlApplicationContext(resource);
        // 获取ClassA的实例（此时会发生循环依赖）
        ClassA classA = (ClassA) context.getBean("classA");
        classA.getClassB();
    }
}
