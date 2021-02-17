package br.com.xavecoding.regesc.service;

import java.util.Optional;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.xavecoding.regesc.orm.Disciplina;
import br.com.xavecoding.regesc.orm.Professor;
import br.com.xavecoding.regesc.repository.DisciplinaRepository;
import br.com.xavecoding.regesc.repository.ProfessorRepository;

@Service
public class CrudDisciplinaService {

	private DisciplinaRepository disciplinaRepository;
	private ProfessorRepository professorRepository;

	public CrudDisciplinaService(DisciplinaRepository disciplinaRepository, 
								 ProfessorRepository professorRepository) {

		this.disciplinaRepository = disciplinaRepository;
		this.professorRepository = professorRepository;
	}

	public void menu(Scanner scanner) {

		Boolean  isTrue = true;

		while(isTrue) {
			System.out.println("\nQual a opção que você quer executar?");
			System.out.println("0 - Voltar ao menu anterior");
			System.out.println("1 - Cadastrar nova disciplina");
			System.out.println("2 - Atualizar uma disciplina");
			System.out.println("3 - Visualizar todas as disciplina");
			System.out.println("4 - Deletar uma disciplina");

			int opcao = scanner.nextInt();

			switch(opcao) {
				case 1:
					this.cadastrar(scanner);
					break;
				case 2:
					this.atualizar(scanner);
					break;
				case 3:
					this.visualizar();
					break;
				case 4:
					this.deletar(scanner);
					break;
				default:
					isTrue = false;
					break;
			}
		}
		System.out.println();
	}
	
	public void cadastrar(Scanner scanner) {
		System.out.print("Nome da disciplina: ");
		String nome = scanner.next();
		
		System.out.print("Semestre: ");
		Integer semestre = scanner.nextInt();
		
		System.out.print("Professor ID: ");
		Long professorId = scanner.nextLong();
		
		Optional<Professor> optional = professorRepository.findById(professorId);
		
		if(optional.isPresent()) {
			Professor professor = optional.get();
			Disciplina disciplina = new Disciplina(nome, semestre, professor);
			disciplinaRepository.save(disciplina);
			System.out.println("Disciplina Salva!");
		} else {
			System.out.println("Professor ID " + professorId + " inválido.");
		}
	}
	
	private void atualizar(Scanner scanner) {
		System.out.print("Digite o id da Disciplina a ser atualizada: ");
		Long id = scanner.nextLong();
		
		Optional<Disciplina> optionalDisciplina = this.disciplinaRepository.findById(id);
		
		if(optionalDisciplina.isPresent()) {
			Disciplina disciplina = optionalDisciplina.get();
			
			System.out.print("Nome da disciplina: ");
			String nome = scanner.next();
			
			System.out.print("Semestre: ");
			Integer semestre = scanner.nextInt();
			
			System.out.print("Professor ID: ");
			Long professorId = scanner.nextLong();
			
			Optional<Professor> optionalProfessor = this.professorRepository.findById(professorId);
			
			if(optionalProfessor.isPresent()) {
				Professor professor = optionalProfessor.get();
				
				disciplina.setNome(nome);
				disciplina.setSemestre(semestre);
				disciplina.setProfessor(professor);
				
				disciplinaRepository.save(disciplina);
				System.out.println("Atualizado com sucesso!!!");
			} else {
				System.out.println("Professor ID " + professorId + " inválido!");
			}
		} else {
			System.out.println("O id da disciplina informado " + id + " Inválido!");
		}
		
	}
	
	private void visualizar() {
		
		Iterable<Disciplina> disciplinas = this.disciplinaRepository.findAll();
		
		for(Disciplina disciplina : disciplinas) {
			System.out.println(disciplina);
		}
		System.out.println();
	}
	
	private void deletar(Scanner scanner) {
		System.out.println("Id");
		Long id = scanner.nextLong();
		
		// Lançará uma exception se não encontrar o Id passado na tabela
		this.disciplinaRepository.deleteById(id);
		System.out.println("Disciplina deletada.\n");
	}
}
