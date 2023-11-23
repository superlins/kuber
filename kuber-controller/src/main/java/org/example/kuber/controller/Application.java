package org.example.kuber.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class Application {

    private static final Logger PRUDENT_LOGGER = LoggerFactory.getLogger("PRUDENT_LOGGER");

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(Application.class, args);

        for (;;) {
            PRUDENT_LOGGER.info("1111111111");
            TimeUnit.MILLISECONDS.sleep(10);
        }
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
