package com.ufrpe.ava.gui.controladores;

import com.ufrpe.ava.util.Alertas;
import com.ufrpe.ava.util.Navegacao;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * Created by paulomenezes on 01/12/15.
 */
public class PainelDepartamentoAdicionar extends Tela {
    @FXML
    private TextField campoNome;

    public void botaoCancelarAction() {
        Navegacao.carregarPainel("painelDepartamentoInicio");
    }

    public void botaoSalvarAction() {
        try {
            if (this.avaFachada.cadastrarDepartamento(campoNome.getText()) != null) {
                Navegacao.carregarPainel("painelDepartamentoInicio");
            } else {
                Alertas.falhaCadastro("departamento");
                Navegacao.carregarPainel("painelDepartamentoInicio");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
