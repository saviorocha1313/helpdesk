package com.savio.helpdesk.services;
//  Camada de Service: responsável pelas regras de negócio e comunicação com a camada Repository

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.savio.helpdesk.services.exceptions.DataIntegrityViolationException;
import com.savio.helpdesk.services.exceptions.ObjectnotFoundException;
//  Exceção personalizada lançada quando não encontra o técnico

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//  Anotações Spring para definir este bean como um serviço

import com.savio.helpdesk.domain.Pessoa;
import com.savio.helpdesk.domain.Tecnico;
import com.savio.helpdesk.domain.dtos.TecnicoDTO;
import com.savio.helpdesk.repositories.PessoaRepository;
import com.savio.helpdesk.repositories.TecnicoRepository;

@Service //  Indica que essa classe é um componente de serviço do Spring
public class TecnicoService {

    @Autowired //  Injeção de dependência do Spring para utilizar o repositório
    private TecnicoRepository repository;
    @Autowired
    private PessoaRepository pessoaRepository;

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
		validaPorCpfEEmail(objDTO);
		Tecnico newObj = new Tecnico(objDTO);
		return repository.save(newObj); //Salva no banco e retorna como id 
	}
	public Tecnico update(Integer id, @Valid TecnicoDTO objDTO) {
		objDTO.setId(id);
		Tecnico oldObj = findById(id);
		validaPorCpfEEmail(objDTO);
		oldObj = new Tecnico(objDTO);
		return repository.save(oldObj);
	}

	private void validaPorCpfEEmail(TecnicoDTO objDTO) {
		Optional<Pessoa> obj = pessoaRepository.findByCpf(objDTO.getCpf());
		if(obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("CPF já cadastrado no sistema!");
			
		}
		obj = pessoaRepository.findByEmail(objDTO.getEmail());//Aqui se o id for diferente e lançado uma excesão
		if(obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("E-mail já cadastrado no sistema!");
			
		}
	}

	public Tecnico update2(Integer id, @Valid TecnicoDTO objDTO) {
		objDTO.setId(id);
		Tecnico oldObj = findById(id);
        return oldObj;
    }
}


