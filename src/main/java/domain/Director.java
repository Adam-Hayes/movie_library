package domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Director {
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private List<Film> filmList = new ArrayList<>();

    public Director() {
    }

    public Director(Long id, String firstName, String lastName, LocalDate birthDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " " + birthDate + " " + filmList;
    }
}
