package com.ufrpe.ava.negocio.entidades;

/**
 * Created by paulomenezes on 20/11/15.
 */
public class Departamento {
    private String nome;
    private int idDepartamento;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(int idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    @Override
    public String toString() {
        return nome;
    }
}
