package com.ufrpe.ava.aspecto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ufrpe.ava.excecoes.ListaCadastroVaziaExceptions;
import com.ufrpe.ava.negocio.controladores.ControladorCurso;
import com.ufrpe.ava.negocio.entidades.Curso;
import com.ufrpe.ava.negocio.entidades.Departamento;
import com.ufrpe.ava.negocio.entidades.DisciplinaDisponivel;

/**
 * Created by paulomenezes on 01/12/15.
 */
public aspect Consultas extends ConexaoMySQL {
	
    pointcut selecionarDepartamentos(): execution(* ControladorCurso.selecionarDepartamentos());
    pointcut selecionarCursos(): execution(* ControladorCurso.selecionarCursos());
    pointcut disciplinasDisponiveis(String cpf) : call(* ControladorCurso.disciplinasDisponiveis(..)) && args(cpf);
    
    
    
    
    
    ArrayList<DisciplinaDisponivel> around(String cpf) throws SQLException,ListaCadastroVaziaExceptions: disciplinasDisponiveis(cpf){
    	
    	PreparedStatement statement = connection.prepareStatement(" SELECT R.nome, R.cargaHoraria,R.nome_professor FROM alunoHistorico AS A JOIN requisitos AS R ON A.idOferta = R.requisito WHERE A.cpfAluno = ? ");
    	statement.setString(1, cpf);
    	ResultSet resultSet = statement.executeQuery();
        ArrayList<DisciplinaDisponivel> disciplinas = new ArrayList<>();
        
    	
        while(resultSet.next()){
        	
        	DisciplinaDisponivel d = new DisciplinaDisponivel();
        	
        	d.setNome(resultSet.getString("nome"));
        	d.setCargaHoraria(resultSet.getInt("cargaHoraria"));
        	d.setNomeProfessor(resultSet.getString("nome_professor"));
        	
        	disciplinas.add(d);
        }
        
        	
        if(disciplinas.isEmpty()){
        	
        	throw new ListaCadastroVaziaExceptions();
        }
        
        return disciplinas;
    	
    }
    
    
    ArrayList<Departamento> around()throws SQLException,ListaCadastroVaziaExceptions: selecionarDepartamentos() {
    	
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM departamento");
            ResultSet resultSet = statement.executeQuery();

            ArrayList<Departamento> lista = new ArrayList<>();
            while (resultSet.next()) {
                Departamento usuario = new Departamento();
                usuario.setIdDepartamento(resultSet.getInt("idDepartamento"));
                usuario.setNome(resultSet.getString("nome"));

                lista.add(usuario);
            }
            
            if(lista.isEmpty()){
            	
            	throw new ListaCadastroVaziaExceptions();
            }

            return lista;
    }

    ArrayList<Curso> around()throws SQLException,ListaCadastroVaziaExceptions: selecionarCursos() {

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
            
                if(lista.isEmpty()){
                	throw new ListaCadastroVaziaExceptions();
                }
                
                return lista;
    }
    
    
}
