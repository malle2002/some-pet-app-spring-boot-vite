package org.example.api.users;

import jakarta.persistence.*;
import lombok.Data;
import org.example.api.common.domain.Money;
import org.example.api.pets.PetModel;

import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "users")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "first_name", nullable = false, length = 31)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 31)
    private String lastName;

//    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
//            flags = Pattern.Flag.CASE_INSENSITIVE)
    @Column(name = "email_address", nullable = false, unique = true, length = 255)
    private String emailAddress;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "budget_amount")),
            @AttributeOverride(name = "currencyCode", column = @Column(name = "budget_currency"))
    })
    private Money budget;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PetModel> pets;

    public UserModel(String firstName, String lastName, String emailAddress, Money budget) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.budget = budget;
    }

    public UserModel() {
    }
}
