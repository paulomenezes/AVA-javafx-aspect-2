package com.ufrpe.ava.negocio;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ufrpe.ava.excecoes.ListaCadastroVaziaExceptions;
import com.ufrpe.ava.excecoes.ObjetoNaoExistenteExcepitions;
import com.ufrpe.ava.negocio.controladores.*;
import com.ufrpe.ava.negocio.entidades.*;

/**
 * Created by paulomenezes on 01/12/15.
 */
public class AvaFachada implements IAvaFachada {
    private ControladorUsuario controladorUsuario;
    private ControladorCurso controladorCurso;
    private ControladorDisciplina controladorDiscilpina;
    private ControladorLogging controladorLogging;
    private ControladorProjetoPesquisa controladorProjetoPesquisa;
    private ControladorAviso controladorAviso;

    public AvaFachada() {
        controladorUsuario = new ControladorUsuario();
        controladorCurso = new ControladorCurso();
        controladorLogging = new ControladorLogging();
        controladorDiscilpina = new ControladorDisciplina();
        controladorProjetoPesquisa = new ControladorProjetoPesquisa();
        controladorAviso = new ControladorAviso();
    }
    

    /*FUNCOES USUARIOS ------------------------------------------------------------------------------------*/
    
    @Override
    public Usuario buscarLogin(String cpf, String senha) throws SQLException,ObjetoNaoExistenteExcepitions {
    	return controladorUsuario.buscarLogin(cpf, senha);
  
    }

    @Override
    public ArrayList<Usuario> selecionarUsuarios() throws SQLException,ListaCadastroVaziaExceptions {
        return controladorUsuario.selecionarTudo();
    }

    @Override
    public void cadastrarAluno(String cpf,String nome,String foto, String email, String senha, int grad, int codCurso)
    		throws SQLException {

        controladorUsuario.cadastrarAluno(cpf,nome,foto, email, senha,grad,codCurso);
    }

    @Override
	public void cadastrarProfessor(String cpf, String nome, String foto, String email, String senha, int grad,
			int idDpto) throws SQLException {
		
    	controladorUsuario.cadastrarProfessor(cpf, nome, foto, email, senha, grad, idDpto);
		
	}
    
    @Override
	public void matricularAluno(Matricular m) throws SQLException {
    	controladorUsuario.matricularAluno(m);
    }

    public void removerUsuario(Usuario usuario) throws SQLException {
        controladorUsuario.removerUsuario(usuario);
    }

    /*FUNCOES DEPARTAMENTO ------------------------------------------------------------------------------------*/
    
    @Override
    public Departamento cadastrarDepartamento(String nome) throws SQLException {
        return controladorCurso.cadastrarDepartamento(nome);
    }

    @Override
    public void editarDepartamento(int id, String nome) throws SQLException {
        controladorCurso.editarDepartamento(id, nome);
    }

    public ArrayList<Departamento> selecionarDepartamentos() throws SQLException, ListaCadastroVaziaExceptions {
        return controladorCurso.selecionarDepartamentos();
    }
    
    public void removerDepartamento(Departamento departamento) throws SQLException {
        controladorCurso.removerDepartamento(departamento);
   }
    
    
    
    /*FUNCOES CURSOS ------------------------------------------------------------------------------------*/
    
    public ArrayList<Curso> selecionarCursos()throws SQLException,ListaCadastroVaziaExceptions {
        return controladorCurso.selecionarCursos();
    }

    public Curso cadastrarCurso(String nome, int quantidade, Departamento departamento, String tipo) throws Exception {
        return controladorCurso.cadastrarCurso(nome, quantidade, departamento, tipo);
    }

    public void editarCurso(int idCurso, String nome, int quantidade, Departamento departamento, String tipo) throws SQLException {
        controladorCurso.editarCurso(idCurso, nome, quantidade, departamento, tipo);
    }

    public void removerCurso(Curso curso) throws SQLException {
        controladorCurso.removerCurso(curso);
    }
    
    /* FUNÇÕES  REGISTROS -----------------------------------------------------------------------------*/ 

	@Override
	public void registrarLogin(String registro) {
		
		controladorLogging.registrarLogin(registro);		
	}

	@Override
	public void registrarMatricula(String registro) {
		
		controladorLogging.registrarMatricula(registro);		
	}

	@Override
	public void registrarPersistencia(String registro) {
		
		controladorLogging.registrarPersistencia(registro);
	}
	
	public void registrarAcoesProjeto(String registro){
		
		controladorLogging.registrarAcoesProjeto(registro);
	}

    /* FUNCOES DISCIPLINAS ------------------------------------------------------------------------------------*/
    public Disciplina cadastrarDisciplina(String nome, String tipo, int cargaHoraria, int creditos,ArrayList<String> prerequisito) throws Exception {
        return controladorDiscilpina.cadastrarDisciplina(nome, tipo, cargaHoraria, creditos,prerequisito);
    }

