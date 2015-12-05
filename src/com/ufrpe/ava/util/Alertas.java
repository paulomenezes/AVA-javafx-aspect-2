package com.ufrpe.ava.util;

import javafx.scene.control.Alert;

/**
 * Created by paulomenezes on 01/12/15.
 */
public class Alertas {
    public static void alerta(String titulo, String mensagem, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(mensagem);

        alert.showAndWait();
    }

    public static void campoInvalido(String mensagem) {
        alerta("Campo Inválido", mensagem, Alert.AlertType.ERROR);
    }

    public static void campoObrigatorio(String mensagem) {
        alerta("Campo Obrigatório", mensagem, Alert.AlertType.INFORMATION);
    }

    public static void falhaCadastro(String nome) {
        alerta("Falha", "Houve uma falha ao inserir o " + nome, Alert.AlertType.ERROR);
    }

    public static void falhaEdicao(String nome) {
        alerta("Falha", "Houve uma falha ao atualizar o " + nome, Alert.AlertType.ERROR);
    }
    
    public static void falhaConexaoBanco(){
    	
    	alerta("Falha","Desculpe ocorreu algo errado,Tente Mais tarde",Alert.AlertType.ERROR);
    }
    
    public static void tabelaVazia(){
    	
    	alerta("Atenção","Nada foi cadastrado até o momento",Alert.AlertType.INFORMATION);
    }
    
    public static void ObjetoNaoExiste(String s){
    	
    	alerta("Atenção",s,Alert.AlertType.INFORMATION);
    }
}
