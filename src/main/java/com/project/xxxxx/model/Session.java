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
public class Session {
    private int Id;
    private int IdUser;
    private String Token;
    private String TimeToRelease;
    private Date ExpirationTime;

    @Override
    public String toString() {
        return "Session{" +
                "Id=" + Id +
                ", IdUser=" + IdUser +
                ", Token='" + Token + '\'' +
                ", TimeToRelease='" + TimeToRelease + '\'' +
                ", ExpirationTime=" + ExpirationTime +
                '}';
    }
}
