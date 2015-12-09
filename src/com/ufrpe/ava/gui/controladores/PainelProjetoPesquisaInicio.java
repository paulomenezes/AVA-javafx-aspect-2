package com.ufrpe.ava.gui.controladores;

import com.ufrpe.ava.excecoes.ListaCadastroVaziaExceptions;
import com.ufrpe.ava.negocio.entidades.Disciplina;
import com.ufrpe.ava.negocio.entidades.ProjetoPesquisa;
import com.ufrpe.ava.negocio.entidades.Usuario;
import com.ufrpe.ava.util.Alertas;
import com.ufrpe.ava.util.Navegacao;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by paulomenezes on 08/12/15.
 */
public class PainelProjetoPesquisaInicio extends Tela {
    @FXML
    private Button botaoAdicionar;

    @FXML
    private Button botaoAtualizar;

    @FXML
    private Button botaoRemover;

    @FXML
    private Button botaoEnviarSolicitacao;

    @FXML
    private TableView<ProjetoPesquisa> tabela;

    @FXML
    private TableColumn<ProjetoPesquisa, Integer> id;

    @FXML
    private TableColumn<ProjetoPesquisa, String> nome;

    @FXML
    private TableColumn<ProjetoPesquisa, String> modalidade;

    @FXML
    private TableColumn<ProjetoPesquisa, String> organizacao;

    @FXML
    private TableColumn<ProjetoPesquisa, Double> valorBolsa;

    @FXML
    private TableColumn<ProjetoPesquisa, Integer> nVagas;

    @FXML
    private void initialize() {
        try {
            List<ProjetoPesquisa> projetoPesquisas = this.avaFachada.selecionarProjetoPesquisas();

            tabela.setItems(FXCollections.observableArrayList(projetoPesquisas));

            id = new TableColumn<>("ID");
            id.setCellValueFactory(new PropertyValueFactory<ProjetoPesquisa, Integer>("idProjeto"));

            nome = new TableColumn<>("Nome");
            nome.setCellValueFactory(new PropertyValueFactory<ProjetoPesquisa, String>("nome"));

            modalidade = new TableColumn<>("Modalidade");
            modalidade.setCellValueFactory(new PropertyValueFactory<ProjetoPesquisa, String>("modalidade"));

            organizacao = new TableColumn<>("Organização");
            organizacao.setCellValueFactory(new PropertyValueFactory<ProjetoPesquisa, String>("organizacao"));

            valorBolsa = new TableColumn<>("Valor Bolsa");
            valorBolsa.setCellValueFactory(new PropertyValueFactory<ProjetoPesquisa, Double>("valorBolsa"));

            nVagas = new TableColumn<>("N Vagas");
            nVagas.setCellValueFactory(new PropertyValueFactory<ProjetoPesquisa, Integer>("nVagas"));

            tabela.getColumns().addAll(id, nome, modalidade, organizacao, valorBolsa, nVagas);

            tabela.getSelectionModel().selectedItemProperty().addListener((value, oldValue, newValue) -> {
                if (tabela.getSelectionModel().getSelectedItem() != null) {
                    botaoAtualizar.setDisable(false);
                    botaoRemover.setDisable(false);
                    botaoEnviarSolicitacao.setDisable(false);
                } else {
                    botaoAtualizar.setDisable(true);
                    botaoRemover.setDisable(true);
                    botaoEnviarSolicitacao.setDisable(true);
                }
            });
        }catch(ListaCadastroVaziaExceptions e){
            System.out.println(e.getMessage());
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public void botaoInserirAction() {
        PainelProjetoPesquiasAdicionar.projetoPesquisa = null;
        Navegacao.carregarPainel("painelProjetoPesquisaAdicionar");
    }

    public void botaoAtualizarAction() {
        if (tabela.getSelectionModel().getSelectedItem() != null) {
            try {
                PainelProjetoPesquiasAdicionar.projetoPesquisa = tabela.getSelectionModel().getSelectedItem();
                Navegacao.carregarPainel("painelProjetoPesquisaAdicionar");
            } catch (Exception e) {

            }
        } else {
            botaoAtualizar.setDisable(true);
            botaoRemover.setDisable(true);
            botaoEnviarSolicitacao.setDisable(true);
        }
    }

    public void botaoRemoverAction() {
        if (tabela.getSelectionModel().getSelectedItem() != null) {
            try {
                this.avaFachada.removerProjetoPesquisa(tabela.getSelectionModel().getSelectedItem());
                this.avaFachada.registrarLogin("Projeto"+ tabela.getSelectionModel().getSelectedItem().getNome() +" - Foi Removido do Sistema" ) ;
                tabela.getColumns().clear();

                List<ProjetoPesquisa> projetoPesquisas = this.avaFachada.selecionarProjetoPesquisas();
                tabela.setItems(FXCollections.observableArrayList(projetoPesquisas));

                tabela.getColumns().addAll(id, nome, modalidade, organizacao, valorBolsa, nVagas);

                botaoAtualizar.setDisable(true);
                botaoRemover.setDisable(true);
                botaoEnviarSolicitacao.setDisable(true);
            } catch(ListaCadastroVaziaExceptions e){
                System.out.println(e.getMessage());
            } catch(SQLException e){
                e.getMessage();
            }
        } else {
            botaoAtualizar.setDisable(true);
            botaoRemover.setDisable(true);
            botaoEnviarSolicitacao.setDisable(true);
        }
    }

    public void botaoEnviarSolicitacaoAction() {
        try {
            if (this.avaFachada.enviarSolicitacaoProjeto(tabela.getSelectionModel().getSelectedItem().getIdProjeto(), Tela.usuarioAtivo.getCPF()) != null) {
                Alertas.sucesso("Solicitação de projeto enviada com sucesso, aguardando a resposta do professor responsável.");
                Navegacao.carregarPainel("painelInicio");
            } else {
                Alertas.falhaCadastro("solicitação do projeto.");
            }
        } catch (Exception e) {
            
        	System.out.println(e.getMessage());
            Alertas.falhaCadastro("solicitação do projeto.");
            botaoAtualizar.setDisable(true);
            botaoRemover.setDisable(true);
            botaoEnviarSolicitacao.setDisable(true);
        }
    }
}