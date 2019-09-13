package com.project.xxxxx.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int Id;
    private String Username;
    private String Password;
    private int IdPerson;
    private int IdRole;
    private String UserRegister;
    private Date TimeStamp;

    @Override
    public String toString() {
        return "User{" +
                "Id=" + Id +
                ", Username='" + Username + '\'' +
                ", Password='" + Password + '\'' +
                ", IdPerson=" + IdPerson +
                ", IdRole=" + IdRole +
                ", UserRegister='" + UserRegister + '\'' +
                ", TimeStamp=" + TimeStamp +
                '}';
    }
}
