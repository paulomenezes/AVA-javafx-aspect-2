package com.ufrpe.ava.negocio.entidades;

public class DisciplinaDisponivel {

	private String nome;
	private  int cargaHoraria;
	private String nomeProfessor;
	
	public DisciplinaDisponivel(String nome, int cargaHoraria,String nomeProfessor) {
		
		this.nome = nome;
		this.cargaHoraria = cargaHoraria;
		this.nomeProfessor = nomeProfessor; 
	}
	
	public DisciplinaDisponivel(){
		
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getCargaHoraria() {
		return cargaHoraria;
	}

	public void setCargaHoraria(int cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}

	public String getNomeProfessor() {
		return nomeProfessor;
	}

	public void setNomeProfessor(String nomeProfessor) {
		this.nomeProfessor = nomeProfessor;
	}

	@Override
	public String toString() {
		
		return "Nome da Disciplina : " + nome + "\n"+  "CargaHoraria : " + cargaHoraria +" horas"+ "\n Nome do professor : "
				+ nomeProfessor;
	}
	
	
	
}
