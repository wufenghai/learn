package com.example.demoselector.annotation;

import com.example.demoselector.imports.MyImportSelector;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author wfh
 * @create 2024/1/18 9:52
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(MyImportSelector.class)
public @interface EnableLog {
    String name();
}
