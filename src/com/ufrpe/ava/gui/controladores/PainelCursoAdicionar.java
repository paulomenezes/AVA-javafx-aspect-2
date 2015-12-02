package com.ufrpe.ava.gui.controladores;

import com.ufrpe.ava.util.Navegacao;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * Created by paulomenezes on 01/12/15.
 */
public class PainelCursoAdicionar {
    @FXML
    private TextField campoNome;

    @FXML
    private ComboBox selectDepartamento;

    @FXML
    private TextField campoLimiteAlunos;

    public void botaoCancelarAction() {
        Navegacao.carregarPainel("painelCursoInicio");
    }

    public void botaoSalvarAction() {

    }
}
