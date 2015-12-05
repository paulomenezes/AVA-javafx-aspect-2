package com.ufrpe.ava.aspecto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.ufrpe.ava.negocio.controladores.ControladorCurso;
import com.ufrpe.ava.negocio.entidades.Aluno;
import com.ufrpe.ava.negocio.entidades.Departamento;
import com.ufrpe.ava.negocio.entidades.Usuario;

/**
 * Created by paulomenezes on 01/12/15.
 */
public aspect Insercoes extends ConexaoMySQL {
    
	pointcut cadastrarDepartamento(String nome):
        call(* ControladorCurso.cadastrarDepartamento(String)) && args(nome);
    
	pointcut insercaoUsuario(Usuario usuario): call(* *.cadastrarUsuario(..))&& args(usuario);
    
	
	// IRSERCAO USUARIOS --------------------------------------------------------------------------------------------------------//
	
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
		
			statement = connection.prepareStatement("INSERT INTO aluno(idAluno, idCurso, tipoAluno)VALUES(?,?,?)");
		
		}
	}
	
	
	
	
	

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
