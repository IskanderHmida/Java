import java.sql.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.*;
import java.util.*;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javafx.scene.layout.*;
import java.sql.*;
import java.io.FileReader;
import java.io.*;
import java.sql.ResultSet;
import java.sql.*;
import java.io.FileOutputStream;

// CustomerInformation class for all customer database manipulation
public class CustomerInformation extends Application
{
private Label message; 
private String firstInput;
private String lastInput; 
private String address1;
private String address2;
private String cityInput;
private String emailInput;
private String titleCombo;
private String stateCombo;
private boolean toggleStatus;
private String dateOfRegistration; 
private String allData;
private String allData1;
private Button returnTo;


//Main method 
public static void main(String[] args) 
{

   



       launch(args);
       
   }
   






   // Start method for UI
   public void start(Stage myStage)
   {
   
   Button newCustomer = new Button("New Customer Call");
   Button viewCustomers = new Button("View All Customers");
   Label helloLabel = new Label("Hello! Please choose an option");
   VBox vBox = new VBox(10,helloLabel,newCustomer,viewCustomers);
   vBox.setAlignment(Pos.CENTER);
   Scene firstScene = new Scene(vBox,300,300);
   
   myStage.setScene(firstScene);
   firstScene.getStylesheets().add("JavaFx1.css");
   myStage.setTitle("Welcome");
   myStage.show();
   
   
  //  setOnAction for when user clicks on new customer button
 newCustomer.setOnAction(new EventHandler<ActionEvent>()
   
    {
@Override
public void handle(ActionEvent e) {

   Label titleLabel = new Label("Title"); 
   ComboBox<String> title = new ComboBox<>();
   
   title.getItems() .addAll("Mr.","Ms.","Mrs.","Other");
   
   Label firstLabel = new Label("First Name: ");
   
   TextField firstText = new TextField(); 
   
   Label lastLabel = new Label( "Last Name: "); 
   
   TextField lastText = new TextField(); 
   
   Label addressLabel = new Label ("Street Address 1: ");
   
   TextField street = new TextField();
   
   Label addressLabel2 = new Label ("Street Address 2: ");
   
   TextField street2 = new TextField();
   
   Label cityLabel = new Label ("City: ");
   
   TextField cityText = new TextField();
   
   Label stateLabel = new Label("Select state: ");
   
   Label dateLabel = new Label(dateOfRegistration);

   ComboBox<String>  states = new ComboBox<>();
   
   states.getItems().addAll("Alabama","Alaska","Arizona","Arkansas","California","Colorado","Connecticut",
   "Delaware","Florida","Georgia","Hawaii","Idaho","Illinois","Indiana","Iowa","Kansas","Kentucky","Louisiana",
   "Maine","Maryland","Massachusetts","Michigan","Minnesota","Mississippi","Missouri","Montana","Nebraska","Nevada",
   "New Hampshire","New Jersey","New Mexico","New York","North Carolina", "North Dakota", "Ohio" ,"Oklahoma","Oregon",
   "Pennsylvania","Rhode Island","South Carolina","South Dakota","Tennessee","Texas","Utah","Vermont","Virginia",
   "Washington","West Virginia","Wisconsin","Wyoming","Other");
   
   Label emailLabel = new Label("Email: "); 
   
   TextField emailText = new TextField(); 
   
   RadioButton active = new RadioButton("Active");
   RadioButton inactive = new RadioButton("Inactive");
   
   ToggleGroup status = new ToggleGroup();
   active.setToggleGroup(status);
   inactive.setToggleGroup(status);
   
   Button submit = new Button("Submit"); 
   returnTo = new Button ("Return to Main Menu");
   message = new Label();
   
   GridPane gridPane = new GridPane();
   
   gridPane.add(titleLabel,4,0); 
   gridPane.add(title,5,0); 
   gridPane.add(firstLabel,4,1);
   gridPane.add(firstText,5,1);
   gridPane.add(lastLabel,4,2);
   gridPane.add(lastText,5,2);
   gridPane.add(addressLabel,4,3);
   gridPane.add(street,5,3);
   gridPane.add(addressLabel2,4,4);
   gridPane.add(street2,5,4);
   gridPane.add(cityLabel,4,5);
   gridPane.add(cityText,5,5);
   gridPane.add(stateLabel,4,6);
   gridPane.add(states,5,6);
   gridPane.add(emailLabel,4,7);
   gridPane.add(emailText,5,7);
   gridPane.add(active,4,9);
   gridPane.add(inactive,5,9);
   gridPane.add(submit,7,10);
   gridPane.add(returnTo,7,11);
   gridPane.add(dateLabel,4,13);
   gridPane.add(message,4,11);
   
   
   
   
   gridPane.setHgap(10);
   gridPane.setVgap(20);
   
   //setOnAction for when user clicks on submit button
   submit.setOnAction(new EventHandler<ActionEvent>() 
   {
@Override
public void handle(ActionEvent e) {


try
{

firstInput = firstText.getText();
lastInput = lastText.getText();
address1 =  street.getText();
address2 = street2.getText();
cityInput = cityText.getText();
emailInput = emailText.getText();
titleCombo = title.getValue(); 
stateCombo = states.getValue();


if (firstInput.isEmpty() || lastInput.isEmpty()||
address1.isEmpty() || 
address2.isEmpty()||cityInput.isEmpty()||emailInput.isEmpty())
{
message.setText("ERROR. Please fill all the boxes");
}
else if (titleCombo == null || stateCombo==null)
{
message.setText("Please select the title and state ");
}

else if (!inactive.isSelected() && !active.isSelected())
{
message.setText("Please select account status");
}

else 
{
message.setText("Thank you");

}

if (active.isSelected())
{
toggleStatus = true; 

}


else if(inactive.isSelected())
{
toggleStatus = false; 

}
// Calling addCustomer method 
addCustomer();
}

catch (Exception y)
{
System.out.println("ERROR: " + y.getMessage());
}

}
});

   
   // Calendar object and format 
   DateFormat df = new SimpleDateFormat("dd/MM/yy");
   Calendar calobj = Calendar.getInstance();
   dateOfRegistration= (df.format(calobj.getTime()));
   
   
   
   // Set scene and stage 
   Scene scene = new Scene(gridPane,700,750);
   scene.getStylesheets().add("JavaFx.css");
   myStage.setScene(scene);
   myStage.setTitle("Customer Information");
   myStage.show();
   
   
  // setOnAction for when user clicks on returnTo button
   returnTo.setOnAction(new EventHandler<ActionEvent>()
   {
   @Override
public void handle(ActionEvent v) {
   // Reroute stage to the main menu 
   myStage.setScene(firstScene);
   firstScene.getStylesheets().add("JavaFx1.css");
   myStage.setTitle("Welcome");
   myStage.show();

}
});
 
   }
   
   });
   
   
   // setOnAction for when user clicks on viewCustomers button
   viewCustomers.setOnAction(new EventHandler<ActionEvent>()
   {
   @Override
public void handle(ActionEvent e) {
viewCustomers();

Button exitButton = new Button("Exit");
Button export = new Button("Export to File");
returnTo = new Button ("Return to Main Menu");

TextArea textArea = new TextArea(allData);
textArea.setWrapText(true);
ToolBar toolbar = new ToolBar();
ToolBar toolbar2 = new ToolBar();
ToolBar toolbar3 = new ToolBar();
BorderPane borderPane = new BorderPane();
borderPane.setPrefSize(200,400);

HBox hBox = new HBox(10,export,exitButton,returnTo);
VBox vBox = new VBox();

BorderPane.setMargin(hBox,new Insets(12,12,12,12));
borderPane.setCenter(textArea);
borderPane.setBottom(hBox);
borderPane.setTop(toolbar);
borderPane.setLeft(toolbar2);
borderPane.setRight(toolbar3);

Scene customersScene = new Scene(borderPane,400,500);

myStage.setScene(customersScene);
customersScene.getStylesheets().add("JavaFx.css");
myStage.setTitle("Customers List");
myStage.show();

// setOnAction for when user clicks on exit button
exitButton.setOnAction(new EventHandler<ActionEvent>()
   {
   @Override
public void handle(ActionEvent e) {
myStage.close();
}
});

// setOnAction for when user clicks on export button
export.setOnAction(new EventHandler<ActionEvent>()
   {
   @Override
public void handle(ActionEvent v) {
exportToFile();
}
});

// setOnAction for when user clicks returnTo
returnTo.setOnAction(new EventHandler<ActionEvent>()
   {
   @Override
public void handle(ActionEvent v) {

myStage.setScene(firstScene);
   firstScene.getStylesheets().add("JavaFx1.css");
   myStage.setTitle("Welcome");
   myStage.show();

}
});


}
});
}




// Method that add customers to the database 
   public void addCustomer()
   {
     final String DB_URL = "jdbc:sqlite:CustomerDB.sqlite";
     int affectedRows;
      
      try
      {
         
         Connection conn = DriverManager.getConnection(DB_URL);

                System.out.println("Success ");
                if (conn !=  null)
         {
         //Get MetaData information 
            DatabaseMetaData meta = conn.getMetaData();
            System.out.printf("Connection to \"%s\" created: Driver is \"%s\", version %s\n", 
               meta.getURL(), 
               meta.getDriverName(), 
               meta.getDriverVersion());
            
            }
            
       // Prepared statement for adding new customers      
      String sql = "Insert into Customers " + "(Title, FirstName, LastName, StreetAddress1, StreetAddress2, City, State, Email, Status, DateOfRegistration) " +   
      "values " + "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
      PreparedStatement prep = conn.prepareStatement(sql);
   
      prep.setString(1, titleCombo);
      prep.setString(2, firstInput);
      prep.setString(3, lastInput);
      prep.setString(4, address1);
      prep.setString(5, address2);
      prep.setString(6, cityInput);
      prep.setString(7, stateCombo);
      prep.setString(8, emailInput);
      prep.setBoolean(9, toggleStatus);
      prep.setString(10, dateOfRegistration);
      
      
      
      affectedRows = prep.executeUpdate();
		System.out.print(affectedRows);
      conn.close();	         
      }
      catch (Exception exx)
      {
         System.out.println("ERROR: " + exx.getMessage());
      }

      }
      
     
     
      
     // Method that will get all customers information from the database 
     public void viewCustomers()
      {
      
   
      final String DB_URL = "jdbc:sqlite:CustomerDB.sqlite";
   
      try
      {
         //Get MetaData information 
         Connection conn = DriverManager.getConnection(DB_URL);
         Statement statement = conn.createStatement();
                System.out.println("Success ");
                if (conn !=  null)
         {
            DatabaseMetaData meta = conn.getMetaData();
            System.out.printf("Connection to \"%s\" created: Driver is \"%s\", version %s\n", 
               meta.getURL(), 
               meta.getDriverName(), 
               meta.getDriverVersion());
            
            
         
      }
      String sql = "SELECT * FROM customers";
      ResultSet results = statement.executeQuery(sql);
      
      allData = "\n";
       while (results.next())
          {
          
         allData += results.getString("Title") + " " +  results.getString("FirstName") + " " + results.getString("LastName") + 
        "  \n Street Address 1: " +  results.getString("StreetAddress1") + "\n Street Address 2:  " + results.getString("StreetAddress2") +
         "\n City: " + results.getString("City") + "\n State: " + results.getString("State") + "\n Account Status: " +
          results.getBoolean("Status") + "\n Date Of Registration: " + results.getString("DateOfRegistration") + " \n \n ";
              }
 
 }

        catch (Exception yy)
      {
         System.out.println("ERROR: " + yy.getMessage());
      }
      }
      
      
      
      
      
