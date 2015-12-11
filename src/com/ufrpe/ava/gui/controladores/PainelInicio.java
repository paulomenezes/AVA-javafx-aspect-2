package com.ufrpe.ava.gui.controladores;

import java.sql.SQLException;
import java.util.List;

import com.ufrpe.ava.excecoes.ListaCadastroVaziaExceptions;
import com.ufrpe.ava.negocio.entidades.Aviso;
import com.ufrpe.ava.negocio.entidades.Curso;
import com.ufrpe.ava.negocio.entidades.Disciplina;
import com.ufrpe.ava.negocio.entidades.ProjetoPesquisa;
import com.ufrpe.ava.negocio.entidades.Usuario;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class PainelInicio extends Tela {
    @FXML
    private Label textoProfessores;

    @FXML
    private Label textoAlunos;

    @FXML
    private Label textoCursos;

    @FXML
    private Label textoPesquisas;

    @FXML
    private Label textoDisciplinas;

    @FXML
    private TableView<Aviso> tabela;

    @FXML
    private TableColumn<Aviso, String> titulo;

    @FXML
    private TableColumn<Aviso, String> descricao;

    @FXML
    public void initialize() {
        try {
            List<Usuario> usuarios = this.avaFachada.selecionarUsuarios();
            List<Curso> cursos = this.avaFachada.selecionarCursos();
            List<ProjetoPesquisa> projetoPesquisas = this.avaFachada.selecionarProjetoPesquisas();
            List<Disciplina> disciplinas = this.avaFachada.selecionarDisciplinas();

            int professores = 0, alunos = 0;
            for (int i = 0; i < usuarios.size(); i++) {
                if (usuarios.get(i).getGrad() == 0) {
                    professores++;
                } else if (usuarios.get(i).getGrad() == 1 || usuarios.get(i).getGrad() == 2) {
                    alunos++;
                }
            }

            textoAlunos.setText(String.valueOf(alunos));
            textoProfessores.setText(String.valueOf(professores));
            textoCursos.setText(String.valueOf(cursos.size()));
            textoPesquisas.setText(String.valueOf(projetoPesquisas.size()));
            textoDisciplinas.setText(String.valueOf(disciplinas.size()));

            carregarAvisos();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ListaCadastroVaziaExceptions e) {
            e.printStackTrace();
        }
    }

    private void carregarAvisos() {
        try {
            List<Aviso> avisos = this.avaFachada.selecionarAvisos(this.usuarioAtivo.getCPF());

            tabela.setItems(FXCollections.observableArrayList(avisos));

            titulo = new TableColumn<>("Título");
            titulo.setCellValueFactory(new PropertyValueFactory<Aviso, String>("titulo"));

            descricao = new TableColumn<>("Descrição");
            descricao.setCellValueFactory(new PropertyValueFactory<Aviso, String>("descricao"));

            tabela.getColumns().addAll(titulo, descricao);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}