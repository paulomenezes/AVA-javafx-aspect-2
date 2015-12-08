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

    public void cadastrarAluno(String nome, String cpf,String email, String senha, int codCurso, int tipo, int grad)
    		throws SQLException {

        Aluno aluno = new Aluno(nome,cpf,email,senha,grad,tipo, codCurso);
        cadastrarUsuario(aluno);
    }

    public void cadastrarProfessor(String nome, String cpf,String email, String senha, int idDpto, int grad)
            throws SQLException {

        Professor professor = new Professor(nome, cpf,email,senha,grad,idDpto);
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

    public void cadastrarUsuario(String nome, String cpf, String email, String senha) throws SQLException {
        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setCPF(cpf);
        usuario.setEmail(email);
        usuario.setSenha(senha);

        cadastrarUsuario(usuario);
    }
}
