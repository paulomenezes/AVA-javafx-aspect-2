package com.ufrpe.ava.negocio.entidades;

public class OfertaProfessor {
	
	private int idOferta;
	private String nomeDisciplina;
	private String cpfProfessor;
	
	
	public OfertaProfessor(int idOferta, String nomeDisciplina, String cpfProfessor) {
		this.idOferta = idOferta;
		this.nomeDisciplina = nomeDisciplina;
		this.cpfProfessor = cpfProfessor;
	}
	
	public OfertaProfessor(){
		
		
	}

	public int getIdOferta() {
		return idOferta;
	}

	public void setIdOferta(int idOferta) {
		this.idOferta = idOferta;
	}

	public String getNomeDisciplina() {
		return nomeDisciplina;
	}

	public void setNomeDisciplina(String nomeDisciplina) {
		this.nomeDisciplina = nomeDisciplina;
	}

	public String getCpfProfessor() {
		return cpfProfessor;
	}

	public void setCpfProfessor(String cpfProfessor) {
		this.cpfProfessor = cpfProfessor;
	}

	@Override
	public String toString() {
		
		return "Cod -"+ this.idOferta +" - "+this.nomeDisciplina;
	}
	
	
	

}
