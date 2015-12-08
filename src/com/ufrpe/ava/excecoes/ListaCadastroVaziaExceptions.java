package com.ufrpe.ava.excecoes;

@SuppressWarnings("serial")
public class ListaCadastroVaziaExceptions extends Exception {
	
private String descricao;
	
	public ListaCadastroVaziaExceptions(String descricao){
		
		super("Não foi encontrada nenhuma opcao de "+descricao);
		this.descricao = descricao;
		
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
