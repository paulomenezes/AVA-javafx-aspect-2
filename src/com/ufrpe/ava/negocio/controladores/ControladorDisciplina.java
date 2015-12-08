package com.ufrpe.ava.negocio.controladores;

import com.ufrpe.ava.excecoes.ListaCadastroVaziaExceptions;
import com.ufrpe.ava.negocio.entidades.Curso;
import com.ufrpe.ava.negocio.entidades.Departamento;
import com.ufrpe.ava.negocio.entidades.Disciplina;
import com.ufrpe.ava.negocio.entidades.DisciplinaDisponivel;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by paulomenezes on 08/12/15.
 */
public class ControladorDisciplina {
    public ArrayList<Disciplina> selecionarDisciplinas()throws SQLException,ListaCadastroVaziaExceptions {
        return null;
    }

    public Disciplina cadastrarDisciplina(String nome, String tipo, int cargaHoraria, int creditos) throws Exception  {
        Disciplina disciplina = new Disciplina();
        disciplina.setNome(nome);
        disciplina.setTipo(tipo);
        disciplina.setCargaHoraria(cargaHoraria);
        disciplina.setCreditos(creditos);

        return disciplina;
    }

    public void editarDisciplina(int id, String nome, String tipo, int cargaHoraria, int creditos) throws SQLException  {
        Disciplina disciplina = new Disciplina();
        disciplina.setNome(nome);
        disciplina.setTipo(tipo);
        disciplina.setCargaHoraria(cargaHoraria);
        disciplina.setCreditos(creditos);
    }

    public void removerDisciplina(Disciplina disciplina) throws SQLException {

    }
}

