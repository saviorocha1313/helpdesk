package com.savio.helpdesk.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    @Autowired
    private BCryptPasswordEncoder encoder; // Injeção do encoder para criptografar as senhas

    public void instanciaDB() {

        // Criando Técnicos com senha criptografada
        Tecnico tec1 = new Tecnico(null, "Savio Augusto", "176.663.719-18", "saviorocha1.3@hotmail.com", encoder.encode("123"));
        tec1.addPerfil(Perfil.ADMIN);

        Tecnico tec2 = new Tecnico(null, "Lucas Silva", "12345678901", "lucas.silva@hotmail.com", encoder.encode("456"));
        Tecnico tec3 = new Tecnico(null, "Ana Pereira", "98765432100", "ana.pereira@hotmail.com", encoder.encode("789"));
        Tecnico tec4 = new Tecnico(null, "João Carvalho", "11223344556", "joao.carvalho@hotmail.com", encoder.encode("101"));
        Tecnico tec5 = new Tecnico(null, "Maria Costa", "22334455667", "maria.costa@hotmail.com", encoder.encode("102"));

        tecnicoRepository.saveAll(Arrays.asList(tec1, tec2, tec3, tec4, tec5));

        // Criando Clientes com senha criptografada
        Cliente cli1 = new Cliente(null, "Carlos Eduardo", "12345678912", "carlos.eduardo@gmail.com", encoder.encode("123"));
        Cliente cli2 = new Cliente(null, "Fernanda Oliveira", "98765432101", "fernanda.oliveira@gmail.com", encoder.encode("456"));
        Cliente cli3 = new Cliente(null, "Roberto Lima", "65432198700", "roberto.lima@hotmail.com", encoder.encode("789"));
        Cliente cli4 = new Cliente(null, "Juliana Santos", "32165498733", "juliana.santos@gmail.com", encoder.encode("101"));
        Cliente cli5 = new Cliente(null, "Patrícia Rocha", "78912365477", "patricia.rocha@hotmail.com", encoder.encode("102"));

        clienteRepository.saveAll(Arrays.asList(cli1, cli2, cli3, cli4, cli5));

        // Criando Chamados
        Chamado c1 = new Chamado(null, Prioridade.ALTA, Status.ANDAMENTO, "Chamado 01", "Primeiro chamado", tec1, cli1);
        Chamado c2 = new Chamado(null, Prioridade.MEDIA, Status.ABERTO, "Chamado 02", "Segundo chamado", tec2, cli2);
        Chamado c3 = new Chamado(null, Prioridade.BAIXA, Status.ENCERRADO, "Chamado 03", "Terceiro chamado", tec3, cli3);
        Chamado c4 = new Chamado(null, Prioridade.ALTA, Status.ABERTO, "Chamado 04", "Quarto chamado", tec4, cli4);
        Chamado c5 = new Chamado(null, Prioridade.MEDIA, Status.ENCERRADO, "Chamado 05", "Quinto chamado", tec5, cli5);
        Chamado c6 = new Chamado(null, Prioridade.BAIXA, Status.ANDAMENTO, "Chamado 06", "Sexto chamado", tec1, cli2);
        Chamado c7 = new Chamado(null, Prioridade.ALTA, Status.ENCERRADO, "Chamado 07", "Sétimo chamado", tec2, cli3);

        chamadoRepository.saveAll(Arrays.asList(c1, c2, c3, c4, c5, c6, c7));
    }
}