package com.geetopod.ceg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebApp {
    private static WebApp __instance;

    @Autowired
    private AppConfig _appConfig;

    public static WebApp instance() {
        return __instance;
    }

    public WebApp() {
        __instance = this;
    }

    public AppConfig appConfig() {
        return _appConfig;
    }

    public static void main(String[] args) {
        com.geetopod.clients.Services.instance().session(new SpringBootSession());
        SpringApplication.run(WebApp.class, args);
    }
}
