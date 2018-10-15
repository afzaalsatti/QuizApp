package quizapp;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Alert;

public class DataBase {
Statement stmt;
  boolean check=true;
Connection con;
ResultSet result;
    public DataBase() {
        try{
            result=null;
             Class.forName("oracle.jdbc.driver.OracleDriver");
           
          con=DriverManager.getConnection(  
"jdbc:oracle:thin:@localhost:1521:xe","system","AFZAAL");
           stmt = con.createStatement() ;
        }catch(Exception exp)
        {
             System.err.println(exp);
        }
     
           createRecord();
           
           setQuestions();
     
        
        
    }
    private void createRecord()
    {
         new Thread(() -> {
          String create_query="create Table STUDENTS  "+
                "("
                + "name varchar2(20) not null ,"
                + "email varchar2(35) not null ,"
                + "pass varchar2(18) not null ,"
                +"perc number not null ,"
                + "Primary Key(email)"+
                ")";
        String create_questions_query="create Table QUIZCONTENT  "+
                "("
                + "no Number not null ,"
                + "sub varchar2(40) not null ,"
                + "ques varchar2(180) not null ,"
                + "answer varchar2(60) not null ,"
                
                + "Primary Key(no,ques)"+
                ")";
        
        try{
            
        stmt.execute(create_query);
        stmt.execute(create_questions_query);
        setQuestions();
        }
        catch(Exception exp)
        {
            check=false;
            System.err.println(exp);
        }
        }).start();
        
    }
    
    public void setData(String name,String email,String pass)
            
    {
        new Thread(() -> {
            int init_perc=0;
            try{
                stmt.executeUpdate(  "INSERT INTO STUDENTS " +  "(name, email, pass, perc)"
                        + " VALUES ('" +   name + "', '" + email + "', '" + pass + "', "  + init_perc + ")" );
                
            }
            catch(SQLException exp)
            {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning !!!");
                alert.setHeaderText("Invalid Input");
                alert.setContentText("Email must be unique this already exits");
                alert.showAndWait();
                System.out.println(exp);
            }}).start();
       
       
    }
    private void setQuestions() 
    {
        new Thread(() -> {
            if(check)
            {
                int i=0;
                String []quesENG={
                    "Which of these sentences is correct? \n  A) The milkman has just been. \n B) The milkman have just been." ,
                    "Which of these sentences is correct? \n A) The pen do not work.\n B) The pen does not work",
                    "Which of these sentences is correct? \n  A) We were watching television last night.\n  B) We was watching television last night ",
                    "Which of these sentences is correct?  \n A) The car goes really well.\n B) The car go really well",
                    "Which of these sentences is correct? \n A) There were a loud noise. \n B) There was a loud noise",
                    " Which is the correct word to complete this sentence? \n The shop ... having a closing-down sale next week ",
                    "The cats ... being chased by two large dogs. ",
                    "My grandfather _____ the crossword every week ",
                    "The children _____ to school every day",
                    "There _____ many horses in the field ",
                    "Is this question correct? \n  Who do the accounts here? (yes/no) ",
                    "Is this question correct? \n  Who was at the party? (yes/no)  ",
                    "Is this question correct? \n Where do Sally keep the cellotape? (yes/no)",
                    "Is this sentence correct? \n We was not to know about the fire alarm.(yes/no)",
                    "Is this sentence correct? \n  There were a lot of people at the fair.(yes/no)",
                    "Is this sentence correct? \n  Ann and Clive do some babysitting sometimes.(yes/no)"
                        
                };
                
                String []ansENG= {
                    "A",
                    "B",
                    "A",
                    "A",
                    "B",
                    "is",
                    "were",
                    "does",
                    "walk",
                    "are",
                    "no",
                    "yes",
                    "no",
                    "no",
                    "no",
                    "yes"
                };
                
                
                
                String []quesGK={
                    "Which Dutch painter cut off part of his ear?",
                    "In which country is the Great Barrier Reef?",
                    "What color is chlorophyll? ",
                    "What is the capital of Thailand?",
                    "How many points are scored for a touchdown in American football? ",
                    "Which soft green egg-shaped fruit comes from New Zealand? ",
                    "Who was the youngest Beatle? ",
                    "What language do the people of Brazil speak? ",
                    "Who lives at number four, Privet Drive? ",
                    "In which city is the cathedral of Nôtre Dame?",
                    "What color is the middle stripe of the French flag?",
                    "Who wrote a famous diary while hiding from the Nazis in Amsterdam?",
                    "Who directed, Jaws, Raiders of the Lost Ark, and ET?",
                    "Who was the leading actress in Sleepless in Seattle and You’ve Got Mail?",
                    "What is the family name of JFK?",
                    "How many players are there in a baseball team....?",
                    "What is the national sport of Japan?",
                    "In which country was Buddha born?",
                    " What nationality was Picasso?",
                    "Whose nose grew longer every time he told a lie?",
                    " In which galaxy do we live? ",
                    "In which year did the Titanic sink?",
                    " Who painted The Scream? "
                };
                
                
                String []ansGK= {
                    "Vincent van Gogh",
                    "Australia",
                    "Green",
                    "Bangkok",
                    "Six",
                    "The Kiwi fruit",
                    "George Harrison",
                    "Portuguese",
                    "Harry Potter",
                    "Paris",
                    "White",
                    "Anne Frank",
                    "Steven Spielberg",
                    "Meg Ryan",
                    "Kennedy",
                    
                    "Nine",
                    "Sumo wrestling",
                    "Nepal",
                    "Spanish",
                    "Pinocchio",
                    "The Milky Way",
                    "1912",
                    "Edvard Munch"
                };
                for(int j=0;j<quesGK.length;j++)
                {
                    String subject="General Knowledge";
                    
                    
                    
                    
                    
                    
                    
                    
                    try{
                        stmt.executeUpdate(  "INSERT INTO QUIZCONTENT " +  "(no, sub, ques, answer)"
                                + " VALUES (" +   j+ ", '" + subject+ "', '" + quesGK[j] + "', '"  + ansGK[j] + "')" );
                    }
                    catch(SQLException exp)
                    {
                        System.err.println(exp);
                    }
                    
                    i=j;
                }
                for(int k=0;k<quesENG.length;k++)
                {
                    String Subject="English";
                    try{
                        stmt.executeUpdate(  "INSERT INTO QUIZCONTENT " +  "(no, sub, ques, answer)"
                                + " VALUES (" +   i+ ", '" + Subject+ "', '" + quesENG[k] + "', '"  + ansENG[k] + "')" );
                    }
                    catch(Exception exp)
                    {
                        System.err.println(exp);
                    }
                    i++;
                }}
        }).start();
        

    }
   
