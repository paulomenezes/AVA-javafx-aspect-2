package com.ufrpe.ava.aspecto;

import com.ufrpe.ava.negocio.entidades.Departamento;
import com.ufrpe.ava.negocio.entidades.Usuario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import com.ufrpe.ava.negocio.controladores.ControladorCurso;

/**
 * Created by paulomenezes on 01/12/15.
 */
public aspect Consultas extends ConexaoMySQL {
    pointcut selecionarDepartamentos(): execution(* ControladorCurso.selecionarDepartamentos());

    ArrayList<Departamento> around(): selecionarDepartamentos() {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM departamento");
            ResultSet resultSet = statement.executeQuery();

            ArrayList<Departamento> lista = new ArrayList<>();
            while (resultSet.next()) {
                Departamento usuario = new Departamento();
                usuario.setIdDepartamento(resultSet.getInt("idDepartamento"));
                usuario.setNome(resultSet.getString("nome"));

                lista.add(usuario);
            }

            return lista;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
