package com.ufrpe.ava.gui.controladores;

import com.ufrpe.ava.negocio.entidades.Departamento;
import com.ufrpe.ava.negocio.entidades.Usuario;
import com.ufrpe.ava.util.Navegacao;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

/**
 * Created by paulomenezes on 01/12/15.
 */
public class PainelDepartamentoInicio extends Tela {
    @FXML
    private Button botaoAdicionar;

    @FXML
    private Button botaoAtualizar;

    @FXML
    private Button botaoRemover;

    @FXML
    private TableView<Departamento> tabela;

    @FXML
    private TableColumn<Departamento, Integer> id;

    @FXML
    private TableColumn<Departamento, String> nome;

    private Departamento linhaSelecionada = null;

    @FXML
    private void initialize() {
        List<Departamento> departamentos = this.avaFachada.selecionarDepartamentos();

        if (departamentos != null) {
            tabela.setItems(FXCollections.observableArrayList(departamentos));

            id = new TableColumn<>("ID");
            id.setCellValueFactory(new PropertyValueFactory<Departamento, Integer>("idDepartamento"));

            nome = new TableColumn<>("Nome");
            nome.setCellValueFactory(new PropertyValueFactory<Departamento, String>("nome"));

            tabela.getColumns().addAll(id, nome);

            tabela.getSelectionModel().selectedItemProperty().addListener((value, oldValue, newValue) -> {
                botaoAtualizar.setDisable(false);
                botaoRemover.setDisable(false);

                linhaSelecionada = newValue;
            });
        } else {
            System.out.println("Nenhum departamento adicionado.");
        }
    }

    public void botaoInserirAction() {
        Navegacao.carregarPainel("painelDepartamentoAdicionar");
    }

    public void botaoAtualizarAction() {

    }

    public void botaoRemoverAction() {
        if (linhaSelecionada != null) {
            try {
                if (this.avaFachada.removerDepartamento(linhaSelecionada)) {
                    tabela.getColumns().clear();

                    List<Departamento> departamentos = this.avaFachada.selecionarDepartamentos();
                    tabela.setItems(FXCollections.observableArrayList(departamentos));

                    tabela.getColumns().addAll(id, nome);

                    botaoAtualizar.setDisable(true);
                    botaoRemover.setDisable(true);
                } else {

                }
            } catch (Exception e) {

            }
        } else {
            botaoAtualizar.setDisable(true);
            botaoRemover.setDisable(true);
        }
    }
}
