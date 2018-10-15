/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quizapp;

import java.util.Date;
import java.util.Properties;
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
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;


public class LogIn {

    Stage stage;
    String UserMail;
    DataBase obj;
  Image i=new Image("background.jpg");
     ImageView image=new ImageView();
     Text admin=new Text("Log In as Admin");
     Text forget=new Text("forgot Password");
   
    public LogIn(Stage stage,DataBase obj) {
        this.stage=stage;
        this.obj=obj;
      
    }
    
    Group root=new Group();
        Scene login=new Scene(root,1600,825,Color.rgb(46,35,62));
        Rectangle rect;
        Button signup_button=new Button("SignUp");
        Button login_button=new Button("LogIn");
        TextField email=new TextField();
        PasswordField pass=new PasswordField();
        Text header=new Text("LogIn");
        Text email_Tag=new Text("Email : ");
        Text pass_Tag=new Text("Password : ");
       
    public Scene loginScreen()
    {
        
        
      
      image.setImage(i);
        
        setDimentions();
        actionListener();
         
        
        
       
        
       
        root.getChildren().addAll(image,rect,email,pass,header,email_Tag,pass_Tag,signup_button,login_button,admin,forget);
        
        return login;
        
        
        
    }
    private void sendRecoveryMail()
    {
              new Thread(new Runnable() {
               @Override
               public void run() {
                   try{
            String host="smtp.gmail.com";
    String user="your mail";
    String pass="Password of your company email";
    String to=" Email to which you want to send pass";
    String from="from";
    String subject="Password Recovery";
    String text="Use This PassWord to Login Your Account";
    Boolean Debug=false;
    Properties prop=System.getProperties();
    prop.put("mail.smtp.starttls.enable", true);
    prop.put("mail.smtp.host", host);
    prop.put("mail.smtp.port", 587);
    prop.put("mail.smtp.auth", true);
    prop.put("mail.smtp.starttls.required", true);
    java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            Session mailSession = Session.getDefaultInstance(prop, null);
            mailSession.setDebug(Debug);
            Message msg = new MimeMessage(mailSession);
            msg.setFrom(new InternetAddress(from));
            InternetAddress[] address = {new InternetAddress(to)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(subject); msg.setSentDate(new Date());
            msg.setText(text);

           Transport transport=mailSession.getTransport("smtp");
           transport.connect(host, user, pass);
           transport.sendMessage(msg, msg.getAllRecipients());
           transport.close();
           System.out.println("message send successfully");
            
        }
        catch(MessagingException exc)
        {
            System.out.println(exc);
        }
               }
           }).start();
            
        
        
        
        
        
        
    }
    public void actionListener()
    {
        signup_button.setOnMouseEntered(event ->
        {
           signup_button.setStyle("-fx-background-color: MediumSeaGreen");
        });
       signup_button.setOnMouseExited(event->
        {
            signup_button.setStyle("-fx-background-color: "+Color.BURLYWOOD);
            
        });
       login_button.setOnMouseEntered(event ->
        {
           login_button.setStyle("-fx-background-color: MediumSeaGreen");
        });
       login_button.setOnMouseExited(event->
        {
            login_button.setStyle("-fx-background-color: "+Color.BURLYWOOD);
            
        });
       signup_button.setOnAction(event->
        {
            stage.setScene(new SignUp(stage,obj).signUpScreen());
            
        }
        );
       admin.setOnMouseClicked(event ->
       
       {
          stage.setScene(new AdminLogin(stage,obj).getAdminPanel());
           
       });
       forget.setOnMouseClicked(event ->
       
       {
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Suggestion");
            alert.setHeaderText("I used java Mail to send Recovery Pass");
            alert.setContentText("");
            alert.showAndWait();
            
      //sendRecoveryMail();
            
           
       });
        login_button.setOnAction(event->
        {
            String Email=email.getText();
            String Pass=pass.getText();
            try{
                 if(obj.verify(Email, Pass))
            {stage.setScene(new MainQuizWindow(stage,obj,Email).getQuizWindow());}
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
        rect=new Rectangle(login.getWidth(),100);
        rect.setFill(Color.rgb(46,35,50));
        
        
        //Header
        header.setLayoutX((login.getWidth()/2));header.setLayoutY((login.getHeight()/2)-370);
        header.setStyle("-fx-background-color: MediumSeaGreen");
        header.setScaleX(10);header.setScaleY(5.5);
        //Labels
        
        
        email_Tag.setLayoutX((login.getWidth()/2)-100);email_Tag.setLayoutY((login.getHeight()/2)-150);
        email_Tag.setStyle("-fx-background-color:"+ Color.BISQUE);
        email_Tag.setScaleX(3);email_Tag.setScaleY(2);
        
        pass_Tag.setLayoutX((login.getWidth()/2)-140);pass_Tag.setLayoutY((login.getHeight()/2)-90);
        pass_Tag.setStyle("-fx-background-color:"+ Color.BISQUE);
        pass_Tag.setScaleX(3);pass_Tag.setScaleY(2);
        
       
        
        //TextFields
       
        
        
        email.setLayoutX((login.getWidth()/2));email.setLayoutY((login.getHeight()/2)-170);
        email.setStyle("-fx-background-color: MediumSeaGreen");
        
        pass.setLayoutX((login.getWidth()/2));pass.setLayoutY((login.getHeight()/2)-110);
        pass.setStyle("-fx-background-color: MediumSeaGreen");
        
       
        
        //Buttons
        signup_button.setLayoutX((login.getWidth()/2)+80);signup_button.setLayoutY((login.getHeight()/2)-50);
        
        login_button.setLayoutX((login.getWidth()/2));login_button.setLayoutY((login.getHeight()/2)-50);
        forget.setLayoutX((login.getWidth()/2)+15);forget.setLayoutY((login.getHeight()/2)+5);
        admin.setLayoutX((login.getWidth()/2)+15);admin.setLayoutY((login.getHeight()/2)+35);
        
        
          
         image.setLayoutX(0);
        image.setLayoutY(10);
        
      
        
        
        
        
        
    }
    
    
}
