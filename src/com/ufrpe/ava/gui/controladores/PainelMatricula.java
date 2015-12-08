package com.ufrpe.ava.gui.controladores;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

import com.ufrpe.ava.excecoes.ListaCadastroVaziaExceptions;
import com.ufrpe.ava.negocio.entidades.DisciplinaDisponivel;
import com.ufrpe.ava.negocio.entidades.Matricular;
import com.ufrpe.ava.util.Alertas;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.text.Font;

public class PainelMatricula  extends Tela implements Initializable{

	
	@FXML
	private Button botaoMatricular;
	
	@FXML
	private Button botaoRetornar;
	
	
	
	@FXML
	private ListView <CheckBox> listaMatricula;


	private ObservableList<CheckBox> data;
	
	private ArrayList<DisciplinaDisponivel> lista = new ArrayList<>();
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		data = FXCollections.observableArrayList();
		
		
		try {
				lista = avaFachada.disciplinasDisponiveis(usuarioAtivo.getCPF());
				
				for (DisciplinaDisponivel d : lista) {
					
					CheckBox box = new CheckBox(d.toString());
					box.setFont(new Font("Serif", 15));
					box.setId(Integer.toString(d.getIdOferta()));
					
					data.add(box);
				}
			
			
			listaMatricula.setItems(data);
			
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (ListaCadastroVaziaExceptions e) {
		
			System.out.println(e.getMessage());
		
		}catch(NullPointerException e){
			
			System.out.println("Usuario não ativo no sistema") ;
		}
	}
	
	
	public void botaoMatricularAction(){
		
		int cont = 0;
		
		ArrayList<Matricular> matriculas = new ArrayList<>();
		
		for (CheckBox c: data) {
			
			if(c.isSelected()){
				
				Matricular matricula = new Matricular();
				
				try{
					matricula.setCpfAluno(usuarioAtivo.getCPF());
				}catch(NullPointerException e){
					
					System.out.println("Usuario não ativo no sistema");
					
				}
	
				LocalDate hoje = LocalDate.now();
				matricula.setDataMatricula(hoje.format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));
				matricula.setIdOferta(Integer.parseInt(c.getId()));
				matricula.setNumProtocolo(Integer.toString(new Random().nextInt(1000)));
				
				matriculas.add(matricula);
				cont ++;
			}
		}
		
		
		if(cont>=1 && cont<=6 ){
			
			int aux = 0;
			
			for (Matricular matricular : matriculas) {
					
				try {
					avaFachada.matricularAluno(matricular);
					avaFachada.registrarMatricula("Aluno com Cpf - "+ matricular.getCpfAluno()+"\n Relizou Matricula na Oferta - \n "+
					lista.get(aux).toString());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
				}
				
				aux++;
			}
			
		}else{
			
			Alertas.selecaoOfertasIndevido();
		}
		
	}
	
	
	public void botaoRetornarAction(){
		
	}
	
}
