package org.example.api.pets;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.util.Date;

@Entity
@DiscriminatorValue("CAT")
public class Cat extends PetModel {
    public Cat(String name, String description, Date dateOfBirth) {
        super(name, description, dateOfBirth);
    }

    public Cat() {

    }

    @Override
    public void printIfBought() {
        System.out.println(
                "Meow, cat " + this.getName() + " has owner " + this.getOwner().getFirstName() + this.getOwner().getLastName());
    }
}