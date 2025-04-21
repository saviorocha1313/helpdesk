package com.savio.helpdesk.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.savio.helpdesk.domain.enums.Perfil;

@Entity
public class Tecnico extends Pessoa {
    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @OneToMany(mappedBy = "tecnico")
    private List<Chamado> chamados = new ArrayList<>();

    public Tecnico() {
        super();
        configurarPerfisPadrao();
    }

    public Tecnico(Integer id, String nome, String cpf, String email, String senha) {
        super(id, nome, cpf, email, senha);
        configurarPerfisPadrao();
    }

    private void configurarPerfisPadrao() {
        addPerfil(Perfil.ADMIN);
        addPerfil(Perfil.CLIENTE);
    }

    public List<Chamado> getChamados() {
        return new ArrayList<>(chamados);
    }

    public void setChamados(List<Chamado> chamados) {
        this.chamados = chamados;
    }

    public void addPerfil(Perfil perfil) {
        this.perfis.add(perfil.getCodigo());
    }
}