    public boolean verify(String email,String pass) throws SQLException
    {
         String query="Select * from STUDENTS where lower(email) ="+ "lower('"+email+"') and pass="+"'"+pass+"'";
         result=stmt.executeQuery(query);
         System.out.println(result);
         if(result.next()==false)
         {
             result.close();
             return false;
         }
       
        
            return true;
        
        
        
       
    }
    
    
    public boolean verifyAdmin(String email,String pass) throws SQLException
    {
         String query="Select * from Admins where lower(email) ="+ "lower('"+email+"') and pass="+"'"+pass+"'";
         result=stmt.executeQuery(query);
         System.out.println(result);
         if(result.next()==false)
         {
             result.close();
             return false;
         }
       
        
            return true;
        
        
        
       
    }
    public List getQuestions(String subject) throws SQLException
    {
        
        String query="Select * from QUIZCONTENT where lower(sub)=lower( '"+subject+"')";
         List QList=new ArrayList();
         result=stmt.executeQuery(query);
        
        
        int i=0;
        while(result.next())
        {
            QList.add(result.getString("ques"));
            
            
            
            
            
            
        }

       
        return QList;
    }
     public List getAnswers(String subject) throws SQLException
    {
         List AList=new ArrayList();
        String query="Select * from QUIZCONTENT where lower(sub)=lower( '"+subject+"')";
         
        result=stmt.executeQuery(query);
        
        while(result.next())
        {
            AList.add(result.getString("answer"));
            
            
            
            
             
        }
        return AList;
    }
     public void addScore(double perc,String email)
     {
          try
        {
        stmt.executeUpdate(  "UPDATE STUDENTS SET perc ="+ perc+ "where lower(email) =lower('"+email+"')" );
        }catch(Exception exp)
        {
            System.err.println(exp);
        }
         
     }
    public String[] getData(String email) throws SQLException
    {
        
        String query="Select * from STUDENTS where lower(email) ="+ "lower('"+email+"')";
         result=stmt.executeQuery(query);
         System.out.println(result);
         if(result.next()==false)
         {
             String data[] = null;
             result.close();
             return data;
         }
       
         else{     
             String [] data={"","",""};
             data[0]=result.getString("name");
             data[1]=result.getString("email");
              data[2]=result.getString("perc");
             
             
        
        System.out.println(result.getString("name"));
        System.out.println(result.getString("email"));
         System.out.println(result.getString("perc"));
         return data;
         }
        
        
    }
    
    
    public void  deleteStudent(String email)
    {
        try
        {
        stmt.executeUpdate(  "DELETE FROM STUDENTS " +  "WHERE lower(email) = lower('"+email+"')" );
        }catch(Exception exp)
        {
            System.err.println(exp);
        }
    }
    
    
    public void addQuestion(String sub,String ques,String ans)
     {
         try{
                      stmt.executeUpdate(  "INSERT INTO QUESTIONANSWER " +  "(no, sub, ques, answer)"
               + " VALUES (" +   40+ ", '" + sub+ "', '" + ques + "', '"  + ans + "')" ); 
                 }
                 catch(Exception exp)
                 {
                     System.err.println(exp);
                 }
         
     }
}
