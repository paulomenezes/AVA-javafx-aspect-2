package com.ufrpe.ava.aspecto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.ufrpe.ava.negocio.controladores.ControladorCurso;
import com.ufrpe.ava.negocio.entidades.Aluno;
import com.ufrpe.ava.negocio.entidades.Departamento;
import com.ufrpe.ava.negocio.entidades.Matricular;
import com.ufrpe.ava.negocio.entidades.Professor;
import com.ufrpe.ava.negocio.entidades.Usuario;

/**
 * Created by paulomenezes on 01/12/15.
 */
public aspect Insercoes extends ConexaoMySQL {
   
	// IRSERCAO POINTCUTS --------------------------------------------------------------------------------------------------------//
	
	pointcut cadastrarDepartamento(String nome):
        call(* ControladorCurso.cadastrarDepartamento(String)) && args(nome);
    
	pointcut insercaoUsuario(Usuario usuario): call(* *.cadastrarUsuario(..))&& args(usuario);
	
	pointcut matricularAluno(Matricular matricula) : 
		call(* com.ufrpe.ava.negocio.controladores.ControladorUsuario.matricularAluno(..))&& args(matricula)  ;
    
	
	// IRSERCAO RELACIONADA  A USUARIOS --------------------------------------------------------------------------------------------------------//
	
	void around(Usuario usuario)throws SQLException: insercaoUsuario(usuario){
		
		connection.setAutoCommit(false);
		PreparedStatement statement = connection.prepareStatement("INSERT INTO usuario (cpf, nome, foto, email, senha, tipo)VALUES(?,?,?,?,?,?)");
		statement.setString(1, usuario.getCPF());
		statement.setString(2, usuario.getNome());
		statement.setString(3, usuario.getFoto());
		statement.setString(4, usuario.getEmail());
		statement.setString(5, usuario.getSenha());
		statement.setInt(6, usuario.getGrad());
		statement.execute();
		
		if(usuario instanceof Aluno){
		
			statement = connection.prepareStatement("INSERT INTO aluno(cpfAluno, idCurso, tipo)VALUES(?,?,?)");
			statement.setString(1, usuario.getCPF());
			statement.setInt(2, ((Aluno) usuario).getCurso());
			statement.setInt(3, ((Aluno) usuario).getTipoAluno());
			statement.execute();
			connection.commit();
			
		
		}else if(usuario instanceof Professor){
			
			statement.close();
			statement = connection.prepareStatement("INSERT INTO professor(cpfProfessor, idDepartamento)VALUES(?,?)");
			statement.setString(1, usuario.getCPF());
			statement.setInt(2, ((Professor) usuario).getIdDpto());
			statement.execute();
			connection.commit();
		}
		
	}
	
	
	void around(Matricular matricula) throws SQLException : matricularAluno(matricula){
		
		PreparedStatement statement = connection.prepareStatement("INSERT INTO matricular(cpfAluno,idOferta,dataMatricula,numeroProtocolo)VALUES(?,?,?,?)");
        
		statement.setString(1,matricula.getCpfAluno());
        statement.setInt(2,matricula.getIdOferta());
        statement.setString(3,matricula.getDataMatricula());
        statement.setString(4, matricula.getNumProtocolo());
        
        statement.execute();
		  
	}

	// IRSERCAO DEPARTAMENTO --------------------------------------------------------------------------------------------------------//

    Departamento around(String nome) throws Exception: cadastrarDepartamento(nome) {

        try {
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement("INSERT INTO departamento (nome) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, nome);
            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();

            if (resultSet.next()) {
                Departamento departamento = new Departamento();
                departamento.setIdDepartamento(resultSet.getInt(1));
                departamento.setNome(nome);

                connection.commit();

                return departamento;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    
    
    
}
