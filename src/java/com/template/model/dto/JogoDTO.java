package com.template.model.dto;

public class JogoDTO {

    //atributos dos jogos
    private int id;
    private String nome;
    private String genero;
    private String plataforma;
    private double preco;

    //getter e setter
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getGenero() {
        return genero;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public double getPreco() {
        return preco;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
}
