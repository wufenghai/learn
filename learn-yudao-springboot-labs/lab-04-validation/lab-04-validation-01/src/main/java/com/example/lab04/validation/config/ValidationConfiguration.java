package com.example.lab04.validation.config;

import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.annotation.Resource;
import javax.validation.Validator;


/**
 * 配置校验实现国际化
 */
@Configuration
public class ValidationConfiguration {

    @Resource
    ApplicationContext applicationContext;

    /**
     * 参考  方法，构建 Validator Bean
     *
     * @return Validator 对象
     */
    @Bean
    public Validator validator(MessageSource messageSource)  {
        // 创建 LocalValidatorFactoryBean 对象
        LocalValidatorFactoryBean validator = ValidationAutoConfiguration.defaultValidator(applicationContext);
        // 设置 messageSource 属性，实现 i18n 国际化
        validator.setValidationMessageSource(messageSource);
        // 返回
        return validator;
    }

}