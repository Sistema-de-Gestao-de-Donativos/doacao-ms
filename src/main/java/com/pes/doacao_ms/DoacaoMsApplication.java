package com.pes.doacao_ms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.pes.doacao_ms.feignClients")
public class DoacaoMsApplication {

    public static void main(String[] args) {
        SpringApplication.run(DoacaoMsApplication.class, args);
    }
}
