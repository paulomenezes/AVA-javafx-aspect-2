package com.ufrpe.ava.negocio.controladores;

import com.ufrpe.ava.excecoes.ListaCadastroVaziaExceptions;
import com.ufrpe.ava.negocio.entidades.Aviso;
import com.ufrpe.ava.negocio.entidades.SolicitacaoProjeto;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by paulomenezes on 08/12/15.
 */
public class ControladorAviso {
    public ArrayList<Aviso> selecionarAvisos(String cpf) throws SQLException, ListaCadastroVaziaExceptions {
        return null;
    }

    public Aviso cadastrarAviso(String idRemetente, String titulo, String descricao, int prioridade, String dataEnvio, String horaEnvio, int idDestinatarioO, String idDestinatarioU) throws SQLException  {
        Aviso aviso = new Aviso();
        aviso.setIdRemetente(idRemetente);
        aviso.setTitulo(titulo);
        aviso.setDescricao(descricao);
        aviso.setPrioridade(prioridade);
        aviso.setDataEnvio(dataEnvio);
        aviso.setHoraEnvio(horaEnvio);
        aviso.setIdDestinatarioO(idDestinatarioO);
        aviso.setIdDestinatarioU(idDestinatarioU);

        return aviso;
    }

    public void editarAviso(int idAviso, String idRemetente, String titulo, String descricao, int prioridade, String dataEnvio, String horaEnvio, int idDestinatarioO, String idDestinatarioU) throws SQLException {
        Aviso aviso = new Aviso();
        aviso.setIdAviso(idAviso);
        aviso.setIdRemetente(idRemetente);
        aviso.setTitulo(titulo);
        aviso.setDescricao(descricao);
        aviso.setPrioridade(prioridade);
        aviso.setDataEnvio(dataEnvio);
        aviso.setHoraEnvio(horaEnvio);
        aviso.setIdDestinatarioO(idDestinatarioO);
        aviso.setIdDestinatarioU(idDestinatarioU);
    }

    public void removerAviso(Aviso Aviso) throws SQLException {

    }
}
