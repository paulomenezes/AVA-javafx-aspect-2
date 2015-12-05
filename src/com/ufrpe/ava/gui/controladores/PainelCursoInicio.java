package com.ufrpe.ava.gui.controladores;

import java.sql.SQLException;
import java.util.List;

import com.ufrpe.ava.excecoes.ListaCadastroVaziaExceptions;
import com.ufrpe.ava.negocio.entidades.Curso;
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
    private TableColumn<Curso, Integer> limiteAluno;

    @FXML
    private TableColumn<Curso, Integer> qtdAluno;

    @FXML
    private TableColumn<Curso, String> departamento;

    @FXML
    private void initialize() {
    	
    	
    		try{	
		    	  	List<Curso> cursos = this.avaFachada.selecionarCursos();
		
		            tabela.setItems(FXCollections.observableArrayList(cursos));
		
		            id = new TableColumn<>("ID");
		            id.setCellValueFactory(new PropertyValueFactory<Curso, Integer>("idDepartamento"));
		
		            nome = new TableColumn<>("Nome");
		            nome.setCellValueFactory(new PropertyValueFactory<Curso, String>("nome"));
		
		            limiteAluno = new TableColumn<>("Limite Alunos");
		            limiteAluno.setCellValueFactory(new PropertyValueFactory<Curso, Integer>("limiteAluno"));
		
		            qtdAluno = new TableColumn<>("Quantidade Alunos");
		            qtdAluno.setCellValueFactory(new PropertyValueFactory<Curso, Integer>("qtdAluno"));
		
		            departamento = new TableColumn<>("Departamento");
		            departamento.setCellValueFactory(new PropertyValueFactory<Curso, String>("departamento"));
		
		            tabela.getColumns().addAll(id, nome, limiteAluno, qtdAluno, departamento);
		
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
    		}catch(SQLException e){
    			System.out.println(e.getMessage());
    		}
       
    }

    public void botaoInserirAction() {
        Navegacao.carregarPainel("painelCursoAdicionar");
    }

    public void botaoAtualizarAction() {

    }

    public void botaoRemoverAction() {

    }

    public void botaoDepartamentoAction() {
        Navegacao.carregarPainel("painelDepartamentoInicio");
    }
}
