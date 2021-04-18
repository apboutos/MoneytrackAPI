package com.apboutos.moneytrackapi.model;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.*;

import static com.apboutos.moneytrackapi.model.User.UserRolePermission.*;

@Entity
@NoArgsConstructor
@Data
public class User implements UserDetails {

    @Id
    @Email
    @Size(max = 100)
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

    public Boolean confirmProperDatabaseSave(User databaseSavedUser){

        return this.username.equals(databaseSavedUser.getUsername()) &&
                this.password.equals(databaseSavedUser.getPassword()) &&
                this.email.equals(databaseSavedUser.getEmail()) &&
                this.registrationDate.equals(databaseSavedUser.registrationDate) &&
                this.userRole.equals(databaseSavedUser.userRole) &&
                this.locked.equals(databaseSavedUser.locked) &&
                this.enabled.equals(databaseSavedUser.enabled);
    }

    @RequiredArgsConstructor
    public enum UserRole{
        USER(new HashSet<>(Arrays.asList(USER_READ,ENTRY_READ,ENTRY_WRITE,CATEGORY_READ,CATEGORY_WRITE))),
        ADMIN(new HashSet<>(Arrays.asList(USER_READ,USER_WRITE,ENTRY_READ,ENTRY_WRITE,CATEGORY_READ,CATEGORY_WRITE)));

        @Getter
        private final Set<UserRolePermission> permissions;

    }

    public enum UserRolePermission{
        USER_WRITE,
        USER_READ,
        ENTRY_WRITE,
        ENTRY_READ,
        CATEGORY_WRITE,
        CATEGORY_READ
    }

}
