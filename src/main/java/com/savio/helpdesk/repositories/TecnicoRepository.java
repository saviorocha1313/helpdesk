package com.savio.helpdesk.repositories;
//  Camada Repository — responsável por acessar os dados da entidade Tecnico

import org.springframework.data.jpa.repository.JpaRepository;
//  Interface do Spring Data que traz métodos prontos de CRUD e busca

import com.savio.helpdesk.domain.Tecnico;
//  Entidade que representa um técnico no sistema

public interface TecnicoRepository extends JpaRepository<Tecnico, Integer> {
    //  Extende JpaRepository para herdar métodos como:
    //  findAll(), findById(id), save(obj), delete(obj)
    //  Tecnico é a entidade alvo; Integer é o tipo da chave primária (ID)
    //  Interface limpa, sem necessidade de métodos personalizados por enquanto
}

