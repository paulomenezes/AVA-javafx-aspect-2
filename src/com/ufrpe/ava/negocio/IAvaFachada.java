package com.ufrpe.ava.negocio;

import java.sql.SQLException;
import java.util.List;

import com.ufrpe.ava.excecoes.ListaCadastroVaziaExceptions;
import com.ufrpe.ava.excecoes.ObjetoNaoExistenteExcepitions;
import com.ufrpe.ava.negocio.entidades.Curso;
import com.ufrpe.ava.negocio.entidades.Departamento;
import com.ufrpe.ava.negocio.entidades.Usuario;

/**
 * Created by paulomenezes on 01/12/15.
 */
public interface IAvaFachada {

 /*FUNCOES USUARIOS ------------------------------------------------------------------------------------*/
	
    void cadastrarAluno(String nome, String cpf, String email, String senha, int codCurso, int tipo, int grad)
    		throws SQLException;

    void cadastrarProfessor(String nome, String cpf, String email, String senha, int idDepartamento, int grad)
    		throws SQLException;
    
    Usuario buscarLogin(String cpf, String senha) throws SQLException, ObjetoNaoExistenteExcepitions;

    List<Usuario> selecionarTudo();



/*FUNCOES DEPARTAMENTOS ------------------------------------------------------------------------------------*/  
    
    Departamento cadastrarDepartamento(String nome) throws Exception;
    
    void editarDepartamento(int id, String nome) throws SQLException;

    void removerDepartamento(Departamento departamento) throws SQLException;

    List<Departamento> selecionarDepartamentos() throws SQLException, ListaCadastroVaziaExceptions;
    
    
/*FUNCOES CURSOS ------------------------------------------------------------------------------------*/
    
    List<Curso> selecionarCursos() throws SQLException, ListaCadastroVaziaExceptions;
    
}

