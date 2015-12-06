package com.ufrpe.ava.negocio.entidades;

public class Aviso {
	private int idAviso;
	private String cpfRemetente;
	private String titulo;
	private String descricao;
	private byte prioridade;
	private String dataEnvio;
	private String horaEnvio;
	private String idOferta; // não obrigatório
	private String cpfDestinatario; // não obri...
	
	public int getIdAviso() {
		return idAviso;
	}
	public void setIdAviso(int idAviso) {
		this.idAviso = idAviso;
	}
	public String getCpfRemetente() {
		return cpfRemetente;
	}
	public void setCpfRemetente(String cpfRemetente) {
		this.cpfRemetente = cpfRemetente;
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
	public byte getPrioridade() {
		return prioridade;
	}
	public void setPrioridade(byte prioridade) {
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
	public String getIdOferta() {
		return idOferta;
	}
	public void setIdOferta(String idOferta) {
		this.idOferta = idOferta;
	}
	public String getCpfDestinatario() {
		return cpfDestinatario;
	}
	public void setCpfDestinatario(String cpfDestinatario) {
		this.cpfDestinatario = cpfDestinatario;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cpfDestinatario == null) ? 0 : cpfDestinatario.hashCode());
		result = prime * result + ((cpfRemetente == null) ? 0 : cpfRemetente.hashCode());
		result = prime * result + ((dataEnvio == null) ? 0 : dataEnvio.hashCode());
		result = prime * result + ((horaEnvio == null) ? 0 : horaEnvio.hashCode());
		result = prime * result + idAviso;
		result = prime * result + ((idOferta == null) ? 0 : idOferta.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aviso other = (Aviso) obj;
		if (cpfDestinatario == null) {
			if (other.cpfDestinatario != null)
				return false;
		} else if (!cpfDestinatario.equals(other.cpfDestinatario))
			return false;
		if (cpfRemetente == null) {
			if (other.cpfRemetente != null)
				return false;
		} else if (!cpfRemetente.equals(other.cpfRemetente))
			return false;
		if (dataEnvio == null) {
			if (other.dataEnvio != null)
				return false;
		} else if (!dataEnvio.equals(other.dataEnvio))
			return false;
		if (horaEnvio == null) {
			if (other.horaEnvio != null)
				return false;
		} else if (!horaEnvio.equals(other.horaEnvio))
			return false;
		if (idAviso != other.idAviso)
			return false;
		if (idOferta == null) {
			if (other.idOferta != null)
				return false;
		} else if (!idOferta.equals(other.idOferta))
			return false;
		return true;
	}
}
