package org.example;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "pet", schema = "test_hibernate")
public class PetEntity implements Serializable {
    @Id
    private int petId;
    @NotBlank
    private String petName;
    private Date birthdate;
    private String breedType;
    private String sex;
    private String color;
    private int userId;

    @Id
    @Column(name = "petID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getPetId() {
        return petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }

    @Basic
    @Column(name = "petName")
    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    @Basic
    @Column(name = "birthdate")
    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthDate) {
        this.birthdate = birthDate;
    }

    @Basic
    @Column(name = "breedType")
    public String getBreedType() {
        return breedType;
    }

    public void setBreedType(String breedType) {
        this.breedType = breedType;
    }

    @Basic
    @Column(name = "sex")
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Basic
    @Column(name = "color")
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Basic
    @Column(name = "userId")
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        PetsEntity that = (PetsEntity) o;
//
//        if (petId != that.petId) return false;
//        if (ownerId != that.ownerId) return false;
//        if (petName != null ? !petName.equals(that.petName) : that.petName != null) return false;
//        if (birthDate != null ? !birthDate.equals(that.birthDate) : that.birthDate != null) return false;
//        if (breedType != null ? !breedType.equals(that.breedType) : that.breedType != null) return false;
//        if (sex != null ? !sex.equals(that.sex) : that.sex != null) return false;
//        if (color != null ? !color.equals(that.color) : that.color != null) return false;
//
//        return true;
//    }

//    @Override
//    public int hashCode() {
//        int result = petId;
//        result = 31 * result + (petName != null ? petName.hashCode() : 0);
//        result = 31 * result + (birthDate != null ? birthDate.hashCode() : 0);
//        result = 31 * result + (breedType != null ? breedType.hashCode() : 0);
//        result = 31 * result + (sex != null ? sex.hashCode() : 0);
//        result = 31 * result + (color != null ? color.hashCode() : 0);
//        result = 31 * result + ownerId;
//        return result;
//    }
//
//    @Override
//    public String toString() {
//        return null;
//    }
}
