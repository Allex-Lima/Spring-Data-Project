package br.com.xavecoding.regesc;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.xavecoding.regesc.orm.Professor;
import br.com.xavecoding.regesc.repository.ProfessorRepository;


@SpringBootApplication
public class RegescApplication implements CommandLineRunner{
	
	private ProfessorRepository pr;
	
	public RegescApplication(ProfessorRepository pr) {
		this.pr = pr;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(RegescApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		Professor professor = new Professor("Alex", "xyz");
		
		System.out.println("Professor ANTES do save!");
		System.out.println(professor);
		
		this.pr.save(professor);
		
		System.out.println("Professor DEPOIS do save!");
		System.out.println(professor);
	}

}
