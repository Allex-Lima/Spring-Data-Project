package br.com.xavecoding.regesc;

import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.xavecoding.regesc.orm.Professor;
import br.com.xavecoding.regesc.repository.ProfessorRepository;
import br.com.xavecoding.regesc.service.CrudProfessorService;


@SpringBootApplication
public class RegescApplication implements CommandLineRunner{
	
	private CrudProfessorService professorService;
	
	// Os objetos passado por parametro são injetados automaticamente pelo Spring
	//Porque suas classes possuem a anotações @Service
	public RegescApplication(CrudProfessorService professorService) {
		this.professorService = professorService;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(RegescApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		Boolean isTrue = true;
		Scanner scanner = new Scanner(System.in);
		
		while(isTrue) {
			System.out.println("Qual a entidade você deseja interagir? ");
			System.out.println("0 - Sair");
			System.out.println("1 - Professor");
			
			int opcao = scanner.nextInt();
			
			switch(opcao) {
			case 1:
				this.professorService.menu(scanner);
				break;
			default:
				isTrue = false;
				break;
			}
		}
	}

}
