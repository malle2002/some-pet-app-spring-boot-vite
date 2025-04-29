package org.example.api.pets;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.api.common.domain.Money;

import java.util.Date;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
public class UpdatePetDto {
    private UUID owner;

    private String name;

    private String description;

    @Temporal(TemporalType.DATE)
    @PastOrPresent(message = "Date of birth cannot be in the future.")
    private Date dateOfBirth;

    private Money price;
}

