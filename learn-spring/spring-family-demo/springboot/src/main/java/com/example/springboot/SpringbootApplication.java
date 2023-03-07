package com.example.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * https://blog.csdn.net/weixin_47872288/article/details/119514865?ops_request_misc=%257B%2522request%255Fid%2522%253A%2522167757201516782428679574%2522%252C%2522scm%2522%253A%252220140713.130102334..%2522%257D&request_id=167757201516782428679574&biz_id=0&utm_medium=distribute.pc_search_result.none-task-blog-2~all~top_positive~default-1-119514865-null-null.142^v73^wechat,201^v4^add_ask,239^v2^insert_chatgpt&utm_term=Spring%20Boot&spm=1018.2226.3001.4187
 *
 * 在static中主要存放的是css、html等静态文件
 * templates为前端模板
 * application.properties为核心配置文件
 */
//SpringBoot项目启动入口类
@SpringBootApplication //开启springboot配置.
@MapperScan("com.example.springboot.mapper")
public class SpringbootApplication {

    public static void main(String[] args) {
        //SpringApplication.run(SpringbootApplication.class, args);

        //获取入口SpringBoot类
        SpringApplication springApplication = new SpringApplication(SpringbootApplication.class);

        //设置它的属性  可通过再资源类中加入一个文本banner.txt即可
        springApplication.setBannerMode(Banner.Mode.OFF);
        springApplication.run(args);
    }

}
