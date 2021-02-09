package br.com.xavecoding.regesc.service;

import java.util.Optional;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.xavecoding.regesc.orm.Professor;
import br.com.xavecoding.regesc.repository.ProfessorRepository;

@Service
public class CrudProfessorService {
	
	// Depedência da classe CrudProfessorService
	private ProfessorRepository professorRepository;
	
	// O Spring automaticamente cria um objeto com a interface 'ProfessorRepository',
	// e o injeta para nós no construtor da classe atual. ==> Injeção de Dependência 
	public CrudProfessorService(ProfessorRepository professorRepository) {
		this.professorRepository = professorRepository;
	}
	
	public void menu(Scanner scanner) {
		Boolean isTrue = true;

		while(isTrue) {
			System.out.println("\nQual ação você deseja executar? ");
			System.out.println("0 - Voltar ao menu anterior");
			System.out.println("1 - Cadastrar novo Professor");
			System.out.println("2 - Atualizar um Professor");

			int opcao = scanner.nextInt();

			switch(opcao) {
			case 1:
				this.cadastrar(scanner);
				break;
			case 2:
				this.atualizar(scanner);
				break;
			default:
				isTrue = false;
				break;
			}
		}
		System.out.println();
	}
	
	private void cadastrar(Scanner scanner) {
		
		System.out.print("Digite o nome do professor: ");
		String nome = scanner.next();
		
		System.out.print("Digite o prontuario do professor: ");
		String prontuario = scanner.next();
		
		Professor professor = new Professor(nome, prontuario);
		this.professorRepository.save(professor);
		System.out.println("Professor salvo no Banco!!!\n");
	}
	
	private void atualizar(Scanner scanner) {
		System.out.print("Digite o Id do professor a ser atualizado: ");
		Long id = scanner.nextLong();
		
		Optional<Professor> optional = this.professorRepository.findById(id);
		
		if(optional.isPresent()) {
			
			System.out.print("Digite o nome do professor: ");
			String nome = scanner.next();
			
			System.out.print("Digite o prontuario do professor: ");
			String prontuario = scanner.next();
			
			Professor professor = optional.get();
			
			professor.setNome(nome);
			professor.setProntuario(prontuario);
			
			// atualiza (persiste) o objeto/registro/tupla no BD
			professorRepository.save(professor);
			System.out.println("Professor atualizado com sucesso!!!\n");
			
		} else {
			System.out.println("O Id " + id + " é invalido.");
		} 
	}
}
