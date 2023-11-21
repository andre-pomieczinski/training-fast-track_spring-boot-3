package br.com.alura.spring.data;

import br.com.alura.spring.data.orm.Cargo;
import br.com.alura.spring.data.repository.CargoRepository;
import br.com.alura.spring.data.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.repository.CrudRepository;

import java.util.Scanner;

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {

	private final CrudCargoService crudCargoService;
	private final CrudUnidadeTrabalhoService crudUnidadeTrabalhoService;
	private final CrudFuncionarioService crudFuncionarioService;

	@Autowired
	private RelatoriosService relatoriosService;

	@Autowired
	private RelatorioFuncionarioDinamico relatorioFuncionarioDinamico;

	private Boolean system = true;

	public SpringDataApplication(CrudCargoService crudCargoService, CrudUnidadeTrabalhoService crudUnidadeTrabalhoService,
								 CrudFuncionarioService crudFuncionarioService){
		this.crudCargoService = crudCargoService;
		this.crudUnidadeTrabalhoService = crudUnidadeTrabalhoService;
		this.crudFuncionarioService = crudFuncionarioService;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringDataApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in);

		while (system) {
			System.out.println("Qual ação você deseja executar?");
			System.out.println("0 - Sair");
			System.out.println("1 - Cargos");
			System.out.println("2 - Unidade de trabalhos");
			System.out.println("3 - Funcionários");
			System.out.println("4 - Relatórios");
			System.out.println("5 - Relatórios funcionario dinâmico");

			int action = scanner.nextInt();
			switch (action) {
				case 1:
					crudCargoService.inicial(scanner);
					break;
				case 2:
					crudUnidadeTrabalhoService.inicial(scanner);
					break;
				case 3:
					crudFuncionarioService.inicial(scanner);
					break;
				case 4:
					relatoriosService.inicial(scanner);
					break;
				case 5:
					relatorioFuncionarioDinamico.inicial(scanner);
					break;
				default:
					system = false;
					break;
			}

		}


	}
}
