package com.ufrpe.ava.negocio.controladores;

import java.sql.SQLException;
import java.util.ArrayList;

import com.ufrpe.ava.excecoes.ListaCadastroVaziaExceptions;
import com.ufrpe.ava.excecoes.ObjetoNaoExistenteExcepitions;
import com.ufrpe.ava.negocio.entidades.Aluno;
import com.ufrpe.ava.negocio.entidades.Matricular;
import com.ufrpe.ava.negocio.entidades.Professor;
import com.ufrpe.ava.negocio.entidades.Usuario;

/**
 * Created by paulomenezes on 01/12/15.
 */
public class ControladorUsuario {

    public Usuario buscarLogin(String cpf, String senha) throws SQLException,ObjetoNaoExistenteExcepitions {
    	return null;
    }


    public ArrayList<Usuario> selecionarTudo() throws SQLException,ListaCadastroVaziaExceptions {
        return null;
    }

    public void cadastrarAluno(String cpf,String nome,String foto, String email,String senha,  int grad, int codCurso)
    		throws SQLException {

        Aluno aluno = new Aluno(cpf,nome,foto,email,senha,grad, codCurso);
        cadastrarUsuario(aluno);
    }

    public void cadastrarProfessor(String cpf,String nome,String foto, String email,String senha,  int grad, int idDpto)
            throws SQLException {

        Professor professor = new Professor(cpf,nome,foto,email,senha,grad, idDpto);
        cadastrarUsuario(professor);
    }

    private void cadastrarUsuario(Usuario usuario) throws SQLException {
    	//INSERIDO PELO ASPECTO INSERÇOES
    }

    public void matricularAluno(Matricular m) throws SQLException{
    	//INSERIDO PELO ASPECTO INSERÇÕES
    }

    public void removerUsuario(Usuario usuario) throws SQLException {

    }

    public void cadastrarUsuario(String cpf, String nome, String email, String senha) throws SQLException {
        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setCPF(cpf);
        usuario.setEmail(email);
        usuario.setSenha(senha);

        cadastrarUsuario(usuario);
    }
}
