package com.apboutos.moneytrackapi.model;


import lombok.*;
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
@Getter
@Setter
public class User implements UserDetails {

    @Id
    @Email
    @Size(max = 100)
    private String username;
    @Size(min = 60, max = 60)
    private String password;
    private Timestamp registrationDate;
    private Timestamp lastLogin;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;
    private Boolean locked = false;
    private Boolean enabled = false;

    @ManyToMany
    @JoinTable(name = "user_categories", joinColumns = @JoinColumn(name = "username"), inverseJoinColumns = {@JoinColumn(name = "name"),@JoinColumn(name = "type")})
    private Set<Category> categories;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(userRole.name());
        return Collections.singletonList(authority);
    }

    /*
        We cannot use lombok default implementation of equals and hashcode because lombok uses the list of categories
        inside the hashcode implementation, and this creates a stackoverflow error due to recursive call of hashcode
        between User and Category.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(registrationDate, user.registrationDate) && Objects.equals(lastLogin, user.lastLogin) && userRole == user.userRole && Objects.equals(locked, user.locked) && Objects.equals(enabled, user.enabled);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, registrationDate, lastLogin, userRole, locked, enabled);
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
