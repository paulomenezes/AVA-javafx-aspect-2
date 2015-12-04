package com.ufrpe.ava.gui.controladores;

import com.ufrpe.ava.negocio.entidades.Departamento;
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

    public static Departamento departamento;

    @FXML
    private void initialize() {
        if (departamento != null) {
            campoNome.setText(departamento.getNome());
        }
    }

    public void botaoCancelarAction() {
        Navegacao.carregarPainel("painelDepartamentoInicio");
    }

    public void botaoSalvarAction() {
        try {
            if (departamento != null) {
                if (this.avaFachada.editarDepartamento(departamento.getIdDepartamento(), campoNome.getText()) != null) {
                    Navegacao.carregarPainel("painelDepartamentoInicio");
                } else {
                    Alertas.falhaEdicao("departamento");
                    Navegacao.carregarPainel("painelDepartamentoInicio");
                }
            } else {
                if (this.avaFachada.cadastrarDepartamento(campoNome.getText()) != null) {
                    Navegacao.carregarPainel("painelDepartamentoInicio");
                } else {
                    Alertas.falhaCadastro("departamento");
                    Navegacao.carregarPainel("painelDepartamentoInicio");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
