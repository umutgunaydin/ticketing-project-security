package com.company;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication //includes @Configuration//
public class TicketingProjectSecurityApplication {//

    public static void main(String[] args) {
        SpringApplication.run(TicketingProjectSecurityApplication.class, args);
    }

    @Bean
    public ModelMapper mapper(){
        return new ModelMapper();
    }

}
