/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quizapp;

import java.awt.FileDialog;
import java.awt.Frame;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import static javafx.scene.input.DataFormat.URL;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.print.DocFlavor.URL;
import javax.swing.JOptionPane;

/**
 *
 * @author ROOTED
 */

public class ProfileView {
Stage stage;
DataBase obj;

     Rectangle header;
    
     
     
     
    
     ImageView image,pImage;
     ImageView logoutImg;
     
     
     Text name=new Text();Text email=new Text();Text perc=new Text();
     Text name_Tag=new Text("Name : ");
        Text email_Tag=new Text("Email : ");
        Text pass_Tag=new Text("Percentage : ");
        String [] data={"Afzaal","ASKASKASKAJKA","AKJSJNKASNKAJS"};
    public ProfileView(Stage stage,DataBase obj,String email) {
        this.stage=stage;
        this.obj=obj;
        data[0]=email;
         
    }
    
    Group root=new Group();
        Scene profile=new Scene(root,1600,825,Color.rgb(46,35,62));
        
       
    public Scene getProfile()
    {
        try
        {
        
        
        Image j=new Image("blackgrad.jpg");
        Image k=new Image("profileicon.PNG");
        
        image=new ImageView(j);
        pImage=new ImageView(k);
         getInfo();
        setDimentions();
       
        actionListener();
        
        
               
        
       
       
        root.getChildren().addAll(image,pImage,header,logoutImg,name_Tag,pass_Tag,email_Tag,name,email,perc);
        
        return profile;}
        catch(Exception e)
        {
            System.err.println(e);
        }
        
         return profile;
        
    }
    private void getInfo()
    {
       // data=something;
        try
        {
            data=obj.getData(data[0]);
        name.setText(data[0]);
        email.setText(data[1]);
        perc.setText(data[2]+"%");
            
        }
        catch(Exception exp)
           {
               System.err.println("Error at line 106 ProfileView.java "+exp);
           }
        
    }
    public void actionListener()
    {
        
       logoutImg.setOnMouseClicked(event ->
       {
           JOptionPane.showMessageDialog(null, data[1]);
             stage.setScene(new MainQuizWindow(stage,obj,data[1]).getQuizWindow());
       }
       );
      pImage.setOnMouseClicked(event ->
       {
            
    FileDialog dialog = new FileDialog((Frame)null, "Select Profile Picture");
    dialog.setMode(FileDialog.LOAD);
    dialog.setVisible(true);
   
    //String file = dialog.getDirectory();
   String file=dialog.getFile();
    Image selected_img=new Image(file);
    
   if(selected_img.getHeight()<350  &&  selected_img.getHeight()>180)
   {
       if(selected_img.getWidth()>350  &&  selected_img.getWidth()<180)
       {
       Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning !!!");
            alert.setHeaderText("Allowed Size : Max Width 350  Min width : 180 and vice virsa");
            alert.setContentText(" ");
            alert.showAndWait();
       }
       else
   {
       
    pImage.setImage(selected_img);
    changeImage();
       
   }
   }
   else
   {
       Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning !!!");
            alert.setHeaderText("Allowed Size : Max Width 300  Min width : 180 and vice virsa");
            alert.setContentText(" ");
            alert.showAndWait();
   }
    
    
    
    
   // System.out.println(file + " chosen.");

       }
       );
      
//       select.setOnAction(event ->
//       {
//           subject_list.setVisible(false);
//           select.setVisible(false);
//            Alert alert = new Alert(Alert.AlertType.WARNING);
//alert.setTitle("Warning !!!");
//alert.setHeaderText("Invalid credencials");
//alert.showAndWait();
//       }
//       );
       
    }
    private void changeImage()
    {
        pImage.setLayoutX(50);
        pImage.setLayoutY((profile.getHeight()/2)-200);
       
        pImage.maxWidth(300);
        pImage.maxHeight(300);
        
    }
    private void setDimentions()
    {
        
         name_Tag.setLayoutX((profile.getWidth()/2)-100);name_Tag.setLayoutY((profile.getHeight()/2)-150);
        name_Tag.setStyle("-fx-background-color:"+ Color.BISQUE);
        name_Tag.setScaleX(3);name_Tag.setScaleY(2);
        
        email_Tag.setLayoutX((profile.getWidth()/2)-100);email_Tag.setLayoutY((profile.getHeight()/2)-90);
        email_Tag.setStyle("-fx-background-color:"+ Color.BISQUE);
        email_Tag.setScaleX(3);email_Tag.setScaleY(2);
        
        pass_Tag.setLayoutX((profile.getWidth()/2)-140);pass_Tag.setLayoutY((profile.getHeight()/2)-30);
        pass_Tag.setStyle("-fx-background-color:"+ Color.BISQUE);
        pass_Tag.setScaleX(3);pass_Tag.setScaleY(2);
        //header
        header=new Rectangle(profile.getWidth(),100);
        header.setFill(Color.rgb(46,35,50));
        
        //images
         
         image.setLayoutX(0);
        image.setLayoutY(10);
         pImage.setLayoutX(50);
        pImage.setLayoutY(profile.getHeight()/2-200);
         pImage.maxWidth(300);
        pImage.maxHeight(300);
       
        
        
        
        //logout image
         Image logout_image=new Image("logout.jpg");
        logoutImg=new ImageView(logout_image);
        logoutImg.setLayoutX(profile.getWidth()-240);
        logoutImg.setLayoutY(-60);
        logoutImg.setScaleX(0.1);
        logoutImg.setScaleY(0.1);
       
       
        
        name.setLayoutX((profile.getWidth()/2)+150);name.setLayoutY((profile.getHeight()/2)-150);
       name.setStyle("-fx-background-color:"+ Color.BISQUE);
       name.setScaleX(3);name.setScaleY(2);
      
      email.setLayoutX((profile.getWidth()/2)+150);email.setLayoutY((profile.getHeight()/2)-90);
      email.setStyle("-fx-background-color:"+ Color.BISQUE);
      email.setScaleX(3);email.setScaleY(2);
      
      perc.setLayoutX((profile.getWidth()/2)+150);perc.setLayoutY((profile.getHeight()/2)-30);
      perc.setStyle("-fx-background-color:"+ Color.BISQUE);
      perc.setScaleX(3);perc.setScaleY(2);
       
        
        
        
        
       
        
        
        
        
       
        
        
        
        
        
    }
}
