package com.ufrpe.ava.negocio.controladores;

import java.sql.SQLException;
import java.util.ArrayList;

import com.ufrpe.ava.excecoes.ObjetoNaoExistenteExcepitions;
import com.ufrpe.ava.negocio.entidades.Aluno;
import com.ufrpe.ava.negocio.entidades.Professor;
import com.ufrpe.ava.negocio.entidades.Usuario;

/**
 * Created by paulomenezes on 01/12/15.
 */
public class ControladorUsuario {

    public Usuario buscarLogin(String cpf, String senha) throws SQLException,ObjetoNaoExistenteExcepitions {
    	return null;
    }


    public ArrayList<Usuario> selecionarTudo() {
        return null;
    }

    public void cadastrarAluno(String nome, String cpf,String email, String senha, int codCurso, String tipo, int grad)
            throws Exception {

        Aluno aluno = new Aluno(nome,cpf,email,senha,grad,tipo, codCurso);
        cadastrarUsuario(aluno);
    }

    public void cadastrarProfessor(String nome, String cpf,String email, String senha, int idDpto, int grad)
            throws Exception {

        Professor professor = new Professor(nome, cpf,email,senha,grad,idDpto);
        cadastrarUsuario(professor);
    }

    private void cadastrarUsuario(Usuario usuario) throws Exception {
    	//INSERIDO PELO ASPECTO INSERÇOES
    }

}
