package com.mxk.buildds.application;

import com.mxk.buildds.config.Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication(scanBasePackages = "com.mxk.buildds")
public class Application {

    public static void main(String[] args) throws Exception {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);

        Initialization initialization = (Initialization) ctx.getBean("initialization");
        Config config = initialization.getConfiguration();
        System.out.println(config);
    }

}
