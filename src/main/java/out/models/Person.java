package out.models;

import javax.validation.constraints.*;

public class Person {

    private int id_person;

    @Pattern(regexp = "[A-Z]\\w+ [A-Z]\\w+ [A-Z]\\w+", message = "Your fio should be in this format: FirstName LastName Patronymic")
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 50, message = "Name should between 2 and 30 characters")
    private String name;

    @Min(value = 1930, message = "Year of birth should be greater than 1930")
    @Max(value = 2022, message = "Year of birth should be less than 2022")
    private int yearOfBirth;

    public Person() {

    }

    public Person(String name, int yearOfBirth) {

        this.name = name;
        this.yearOfBirth = yearOfBirth;

    }

    public int getId_person() {
        return id_person;
    }

    public void setId_person(int id_person) {
        this.id_person = id_person;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }
}
