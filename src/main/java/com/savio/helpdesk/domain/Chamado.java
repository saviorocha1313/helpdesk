package com.savio.helpdesk.domain;
//  Pacote da camada Model — representa os dados da aplicação (entidades JPA)

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
//  Utilitários para data e comparação de objetos

import javax.persistence.*;
//  Anotações JPA para persistência (tabela, chave primária, relacionamentos)

import com.fasterxml.jackson.annotation.JsonFormat;
//  Anotação para definir o formato de datas no JSON de resposta da API

import com.savio.helpdesk.domain.enums.Prioridade;
import com.savio.helpdesk.domain.enums.Status;
//  Enums utilizados para controlar prioridade e status dos chamados

@Entity //  Marca a classe como uma entidade do JPA — será mapeada para uma tabela no banco
public class Chamado implements Serializable {
    private static final long serialVersionUID = 1L;
    //  Permite que objetos dessa classe sejam serializados (útil em APIs)

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    //  Chave primária gerada automaticamente pelo banco

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataAbertura = LocalDate.now();
    //  Data em que o chamado foi aberto — inicializa com a data atual

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataFechamento;
    //  Data de fechamento (pode ser nula até o chamado ser finalizado)

    private Prioridade prioridade; //  Enum: BAIXA, MEDIA ou ALTA
    private Status status;         //  Enum: ABERTO, ANDAMENTO, ENCERRADO

    private String titulo;         //  Título do chamado
    private String observações;    // Descrição ou comentários sobre o chamado

    @ManyToOne
    @JoinColumn(name = "tecnico_id")
    private Tecnico tecnico;
    //  Muitos chamados podem ser atribuídos a um único técnico

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
    //  Muitos chamados podem ser feitos por um único cliente

    //  Construtor vazio exigido pelo JPA
    public Chamado() {
        super();
    }

    //  Construtor com todos os campos, exceto data de abertura que é automática
    public Chamado(Integer id, Prioridade prioridade, Status status, String titulo, String observações, Tecnico tecnico,
            Cliente cliente) {
        super();
        this.id = id;
        this.prioridade = prioridade;
        this.status = status;
        this.titulo = titulo;
        this.observações = observações;
        this.tecnico = tecnico;
        this.cliente = cliente;
    }

    //  Getters e setters — permitem acesso controlado aos atributos privados
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public LocalDate getDataAbertura() { return dataAbertura; }
    public void setDataAbertura(LocalDate dataAbertura) { this.dataAbertura = dataAbertura; }

    public LocalDate getDataFechamento() { return dataFechamento; }
    public void setDataFechamento(LocalDate dataFechamento) { this.dataFechamento = dataFechamento; }

    public Prioridade getPrioridade() { return prioridade; }
    public void setPrioridade(Prioridade prioridade) { this.prioridade = prioridade; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getObservações() { return observações; }
    public void setObservações(String observações) { this.observações = observações; }

    public Tecnico getTecnico() { return tecnico; }
    public void setTecnico(Tecnico tecnico) { this.tecnico = tecnico; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    //  Métodos para comparar objetos com base no ID — utilizados por coleções e frameworks
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Chamado other = (Chamado) obj;
        return Objects.equals(id, other.id);
    }
}
