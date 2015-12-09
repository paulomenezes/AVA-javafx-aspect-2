package com.ufrpe.ava.negocio.entidades;

import java.util.HashMap;
import java.util.Map;

public class OfertaDisciplina {
	private int idOferta;
	private int quantAlunos;
	private Disciplina disciplina;
	private int ano;
	private int semestre;
	private Professor professor; // que ministra a disciplina
	//private Map<Aluno, Nota> alunosMatriculados_Situacao = new HashMap<Aluno, Nota>();
	private Curso curso; //uma oferta disciplina pode ser criada em diferentes cursos com o mesmo parametro
	//facilita na pesquisa por hist�rico
	private Map<String, Integer> horario = new HashMap<String, Integer>();
	
	
	
	public int getCodOferta() {
		return idOferta;
	}
	public void setCodOferta(int idOferta) {
		this.idOferta = idOferta;
	}
	public int getQuantAlunos() {
		return quantAlunos;
	}
	public void setQuantAlunos(int quantAlunos) {
		this.quantAlunos = quantAlunos;
	}
	public Disciplina getDisciplina() {
		return disciplina;
	}
	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}
	//provavelmente n�o � necess�rio, a confirmar
	/*
	public Map<Aluno, Nota> getAlunosMatriculados_Situacao() {
		return alunosMatriculados_Situacao;
	}
	public void setAlunosMatriculados_Situacao(Map<Aluno, Nota> alunosMatriculados_Situacao) {
		this.alunosMatriculados_Situacao = alunosMatriculados_Situacao;
	}
	*/
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	public int getSemestre() {
		return semestre;
	}
	public void setSemestre(int semestre) {
		this.semestre = semestre;
	}
	public Professor getProfessor() {
		return professor;
	}
	public void setProfessor(Professor professor) {
		this.professor = professor;
	}
	public Curso getCurso() {
		return curso;
	}
	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public Map<String, Integer> getHorario() {
		return horario;
	}
	public void setHorario(Map<String, Integer> horario) {
		this.horario = horario;
	}
	
}
