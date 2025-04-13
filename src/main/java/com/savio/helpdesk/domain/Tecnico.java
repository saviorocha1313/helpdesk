package com.savio.helpdesk.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.savio.helpdesk.domain.enums.Perfil;

@Entity
public class Tecnico extends Pessoa{
	private static final long serialVersionUID = 1L;
	
	@OneToMany(mappedBy = "tecnico")//Aqui mapeamos o tecnico do outro lado
	private List<Chamado> chamados = new ArrayList<>();

	public Tecnico() {
		super();
		addPerfis(Perfil.CLIENTE);
	}

	public Tecnico(Integer id, String nome, String cpf, String email, String senha) {
		super(id, nome, cpf, email, senha);
		addPerfis(Perfil.CLIENTE);
		
	}

	public List<Chamado> getChamados() { // Metodo para ter acesso a lista de chamados 
		return chamados;
	}

	public void setChamados(List<Chamado> chamados) {
		this.chamados = chamados;
	}
	
	
}
