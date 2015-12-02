package com.ufrpe.ava.negocio.entidades;

import java.util.ArrayList;

public class ProjetoPesquisa {
	private String nome;
	private String modalidade;
	private String organizacao; //cnpq, facepe etc
	private ArrayList<Aluno> alunos;
	private int codProjeto; 
	private ArrayList<Professor> professores;
	
	public ProjetoPesquisa(){
		this.alunos = new ArrayList<Aluno>();	
		this.professores = new ArrayList<Professor>();
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getModalidade() {
		return modalidade;
	}
	public void setModalidade(String modalidade) {
		this.modalidade = modalidade;
	}
	public String getOrganizacao() {
		return organizacao;
	}

	public void setOrganizacao(String organizacao) {
		this.organizacao = organizacao;
	}

	public ArrayList<Aluno> getAlunos() {
		return alunos;
	}
	public void setAlunos(ArrayList<Aluno> alunos) {
		this.alunos = alunos;
	}
	public int getCodProjeto() {
		return codProjeto;
	}
	public void setCodProjeto(int codProjeto) {
		this.codProjeto = codProjeto;
	}

	public ArrayList<Professor> getProfessores() {
		return professores;
	}

	public void setProfessores(ArrayList<Professor> professores) {
		this.professores = professores;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codProjeto;
		result = prime * result + ((modalidade == null) ? 0 : modalidade.hashCode());
		result = prime * result + ((organizacao == null) ? 0 : organizacao.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProjetoPesquisa other = (ProjetoPesquisa) obj;
		if (codProjeto != other.codProjeto)
			return false;
		if (modalidade == null) {
			if (other.modalidade != null)
				return false;
		} else if (!modalidade.equals(other.modalidade))
			return false;
		if (organizacao == null) {
			if (other.organizacao != null)
				return false;
		} else if (!organizacao.equals(other.organizacao))
			return false;
		return true;
	}
	
	
}
