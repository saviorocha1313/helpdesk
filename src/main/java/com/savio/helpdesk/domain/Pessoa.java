package com.savio.helpdesk.domain;
//  Camada Model — essa é a superclasse das entidades Cliente e Técnico

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
//  Utilitários para datas, coleções e conversões de enum

import javax.persistence.*;
//  Anotações JPA para mapeamento da entidade e seus atributos

import com.fasterxml.jackson.annotation.JsonFormat;
//  Usado para formatar datas na serialização JSON

import com.savio.helpdesk.domain.enums.Perfil;
//  Enum com tipos de perfil disponíveis no sistema (CLIENTE, ADMIN, TECNICO)

@Entity
public abstract class Pessoa implements Serializable {
    //  Classe abstrata — não pode ser instanciada diretamente, mas serve como base
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;
    //  Chave primária gerada automaticamente pelo banco

    protected String nome;

    @Column(unique = true)
    protected String cpf; //  CPF único — garante integridade e validação

    @Column(unique = true)
    protected String email;

    protected String senha;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "PERFIS")
    protected Set<Integer> perfis = new HashSet<>();
    //  Armazena os códigos dos perfis associados à pessoa (como ADMIN, CLIENTE, etc.)
    //  Usa FetchType.EAGER para carregar os perfis sempre que a pessoa for buscada

    @JsonFormat(pattern = "dd/MM/yyyy")
    protected LocalDate dataCriacao = LocalDate.now();
    //  Define automaticamente a data de criação do registro

    //  Construtor padrão
    public Pessoa() {
        super();
        addPerfis(Perfil.CLIENTE); // 👤 Garante que toda pessoa comece com perfil CLIENTE por padrão
    }

    //  Construtor completo — usado para criar objetos com dados iniciais
    public Pessoa(Integer id, String nome, String cpf, String email, String senha) {
        super();
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
        addPerfis(Perfil.CLIENTE);
    }

    //  Getters e setters — acesso controlado aos atributos encapsulados
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    public Set<Perfil> getPerfis() {
        //  Converte os códigos inteiros de perfil para os enums correspondentes
        return perfis.stream().map(Perfil::toEnum).collect(Collectors.toSet());
    }

    public void addPerfis(Perfil perfil) {
        this.perfis.add(perfil.getCodigo());
        //  Adiciona o código do perfil no conjunto de perfis
    }

    public LocalDate getDataCriacao() { return dataCriacao; }
    public void setDataCriacao(LocalDate dataCriacao) { this.dataCriacao = dataCriacao; }

    //  hashCode e equals — utilizados para comparação e armazenamento em coleções
    @Override
    public int hashCode(){
        final int prime = 31;
        int result = 1;
        result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Pessoa other = (Pessoa) obj;
        return Objects.equals(cpf, other.cpf) && Objects.equals(id, other.id);
    }
}
