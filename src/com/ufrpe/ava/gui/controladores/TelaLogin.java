package com.ufrpe.ava.gui.controladores;

import com.ufrpe.ava.AVA;
import com.ufrpe.ava.excecoes.ObjetoNaoExistenteExcepitions;
import com.ufrpe.ava.util.Validacao;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TelaLogin extends Tela {
    @FXML
    private TextField campoCPF;

    @FXML
    private TextField campoSenha;

    @FXML
    private Button botaoEsqueciSenha;

    @FXML
    private Button botaoCriarConta;

    public void botaoEntrarAction() {
        ArrayList<String>listaValidacao = new ArrayList<>();
        listaValidacao.add(campoCPF.getText());
        listaValidacao.add(campoSenha.getText());

        if (Validacao.validarCampos(listaValidacao)) {
            try {
                this.usuarioAtivo = this.avaFachada.buscarLogin(campoCPF.getText(), campoSenha.getText());
                AVA.carregar("inicio");
            } catch (ObjetoNaoExistenteExcepitions e) {
                System.out.println(e.getMessage());
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Campos obrigatórios");
            alert.setHeaderText("Por favor preencher o CPF e a senha");
            alert.showAndWait();
        }
    }

    public void botaoEsqueciSenhaAction() {
        System.out.println("Esqueci a senha");
    }

    public void botaoCriarContaAction() {
        System.out.println("Criar conta");

        AVA.carregar("cadastro");
    }
}
