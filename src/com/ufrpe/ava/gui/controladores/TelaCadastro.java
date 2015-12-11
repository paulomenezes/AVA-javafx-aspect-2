package com.ufrpe.ava.gui.controladores;

import java.sql.SQLException;
import java.util.ArrayList;

import com.ufrpe.ava.AVA;
import com.ufrpe.ava.excecoes.ListaCadastroVaziaExceptions;
import com.ufrpe.ava.negocio.entidades.Curso;
import com.ufrpe.ava.negocio.entidades.Departamento;
import com.ufrpe.ava.negocio.entidades.Usuario;
import com.ufrpe.ava.util.Alertas;
import com.ufrpe.ava.util.Validacao;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

/**
 * Created by paulomenezes on 01/12/15.
 */
public class TelaCadastro extends Tela {
    @FXML
    private TextField campoCPF;

    @FXML
    private TextField campoSenha;

    @FXML
    private TextField campoReSenha;

    @FXML
    private CheckBox professorCheck;

    @FXML
    private CheckBox alunoCheck;

    @FXML
    private TextField campoNome;

    @FXML
    private TextField campoEmail;

    @FXML
    private Button botaoVoltar;

    @FXML
    private Button botaoCriarConta;

    @FXML
    private ChoiceBox<Departamento> departamento;

    @FXML
    private ChoiceBox<Curso> curso;

    @FXML
    private CheckBox graduacaoCheck;

    @FXML
    private CheckBox posGradCheck;

    public void desabilitar() {
        graduacaoCheck.setDisable(true);
        posGradCheck.setDisable(true);
    }

    public void clickGraduacaoCheck() {
        if (graduacaoCheck.isSelected()) {
            posGradCheck.setDisable(true);
        } else {
            posGradCheck.setDisable(false);
        }
    }

    public void clickPosGradCheck() {
        if (posGradCheck.isSelected()) {
            graduacaoCheck.setDisable(true);
        } else {
            graduacaoCheck.setDisable(false);
        }
    }

    public void clickProfessorCheck() {
        if (professorCheck.isSelected()) {
            alunoCheck.setDisable(true);
            curso.setDisable(true);
        } else {
            alunoCheck.setDisable(false);
            curso.setDisable(false);
        }
    }

    public void clickAlunoCheck() {
        if (alunoCheck.isSelected()) {
            professorCheck.setDisable(true);
            departamento.setDisable(true);
            graduacaoCheck.setDisable(false);
            posGradCheck.setDisable(false);
        } else {
            professorCheck.setDisable(false);
            departamento.setDisable(false);
            graduacaoCheck.setDisable(true);
            posGradCheck.setDisable(true);
        }
    }

    @FXML
    public void initialize() {
        try {
            java.util.List<Departamento> depts = this.avaFachada.selecionarDepartamentos();

            departamento.getItems().addAll(depts);

            java.util.List<Curso> curs = this.avaFachada.selecionarCursos();

            curso.getItems().addAll(curs);
        } catch (ListaCadastroVaziaExceptions e) {
            System.out.println(e.getMessage());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void botaoVoltarAction() {
        AVA.carregar("login");
    }

    public void botaoCriarContaAction() {
        ArrayList<String> listaValidacao = new ArrayList<String>();
        listaValidacao.add(campoCPF.getText());
        listaValidacao.add(campoEmail.getText());
        listaValidacao.add(campoNome.getText());
        listaValidacao.add(campoSenha.getText());
        listaValidacao.add(campoReSenha.getText());
        

        if (Validacao.validarCampos(listaValidacao) && validarCheckBox()) {
            if (Validacao.validarSenha(campoSenha.getText(), campoReSenha.getText()) &&
                    Validacao.validarEmail(campoEmail.getText()) &&
                    Validacao.validarCPF(campoCPF.getText())) {
                
            	
                if (professorCheck.isSelected()){
                    try {
                    	
                        this.avaFachada.cadastrarProfessor(campoCPF.getText(),campoNome.getText(),null,campoEmail.getText(),
                                campoSenha.getText(),0, departamento.getSelectionModel().getSelectedItem().getIdDepartamento());
                        
                        Usuario usuario = new Usuario(campoCPF.getText(), campoNome.getText(),null, campoEmail.getText(),
                                campoSenha.getText(), 1);
                        
                        Tela.usuarioAtivo = usuario;
                        
                        Alertas.sucesso("Cadastro Realizado.");
                        
                        avaFachada.registrarPersistencia("Usuario - "+ campoNome.getText()+ " Cpf -"+campoCPF.getText() + " Foi Cadastrado no sistema") ;

                        AVA.carregar("inicio");
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                    }
                } else {

                    try {
                        this.avaFachada.cadastrarAluno(campoCPF.getText(), campoNome.getText(),null, campoEmail.getText(),
                                campoSenha.getText(), 1 ,curso.getSelectionModel().getSelectedItem().getIdCurso());
                        
                        Usuario usuario = new Usuario(campoCPF.getText(), campoNome.getText(),null, campoEmail.getText(),
                                campoSenha.getText(), 1);
                        
                        Tela.usuarioAtivo = usuario;
                        
                        Alertas.sucesso("Cadastro Realizado.");
                        
                        avaFachada.registrarPersistencia("Usuario - "+ campoNome.getText()+ " Cpf -"+campoCPF.getText() + " Foi Cadastrado no sistema") ;

                        AVA.carregar("inicio");
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                    }
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Login inválido");
                alert.setHeaderText("CPF, senha ou Email inválido");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Campos obrigatórios");
            alert.setHeaderText("Por favor preencher os campos corretamente.");
            alert.showAndWait();
        }
    }

    private Boolean validarCheckBox() {
        if (!professorCheck.isSelected() && !alunoCheck.isSelected()) {
            return false;
        } else if(alunoCheck.isSelected() && curso.getSelectionModel().getSelectedItem() != null) {
            if (!graduacaoCheck.isSelected() && !posGradCheck.isSelected()) {
                return false;
            } else {
                return true;
            }
        } else if (professorCheck.isSelected() && !alunoCheck.isSelected() && departamento.getSelectionModel().getSelectedItem() != null) {
            return true;
        } else {
            return false;
        }
    }
}
