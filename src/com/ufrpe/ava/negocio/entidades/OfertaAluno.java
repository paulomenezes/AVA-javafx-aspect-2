package com.ufrpe.ava.negocio.entidades;

public class OfertaAluno {
	
	private int idOferta;
	private String cpfAluno;
	private String nome;
	
	
	public OfertaAluno(int idOferta, String cpfAluno, String nome) {
		
		this.idOferta = idOferta;
		this.cpfAluno = cpfAluno;
		this.nome = nome;
	}

	public OfertaAluno(){
		
		
	}

	public int getIdOferta() {
		return idOferta;
	}


	public void setIdOferta(int idOferta) {
		this.idOferta = idOferta;
	}


	public String getCpfAluno() {
		return cpfAluno;
	}


	public void setCpfAluno(String cpfAluno) {
		this.cpfAluno = cpfAluno;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	@Override
	public String toString() {
		return "Nome :" + nome;
	}
	
	
	

}
