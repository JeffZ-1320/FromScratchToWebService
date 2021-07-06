package org.example;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "breed_type", schema = "test_hibernate", catalog = "")
public class BreedTypeEntity {
    private String breedType;

    @Id
    @Column(name = "breedType")
    public String getBreedType() {
        return breedType;
    }

    public void setBreedType(String breedType) {
        this.breedType = breedType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BreedTypeEntity that = (BreedTypeEntity) o;

        if (breedType != null ? !breedType.equals(that.breedType) : that.breedType != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return breedType != null ? breedType.hashCode() : 0;
    }
}
