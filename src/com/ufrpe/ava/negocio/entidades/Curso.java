package com.ufrpe.ava.negocio.entidades;

public class Curso {
	private int idCurso;

	private String nome;

    private int limiteAluno;

    private int quantAlunos;

	private Departamento idDepartamento;

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getLimiteAluno() {
        return limiteAluno;
    }

    public void setLimiteAluno(int limiteAluno) {
        this.limiteAluno = limiteAluno;
    }

    public int getQuantAlunos() {
        return quantAlunos;
    }

    public void setQuantAlunos(int quantAlunos) {
        this.quantAlunos = quantAlunos;
    }

    public Departamento getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(Departamento idDepartamento) {
        this.idDepartamento = idDepartamento;
    }
}
