package com.ufrpe.ava.negocio.entidades;

public class Matricular {

	
	private String cpfAluno;
	private int IdOferta;
	private String dataMatricula;
	private String numProtocolo;
	

	public Matricular(String cpfAluno, int idOferta, String dataMatricula, String numProtocolo) {
		
		this.cpfAluno = cpfAluno;
		IdOferta = idOferta;
		this.dataMatricula = dataMatricula;
		this.numProtocolo = numProtocolo;
	}
	
	public Matricular(){
		
	}
	
	
	public String getCpfAluno() {
		return cpfAluno;
	}

	public void setCpfAluno(String cpfAluno) {
		this.cpfAluno = cpfAluno;
	}

	public int getIdOferta() {
		return IdOferta;
	}

	public void setIdOferta(int idOferta) {
		IdOferta = idOferta;
	}

	public String getDataMatricula() {
		return dataMatricula;
	}

	public void setDataMatricula(String dataMatricula) {
		this.dataMatricula = dataMatricula;
	}

	public String getNumProtocolo() {
		return numProtocolo;
	}

	public void setNumProtocolo(String numProtocolo) {
		this.numProtocolo = numProtocolo;
	}
}
