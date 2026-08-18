package com.template.controller;

import com.template.services.JogoServices;
import com.template.model.dto.JogoDTO;
import com.template.util.DialogUtil;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class MainController {

    @FXML private Button btnSalvar;
    @FXML private Button btnEditar;
    @FXML private Button btnExcluir;
    @FXML private Button btnLimpar;
    @FXML private TextField txtId;
    @FXML private TextField txtNome;
    @FXML private TextField txtGenero;
    @FXML private TextField txtPlataforma;
    @FXML private TextField txtPreco;
    @FXML private Label lblMensagem;
    @FXML private Label lblTotal;
    @FXML private TableView<JogoDTO> tblJogos;
    @FXML private TableColumn<JogoDTO, Integer> colId;
    @FXML private TableColumn<JogoDTO, String> colNome;
    @FXML private TableColumn<JogoDTO, String> colGenero;
    @FXML private TableColumn<JogoDTO, Double> colPreco;
    @FXML private TableColumn<JogoDTO, String> colPlataforma;

    private final JogoServices jogoService = new JogoServices();

    @FXML
    private void carregarJogo() {
        ArrayList<JogoDTO> listaJogos = jogoService.listar();
        tblJogos.setItems(FXCollections.observableArrayList(listaJogos));
        lblTotal.setText("Total de jogos: " + listaJogos.size());
    }

    @FXML
    private void btnSalvarAction(ActionEvent event) {
        try {
            JogoDTO jogoDTO = new JogoDTO();
            jogoDTO.setNome(txtNome.getText());
            jogoDTO.setGenero(txtGenero.getText());
            jogoDTO.setPlataforma(txtPlataforma.getText());
            jogoDTO.setPreco(converterPreco(txtPreco.getText()));

            jogoService.cadastrar(jogoDTO);

            carregarJogo();
            btnLimparAction(event);
            lblMensagem.setText("Jogo cadastrado com sucesso!");
        } catch (IllegalArgumentException e) {
            lblMensagem.setText(e.getMessage());
        }
    }

    @FXML
    private void btnEditarAction(ActionEvent event) {
        try {
            if (txtId.getText() == null || txtId.getText().isEmpty()) {
                throw new IllegalArgumentException("Selecione um jogo na tabela para editar.");
            }

            JogoDTO jogoDTO = new JogoDTO();
            jogoDTO.setId(Integer.parseInt(txtId.getText()));
            jogoDTO.setNome(txtNome.getText());
            jogoDTO.setGenero(txtGenero.getText());
            jogoDTO.setPlataforma(txtPlataforma.getText());
            jogoDTO.setPreco(converterPreco(txtPreco.getText()));

            jogoService.atualizar(jogoDTO);

            carregarJogo();
            btnLimparAction(event);
            lblMensagem.setText("Jogo atualizado com sucesso!");
        } catch (IllegalArgumentException e) {
            lblMensagem.setText(e.getMessage());
        }
    }

    @FXML
    private void btnExcluirAction(ActionEvent event) {
        JogoDTO jogoSelecionado = tblJogos.getSelectionModel().getSelectedItem();

        if (jogoSelecionado == null) {
            lblMensagem.setText("Selecione um jogo para excluir!");
            return;
        }

        if (DialogUtil.confirmacao("Deseja realmente excluir o jogo " + jogoSelecionado.getNome() + "?")) {
            try {
                jogoService.excluir(jogoSelecionado.getId());
                carregarJogo();
                btnLimparAction(event);
                lblMensagem.setText("Jogo excluído com sucesso!");
            } catch (IllegalArgumentException e) {
                lblMensagem.setText(e.getMessage());
            }
        }
    }

    @FXML
    private void carregarCampos() {
        JogoDTO objJogoDTO = tblJogos.getSelectionModel().getSelectedItem();

        if (objJogoDTO != null) {
            txtId.setText(String.valueOf(objJogoDTO.getId()));
            txtNome.setText(objJogoDTO.getNome());
            txtPlataforma.setText(objJogoDTO.getPlataforma());
            txtPreco.setText(String.valueOf(objJogoDTO.getPreco()));
            txtGenero.setText(objJogoDTO.getGenero());
        }
    }

    @FXML
    private void btnLimparAction(ActionEvent event) {
        txtId.clear();
        txtNome.clear();
        txtGenero.clear();
        txtPreco.clear();
        txtPlataforma.clear();
        lblMensagem.setText("");
    }

    @FXML
    private void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colGenero.setCellValueFactory(new PropertyValueFactory<>("genero"));
        colPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));
        colPlataforma.setCellValueFactory(new PropertyValueFactory<>("plataforma"));
    }

    // Auxiliar para tratar conversão de texto para número sem quebrar a tela
    private double converterPreco(String precoTexto) {
        try {
            return Double.parseDouble(precoTexto.replace(",", "."));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("O preço deve ser um valor numérico válido.");
        }
    }
}