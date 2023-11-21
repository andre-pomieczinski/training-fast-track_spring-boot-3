package br.com.alura.spring.data.service;

import br.com.alura.spring.data.orm.Cargo;
import br.com.alura.spring.data.repository.CargoRepository;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

@Service
public class CrudCargoService {

    private CargoRepository cargoRepository;

    private Boolean system = true;

    public CrudCargoService(CargoRepository cargoRepository) {
        this.cargoRepository = cargoRepository;
    }

    public void inicial(Scanner scanner) {
        while (system) {
            System.out.println("Qual ação você deseja executar? ");
            System.out.println("0 - Sair");
            System.out.println("1 - Cadastro de novo cargo");
            System.out.println("2 - Atualização de cargo");
            System.out.println("3 - Visualizar cargos");
            System.out.println("4 - Excluír cargos");
            int action = scanner.nextInt();
            switch (action) {
                case 1:
                    salvar(scanner);
                    break;
                case 2:
                    atualizar(scanner);
                    break;
                case 3:
                    visualizar();
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
        System.out.println("Digite a descrição do novo cargo: ");
        String descricao = scanner.next();
        Cargo cargo = new Cargo();
        cargo.setDescricao(descricao);
        cargoRepository.save(cargo);
        System.out.println("Cargo cadastrado com sucesso!");
    }

    private void atualizar(Scanner scanner) {
        System.out.println("Digite o código do cargo a ser atualizado: ");
        int id = scanner.nextInt();
        System.out.println("Digite a nova descrição deste cargo: ");
        String descricao = scanner.next();
        Cargo cargo = new Cargo();
        cargo.setId(id);
        cargo.setDescricao(descricao);
        cargoRepository.save(cargo);
        System.out.println("Cargo atualizado com sucesso!");
    }

    private void deletar(Scanner scanner) {
        System.out.println("Digite o código do cargo a ser excluído: ");
        int id = scanner.nextInt();
        cargoRepository.deleteById(id);
        System.out.println("Cargo excluído com sucesso!");
    }

    private void visualizar() {
        List<Cargo> cargos = cargoRepository.findAll();
        cargos.forEach(cargo -> System.out.println(cargo));
    }

}
