package com.ufrpe.ava.gui.controladores;

import java.sql.SQLException;
import java.util.List;

import com.ufrpe.ava.excecoes.ListaCadastroVaziaExceptions;
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

    @FXML
    private void initialize() {
    	

        try{
        	
        	List<Departamento> departamentos = this.avaFachada.selecionarDepartamentos();

            tabela.setItems(FXCollections.observableArrayList(departamentos));

            id = new TableColumn<>("ID");
            id.setCellValueFactory(new PropertyValueFactory<Departamento, Integer>("idDepartamento"));

            nome = new TableColumn<>("Nome");
            nome.setCellValueFactory(new PropertyValueFactory<Departamento, String>("nome"));

            tabela.getColumns().addAll(id, nome);

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
        PainelDepartamentoAdicionar.departamento = null;
        Navegacao.carregarPainel("painelDepartamentoAdicionar");
    }

    public void botaoAtualizarAction() {
        if (tabela.getSelectionModel().getSelectedItem() != null) {
            try {
                PainelDepartamentoAdicionar.departamento = tabela.getSelectionModel().getSelectedItem();
                Navegacao.carregarPainel("painelDepartamentoAdicionar");
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
                
            	this.avaFachada.removerDepartamento(tabela.getSelectionModel().getSelectedItem());
                    tabela.getColumns().clear();

                    List<Departamento> departamentos = this.avaFachada.selecionarDepartamentos();
                    tabela.setItems(FXCollections.observableArrayList(departamentos));

                    tabela.getColumns().addAll(id, nome);

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
