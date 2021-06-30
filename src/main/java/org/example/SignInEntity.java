package org.example;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SignInEntity {
    @Id
    private String email;
    private String password;

    public SignInEntity(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public SignInEntity() {

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
