package com.ufrpe.ava.negocio.entidades;

import java.util.HashMap;
import java.util.Map;

public class Curso extends Entidade{
	private String nome;
	private int quantAlunos;
	//Integer � o per�odo e a Disciplina
	private Map<Integer, Disciplina> gradeDisciplina = new HashMap<Integer, Disciplina>(); 
	private Departamento departamento;
	private int codCurso;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getQuantAlunos() {
		return quantAlunos;
	}
	public void setQuantAlunos(int quantAlunos) {
		this.quantAlunos = quantAlunos;
	}
	public Map<Integer, Disciplina> getGradeDisciplina() {
		return gradeDisciplina;
	}
	public void setGradeDisciplina(Map<Integer, Disciplina> gradeDisciplina) {
		this.gradeDisciplina = gradeDisciplina;
	}
	public Departamento getDepartamento() {
		return departamento;
	}
	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}
	public int getCodCurso() {
		return codCurso;
	}
	public void setCodCurso(int codigo) {
		this.codCurso = codigo;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + codCurso;
		result = prime * result + ((departamento == null) ? 0 : departamento.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		Curso other = (Curso) obj;
		if (codCurso != other.codCurso)
			return false;
		if (departamento == null) {
			if (other.departamento != null)
				return false;
		} else if (!departamento.equals(other.departamento))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
	
	
}
