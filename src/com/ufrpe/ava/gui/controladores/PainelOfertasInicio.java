package com.ufrpe.ava.gui.controladores;

import java.sql.SQLException;
import java.util.List;

import com.ufrpe.ava.excecoes.ListaCadastroVaziaExceptions;
import com.ufrpe.ava.negocio.entidades.OfertaDisciplina;
import com.ufrpe.ava.util.Navegacao;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class PainelOfertasInicio extends Tela {

	
	@FXML
    private Button botaoInserir;

    @FXML
    private Button botaoAtualizar;

    @FXML
    private Button botaoRemover;

    @FXML
    private TableView<OfertaDisciplina> tabela;

    @FXML
    private TableColumn<OfertaDisciplina, Integer> idOferta;

    @FXML
    private TableColumn<OfertaDisciplina, String> nomeDisciplina;

    @FXML
    private TableColumn<OfertaDisciplina, String> nomeCurso;

    @FXML
    private TableColumn<OfertaDisciplina, Integer> qtdAlunos;

    @FXML
    private TableColumn<OfertaDisciplina, Integer> ano;
    
    @FXML
    private TableColumn<OfertaDisciplina, Integer> semestre;
    
    
    @FXML
    private void initialize() {
        try {
            List<OfertaDisciplina> ofertas = this.avaFachada.selecionarOfertas();
            
            

            tabela.setItems(FXCollections.observableArrayList(ofertas));

            idOferta = new TableColumn<>("IdOferta");
            idOferta.setCellValueFactory(new PropertyValueFactory<OfertaDisciplina, Integer>("idOferta"));

            nomeDisciplina = new TableColumn<>("NomeDisciplina");
            nomeDisciplina.setCellValueFactory(new PropertyValueFactory<OfertaDisciplina, String>("nomeDisciplina"));

            nomeCurso = new TableColumn<>("NomeCurso");
            nomeCurso.setCellValueFactory(new PropertyValueFactory<OfertaDisciplina, String>("nomeCurso"));

            qtdAlunos = new TableColumn<>("qtdAlunos");
            qtdAlunos.setCellValueFactory(new PropertyValueFactory<OfertaDisciplina, Integer>("qtdAlunos"));

            ano = new TableColumn<>("Ano");
            ano.setCellValueFactory(new PropertyValueFactory<OfertaDisciplina, Integer>("Ano"));
            
            semestre = new TableColumn<>("Semestre");
            semestre.setCellValueFactory(new PropertyValueFactory<OfertaDisciplina, Integer>("Semestre"));


            tabela.getColumns().addAll(idOferta, nomeDisciplina, nomeCurso, qtdAlunos, ano,semestre);

            tabela.getSelectionModel().selectedItemProperty().addListener((value, oldValue, newValue) -> {
               
            	if (tabela.getSelectionModel().getSelectedItem() != null) {
                    
                    botaoRemover.setDisable(false);
                } else {
                   
                    botaoRemover.setDisable(true);
                }
            });
        }catch(ListaCadastroVaziaExceptions e){
            System.out.println(e.getMessage());
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
    
    
    public void botaoRemoverAction() {
    	
        if (tabela.getSelectionModel().getSelectedItem() != null) {
        	
        	
            try {
                this.avaFachada.removerOferta(tabela.getSelectionModel().getSelectedItem());
                this.avaFachada.registrarPersistencia("A Oferta -" +tabela.getSelectionModel().getSelectedItem().getIdOferta()+"- Foi Excluida do sistema");
                tabela.getColumns().clear();

                List<OfertaDisciplina> ofertas = this.avaFachada.selecionarOfertas();
                tabela.setItems(FXCollections.observableArrayList(ofertas));

                tabela.getColumns().addAll(idOferta, nomeDisciplina, nomeCurso, qtdAlunos, ano,semestre);

                botaoRemover.setDisable(true);
            } catch(ListaCadastroVaziaExceptions e){

                System.out.println(e.getMessage());

            } catch(SQLException e){

                e.getMessage();
            }

        } else {
            
            botaoRemover.setDisable(true);
        }
    }

    
    public void botaoInserirAction(){
    	Navegacao.carregarPainel("PainelOfertasAdicinar");
    }
    
}
