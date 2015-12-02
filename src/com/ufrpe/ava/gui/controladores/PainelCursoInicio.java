package com.ufrpe.ava.gui.controladores;

import com.ufrpe.ava.util.Navegacao;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Created by paulomenezes on 01/12/15.
 */
public class PainelCursoInicio {
    @FXML
    private Button botaoAdicionar;

    @FXML
    private Button botaoAtualizar;

    @FXML
    private Button botaoRemover;

    @FXML
    private Button botaoDepartamento;

    public void botaoInserirAction() {
        Navegacao.carregarPainel("painelCursoAdicionar");
    }

    public void botaoAtualizarAction() {

    }

    public void botaoRemoverAction() {

    }

    public void botaoDepartamentoAction() {
        Navegacao.carregarPainel("painelDepartamentoInicio");
    }
}
