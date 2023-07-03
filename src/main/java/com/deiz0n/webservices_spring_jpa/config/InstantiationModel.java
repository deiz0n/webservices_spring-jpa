package com.deiz0n.webservices_spring_jpa.config;

import com.deiz0n.webservices_spring_jpa.models.Category;
import com.deiz0n.webservices_spring_jpa.models.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class InstantiationModel implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {



    }
}
