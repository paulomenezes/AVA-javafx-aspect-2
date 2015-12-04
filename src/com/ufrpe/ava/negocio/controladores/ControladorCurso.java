package com.ufrpe.ava.negocio.controladores;

import com.ufrpe.ava.negocio.entidades.Aluno;
import com.ufrpe.ava.negocio.entidades.Curso;
import com.ufrpe.ava.negocio.entidades.Departamento;

import java.util.ArrayList;

/**
 * Created by paulomenezes on 01/12/15.
 */
public class ControladorCurso {
    public Departamento cadastrarDepartamento(String nome) throws Exception {
        Departamento departamento = new Departamento();
        departamento.setNome(nome);

        return departamento;
    }

    public Departamento editarDepartamento(int id, String nome) throws Exception {
        Departamento departamento = new Departamento();
        departamento.setIdDepartamento(id);
        departamento.setNome(nome);

        return departamento;
    }

    public ArrayList<Departamento> selecionarDepartamentos() {
        return null;
    }
    public ArrayList<Curso> selecionarCursos() {
        return null;
    }

    public boolean removerDepartamento(Departamento departamento) throws Exception {
        return false;
    }
}
