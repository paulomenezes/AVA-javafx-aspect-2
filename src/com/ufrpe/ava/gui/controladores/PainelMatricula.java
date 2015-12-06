package com.ufrpe.ava.gui.controladores;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.ufrpe.ava.excecoes.ListaCadastroVaziaExceptions;
import com.ufrpe.ava.negocio.entidades.DisciplinaDisponivel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;

public class PainelMatricula  extends Tela implements Initializable{

	
	@FXML
	private Button botaoMatricular;
	
	@FXML
	private Button botaoRetornar;
	
	
	
	@FXML
	private ListView <CheckBox> listaMatricula;


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		ObservableList<CheckBox> data = FXCollections.observableArrayList();
		
		ArrayList<DisciplinaDisponivel> lista = new ArrayList<>();
		
		
		try {
			
				lista = avaFachada.disciplinasDisponiveis("123");
				
				for (DisciplinaDisponivel d : lista) {
					
					data.add(new CheckBox(d.toString()));
				}
			
			
			listaMatricula.setItems(data);
			
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (ListaCadastroVaziaExceptions e) {
		
			System.out.println(e.getMessage());
		}
	}
	
	
}
