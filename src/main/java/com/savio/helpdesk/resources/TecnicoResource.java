package com.savio.helpdesk.resources; // // Aqui usamos o controller para expor endpoints REST e delegar as ações ao service

import java.util.List;
import java.util.stream.Collectors;

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
	public ResponseEntity<TecnicoDTO> findById(@PathVariable Integer id){ //Retorno o tecnico dto objeto de transferencia de dados e Controla o http
		Tecnico obj = service.findById(id);
		return ResponseEntity.ok().body(new TecnicoDTO(obj));
	}
	@GetMapping
	public ResponseEntity<List<TecnicoDTO>> findAll(){ //Aqui vou puxar a lista de tecnicos Dtos 
		List<Tecnico> list = service.findAll();
		List<TecnicoDTO> listDTO = list.stream().map(obj -> new TecnicoDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);//fiz a requisição e vou retornar a lista de dto 
	}
}
