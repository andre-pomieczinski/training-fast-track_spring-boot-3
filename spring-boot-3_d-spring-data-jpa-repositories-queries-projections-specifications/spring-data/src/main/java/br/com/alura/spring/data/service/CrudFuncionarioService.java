package br.com.alura.spring.data.service;

import br.com.alura.spring.data.orm.Cargo;
import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.orm.UnidadeTrabalho;
import br.com.alura.spring.data.repository.CargoRepository;
import br.com.alura.spring.data.repository.FuncionarioRepository;
import br.com.alura.spring.data.repository.UnidadeTrabalhoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class CrudFuncionarioService {
    private final FuncionarioRepository funcionarioRepository;
    private final CargoRepository cargoRepository;
    private final UnidadeTrabalhoRepository unidadeTrabalhoRepository;

    private Boolean system = true;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public CrudFuncionarioService(FuncionarioRepository funcionarioRepository, CargoRepository cargoRepository,
                                    UnidadeTrabalhoRepository unidadeTrabalhoRepository) {
        this.funcionarioRepository = funcionarioRepository;
        this.cargoRepository = cargoRepository;
        this.unidadeTrabalhoRepository = unidadeTrabalhoRepository;
    }

    public void inicial(Scanner scanner) {
        while (system) {
            System.out.println("Funcionarios, qual ação deseja executar? ");
            System.out.println("0 - Sair");
            System.out.println("1 - Cadastro");
            System.out.println("2 - Atualização");
            System.out.println("3 - Visualizar todos");
            System.out.println("4 - Excluír");
            int action = scanner.nextInt();
            switch (action) {
                case 1:
                    salvar(scanner);
                    break;
                case 2:
                    atualizar(scanner);
                    break;
                case 3:
                    visualizar(scanner);
                    break;
                case 4:
                    deletar(scanner);
                    break;
                default:
                    system = false;
                    break;
            }
        }
    }

    private void salvar(Scanner scanner) {
        System.out.println("Digite o nome: ");
        String nome = scanner.next();

        System.out.println("Digite o CPF: ");
        String cpf = scanner.next();

        System.out.println("Digite o salário: ");
        Double salario = scanner.nextDouble();

        System.out.println("Digite a data de contratação: ");
        String dataContratacao = scanner.next();

        System.out.println("Digite o código do cargo: ");
        Integer cargoId = scanner.nextInt();

        List<UnidadeTrabalho> unidades = unidade(scanner);

        Funcionario funcionario = new Funcionario();
        funcionario.setNome(nome);
        funcionario.setCpf(cpf);
        funcionario.setSalario(salario);
        funcionario.setDataContratacao(LocalDate.parse(dataContratacao, formatter));
        Cargo cargo = cargoRepository.findById(cargoId).get();
        funcionario.setCargo(cargo);
        funcionario.setUnidadeTrabalhos(unidades);
        funcionarioRepository.save(funcionario);
        System.out.println("Funcionário cadastrado com sucesso!");
    }

    private List<UnidadeTrabalho> unidade(Scanner scanner) {
        Boolean isTrue = true;
        List<UnidadeTrabalho> unidades = new ArrayList<>();

        while (isTrue) {
            System.out.println("Digite o código da unidade (P/ sair digite 0): ");
            Integer unidadeId = scanner.nextInt();
            if (unidadeId != 0) {
                Optional<UnidadeTrabalho> unidade = unidadeTrabalhoRepository.findById(unidadeId);
                unidades.add(unidade.get());
            } else {
                isTrue = false;
            }
        }
        return unidades;
    }

    private void atualizar(Scanner scanner) {
        System.out.println("Digite o código: ");
        Integer id = scanner.nextInt();

        System.out.println("Digite o nome: ");
        String nome = scanner.next();

        System.out.println("Digite o CPF: ");
        String cpf = scanner.next();

        System.out.println("Digite o salário: ");
        Double salario = scanner.nextDouble();

        System.out.println("Digite a data de contratação: ");
        String dataContratacao = scanner.next();

        System.out.println("Digite o código do cargo: ");
        Integer cargoId = scanner.nextInt();

        Funcionario funcionario = new Funcionario();
        funcionario.setId(id);
        funcionario.setNome(nome);
        funcionario.setCpf(cpf);
        funcionario.setSalario(salario);
        funcionario.setDataContratacao(LocalDate.parse(dataContratacao, formatter));
        Cargo cargo = cargoRepository.findById(cargoId).get();
        funcionario.setCargo(cargo);
        funcionarioRepository.save(funcionario);
        System.out.println("Funcionário atualizado com sucesso!");
    }

    private void deletar(Scanner scanner) {
        System.out.println("Digite o código do funcionário a ser excluído: ");
        int id = scanner.nextInt();
        funcionarioRepository.deleteById(id);
        System.out.println("Funcionário excluído com sucesso!");
    }

    private void visualizar(Scanner scanner) {
        System.out.println("Qual página você deseja visualizar?");
        Integer page = scanner.nextInt();

        Pageable pegeable = PageRequest.of(page, 2, Sort.by(Sort.Direction.ASC, "nome"));
        Page<Funcionario> funcionarios = funcionarioRepository.findAll(pegeable);

        System.out.println(funcionarios);
        System.out.println("Pagina atual " + funcionarios.getNumber());
        System.out.println("Total elementos " + funcionarios.getTotalElements());
        funcionarios.forEach(funcionario -> System.out.println(funcionario));
    }
}
