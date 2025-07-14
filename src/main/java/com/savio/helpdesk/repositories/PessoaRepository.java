package com.savio.helpdesk.repositories;
//  Camada Repository — trata do acesso direto ao banco de dados

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
//  Interface JPA que oferece métodos prontos (findAll, save, findById, etc.)

import com.savio.helpdesk.domain.Pessoa;
//  Superclasse abstrata das entidades Cliente e Tecnico

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {
    //  Interface genérica que fornece acesso aos dados da entidade Pessoa
    //  Mesmo sendo abstrata, pode ser usada para consultas polimórficas
    //  Não precisa de implementação manual — o Spring cuida do CRUD
	Optional<Pessoa> findByCpf(String cpf);
	Optional<Pessoa> findByEmail(String email);
}
