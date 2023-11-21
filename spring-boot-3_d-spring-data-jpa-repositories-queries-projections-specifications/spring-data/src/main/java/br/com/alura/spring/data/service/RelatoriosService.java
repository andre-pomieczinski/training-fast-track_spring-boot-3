package br.com.alura.spring.data.service;

import br.com.alura.spring.data.orm.Cargo;
import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.orm.FuncionarioProjecao;
import br.com.alura.spring.data.repository.CargoRepository;
import br.com.alura.spring.data.repository.FuncionarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

@Service
public class RelatoriosService {

    private FuncionarioRepository funcionarioRepository;

    private Boolean system = true;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public RelatoriosService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public void inicial(Scanner scanner) {
        while (system) {
            System.out.println("Qual ação você deseja executar? ");
            System.out.println("0 - Sair");
            System.out.println("1 - Busca funcionário por nome");
            System.out.println("2 - Busca funcionário por nome, data contratação e salário maior");
            System.out.println("3 - Busca funcionário com data contratação maior que...");
            System.out.println("4 - Pesquisa funcionario salario");
            int action = scanner.nextInt();
            switch (action) {
                case 1:
                    buscaFuncionarioNome(scanner);
                    break;
                case 2:
                    buscaFuncionarioNomeSalarioMaiorData(scanner);
                    break;
                case 3:
                    buscaFuncionarioComDataContratacaoMaior(scanner);
                    break;
                case 4:
                    pesquisaFuncionarioSalario();
                    break;
                default:
                    system = false;
                    break;
            }
        }
    }

    private void buscaFuncionarioComDataContratacaoMaior(Scanner scanner) {
        System.out.println("Digite a data(dd/MM/yyyy): ");
        String data = scanner.next();
        LocalDate localDate = LocalDate.parse(data, formatter);

        List<Funcionario> funcionarios = funcionarioRepository.findDataContratacaoMaior(localDate);
        funcionarios.forEach(System.out::println);
    }

    private void buscaFuncionarioNome(Scanner scanner) {
        System.out.println("Qual nome deseja buscar:");
        String nome = scanner.next();

        List<Funcionario> funcionarios = funcionarioRepository.findByNome(nome);
        funcionarios.forEach(System.out::println);
    }

    private void buscaFuncionarioNomeSalarioMaiorData(Scanner scanner) {
        System.out.println("Digite o nome: ");
        String nome = scanner.next();

        System.out.println("Digite a data(dd/MM/yyyy): ");
        String data = scanner.next();
        LocalDate localDate = LocalDate.parse(data, formatter);

        System.out.println("Digite o salário: ");
        Double salario = scanner.nextDouble();

        List<Funcionario> funcionarios = funcionarioRepository.findNomeSalarioMaiorDataContratacao(nome, salario, localDate);
        funcionarios.forEach(System.out::println);
    }

    private void pesquisaFuncionarioSalario() {
        List<FuncionarioProjecao> lista = funcionarioRepository.findFuncionarioSalario();
        lista.forEach(f -> System.out.println("Funcionario: id: " + f.getId()
                + " | nome: " + f.getNome()
                + " | salario: " + f.getSalario()));
    }

}