    public void editarDisciplina(int id, String nome, String tipo, int cargaHoraria, int creditos) throws SQLException {
        controladorDiscilpina.editarDisciplina(id, nome, tipo, cargaHoraria, creditos);
    }

    public void removerDisciplina(Disciplina disciplina) throws SQLException {
        controladorDiscilpina.removerDisciplina(disciplina);
    }

    public ArrayList<Disciplina> selecionarDisciplinas() throws SQLException, ListaCadastroVaziaExceptions {
        return controladorDiscilpina.selecionarDisciplinas();
        
    }
    
    public ArrayList<DisciplinaDisponivel> disciplinasDisponiveis(String cpf)  throws SQLException,ListaCadastroVaziaExceptions{
    	
    	return controladorCurso.disciplinasDisponiveis(cpf);
    	
    }
    
	@Override
	public void removerOferta(OfertaDisciplina oferta) throws SQLException {
		
		controladorDiscilpina.removerOferta(oferta);
	}
	
	@Override
	public void cadastrarOferta(OfertaDisciplina oferta) throws SQLException {
		
		controladorDiscilpina.cadastrarOferta(oferta);
	}
	
	 public ArrayList<OfertaDisciplina> selecionarOfertas()throws SQLException,ListaCadastroVaziaExceptions{
		 
		 return controladorDiscilpina.selecionarOfertas();
	 }

	 public void cadastrarMinistrarOferta(MinistrarOferta ministra)throws SQLException{
		 
		 controladorDiscilpina.cadastrarMinistrarOferta(ministra);
	 }
	 
	 @Override
	 public ArrayList<OfertaProfessor> ofertaProfessor(String cpf) throws SQLException, ListaCadastroVaziaExceptions {
		return controladorDiscilpina.ofertaProfessor(cpf);
	}


	@Override
	public ArrayList<OfertaAluno> ofertaAluno(int idOferta) throws SQLException, ListaCadastroVaziaExceptions {
		return controladorDiscilpina.ofertaAluno(idOferta);
	}


	@Override
	public Nota buscarNota(String cpfAluno, int idOferta) throws SQLException, ListaCadastroVaziaExceptions {
		return controladorDiscilpina.buscarNota(cpfAluno, idOferta);
	}

	
	@Override
	public void alterarNota(Nota notaNova) throws SQLException {
		
		controladorDiscilpina.alterarNota(notaNova);
	}
    /* FUNCOES PROJETO PESQUISA ------------------------------------------------------------------------------------*/
    public ProjetoPesquisa cadastrarProjetoPesquisa(String nome, String modalidade, String organizacao, double valorBolsa, int nVagas) throws Exception {
        return controladorProjetoPesquisa.cadastrarProjetoPesquisa(nome, modalidade, organizacao, valorBolsa, nVagas);
    }

    public void editarProjetoPesquisa(int id, String nome, String modalidade, String organizacao, double valorBolsa, int nVagas) throws SQLException {
        controladorProjetoPesquisa.editarProjetoPesquisa(id, nome, modalidade, organizacao, valorBolsa, nVagas);
    }

    public void removerProjetoPesquisa(ProjetoPesquisa projetoPesquisa) throws SQLException {
        controladorProjetoPesquisa.removerProjetoPesquisa(projetoPesquisa);
    }

    public List<ProjetoPesquisa> selecionarProjetoPesquisas() throws SQLException, ListaCadastroVaziaExceptions {
        return controladorProjetoPesquisa.selecionarProjetoPesquisas();
    }
    
    public SolicitacaoProjeto enviarSolicitacaoProjeto(int idProjeto, String cpf) throws Exception {
        return controladorProjetoPesquisa.enviarSolicitacao(idProjeto, cpf);
    }

    public void aceitarSolicitacaoProjeto(String cpf) throws Exception {
         controladorProjetoPesquisa.aceitarSolicitacaoProjeto(cpf);
    }

    /* FUNCOES AVISOS ------------------------------------------------------------------------------------*/
    public Aviso cadastrarAviso(String idRemetente, String titulo, String descricao, int prioridade, String dataEnvio, String horaEnvio, int idDestinatarioO, String idDestinatarioU) throws Exception {
        return controladorAviso.cadastrarAviso(idRemetente, titulo, descricao, prioridade, dataEnvio, horaEnvio, idDestinatarioO, idDestinatarioU);
    }

    public void editarAviso(int id, String idRemetente, String titulo, String descricao, int prioridade, String dataEnvio, String horaEnvio, int idDestinatarioO, String idDestinatarioU) throws SQLException {
        controladorAviso.editarAviso(id, idRemetente, titulo, descricao, prioridade, dataEnvio, horaEnvio, idDestinatarioO, idDestinatarioU);
    }

    public void removerAviso(Aviso aviso) throws SQLException {
        controladorAviso.removerAviso(aviso);
    }

    public ArrayList<Aviso> selecionarAvisos(String cpf) throws SQLException, ListaCadastroVaziaExceptions {
        return controladorAviso.selecionarAvisos(cpf);
    }


	
	
}
