package com.ufrpe.ava.negocio.controladores;

import java.sql.SQLException;
import java.util.ArrayList;

import com.ufrpe.ava.excecoes.ListaCadastroVaziaExceptions;
import com.ufrpe.ava.negocio.entidades.Disciplina;
import com.ufrpe.ava.negocio.entidades.MinistrarOferta;
import com.ufrpe.ava.negocio.entidades.Nota;
import com.ufrpe.ava.negocio.entidades.OfertaAluno;
import com.ufrpe.ava.negocio.entidades.OfertaDisciplina;
import com.ufrpe.ava.negocio.entidades.OfertaProfessor;

/**
 * Created by paulomenezes on 08/12/15.
 */
public class ControladorDisciplina {
    
	public ArrayList<Disciplina> selecionarDisciplinas()throws SQLException,ListaCadastroVaziaExceptions {
        return null;
    }

    public Disciplina cadastrarDisciplina(String nome, String tipo, int cargaHoraria, int creditos, ArrayList<String>prerequisito) throws Exception  {
        Disciplina disciplina = new Disciplina();
        disciplina.setNome(nome);
        disciplina.setTipo(tipo);
        disciplina.setCargaHoraria(cargaHoraria);
        disciplina.setCreditos(creditos);

        return disciplina;
    }

    public void editarDisciplina(int id, String nome, String tipo, int cargaHoraria, int creditos) throws SQLException  {
        Disciplina disciplina = new Disciplina();
        disciplina.setNome(nome);
        disciplina.setTipo(tipo);
        disciplina.setCargaHoraria(cargaHoraria);
        disciplina.setCreditos(creditos);
    }

    public void removerDisciplina(Disciplina disciplina) throws SQLException {
    	//OPERAÇÃO PELO ASPECTO REMOÇÕES
    }
    
    public ArrayList<OfertaDisciplina> selecionarOfertas()throws SQLException,ListaCadastroVaziaExceptions{
    	
    	return null;
    }
    
    public void removerOferta(OfertaDisciplina oferta)throws SQLException{	
    	//OPERAÇÃO PELO ASPECTO REMOÇÕES
    }
    
    public void cadastrarOferta(OfertaDisciplina oferta) throws SQLException{
    	//OPERAÇÃO PELO ASPECTO INSERÇÕES
    }
    
    public void cadastrarMinistrarOferta(MinistrarOferta ministra)throws SQLException{
    	//OPERAÇÃO PELO ASPECTO INSERÇÕES
    }
    
    public ArrayList<OfertaProfessor> ofertaProfessor(String cpf) throws SQLException,ListaCadastroVaziaExceptions{
    	
    	return null;
    }
    
    public ArrayList<OfertaAluno> ofertaAluno(int idOferta)throws SQLException,ListaCadastroVaziaExceptions{
    	
    	return null;
    } 
    
    public Nota buscarNota(String cpfAluno, int idOferta)throws SQLException,ListaCadastroVaziaExceptions{
    	
    	return null;
    }
    
    public void alterarNota(Nota notaNova)throws SQLException{
    	//OPERAÇÃO PELO ASPECTO ALTERAÇÕES
    }
    
}

