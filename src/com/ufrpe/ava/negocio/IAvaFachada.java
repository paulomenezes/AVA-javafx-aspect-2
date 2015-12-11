package com.ufrpe.ava.negocio;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ufrpe.ava.excecoes.ListaCadastroVaziaExceptions;
import com.ufrpe.ava.excecoes.ObjetoNaoExistenteExcepitions;
import com.ufrpe.ava.negocio.entidades.*;

/**
 * Created by paulomenezes on 01/12/15.
 */
public interface IAvaFachada {
    /*FUNCOES USUARIOS ------------------------------------------------------------------------------------*/

    void cadastrarAluno(String cpf,String nome,String foto, String email, String senha, int grad, int codCurso)
        throws SQLException;

    void cadastrarProfessor(String cpf, String nome, String foto, String email,String senha, int grad, int idDepartamento)
        throws SQLException;

    Usuario buscarLogin(String cpf, String senha) throws SQLException, ObjetoNaoExistenteExcepitions;

    ArrayList<Usuario> selecionarUsuarios() throws SQLException,ListaCadastroVaziaExceptions;

    void matricularAluno(Matricular m) throws SQLException;

    void removerUsuario(Usuario selectedItem) throws SQLException;


    /* FUNCOES DEPARTAMENTOS ------------------------------------------------------------------------------------*/
    Departamento cadastrarDepartamento(String nome) throws SQLException;

    void editarDepartamento(int id, String nome) throws SQLException;

    void removerDepartamento(Departamento departamento) throws SQLException;

    List<Departamento> selecionarDepartamentos() throws SQLException, ListaCadastroVaziaExceptions;


    /* FUNCOES CURSOS ------------------------------------------------------------------------------------*/
    ArrayList<Curso> selecionarCursos() throws SQLException, ListaCadastroVaziaExceptions;

    Curso cadastrarCurso(String nome, int quantidade, Departamento departamento, String tipo) throws Exception;

    void editarCurso(int idCurso, String nome, int quantidade, Departamento departamento, String tipo) throws SQLException;

    void removerCurso(Curso curso) throws SQLException;

    /* FUNCOES CURSOS ------------------------------------------------------------------------------------*/
    public ArrayList<DisciplinaDisponivel> disciplinasDisponiveis(String cpf)  throws SQLException,ListaCadastroVaziaExceptions;

    /*FUNCOES LOGGGING ------------------------------------------------------------------------------------*/

    void registrarLogin(String registro);
    void registrarMatricula(String registro);
    void registrarPersistencia(String registro);
    public void registrarAcoesProjeto(String registro);

    /* FUNCOES DISCIPLINAS ------------------------------------------------------------------------------------*/
    Disciplina cadastrarDisciplina(String nome, String tipo, int cargaHoraria, int creditos,ArrayList<String> prerequisito) throws Exception;

    void editarDisciplina(int id, String nome, String tipo, int cargaHoraria, int creditos) throws SQLException;

    void removerDisciplina(Disciplina disciplina) throws SQLException;

    ArrayList<Disciplina> selecionarDisciplinas() throws SQLException, ListaCadastroVaziaExceptions;
    
    void removerOferta(OfertaDisciplina oferta)throws SQLException;
    
    void cadastrarOferta(OfertaDisciplina oferta)throws SQLException;
    
    public ArrayList<OfertaDisciplina> selecionarOfertas()throws SQLException,ListaCadastroVaziaExceptions;
    
    void cadastrarMinistrarOferta(MinistrarOferta ministra)throws SQLException;
    
    public ArrayList<OfertaProfessor> ofertaProfessor(String cpf) throws SQLException,ListaCadastroVaziaExceptions;
    
    public ArrayList<OfertaAluno> ofertaAluno(int idOferta )throws SQLException,ListaCadastroVaziaExceptions;
    
    public ArrayList<Nota> buscarNota(String cpfAluno)throws SQLException,ListaCadastroVaziaExceptions;
    
    public void alterarNota(Nota notaNova)throws SQLException;

    /* FUNCOES PROJETO PESQUISA ------------------------------------------------------------------------------------*/
    ProjetoPesquisa cadastrarProjetoPesquisa(String nome, String modalidade, String organizacao, double valorBolsa, int nVagas) throws Exception;

    void editarProjetoPesquisa(int id, String nome, String modalidade, String organizacao, double valorBolsa, int nVagas) throws SQLException;

    void removerProjetoPesquisa(ProjetoPesquisa projetoPesquisa) throws SQLException;

    List<ProjetoPesquisa> selecionarProjetoPesquisas() throws SQLException, ListaCadastroVaziaExceptions;

    SolicitacaoProjeto enviarSolicitacaoProjeto(int idProjeto, String cpf) throws Exception;
    
    ArrayList<SolicitacaoProjeto> selecionarSolicitacoes(String cpf) throws Exception;

    void aceitarSolicitacaoProjeto(int id, int estado) throws Exception;

    /* FUNCOES AVISOS ------------------------------------------------------------------------------------*/
    Aviso cadastrarAviso(String idRemetente, String titulo, String descricao, int prioridade, String dataEnvio, String horaEnvio, int idDestinatarioO, String idDestinatarioU) throws Exception;

    void editarAviso(int id, String idRemetente, String titulo, String descricao, int prioridade, String dataEnvio, String horaEnvio, int idDestinatarioO, String idDestinatarioU) throws SQLException;

    void removerAviso(Aviso aviso) throws SQLException;

    ArrayList<Aviso> selecionarAvisos(String cpf) throws SQLException, ListaCadastroVaziaExceptions;
}

