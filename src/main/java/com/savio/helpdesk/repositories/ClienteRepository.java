package com.savio.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.savio.helpdesk.domain.Cliente;


public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
