package com.template.controller;

import com.template.model.dao.JogoDAO;
import com.template.model.dto.JogoDTO;
import com.template.util.DialogUtil;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

import static com.template.validator.jogoValidator.validarJogo;


public class MainController
{
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

    @FXML
    private void carregarJogo(){
        JogoDAO objJogogoDAO = new JogoDAO();
        ArrayList<JogoDTO> listaJogos = objJogogoDAO.listarJogos();
        tblJogos.setItems(FXCollections.observableArrayList(listaJogos));
        lblTotal.setText("Total de jogos: " + listaJogos.size());
    }

    @FXML
    private void btnSalvarAction(ActionEvent event){

        //validar campo de pesquisa
        if(!validarJogo(txtNome.getText(), txtGenero.getText(), txtPreco.getText())){
            lblMensagem.setText("Preencha todos os campos!");
            return;
        }

        String nome = txtNome.getText();
        String genero = txtGenero.getText();
        String plataforma = txtPlataforma.getText();
        double preco = Double.parseDouble(txtPreco.getText());


        JogoDTO jogoDTO = new JogoDTO();

        jogoDTO.setNome(nome);
        jogoDTO.setGenero(genero);
        jogoDTO.setPlataforma(plataforma);
        jogoDTO.setPreco(preco);

        JogoDAO jogoDAO = new JogoDAO();
        jogoDAO.cadastrarJogo(jogoDTO);
        carregarJogo();
        lblMensagem.setText("");
        btnLimparAction(event);
    }

    @FXML
    private void btnLimparAction(ActionEvent event){
        txtId.clear();
        txtNome.clear();
        txtGenero.clear();
        txtPreco.clear();
        txtPlataforma.clear();
    }

    @FXML
    private void carregarCampos() {
        JogoDTO objJogoDTO = tblJogos.getSelectionModel().getSelectedItem();

        if (objJogoDTO != null){
            txtId.setText(String.valueOf(objJogoDTO.getId()));
            txtNome.setText(objJogoDTO.getNome());
            txtPlataforma.setText(objJogoDTO.getPlataforma());
            txtPreco.setText(String.valueOf(objJogoDTO.getPreco()));
            txtGenero.setText(objJogoDTO.getGenero());
        }
    }

    @FXML
    private void btnExcluirAction(ActionEvent event){

        JogoDTO jogoSelecionado = tblJogos.getSelectionModel().getSelectedItem();

        if (jogoSelecionado == null) {
            lblMensagem.setText("Selecione um jogo para excluir!");
            return;
        }

        if (DialogUtil.confirmacao("Deseja realmente excluir o jogo " + jogoSelecionado.getNome() + "?")) {

            JogoDAO objJogoDAO = new JogoDAO();
            objJogoDAO.excluirJogo(jogoSelecionado.getId());

            carregarJogo();
            btnLimparAction(event);

            lblMensagem.setText("Jogo excluído com sucesso!");
        }
    }

    @FXML
    private void btnEditarAction(ActionEvent event){

        int id = Integer.parseInt(txtId.getText());
        String nome = txtNome.getText();
        String genero = txtGenero.getText();
        double preco = Double.parseDouble(txtPreco.getText());
        String plataforma = txtPlataforma.getText();

        JogoDTO jogoDTO = new JogoDTO();
        jogoDTO.setId(id);
        jogoDTO.setNome(nome);
        jogoDTO.setGenero(genero);
        jogoDTO.setPreco(preco);
        jogoDTO.setPlataforma(plataforma);

        JogoDAO objJogoDAO = new JogoDAO();
        objJogoDAO.atualizarJogo(jogoDTO);
        carregarJogo();
        btnLimparAction(event);
    }

    @FXML
    private void initialize()
    {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colGenero.setCellValueFactory(new PropertyValueFactory<>("genero"));
        colPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));
        colPlataforma.setCellValueFactory(new PropertyValueFactory<>("plataforma"));
    }
}