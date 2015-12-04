package com.ufrpe.ava.negocio.entidades;

import java.util.Arrays;

public class Nota {
	private int[] notas = new int[4];//1�,2�,3�, final
	private String situacao;
	private Aluno aluno;
	private OfertaDisciplina oferta;
	private int codNota;
	
	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public OfertaDisciplina getOferta() {
		return oferta;
	}

	public void setOferta(OfertaDisciplina oferta) {
		this.oferta = oferta;
	}

	public int[] getNotas() {
		return notas;
	}

	public String getSituacao() {
		return situacao;
	}
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	
	public int getCodNota() {
		return codNota;
	}

	public void setCodNota(int codNota) {
		this.codNota = codNota;
	}
	public void setarNota(int indice, int nota){
		this.notas[indice] = nota;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + codNota;
		result = prime * result + Arrays.hashCode(notas);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Nota other = (Nota) obj;
		if (codNota != other.codNota)
			return false;
		if (!Arrays.equals(notas, other.notas))
			return false;
		return true;
	}
	
	
}
