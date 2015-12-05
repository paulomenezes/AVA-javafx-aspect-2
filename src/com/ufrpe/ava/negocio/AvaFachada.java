package com.ufrpe.ava.negocio;

import java.sql.SQLException;
import java.util.ArrayList;

import com.ufrpe.ava.excecoes.ListaCadastroVaziaExceptions;
import com.ufrpe.ava.excecoes.ObjetoNaoExistenteExcepitions;
import com.ufrpe.ava.negocio.controladores.ControladorCurso;
import com.ufrpe.ava.negocio.controladores.ControladorUsuario;
import com.ufrpe.ava.negocio.entidades.Curso;
import com.ufrpe.ava.negocio.entidades.Departamento;
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
    	Usuario usuario = controladorUsuario.buscarLogin(cpf, senha);
        return usuario;
    }

    @Override
    public ArrayList<Usuario> selecionarTudo() {
        return null;
    }

    @Override
    public void cadastrarAluno(String nome, String cpf, String email, String senha, int codCurso, String tipo, int grad)
            throws Exception {

        controladorUsuario.cadastrarAluno(nome, cpf, email, senha, codCurso, tipo, grad);
    }

    @Override
    public void cadastrarProfessor(String nome, String cpf, String email, String senha, int idDepartamento, int grad)
            throws Exception {

        controladorUsuario.cadastrarProfessor(nome, cpf, email, senha, idDepartamento, grad);
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

   
}
