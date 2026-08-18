package com.template.model.dao;

import com.template.model.Conexao;
import com.template.model.dto.JogoDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;
import java.util.logging.Level;

public class JogoDAO {
    private static final Logger logger = Logger.getLogger(JogoDAO.class.getName());

    // INCLUDE
    public void cadastrarJogo(JogoDTO jogo) {

        String sql = "INSERT INTO jogos (nome, genero, plataforma, preco) VALUES (?, ?, ?, ?)";

        try (Connection conn = Conexao.obterConexao(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, jogo.getNome());
            stmt.setString(2, jogo.getGenero());
            stmt.setString(3, jogo.getPlataforma());
            stmt.setDouble(4, jogo.getPreco());

            stmt.executeUpdate();

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao inserir", e);
        }
    }

    // READ
    public ArrayList<JogoDTO> listarJogos() {
        // A lista foi movida para DENTRO do método (variável local)
        ArrayList<JogoDTO> listaJogos = new ArrayList<>();

        String sql = "SELECT * FROM jogos";

        try (Connection conn = Conexao.obterConexao(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                JogoDTO jogo = new JogoDTO();
                jogo.setId(rs.getInt("id"));
                jogo.setNome(rs.getString("nome"));
                jogo.setGenero(rs.getString("genero"));
                jogo.setPlataforma(rs.getString("plataforma"));
                jogo.setPreco(rs.getDouble("preco"));

                listaJogos.add(jogo);
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao listar", e);
        }
        return listaJogos;
    }

    // UPDATE
    public void atualizarJogo(JogoDTO jogo) {
        String sql = "UPDATE jogos SET nome=?, genero=?, plataforma=?, preco=? WHERE id=?";

        try (Connection conn = Conexao.obterConexao(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, jogo.getNome());
            stmt.setString(2, jogo.getGenero());
            stmt.setString(3, jogo.getPlataforma());
            stmt.setDouble(4, jogo.getPreco());
            stmt.setInt(5, jogo.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao update", e);
        }
    }

    // DELETE
    public void excluirJogo(int id) {
        String sql = "DELETE FROM jogos WHERE id=?";

        try (Connection conn = Conexao.obterConexao(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao deletar", e);
        }
    }
}