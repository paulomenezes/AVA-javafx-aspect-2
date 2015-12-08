package com.ufrpe.ava.negocio;

import java.sql.SQLException;
import java.util.ArrayList;

import com.ufrpe.ava.excecoes.ListaCadastroVaziaExceptions;
import com.ufrpe.ava.excecoes.ObjetoNaoExistenteExcepitions;
import com.ufrpe.ava.negocio.controladores.ControladorCurso;
import com.ufrpe.ava.negocio.controladores.ControladorLogging;
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
    private ControladorLogging controladorLogging;

    public AvaFachada() {
        controladorUsuario = new ControladorUsuario();
        controladorCurso = new ControladorCurso();
        controladorLogging = new ControladorLogging();
    }
    

    /*FUNCOES USUARIOS ------------------------------------------------------------------------------------*/
    
    @Override
    public Usuario buscarLogin(String cpf, String senha) throws SQLException,ObjetoNaoExistenteExcepitions {
    	return controladorUsuario.buscarLogin(cpf, senha);
  
    }

    @Override
    public ArrayList<Usuario> selecionarUsuarios() throws SQLException,ListaCadastroVaziaExceptions {
        return controladorUsuario.selecionarTudo();
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

    public void removerUsuario(Usuario usuario) throws SQLException {
        controladorUsuario.removerUsuario(usuario);
    }

    public void cadastrarUsuario(String nome, String cpf, String email, String senha) throws SQLException  {
        controladorUsuario.cadastrarUsuario(nome, cpf, email, senha);
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

    public Curso cadastrarCurso(String nome, int quantidade, Departamento departamento, String tipo) throws Exception {
        return controladorCurso.cadastrarCurso(nome, quantidade, departamento, tipo);
    }

    public void editarCurso(int idCurso, String nome, int quantidade, Departamento departamento, String tipo) throws SQLException {
        controladorCurso.editarCurso(idCurso, nome, quantidade, departamento, tipo);
    }

    public void removerCurso(Curso curso) throws SQLException {
        controladorCurso.removerCurso(curso);
    }

    /* FUNÇÕES  DISCIPLINAS -----------------------------------------------------------------------------*/ 
    
    public ArrayList<DisciplinaDisponivel> disciplinasDisponiveis(String cpf)  throws SQLException,ListaCadastroVaziaExceptions{
    	
    	return controladorCurso.disciplinasDisponiveis(cpf);
    	
    }

    
    /* FUNÇÕES  REGISTROS -----------------------------------------------------------------------------*/ 

	@Override
	public void registrarLogin(String registro) {
		
		controladorLogging.registrarLogin(registro);		
	}

	@Override
	public void registrarMatricula(String registro) {
		
		controladorLogging.registrarMatricula(registro);		
	}

	@Override
	public void registrarPersistencia(String registro) {
		
		controladorLogging.registrarPersistencia(registro);
	}


	
}
