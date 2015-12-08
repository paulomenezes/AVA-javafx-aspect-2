package com.ufrpe.ava.gui.controladores;

import com.ufrpe.ava.excecoes.ListaCadastroVaziaExceptions;
import com.ufrpe.ava.negocio.entidades.Curso;
import com.ufrpe.ava.negocio.entidades.Usuario;
import com.ufrpe.ava.util.Navegacao;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.util.List;

public class PainelUsuarioInicio extends Tela {
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
    private TableColumn<Usuario, String> tipo;

    @FXML
    private void initialize() {
        try {
            List<Usuario> usuarios = this.avaFachada.selecionarUsuarios();

            tabela.setItems(FXCollections.observableArrayList(usuarios));

            cpf = new TableColumn<>("CPF");
            cpf.setCellValueFactory(new PropertyValueFactory<Usuario, String>("CPF"));

            nome = new TableColumn<>("Nome");
            nome.setCellValueFactory(new PropertyValueFactory<Usuario, String>("nome"));

            tipo = new TableColumn<>("Tipo");
            tipo.setCellValueFactory(new PropertyValueFactory<Usuario, String>("tipo"));

            email = new TableColumn<>("E-mail");
            email.setCellValueFactory(new PropertyValueFactory<Usuario, String>("email"));

            tabela.getColumns().addAll(cpf, nome, email, tipo);

            tabela.getSelectionModel().selectedItemProperty().addListener((value, oldValue, newValue) -> {
                if (tabela.getSelectionModel().getSelectedItem() != null) {
                    botaoRemover.setDisable(false);
                } else {
                    botaoRemover.setDisable(true);
                }
            });
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void botaoCoordenadorAction() {
        PainelUsuarioAdicionar.tipo = -1;
        PainelUsuarioAdicionar.usuario = null;
        Navegacao.carregarPainel("painelUsuarioAdicionar");
    }

    public void botaoProfessorAction() {
        PainelUsuarioAdicionar.tipo = 0;
        PainelUsuarioAdicionar.usuario = null;
        Navegacao.carregarPainel("painelUsuarioAdicionar");
    }

    public void botaoAlunoAction() {
        PainelUsuarioAdicionar.tipo = 1;
        PainelUsuarioAdicionar.usuario = null;
        Navegacao.carregarPainel("painelUsuarioAdicionar");
    }

    public void botaoRemoverAction() {
        if (tabela.getSelectionModel().getSelectedItem() != null) {
            try {
                this.avaFachada.removerUsuario(tabela.getSelectionModel().getSelectedItem());
                tabela.getColumns().clear();

                List<Usuario> usuarios = this.avaFachada.selecionarUsuarios();
                tabela.setItems(FXCollections.observableArrayList(usuarios));

                tabela.getColumns().addAll(cpf, nome, email, tipo);

                botaoRemover.setDisable(true);
            } catch(ListaCadastroVaziaExceptions e){
                e.getMessage();
            } catch(SQLException e){
                e.getMessage();
            }
        } else {
            botaoRemover.setDisable(true);
        }
    }
}