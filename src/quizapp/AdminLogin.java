/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quizapp;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author ROOTED
 */
public class AdminLogin {
    
    
    Stage stage;
    String UserMail;
    ImageView logoutImg;
    DataBase obj;
  Image i=new Image("background.jpg");
  Image add=new Image("add.png");
  Image remove=new Image("delete.png");
  Image search=new Image("search.png");
  Image addQ=new Image("quesadd.png");
  Image getAll=new Image("getall.png");
  
     ImageView image=new ImageView();
     ImageView add_student=new ImageView();
     ImageView delete_student=new ImageView();
     ImageView search_student=new ImageView();
     ImageView add_question=new ImageView();
     ImageView get_all_student=new ImageView();
   
    public AdminLogin(Stage stage,DataBase obj) {
        this.stage=stage;
        this.obj=obj;
      
    }
    
    Group root=new Group();
        Scene admin=new Scene(root,1600,825,Color.rgb(46,35,62));
        Rectangle rect;
        
        Button login_button=new Button("LogIn");
        TextField email=new TextField();
        PasswordField pass=new PasswordField();
        Text header=new Text("Admin Panel");
        Text email_Tag=new Text("Email : ");
        Text pass_Tag=new Text("Password : ");
       
    public Scene getAdminPanel()
    {
        
        
      
      image.setImage(i);
        add_student.setImage(add);
     delete_student.setImage(remove);
      search_student.setImage(search);
     add_question.setImage(addQ);
     get_all_student.setImage(getAll);
        
        setDimentions();
        actionListener();
         
        
        
       
        
       
        root.getChildren().addAll(image,rect,email,pass,header,email_Tag,pass_Tag,login_button,logoutImg,add_student,delete_student,get_all_student,search_student,add_question);
        
        return admin;
        
        
        
    }
    public void actionListener()
    {
        logoutImg.setOnMouseClicked(event ->
       {
           
             stage.setScene(new LogIn(stage,obj).loginScreen());
       }
       );
       
       login_button.setOnMouseEntered(event ->
        {
           login_button.setStyle("-fx-background-color: MediumSeaGreen");
        });
       login_button.setOnMouseExited(event->
        {
            login_button.setStyle("-fx-background-color: "+Color.BURLYWOOD);
            
        });
       
        login_button.setOnAction(event->
        {
            String Email=email.getText();
            String Pass=pass.getText();
            try{
                 if(obj.verifyAdmin(Email, Pass))
            {
            
            
            
            login_button.setVisible(false);
         email.setVisible(false);
         pass.setVisible(false);
        
        email_Tag.setVisible(false);
        pass_Tag.setVisible(false);
        
        add_student.setVisible(true);
       get_all_student.setVisible(true);
        delete_student.setVisible(true);
       add_question.setVisible(true);
       search_student.setVisible(true);
            
            
            
            }
            else
                 {
                     email.setText("");
                     pass.setText("");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning !!!");
            alert.setHeaderText("Invalid credentials");
            alert.showAndWait();
                 
                 }
                
            }catch( Exception exp)
            {
                System.err.println("Error at line 106 LogIn.java"+exp);
            }
           
            
        }
        );
    }
    private void setDimentions()
    {
        //Rectangle at top
        rect=new Rectangle(admin.getWidth(),100);
        rect.setFill(Color.rgb(46,35,50));
        
        
        //Header
        header.setLayoutX((admin.getWidth()/2));header.setLayoutY((admin.getHeight()/2)-370);
        header.setStyle("-fx-background-color: MediumSeaGreen");
        header.setScaleX(10);header.setScaleY(5.5);
        //Labels
        
        
        email_Tag.setLayoutX((admin.getWidth()/2)-100);email_Tag.setLayoutY((admin.getHeight()/2)-150);
        email_Tag.setStyle("-fx-background-color:"+ Color.BISQUE);
        email_Tag.setScaleX(3);email_Tag.setScaleY(2);
        
        pass_Tag.setLayoutX((admin.getWidth()/2)-140);pass_Tag.setLayoutY((admin.getHeight()/2)-90);
        pass_Tag.setStyle("-fx-background-color:"+ Color.BISQUE);
        pass_Tag.setScaleX(3);pass_Tag.setScaleY(2);
        
       
        
        //TextFields
       
        
        
        email.setLayoutX((admin.getWidth()/2));email.setLayoutY((admin.getHeight()/2)-170);
        email.setStyle("-fx-background-color: MediumSeaGreen");
        
        pass.setLayoutX((admin.getWidth()/2));pass.setLayoutY((admin.getHeight()/2)-110);
        pass.setStyle("-fx-background-color: MediumSeaGreen");
        
       
        
        //Buttons
       
        
        login_button.setLayoutX((admin.getWidth()/2));login_button.setLayoutY((admin.getHeight()/2)-50);
        
        
        add_student.setLayoutX((admin.getWidth()/2)-500);add_student.setLayoutY(300);
        get_all_student.setLayoutX((admin.getWidth()/2));get_all_student.setLayoutY(300);
        delete_student.setLayoutX((admin.getWidth()/2)+100);delete_student.setLayoutY(300);
        add_question.setLayoutX((admin.getWidth()/2)-50);add_question.setLayoutY(400);
        search_student.setLayoutX((admin.getWidth()/2)+50);search_student.setLayoutY(400);
        
        
        
        add_student.setVisible(false);
       get_all_student.setVisible(false);
        delete_student.setVisible(false);
       add_question.setVisible(false);
       search_student.setVisible(false);
          
          
         image.setLayoutX(0);
        image.setLayoutY(10);
        
        
        Image logout_image=new Image("logout.jpg");
        logoutImg=new ImageView(logout_image);
        logoutImg.setLayoutX(admin.getWidth()-240);
        logoutImg.setLayoutY(-60);
        logoutImg.setScaleX(0.1);
        logoutImg.setScaleY(0.1);
        
      
        
        
        
        
        
    }
    
    
}
