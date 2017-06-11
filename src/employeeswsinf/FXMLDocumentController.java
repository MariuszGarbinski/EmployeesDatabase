package employeeswsinf;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Mariusz Garbiński (29304)
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label lblInfo;
    @FXML
    private Button btnAdd;
    private Button btnSaveEmployee, btnDelete, btnSaveChanges, btnRefresh;
    @FXML
    private TableView<EmployeeDetails> lstEmployees;
    @FXML
    private Label label;
    @FXML
    private Label label1;
    @FXML
    private Label label2;
    @FXML
    private Label label21;
    @FXML
    private Label label211;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtSurname;
    @FXML
    private TextField txtIdNumber;
    @FXML
    private TextField txtReward;
    @FXML
    private Label label11;
    @FXML
    private TableColumn<EmployeeDetails, String> columnName;
    @FXML
    private TableColumn<EmployeeDetails, String> columnSurname;
    @FXML
    private TableColumn<EmployeeDetails, String> columnIdNumber;
    @FXML
    private TableColumn<EmployeeDetails, String> columnReward;
    
    private ObservableList<EmployeeDetails>data;
    private DbConnection db;
    
    @FXML
    private void btnAddAction(ActionEvent event) {
        
        txtName.setText("");
        txtSurname.setText("");
        txtIdNumber.setText("");
        txtReward.setText("");
        lblInfo.setText("Pasek informacyjny: Wprowadź dane pracownika");
    }    
    
    @FXML
    private void btnSaveEmployeeAction(ActionEvent event) {
        
        java.sql.PreparedStatement preparedStatement = null;

        try {
            Connection conn = db.Connect();
            String sql = "INSERT INTO workers_tbl (name, surname, IdNumber, Reward)"
                        + " values (?, ?, ?, ?)";
            preparedStatement = conn.prepareStatement(sql);
            
            preparedStatement.setString(1, txtName.getText());
            preparedStatement.setString(2, txtSurname.getText());
            preparedStatement.setString(3, txtIdNumber.getText());
            preparedStatement.setString(4, txtReward.getText());
		    		        		
            preparedStatement.executeUpdate();
            
            txtName.setText("");
            txtSurname.setText("");
            txtIdNumber.setText("");
            txtReward.setText("");
            
            lblInfo.setText("Pasek informacyjny: Zapisano pracownika");
                    
            } catch (SQLException ex) {
                System.err.print("Błąd: " + ex);
            }
            
        try{
            Connection conn = db.Connect();
            data = FXCollections.observableArrayList();
         
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM workers_tbl ORDER BY surname");
            while (rs.next()){
                data.add(new EmployeeDetails(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
            }
        }catch (SQLException ex){
            System.err.print("Błąd: " + ex);
        }
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnSurname.setCellValueFactory(new PropertyValueFactory<>("surname"));
        columnIdNumber.setCellValueFactory(new PropertyValueFactory<>("idNumber"));
        columnReward.setCellValueFactory(new PropertyValueFactory<>("reward"));
        
        lstEmployees.setItems(null);
        lstEmployees.setItems(data);
    }  
    
    @FXML
    private void btnSaveChangesAction(ActionEvent event) {
        
        java.sql.PreparedStatement preparedStatement = null;
        String surname = lstEmployees.getSelectionModel().getSelectedItem().getSurname();
        Connection conn = db.Connect();
            try {
		String sql = "UPDATE workers_tbl SET name = ?, surname = ?, idNumber = ?, reward = ?" 
                              + " WHERE surname = ?";
		preparedStatement = conn.prepareStatement(sql);
		    		        
		preparedStatement.setString(1, txtName.getText());
		preparedStatement.setString(2, txtSurname.getText());
		preparedStatement.setString(3, txtIdNumber.getText());
		preparedStatement.setString(4, txtReward.getText());
                preparedStatement.setString(5, surname);
		    		        		
		preparedStatement.executeUpdate();
		
		lblInfo.setText("Pasek informacyjny: Zapisano zmiany u pracownika " + surname);
                
            } catch (SQLException ex) {
		System.err.print("Błąd: " + ex);
            }
        
        try{
            data = FXCollections.observableArrayList();
         
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM workers_tbl ORDER BY surname");
            while (rs.next()){
                data.add(new EmployeeDetails(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
            }
        }catch (SQLException ex){
            System.err.print("Błąd: " + ex);
        }
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnSurname.setCellValueFactory(new PropertyValueFactory<>("surname"));
        columnIdNumber.setCellValueFactory(new PropertyValueFactory<>("idNumber"));
        columnReward.setCellValueFactory(new PropertyValueFactory<>("reward"));
        
        lstEmployees.setItems(null);
        lstEmployees.setItems(data);
    }   
    
    @FXML
    private void btnDeleteAction(ActionEvent event) {
        
        java.sql.PreparedStatement preparedStatement = null;
        String surname = lstEmployees.getSelectionModel().getSelectedItem().getSurname();
        Connection conn = db.Connect();
        try {
		String sql = "DELETE FROM  workers_tbl WHERE surname = '" + surname + "'";
                preparedStatement = conn.prepareStatement(sql);
		preparedStatement.executeUpdate();
		
                txtName.setText("");
                txtSurname.setText("");
                txtIdNumber.setText("");
                txtReward.setText("");
            
                lblInfo.setText("Pasek informacyjny: Usunięto pracownika!!!");
                
            }catch (SQLException ex) {
		System.err.print("Błąd: " + ex);
            }
            
        try{
            data = FXCollections.observableArrayList();
         
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM workers_tbl ORDER BY surname");
            while (rs.next()){
                data.add(new EmployeeDetails(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
            }
        }catch (SQLException ex){
            System.err.print("Błąd: " + ex);
        }
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnSurname.setCellValueFactory(new PropertyValueFactory<>("surname"));
        columnIdNumber.setCellValueFactory(new PropertyValueFactory<>("idNumber"));
        columnReward.setCellValueFactory(new PropertyValueFactory<>("reward"));
        
        lstEmployees.setItems(null);
        lstEmployees.setItems(data);
    }   
    
    @FXML
    private void btnRefreshAction(ActionEvent event) throws SQLException {
        try{
            Connection conn = db.Connect();
            data = FXCollections.observableArrayList();
         
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM workers_tbl ORDER BY surname");
            while (rs.next()){
                data.add(new EmployeeDetails(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
            }
            lblInfo.setText("Pasek informacyjny: Tabela została odświeżona");
        }catch (SQLException ex){
            System.err.print("Błąd: " + ex);
        }
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnSurname.setCellValueFactory(new PropertyValueFactory<>("surname"));
        columnIdNumber.setCellValueFactory(new PropertyValueFactory<>("idNumber"));
        columnReward.setCellValueFactory(new PropertyValueFactory<>("reward"));
        
        lstEmployees.setItems(null);
        lstEmployees.setItems(data);
    } 
    
    @FXML
    private void lstEmployeesAction(MouseEvent event) {
        
        String surname = lstEmployees.getSelectionModel().getSelectedItem().getSurname();

        try {
            Connection conn = db.Connect();
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM workers_tbl WHERE surname = '" + surname + "'");
                if(rs.next()){
                    txtName.setText(rs.getString(2));
                    txtSurname.setText(rs.getString(3));
                    txtIdNumber.setText(rs.getString(4));
                    txtReward.setText(rs.getString(5));
                    lblInfo.setText("Pasek informacyjny: Wybrano pracownika: " + rs.getString(2) + " " + rs.getString(3));
                }else{
                    txtName.setText("");
                    txtSurname.setText("");
                    txtIdNumber.setText("");
                    txtReward.setText("");
                    lblInfo.setText("Pasek informacyjny: Brak pracownika w bazie!!!");
                }
            } catch (SQLException ex) {
                System.err.print("Błąd: " + ex);
            }
    }  
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        db = new DbConnection();
    } 
}