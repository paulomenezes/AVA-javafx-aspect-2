package com.ufrpe.ava.negocio.entidades;

public class Aviso {
	private int idAviso;
	private String idRemetente;
	private String titulo;
	private String descricao;
	private int prioridade;
	private String dataEnvio;
	private String horaEnvio;
	private int idDestinatarioO; // OFERTA não obrigatório
	private String idDestinatarioU; // USUARIO não obrigatório

	public int getIdAviso() {
		return idAviso;
	}

	public void setIdAviso(int idAviso) {
		this.idAviso = idAviso;
	}

	public String getIdRemetente() {
		return idRemetente;
	}

	public void setIdRemetente(String idRemetente) {
		this.idRemetente = idRemetente;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(int prioridade) {
		this.prioridade = prioridade;
	}

	public String getDataEnvio() {
		return dataEnvio;
	}

	public void setDataEnvio(String dataEnvio) {
		this.dataEnvio = dataEnvio;
	}

	public String getHoraEnvio() {
		return horaEnvio;
	}

	public void setHoraEnvio(String horaEnvio) {
		this.horaEnvio = horaEnvio;
	}

	public int getIdDestinatarioO() {
		return idDestinatarioO;
	}

	public void setIdDestinatarioO(int idDestinatarioO) {
		this.idDestinatarioO = idDestinatarioO;
	}

	public String getIdDestinatarioU() {
		return idDestinatarioU;
	}

	public void setIdDestinatarioU(String idDestinatarioU) {
		this.idDestinatarioU = idDestinatarioU;
	}
}
