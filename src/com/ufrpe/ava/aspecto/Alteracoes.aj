package com.ufrpe.ava.aspecto;

import com.ufrpe.ava.negocio.entidades.Departamento;
import com.ufrpe.ava.negocio.controladores.ControladorCurso;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by paulomenezes on 03/12/15.
 */
public aspect Alteracoes extends ConexaoMySQL {
    pointcut editarDepartamento(int id, String nome):
            call(* ControladorCurso.editarDepartamento(int, String)) && args(id, nome);

    Departamento around(int id, String nome) throws Exception: editarDepartamento(id, nome) {

        try {
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

            return departamento;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
