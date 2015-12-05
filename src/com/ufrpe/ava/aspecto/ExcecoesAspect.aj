package com.ufrpe.ava.aspecto;

import java.sql.SQLException;

import com.ufrpe.ava.excecoes.ListaCadastroVaziaExceptions;
import com.ufrpe.ava.excecoes.ObjetoNaoExistenteExcepitions;
import com.ufrpe.ava.util.Alertas;

public aspect ExcecoesAspect {

	
	pointcut selecionarTabelas() : call(* com.ufrpe.ava.negocio.controladores.ControladorCurso.selecionar*());
	pointcut removerExcecao() : call(* *.remover*(..));
	pointcut loginExcecao() : call(* com.ufrpe.ava.negocio.controladores.ControladorUsuario.buscarLogin(..));
	pointcut conexaoFalha() : execution(* ConexaoMySQL.getConnection());
	
	pointcut insercaoProfessor() : execution(* com.ufrpe.ava.negocio.AvaFachada.cadastrarProfessor(..));
	pointcut inserirAluno() : execution(* com.ufrpe.ava.negocio.AvaFachada.cadastrarAluno(..));
	
	
	
	after()throwing (SQLException e) : selecionarTabelas() || removerExcecao() || loginExcecao(){
		
		Alertas.falhaConexaoBanco();
		
	}
	
	after()throwing(Exception e): conexaoFalha(){
		
		Alertas.falhaConexaoBanco();
	}
	
	after()throwing(ListaCadastroVaziaExceptions e): selecionarTabelas(){
		
		Alertas.tabelaVazia();
	}
	
	after()throwing(ObjetoNaoExistenteExcepitions e) : loginExcecao(){
		
		Alertas.ObjetoNaoExiste(e.getMessage());
	}
	
	after()throwing(SQLException e): insercaoProfessor() || inserirAluno(){
		
		Alertas.ObjetoJaExiste("Usuario já existe");
	}
}
