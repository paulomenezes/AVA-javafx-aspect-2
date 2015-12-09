package com.ufrpe.ava.negocio.entidades;

public class Aluno extends Usuario{
	
	
	private int curso;

	public Aluno(){
		
	}
	
	public Aluno(String cpf,String nome,String foto, String email, String senha,int grad, int curso) {
		super(cpf,nome,null, email, senha, grad);
		this.curso = curso;
	}	
	
	public int getCurso() {
		return curso;
	}
	public void setCurso(int codCurso) {
		this.curso = codCurso;
	}
	

	
	
}
