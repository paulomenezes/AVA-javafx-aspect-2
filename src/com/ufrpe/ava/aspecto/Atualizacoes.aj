package com.ufrpe.ava.aspecto;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.ufrpe.ava.negocio.controladores.ControladorCurso;
import com.ufrpe.ava.negocio.controladores.ControladorDisciplina;
import com.ufrpe.ava.negocio.controladores.ControladorProjetoPesquisa;
import com.ufrpe.ava.negocio.entidades.Departamento;
import com.ufrpe.ava.negocio.entidades.Nota;

/**
 * Created by paulomenezes on 03/12/15.
 *
 */
//POINT CUTS -------------------------------------------------------------------------------------------------------------------
public aspect Atualizacoes extends ConexaoMySQL {
    pointcut editarDepartamento(int id, String nome):
            call(* ControladorCurso.editarDepartamento(int, String)) && args(id, nome);

    pointcut editarCurso(int id, String nome, int quantidade, Departamento departamento, String tipo):
            call(* ControladorCurso.editarCurso(int, String, int, Departamento, String)) && args(id, nome, quantidade, departamento, tipo);

    pointcut editarDisciplina(int id, String nome, String tipo, int cargaHoraria, int creditos):
            call(* ControladorDisciplina.editarDisciplina(int, String, String, int, int)) && args(id, nome, tipo, cargaHoraria, creditos);

    pointcut editarProjetoPesquisa(int id, String nome, String modalidade, String organizacao, double valorBolsa, int nVagas):
            call(* ControladorProjetoPesquisa.editarProjetoPesquisa(int, String, String, String, double, int)) && args(id, nome, modalidade, organizacao, valorBolsa, nVagas);

    pointcut aceitarSolicitacaoProjeto(int id, int estado):
            call(* ControladorProjetoPesquisa.aceitarSolicitacaoProjeto(int, int)) && args(id, estado);            

    
    pointcut alterarNota(Nota notaNova) : 
    		call(* ControladorDisciplina.alterarNota(..))&& args(notaNova);
    
  //ADVICES ----------------------------------------------------------------------------------------------------------------------   
    
    
    void around(Nota notaNova)throws SQLException : alterarNota(notaNova){
    	
    	connection.setAutoCommit(false);
        PreparedStatement statement = connection.prepareStatement("UPDATE nota SET nota1 = ?, nota2 = ?, nota3 = ?,notaFinal = ? WHERE cpfAluno = ? AND idOferta = ?");
        statement.setDouble(1,notaNova.getNota1());
        statement.setDouble(2,notaNova.getNota2());
        statement.setDouble(3,notaNova.getNota3());
        statement.setDouble(4,notaNova.getNotaFinal());
        statement.setString(5,notaNova.getCpfAluno());
        statement.setInt(6, notaNova.getIdOferta());
    
        statement.executeUpdate();

        connection.commit();
    	
    }
    
    
    void around(int id, int estado) throws SQLException: aceitarSolicitacaoProjeto(id, estado) {
        connection.setAutoCommit(false);
        PreparedStatement statement = connection.prepareStatement("UPDATE solicitacaoprojeto SET estado = ? WHERE idSolicitacao = ?");
        statement.setInt(1, estado);
        statement.setInt(2, id);
        statement.executeUpdate();

        connection.commit();
    }            

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
        statement.setInt(5, id);
        statement.executeUpdate();

        System.out.println(id + "");
        System.out.println(nome);

        connection.commit();
    }

    void around(int id, String nome, String tipo, int cargaHoraria, int creditos) throws SQLException:
        editarDisciplina(id, nome, tipo, cargaHoraria, creditos) {

        connection.setAutoCommit(false);
        PreparedStatement statement = connection.prepareStatement("UPDATE disciplina SET nome = ?, cargaHoraria = ?, creditos = ?, tipo = ? WHERE idDisciplina = ?");
        statement.setString(1, nome);
        statement.setInt(2, cargaHoraria);
        statement.setInt(3, creditos);
        statement.setString(4, tipo);
        statement.setInt(5, id);
        statement.executeUpdate();

        System.out.println(id + "");
        System.out.println(nome);

        connection.commit();
    }

    void around(int id, String nome, String modalidade, String organizacao, double valorBolsa, int nVagas) throws SQLException:
            editarProjetoPesquisa(id, nome, modalidade, organizacao, valorBolsa, nVagas) {

        connection.setAutoCommit(false);
        PreparedStatement statement = connection.prepareStatement("UPDATE projetoPesquisa SET nome = ?, modalidade = ?, organizacao = ?, valorBolsa = ?, nVagas = ? WHERE idProjeto = ?");
        statement.setString(1, nome);
        statement.setString(2, modalidade);
        statement.setString(3, organizacao);
        statement.setDouble(4, valorBolsa);
        statement.setInt(5, nVagas);
        statement.setInt(6, id);
        statement.executeUpdate();

        System.out.println(id + "");
        System.out.println(nome);

        connection.commit();
    }
}
