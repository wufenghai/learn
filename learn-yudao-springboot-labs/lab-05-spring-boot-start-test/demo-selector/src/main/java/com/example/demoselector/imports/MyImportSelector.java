package com.example.demoselector.imports;

import com.example.demoselector.annotation.EnableLog;
import com.example.demoselector.bean.People;
import com.example.demoselector.config.MyConfig;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * selectImports方法的返回值，必须是一个class（全称），该class会被spring容器所托管起来
 *
 * @author wfh
 * @create 2024/1/18 9:47
 */
public class MyImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        //获取注解的属性信息
        System.out.println(importingClassMetadata.getAllAnnotationAttributes(EnableLog.class.getName()));
        //这里可以获取到注解的详细信息，然后根据信息去动态的返回需要被spring容器管理的bean
        return new String[]{"com.example.demoselector.bean.User", People.class.getName(), MyConfig.class.getName()};
    }
}
