package com.ufrpe.ava.gui.controladores;

import java.sql.SQLException;
import java.util.ArrayList;

import com.ufrpe.ava.excecoes.ListaCadastroVaziaExceptions;
import com.ufrpe.ava.negocio.entidades.Nota;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class PainelVisualizarNotas extends Tela {

	@FXML
	TableView<Nota> tabela;

    @FXML
    private TableColumn<Nota, Double> nota1;

    @FXML
    private TableColumn<Nota, Double> nota2;

    @FXML
    private TableColumn<Nota, Double> nota3;

    @FXML
    private TableColumn<Nota, Double> notaFinal;

    @FXML
    private TableColumn<Nota, String> nomeDisciplina;
	
	public void initialize() {
		try {
			ArrayList<Nota> notas = this.avaFachada.buscarNota(Tela.usuarioAtivo.getCPF());
			
			System.out.println(notas);
			
			tabela.setItems(FXCollections.observableArrayList(notas));

            nota1 = new TableColumn<>("1º nota");
            nota1.setCellValueFactory(new PropertyValueFactory<Nota, Double>("nota1"));

            nota2 = new TableColumn<>("2º nota");
            nota2.setCellValueFactory(new PropertyValueFactory<Nota, Double>("nota2"));

            nota3 = new TableColumn<>("3º nota");
            nota3.setCellValueFactory(new PropertyValueFactory<Nota, Double>("nota3"));

            notaFinal = new TableColumn<>("Nota Final");
            notaFinal.setCellValueFactory(new PropertyValueFactory<Nota, Double>("notaFinal"));

            nomeDisciplina = new TableColumn<>("Disciplina");
            nomeDisciplina.setCellValueFactory(new PropertyValueFactory<Nota, String>("nomeDisciplina"));

            tabela.getColumns().addAll(nota1, nota2, nota3, notaFinal, nomeDisciplina);
		} catch (SQLException | ListaCadastroVaziaExceptions e) {
			e.printStackTrace();
		}
		
		
	}
}
