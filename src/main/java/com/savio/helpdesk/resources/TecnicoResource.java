package com.savio.helpdesk.resources; // aqui não vamos delegar serviços para o banco de dados 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.savio.helpdesk.domain.Tecnico;
import com.savio.helpdesk.domain.dtos.TecnicoDTO;
import com.savio.helpdesk.services.TecnicoService;

@RestController
@RequestMapping(value = "/tecnicos") //End points para serviços dos meus tecnicos e sei que eu quero acessar este metodo
public class TecnicoResource {
	
	// localhost:8080/tecnicos/1 
	
	@Autowired
	private TecnicoService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<TecnicoDTO> findById(@PathVariable Integer id){ // Controla o http
		Tecnico obj = service.findById(id);
		return ResponseEntity.ok().body(new TecnicoDTO(obj));
	}
	
}
