package br.com.xavecoding.regesc.orm;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "professores")
public class Professor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String nome;
	
	@Column(nullable = false, unique = true)
	private String prontuario;
	
	@OneToMany(mappedBy = "professor", fetch = FetchType.LAZY)
	private List<Disciplina> disciplina;
	
	@Deprecated
	public Professor() { }
	
	public Professor(String nome, String prontuario) {
		this.nome = nome;
		this.prontuario = prontuario;
	}
	
	public Long getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getProntuario() {
		return prontuario;
	}
	
	public void setProntuario(String prontuario) {
		this.prontuario = prontuario;
	}

	@Override
	public String toString() {
		return "Professor [id=" + id + ", nome=" + nome + ", prontuario=" + prontuario + "]";
	}
	
	public List<Disciplina> getDisciplinas(){
		return disciplina;
	}
	
	public void setDisciplinas(List<Disciplina> disciplinas) {
		this.disciplina = disciplinas;
	}
}
