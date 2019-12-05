package com.project.springmodel.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    @Id
    private Long Id;
    private String FirstName;
    private String FatherName;
    private String MotherName;
    private int Sex;
    private LocalDate BirthDate;
    private String Document;
    private String Email;
    private String Mobile;
    private String Telephone;
    private String UserRegister;
    private LocalDateTime TimeStamp;

    @Override
    public String toString() {
        return "Person{" +
                "Id=" + Id +
                ", FirstName='" + FirstName + '\'' +
                ", FatherLastName='" + FatherName + '\'' +
                ", MotherLastName='" + MotherName + '\'' +
                ", Sex=" + Sex +
                ", BirthDate=" + BirthDate +
                ", Document='" + Document + '\'' +
                ", Email='" + Email + '\'' +
                ", Mobile='" + Mobile + '\'' +
                ", Telephone='" + Telephone + '\'' +
                ", UserRegister='" + UserRegister + '\'' +
                ", TimeStamp=" + TimeStamp +
                '}';
    }
}
