package com.ufrpe.ava.gui.controladores;

import java.sql.SQLException;
import java.util.List;

import com.ufrpe.ava.excecoes.ListaCadastroVaziaExceptions;
import com.ufrpe.ava.negocio.entidades.Curso;
import com.ufrpe.ava.negocio.entidades.Departamento;
import com.ufrpe.ava.negocio.entidades.Disciplina;
import com.ufrpe.ava.util.Alertas;
import com.ufrpe.ava.util.Navegacao;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * Created by paulomenezes on 01/12/15.
 */
public class PainelDisciplinaAdicionar extends Tela {
    @FXML
    private TextField campoNome;

    @FXML
    private TextField campoCreditos;

    @FXML
    private TextField campoCargaHoraria;

    @FXML
    private ComboBox campoTipo;

    public static Disciplina disciplina;

    @FXML
    private void initialize() {
        campoTipo.getItems().add("Graduação");
        campoTipo.getItems().add("Pós-Graduação");

        if (disciplina != null) {
            campoNome.setText(disciplina.getNome());
            campoCargaHoraria.setText(String.valueOf(disciplina.getCargaHoraria()));
            campoCreditos.setText(String.valueOf(disciplina.getCreditos()));
            campoTipo.getSelectionModel().select(disciplina.getTipo());
        }
    }

    public void botaoCancelarAction() {
        Navegacao.carregarPainel("painelDisciplinaInicio");
    }

    public void botaoSalvarAction() {
        if (!campoNome.getText().isEmpty() && campoTipo.getSelectionModel() != null && !campoCargaHoraria.getText().isEmpty() && !campoCreditos.getText().isEmpty()) {
            try {
                if (disciplina == null) {
                    this.avaFachada.cadastrarDisciplina(campoNome.getText(), campoTipo.getSelectionModel().toString(), Integer.parseInt(campoCargaHoraria.getText().toString()), Integer.parseInt(campoCreditos.getText().toString()));
                } else {
                    this.avaFachada.editarDisciplina(disciplina.getIdDisciplina(), campoNome.getText(), campoTipo.getSelectionModel().toString(), Integer.parseInt(campoCargaHoraria.getText().toString()), Integer.parseInt(campoCreditos.getText().toString()));
                }

                Navegacao.carregarPainel("painelDisciplinaInicio");
            } catch(SQLException e) {
                System.out.println(e.getMessage());
                Navegacao.carregarPainel("painelDisciplinaInicio");
            } catch (Exception e) {
                System.out.println(e.getMessage());
                Navegacao.carregarPainel("painelDisciplinaInicio");
            }
        } else {
            Alertas.campoObrigatorio("Preencha todos os campos.");
        }
    }
}
