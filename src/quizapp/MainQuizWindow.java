/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quizapp;

import java.awt.FileDialog;
import java.awt.Frame;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import javafx.scene.control.Menu;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class MainQuizWindow {
     Stage stage;
     String email;
     DataBase obj;
     int ques_index=0;
     Rectangle header;
     Button logout,select,submit;
     
     Text question=new Text("How Are You ?");
     TextField answer=new TextField();
     int score=0;
     int x=0;
     int prev_index=0;
     List student_answers=new ArrayList();
     List prev_answers=new ArrayList();
     
    
     ImageView image;
     ImageView welcome;
    
     ImageView logoutImg;
     
     Button next,prev;
     
    List QList=new ArrayList();
            List AList=new ArrayList();
    public MainQuizWindow(Stage stage,DataBase obj,String email) {
        this.stage=stage;
        this.obj=obj;
        this.email=email;
    }
    
    Group root=new Group();
        Scene quizwindow=new Scene(root,1600,825,Color.rgb(46,35,62));
        
       
    public Scene getQuizWindow()
    {
        
        
        Image j=new Image("profileicon.PNG");
        Image selected_img = null;
        //Dynamically Setting gif if Score> 0 then welcome.gif else welcomback.gif  it will be decided by using DB data
        try{
            //calling DB Function using Database class Object obj
            String []data=obj.getData(email);
            if(data.length>0)
            {
                if(Double.parseDouble(data[2])>0)
                {
                     selected_img=new Image("welcomeback.gif");
                }
                else
                {
                    selected_img=new Image("welcome.gif");
                }
                
            }
            else
            {
                selected_img=new Image("welcome.gif");
            }
        }
        catch(Exception e)
        {
            System.err.println("Error at line 83 MainQuizWindow.java");
        }
     
      welcome=new ImageView(selected_img);
        image=new ImageView(j);
        setDimentions();
        actionListener();
        
        
               
        
       
       
        root.getChildren().addAll(header,select,logoutImg,image,next,prev,welcome,question,answer,submit);
        
        return quizwindow;
        
        
        
    }
    private void startQuiz()
    {
        if(ques_index<AList.size() )
        {
        question.setText(QList.get(ques_index).toString());
        
       
        }
        
    }
    private void checkAnswer()
    {
        String ans=answer.getText();
        
        if(prev_answers.size()==ques_index)
        {
            prev_answers.add(ans);
             if((AList.get(ques_index).toString().toLowerCase()).equals(ans.toLowerCase()))
        {
            score++;
        }
             
        JOptionPane.showMessageDialog(null, "Score "+ score);
        }
        
           else
             {
                 if(prev_answers.get(ques_index).toString().equals(AList.get(ques_index).toString()))
                 {
                     
                 }
                 else
                 {
                     if(answer.getText().equalsIgnoreCase(AList.get(ques_index).toString()))
                     {
                         score++;
                     }
                 }
                 
                 
                 
                 
             } 
              
        
       
    }
    private void alertMessage()
    {
        
        
          Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning !!!");
            alert.setHeaderText("Invalid Input");
            alert.setContentText("Select Subject Again ");
            alert.showAndWait();
    }
    public void actionListener()
    {
        //Button Hover Animations
         next.setOnMouseEntered(event ->
        {
           next.setStyle("-fx-background-color: MediumSeaGreen");
        });
       next.setOnMouseExited(event->
        {
           next.setStyle("-fx-background-color: "+Color.BURLYWOOD);
            
        });
       
       
       
         prev.setOnMouseEntered(event ->
        {
          prev.setStyle("-fx-background-color: MediumSeaGreen");
        });
       prev.setOnMouseExited(event->
        {
           prev.setStyle("-fx-background-color: "+Color.BURLYWOOD);
            
        });
       
       
       
        select.setOnMouseEntered(event ->
        {
          select.setStyle("-fx-background-color: MediumSeaGreen");
        });
       select.setOnMouseExited(event->
        {
           select.setStyle("-fx-background-color: "+Color.BURLYWOOD);
            
        });
        
        
        
        
        
        
        
        
        
        
        
       logoutImg.setOnMouseClicked(event ->
       {
            stage.setScene(new LogIn(stage,obj).loginScreen());
       }
       );
      image.setOnMouseClicked(event ->
       {
           
            stage.setScene(new ProfileView(stage,obj,email).getProfile());

       }
       );
      
      next.setOnMouseClicked(event ->
      {
          int check=ques_index+1;
          prev_index++;
          if(check >=AList.size())
          {
              Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning !!!");
            alert.setHeaderText("Click Submit or go Previous");
            alert.setContentText(" ");
            alert.showAndWait();
              
          }
          else
          {
              checkAnswer();
              ques_index++;
              if(prev_answers.size()==ques_index)
              {
             answer.setText("");}
              else
              {
                 // JOptionPane.showMessageDialog(null, prev_answers.size() +"   "+ ques_index);
                  answer.setText(prev_answers.get(ques_index).toString());
              }
              
              
               startQuiz();
          }
         
      }
      
      
      );
      submit.setOnMouseClicked(event ->
      {
          double perc=score*100/AList.size();
          obj.addScore(perc, email);
          stage.setScene(new ProfileView(stage,obj,email).getProfile());
          
      }
      
      
      
      );
      
      
      
       prev.setOnMouseClicked(event ->
      {
          int total_score=ques_index;
           
          if(x==0)
          {
               if(ques_index==0)
          {
                if((AList.get(ques_index).toString()).equalsIgnoreCase(prev_answers.get(ques_index).toString()))
              {
                  System.err.println("matched");
              }
              else
              {
                  if(score >0)
              {
                   score=total_score-1;
              }
              }
          }
         x++;
          }
         
          if(ques_index <=0)
          {
              
              Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning !!!");
            alert.setHeaderText("This is First Question of Quiz");
            alert.setContentText(" ");
            alert.showAndWait();
              
          }
          else{
              int i;
             if(ques_index != 0)
             {
                i= ques_index--; }
              if((AList.get(ques_index).toString()).equalsIgnoreCase(prev_answers.get(ques_index).toString()))
              {
                  System.err.println("matched");
              }
              else
              {
                  if(score >0)
              {
                  score=total_score-1;
              }
              }
              
                 answer.setText(prev_answers.get(ques_index).toString());
            
              
               startQuiz();
          }
         
      }
      
      
      );
      
      
      
      
      
      
      
      
      
      
       select.setOnAction(event ->
       {
           //JOptionPane.showMessageDialog(null, prev_answers.size());
           select.setVisible(false);
           welcome.setVisible(false);
           
           TextInputDialog dialog = new TextInputDialog("");
dialog.setTitle("Quiz App");
dialog.setHeaderText("AOA, Input Subject for Quiz ");
dialog.setContentText("English\nGeneral Knowledge");
// Traditional way to get the response value.
       Optional<String> result = dialog.showAndWait();
       String res=result.get();
       

           new Thread(new Runnable() {
                   @Override
                   public void run() {
                       try
           {
               
          QList=obj.getQuestions(res);
          AList=obj.getAnswers(res);
          
          if(AList.isEmpty() )
          {
              JOptionPane.showMessageDialog(null, "Invalid Subject");
             // alertMessage();
              select.setVisible(true);
               welcome.setVisible(true);
          }
          else
          {
               question.setVisible(true);
                answer.setVisible(true);
                submit.setVisible(true);
              next.setVisible(true);
              prev.setVisible(true);
              
              System.out.print(QList.size());
              startQuiz();
               for(int i=0;i<QList.size();i++)
           {
            
               System.out.print(QList.get(i) +" : ");
               System.out.println(AList.get(i));
                         }
          }
          
          
           }
           catch(Exception exp)
           {
               
               System.err.println("Error at line 111 MainQuizWindow.java "+exp);
           }
                   }
               }).start();
           
           
            
       }
       );
       
    }
    private void setDimentions()
    {
        //header
        header=new Rectangle(quizwindow.getWidth(),100);
        header.setFill(Color.rgb(46,35,50));
        
        //images
         
         image.setLayoutX(quizwindow.getWidth()-100);
        image.setLayoutY(-15);
        image.setScaleX(0.4);
        image.setScaleY(0.4);
        
        
//        
//        welcome.setLayoutX(quizwindow.getWidth());
//        welcome.setLayoutY(quizwindow.getHeight());
        
        
        
        //logout image
         Image logout_image=new Image("logout.jpg");
        logoutImg=new ImageView(logout_image);
        logoutImg.setLayoutX(quizwindow.getWidth()-240);
        logoutImg.setLayoutY(-60);
        logoutImg.setScaleX(0.1);
        logoutImg.setScaleY(0.1);
        
        
        //Buttons
        next=new Button("Next   ");
        next.setLayoutX(quizwindow.getWidth()/2+200);
        next.setLayoutY(600);
        next.setVisible(false);
        
        
        submit=new Button("Submit Quiz ");
        submit.setLayoutX(quizwindow.getWidth()/2);
       submit.setLayoutY(600);
        submit.setVisible(false);
        
        prev=new Button("Previous");
        prev.setLayoutX(quizwindow.getWidth()/2-200);
        prev.setLayoutY(600);
        prev.setVisible(false);
        
        //question field
         question.setLayoutX(quizwindow.getWidth()/2);
        question.setLayoutY(150);
        question.setScaleX(3);question.setScaleY(3);
        question.setVisible(false);
        
        
        
        
        
        
        //answer field
        
        answer.setLayoutX(quizwindow.getWidth()/2);
        answer.setLayoutY(300);
        answer.setScaleX(3);answer.setScaleY(3);
        answer.setVisible(false);
        
        
        //welcome gif
        welcome.setLayoutX(quizwindow.getWidth()/2-200);
        welcome.setLayoutY(quizwindow.getHeight()/2-330);
        welcome.setScaleX(2.5);
        
        
        
        
        
        
        
       
       
         
       
        //Button
        
        select=new Button("Start");
        
        select.setLayoutX(quizwindow.getWidth()/2);
        select.setLayoutY(quizwindow.getHeight()/2+100);
        
        
        
        
       
        
        
        
        
        
    }
    
}
