package com.template.validator;

import java.util.ArrayList;
import java.util.List;

import static com.template.util.DialogUtil.showWarning;

public class JogoValidator {
    public static boolean validarJogo (String nome, String genero, String preco, String plataforma){
        List<Validador<?>> validadores = new ArrayList<>();

        validadores.add(new CampoObrigatorioValidador("nome", nome));
        validadores.add(new CampoObrigatorioValidador("genero", genero));
        validadores.add(new CampoObrigatorioValidador("preco", preco));
        validadores.add(new CampoObrigatorioValidador("plataforma", plataforma));

        validadores.add(new PrecoValidador(preco));

        for (Validador<?> validador : validadores){
            if (!validador.validar()){
                showWarning(validador.getMensagemErro());
                return false;
            }
        }
        return true;
    }
}

/*if(nome.isEmpty() || genero.isEmpty() || preco.isEmpty()){
            return false;
        }
        return true;
    }
    public static boolean validarNome (String nome){
        if(nome.isEmpty()){
            return false;
        }
        return true;*/
/*
        if(txtNome.getText().isEmpty() || txtGenero.getText().isEmpty() || txtPreco.getText().isEmpty()) {
            lblMensagem.setText("Preencha todos os campos!");
            return;
        }
 */