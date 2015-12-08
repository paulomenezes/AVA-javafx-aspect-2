package com.ufrpe.ava.gui.controladores;

import com.ufrpe.ava.excecoes.ListaCadastroVaziaExceptions;
import com.ufrpe.ava.negocio.entidades.Curso;
import com.ufrpe.ava.negocio.entidades.Usuario;
import com.ufrpe.ava.util.Navegacao;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.util.List;

public class PainelUsuarioInicio extends Tela {
    @FXML
    private Button botaoAdicionar;

    @FXML
    private Button botaoAtualizar;

    @FXML
    private Button botaoRemover;

    @FXML
    private TableView<Usuario> tabela;

    @FXML
    private TableColumn<Usuario, String> cpf;

    @FXML
    private TableColumn<Usuario, String> nome;

    @FXML
    private TableColumn<Usuario, String> email;

    @FXML
    private TableColumn<Usuario, Integer> tipo;

    @FXML
    private void initialize() {
        try {
            List<Usuario> usuarios = this.avaFachada.selecionarUsuarios();

            tabela.setItems(FXCollections.observableArrayList(usuarios));

            cpf = new TableColumn<>("CPF");
            cpf.setCellValueFactory(new PropertyValueFactory<Usuario, String>("cpf"));

            nome = new TableColumn<>("Nome");
            nome.setCellValueFactory(new PropertyValueFactory<Usuario, String>("nome"));

            tipo = new TableColumn<>("Tipo");
            tipo.setCellValueFactory(new PropertyValueFactory<Usuario, Integer>("tipo"));

            email = new TableColumn<>("E-mail");
            email.setCellValueFactory(new PropertyValueFactory<Usuario, String>("email"));

            tabela.getColumns().addAll(cpf, nome, email, tipo);

            tabela.getSelectionModel().selectedItemProperty().addListener((value, oldValue, newValue) -> {
                if (tabela.getSelectionModel().getSelectedItem() != null) {
                    botaoAtualizar.setDisable(false);
                    botaoRemover.setDisable(false);
                } else {
                    botaoAtualizar.setDisable(true);
                    botaoRemover.setDisable(true);
                }
            });
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void botaoInserirAction() {
        PainelUsuarioAdicionar.usuario = null;
        Navegacao.carregarPainel("painelUsuarioAdicionar");
    }

    public void botaoAtualizarAction() {
        if (tabela.getSelectionModel().getSelectedItem() != null) {
            try {
                PainelUsuarioAdicionar.usuario = tabela.getSelectionModel().getSelectedItem();
                Navegacao.carregarPainel("painelUsuarioAdicionar");
            } catch (Exception e) {

            }
        } else {
            botaoAtualizar.setDisable(true);
            botaoRemover.setDisable(true);
        }
    }

    public void botaoRemoverAction() {
        if (tabela.getSelectionModel().getSelectedItem() != null) {
            try {
                this.avaFachada.removerUsuario(tabela.getSelectionModel().getSelectedItem());
                tabela.getColumns().clear();

                List<Usuario> usuarios = this.avaFachada.selecionarUsuarios();
                tabela.setItems(FXCollections.observableArrayList(usuarios));

                tabela.getColumns().addAll(cpf, nome, email, tipo);

                botaoAtualizar.setDisable(true);
                botaoRemover.setDisable(true);
            } catch(SQLException e){
                e.getMessage();
            }
        } else {
            botaoAtualizar.setDisable(true);
            botaoRemover.setDisable(true);
        }
    }
}