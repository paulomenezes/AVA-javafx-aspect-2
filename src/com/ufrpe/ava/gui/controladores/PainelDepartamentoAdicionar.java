package com.ufrpe.ava.gui.controladores;

import java.sql.SQLException;

import com.ufrpe.ava.negocio.entidades.Departamento;
import com.ufrpe.ava.util.Alertas;
import com.ufrpe.ava.util.Navegacao;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * Created by paulomenezes on 01/12/15.
 */
public class PainelDepartamentoAdicionar extends Tela {
    @FXML
    private TextField campoNome;

    public static Departamento departamento;

    @FXML
    private void initialize() {
        if (departamento != null) {
            campoNome.setText(departamento.getNome());
        }
    }

    public void botaoCancelarAction() {
        Navegacao.carregarPainel("painelDepartamentoInicio");
    }

    public void botaoSalvarAction() {
        if (!campoNome.getText().isEmpty()) {
            try {
                if (departamento == null) {
                    this.avaFachada.cadastrarDepartamento(campoNome.getText());
                    this.avaFachada.registrarPersistencia("Departamento - "+ campoNome.getText() +"foi Adicionado no sistema.") ;
                } else {
                    this.avaFachada.editarDepartamento(departamento.getIdDepartamento(), campoNome.getText());
                }

                Navegacao.carregarPainel("painelDepartamentoInicio");
            } catch(SQLException e) {
                System.out.println(e.getMessage());
                Navegacao.carregarPainel("painelDepartamentoInicio");
            } catch (Exception e) {
                System.out.println(e.getMessage());
                Navegacao.carregarPainel("painelDepartamentoInicio");
            }
        } else {
            Alertas.campoObrigatorio("Preencha todos os campos.");
        }
    }
}
