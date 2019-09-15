package com.project.xxxxx.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class User implements UserDetails {
    private final int Id;
    private final String Username;
    private final String Password;
    private final int IdPerson;
    private final String UserRegister;
    private final LocalDate TimeStamp;
    private final boolean Active;
    private final Collection<? extends GrantedAuthority> authorities;

    public User(int Id,
                int idPerson,
                Date timeStamp,
                String username,
                String password,
                String role,
                boolean active,
                String userRegister) {
        this.Id = Id;
        this.Username = username;
        this.Password = password;
        this.IdPerson = idPerson;
        this.Active = active;
        this.UserRegister = userRegister;
        this.TimeStamp = timeStamp.toLocalDate();

        List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority(role));

        this.authorities = authorities;
    }

    @Override
    public String toString() {
        return "User{" +
                "Id=" + Id +
                ", Username='" + Username + '\'' +
                ", Password='" + Password + '\'' +
                ", IdPerson=" + IdPerson +
                ", UserRegister='" + UserRegister + '\'' +
                ", TimeStamp=" + TimeStamp +
                ", Active=" + Active +
                ", authorities=" + authorities +
                '}';
    }

    @JsonIgnore
    public int getId() {
        return Id;
    }

    @Override
    public String getUsername() {
        return this.Username;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return this.Password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isEnabled() {
        return this.Active;
    }
}
