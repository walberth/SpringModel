package com.project.xxxxx.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class JwtUserDetails implements UserDetails {
    private final int Id;
    private final String Username;
    private final String Password;
    private final int IdPerson;
    private final String UserRegister;
    private final Date TimeStamp;
    private final Collection<? extends GrantedAuthority> authorities;

    public JwtUserDetails(int Id,
                          int idPerson,
                          Date timeStamp,
                          String username,
                          String password,
                          List<String> roles,
                          String userRegister) {
        this.Id = Id;
        this.Username = username;
        this.Password = password;
        this.IdPerson = idPerson;
        this.UserRegister = userRegister;
        this.TimeStamp = timeStamp;

        List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();

        for(String role : roles){
            authorities.add(new SimpleGrantedAuthority(role));
        }

        this.authorities = authorities;
    }

    @Override
    public String toString() {
        return "JwtUserDetails{" +
                "Id=" + Id +
                ", Username='" + Username + '\'' +
                ", Password='" + Password + '\'' +
                ", IdPerson=" + IdPerson +
                ", UserRegister='" + UserRegister + '\'' +
                ", TimeStamp=" + TimeStamp +
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
        return true;
    }
}
