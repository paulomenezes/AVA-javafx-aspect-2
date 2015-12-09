package com.ufrpe.ava.aspecto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.ufrpe.ava.negocio.controladores.ControladorAviso;
import com.ufrpe.ava.negocio.controladores.ControladorCurso;
import com.ufrpe.ava.negocio.controladores.ControladorDisciplina;
import com.ufrpe.ava.negocio.controladores.ControladorProjetoPesquisa;
import com.ufrpe.ava.negocio.entidades.*;

/**
 * Created by paulomenezes on 01/12/15.
 */
public aspect Insercoes extends ConexaoMySQL {
   
	// IRSERCAO POINTCUTS --------------------------------------------------------------------------------------------------------//
	
	pointcut cadastrarDepartamento(String nome):
        call(* ControladorCurso.cadastrarDepartamento(String)) && args(nome);

	pointcut cadastrarCurso(String nome, int quantidade, Departamento departamento, String tipo):
			call(* ControladorCurso.cadastrarCurso(String, int, Departamento, String)) && args(nome, quantidade, departamento, tipo);

    pointcut cadastrarDisciplina(String nome, String tipo, int cargaHoraria, int creditos):
            call(* ControladorDisciplina.cadastrarDisciplina(String, String, int, int)) && args(nome, tipo, cargaHoraria, creditos);

    pointcut cadastrarProjetoPesquisa(String nome, String modalidade, String organizacao, double valorBolsa, int nVagas):
            call(* ControladorProjetoPesquisa.cadastrarProjetoPesquisa(String, String, String, double, int)) && args(nome, modalidade, organizacao, valorBolsa, nVagas);

    pointcut cadastrarAviso(String idRemetente, String titulo, String descricao, int prioridade, String dataEnvio, String horaEnvio, int idDestinatarioO, String idDestinatarioU):
            call(* ControladorAviso.cadastrarAviso(String, String, String, int, String, String, int, String)) && args(idRemetente, titulo, descricao, prioridade, dataEnvio, horaEnvio, idDestinatarioO, idDestinatarioU);

    pointcut enviarSolicitacao(int idProjeto, String cpf):
            call(* ControladorProjetoPesquisa.enviarSolicitacao(int, String)) && args(idProjeto, cpf);

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

            if (usuario instanceof Aluno) {

                statement = connection.prepareStatement("INSERT INTO aluno(cpfAluno, idCurso)VALUES(?,?)");
                statement.setString(1, usuario.getCPF());
                statement.setInt(2, ((Aluno) usuario).getCurso());
                statement.execute();
                connection.commit();


            } else if (usuario instanceof Professor) {

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

            statement.setString(1, matricula.getCpfAluno());
            statement.setInt(2, matricula.getIdOferta());
            statement.setString(3, matricula.getDataMatricula());
            statement.setString(4, matricula.getNumProtocolo());

            statement.execute();
	}

	// IRSERCAO DEPARTAMENTO --------------------------------------------------------------------------------------------------------//

    Departamento around(String nome) throws SQLException: cadastrarDepartamento(nome) {
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
    }

	Curso around(String nome, int quantidade, Departamento departamento, String tipo) throws SQLException: cadastrarCurso(nome, quantidade, departamento, tipo) {
			connection.setAutoCommit(false);
			PreparedStatement statement = connection.prepareStatement("INSERT INTO curso (nome, qtdAlunos, idDepartamento, tipo) VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, nome);
			statement.setInt(2, quantidade);
			statement.setInt(3, departamento.getIdDepartamento());
			statement.setString(4, tipo);
			statement.executeUpdate();

			ResultSet resultSet = statement.getGeneratedKeys();

			if (resultSet.next()) {
				Curso curso = new Curso();
				curso.setIdCurso(resultSet.getInt(1));
				curso.setNome(nome);
				curso.setQuantAlunos(quantidade);
				curso.setTipo(tipo);

				connection.commit();

				return curso;
			} else {
				return null;
			}
	}

    Disciplina around(String nome, String tipo, int cargaHoraria, int creditos) throws SQLException: cadastrarDisciplina(nome, tipo, cargaHoraria, creditos) {
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement("INSERT INTO disciplina (nome, tipo, cargaHoraria, creditos) VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, nome);
            statement.setString(2, tipo);
            statement.setInt(3, cargaHoraria);
            statement.setInt(4, creditos);
            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();

            if (resultSet.next()) {
                Disciplina disciplina = new Disciplina();
                disciplina.setIdDisciplina(resultSet.getInt(1));
                disciplina.setNome(nome);
                disciplina.setTipo(tipo);
                disciplina.setCreditos(creditos);
                disciplina.setCargaHoraria(cargaHoraria);

                connection.commit();

                return disciplina;
            } else {
                return null;
            }
    }

    ProjetoPesquisa around(String nome, String modalidade, String organizacao, double valorBolsa, int nVagas) throws Exception:
        cadastrarProjetoPesquisa(nome, modalidade, organizacao, valorBolsa, nVagas) {
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement("INSERT INTO projetoPesquisa (nome, modalidade, organizacao, valorBolsa, nVagas) VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, nome);
            statement.setString(2, modalidade);
            statement.setString(3, organizacao);
            statement.setDouble(4, valorBolsa);
            statement.setInt(5, nVagas);
            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();

            if (resultSet.next()) {
                ProjetoPesquisa projetoPesquisa = new ProjetoPesquisa();
                projetoPesquisa.setIdProjeto(resultSet.getInt(1));
                projetoPesquisa.setNome(nome);
                projetoPesquisa.setModalidade(modalidade);
                projetoPesquisa.setOrganizacao(organizacao);
                projetoPesquisa.setValorBolsa(valorBolsa);
                projetoPesquisa.setnVagas(nVagas);

                connection.commit();

                return projetoPesquisa;
            } else {
                return null;
            }
    }

    SolicitacaoProjeto around(int idProjeto, String cpf) throws Exception:
            enviarSolicitacao(idProjeto, cpf) {
            System.out.println(cpf);
            System.out.println(idProjeto);

            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement("INSERT INTO solicitacaoProjeto (cpfAluno, idProjeto, estado) VALUES (?, ?, 0)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, cpf);
            statement.setInt(2, idProjeto);
            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();

            if (resultSet.next()) {
                SolicitacaoProjeto solicitacaoProjeto = new SolicitacaoProjeto();
                solicitacaoProjeto.setIdSolicitacao(resultSet.getInt(1));
                solicitacaoProjeto.setCpfAluno(cpf);
                solicitacaoProjeto.setIdProjeto(idProjeto);
                solicitacaoProjeto.setEstado(0);

                connection.commit();

                return solicitacaoProjeto;
            } else {
                return null;
            }
    }

    Aviso around(String idRemetente, String titulo, String descricao, int prioridade, String dataEnvio, String horaEnvio, int idDestinatarioO, String idDestinatarioU) throws SQLException:
            cadastrarAviso(idRemetente, titulo, descricao, prioridade, dataEnvio, horaEnvio, idDestinatarioO, idDestinatarioU) {
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement("INSERT INTO aviso (idRemetente, titulo, descricao, prioridade, dataEnvio, horaEnvio, idDestinatarioO, idDestinatarioU) VALUES (?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, idRemetente);
            statement.setString(2, titulo);
            statement.setString(3, descricao);
            statement.setInt(4, prioridade);
            statement.setString(5, dataEnvio);
            statement.setString(6, horaEnvio);
            statement.setInt(7, idDestinatarioO);
            statement.setString(8, idDestinatarioU);
            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();

            if (resultSet.next()) {
                Aviso aviso = new Aviso();
                aviso.setIdAviso(resultSet.getInt(1));
                aviso.setIdRemetente(idRemetente);
                aviso.setTitulo(titulo);
                aviso.setDescricao(descricao);
                aviso.setPrioridade(prioridade);
                aviso.setDataEnvio(dataEnvio);
                aviso.setHoraEnvio(horaEnvio);
                aviso.setIdDestinatarioO(idDestinatarioO);
                aviso.setIdDestinatarioU(idDestinatarioU);

                connection.commit();

                return aviso;
            } else {
                return null;
            }
    }

}
