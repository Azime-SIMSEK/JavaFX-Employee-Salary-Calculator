package application;
	
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main extends Application {
	
	 private List<Payable> employees = new ArrayList<>();
	 TextField FirstNameinput,LastNameinput,socialSecurityNumberinput,weeklySalaryinput;
	

	 //text fields
	 TextField tfFirstName=new TextField();
	 TextField tfLastName=new TextField();
	 TextField tfSSN=new TextField();
	 
	 TextField tfSearchUpdateSSN=new TextField();
	 TextField tfSALARY=new TextField();
	 TextField tfGrossSales=new TextField();
	 TextField tfCommissionRate=new TextField();
	 TextField tfBaseSalary=new TextField();
	 TextField tfWeeklySalary=new TextField();
	 TextField tfWage=new TextField();
	 TextField tfHours=new TextField();
	 
	 //buttons
	 Button btAdd=new Button("Add");
	 Button btSearchbySSN=new Button("Search by SSN");
	 Button btUpdatebySSN=new Button("Update by SSN");
	 Button btClean=new Button("Clean Text Fields");
	
	 
	 //labels
	 Label lbFirstName=new Label("First Name");
	 Label lbLastName=new Label("Last Name");
	 Label lbSSN=new Label("SSN");
	 Label lbSearchUpdateSSN=new Label("Search/Update SSN");
	 Label lbSALARY=new Label("SALARY");
	 Label lbGrossSales=new Label("Gross Sales");
	 Label lbCommissionRate=new Label("Commission Rate");
	 Label lbBaseSalary=new Label("Base Salary");
	 Label lbWeeklySalary=new Label("Weekly Salary");
	 Label lbWage=new Label("Wage");
	 Label lbHours=new Label("Hours");
	 Label lbChoice=new Label("Choose Employee Type");
	 
	
	
	
	@Override
	public void start(Stage primaryStage) {
		try {
			tfFirstName.setPrefColumnCount(12);
			tfLastName.setPrefColumnCount(12);
			tfSSN.setPrefColumnCount(12);
			tfSearchUpdateSSN.setPrefColumnCount(12);
			tfGrossSales.setPrefColumnCount(12);
			tfCommissionRate.setPrefColumnCount(12);
			tfBaseSalary.setPrefColumnCount(12);
			tfWeeklySalary.setPrefColumnCount(12);
			tfWage.setPrefColumnCount(12);
			tfHours.setPrefColumnCount(12);
			
			ChoiceBox<String> choiceBox= new ChoiceBox<>();
			choiceBox.getItems().addAll("Salaried Employee","Hourly Employee","Commission Employee",
					"Base Plus Commission Employee","None");
			
			GridPane p1 =new GridPane();
			p1.setAlignment(Pos.CENTER);
			p1.setHgap(12);
			p1.setVgap(12);
			
			//left side										//right side
			p1.add(lbFirstName, 0, 1);                      p1.add(lbGrossSales, 3, 1);
			p1.add(tfFirstName, 1, 1);                      p1.add(tfGrossSales, 4, 1);
			
			p1.add(lbLastName, 0, 2);						p1.add(lbCommissionRate, 3, 2);
			p1.add(tfLastName, 1, 2);						p1.add(tfCommissionRate, 4, 2);
			
			p1.add(lbSSN, 0, 3);							p1.add(lbBaseSalary, 3, 3);
			p1.add(tfSSN, 1, 3);							p1.add(tfBaseSalary, 4, 3);
			
			p1.add(lbSearchUpdateSSN, 0, 4);				p1.add(lbWeeklySalary, 3, 4);
			p1.add(tfSearchUpdateSSN, 1, 4);				p1.add(tfWeeklySalary, 4, 4);
						
			p1.add(lbSALARY, 0, 5);							p1.add(lbWage, 3, 5);
			p1.add(tfSALARY, 1, 5);							p1.add(tfWage, 4, 5);
															
															p1.add(lbHours, 3, 6);
															p1.add(tfHours, 4, 6);
		
			
			HBox p2 = new HBox(10);
			p2.setAlignment(Pos.TOP_CENTER);
			p2.getChildren().addAll(lbChoice,choiceBox);
			
			
			
			HBox p3 = new HBox(10);
			p3.getChildren().addAll(btAdd,btSearchbySSN,btUpdatebySSN,btClean);
			p3.setAlignment(Pos.CENTER);
			
			
			
			BorderPane borderpane = new BorderPane();
			borderpane.setCenter(p1);
			borderpane.setTop(p2);
			borderpane.setBottom(p3);
			
			//inner margins
			borderpane.setPadding(new Insets(30,30,30,30));
			
			Scene scene = new Scene(borderpane,800,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("EMPLOYEE SALARY CALCULATOR");
			primaryStage.setScene(scene);
			primaryStage.show();
			
			
			

			
			btAdd.setOnAction(e->{
				
				String employeeType = choiceBox.getValue();
		        Payable employee = createEmployeeFromInput(employeeType);
		        employees.add(employee);
		        WriteToFile();
		        cleanTextFields();
		
			});
			
			
			 btSearchbySSN.setOnAction(e->{
				 
				 String searchSSN = tfSearchUpdateSSN.getText();
				 searchEmployeeBySSN(searchSSN);
				 
			 });
			
			 
			 btUpdatebySSN.setOnAction(e->{
				
				    String searchSSN = tfSearchUpdateSSN.getText();
		            String newFirstName = tfFirstName.getText();
		            String newLastName = tfLastName.getText();
		            double newSalary = Double.parseDouble(tfWeeklySalary.getText());

		            updateEmployeeBySSN(searchSSN, newFirstName, newLastName, newSalary);
			 });
		
			
			btClean.setOnAction(e->{
				
				cleanTextFields();
				
			});
			
		
			choiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
	            setTextFieldStates(newValue);
	        });
			
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	private Payable createEmployeeFromInput(String employeeType) {
        switch (employeeType) {
            case "Salaried Employee":
                return new SalariedEmployee(tfFirstName.getText(),tfLastName.getText(),tfSSN.getText(), Double.parseDouble(tfWeeklySalary.getText()));
            case "Hourly Employee":
                return new HourlyEmployee(tfFirstName.getText(),tfLastName.getText(),tfSSN.getText(),Double.parseDouble(tfWage.getText()),
               	Double.parseDouble(tfHours.getText()));
            case "Commission Employee":
                return new CommissionEmployee(tfFirstName.getText(),tfLastName.getText(),tfSSN.getText(),Double.parseDouble(tfGrossSales.getText()),
                		Double.parseDouble(tfCommissionRate.getText()));
            case "Base Plus Commission Employee":
                return new BasePlusCommissionEmployee(tfFirstName.getText(),tfLastName.getText(),tfSSN.getText(),Double.parseDouble(tfGrossSales.getText()),
                		Double.parseDouble(tfCommissionRate.getText()), Double.parseDouble(tfBaseSalary.getText()));
            default:
                throw new IllegalArgumentException("Invalid employee type: " + employeeType);
        }
    }
	
	private void setTextFieldStates(String employeeType) {

        switch (employeeType) {
            case "Salaried Employee":
                tfFirstName.setDisable(false);					    tfGrossSales.setDisable(true);
                tfLastName.setDisable(false);						tfCommissionRate.setDisable(true);
                tfSSN.setDisable(false);							tfBaseSalary.setDisable(true);
                tfSALARY.setDisable(false);							tfWeeklySalary.setDisable(false);
                													tfWage.setDisable(true);
                													tfHours.setDisable(true);
                break;
            case "Hourly Employee":
                tfFirstName.setDisable(false);						tfGrossSales.setDisable(true);
                tfLastName.setDisable(false);						tfCommissionRate.setDisable(true);
                tfSSN.setDisable(false);							tfBaseSalary.setDisable(true);
                													tfWeeklySalary.setDisable(true);
                													tfWage.setDisable(false);
                													tfHours.setDisable(false);
                break;
            case "Commission Employee":
                tfFirstName.setDisable(false);					    tfGrossSales.setDisable(false);
                tfLastName.setDisable(false);					    tfCommissionRate.setDisable(false);
                tfSSN.setDisable(false);							tfBaseSalary.setDisable(true);
                													tfWeeklySalary.setDisable(true);
                													tfWage.setDisable(true);
                													tfHours.setDisable(true);
                break;
            case "Base Plus Commission Employee":
            	 tfFirstName.setDisable(false);					    tfGrossSales.setDisable(false);
                 tfLastName.setDisable(false);					    tfCommissionRate.setDisable(false);
                 tfSSN.setDisable(false);							tfBaseSalary.setDisable(false);
                 													tfWeeklySalary.setDisable(true);
                 													tfWage.setDisable(true);
                 													tfHours.setDisable(true);
                break;
            default:
                break;
        }
    }
	
	
	private void searchEmployeeBySSN(String searchSSN) {
	    for (Payable employee : employees) {
	        if (employee instanceof SalariedEmployee) {
	        	 SalariedEmployee salariedemp = (SalariedEmployee) employee;
	            if (salariedemp.getSocialSecurityNumber().equals(searchSSN)) {
	                displaySalariedEmployeeInfo(salariedemp);
	                return; 
	            }
	        }
	            else if (employee instanceof HourlyEmployee) {
	            	HourlyEmployee hourlyEmployee = (HourlyEmployee) employee;
	                if (hourlyEmployee.getSocialSecurityNumber().equals(searchSSN)) {

	                	displayHourlyEmployeeInfo(hourlyEmployee);
	                    return; 
	                }
	        }
	            
	            else if (employee instanceof CommissionEmployee) {
	                CommissionEmployee commissionEmployee = (CommissionEmployee) employee;
	                if (commissionEmployee.getSocialSecurityNumber().equals(searchSSN)) {
	                    displayCommissionEmployeeInfo(commissionEmployee);
	                    return; 
	                }
	        }
	            
	            else if (employee instanceof BasePlusCommissionEmployee) {
	            	BasePlusCommissionEmployee basecommissionEmployee = (BasePlusCommissionEmployee) employee;
	                if (basecommissionEmployee.getSocialSecurityNumber().equals(searchSSN)) {
	                    displayBasePlusCommissionEmployeeInfo(basecommissionEmployee);
	                    return;
	                }
	        }
	 
	}
	    System.out.println("Employee is not found");
	}

	private void displaySalariedEmployeeInfo(SalariedEmployee employee) {
		
		 String NameInfo = employee.getFirstName();
         String LastNameInfo =  employee.getLastName();
         String SSNInfo =  employee.getSocialSecurityNumber();
         double weeklySalaryInfo = employee.getWeeklySalary();
         
	    tfFirstName.setText(NameInfo);
	    tfLastName.setText(LastNameInfo);
	    tfSSN.setText(SSNInfo);
	    tfWeeklySalary.setText(String.valueOf(weeklySalaryInfo));

	}
	
	 private void displayCommissionEmployeeInfo(CommissionEmployee employee) {
		 String NameInfo = employee.getFirstName();
         String LastNameInfo =  employee.getLastName();
         String SSNInfo =  employee.getSocialSecurityNumber();
         double grossSalesInfo = employee.getGrossSales();
         double commissionRateInfo = employee.getCommissionRate();
         
        tfFirstName.setText(NameInfo);
 	    tfLastName.setText(LastNameInfo);
 	    tfSSN.setText(SSNInfo);
 	    tfGrossSales.setText(String.valueOf(grossSalesInfo));
 	    tfCommissionRate.setText(String.valueOf(commissionRateInfo));
	 }
	
	 private void displayHourlyEmployeeInfo(HourlyEmployee employee) {
		 String NameInfo = employee.getFirstName();
         String LastNameInfo =  employee.getLastName();
         String SSNInfo =  employee.getSocialSecurityNumber();
         double wageInfo = employee.getWage();
         double hoursInfo = employee.getHours();
         
        tfFirstName.setText(NameInfo);
 	    tfLastName.setText(LastNameInfo);
 	    tfSSN.setText(SSNInfo);
 	    tfWage.setText(String.valueOf(wageInfo));
 	    tfHours.setText(String.valueOf(hoursInfo));
	 }
	 
	 private void  displayBasePlusCommissionEmployeeInfo(BasePlusCommissionEmployee employee) {
		 String NameInfo = employee.getFirstName();
         String LastNameInfo =  employee.getLastName();
         String SSNInfo =  employee.getSocialSecurityNumber();
         double grossSalesInfo = employee.getGrossSales();
         double commissionRateInfo = employee.getCommissionRate();
         double baseSalaryInfo = employee.getBaseSalary();
         
        tfFirstName.setText(NameInfo);
 	    tfLastName.setText(LastNameInfo);
 	    tfSSN.setText(SSNInfo);
 	    tfGrossSales.setText(String.valueOf(grossSalesInfo));
 	    tfCommissionRate.setText(String.valueOf(commissionRateInfo));
 	    tfBaseSalary.setText(String.valueOf(baseSalaryInfo));
	 }
	 
	 
	 private void updateEmployeeBySSN(String searchSSN, String newFirstName, String newLastName, double newSalary) {
		    for (Payable employee : employees) {
		        if (employee instanceof SalariedEmployee) {
		            SalariedEmployee salariedEmp = (SalariedEmployee) employee;
		            if (salariedEmp.getSocialSecurityNumber().equals(searchSSN)) {
		             
		                salariedEmp.setFirstName(newFirstName);
		                salariedEmp.setLastName(newLastName);
		                salariedEmp.setWeeklySalary(newSalary);

		               
		                WriteToFile(); 

		                System.out.println("Employee's informations is updated.");
		                return; 
		            }
		        }
		        
		    }

		    System.out.println("Employee is not found.");
		}
	 

	public void cleanTextFields()
	{
		tfFirstName.clear();
		tfLastName.clear();
		tfSSN.clear();
		tfSearchUpdateSSN.clear();
		tfSALARY.clear();
	    tfGrossSales.clear();
	    tfCommissionRate.clear();
		tfBaseSalary.clear();
		tfWeeklySalary.clear();
		tfWage.clear();
		tfHours.clear();
	
	}
	
	
	private void WriteToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("file.txt"))) {
            for (Payable employee : employees) {
                writer.write(employee.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }	
	
	
	
	public static void main(String[] args) {
		launch(args);
	}

}
