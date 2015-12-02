package com.ufrpe.ava.util;

import com.ufrpe.ava.gui.controladores.TelaInicio;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class Navegacao {
    private static TelaInicio telaInicio;

    public static void setTelaInicio(TelaInicio telaInicio) {
        Navegacao.telaInicio = telaInicio;
    }

    public static void carregarPainel(String fxml) {
        try {
            telaInicio.setVista(FXMLLoader.load(Navegacao.class.getResource("../gui/telas/" + fxml + ".fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}