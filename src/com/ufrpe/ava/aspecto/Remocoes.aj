package com.ufrpe.ava.aspecto;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.ufrpe.ava.negocio.controladores.ControladorCurso;
import com.ufrpe.ava.negocio.controladores.ControladorUsuario;
import com.ufrpe.ava.negocio.controladores.ControladorDisciplina;
import com.ufrpe.ava.negocio.entidades.Curso;
import com.ufrpe.ava.negocio.entidades.Departamento;
import com.ufrpe.ava.negocio.entidades.Disciplina;
import com.ufrpe.ava.negocio.entidades.Usuario;

/**
 * Created by paulomenezes on 02/12/15.
 */
public aspect Remocoes extends ConexaoMySQL {
    pointcut removerDepartamento(Departamento departamento):
            call(* ControladorCurso.removerDepartamento(Departamento)) && args(departamento);

    pointcut removerCurso(Curso curso):
            call(* ControladorCurso.removerCurso(Curso)) && args(curso);

    pointcut removerUsuario(Usuario usuario):
            call(* ControladorUsuario.removerUsuario(Usuario)) && args(usuario);

    pointcut removerDisciplina(Disciplina disciplina):
            call(* ControladorDisciplina.removerDisciplina(Disciplina)) && args(disciplina);

     void around(Departamento departamento) throws SQLException : removerDepartamento(departamento) {
        connection.setAutoCommit(false);
        PreparedStatement statement = connection.prepareStatement("DELETE FROM departamento WHERE idDepartamento = ?");
        statement.setInt(1, departamento.getIdDepartamento());
        statement.executeUpdate();
        connection.commit();
    }

    void around(Curso curso) throws SQLException : removerCurso(curso) {
        connection.setAutoCommit(false);
        PreparedStatement statement = connection.prepareStatement("DELETE FROM curso WHERE idCurso = ?");
        statement.setInt(1, curso.getIdCurso());
        statement.executeUpdate();
        connection.commit();
    }

    void around(Usuario usuario) throws SQLException : removerUsuario(usuario) {
        connection.setAutoCommit(false);
        PreparedStatement statement = connection.prepareStatement("DELETE FROM usuario WHERE cpf = ?");
        statement.setString(1, usuario.getCPF());
        statement.executeUpdate();
        connection.commit();
    }

    void around(Disciplina disciplina) throws SQLException : removerDisciplina(disciplina) {
        connection.setAutoCommit(false);
        PreparedStatement statement = connection.prepareStatement("DELETE FROM disciplina WHERE cpf = ?");
        statement.setInt(1, disciplina.getIdDisciplina());
        statement.executeUpdate();
        connection.commit();
    }
}
