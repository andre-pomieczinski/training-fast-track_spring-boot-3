package br.com.alura.spring.data.service;

import br.com.alura.spring.data.orm.Cargo;
import br.com.alura.spring.data.orm.UnidadeTrabalho;
import br.com.alura.spring.data.repository.CargoRepository;
import br.com.alura.spring.data.repository.UnidadeTrabalhoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

@Service
public class CrudUnidadeTrabalhoService {
    private UnidadeTrabalhoRepository unidadeTrabalhoRepository;

    private Boolean system = true;

    public CrudUnidadeTrabalhoService(UnidadeTrabalhoRepository unidadeTrabalhoRepository) {
        this.unidadeTrabalhoRepository = unidadeTrabalhoRepository;
    }

    public void inicial(Scanner scanner) {
        while (system) {
            System.out.println("Unidade de trabalhos, qual ação deseja executar? ");
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
        System.out.println("Digite a descrição: ");
        String descricao = scanner.next();
        System.out.println("Digite o endereço: ");
        String endereco = scanner.next();
        UnidadeTrabalho unidadeTrabalho = new UnidadeTrabalho();
        unidadeTrabalho.setDescricao(descricao);
        unidadeTrabalho.setEndereco(endereco);
        unidadeTrabalhoRepository.save(unidadeTrabalho);
        System.out.println("Unidade de trabalho cadastrado com sucesso!");
    }

    private void atualizar(Scanner scanner) {
        System.out.println("Digite o código da unidade a ser atualizado: ");
        int id = scanner.nextInt();
        System.out.println("Digite a nova descrição: ");
        String descricao = scanner.next();
        System.out.println("Digite o novo endereço: ");
        String endereco = scanner.next();
        UnidadeTrabalho unidadeTrabalho = new UnidadeTrabalho();
        unidadeTrabalho.setId(id);
        unidadeTrabalho.setDescricao(descricao);
        unidadeTrabalho.setEndereco(endereco);
        unidadeTrabalhoRepository.save(unidadeTrabalho);
        System.out.println("Unidade de trabalho atualizado com sucesso!");
    }

    private void deletar(Scanner scanner) {
        System.out.println("Digite o código da unidade a ser excluído: ");
        int id = scanner.nextInt();
        unidadeTrabalhoRepository.deleteById(id);
        System.out.println("Unidade de trabalho excluído com sucesso!");
    }

    private void visualizar() {
        List<UnidadeTrabalho> unidadeTrabalhos = unidadeTrabalhoRepository.findAll();
        unidadeTrabalhos.forEach(unidade -> System.out.println(unidade));
    }
}
