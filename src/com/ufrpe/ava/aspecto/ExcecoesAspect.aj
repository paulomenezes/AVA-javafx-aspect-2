package com.ufrpe.ava.aspecto;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.ufrpe.ava.AVA;
import com.ufrpe.ava.excecoes.ListaCadastroVaziaExceptions;
import com.ufrpe.ava.excecoes.ObjetoNaoExistenteExcepitions;
import com.ufrpe.ava.util.Alertas;

public aspect ExcecoesAspect {

	// POINTCUTS ---------------------------------------------------------------------------------------------------------------- 
	pointcut selecionarControladorCurso() : call(* com.ufrpe.ava.negocio.controladores.ControladorCurso.selecionar*());
	pointcut matriculaDisponivel() : call(* com.ufrpe.ava.negocio.controladores.ControladorCurso.disciplinasDisponiveis(..));
	pointcut loginExcecao() : execution(* *.buscarLogin(..));
	
	
	pointcut removerExcecao() : call(* *.remover*(..));
	pointcut conexaoFalha() : call(* DriverManager.getConnection(..));
	
	pointcut insercaoProfessor() : execution(* com.ufrpe.ava.negocio.AvaFachada.cadastrarProfessor(..));
	pointcut inserirAluno() : execution(* com.ufrpe.ava.negocio.AvaFachada.cadastrarAluno(..));
	pointcut matricularAluno() : 
		call(* com.ufrpe.ava.negocio.controladores.ControladorUsuario.matricularAluno(..));
    
	
	//ADVICES ---------------------------------------------------------------------------------------------------------------------
	
	after()throwing (SQLException e) : selecionarControladorCurso() || removerExcecao() || loginExcecao() || matriculaDisponivel(){
		
		Alertas.falhaConexaoBanco();
		
	}
	
	after()throwing : conexaoFalha(){
		
		Alertas.falhaCredencialBanco();
		AVA.sStage.close();
	}
	
	after()throwing(ListaCadastroVaziaExceptions e): selecionarControladorCurso() || matriculaDisponivel(){
		
		Alertas.tabelaVazia();
	}
	
	after()throwing(ObjetoNaoExistenteExcepitions e) : loginExcecao(){
		
		Alertas.ObjetoNaoExiste(e.getMessage());
	}
	
	after()throwing(SQLException e): insercaoProfessor() || inserirAluno(){
		
		Alertas.ObjetoJaExiste("Usuario já existe");
	}
	
	after()throwing(SQLException e) : matricularAluno(){
		
		Alertas.ObjetoJaExiste("Essa Matricula já foi feita");
	}
}
