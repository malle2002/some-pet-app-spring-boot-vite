package org.example.api.pets;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Optional;

@Getter
@Setter
public class StorePetDto {
    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotBlank
    @Temporal(TemporalType.DATE)
    @PastOrPresent(message = "Date of birth cannot be in the future.")
    private Date dateOfBirth;

    private Optional<Integer> rating;

    public StorePetDto(String name, String description, Date dateOfBirth,  Optional<Integer> rating) {
        this.name = name;
        this.description = description;
        this.dateOfBirth = dateOfBirth;
        this.rating = rating;
    }
}

