package com.ufrpe.ava.aspecto;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.ufrpe.ava.AVA;
import com.ufrpe.ava.excecoes.ListaCadastroVaziaExceptions;
import com.ufrpe.ava.excecoes.ObjetoNaoExistenteExcepitions;
import com.ufrpe.ava.negocio.entidades.Matricular;
import com.ufrpe.ava.util.Alertas;

public aspect ExcecoesAspect {

	// POINTCUTS ---------------------------------------------------------------------------------------------------------------- 
	pointcut selecionarControladorCurso() : call(* com.ufrpe.ava.negocio.controladores.ControladorCurso.selecionar*());
	pointcut selecionarControladorUsuarios(): execution(* com.ufrpe.ava.negocio.controladores.ControladorUsuario.selecionar*());
	pointcut matriculaDisponivel() : call(* *.disciplinasDisponiveis(..));
	pointcut selecionarDisciplinas(): execution(* com.ufrpe.ava.negocio.controladores.ControladorDisciplina.selecionarDisciplinas(..));
	pointcut loginExcecao() : execution(* *.buscarLogin(..));
	
	
	pointcut removerExcecao() : call(* *.remover*(..));
	pointcut conexaoFalha() : call(* DriverManager.getConnection(..));
	
	pointcut insercaoProfessor() : execution(* com.ufrpe.ava.negocio.AvaFachada.cadastrarProfessor(..));
	pointcut inserirAluno() : execution(* com.ufrpe.ava.negocio.AvaFachada.cadastrarAluno(..));
	pointcut matricularAluno(Matricular matricula) : 
		call(* com.ufrpe.ava.negocio.controladores.ControladorUsuario.matricularAluno(..)) && args(matricula);
	
	pointcut cadastrarDepartamento(String nome): execution(* com.ufrpe.ava.negocio.controladores.ControladorCurso.cadastrarDepartamento(..)) && args(nome);
	pointcut cadastrarDisciplina(): call(* com.ufrpe.ava.negocio.controladores.ControladorDisciplina.cadastrarDisciplina(..));
	pointcut cadastrarCurso(): call(* com.ufrpe.ava.negocio.controladores.ControladorCurso.cadastrarCurso(..));
	pointcut cadastrarProjeto(): call(* com.ufrpe.ava.negocio.controladores.ControladorProjetoPesquisa.cadastrarProjetoPesquisa(..));
	
	//ADVICES ---------------------------------------------------------------------------------------------------------------------
	
	after()throwing (SQLException e) : selecionarControladorCurso() || removerExcecao() || loginExcecao() || matriculaDisponivel() ||
	  selecionarControladorUsuarios(){
		
		Alertas.falhaConexaoBanco();
		
	}
	
	
	after()throwing : conexaoFalha(){
		
		Alertas.falhaCredencialBanco();
		AVA.sStage.close();
	}
	
	after()throwing(ListaCadastroVaziaExceptions e): selecionarControladorCurso(){
		
		Alertas.tabelaVazia();
	}
	
	after()throwing(ListaCadastroVaziaExceptions e): matriculaDisponivel() || selecionarControladorUsuarios() || selecionarDisciplinas(){
		
		Alertas.selecaoVazia(e.getMessage());
	}

	
	after()throwing(ObjetoNaoExistenteExcepitions e) : loginExcecao(){
		
		Alertas.ObjetoNaoExiste(e.getMessage());
	}
	
	after()throwing(SQLException e): insercaoProfessor() || inserirAluno(){
		
		Alertas.ObjetoJaExiste("Usuario já existe");
	}
	
	after(String nome)throwing(SQLException e) : cadastrarDepartamento(nome){
		
		Alertas.ObjetoJaExiste("Departamento já Existe no sistema");
	}
	
	after()throwing(Exception e) : cadastrarDisciplina(){
		
		Alertas.ObjetoJaExiste("Disciplina já Existe no sistema");
	}
	
	after()throwing(Exception e) : cadastrarCurso(){
		
		Alertas.ObjetoJaExiste(" Curso já Existe no sistema");
	}
	
	after()throwing(Exception e) : cadastrarProjeto(){
		
		Alertas.ObjetoJaExiste(" Projeto já Existe no sistema");
	}
	
	after(Matricular matricula)throwing(SQLException e) : matricularAluno(matricula){
		
		Alertas.ObjetoJaExiste("A matricula na Oferta- "+matricula.getIdOferta()+" \n Data -"+matricula.getDataMatricula()+"\n Ja foi Realizada");
	}
}
