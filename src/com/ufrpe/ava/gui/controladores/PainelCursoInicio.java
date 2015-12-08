package com.ufrpe.ava.gui.controladores;

import java.sql.SQLException;
import java.util.List;

import com.ufrpe.ava.excecoes.ListaCadastroVaziaExceptions;
import com.ufrpe.ava.negocio.entidades.Curso;
import com.ufrpe.ava.negocio.entidades.Departamento;
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
public class PainelCursoInicio extends Tela {
    @FXML
    private Button botaoAdicionar;

    @FXML
    private Button botaoAtualizar;

    @FXML
    private Button botaoRemover;

    @FXML
    private Button botaoDepartamento;

    @FXML
    private TableView<Curso> tabela;

    @FXML
    private TableColumn<Curso, Integer> id;

    @FXML
    private TableColumn<Curso, String> nome;

    @FXML
    private TableColumn<Curso, Integer> qtdAluno;

    @FXML
    private TableColumn<Curso, String> departamento;

    @FXML
    private TableColumn<Curso, String> tipo;

    @FXML
    private void initialize() {
        try {
            List<Curso> cursos = this.avaFachada.selecionarCursos();

            tabela.setItems(FXCollections.observableArrayList(cursos));

            id = new TableColumn<>("ID");
            id.setCellValueFactory(new PropertyValueFactory<Curso, Integer>("idCurso"));

            nome = new TableColumn<>("Nome");
            nome.setCellValueFactory(new PropertyValueFactory<Curso, String>("nome"));

            qtdAluno = new TableColumn<>("Quantidade Alunos");
            qtdAluno.setCellValueFactory(new PropertyValueFactory<Curso, Integer>("quantAlunos"));

            departamento = new TableColumn<>("Departamento");
            departamento.setCellValueFactory(new PropertyValueFactory<Curso, String>("idDepartamento"));

            tipo = new TableColumn<>("Tipo");
            tipo.setCellValueFactory(new PropertyValueFactory<Curso, String>("tipo"));

            tabela.getColumns().addAll(id, nome, qtdAluno, departamento, tipo);

            tabela.getSelectionModel().selectedItemProperty().addListener((value, oldValue, newValue) -> {
                if (tabela.getSelectionModel().getSelectedItem() != null) {
                    botaoAtualizar.setDisable(false);
                    botaoRemover.setDisable(false);
                } else {
                    botaoAtualizar.setDisable(true);
                    botaoRemover.setDisable(true);
                }
            });
        } catch(ListaCadastroVaziaExceptions e) {
            System.out.println(e.getMessage());
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void botaoInserirAction() {
        PainelCursoAdicionar.curso = null;
        Navegacao.carregarPainel("painelCursoAdicionar");
    }

    public void botaoAtualizarAction() {
        if (tabela.getSelectionModel().getSelectedItem() != null) {
            try {
                PainelCursoAdicionar.curso = tabela.getSelectionModel().getSelectedItem();
                Navegacao.carregarPainel("painelCursoAdicionar");
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
                this.avaFachada.removerCurso(tabela.getSelectionModel().getSelectedItem());
                tabela.getColumns().clear();

                List<Curso> cursos = this.avaFachada.selecionarCursos();
                tabela.setItems(FXCollections.observableArrayList(cursos));

                tabela.getColumns().addAll(id, nome, qtdAluno, departamento, tipo);

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

    public void botaoDepartamentoAction() {
        Navegacao.carregarPainel("painelDepartamentoInicio");
    }
}
