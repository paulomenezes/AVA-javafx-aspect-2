package com.ufrpe.ava.gui.controladores;

import com.ufrpe.ava.negocio.AvaFachada;
import com.ufrpe.ava.negocio.IAvaFachada;
import com.ufrpe.ava.negocio.entidades.Usuario;

/**
 * Created by paulomenezes on 01/12/15.
 */
public class Tela {
    public IAvaFachada avaFachada = new AvaFachada();
    public static Usuario usuarioAtivo = new Usuario();
}
