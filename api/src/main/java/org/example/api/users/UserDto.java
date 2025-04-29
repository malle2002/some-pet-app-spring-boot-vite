package org.example.api.users;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.example.api.common.domain.Money;

@Data
public class UserDto {
    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String emailAddress;

    @NotBlank
    private Money budget;
}
