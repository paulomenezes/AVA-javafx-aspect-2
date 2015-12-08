package com.ufrpe.ava.gui.controladores;

import java.sql.SQLException;
import java.util.List;

import com.ufrpe.ava.excecoes.ListaCadastroVaziaExceptions;
import com.ufrpe.ava.negocio.entidades.Departamento;
import com.ufrpe.ava.negocio.entidades.Disciplina;
import com.ufrpe.ava.util.Navegacao;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Created by paulomenezes on 01/12/15.
 */
public class PainelDisciplinaInicio extends Tela {
    @FXML
    private Button botaoAdicionar;

    @FXML
    private Button botaoAtualizar;

    @FXML
    private Button botaoRemover;

    @FXML
    private TableView<Disciplina> tabela;

    @FXML
    private TableColumn<Disciplina, Integer> id;

    @FXML
    private TableColumn<Disciplina, String> nome;

    @FXML
    private TableColumn<Disciplina, Integer> tipo;

    @FXML
    private TableColumn<Disciplina, Integer> cargaHoraria;

    @FXML
    private TableColumn<Disciplina, Integer> creditos;

    @FXML
    private void initialize() {
        try {
            List<Disciplina> disciplinas = this.avaFachada.selecionarDisciplinas();

            tabela.setItems(FXCollections.observableArrayList(disciplinas));

            id = new TableColumn<>("ID");
            id.setCellValueFactory(new PropertyValueFactory<Disciplina, Integer>("idDisciplina"));

            nome = new TableColumn<>("Nome");
            nome.setCellValueFactory(new PropertyValueFactory<Disciplina, String>("nome"));

            tipo = new TableColumn<>("Tipo");
            tipo.setCellValueFactory(new PropertyValueFactory<Disciplina, Integer>("tipo"));

            cargaHoraria = new TableColumn<>("Carga Horária");
            cargaHoraria.setCellValueFactory(new PropertyValueFactory<Disciplina, Integer>("cargaHoraria"));

            creditos = new TableColumn<>("Créditos");
            creditos.setCellValueFactory(new PropertyValueFactory<Disciplina, Integer>("creditos"));

            tabela.getColumns().addAll(id, nome, tipo, cargaHoraria, creditos);

            tabela.getSelectionModel().selectedItemProperty().addListener((value, oldValue, newValue) -> {
                if (tabela.getSelectionModel().getSelectedItem() != null) {
                    botaoAtualizar.setDisable(false);
                    botaoRemover.setDisable(false);
                } else {
                    botaoAtualizar.setDisable(true);
                    botaoRemover.setDisable(true);
                }
            });
        }catch(ListaCadastroVaziaExceptions e){
            System.out.println(e.getMessage());
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public void botaoInserirAction() {
        PainelDisciplinaAdicionar.disciplina = null;
        Navegacao.carregarPainel("painelDisciplinaAdicionar");
    }

    public void botaoAtualizarAction() {
        if (tabela.getSelectionModel().getSelectedItem() != null) {
            try {
                PainelDisciplinaAdicionar.disciplina = tabela.getSelectionModel().getSelectedItem();
                Navegacao.carregarPainel("painelDisciplinaAdicionar");
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
                this.avaFachada.removerDisciplina(tabela.getSelectionModel().getSelectedItem());
                this.avaFachada.registrarPersistencia("A disciplina -" +tabela.getSelectionModel().getSelectedItem().getNome()+"- Foi Excluida do sistema");
                tabela.getColumns().clear();

                List<Disciplina> disciplinas = this.avaFachada.selecionarDisciplinas();
                tabela.setItems(FXCollections.observableArrayList(disciplinas));

                tabela.getColumns().addAll(id, nome, tipo, cargaHoraria, creditos);

                botaoAtualizar.setDisable(true);
                botaoRemover.setDisable(true);
            } catch(ListaCadastroVaziaExceptions e){

                System.out.println(e.getMessage());

            } catch(SQLException e){

                e.getMessage();
            }

        } else {
            botaoAtualizar.setDisable(true);
            botaoRemover.setDisable(true);
        }
    }

}
