package com.ufrpe.ava.negocio;

import java.sql.SQLException;
import java.util.ArrayList;

import com.ufrpe.ava.excecoes.ListaCadastroVaziaExceptions;
import com.ufrpe.ava.excecoes.ObjetoNaoExistenteExcepitions;
import com.ufrpe.ava.negocio.controladores.ControladorCurso;
import com.ufrpe.ava.negocio.controladores.ControladorUsuario;
import com.ufrpe.ava.negocio.entidades.Curso;
import com.ufrpe.ava.negocio.entidades.Departamento;
import com.ufrpe.ava.negocio.entidades.DisciplinaDisponivel;
import com.ufrpe.ava.negocio.entidades.Matricular;
import com.ufrpe.ava.negocio.entidades.Usuario;

/**
 * Created by paulomenezes on 01/12/15.
 */
public class AvaFachada implements IAvaFachada {
    private ControladorUsuario controladorUsuario;
    private ControladorCurso controladorCurso;

    public AvaFachada() {
        controladorUsuario = new ControladorUsuario();
        controladorCurso = new ControladorCurso();
    }
    

    /*FUNCOES USUARIOS ------------------------------------------------------------------------------------*/
    
    @Override
    public Usuario buscarLogin(String cpf, String senha) throws SQLException,ObjetoNaoExistenteExcepitions {
    	return controladorUsuario.buscarLogin(cpf, senha);
  
    }

    @Override
    public ArrayList<Usuario> selecionarTudo() {
        return null;
    }

    @Override
    public void cadastrarAluno(String nome, String cpf, String email, String senha, int codCurso, int tipo, int grad)
    		throws SQLException {

        controladorUsuario.cadastrarAluno(nome, cpf, email, senha, codCurso, tipo, grad);
    }

    @Override
    public void cadastrarProfessor(String nome, String cpf, String email, String senha, int idDepartamento, int grad)
    		throws SQLException {

        controladorUsuario.cadastrarProfessor(nome, cpf, email, senha, idDepartamento, grad);
    }

    @Override
	public void matricularAluno(Matricular m) throws SQLException {
    	controladorUsuario.matricularAluno(m);
    }
    
    /*FUNCOES DEPARTAMENTO ------------------------------------------------------------------------------------*/
    
    @Override
    public Departamento cadastrarDepartamento(String nome) throws Exception {
        return controladorCurso.cadastrarDepartamento(nome);
    }

    @Override
    public void editarDepartamento(int id, String nome) throws SQLException {
        controladorCurso.editarDepartamento(id, nome);
    }

    public ArrayList<Departamento> selecionarDepartamentos() throws SQLException, ListaCadastroVaziaExceptions {
        return controladorCurso.selecionarDepartamentos();
    }
    
    public void removerDepartamento(Departamento departamento) throws SQLException {
        controladorCurso.removerDepartamento(departamento);
   }
    
    
    
    /*FUNCOES CURSOS ------------------------------------------------------------------------------------*/
    
    public ArrayList<Curso> selecionarCursos()throws SQLException,ListaCadastroVaziaExceptions {
        return controladorCurso.selecionarCursos();
    }

    /* FUNÇÕES  DISCIPLINAS -----------------------------------------------------------------------------*/ 
    
    public ArrayList<DisciplinaDisponivel> disciplinasDisponiveis(String cpf)  throws SQLException,ListaCadastroVaziaExceptions{
    	
    	return controladorCurso.disciplinasDisponiveis(cpf);
    	
    }


	
}
