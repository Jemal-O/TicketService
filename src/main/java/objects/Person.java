package objects;

import java.util.Calendar;

public class Person {

    private String name;
    private String lastName;
    private String patronymicName;
    private Calendar birthDate;

    public Person() {
    }

    public Person(String name, String lastName, String patronymicName, Calendar birthDate) {
        super();
        this.name = name;
        this.lastName = lastName;
        this.patronymicName = patronymicName;
        this.birthDate = birthDate;
    }

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

    public Calendar getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Calendar birthDate) {
        this.birthDate = birthDate;
    }

}
