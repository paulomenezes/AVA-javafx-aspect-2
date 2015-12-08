package com.ufrpe.ava.gui.controladores;

import com.ufrpe.ava.AVA;
import com.ufrpe.ava.util.Navegacao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;

public class TelaInicio extends Tela {

    @FXML
    private StackPane vistaHolder;

    public void setVista(Node node) {
        vistaHolder.getChildren().setAll(node);
    }

    @FXML
    void menuInicioAction(ActionEvent event) {
        Navegacao.carregarPainel("painelInicio");
    }

    @FXML
    void menuSairAction(ActionEvent event) {
        AVA.carregar("login");
    }

    @FXML
    void menuUsuarioAction(ActionEvent event) {
        Navegacao.carregarPainel("painelUsuarioInicio");
    }

    @FXML
    void menuCursoAction(ActionEvent event) {
        Navegacao.carregarPainel("painelCursoInicio");
    }

    @FXML
    void menuDisciplinaAction(ActionEvent event) {
        Navegacao.carregarPainel("painelDisciplinaInicio");
    }

    @FXML
    void menuProjetoPesquisaAction(ActionEvent event) {
        Navegacao.carregarPainel("painelProjetoPesquisaInicio");
    }
}