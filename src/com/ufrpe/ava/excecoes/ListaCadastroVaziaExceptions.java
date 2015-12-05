package com.ufrpe.ava.excecoes;

@SuppressWarnings("serial")
public class ListaCadastroVaziaExceptions extends Exception {

	public ListaCadastroVaziaExceptions(){
		
		super("Não existe Cadastros efetuados no momento.");
	}
}
