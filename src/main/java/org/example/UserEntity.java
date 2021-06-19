package org.example;

import javax.persistence.*;
import java.lang.annotation.Annotation;

@Entity
@Table(name = "user", schema = "test_hibernate")
public class UserEntity implements Entity{
    private int userId;
    private String firstName;
    private String lastName;
    private Byte numberOfPets;

    public UserEntity() {
    }

    public UserEntity(String firstName, String lastName, Byte numberOfPets) {
//        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.numberOfPets = numberOfPets;
    }

    @Id
    @Column(name = "userId")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "firstName")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "lastName")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "numberOfPets")
    public Byte getNumberOfPets() {
        return numberOfPets;
    }

    public void setNumberOfPets(Byte numberOfPets) {
        this.numberOfPets = numberOfPets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (userId != that.userId) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
        if (numberOfPets != null ? !numberOfPets.equals(that.numberOfPets) : that.numberOfPets != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (numberOfPets != null ? numberOfPets.hashCode() : 0);
        return result;
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return null;
    }

    @Override
    public String name() {
        return null;
    }
}
