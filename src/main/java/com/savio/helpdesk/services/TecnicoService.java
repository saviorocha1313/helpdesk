
package com.savio.helpdesk.services;

import java.util.Optional;

import com.savio.helpdesk.services.exceptions.ObjectnotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.savio.helpdesk.domain.Tecnico;
import com.savio.helpdesk.repositories.TecnicoRepository;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository repository;

    // Método para buscar técnico pelo ID
    public Tecnico findById(Integer id) {
        Optional<Tecnico> obj = repository.findById(id); // Busca técnico no repositório
        return obj.orElseThrow(() -> 
            new ObjectnotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Tecnico.class.getName(), null)
        ); // Lança exceção se não encontrar objeto
    }
}

