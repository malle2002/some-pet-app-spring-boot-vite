package org.example.api.pets;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;
import org.example.api.common.domain.HelperFunctions;
import org.example.api.common.domain.Money;
import org.example.api.users.UserModel;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "pet_type")
@Table(name = "pets")
public abstract class PetModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID id;

    @ManyToOne
    @Nullable
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private UserModel owner;

    @Column(name = "name", nullable = false, length = 30)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "date_of_birth", nullable = false, updatable = false)
//    @Temporal(TemporalType.DATE)
    @PastOrPresent(message = "Date of birth cannot be in the future.")
    private Date dateOfBirth;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "price_amount")),
            @AttributeOverride(name = "currencyCode", column = @Column(name = "price_currency"))
    })
    private Money price;

    public PetModel(String name, String description, Date dateOfBirth) {
        this.name = name;
        this.description = description;
        this.dateOfBirth = dateOfBirth;
    }

    public PetModel() {
    }

    public void initializePrice() {
        this.price = new Money(this.calculatePrice(), Currency.getInstance("USD"));
    }

    public BigDecimal calculatePrice() {
        int age = HelperFunctions.GetAge(this.getDateOfBirth());
        return BigDecimal.valueOf(age);
    }

    public abstract void printIfBought();
//    @Override
//    public boolean equals(Object o) {
//        if (this == o)
//            return true;
//        if (!(o instanceof PetModel pet))
//            return false;
//        return Objects.equals(this.id, pet.id) && Objects.equals(this.name, pet.name) && Objects.equals(this.dateOfBirth, pet.dateOfBirth);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(this.id, this.name, this.dateOfBirth);
//    }
//
//    @Override
//    public String toString() {
//        return "Pet{" + "id=" + this.id + ", name='" + this.name + '\'' + '}';
//    }
}
