package com.project.cantina.util;

public class Utils {
	
    public static String verificarCampos(String valorAtual, String novoValor) {
        return (novoValor == null || valorAtual != null && valorAtual.equals(novoValor)) ? valorAtual : novoValor;
    }
}
