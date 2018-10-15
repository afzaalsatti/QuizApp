package quizapp;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.Reflection;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class SignUp {
Stage stage;
DataBase obj;
 Image i=new Image("background.jpg");
     ImageView image=new ImageView();

    public SignUp(Stage stage,DataBase obj) {
        
        this.stage=stage;
        this.obj=obj;
    }
    
    Group root=new Group();
        Scene signup=new Scene(root,1600,825,Color.rgb(46,35,62));
        Rectangle rect;
        Button signup_button=new Button("SignUp");
        Button login_button=new Button("LogIn");
        TextField name=new TextField();
        TextField email=new TextField();
        PasswordField pass=new PasswordField();
        PasswordField pass_check=new PasswordField();
        Text header=new Text("Signup");
        Text name_Tag=new Text("Name : ");
        Text email_Tag=new Text("Email : ");
        Text pass_Tag=new Text("Password : ");
        Text confirn_pass_Tag=new Text("Confirm Password : ");
    public Scene signUpScreen()
    {
        
        image.setImage(i);
        
        
        setDimentions();
        actionListener();
         
        
        
       
        
       
        root.getChildren().addAll(image,rect,name,email,pass,pass_check,header,name_Tag,email_Tag,confirn_pass_Tag,pass_Tag,signup_button,login_button);
       
        return signup;
        
        
        
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
       login_button.setOnAction(event->
        {
            stage.setScene(new LogIn(stage,obj).loginScreen());
            
            
        }
        );
       signup_button.setOnAction(event->
        {
            String name=this.name.getText();
            String email=this.email.getText();
            String pass=this.pass.getText();
            //pass_check.getText();
            try{
             if((name.length()>5  && email.length()>12) && (pass.length()>=8))
            {
                if((pass.equals(pass_check.getText())))
                {
                obj.setData(name, email, pass);
                 stage.setScene(new LogIn(stage,obj).loginScreen());}
                else
            {
                 Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning !!!");
            alert.setHeaderText("Invalid Input");
            alert.setContentText("Password length must be greater than 8 \n Name length must be greater than 5 ");
            alert.showAndWait();
            }
            
            }
            else
            {
                 Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning !!!");
            alert.setHeaderText("Invalid Input");
            alert.setContentText("Password length must be greater than 8 \n Name length must be grater than 5 ");
            alert.showAndWait();
            }   
            }
            catch(Exception exp)
            {
                Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning !!!");
            alert.setHeaderText("Invalid Input");
            alert.setContentText("Email must be unique this already exits");
            alert.showAndWait();
                
            }
            
            
           
            
            
        }
        );
    }
    private void setDimentions()
    {
        //Rectangle
        rect=new Rectangle(signup.getWidth(),100);
        rect.setFill(Color.rgb(46,35,50));
        
        
        //Header
    

        
        
        
        
        
        
        header.setLayoutX((signup.getWidth()/2));header.setLayoutY((signup.getHeight()/2)-370);
        header.setStyle("-fx-background-color: MediumSeaGreen");
        header.setCache(true);

header.setFill(Color.CADETBLUE);
header.setFont(Font.font(null, FontWeight.BOLD, 30));
 
Reflection r = new Reflection();
r.setFraction(0.7f);
 
header.setEffect(r);
 

       // header.setScaleX(10);header.setScaleY(5.5);
        //Labels
        name_Tag.setLayoutX((signup.getWidth()/2)-100);name_Tag.setLayoutY((signup.getHeight()/2)-150);
        name_Tag.setStyle("-fx-background-color:"+ Color.BISQUE);
        name_Tag.setScaleX(3);name_Tag.setScaleY(2);
        
        email_Tag.setLayoutX((signup.getWidth()/2)-100);email_Tag.setLayoutY((signup.getHeight()/2)-90);
        email_Tag.setStyle("-fx-background-color:"+ Color.BISQUE);
        email_Tag.setScaleX(3);email_Tag.setScaleY(2);
        
        pass_Tag.setLayoutX((signup.getWidth()/2)-140);pass_Tag.setLayoutY((signup.getHeight()/2)-30);
        pass_Tag.setStyle("-fx-background-color:"+ Color.BISQUE);
        pass_Tag.setScaleX(3);pass_Tag.setScaleY(2);
        
       confirn_pass_Tag.setLayoutX((signup.getWidth()/2)-230);confirn_pass_Tag.setLayoutY((signup.getHeight()/2)+30);
        confirn_pass_Tag.setStyle("-fx-background-color:"+ Color.BISQUE);
        confirn_pass_Tag.setScaleX(3);confirn_pass_Tag.setScaleY(2);
        
        //TextFields
        name.setLayoutX((signup.getWidth()/2));name.setLayoutY((signup.getHeight()/2)-170);
        name.setStyle("-fx-background-color: MediumSeaGreen");
        
        
        email.setLayoutX((signup.getWidth()/2));email.setLayoutY((signup.getHeight()/2)-110);
        email.setStyle("-fx-background-color: MediumSeaGreen");
        
        pass.setLayoutX((signup.getWidth()/2));pass.setLayoutY((signup.getHeight()/2)-50);
        pass.setStyle("-fx-background-color: MediumSeaGreen");
        
        pass_check.setLayoutX((signup.getWidth()/2));pass_check.setLayoutY((signup.getHeight()/2)+10);
        pass_check.setStyle("-fx-background-color: MediumSeaGreen");
        
        //Buttons
        signup_button.setLayoutX((signup.getWidth()/2));signup_button.setLayoutY((signup.getHeight()/2)+60);
        
   
   
   
        
        
        login_button.setLayoutX((signup.getWidth()/2)+80);login_button.setLayoutY((signup.getHeight()/2)+60);
        
         image.setLayoutX(0);
        image.setLayoutY(10);
        
    }
    
    
}
