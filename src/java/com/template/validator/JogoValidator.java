package com.template.validator;

import java.util.ArrayList;
import java.util.List;

import static com.template.util.DialogUtil.showWarning;

public class JogoValidator  implements  IJogoValidator{
    public boolean validarJogo (String nome, String genero, String preco, String plataforma){
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