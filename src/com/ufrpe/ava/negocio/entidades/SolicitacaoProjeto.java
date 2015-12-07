package com.ufrpe.ava.negocio.entidades;

public class SolicitacaoProjeto {
	private int idSolicitacao;
	private Aluno aluno;
	private ProjetoPesquisa projeto;
	private boolean estado;
	public int getCodSolicitacao() {
		return idSolicitacao;
	}
	public void setCodSolicitacao(int idSolicitacao) {
		this.idSolicitacao = idSolicitacao;
	}
	public Aluno getAluno() {
		return aluno;
	}
	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	public ProjetoPesquisa getProjeto() {
		return projeto;
	}
	public void setProjeto(ProjetoPesquisa projeto) {
		this.projeto = projeto;
	}
	public boolean isEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	
}
