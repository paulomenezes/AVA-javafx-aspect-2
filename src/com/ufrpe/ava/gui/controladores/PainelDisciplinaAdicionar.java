package com.ufrpe.ava.gui.controladores;

import java.sql.SQLException;
import java.util.ArrayList;

import com.ufrpe.ava.excecoes.ListaCadastroVaziaExceptions;
import com.ufrpe.ava.negocio.entidades.Disciplina;
import com.ufrpe.ava.util.Alertas;
import com.ufrpe.ava.util.Navegacao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
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
    private ListView<CheckBox>  listDisciplinas;

    @FXML
    private CheckBox checkRequisito;
    
    private ObservableList<CheckBox> data;
    
    
    @FXML
    private void initialize() {
        campoTipo.getItems().add("Graduação");
        campoTipo.getItems().add("Pós-Graduação");


		data = FXCollections.observableArrayList();
		
        
        try {
			ArrayList<Disciplina> disciplinas = avaFachada.selecionarDisciplinas();
			
			
			
			for (Disciplina d : disciplinas) {
				
				data.add(new CheckBox(d.getIdDisciplina()+"-"+d.getNome()));
			}
			
			listDisciplinas.setItems(data);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ListaCadastroVaziaExceptions e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
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
        		
        		if(checkRequisito.isSelected()){
        			
        			if (disciplina == null) {
        				
        				
        				ArrayList<String> list = new ArrayList<>(); 
        				
        				for (CheckBox checkBox : data) {
							
        					if(checkBox.isSelected()){
        						
        						String[] s = checkBox.getText().split("-");
        						
        						list.add(s[0]);
        					}
						}
        				
	                    this.avaFachada.cadastrarDisciplina(campoNome.getText(), campoTipo.getValue().toString(), Integer.parseInt(campoCargaHoraria.getText().toString()),
	                    		Integer.parseInt(campoCreditos.getText().toString()),list);
	                    
	                    this.avaFachada.registrarPersistencia("Disciplina - "+ campoNome.getText() +" -  Foi cadastrada no sistema") ;
	                    
	                } else {
	                	
	                    this.avaFachada.editarDisciplina(disciplina.getIdDisciplina(), campoNome.getText(), campoTipo.getValue().toString(), Integer.parseInt(campoCargaHoraria.getText().toString()), Integer.parseInt(campoCreditos.getText().toString()));
	                    this.avaFachada.registrarPersistencia("Disciplina - "+ campoNome.getText() +" -  Foi alterada no sistema") ;
	                }
        			Navegacao.carregarPainel("painelDisciplinaInicio");
        			
        		}else{

        			if (disciplina == null) {
        				
	                    this.avaFachada.cadastrarDisciplina(campoNome.getText(), campoTipo.getValue().toString(), Integer.parseInt(campoCargaHoraria.getText().toString()), Integer.parseInt(campoCreditos.getText().toString()), null);
	                    this.avaFachada.registrarPersistencia("Disciplina - "+ campoNome.getText() +" -  Foi cadastrada no sistema") ;
	                } else {
	                    this.avaFachada.editarDisciplina(disciplina.getIdDisciplina(), campoNome.getText(), campoTipo.getValue().toString(), Integer.parseInt(campoCargaHoraria.getText().toString()), Integer.parseInt(campoCreditos.getText().toString()));
	                    this.avaFachada.registrarPersistencia("Disciplina - "+ campoNome.getText() +" -  Foi alterada no sistema") ;
	                }
	
	                Navegacao.carregarPainel("painelDisciplinaInicio");
        		}
            } catch(SQLException e) {
                System.out.println(e.getMessage());
                Navegacao.carregarPainel("painelDisciplinaInicio");
            } catch (Exception e) {
                System.out.println(e.getMessage());
                Alertas.campoInvalido("Digite apenas números nos créditos e na carga horária");
               // Navegacao.carregarPainel("painelDisciplinaInicio");
            }
        } else {
            Alertas.campoObrigatorio("Preencha todos os campos.");
        }
    }
    
    
     public void clickRequisito(){
    	
    	if(checkRequisito.isSelected()){
    		
    		listDisciplinas.setVisible(true);
    		listDisciplinas.setDisable(false);
    	}else{
    		
    		listDisciplinas.setVisible(false);
    		listDisciplinas.setDisable(true);
    	}
    	
    }
}
