package com.xxg.study.config;


import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.util.Locale;

/**
 * description: MySecurityMessages
 * date: 2020/11/10 13:13
 * author: faner
 */
@Configuration
public class MySecurityMessages {

    /**
     * 自定义错误信息
     * @return
     */
    @Bean
    public MessageSource messageSource() {
        Locale.setDefault(Locale.CHINA);
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        //中文提示信息配置文件
        messageSource.addBasenames("classpath:messages_zh_CN");
        return messageSource;
    }
}

