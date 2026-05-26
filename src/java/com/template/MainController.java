package com.template;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.util.ArrayList;


public class MainController
{
    @FXML private Button btSalvar;
    @FXML private Button btEditar;
    @FXML private Button btExcluir;
    @FXML private Button btAdicionar;
    @FXML private Button btLimpar;
    @FXML private TextField txfId;
    @FXML private TextField txfNome;
    @FXML private TextField txfGenero;
    @FXML private TextField txfPlataforma;
    @FXML private TextField txfPreco;
    @FXML private TableView<JogoDTO> tblJogos;
    @FXML private TableColumn<JogoDTO, Integer> colId;
    @FXML private TableColumn<JogoDTO, String> colNome;
    @FXML private TableColumn<JogoDTO, String> colGenero;
    @FXML private TableColumn<JogoDTO, Double> colPreco;
    @FXML private TableColumn<JogoDTO, String> colPlataforma;

    @FXML
    private void carregarJogo(){
        JogoDAO objJogogoDAO = new JogoDAO();

    }



    @FXML
    private void BtSalvar(ActionEvent event){
        int id = Integer.parseInt(txfId.getText());
        String nome = txfNome.getText();
        String genero = txfGenero.getText();
        double preco = Double.parseDouble(txfPreco.getText());
        String plataforma = txfPlataforma.getText();

    }


    @FXML
    private void btLimpar(ActionEvent event){
        txfId.clear();
        txfNome.clear();
        txfGenero.clear();
        txfPreco.clear();
        txfPlataforma.clear();
    }

    @FXML
    private void btExcluir(ActionEvent event){

    }

    @FXML
    private void btAdicionar(ActionEvent event){

    }

    @FXML
    private void btEditar(ActionEvent event){

    }



    @FXML
    private void initialize()
    {
        System.out.println("FXML loaded successfully!");
    }
}
