package com.ufrpe.ava.negocio.entidades;

import java.util.ArrayList;

public class Disciplina {
	private int idDisciplina;
	private String nome;
	private int cargaHoraria;
	private int creditos;
	private String tipo; //n�o sei o que �
	//String = dia, Integer = hor�rio. Uma disciplina pode ter mais de um hor�rio
	private ArrayList<Disciplina> preRequisitos = new ArrayList<Disciplina>();
	
	public int getCodDisciplina() {
		return idDisciplina;
	}
	public void setCodDisciplina(int idDisciplina) {
		this.idDisciplina = idDisciplina;
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
	public int getCreditos() {
		return creditos;
	}
	public void setCreditos(int creditos) {
		this.creditos = creditos;
	}
	public ArrayList<Disciplina> getPreRequisitos() {
		return preRequisitos;
	}
	public void setPreRequisitos(ArrayList<Disciplina> preRequisitos) {
		this.preRequisitos = preRequisitos;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	
	
}
