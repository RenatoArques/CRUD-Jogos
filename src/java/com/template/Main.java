package com.template;

import com.template.controller.MainController;
import com.template.validator.IJogoValidator;
import com.template.validator.JogoValidator;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application
{
    @Override
    public void start(Stage stage) throws Exception
    {
        IJogoValidator jogoValidator = new JogoValidator();

        FXMLLoader loader = new FXMLLoader(Main.class.getResource("main.fxml"));

        loader.setControllerFactory(controllerClass -> {
            if(controllerClass == MainController.class){
                return new MainController(jogoValidator);
            }
            try{
                return controllerClass.newInstance();
            } catch (Exception e){
                throw new RuntimeException(e);
            }
        });

        Scene scene = new Scene(loader.load(),657,541);

        stage.setTitle("CRUD JOGOS");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args)
    {
        launch();
    }
}