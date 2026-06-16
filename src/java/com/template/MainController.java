package com.template;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;


public class MainController
{
    @FXML private Button btnSalvar;
    @FXML private Button btnEditar;
    @FXML private Button btnExcluir;
    @FXML private Button btnLimpar;
    @FXML private TextField txfId;
    @FXML private TextField txfNome;
    @FXML private TextField txfGenero;
    @FXML private TextField txfPlataforma;
    @FXML private TextField txfPreco;
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

        if(txfNome.getText().isEmpty() || txfGenero.getText().isEmpty() || txfPreco.getText().isEmpty()) {
            lblMensagem.setText("Preencha todos os campos!");
            return;
        }

        String nome = txfNome.getText();
        String genero = txfGenero.getText();
        String plataforma = txfPlataforma.getText();
        double preco = Double.parseDouble(txfPreco.getText());
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
        txfId.clear();
        txfNome.clear();
        txfGenero.clear();
        txfPreco.clear();
        txfPlataforma.clear();
    }

    @FXML
    private void carregarCampos() {
        JogoDTO objJogoDTO = tblJogos.getSelectionModel().getSelectedItem();

        if (objJogoDTO != null){
            txfId.setText(String.valueOf(objJogoDTO.getId()));
            txfNome.setText(objJogoDTO.getNome());
            txfPlataforma.setText(objJogoDTO.getPlataforma());
            txfPreco.setText(String.valueOf(objJogoDTO.getPreco()));
            txfGenero.setText(objJogoDTO.getGenero());
        }
    }

    @FXML
    private void btnExcluirAction(ActionEvent event){

        JogoDTO jogoSelecionado = tblJogos.getSelectionModel().getSelectedItem();

        if (jogoSelecionado == null) {
            lblMensagem.setText("Selecione um jogo para excluir!");
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmacao");
        alert.setHeaderText("Excluir jogo");
        alert.setContentText("Deseja realmente excluir o jogo " + jogoSelecionado.getNome() + "?");

        if(alert.showAndWait().get() == ButtonType.OK){

            JogoDAO objJogoDAO = new JogoDAO();
            objJogoDAO.excluirJogo(jogoSelecionado.getId());

            carregarJogo();
            btnLimparAction(event);

            lblMensagem.setText("Jogo excluido com sucesso!");
        }
    }

    @FXML
    private void btnEditarAction(ActionEvent event){

        int id = Integer.parseInt(txfId.getText());
        String nome = txfNome.getText();
        String genero = txfGenero.getText();
        double preco = Double.parseDouble(txfPreco.getText());
        String plataforma = txfPlataforma.getText();

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