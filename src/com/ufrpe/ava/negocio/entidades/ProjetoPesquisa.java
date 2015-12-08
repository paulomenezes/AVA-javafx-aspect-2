package com.ufrpe.ava.negocio.entidades;

import java.util.ArrayList;

public class ProjetoPesquisa {
    private int idProjeto;
	private String nome;
	private String modalidade;
	private String organizacao;
    private double valorBolsa;
    private int nVagas;

    public int getIdProjeto() {
        return idProjeto;
    }

    public void setIdProjeto(int idProjeto) {
        this.idProjeto = idProjeto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getModalidade() {
        return modalidade;
    }

    public void setModalidade(String modalidade) {
        this.modalidade = modalidade;
    }

    public String getOrganizacao() {
        return organizacao;
    }

    public void setOrganizacao(String organizacao) {
        this.organizacao = organizacao;
    }

    public double getValorBolsa() {
        return valorBolsa;
    }

    public void setValorBolsa(double valorBolsa) {
        this.valorBolsa = valorBolsa;
    }

    public int getnVagas() {
        return nVagas;
    }

    public void setnVagas(int nVagas) {
        this.nVagas = nVagas;
    }
}
