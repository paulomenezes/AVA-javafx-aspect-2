package com.ufrpe.ava.gui.controladores;

import com.ufrpe.ava.excecoes.ListaCadastroVaziaExceptions;
import com.ufrpe.ava.negocio.entidades.*;
import com.ufrpe.ava.util.Alertas;
import com.ufrpe.ava.util.Navegacao;
import com.ufrpe.ava.util.Validacao;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by paulomenezes on 08/12/15.
 */
public class PainelUsuarioAdicionar extends Tela {
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

    public static Usuario usuario;

    public static int tipo;

    @FXML
    public void initialize() {
        
    	
    	try {
            List<Departamento> departamentos = this.avaFachada.selecionarDepartamentos();
            departamento.getItems().addAll(departamentos);

            List<Curso> cursos = this.avaFachada.selecionarCursos();
            curso.getItems().addAll(cursos);

           if (tipo == 0) {
                graduacaoCheck.setDisable(true);
                posGradCheck.setDisable(true);
                curso.setDisable(true);
                departamento.setDisable(false);
            } else if (tipo == 1) {
                graduacaoCheck.setDisable(false);
                posGradCheck.setDisable(false);
                curso.setDisable(false);
                departamento.setDisable(true);
            }
        } catch (ListaCadastroVaziaExceptions e) {
            System.out.println(e.getMessage());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void botaoCancelarAction() {
        Navegacao.carregarPainel("painelUsuarioInicio");
    }

    @FXML
    public void botaoSalvarAction() {
        ArrayList<String> listaValidacao = new ArrayList<String>();
        listaValidacao.add(campoCPF.getText());
        listaValidacao.add(campoEmail.getText());
        listaValidacao.add(campoNome.getText());
        listaValidacao.add(campoSenha.getText());
        listaValidacao.add(campoReSenha.getText());

        if (Validacao.validarCampos(listaValidacao) &&
                Validacao.validarSenha(campoSenha.getText(), campoReSenha.getText()) &&
                Validacao.validarEmail(campoEmail.getText()) &&
                Validacao.validarCPF(campoCPF.getText())) {
            try {
                
            	if (tipo == 0) {
                    if (departamento.getValue() != null) {
                        this.avaFachada.cadastrarProfessor(campoCPF.getText(),campoNome.getText(),null,campoEmail.getText(), campoSenha.getText(),0,departamento.getValue().getIdDepartamento());
                        avaFachada.registrarPersistencia("Usuario - "+ campoNome.getText()+ " Cpf -"+campoCPF.getText() + " Foi Cadastrado no sistema") ;
                        Navegacao.carregarPainel("painelUsuarioInicio");
                    } else {
                        Alertas.campoObrigatorio("selecione um departamento.");
                    }
                } else if (tipo == 1) {
                    if (curso.getValue() != null) {
                        if (graduacaoCheck.isSelected()) {
                            this.avaFachada.cadastrarAluno(campoCPF.getText(),campoNome.getText(), null ,campoEmail.getText(), campoSenha.getText(),1, curso.getValue().getIdCurso());
                            avaFachada.registrarPersistencia("Usuario - "+ campoNome.getText()+ " Cpf -"+campoCPF.getText() + " Foi Cadastrado no sistema") ;
                            Navegacao.carregarPainel("painelUsuarioInicio");
                        } else if (posGradCheck.isSelected()) {
                            this.avaFachada.cadastrarAluno(campoCPF.getText(),campoNome.getText(), null ,campoEmail.getText(), campoSenha.getText(),1, curso.getValue().getIdCurso());

                            Navegacao.carregarPainel("painelUsuarioInicio");
                        } else {
                            Alertas.campoObrigatorio("selecione graduação ou pós-graduação.");
                        }
                    } else {
                        Alertas.campoObrigatorio("selecione um curso.");
                    }
                }
            } catch (Exception e) {
                Alertas.falhaCadastro("Usuario");
                System.out.println(e.getMessage());
            }
        } else {
            Alertas.campoObrigatorio("Preencha todos os campos corretamente.");
        }
    }
}
