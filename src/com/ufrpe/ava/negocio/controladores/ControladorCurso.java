package com.ufrpe.ava.negocio.controladores;

import java.sql.SQLException;
import java.util.ArrayList;

import com.ufrpe.ava.excecoes.ListaCadastroVaziaExceptions;
import com.ufrpe.ava.negocio.entidades.Curso;
import com.ufrpe.ava.negocio.entidades.Departamento;
import com.ufrpe.ava.negocio.entidades.DisciplinaDisponivel;

/**
 * Created by paulomenezes on 01/12/15.
 */
public class ControladorCurso {
	
    public Departamento cadastrarDepartamento(String nome) throws Exception {
        Departamento departamento = new Departamento();
        departamento.setNome(nome);

        return departamento;
    }

    public void editarDepartamento(int id, String nome) throws SQLException {
        Departamento departamento = new Departamento();
        departamento.setIdDepartamento(id);
        departamento.setNome(nome);

    }

    public ArrayList<Departamento> selecionarDepartamentos() throws SQLException,ListaCadastroVaziaExceptions {
        return null;
    }
    public ArrayList<Curso> selecionarCursos()throws SQLException,ListaCadastroVaziaExceptions {
        return null;
    }

    public void removerDepartamento(Departamento departamento) throws SQLException{
        //CHAMADA ASPECTO REMOVER
    }
    
    public ArrayList<DisciplinaDisponivel> disciplinasDisponiveis(String cpf)  throws SQLException,ListaCadastroVaziaExceptions{
    	//CHAMADA ASPECTO CONSULTAR
    	return null;
    } 
}
