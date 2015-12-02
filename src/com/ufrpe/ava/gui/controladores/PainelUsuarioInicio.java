package com.ufrpe.ava.gui.controladores;

import com.ufrpe.ava.util.Navegacao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class PainelUsuarioInicio {

    @FXML
    void previousPane(ActionEvent event) {
        Navegacao.carregarPainel("painelInicio");
    }
}