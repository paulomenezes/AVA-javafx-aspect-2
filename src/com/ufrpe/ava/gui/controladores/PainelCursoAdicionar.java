package com.ufrpe.ava.gui.controladores;

import java.sql.SQLException;
import java.util.List;

import com.ufrpe.ava.excecoes.ListaCadastroVaziaExceptions;
import com.ufrpe.ava.negocio.entidades.Curso;
import com.ufrpe.ava.negocio.entidades.Departamento;
import com.ufrpe.ava.util.Alertas;
import com.ufrpe.ava.util.Navegacao;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * Created by paulomenezes on 01/12/15.
 */
public class PainelCursoAdicionar extends Tela {
    @FXML
    private TextField campoNome;

    @FXML
    private ChoiceBox<Departamento> selectDepartamento;

    @FXML
    private TextField campoQuantidade;

    @FXML
    private ComboBox campoTipo;

    public static Curso curso;

    @FXML
    public void initialize() {
        try {
            List<Departamento> departamentos = this.avaFachada.selecionarDepartamentos();

            selectDepartamento.getItems().addAll(departamentos);

            campoTipo.getItems().add("Graduação");
            campoTipo.getItems().add("Pós-Graduação");

            if (curso != null) {
                for (int i = 0; i < departamentos.size(); i++) {
                    if (departamentos.get(i).getIdDepartamento() == curso.getIdDepartamento().getIdDepartamento()) {
                        selectDepartamento.getSelectionModel().select(i);
                        break;
                    }
                }

                campoNome.setText(curso.getNome());
                campoQuantidade.setText(String.valueOf(curso.getQuantAlunos()));
                campoTipo.getSelectionModel().select(curso.getTipo());
            }
        } catch (ListaCadastroVaziaExceptions e) {
            System.out.println(e.getMessage());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void botaoCancelarAction() {
        Navegacao.carregarPainel("painelCursoInicio");
    }

    @FXML
    public void botaoSalvarAction() {
        if (!campoNome.getText().isEmpty() && !campoQuantidade.getText().isEmpty() && selectDepartamento.getValue() != null && campoTipo.getValue() != null) {
            try {
                if (curso == null) {
                    this.avaFachada.cadastrarCurso(campoNome.getText(), Integer.parseInt(campoQuantidade.getText()), selectDepartamento.getValue(), campoTipo.getValue().toString());
                    this.avaFachada.registrarPersistencia("Curso - "+ campoNome.getText() + " -  Foi Cadastrado no Sistema.") ;
                } else {
                    this.avaFachada.editarCurso(curso.getIdCurso(), campoNome.getText(), Integer.parseInt(campoQuantidade.getText()), selectDepartamento.getValue(), campoTipo.getValue().toString());
                    this.avaFachada.registrarPersistencia("Curso - "+ campoNome.getText() + " -  Foi aterado no Sistema.") ;
                }

                Navegacao.carregarPainel("painelCursoInicio");
            } catch(Exception e) {
            	System.out.println(e.getMessage());
                Alertas.campoInvalido("Digite apenas números na quantidade de alunos.");
            }
        } else {
            Alertas.campoObrigatorio("Preencha todos os campos.");
        }
    }
}
