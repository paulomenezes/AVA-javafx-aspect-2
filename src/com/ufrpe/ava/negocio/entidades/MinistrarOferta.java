package com.ufrpe.ava.negocio.entidades;

public class MinistrarOferta {

	private String cpfProfessor;
	private int idOferta;
	
	
	public MinistrarOferta(String cpfProfessor, int idOferta) {
		this.cpfProfessor = cpfProfessor;
		this.idOferta = idOferta;
	}


	public String getCpfProfessor() {
		return cpfProfessor;
	}


	public void setCpfProfessor(String cpfProfessor) {
		this.cpfProfessor = cpfProfessor;
	}


	public int getIdOferta() {
		return idOferta;
	}


	public void setIdOferta(int idOferta) {
		this.idOferta = idOferta;
	}
	
	
	
	
}
