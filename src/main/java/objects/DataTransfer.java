package objects;

import java.util.Calendar;

public class DataTransfer {

    private String name;
    private String lastName;
    private String patronymicName;
    private String departCity;
    private String arrivalCity;
    private Calendar departDate;
    private Calendar arrivalDate;
    private Calendar birthDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPatronymicName() {
        return patronymicName;
    }

    public void setPatronymicName(String patronymicName) {
        this.patronymicName = patronymicName;
    }

    public String getDepartCity() {
        return departCity;
    }

    public void setDepartCity(String departCity) {
        this.departCity = departCity;
    }

    public String getArrivalCity() {
        return arrivalCity;
    }

    public void setArrivalCity(String arrivalCity) {
        this.arrivalCity = arrivalCity;
    }

    public Calendar getDepartDate() {
        return departDate;
    }

    public void setDepartDate(Calendar departDate) {
        this.departDate = departDate;
    }

    public Calendar getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Calendar arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public Calendar getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Calendar birthDate) {
        this.birthDate = birthDate;
    }

}
