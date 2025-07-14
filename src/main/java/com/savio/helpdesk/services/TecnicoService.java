package com.savio.helpdesk.services;
//  Camada de Service: responsável pelas regras de negócio e comunicação com a camada Repository

import java.util.List;
import java.util.Optional;

import com.savio.helpdesk.services.exceptions.ObjectnotFoundException;
//  Exceção personalizada lançada quando não encontra o técnico

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//  Anotações Spring para definir este bean como um serviço

import com.savio.helpdesk.domain.Tecnico;
import com.savio.helpdesk.domain.dtos.TecnicoDTO;
import com.savio.helpdesk.repositories.TecnicoRepository;

@Service //  Indica que essa classe é um componente de serviço do Spring
public class TecnicoService {

    @Autowired //  Injeção de dependência do Spring para utilizar o repositório
    private TecnicoRepository repository;

    //  Método para buscar técnico pelo ID
    public Tecnico findById(Integer id) {
        Optional<Tecnico> obj = repository.findById(id); //  Busca opcional do técnico com base no ID
        return obj.orElseThrow(() -> 
            new ObjectnotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Tecnico.class.getName(),
                null
            )
        ); //  Lança exceção customizada se o técnico não for encontrado
    }

    //  Método para retornar todos os técnicos cadastrados no banco
    public List<Tecnico> findAll() {
        return repository.findAll(); //  Retorna a lista completa direto do repository
    }

	public Tecnico create(TecnicoDTO objDTO) {
		objDTO.setId(null);
		Tecnico newObj = new Tecnico(objDTO);
		return repository.save(newObj); //Salva no banco e retorna como id 
	}
}


