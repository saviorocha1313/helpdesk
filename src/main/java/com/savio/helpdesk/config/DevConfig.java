package com.savio.helpdesk.config;
//  Pacote onde ficam as configurações da aplicação. Não é parte direta do MVC, mas dá suporte a ele.

import org.springframework.beans.factory.annotation.Value; //  Permite ler valores definidos no application.properties
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean; //  Indica que o método retorna um componente gerenciado pelo Spring
import org.springframework.context.annotation.Configuration; //  Define que essa classe contém configurações
import org.springframework.context.annotation.Profile; //  Permite executar essa classe apenas em um perfil específico (ex.: dev)

import com.savio.helpdesk.services.DBService; //  Classe que contém a lógica de inicialização/população do banco de dados

@Configuration
@Profile("dev") //  A classe só será carregada quando o perfil "dev" estiver ativo (definido no application.properties)
public class DevConfig {

    private final DBService dbService; //  Serviço responsável por instanciar dados no banco (se necessário)

    @Value("${spring.jpa.hibernate.ddl-auto}") //  Lê a propriedade que controla a forma como o Hibernate lida com o schema
    private String ddlAuto;

    public DevConfig(DBService dbService) {
        this.dbService = dbService; //  Injeta o DBService via construtor (boa prática!)
    }

    @Bean
    public CommandLineRunner startDb() {
        return args -> {
            //  Quando o projeto inicia, verifica se o modo de criação do banco está como "create"
            if ("create".equals(ddlAuto)) {
                dbService.instanciaDB(); //  Se estiver, chama o método que popula/cria as tabelas iniciais no banco
            }
        };
    }
}



