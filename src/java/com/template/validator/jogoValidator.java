package com.template.validator;

public class jogoValidator {
    public static boolean validarJogo (String nome, String genero, String preco){
        if(nome.isEmpty() || genero.isEmpty() || preco.isEmpty()){
            return false;
        }
        return true;
    }
    public static boolean validarNome (String nome){
        if(nome.isEmpty()){
            return false;
        }
        return true;
    }
}
/*if(txtNome.getText().isEmpty() || txtGenero.getText().isEmpty() || txtPreco.getText().isEmpty()) {
            lblMensagem.setText("Preencha todos os campos!");
            return;
        }

 */