package com.ufrpe.ava;

import java.io.IOException;

import com.ufrpe.ava.gui.controladores.TelaInicio;
import com.ufrpe.ava.util.Navegacao;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class AVA extends Application {
    public static Stage sStage;

    @Override
    public void start(Stage stage) throws Exception{
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("gui/telas/login.fxml")), 400, 300);

        sStage = stage;

        sStage.setTitle("Login AVA");
        sStage.setScene(scene);
        sStage.show();
    }

    public static void carregar(String tela) {
        try {
            switch (tela) {
                case "login":
                    Scene telaLogin = new Scene(FXMLLoader.load(AVA.class.getResource("gui/telas/login.fxml")), 400, 300);
                    sStage.setTitle("Login AVA");
                    sStage.setScene(telaLogin);
                    sStage.show();

                    break;
                case "cadastro":
                    Scene telaCadastro = new Scene(FXMLLoader.load(AVA.class.getResource("gui/telas/cadastro.fxml")), 435, 325);
                    sStage.setTitle("Cadastro AVA");
                    sStage.setScene(telaCadastro);
                    sStage.show();

                    break;
                case "esqueciSenha":
                    Scene telaEsqueciSenha = new Scene(FXMLLoader.load(AVA.class.getResource("gui/telas/esqueciSenha.fxml")), 300, 275);
                    sStage.setTitle("Esqueci a Senha AVA");
                    sStage.setScene(telaEsqueciSenha);
                    sStage.show();

                    break;
                case "inicio":
                    sStage.setTitle("AVA");
                    sStage.setScene(criarCena(carregarTelaInicial()));
                    sStage.show();
                            
                    break;
                case "painelMatricular":
                
                	Scene telaMatricula = new Scene(FXMLLoader.load(AVA.class.getResource("gui/telas/painelMatricular.fxml")), 435, 325);
                    sStage.setTitle("Matricula");
                    sStage.setScene(telaMatricula);
                    sStage.show();
                	
                     break;
                             
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Pane carregarTelaInicial() throws IOException {
        FXMLLoader loader = new FXMLLoader();

        Pane mainPane = loader.load(
            AVA.class.getResourceAsStream(
                "gui/telas/inicio.fxml"
            )
        );

        TelaInicio telaInicio = loader.getController();

        Navegacao.setTelaInicio(telaInicio);
        Navegacao.carregarPainel("painelInicio");

        return mainPane;
    }

    public static Scene criarCena(Pane mainPane) {
        Scene scene = new Scene(mainPane, 640, 400);

        return scene;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
