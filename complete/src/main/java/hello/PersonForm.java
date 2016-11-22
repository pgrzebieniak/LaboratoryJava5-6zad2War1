package hello;

import javax.print.DocFlavor;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PersonForm {

    @NotNull
    @Size(min = 3, max = 25)
    private String name;

    @NotNull
    @Size(min = 3, max = 25)
    private String lastName;

   String posiotion="student";

    @NotNull
    @Min(17)
    @Max(25)
    private Integer age;

    @NotNull
    private String nationality;


    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPosition() {
        return this.posiotion;
    }

    public void setPosition(String position) {
        this.posiotion = position;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getNationality() {
        return this.nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }


    public String toString() {
        return "Person(Name: " + this.name +", LastName: " + this.lastName +   ", Age: " + this.age + ")";
    }
}
