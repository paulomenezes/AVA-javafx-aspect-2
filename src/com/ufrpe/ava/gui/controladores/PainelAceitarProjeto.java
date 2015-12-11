package com.ufrpe.ava.gui.controladores;

import java.sql.SQLException;
import java.util.List;

import com.ufrpe.ava.excecoes.ListaCadastroVaziaExceptions;
import com.ufrpe.ava.negocio.entidades.Departamento;
import com.ufrpe.ava.negocio.entidades.SolicitacaoProjeto;
import com.ufrpe.ava.util.Alertas;
import com.ufrpe.ava.util.Navegacao;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class PainelAceitarProjeto extends Tela {
    @FXML
    private Button botaoAceitar;

    @FXML
    private Button botaoRecusar;

    @FXML
    private TableView<SolicitacaoProjeto> tabela;

    @FXML
    private TableColumn<SolicitacaoProjeto, String> aluno;

    @FXML
    private TableColumn<SolicitacaoProjeto, String> projeto;

    @FXML
    private void initialize() {
        try{
        	List<SolicitacaoProjeto> solicitacoes = this.avaFachada.selecionarSolicitacoes(Tela.usuarioAtivo.getCPF());

            tabela.setItems(FXCollections.observableArrayList(solicitacoes));

            aluno = new TableColumn<>("aluno");
            aluno.setCellValueFactory(new PropertyValueFactory<SolicitacaoProjeto, String>("Aluno"));

            projeto = new TableColumn<>("projeto");
            projeto.setCellValueFactory(new PropertyValueFactory<SolicitacaoProjeto, String>("Projeto"));

            tabela.getColumns().addAll(aluno, projeto);

            tabela.getSelectionModel().selectedItemProperty().addListener((value, oldValue, newValue) -> {
                if (tabela.getSelectionModel().getSelectedItem() != null) {
                    botaoAceitar.setDisable(false);
                    botaoRecusar.setDisable(false);
                } else {
                    botaoAceitar.setDisable(true);
                    botaoRecusar.setDisable(true);
                }
            });
        }catch(ListaCadastroVaziaExceptions e){
        	System.out.println(e.getMessage());
        }catch (Exception e) {
			System.out.println(e.getMessage());
		}
       
    }

    public void botaoAceitarAction() {
        if (tabela.getSelectionModel().getSelectedItem() != null) {
        	try {
				this.avaFachada.aceitarSolicitacaoProjeto(tabela.getSelectionModel().getSelectedItem().getIdSolicitacao(), 1);
				Alertas.sucesso("Solicitação aprovada");
				Navegacao.carregarPainel("painelInicio");
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
        } else {
            botaoAceitar.setDisable(true);
            botaoRecusar.setDisable(true);
        }
    }

    public void botaoRecusarAction() {
        if (tabela.getSelectionModel().getSelectedItem() != null) {
        	try {
				this.avaFachada.aceitarSolicitacaoProjeto(tabela.getSelectionModel().getSelectedItem().getIdSolicitacao(), 0);
				Alertas.sucesso("Solicitação recusada");
				Navegacao.carregarPainel("painelInicio");
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
        } else {
            botaoAceitar.setDisable(true);
            botaoRecusar.setDisable(true);
        }
    }
    
}
