package com.template.validator;

public class PrecoValidador implements Validador<Double> {
    private final String nomeCampo;
    private final String valorTexto;
    private Double valorConvertido;

    public PrecoValidador(String nomeCampo, String valorTexto) {
        this.nomeCampo = nomeCampo;
        this.valorTexto = valorTexto;
    }

    public PrecoValidador(String valorTexto) {
        this("Preço", valorTexto);
    }

    @Override
    public boolean validar() {
        if (this.valorTexto == null || this.valorTexto.trim().isEmpty()) {
            return false;
        }

        try {
            String valorFormatado = this.valorTexto.trim().replace(",", ".");
            this.valorConvertido = Double.parseDouble(valorFormatado);

            return this.valorConvertido >= 0;
        } catch (NumberFormatException e) {
            this.valorConvertido = null;
            return false;
        }
    }

    @Override
    public String getMensagemErro() {
        return "O campo " + nomeCampo + " deve conter um valor numérico válido.";
    }

    @Override
    public Double getValor() {
        return this.valorConvertido;
    }
}