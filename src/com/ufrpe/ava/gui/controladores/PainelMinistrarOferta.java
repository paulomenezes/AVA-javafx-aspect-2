package com.ufrpe.ava.gui.controladores;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ufrpe.ava.AVA;
import com.ufrpe.ava.excecoes.ListaCadastroVaziaExceptions;
import com.ufrpe.ava.negocio.entidades.Curso;
import com.ufrpe.ava.negocio.entidades.MinistrarOferta;
import com.ufrpe.ava.negocio.entidades.OfertaDisciplina;
import com.ufrpe.ava.negocio.entidades.Professor;
import com.ufrpe.ava.negocio.entidades.Usuario;
import com.ufrpe.ava.util.Alertas;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

public class PainelMinistrarOferta extends Tela {

	
   @FXML	
   private ChoiceBox<OfertaDisciplina> choiceOferta;   
	
   @FXML	
   private ChoiceBox<Usuario> choiceProfessor;   
   
   @FXML
   private Button botaoSalvar;
	

   @FXML
   private Button botaoCancelar;
   
   
   @FXML
   public void initialize() {
       try {
            ArrayList<OfertaDisciplina> ofertas = this.avaFachada.selecionarOfertas();

             choiceOferta.getItems().addAll(ofertas);

           ArrayList<Usuario> listaUsuarios = new ArrayList<>();
       
           
           for (Usuario usuario : this.avaFachada.selecionarUsuarios() ) {
        	   
        	   if(usuario.getGrad()== 0){
        		   
        		   listaUsuarios.add(usuario);
        	   }
           }

           choiceProfessor.getItems().addAll(listaUsuarios);
           
       } catch (ListaCadastroVaziaExceptions e) {
           System.out.println(e.getMessage());
       } catch (SQLException e) {
           System.out.println(e.getMessage());
       }
   }

   
   public void botaoSalvarAction(){
	   
	   
	   if(choiceOferta.getSelectionModel().getSelectedItem() != null && choiceProfessor.getSelectionModel().getSelectedItem() != null){
		   
		   
		   MinistrarOferta ministrar = new MinistrarOferta(choiceProfessor.getSelectionModel().getSelectedItem() .getCPF() , 
				   choiceOferta.getSelectionModel().getSelectedItem().getIdOferta());
		   
		   try {
			   
			avaFachada.cadastrarMinistrarOferta(ministrar);
			Alertas.sucesso("Cadastro Ministrar Oferta com sucesso");
			AVA.carregar("inicio");
			
		} catch (SQLException e) {
			
			System.out.println(e.getMessage());
		}
		   
	   }else{
		   
		   Alertas.campoObrigatorio("Selecione os Campos Corretamente");
	   } 
	   
   }
   
   public void botaoCancelarAction(){
	   
	   AVA.carregar("inicio");
   }
   
   
}
