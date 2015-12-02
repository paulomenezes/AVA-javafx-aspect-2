package com.ufrpe.ava.aspecto;

import com.ufrpe.ava.negocio.entidades.Departamento;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import com.ufrpe.ava.negocio.controladores.ControladorCurso;

/**
 * Created by paulomenezes on 02/12/15.
 */
public aspect Remocoes extends ConexaoMySQL {
    pointcut removerDepartamento(Departamento departamento):
            call(* ControladorCurso.removerDepartamento(Departamento)) && args(departamento);

    boolean around(Departamento departamento) throws Exception: removerDepartamento(departamento) {
        try {
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement("DELETE FROM departamento WHERE idDepartamento = ?");
            statement.setInt(1, departamento.getIdDepartamento());
            statement.executeUpdate();

            connection.commit();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
