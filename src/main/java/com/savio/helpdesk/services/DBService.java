package com.savio.helpdesk.services;//Injetar instacias em outras partes do codigo

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.savio.helpdesk.domain.Chamado;
import com.savio.helpdesk.domain.Cliente;
import com.savio.helpdesk.domain.Tecnico;
import com.savio.helpdesk.domain.enums.Perfil;
import com.savio.helpdesk.domain.enums.Prioridade;
import com.savio.helpdesk.domain.enums.Status;
import com.savio.helpdesk.repositories.ChamadoRepository;
import com.savio.helpdesk.repositories.ClienteRepository;
import com.savio.helpdesk.repositories.TecnicoRepository;

@Service
public class DBService {
		
	    @Autowired
	    private TecnicoRepository tecnicoRepository;
	    @Autowired
	    private ClienteRepository clienteRepository;
	    @Autowired
	    private ChamadoRepository chamadoRepository;
	    
		public void instanciaDB() { // Este metodo sobe algumas instancias e salva no banco de dados 
			 Tecnico tec1 = new Tecnico(null, "Savio Augusto", "17666371918", "saviorocha1.3@hotmail.com", "123");
		        tec1.addPerfil(Perfil.ADMIN);

		        Cliente cli1 = new Cliente(null, "Alex Venus", "13241828735", "alexvenus@hotmail.com", "124");

		        Chamado c1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 01", "Primeiro chamado", tec1, cli1);

		        tecnicoRepository.saveAll(Arrays.asList(tec1));
		        clienteRepository.saveAll(Arrays.asList(cli1));
		        chamadoRepository.saveAll(Arrays.asList(c1));
		}
}
