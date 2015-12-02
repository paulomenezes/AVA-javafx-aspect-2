package com.ufrpe.ava.negocio;

import com.ufrpe.ava.excecoes.ObjetoJaExistenteExcepitions;
import com.ufrpe.ava.excecoes.ObjetoNaoExistenteExcepitions;
import com.ufrpe.ava.negocio.entidades.Departamento;
import com.ufrpe.ava.negocio.entidades.Usuario;

import java.util.ArrayList;

/**
 * Created by paulomenezes on 01/12/15.
 */
public interface IAvaFachada {

    void cadastrarAluno(String nome, String cpf, String email, String senha, int codCurso, String tipo, int grad)
            throws Exception;

    void cadastrarProfessor(String nome, String cpf, String email, String senha, int idDepartamento, int grad)
            throws Exception;

    Departamento cadastrarDepartamento(String nome) throws Exception;

    boolean removerDepartamento(Departamento departamento) throws Exception;

    Usuario buscarLogin(String cpf, String senha) throws ObjetoNaoExistenteExcepitions;

    ArrayList<Usuario> selecionarTudo();

    ArrayList<Departamento> selecionarDepartamentos();
}

