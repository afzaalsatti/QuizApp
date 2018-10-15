/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quizapp;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author ROOTED
 */
public class QuizApp extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        DataBase obj=new DataBase();
         SignUp signup=new SignUp(primaryStage,obj);
         
         
        primaryStage.centerOnScreen();
        primaryStage.setResizable(true);
        
        primaryStage.setTitle("QuizApp");
         primaryStage.getIcons().add(new Image("icon.jpg"));
        primaryStage.setScene(signup.signUpScreen());
        
       
        primaryStage.show();
        
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
