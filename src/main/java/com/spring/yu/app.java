package com.spring.yu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * Created by yunan on 2017/3/10.
 */
@ServletComponentScan
@SpringBootApplication
public class app
{
    public static void main(String...args){
        SpringApplication.run(app.class,args);
    }

}
