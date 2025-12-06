package com.savio.helpdesk.services;
//  Pacote da camada Service — responsável pela lógica de negócio da aplicação.

import java.util.Arrays; //  Usado para criar listas rapidamente com Arrays.asList

import org.springframework.beans.factory.annotation.Autowired; //  Injeta dependências automaticamente
import org.springframework.stereotype.Service; //  Marca a classe como um componente de serviço no Spring

//  Importa os modelos e repositórios que serão usados
import com.savio.helpdesk.domain.Chamado;
import com.savio.helpdesk.domain.Cliente;
import com.savio.helpdesk.domain.Tecnico;
import com.savio.helpdesk.domain.enums.Perfil;
import com.savio.helpdesk.domain.enums.Prioridade;
import com.savio.helpdesk.domain.enums.Status;
import com.savio.helpdesk.repositories.ChamadoRepository;
import com.savio.helpdesk.repositories.ClienteRepository;
import com.savio.helpdesk.repositories.TecnicoRepository;

@Service //  Essa classe é um "Service" no padrão MVC — lógica de negócio está aqui
public class DBService {

    //  Repositórios que acessam o banco de dados
    @Autowired
    private TecnicoRepository tecnicoRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ChamadoRepository chamadoRepository;

    public void instanciaDB() {
        //  Criando objetos de técnicos com dados fictícios
        Tecnico tec1 = new Tecnico(null, "Savio Augusto", "176.663.719-18", "saviorocha1.3@hotmail.com", "123");
        tec1.addPerfil(Perfil.ADMIN); //  Adicionando perfil de administrador ao técnico

        //  Criando mais técnicos
        Tecnico tec2 = new Tecnico(null, "Lucas Silva", "12345678901", "lucas.silva@hotmail.com", "456");
        Tecnico tec3 = new Tecnico(null, "Ana Pereira", "98765432100", "ana.pereira@hotmail.com", "789");
        Tecnico tec4 = new Tecnico(null, "João Carvalho", "11223344556", "joao.carvalho@hotmail.com", "101");
        Tecnico tec5 = new Tecnico(null, "Maria Costa", "22334455667", "maria.costa@hotmail.com", "102");

        //  Salvando todos os técnicos de uma vez no banco
        tecnicoRepository.saveAll(Arrays.asList(tec1, tec2, tec3, tec4, tec5));

        //  Criando objetos de clientes com dados fictícios
        Cliente cli1 = new Cliente(null, "Carlos Eduardo", "12345678912", "carlos.eduardo@gmail.com", "123");
        Cliente cli2 = new Cliente(null, "Fernanda Oliveira", "98765432101", "fernanda.oliveira@gmail.com", "456");
        Cliente cli3 = new Cliente(null, "Roberto Lima", "65432198700", "roberto.lima@hotmail.com", "789");
        Cliente cli4 = new Cliente(null, "Juliana Santos", "32165498733", "juliana.santos@gmail.com", "101");
        Cliente cli5 = new Cliente(null, "Patrícia Rocha", "78912365477", "patricia.rocha@hotmail.com", "102");

        //  Salvando clientes no banco
        clienteRepository.saveAll(Arrays.asList(cli1, cli2, cli3, cli4, cli5));

        //  Criando chamados com diferentes prioridades e status
        Chamado c1 = new Chamado(null, Prioridade.ALTA, Status.ANDAMENTO, "Chamado 01", "Primeiro chamado", tec1, cli1);
        Chamado c2 = new Chamado(null, Prioridade.MEDIA, Status.ABERTO, "Chamado 02", "Segundo chamado", tec2, cli2);
        Chamado c3 = new Chamado(null, Prioridade.BAIXA, Status.ENCERRADO, "Chamado 03", "Terceiro chamado", tec3, cli3);
        Chamado c4 = new Chamado(null, Prioridade.ALTA, Status.ABERTO, "Chamado 04", "Quarto chamado", tec4, cli4);
        Chamado c5 = new Chamado(null, Prioridade.MEDIA, Status.ENCERRADO, "Chamado 05", "Quinto chamado", tec5, cli5);
        Chamado c6 = new Chamado(null, Prioridade.BAIXA, Status.ANDAMENTO, "Chamado 06", "Sexto chamado", tec1, cli2);
        Chamado c7 = new Chamado(null, Prioridade.ALTA, Status.ENCERRADO, "Chamado 07", "Sétimo chamado", tec2, cli3);

        //  Salvando os chamados no banco
        chamadoRepository.saveAll(Arrays.asList(c1, c2, c3, c4, c5, c6, c7));
    }
}

