package com.ufrpe.ava.gui.controladores;

import com.ufrpe.ava.negocio.entidades.Disciplina;
import com.ufrpe.ava.negocio.entidades.ProjetoPesquisa;
import com.ufrpe.ava.util.Alertas;
import com.ufrpe.ava.util.Navegacao;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.sql.SQLException;

/**
 * Created by paulomenezes on 08/12/15.
 */
public class PainelProjetoPesquiasAdicionar extends Tela {
    @FXML
    private TextField campoNome;

    @FXML
    private TextField campoModalidade;

    @FXML
    private TextField campoOrganizacao;

    @FXML
    private TextField campoValorBolsa;

    @FXML
    private TextField campoNVagas;

    public static ProjetoPesquisa projetoPesquisa;

    @FXML
    private void initialize() {
        if (projetoPesquisa != null) {
            campoNome.setText(projetoPesquisa.getNome());
            campoModalidade.setText(projetoPesquisa.getModalidade());
            campoOrganizacao.setText(projetoPesquisa.getOrganizacao());
            campoValorBolsa.setText(String.valueOf(projetoPesquisa.getValorBolsa()));
            campoNVagas.setText(String.valueOf(projetoPesquisa.getnVagas()));
        }
    }

    public void botaoCancelarAction() {
        Navegacao.carregarPainel("painelProjetoPesquisaInicio");
    }

    public void botaoSalvarAction() {
        if (!campoNome.getText().isEmpty() && !campoModalidade.getText().isEmpty() && !campoOrganizacao.getText().isEmpty() &&
                !campoValorBolsa.getText().isEmpty() && !campoNVagas.getText().isEmpty()) {
            try {
                if (projetoPesquisa == null) {
                    this.avaFachada.cadastrarProjetoPesquisa(campoNome.getText(), campoModalidade.getText(), campoOrganizacao.getText(), Double.parseDouble(campoValorBolsa.getText().toString()), Integer.parseInt(campoNVagas.getText().toString()));
                    this.avaFachada.registrarLogin("Projeto"+ campoNome.getText()+" - Foi Cadastrado no Sistema" ) ;
                } else {
                    this.avaFachada.editarProjetoPesquisa(projetoPesquisa.getIdProjeto(), campoNome.getText(), campoModalidade.getText(), campoOrganizacao.getText(), Double.parseDouble(campoValorBolsa.getText().toString()), Integer.parseInt(campoNVagas.getText().toString()));
                    this.avaFachada.registrarLogin("Projeto"+ campoNome.getText()+" - Foi Alterado no Sistema" ) ;
                }

                Navegacao.carregarPainel("painelProjetoPesquisaInicio");
            } catch(SQLException e) {
                System.out.println(e.getMessage());
                Navegacao.carregarPainel("painelProjetoPesquisaInicio");
            } catch (Exception e) {
                System.out.println(e.getMessage());
                Navegacao.carregarPainel("painelProjetoPesquisaInicio");
            }
        } else {
            Alertas.campoObrigatorio("Preencha todos os campos.");
        }
    }
}

