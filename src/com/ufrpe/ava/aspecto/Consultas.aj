package com.ufrpe.ava.aspecto;

import com.ufrpe.ava.negocio.entidades.Curso;
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
    pointcut selecionarCursos(): execution(* ControladorCurso.selecionarCursos());

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

    ArrayList<Curso> around(): selecionarCursos() {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT c.*, d.idDepartamento AS idDepto, d.nome AS NomeDepartamento FROM curso AS c INNER JOIN departamento AS d ON C.idDepartamento = d.idDepartamento");
            ResultSet resultSet = statement.executeQuery();

            ArrayList<Curso> lista = new ArrayList<>();
            while (resultSet.next()) {
                Curso curso = new Curso();
                curso.setIdCurso(resultSet.getInt("idCurso"));
                curso.setNome(resultSet.getString("nome"));
                curso.setLimiteAluno(resultSet.getInt("limiteAluno"));
                curso.setQuantAlunos(resultSet.getInt("qtdAlunos"));

                Departamento departamento = new Departamento();
                departamento.setIdDepartamento(resultSet.getInt("idDepto"));
                departamento.setNome(resultSet.getString("NomeDepartamento"));

                curso.setIdDepartamento(departamento);

                lista.add(curso);
            }

            return lista;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
