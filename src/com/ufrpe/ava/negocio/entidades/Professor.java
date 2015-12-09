package com.ufrpe.ava.negocio.entidades;

public class Professor extends Usuario{
		
	private int idDpto;
	
	public Professor(){
		
	}
	
	public Professor(String cpf,String nome ,String foto,String email, String senha,int grad,int idDpto) {
		super(cpf,nome,null, email, senha, grad);
		this.idDpto = idDpto;
	}


	public int getIdDpto() {
		return idDpto;
	}


	public void setIdDpto(int idDpto) {
		this.idDpto = idDpto;
	}
	
	
}
