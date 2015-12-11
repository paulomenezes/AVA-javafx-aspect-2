package com.ufrpe.ava.gui.controladores;

import com.ufrpe.ava.AVA;
import com.ufrpe.ava.util.Navegacao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.StackPane;

public class TelaInicio extends Tela {

    @FXML
    private StackPane vistaHolder;
    
    @FXML
    private MenuItem menuUsuario;
    
    @FXML
    private MenuItem menuCursos;


    @FXML
    private MenuItem menuDisciplinas;
    
    @FXML
    private MenuItem menuOfertas;
    
    @FXML
    private MenuItem menuMatricula;
    
    @FXML
    private MenuItem menuPesquisa;
    
    @FXML
    private MenuItem menuNotas;
    
    @FXML
    private MenuItem menuCadastrarProjeto;
    
    @FXML
    private MenuItem menuAceitarAlunos;
    
    @FXML
    private MenuItem menuAplicarNotas;
    
    
    public void setVista(Node node) {
        vistaHolder.getChildren().setAll(node);
    }

   
    
    @FXML
    void menuOfertaProfessorAction(ActionEvent event){
    	
    	Navegacao.carregarPainel("PainelMinistrarOferta");
    }

    @FXML
    void menuManipularProjetoAction(ActionEvent event){
    	
    	Navegacao.carregarPainel("painelProjetoPesquisaInicio");
    }
    
    
    @FXML
    void menuSolicitarProjetoAction(ActionEvent event){
    	
    	Navegacao.carregarPainel("painelProjetoPesquisaInicio");
    }
    
    @FXML
    void menuCadastrarProjetoAction(ActionEvent event){
    	
    	Navegacao.carregarPainel("painelProjetoPesquisaInicio");
    }
    
    @FXML
    void menuOfertasAction(ActionEvent event){
    	
    	Navegacao.carregarPainel("PainelOfertasInicio");
    }

    @FXML
    void menuInicioAction(ActionEvent event) {
        Navegacao.carregarPainel("painelInicio");
    }

    @FXML
    void menuSairAction(ActionEvent event) {
        AVA.carregar("login");
    }

    @FXML
    void menuUsuarioAction(ActionEvent event) {
        Navegacao.carregarPainel("painelUsuarioInicio");
    }

    @FXML
    void menuCursoAction(ActionEvent event) {
        Navegacao.carregarPainel("painelCursoInicio");
    }

    @FXML
    void menuDisciplinaAction(ActionEvent event) {
        Navegacao.carregarPainel("painelDisciplinaInicio");
    }

    @FXML
    void menuProjetoPesquisaAction(ActionEvent event) {
        Navegacao.carregarPainel("painelProjetoPesquisaInicio");
    }

    @FXML
    void menuMatriculaAction(ActionEvent event) {
        Navegacao.carregarPainel("painelMatricular");
    }
    
    @FXML
    private void initialize(){
    	
    	if(Tela.usuarioAtivo.getGrad() != -1){
    		
    		menuUsuario.setDisable(true);
    		menuDisciplinas.setDisable(true);
    		menuCursos.setDisable(true);
    		
    	}
    	
    }
}