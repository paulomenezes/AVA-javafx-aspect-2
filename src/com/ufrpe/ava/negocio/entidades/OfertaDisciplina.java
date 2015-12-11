package com.ufrpe.ava.negocio.entidades;

public class OfertaDisciplina {
	private int idOferta;
	private int idDisciplina;
	private int idCurso;
	private int qtdAlunos;
	private int ano;
	private int semestre;
	private String nomeDisciplina;
	private String nomeCurso;
	

	public OfertaDisciplina(int idDisciplina, int idCurso, int qtdAlunos, int ano, int semestre) {
		super();
		this.idDisciplina = idDisciplina;
		this.idCurso = idCurso;
		this.qtdAlunos = qtdAlunos;
		this.ano = ano;
		this.semestre = semestre;
	}
	
	public OfertaDisciplina(){
		
		
	}


	public int getIdOferta() {
		return idOferta;
	}

	public void setIdOferta(int idOferta) {
		this.idOferta = idOferta;
	}

	public int getIdDisciplina() {
		return idDisciplina;
	}

	public void setIdDisciplina(int idDisciplina) {
		this.idDisciplina = idDisciplina;
	}

	public int getIdCurso() {
		return idCurso;
	}

	public void setIdCurso(int idCurso) {
		this.idCurso = idCurso;
	}

	public int getQtdAlunos() {
		return qtdAlunos;
	}

	public void setQtdAlunos(int qtdAlunos) {
		this.qtdAlunos = qtdAlunos;
	}

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
	
	public String getNomeDisciplina() {
		return nomeDisciplina;
	}

	public void setNomeDisciplina(String nomeDisciplina) {
		this.nomeDisciplina = nomeDisciplina;
	}

	public String getNomeCurso() {
		return nomeCurso;
	}

	public void setNomeCurso(String nomeCurso) {
		this.nomeCurso = nomeCurso;
	}

	@Override
	public String toString() {
		return idOferta +"-"+  this.nomeDisciplina;
	}
}
