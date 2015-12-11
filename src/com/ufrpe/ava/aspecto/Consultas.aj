package com.ufrpe.ava.aspecto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ufrpe.ava.excecoes.ListaCadastroVaziaExceptions;
import com.ufrpe.ava.negocio.controladores.ControladorAviso;
import com.ufrpe.ava.negocio.controladores.ControladorCurso;
import com.ufrpe.ava.negocio.controladores.ControladorUsuario;
import com.ufrpe.ava.negocio.controladores.ControladorDisciplina;
import com.ufrpe.ava.negocio.controladores.ControladorProjetoPesquisa;
import com.ufrpe.ava.negocio.entidades.*;

/**
 * Created by paulomenezes on 01/12/15.
 */
public aspect Consultas extends ConexaoMySQL {

	 // POINT CUTS------------------------------------------------------------------------------------------------------------------------
    
    pointcut selecionarDepartamentos(): execution(* ControladorCurso.selecionarDepartamentos());
    pointcut selecionarCursos(): execution(* ControladorCurso.selecionarCursos());
    pointcut selecionarUsuarios(): execution(* ControladorUsuario.selecionarTudo());
    pointcut selecionarDisciplinas(): execution(* ControladorDisciplina.selecionarDisciplinas());
    pointcut selecionarOfertas(): execution(* ControladorDisciplina.selecionarOfertas());
    pointcut selecionarProjetoPesquisas(): execution(* ControladorProjetoPesquisa.selecionarProjetoPesquisas());
    pointcut selecionarAvisos(String cpf): execution(* ControladorAviso.selecionarAvisos(String)) && args(cpf);

    pointcut disciplinasDisponiveis(String cpf) : call(* ControladorCurso.disciplinasDisponiveis(..)) && args(cpf);
    pointcut ofertaProfessor(String cpf) : call(* ControladorDisciplina.ofertaProfessor(..)) && args(cpf);
    pointcut ofertaAluno(int idOferta) : call(* ControladorDisciplina.ofertaAluno(..)) && args(idOferta);
    pointcut buscarNota(String cpfAluno, int idOferta) : call(* ControladorDisciplina.buscarNota(..)) && args(cpfAluno, idOferta);
    
    //ADVICES------------------------------------------------------------------------------------------------------------------------
    
    Nota around(String cpfAluno, int idOferta) throws SQLException,ListaCadastroVaziaExceptions : buscarNota(cpfAluno,idOferta){
    	
    	PreparedStatement statement = connection.prepareStatement(" SELECT * FROM ava.nota WHERE cpfAluno = ? AND idOferta = ? ");
    	statement.setString(1, cpfAluno);
    	statement.setInt(2, idOferta);
    	
    	ResultSet resultSet = statement.executeQuery();
    	
    	Nota nota = new Nota();
    	
    	if(resultSet.next()){
    		
    		nota.setIdNota(resultSet.getInt("idNota"));
    		nota.setCpfAluno(resultSet.getString("cpfAluno"));
    		nota.setIdOferta(resultSet.getInt("idOferta"));
    		nota.setNota1(resultSet.getDouble("nota1"));
    		nota.setNota2(resultSet.getDouble("nota2"));
    		nota.setNota3(resultSet.getDouble("nota3"));
    		nota.setNota1(resultSet.getDouble("nota1"));
    		nota.setNotaFinal(resultSet.getDouble("notaFinal"));
    	}
    	
    	if(nota.getCpfAluno() == null){
    		
    		throw new ListaCadastroVaziaExceptions("consulta notas");
    	}
    	
    	return nota;
    }
    
    
    ArrayList<OfertaProfessor> around(String cpf) throws SQLException,ListaCadastroVaziaExceptions : ofertaProfessor(cpf){
    	
    	PreparedStatement statement = connection.prepareStatement("SELECT * FROM OfertaProfessor WHERE cpfProfessor = ?");
    	statement.setString(1, cpf);
    	ResultSet resultSet = statement.executeQuery();
    	
    	ArrayList<OfertaProfessor> ofertas = new ArrayList<>();
    	
    	while(resultSet.next()){
    		
    		OfertaProfessor o = new OfertaProfessor();
    		
    		o.setIdOferta(resultSet.getInt("idOferta"));
    		o.setNomeDisciplina(resultSet.getString("nome"));
    		o.setCpfProfessor(resultSet.getString("cpfProfessor")) ;
    		
    		ofertas.add(o);
    	}
    	
    	if(ofertas.isEmpty()){
    		
    		throw new ListaCadastroVaziaExceptions("consulta ofertas");
    	}
    	
    	return ofertas;
    	
    }
    
    
    ArrayList<OfertaAluno>  around(int idOferta) throws SQLException,ListaCadastroVaziaExceptions : ofertaAluno(idOferta){
    	
    	PreparedStatement statement = connection.prepareStatement(" SELECT * FROM OfertaAluno WHERE idOferta = ?");
    	statement.setInt(1,idOferta);
    	ResultSet resultSet = statement.executeQuery();
    	
    	ArrayList<OfertaAluno> ofertas = new ArrayList<>();
    	
    	while(resultSet.next()){
    		
    		OfertaAluno oferta = new OfertaAluno();
    		
    		oferta.setIdOferta(resultSet.getInt("idOferta"));
    		oferta.setNome(resultSet.getString("nome"));
    		oferta.setCpfAluno(resultSet.getString("cpf")) ;
    		
    		ofertas.add(oferta);
    	}
    	
    	if(ofertas.isEmpty()){
    		
    		throw new ListaCadastroVaziaExceptions("consulta ofertas");
    	}
    	
    	return ofertas;
    	
    }
    
    
    ArrayList<OfertaDisciplina> around() throws SQLException,ListaCadastroVaziaExceptions : selecionarOfertas(){
    	
    	PreparedStatement statement = connection.prepareStatement("SELECT * FROM DisciplinaOferta");
    	ResultSet resultSet = statement.executeQuery();
    	
    	ArrayList<OfertaDisciplina> ofertas = new ArrayList<>();
    	
    	while (resultSet.next()){
    		
    		OfertaDisciplina o = new OfertaDisciplina();
    		
    		o.setIdOferta(resultSet.getInt("idOferta"));
    		o.setNomeDisciplina(resultSet.getString("nome"));
    		o.setNomeCurso(resultSet.getString("nome_curso"));
    		o.setQtdAlunos(resultSet.getInt("qtdAlunos"));
    		o.setAno(resultSet.getInt("ano"));
    		o.setSemestre(resultSet.getInt("semestre"));
    		
    		ofertas.add(o);
    	}
    	
    	if(ofertas.isEmpty()){
    		
    		throw new ListaCadastroVaziaExceptions("consulta ofertas");
    	}
    	
    	return ofertas;
    	
    }
    
    
    ArrayList<DisciplinaDisponivel> around(String cpf) throws SQLException,ListaCadastroVaziaExceptions: disciplinasDisponiveis(cpf){
            PreparedStatement statement = connection.prepareStatement("SELECT  I.idOferta,I.nome,I.cargaHoraria FROM ofertasPagas AS O JOIN infoOfertas AS I ON O.idDisciplina =  I.requisito WHERE O.cpfAluno = ? ");
            statement.setString(1, cpf);
            ResultSet resultSet = statement.executeQuery();
            ArrayList<DisciplinaDisponivel> disciplinas = new ArrayList<>();


            while (resultSet.next()) {

                DisciplinaDisponivel d = new DisciplinaDisponivel();
                d.setIdOferta(resultSet.getInt("idOferta"));
                d.setNome(resultSet.getString("nome"));
                d.setCargaHoraria(resultSet.getInt("cargaHoraria"));

                disciplinas.add(d);
            }

            
            statement = connection.prepareStatement("SELECT * FROM infoOfertas WHERE requisito = 0");
            resultSet = statement.executeQuery();
            
            while (resultSet.next()) {

                DisciplinaDisponivel d = new DisciplinaDisponivel();
                d.setIdOferta(resultSet.getInt("idOferta"));
                d.setNome(resultSet.getString("nome"));
                d.setCargaHoraria(resultSet.getInt("cargaHoraria"));
                
                disciplinas.add(d);
            }


            if (disciplinas.isEmpty()) {

                throw new ListaCadastroVaziaExceptions("disciplinas Disponíveis");
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

            if (lista.isEmpty()) {

                throw new ListaCadastroVaziaExceptions("selecao Departamentos");
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
                curso.setQuantAlunos(resultSet.getInt("qtdAlunos"));
                curso.setTipo(resultSet.getString("tipo"));

                Departamento departamento = new Departamento();
                departamento.setIdDepartamento(resultSet.getInt("idDepto"));
                departamento.setNome(resultSet.getString("NomeDepartamento"));

                curso.setIdDepartamento(departamento);

                lista.add(curso);
            }

            if (lista.isEmpty()) {
                throw new ListaCadastroVaziaExceptions("selecao Cursos");
            }

            return lista;
    }

    ArrayList<Usuario> around()throws SQLException,ListaCadastroVaziaExceptions: selecionarUsuarios() {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM usuario");
            ResultSet resultSet = statement.executeQuery();

            ArrayList<Usuario> lista = new ArrayList<>();
            while (resultSet.next()) {
                Usuario usuario = new Usuario();
                usuario.setCPF(resultSet.getString("cpf"));
                usuario.setNome(resultSet.getString("nome"));
                usuario.setEmail(resultSet.getString("email"));
                usuario.setGrad(resultSet.getInt("tipo"));
                usuario.setFoto(resultSet.getString("foto"));

                lista.add(usuario);
            }

            if (lista.isEmpty()) {
                throw new ListaCadastroVaziaExceptions("selecao usuarios");
            }

            return lista;
    }

    ArrayList<Disciplina> around()throws SQLException,ListaCadastroVaziaExceptions: selecionarDisciplinas() {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM disciplina");
            ResultSet resultSet = statement.executeQuery();

            ArrayList<Disciplina> lista = new ArrayList<>();
            while (resultSet.next()) {
                Disciplina disciplina = new Disciplina();
                disciplina.setIdDisciplina(resultSet.getInt("idDisciplina"));
                disciplina.setNome(resultSet.getString("nome"));
                disciplina.setTipo(resultSet.getString("tipo"));
                disciplina.setCargaHoraria(resultSet.getInt("cargaHoraria"));
                disciplina.setCreditos(resultSet.getInt("creditos"));

                lista.add(disciplina);
            }

            if (lista.isEmpty()) {
                throw new ListaCadastroVaziaExceptions("selecao disciplinas");
            }

            return lista;
    }

    ArrayList<ProjetoPesquisa> around() throws SQLException, ListaCadastroVaziaExceptions: selecionarProjetoPesquisas() {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM projetoPesquisa");
            ResultSet resultSet = statement.executeQuery();

            System.out.println(resultSet);

            ArrayList<ProjetoPesquisa> lista = new ArrayList<>();
            while (resultSet.next()) {
                ProjetoPesquisa projetoPesquisa = new ProjetoPesquisa();
                projetoPesquisa.setIdProjeto(resultSet.getInt("idProjeto"));
                projetoPesquisa.setNome(resultSet.getString("nome"));
                projetoPesquisa.setModalidade(resultSet.getString("modalidade"));
                projetoPesquisa.setOrganizacao(resultSet.getString("organizacao"));
                projetoPesquisa.setValorBolsa(resultSet.getDouble("valorBolsa"));
                projetoPesquisa.setnVagas(resultSet.getInt("nVagas"));

                lista.add(projetoPesquisa);
            }

            if (lista.isEmpty()) {
                throw new ListaCadastroVaziaExceptions("selecao projeto pesquisa");
            }

            return lista;
    }

    ArrayList<Aviso> around(String cpf) throws SQLException, ListaCadastroVaziaExceptions: selecionarAvisos(cpf) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM aviso WHERE idDestinatarioU = ? or idDestinatarioU is null ORDER BY idAviso DESC LIMIT 20");
            statement.setString(1, cpf);

            ResultSet resultSet = statement.executeQuery();

            ArrayList<Aviso> lista = new ArrayList<>();
            while (resultSet.next()) {
                Aviso aviso = new Aviso();
                aviso.setIdAviso(resultSet.getInt("idAviso"));
                aviso.setIdRemetente(resultSet.getString("idRemetente"));
                aviso.setTitulo(resultSet.getString("titulo"));
                aviso.setDescricao(resultSet.getString("descricao"));
                aviso.setPrioridade(resultSet.getInt("prioridade"));
                aviso.setDataEnvio(resultSet.getString("dataEnvio"));
                aviso.setHoraEnvio(resultSet.getString("horaEnvio"));
                aviso.setIdDestinatarioO(resultSet.getInt("idDestinatarioO"));
                aviso.setIdDestinatarioU(resultSet.getString("idDestinatarioU"));

                lista.add(aviso);
            }

            if (lista.isEmpty()) {
                throw new ListaCadastroVaziaExceptions("selecao projeto pesquisa");
            }

            return lista;
    }
}
