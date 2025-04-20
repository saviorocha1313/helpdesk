package com.savio.helpdesk.repositories;//interface para salvar algumas coisas no banco 

import org.springframework.data.jpa.repository.JpaRepository;

import com.savio.helpdesk.domain.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

}
