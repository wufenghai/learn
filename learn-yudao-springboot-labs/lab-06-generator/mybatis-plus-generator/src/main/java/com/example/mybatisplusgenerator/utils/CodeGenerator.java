package com.example.mybatisplusgenerator.utils;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.sql.Types;
import java.util.Collections;

/**
 * wfh
 */
public class CodeGenerator {
    public static void main(String[] args) {
        generator();
    }

    public static void generator() {
        FastAutoGenerator.create("jdbc:mysql://127.0.0.1:3306/testdb?serverTimezone=GMT%2B8&characterEncoding=utf-8&userSSL=false" , "root" , "root")
                .globalConfig(builder -> {
                    builder.author("wfh") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir("D:/development/learn/learn-yudao-springboot-labs/lab-06-generator/mybatis-plus-generator/src/main/java/"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.example.mybatisplusgenerator") // 设置父包名
                            .moduleName("testdb") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, "D:/development/learn/learn-yudao-springboot-labs/lab-06-generator/mybatis-plus-generator/src/main/resources/mapper")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.entityBuilder().enableLombok();// 启用lombok注解
                    builder.mapperBuilder().enableMapperAnnotation().build(); // 开启mapper注解(@Mapper)
                    builder.controllerBuilder().enableHyphenStyle()  // 开启访问路径 驼峰转连字符
                            .enableRestStyle();  // 开启生成@RestController 控制器
                    builder.addInclude("staffs") // 设置需要生成的表名
                            .addTablePrefix("t_" , "sys_"); // 设置过滤表前缀
                })
//                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}


