package com.ufrpe.ava.aspecto;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.ufrpe.ava.negocio.controladores.ControladorCurso;
import com.ufrpe.ava.negocio.entidades.Departamento;

/**
 * Created by paulomenezes on 03/12/15.
 */
public aspect Alteracoes extends ConexaoMySQL {
    pointcut editarDepartamento(int id, String nome):
            call(* ControladorCurso.editarDepartamento(int, String)) && args(id, nome);

    void around(int id, String nome) throws SQLException: editarDepartamento(id, nome) {

            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement("UPDATE departamento SET nome = ? WHERE idDepartamento = ?");
            statement.setString(1, nome);
            statement.setInt(2, id);
            statement.executeUpdate();

            System.out.println(id + "");
            System.out.println(nome);

            Departamento departamento = new Departamento();
            departamento.setIdDepartamento(id);
            departamento.setNome(nome);

            connection.commit();
    }
}
