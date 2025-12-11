package com.savio.helpdesk.domain;
//  Camada Model — representa os dados da aplicação e será mapeada pelo JPA

import java.util.ArrayList;
import java.util.List;
//  Utilitários para manipular lista de chamados associados

import javax.persistence.Entity;
import javax.persistence.OneToMany;
//  Anotações JPA para definir a entidade e o relacionamento com outros objetos

import com.fasterxml.jackson.annotation.JsonIgnore;
//  Evita que a lista de chamados seja serializada no JSON para evitar loop infinito

import com.savio.helpdesk.domain.enums.Perfil;
//  Enum que representa os tipos de perfil (CLIENTE, ADMIN, TECNICO)

@Entity //  Marca a classe como uma entidade JPA — será uma tabela no banco de dados
public class Cliente extends Pessoa {
    //  Usa herança — Cliente é um tipo de Pessoa (reaproveita atributos e métodos comuns)
    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @OneToMany(mappedBy = "cliente")
    private List<Chamado> chamados = new ArrayList<>();
    //  Um cliente pode ter vários chamados
    //  `mappedBy = "cliente"` aponta para o atributo cliente da classe Chamado
    //  `@JsonIgnore` impede serialização dessa lista, evitando problemas com recursividade

    //  Construtor vazio exigido pelo JPA
    public Cliente() {
        super(); // 🧬 Chama o construtor da classe mãe (Pessoa)
        addPerfis(Perfil.CLIENTE); // 🔐 Garante que todo cliente tenha esse perfil por padrão
    }

    //  Construtor completo com os campos herdados + perfil padrão
    public Cliente(Integer id, String nome, String cpf, String email, String senha) {
        super(id, nome, cpf, email, senha);
        addPerfis(Perfil.CLIENTE); // 🔐 Define automaticamente o perfil ao instanciar
    }

    public Cliente(com.savio.helpdesk.domain.dtos.ClienteDTO obj) {
        super();
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.cpf = obj.getCpf();
        this.email = obj.getEmail();
        this.senha = obj.getSenha();
        this.perfis = obj.getPerfis().stream().map(x -> x.getCodigo()).collect(java.util.stream.Collectors.toSet());
        this.dataCriacao = obj.getDataCriacao();
    }

    //  Getters e setters para acessar os chamados do cliente
    public List<Chamado> getChamados() {
        return chamados;
    }

    public void setChamados(List<Chamado> chamados) {
        this.chamados = chamados;
    }
}
