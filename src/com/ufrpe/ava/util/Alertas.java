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

    public static void sucesso(String mensagem) {
        alerta("Solicitação registrada com sucesso.", mensagem, Alert.AlertType.CONFIRMATION);
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
    	
    	alerta("Falha","Desculpe ocorreu algo de errado na Conexão,Tente Mais tarde",Alert.AlertType.ERROR);
    }
    
    public static void falhaCredencialBanco(){
    	
    	alerta("Falha","Desculpe ocorreu algo de errado na Conexão \n Preencha o Root e senha corretas do seu Banco \n "
    			+ "Na Classe ConexaoMySQL",Alert.AlertType.ERROR);
    }
    
    public static void tabelaVazia(){
    	
    	alerta("Atenção","Nada foi cadastrado até o momento",Alert.AlertType.INFORMATION);
    }
    
    public static void ObjetoNaoExiste(String s){
    	
    	alerta("Atenção",s,Alert.AlertType.INFORMATION);
    }
    
    public static void ObjetoJaExiste(String s){
    	
    	alerta("Atenção",s,Alert.AlertType.INFORMATION);
    }
    
    public static void selecaoOfertasIndevido(){
    	
    	alerta("Atenção","Selecione no minimo 1 Ofertas ou \n no Máximo 06 Ofertas",Alert.AlertType.WARNING);
    }
    
    public static void selecaoVazia(String s){
    	
    	alerta("Atenção",s,Alert.AlertType.INFORMATION);
    }
    
    public static void naoPodeSerExcluido(){
    	
    	alerta("Atenção","Não pode ser Excluido",Alert.AlertType.INFORMATION);
    }
}
