package com.ufrpe.ava.negocio.controladores;

import com.ufrpe.ava.excecoes.ListaCadastroVaziaExceptions;
import com.ufrpe.ava.negocio.entidades.ProjetoPesquisa;
import com.ufrpe.ava.negocio.entidades.SolicitacaoProjeto;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by paulomenezes on 08/12/15.
 */
public class ControladorProjetoPesquisa {
	
    public ArrayList<ProjetoPesquisa> selecionarProjetoPesquisas()throws SQLException,ListaCadastroVaziaExceptions {
        return new ArrayList<>();
    }

    public ProjetoPesquisa cadastrarProjetoPesquisa(String nome, String modalidade, String organizacao, double valorBolsa, int nVagas) throws Exception  {
        
    	ProjetoPesquisa projetoPesquisa = new ProjetoPesquisa();
        projetoPesquisa.setNome(nome);
        projetoPesquisa.setModalidade(modalidade);
        projetoPesquisa.setOrganizacao(organizacao);
        projetoPesquisa.setValorBolsa(valorBolsa);
        projetoPesquisa.setnVagas(nVagas);

        return projetoPesquisa;
    }

    public void editarProjetoPesquisa(int id, String nome, String modalidade, String organizacao, double valorBolsa, int nVagas) throws SQLException  {
        ProjetoPesquisa projetoPesquisa = new ProjetoPesquisa();
        projetoPesquisa.setNome(nome);
        projetoPesquisa.setModalidade(modalidade);
        projetoPesquisa.setOrganizacao(organizacao);
        projetoPesquisa.setValorBolsa(valorBolsa);
        projetoPesquisa.setnVagas(nVagas);
    }

    public void removerProjetoPesquisa(ProjetoPesquisa projetoPesquisa) throws SQLException {

    }

    public SolicitacaoProjeto enviarSolicitacao(int idProjeto, String cpf) throws Exception {
        return null;
    }

    public void aceitarSolicitacaoProjeto(int id, int estado) throws Exception {
        
    }
    
    public ArrayList<SolicitacaoProjeto> selecionarSolicitacoes(String cpf) throws Exception { 
    	return null;
    }
}
