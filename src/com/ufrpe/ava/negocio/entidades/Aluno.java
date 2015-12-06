package com.ufrpe.ava.negocio.entidades;

public class Aluno extends Usuario{
	private int curso;
	private int tipoAluno; //mestrado, doutorado, graduando
	
	
	public Aluno(){
		
	}
	
	public Aluno(String nome, String cpf,String email, String senha,int grad,int tipoAluno, int curso) {
		super(cpf,nome,email, null, senha, grad);
		this.curso = curso;
		this.tipoAluno = tipoAluno;
	}
	
	public int getTipoAluno(){
		return tipoAluno;
	}
	public void setTipoAluno(int tipoAluno) {
		this.tipoAluno = tipoAluno;
	}
	
	
	public int getCurso() {
		return curso;
	}
	public void setCurso(int codCurso) {
		this.curso = codCurso;
	}
	

	
	
}
