package org.example.kuber.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @RestController
    static class TestController {

        private final Environment environment;

        TestController(Environment environment) {
            this.environment = environment;
        }

        @RequestMapping("/env")
        public Object env() {
            return ((ConfigurableEnvironment) environment).getSystemEnvironment();
        }

        @RequestMapping("/props")
        public Object props() {
            return ((ConfigurableEnvironment) environment).getSystemProperties();
        }
    }
}
