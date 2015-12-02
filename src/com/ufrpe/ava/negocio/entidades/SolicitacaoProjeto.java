package com.ufrpe.ava.negocio.entidades;

public class SolicitacaoProjeto extends Entidade{
	private int codSolicitacao;
	private Aluno aluno;
	private ProjetoPesquisa projeto;
	private boolean estado;
	public int getCodSolicitacao() {
		return codSolicitacao;
	}
	public void setCodSolicitacao(int codSolicitacao) {
		this.codSolicitacao = codSolicitacao;
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((aluno == null) ? 0 : aluno.hashCode());
		result = prime * result + codSolicitacao;
		result = prime * result + (estado ? 1231 : 1237);
		result = prime * result + ((projeto == null) ? 0 : projeto.hashCode());
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
		SolicitacaoProjeto other = (SolicitacaoProjeto) obj;
		if (aluno == null) {
			if (other.aluno != null)
				return false;
		} else if (!aluno.equals(other.aluno))
			return false;
		if (codSolicitacao != other.codSolicitacao)
			return false;
		if (estado != other.estado)
			return false;
		if (projeto == null) {
			if (other.projeto != null)
				return false;
		} else if (!projeto.equals(other.projeto))
			return false;
		return true;
	}
	
	
}
