package employeeswsinf;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Mariusz Garbiński (29304)
 */
public class EmployeeDetails {
    
    private StringProperty name;
    private StringProperty surname;
    private StringProperty idNumber;
    private StringProperty reward;
    
    public EmployeeDetails(String name, String surname, String idNumber, String reward){
        this.name = new SimpleStringProperty(name);
        this.surname = new SimpleStringProperty(surname);
        this.idNumber = new SimpleStringProperty(idNumber);
        this.reward = new SimpleStringProperty(reward);
    }
    
    //Getters
    public String getName() {
        return name.get();
    }
    public String getSurname() {
        return surname.get();
    }
    public String getIdNumber() {
        return idNumber.get();
    }
    public String getReward() {
        return reward.get();
    }
    
    //Setters
    public void setName(String value) {
        name.set(value);
    }
    public void setSurname(String value) {
        surname.set(value);
    }
    public void setIdNumber(String value) {
        idNumber.set(value);
    }
    public void setReward(String value) {
        reward.set(value);
    }
    
    //Property value
    public StringProperty nameProperty(){
        return name;
    }
    public StringProperty surnameProperty(){
        return surname;
    }
    public StringProperty idNumberProperty(){
        return idNumber;
    }
    public StringProperty rewardProperty(){
        return reward;
    }
}