      //Method that will export data to a file 
      public void exportToFile(){

        final String DB_URL = "jdbc:sqlite:CustomerDB.sqlite";

 
         try
      {
                  //Get MetaData information    
         Connection conn = DriverManager.getConnection(DB_URL);
         Statement statement = conn.createStatement();
                System.out.println("Success ");
                if (conn !=  null)
         {
            DatabaseMetaData meta = conn.getMetaData();
            System.out.printf("Connection to \"%s\" created: Driver is \"%s\", version %s\n", 
               meta.getURL(), 
               meta.getDriverName(), 
               meta.getDriverVersion());
            
            
         
      }
              String sql = "SELECT * FROM customers";
              ResultSet results = statement.executeQuery(sql);
              
              // Exported data will go to a file called "testout.Csv"
      FileOutputStream fout=new FileOutputStream("testout.Csv");    
               
               // Set columns for Csv file 
               final String columns = "Title ,FirstName ,Last Name ,Street Address 1 ,Street Address 2 ,City ,State ,Email ,Account Status ,Date Of Registration  \n"; 
               
                byte b2[]= columns.getBytes();
               fout.write(b2);    
             

      
              allData1 = " ";
              
      
              while (results.next())
              
          {
          
              allData1 += results.getString("Title") + " ," +  results.getString("FirstName") + ", " + results.getString("LastName") + " ," +
                results.getString("StreetAddress1") + " ,"  + results.getString("StreetAddress2") + " ,"+ 
               results.getString("City") + " ," + results.getString("State") + " ," + results.getString("Email") + " ," +
              results.getBoolean("Status") + " , " + results.getString("DateOfRegistration") + "\n ";
                
          
                   
        
         

        
             }
             byte b[]= allData1.getBytes();
               fout.write(b);    
             fout.close(); 
         
          }
          

               catch (Exception yy)
      {
               System.out.println("ERROR: " + yy.getMessage());
      }


         }
         }
     
     
      
     
      
 


 
 


