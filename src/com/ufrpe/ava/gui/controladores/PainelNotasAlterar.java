package com.ufrpe.ava.gui.controladores;

import java.sql.SQLException;
import java.util.ArrayList;

import com.ufrpe.ava.AVA;
import com.ufrpe.ava.excecoes.ListaCadastroVaziaExceptions;
import com.ufrpe.ava.negocio.entidades.Nota;
import com.ufrpe.ava.negocio.entidades.OfertaAluno;
import com.ufrpe.ava.negocio.entidades.OfertaProfessor;
import com.ufrpe.ava.util.Alertas;
import com.ufrpe.ava.util.Validacao;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class PainelNotasAlterar extends Tela{

	@FXML
	private ChoiceBox<OfertaProfessor> choiceOferta;  
	
	@FXML
	private ChoiceBox<OfertaAluno> choiceAlunos;
	
	@FXML
	private TextField campoNota1;
	
	@FXML
	private TextField campoNota2;
	
	@FXML
	private TextField campoNota3;
	
	@FXML
	private TextField campoNotaFinal;
	
	
	@FXML
    public void initialize() {
        try {
            
        	ArrayList<OfertaProfessor> ofertas = this.avaFachada.ofertaProfessor(Tela.usuarioAtivo.getCPF());

            choiceOferta.getItems().addAll(ofertas);

        } catch (ListaCadastroVaziaExceptions e) {
            System.out.println(e.getMessage());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    
	 public void botaoCancelarAction() {
	        AVA.carregar("inicio");
	 }
	 
	 public void choiceOfertasClick(){
		 	  
		 if(choiceOferta.getSelectionModel().getSelectedItem() != null){
			 
			try {
				
				ArrayList<OfertaAluno> ofertasAluno = this.avaFachada.ofertaAluno(choiceOferta.getSelectionModel().getSelectedItem().getIdOferta());
				
				choiceAlunos.getItems().addAll(ofertasAluno);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			} catch (ListaCadastroVaziaExceptions e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}  
		 }
	 }
	 
	 
	 
	 
	 public void botaoSalvarAction(){
		 
		 ArrayList<String> listaValidacao = new ArrayList<String>();
	        listaValidacao.add(campoNota1.getText());
	        listaValidacao.add(campoNota2.getText());
	        listaValidacao.add(campoNota3.getText());
	        listaValidacao.add(campoNotaFinal.getText());
		 
	        try {
	        
				 if(Validacao.validarCampos(listaValidacao) && choiceOferta.getSelectionModel().getSelectedItem() != null 
						 && choiceAlunos.getSelectionModel().getSelectedItem() != null ){
						
					 Nota notaNova = new Nota();
					 
					 notaNova.setCpfAluno(choiceAlunos.getSelectionModel().getSelectedItem().getCpfAluno());
					 notaNova.setIdOferta(choiceOferta.getSelectionModel().getSelectedItem().getIdOferta());
					 notaNova.setNota1(Double.parseDouble(campoNota1.getText()));
					 notaNova.setNota2(Double.parseDouble(campoNota2.getText()));
					 notaNova.setNota3(Double.parseDouble(campoNota3.getText()));
					 notaNova.setNotaFinal(Double.parseDouble(campoNotaFinal.getText()));
					 
					 
					 
						this.avaFachada.alterarNota(notaNova);
						Alertas.sucesso("Notas Atualizadas com Sucesso.") ;
						 AVA.carregar("inicio");
					 
				 }else{
					 
					 Alertas.campoObrigatorio("Preencha os Campos Corretamente");
				 }
		 
	        } catch (SQLException e) {
	        	
				System.out.println(e.getMessage());
			
	        }catch(Exception e){
	        	
	        	System.out.println(e.getMessage());
	        	Alertas.campoInvalido("Preencha o Campo Notas só com números");
	        }
			
	 }
}


