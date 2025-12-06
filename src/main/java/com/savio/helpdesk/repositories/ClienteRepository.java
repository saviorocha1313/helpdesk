package com.savio.helpdesk.repositories;
//  Pacote da camada Repository — especializado no acesso a dados da entidade Cliente

import org.springframework.data.jpa.repository.JpaRepository;
//  Interface do Spring Data JPA que fornece métodos CRUD prontos (save, findById, findAll, delete, etc.)

import com.savio.helpdesk.domain.Cliente;
//  Entidade que será gerenciada por este repositório

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    //  Não precisa implementar nada: o JpaRepository já cuida de tudo
    //  Cliente é a entidade principal
    //  Integer é o tipo da chave primária (ID)
    //  Se quiser adicionar consultas personalizadas no futuro, é aqui que você fará isso
}

