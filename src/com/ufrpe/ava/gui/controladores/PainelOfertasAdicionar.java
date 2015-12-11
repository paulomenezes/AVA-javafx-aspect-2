package com.ufrpe.ava.gui.controladores;

import java.sql.SQLException;
import java.util.ArrayList;

import com.ufrpe.ava.excecoes.ListaCadastroVaziaExceptions;
import com.ufrpe.ava.negocio.entidades.Curso;
import com.ufrpe.ava.negocio.entidades.Disciplina;
import com.ufrpe.ava.negocio.entidades.OfertaDisciplina;
import com.ufrpe.ava.util.Alertas;
import com.ufrpe.ava.util.Navegacao;
import com.ufrpe.ava.util.Validacao;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class PainelOfertasAdicionar extends Tela{

	@FXML
	private ChoiceBox<Curso> campoCurso; 
	
	@FXML
	private ChoiceBox<Disciplina> campoDisciplina;

	@FXML
	private  TextField campoQtd;
	
	@FXML
	private  TextField campoAno;
	
	@FXML
	private  TextField campoSemestre;
	
	@FXML
	private  Button  botaoCancelar; 
	
	@FXML
	private  Button  botaoSalvar;
	
	
	 @FXML
	 public void initialize() {
		 
		 
		 try {
	            ArrayList<Disciplina> disc = this.avaFachada.selecionarDisciplinas();

	            campoDisciplina.getItems().addAll(disc);

	            ArrayList<Curso> curs = this.avaFachada.selecionarCursos();

	            campoCurso.getItems().addAll(curs);
	            
	        } catch (ListaCadastroVaziaExceptions e) {
	            System.out.println(e.getMessage());
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
		 
	 }
	 
	 
	 public void botaoSalvarAction(){
		 
		 
		    ArrayList<String> listaValidacao = new ArrayList<String>();
	        listaValidacao.add(campoQtd.getText());
	        listaValidacao.add(campoAno.getText());
	        listaValidacao.add(campoSemestre.getText());
	        
	        
	      try{
	      
	        if(Validacao.validarCampos(listaValidacao) && campoCurso.getSelectionModel().getSelectedItem() != null &&
	        		campoDisciplina.getSelectionModel().getSelectedItem() !=null){
	        	
	        	
	        	OfertaDisciplina oferta = new OfertaDisciplina(campoDisciplina.getSelectionModel().getSelectedItem().getIdDisciplina(), 
	        			campoCurso.getSelectionModel().getSelectedItem().getIdCurso(), Integer.parseInt(campoQtd.getText()) ,
	        			Integer.parseInt(campoAno.getText()), Integer.parseInt(campoSemestre.getText()) );   
	  
					this.avaFachada.cadastrarOferta(oferta);
					 Alertas.sucesso("Cadastro  de Oferta Realizado.");
					 Navegacao.carregarPainel("painelInicio");
					 
				
	        }else{
	        	
	        	Alertas.campoObrigatorio("Preencha todos os campos corretamente.");
	        }
	        
	      } catch (SQLException e) {
				// TODO Auto-generated catch block
				 System.out.println(e.getMessage());
		 }catch(Exception e){
			 
			    Alertas.campoInvalido("Campos Qtd Alunos , Ano e Semestre deve ser preenchidos apenas com Números");
		 } 
	        
	 }
	
	 
	 public void botaoCancelarAction(){
		 
		 Navegacao.carregarPainel("painelOfertasInicio");
	 }	
}
