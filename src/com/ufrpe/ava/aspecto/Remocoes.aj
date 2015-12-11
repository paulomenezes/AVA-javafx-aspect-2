package com.ufrpe.ava.aspecto;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.ufrpe.ava.negocio.controladores.ControladorAviso;
import com.ufrpe.ava.negocio.controladores.ControladorCurso;
import com.ufrpe.ava.negocio.controladores.ControladorUsuario;
import com.ufrpe.ava.negocio.controladores.ControladorDisciplina;
import com.ufrpe.ava.negocio.controladores.ControladorProjetoPesquisa;
import com.ufrpe.ava.negocio.entidades.*;

/**
 * Created by paulomenezes on 02/12/15.
 */
public aspect Remocoes extends ConexaoMySQL {
	
    //POINT CUTS ----------------------------------------------------------------------------------------------------------------------	
    pointcut removerDepartamento(Departamento departamento):
            call(* ControladorCurso.removerDepartamento(Departamento)) && args(departamento);

    pointcut removerCurso(Curso curso):
            call(* ControladorCurso.removerCurso(Curso)) && args(curso);

    pointcut removerUsuario(Usuario usuario):
            call(* ControladorUsuario.removerUsuario(Usuario)) && args(usuario);

    pointcut removerDisciplina(Disciplina disciplina):
            call(* ControladorDisciplina.removerDisciplina(Disciplina)) && args(disciplina);

    pointcut removerProjetoPesquisa(ProjetoPesquisa projetoPesquisa):
            call(* ControladorProjetoPesquisa.removerProjetoPesquisa(ProjetoPesquisa)) && args(projetoPesquisa);

    pointcut removerAviso(Aviso aviso):
            call(* ControladorAviso.removerAviso(Aviso)) && args(aviso);
    
    pointcut removerOferta(OfertaDisciplina oferta): call(* ControladorDisciplina.removerOferta(..)) && args(oferta);

    //ADVICES -----------------------------------------------------------------------------------------------------------------------
    
    
    void around(OfertaDisciplina oferta) throws SQLException : removerOferta(oferta){
    	connection.setAutoCommit(false);
        PreparedStatement statement = connection.prepareStatement("DELETE FROM ofertadisciplina WHERE idOferta = ?");
        statement.setInt(1, oferta.getIdOferta());
        statement.execute();
        connection.commit();
    	
    }
    
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
            PreparedStatement statement = connection.prepareStatement("DELETE FROM disciplina WHERE idDisciplina = ?");
            statement.setInt(1, disciplina.getIdDisciplina());
            statement.executeUpdate();
            connection.commit();
    }

    void around(ProjetoPesquisa projetoPesquisa) throws SQLException : removerProjetoPesquisa(projetoPesquisa) {
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement("DELETE FROM projetoPesquisa WHERE idProjeto = ?");
            statement.setInt(1, projetoPesquisa.getIdProjeto());
            statement.executeUpdate();
            connection.commit();
    }

    void around(Aviso aviso) throws SQLException : removerAviso(aviso) {
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement("DELETE FROM aviso WHERE idAviso = ?");
            statement.setInt(1, aviso.getIdAviso());
            statement.executeUpdate();
            connection.commit();
    }
}
