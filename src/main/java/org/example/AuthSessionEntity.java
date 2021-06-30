package org.example;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "auth_session", schema = "test_hibernate", catalog = "")
public class AuthSessionEntity {
    private String sessionId;
    private Timestamp validUntil;
    private String email;

    public AuthSessionEntity(String sessionId, Timestamp validUntil, String email) {
        this.sessionId = sessionId;
        this.validUntil = validUntil;
        this.email = email;
    }

    public AuthSessionEntity() {

    }

    @Id
    @Column(name = "sessionId")
    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    @Basic
    @Column(name = "validUntil")
    public Timestamp getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(Timestamp validUntil) {
        this.validUntil = validUntil;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AuthSessionEntity that = (AuthSessionEntity) o;

        if (sessionId != null ? !sessionId.equals(that.sessionId) : that.sessionId != null) return false;
        if (validUntil != null ? !validUntil.equals(that.validUntil) : that.validUntil != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = sessionId != null ? sessionId.hashCode() : 0;
        result = 31 * result + (validUntil != null ? validUntil.hashCode() : 0);
        return result;
    }
}
