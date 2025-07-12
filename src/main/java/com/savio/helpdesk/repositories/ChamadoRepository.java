package com.savio.helpdesk.repositories;
//  Pacote da camada Repository — responsável pelo acesso direto ao banco via JPA

import org.springframework.data.jpa.repository.JpaRepository;
//  Interface do Spring Data JPA que já oferece vários métodos prontos (findAll, save, delete, etc.)

import com.savio.helpdesk.domain.Chamado;
//  Entidade que este repositório vai gerenciar (Chamado)

public interface ChamadoRepository extends JpaRepository<Chamado, Integer> {
    //  Herda os métodos de CRUD para a entidade Chamado
    //  O tipo do ID (Integer) é informado como segundo parâmetro
    //  Nenhum método personalizado necessário no momento — Spring já cuida do básico!
}
