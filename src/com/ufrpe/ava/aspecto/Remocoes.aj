package com.ufrpe.ava.aspecto;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.ufrpe.ava.negocio.controladores.ControladorCurso;
import com.ufrpe.ava.negocio.entidades.Curso;
import com.ufrpe.ava.negocio.entidades.Departamento;

/**
 * Created by paulomenezes on 02/12/15.
 */
public aspect Remocoes extends ConexaoMySQL {
    pointcut removerDepartamento(Departamento departamento):
            call(* ControladorCurso.removerDepartamento(Departamento)) && args(departamento);

    pointcut removerCurso(Curso curso):
            call(* ControladorCurso.removerCurso(Curso)) && args(curso);

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
}
