package com.ufrpe.ava.negocio.entidades;

import java.util.HashMap;
import java.util.Map;

public class OfertaDisciplina {
	private int codOferta;
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
		return codOferta;
	}
	public void setCodOferta(int codOferta) {
		this.codOferta = codOferta;
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ano;
		result = prime * result + ((curso == null) ? 0 : curso.hashCode());
		result = prime * result + ((disciplina == null) ? 0 : disciplina.hashCode());
		result = prime * result + quantAlunos;
		result = prime * result + semestre;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		OfertaDisciplina other = (OfertaDisciplina) obj;
		if (ano != other.ano)
			return false;
		if (curso == null) {
			if (other.curso != null)
				return false;
		} else if (!curso.equals(other.curso))
			return false;
		if (disciplina == null) {
			if (other.disciplina != null)
				return false;
		} else if (!disciplina.equals(other.disciplina))
			return false;
		if (quantAlunos != other.quantAlunos)
			return false;
		if (semestre != other.semestre)
			return false;
		if (horario == null) {
			if (other.horario != null)
				return false;
		} else if (!horario.equals(other.horario))
			return false;
		return true;
	}
	
	
}
