package com.example.spring.framework.bean;


import org.junit.Test;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author wfh
 * @create 2023/3/1 9:03
 */
public class HelloWorldTest {

    /**
     * 1. 底层是怎么创建对象的，是通过反射机制调用无参数构造方法吗？
     * 2. Spring是如何创建对象的呢？原理是什么？
     *          dom4j解析beans.xml文件，从中获取class属性值，类的全类名
     *          通过反射机制调用无参数构造方法创建对象
     *          Class clazz = Class.forName("com.atguigu.spring6.bean.HelloWorld");
     *          Object object = clazz.getDeclaredConstructor().newInstance();
     *  3. 把创建好的对象存储到一个什么样的数据结构当中了呢？
     *          bean对象最终存储在spring容器中，在spring源码底层就是一个map集合，存储bean的map在DefaultListableBeanFactory类中：
     *                  private final Map<String, BeanDefinition> beanDefinitionMap;
     *                  this.beanDefinitionMap = new ConcurrentHashMap(256);
     *          Spring容器加载到Bean类时 , 会把这个类的描述信息, 以包名加类名的方式存到beanDefinitionMap 中,
     *          Map<String,BeanDefinition> , 其中 String是Key , 默认是类名首字母小写 , BeanDefinition ,
     *          存的是类的定义(描述信息) , 我们通常叫eanDefinition接口为 : bean的定义对象。
     *
     */
    @Test
    public void testHelloWorld(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
        HelloWorld helloworld = (HelloWorld) ac.getBean("helloWorld");
        helloworld.sayHello();
    }

    @Test
    public void testHelloWorld2(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
        HelloWorld bean = ac.getBean("helloWorld", HelloWorld.class);
        bean.sayHello();
    }

}
