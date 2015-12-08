package com.ufrpe.ava.aspecto;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.ufrpe.ava.negocio.controladores.ControladorCurso;
import com.ufrpe.ava.negocio.entidades.Departamento;

/**
 * Created by paulomenezes on 03/12/15.
 */
public aspect Atualizacoes extends ConexaoMySQL {
    pointcut editarDepartamento(int id, String nome):
            call(* ControladorCurso.editarDepartamento(int, String)) && args(id, nome);

    pointcut editarCurso(int id, String nome, int quantidade, Departamento departamento, String tipo):
            call(* ControladorCurso.editarCurso(int, String, int, Departamento, String)) && args(id, nome, quantidade, departamento, tipo);

    void around(int id, String nome) throws SQLException: editarDepartamento(id, nome) {
        connection.setAutoCommit(false);
        PreparedStatement statement = connection.prepareStatement("UPDATE departamento SET nome = ? WHERE idDepartamento = ?");
        statement.setString(1, nome);
        statement.setInt(2, id);
        statement.executeUpdate();

        System.out.println(id + "");
        System.out.println(nome);

        connection.commit();
    }

    void around(int id, String nome, int quantidade, Departamento departamento, String tipo) throws SQLException: editarCurso(id, nome, quantidade, departamento, tipo) {

        connection.setAutoCommit(false);
        PreparedStatement statement = connection.prepareStatement("UPDATE curso SET nome = ?, qtdAlunos = ?, idDepartamento = ?, tipo = ? WHERE idCurso = ?");
        statement.setString(1, nome);
        statement.setInt(2, quantidade);
        statement.setInt(3, departamento.getIdDepartamento());
        statement.setString(4, tipo);
        statement.executeUpdate();

        System.out.println(id + "");
        System.out.println(nome);

        connection.commit();
    }
}
