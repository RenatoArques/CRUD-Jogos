package com.template.services;

import com.template.model.dao.JogoDAO;
import com.template.model.dto.JogoDTO;

import java.util.ArrayList;

public class JogoServices {

    private final JogoDAO jogoDAO = new JogoDAO();

    public ArrayList<JogoDTO> listar() {
        return jogoDAO.listarJogos();
    }

    public void cadastrar(JogoDTO jogo) {
        validarJogo(jogo);
        jogoDAO.cadastrarJogo(jogo);
    }

    public void atualizar(JogoDTO jogo) {
        if (jogo == null || jogo.getId() <= 0) {
            throw new IllegalArgumentException("ID inválido para atualização.");
        }
        validarJogo(jogo);
        jogoDAO.atualizarJogo(jogo);
    }

    public void excluir(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Selecione um jogo válido para excluir.");
        }
        jogoDAO.excluirJogo(id);
    }


    private void validarJogo(JogoDTO jogo) {
        if (jogo == null) {
            throw new IllegalArgumentException("Os dados do jogo não podem ser nulos.");
        }
        if (jogo.getNome() == null || jogo.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Informe o nome do jogo.");
        }
        if (jogo.getGenero() == null || jogo.getGenero().trim().isEmpty()) {
            throw new IllegalArgumentException("Informe o gênero do jogo.");
        }
        if (jogo.getPlataforma() == null || jogo.getPlataforma().trim().isEmpty()) {
            throw new IllegalArgumentException("Informe a plataforma do jogo.");
        }
        if (jogo.getPreco() < 0) {
            throw new IllegalArgumentException("O preço do jogo não pode ser negativo.");
        }
    }
}