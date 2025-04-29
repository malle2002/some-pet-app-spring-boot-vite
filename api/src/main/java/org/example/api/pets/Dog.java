package org.example.api.pets;

import lombok.Getter;
import lombok.Setter;
import org.example.api.common.domain.HelperFunctions;
import org.example.api.common.domain.Money;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Entity
@DiscriminatorValue("DOG")
public class Dog extends PetModel {
    @Column(name = "rating", updatable = true)
//    @Range(min = 0, max = 10)
    private Integer rating;

    public Dog(String name, String description, Date dateOfBirth, int rating) {
        super(name, description, dateOfBirth);
        this.rating = rating;
    }

    public Dog() {

    }

    @Override
    public BigDecimal calculatePrice() {
        int age = HelperFunctions.GetAge(this.getDateOfBirth());
        return BigDecimal.valueOf(age+this.getRating());
    }

    @Override
    public void printIfBought() {
        System.out.println(
                "Woof, dog " + this.getName() + " has owner " + this.getOwner().getFirstName() + this.getOwner().getLastName());
    }
}
