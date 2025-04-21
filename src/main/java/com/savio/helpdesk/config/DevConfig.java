package com.savio.helpdesk.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.savio.helpdesk.services.DBService;

@Configuration
@Profile("dev")
public class DevConfig {

    @Autowired
    private DBService dbService;
    
    @Value("${spring.jpa.hibernate.ddl-auto}") //antes de chamar este metodo vou validar
    private String value;

    @Bean
    public boolean instanciaDB() {
        if(value.equals("create")) { //aqui eu quero que ele crie as tabelas para mim 
        	this.dbService.instanciaDB();
        	
        }
        return false;
    }
}

