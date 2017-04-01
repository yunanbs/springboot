package com.spring.yu;

import com.spring.yu.config.JwtFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;

/**
 * Created by yunan on 2017/3/10.
 */
@ServletComponentScan
@SpringBootApplication
public class app
{
    @Bean
    public FilterRegistrationBean jwtFilter(){
        final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new JwtFilter());
        registrationBean.addUrlPatterns("/secret/*");
        return registrationBean;
    }

    public static void main(String...args){
        SpringApplication.run(app.class,args);

    }

}
