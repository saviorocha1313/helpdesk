package com.savio.helpdesk.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.savio.helpdesk.domain.enums.Perfil;

@Entity//classe pessoa entidade criando tabela para classe pessoa 
public abstract class Pessoa implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // essa chave id não e minha responsabilidade e do banco para cada objeto o banco gera um id diferente
	protected Integer id;
	protected String nome;
	
	@Column(unique = true)//coluna unica nao vai existir dois cpfs 
	protected String cpf;
	
	@Column(unique = true)
	protected String email;
	protected String senha;
	
	@ElementCollection(fetch = FetchType.EAGER)// informando uma coleçao do elemento do tipo inteiro vai vim com perfil junto com o usuario
	@CollectionTable(name = "PERFIS")//tabela apenas com perfis
	protected Set<Integer> perfis = new HashSet<>(); //Aqui vou armazenar o codigo do perfil daquela pessoa 
	
	@JsonFormat(pattern = "dd/MM/yyyy" )
	protected LocalDate dataCriacao = LocalDate.now();
	
	public Pessoa() {
		super();
		addPerfis(Perfil.CLIENTE); //todo usuario criado ele vai ter o perfil de cliente criado no sistema 
	}

	public Pessoa(Integer id, String nome, String cpf, String email, String senha) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.senha = senha;
		addPerfis(Perfil.CLIENTE);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Set<Perfil> getPerfis() {
		return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet()); //Metodo para fazer a conversão para o tipo set 
	}

	public void addPerfis(Perfil perfil) {
		this.perfis.add(perfil.getCodigo());//aqui temos que adicionar e uma lista nao atributo
	}

	public LocalDate getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

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
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		if (cpf == null) {
			if(other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		if(id == null) {
			if(other.id !=null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
