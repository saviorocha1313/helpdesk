package com.savio.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.savio.helpdesk.domain.Chamado;


public interface ChamadoRepository extends JpaRepository<Chamado, Integer> {

}
