package com.apboutos.moneytrackapi.model;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Collections;

@Entity
@NoArgsConstructor
@Data
public class User implements UserDetails {

    @Id
    @SequenceGenerator(name = "UserSequence",
            sequenceName = "UserSequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "UserSequence")
    private Long id;
    @Size(max = 45)
    private String username;
    @Size(min = 60, max = 60)
    private String password;
    @Email
    private String email;
    private Timestamp registrationDate;
    private Timestamp lastLogin;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;
    private Boolean locked = false;
    private Boolean enabled = false;

    public User(String username,
                   String password,
                   String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(userRole.name());
        return Collections.singletonList(authority);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public enum UserRole{
        USER,
        ADMIN
    }

    public Boolean confirmProperDatabaseSave(User databaseSavedUser){

        return this.username.equals(databaseSavedUser.getUsername()) &&
                this.password.equals(databaseSavedUser.getPassword()) &&
                this.email.equals(databaseSavedUser.getEmail()) &&
                this.registrationDate.equals(databaseSavedUser.registrationDate) &&
                this.userRole.equals(databaseSavedUser.userRole) &&
                this.locked.equals(databaseSavedUser.locked) &&
                this.enabled.equals(databaseSavedUser.enabled);
    }
}
