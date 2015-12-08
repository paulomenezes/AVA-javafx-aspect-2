package com.ufrpe.ava.negocio;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ufrpe.ava.excecoes.ListaCadastroVaziaExceptions;
import com.ufrpe.ava.excecoes.ObjetoNaoExistenteExcepitions;
import com.ufrpe.ava.negocio.entidades.*;

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

    List<Usuario> selecionarUsuarios() throws SQLException,ListaCadastroVaziaExceptions;

    void matricularAluno(Matricular m) throws SQLException;

    void removerUsuario(Usuario selectedItem) throws SQLException;

    void cadastrarUsuario(String nome, String cpf, String email, String senha) throws SQLException;

    /* FUNCOES DEPARTAMENTOS ------------------------------------------------------------------------------------*/
    Departamento cadastrarDepartamento(String nome) throws SQLException;
    
    void editarDepartamento(int id, String nome) throws SQLException;

    void removerDepartamento(Departamento departamento) throws SQLException;

    List<Departamento> selecionarDepartamentos() throws SQLException, ListaCadastroVaziaExceptions;
    
    
    /* FUNCOES CURSOS ------------------------------------------------------------------------------------*/
    List<Curso> selecionarCursos() throws SQLException, ListaCadastroVaziaExceptions;

    Curso cadastrarCurso(String nome, int quantidade, Departamento departamento, String tipo) throws Exception;

    void editarCurso(int idCurso, String nome, int quantidade, Departamento departamento, String tipo) throws SQLException;

    void removerCurso(Curso curso) throws SQLException;

    /* FUNCOES CURSOS ------------------------------------------------------------------------------------*/
    public ArrayList<DisciplinaDisponivel> disciplinasDisponiveis(String cpf)  throws SQLException,ListaCadastroVaziaExceptions;
    
   /*FUNCOES LOGGGING ------------------------------------------------------------------------------------*/
    
    void registrarLogin(String registro);
    void registrarMatricula(String registro);
    void registrarPersistencia(String registro);

    /* FUNCOES DISCIPLINAS ------------------------------------------------------------------------------------*/
    Disciplina cadastrarDisciplina(String nome, String tipo, int cargaHoraria, int creditos) throws Exception;

    void editarDisciplina(int id, String nome, String tipo, int cargaHoraria, int creditos) throws SQLException;

    void removerDisciplina(Disciplina disciplina) throws SQLException;

    List<Disciplina> selecionarDisciplinas() throws SQLException, ListaCadastroVaziaExceptions;

    /* FUNCOES PROJETO PESQUISA ------------------------------------------------------------------------------------*/
    ProjetoPesquisa cadastrarProjetoPesquisa(String nome, String modalidade, String organizacao, double valorBolsa, int nVagas) throws Exception;

    void editarProjetoPesquisa(int id, String nome, String modalidade, String organizacao, double valorBolsa, int nVagas) throws SQLException;

    void removerProjetoPesquisa(ProjetoPesquisa projetoPesquisa) throws SQLException;

    List<ProjetoPesquisa> selecionarProjetoPesquisas() throws SQLException, ListaCadastroVaziaExceptions;
}

