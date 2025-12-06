package com.savio.helpdesk.domain;
//  Pacote da camada Model — essa classe representa um técnico no sistema

import java.util.ArrayList;
import java.util.List;
//  Utilitários para lista de chamados
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
//  Anotações JPA para mapear como entidade e definir relacionamentos

import com.fasterxml.jackson.annotation.JsonIgnore;
//  Evita problemas na serialização do JSON (loop de chamadas)
import com.savio.helpdesk.domain.dtos.TecnicoDTO;
import com.savio.helpdesk.domain.enums.Perfil;
//  Enum que representa os perfis (ADMIN, CLIENTE, etc.)

@Entity //  Define que essa classe será mapeada como tabela no banco
public class Tecnico extends Pessoa {
    //  Representa o técnico que irá atender chamados
    //  Herdando os atributos e métodos comuns da classe Pessoa
    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @OneToMany(mappedBy = "tecnico")
    private List<Chamado> chamados = new ArrayList<>();
    //  Um técnico pode ter vários chamados atribuídos a ele
    //  mappedBy = "tecnico"` refere-se ao atributo da outra classe
    //  @JsonIgnore evita loop infinito ao gerar JSON

    public Tecnico() {
        super();
        configurarPerfisPadrao(); //  Todo técnico terá perfis padrão (ADMIN + CLIENTE)
    }

    public Tecnico(Integer id, String nome, String cpf, String email, String senha) {
        super(id, nome, cpf, email, senha);
        configurarPerfisPadrao(); //  Garante perfis adequados já no construtor
    }
    public Tecnico(TecnicoDTO obj) {
		super();
		this.id = obj.getId();
		this.nome =obj.getNome();
		this.cpf = obj.getCpf();
		this.email = obj.getEmail();
		this.senha = obj.getSenha();
		this.perfis = obj.getPerfis().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
		this.dataCriacao = obj.getDataCriacao();
	}

    private void configurarPerfisPadrao() {
        addPerfil(Perfil.ADMIN);
        addPerfil(Perfil.CLIENTE);
        //  Um técnico tem privilégios de administração e acesso como cliente
    }

    public List<Chamado> getChamados() {
        return new ArrayList<>(chamados);
        //  Retorna cópia da lista pra evitar alteração direta do atributo
    }

    public void setChamados(List<Chamado> chamados) {
        this.chamados = chamados;
        //  Permite definir uma lista de chamados atribuídos ao técnico
    }

    public void addPerfil(Perfil perfil) {
        this.perfis.add(perfil.getCodigo());
        //  Adiciona perfil específico manualmente se necessário
    }
}

