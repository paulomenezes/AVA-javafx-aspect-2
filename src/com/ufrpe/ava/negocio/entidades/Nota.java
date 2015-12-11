package com.ufrpe.ava.negocio.entidades;

public class Nota {
	
	private int idNota;
	private String cpfAluno;
	private int idOferta;
	private double nota1;
	private double nota2;
	private double nota3;
	private double notaFinal;
	private String nomeDisciplina;
	
	public Nota(String cpfAluno, int idOferta, double nota1, double nota2, double nota3, double notaFinal) {
		this.cpfAluno = cpfAluno;
		this.idOferta = idOferta;
		this.nota1 = nota1;
		this.nota2 = nota2;
		this.nota3 = nota3;
		this.notaFinal = notaFinal;
	}

	public Nota(){
		
	}

	public String getNomeDisciplina() {
		return nomeDisciplina;
	}
	
	public void setNomeDisciplina(String nome) {
		this.nomeDisciplina = nome;
	}
	
	public int getIdNota() {
		return idNota;
	}

	public void setIdNota(int idNota) {
		this.idNota = idNota;
	}

	public String getCpfAluno() {
		return cpfAluno;
	}


	public void setCpfAluno(String cpfAluno) {
		this.cpfAluno = cpfAluno;
	}


	public int getIdOferta() {
		return idOferta;
	}


	public void setIdOferta(int idOferta) {
		this.idOferta = idOferta;
	}


	public double getNota1() {
		return nota1;
	}


	public void setNota1(double nota1) {
		this.nota1 = nota1;
	}


	public double getNota2() {
		return nota2;
	}


	public void setNota2(double nota2) {
		this.nota2 = nota2;
	}


	public double getNota3() {
		return nota3;
	}


	public void setNota3(double nota3) {
		this.nota3 = nota3;
	}


	public double getNotaFinal() {
		return notaFinal;
	}


	public void setNotaFinal(double notaFinal) {
		this.notaFinal = notaFinal;
	}


	@Override
	public String toString() {
		return "CpfAluno : " + this.cpfAluno;
	}
	
	
	
	

	
}
