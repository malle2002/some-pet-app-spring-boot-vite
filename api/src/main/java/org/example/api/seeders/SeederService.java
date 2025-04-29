package org.example.api.seeders;

import com.github.javafaker.Faker;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.example.api.common.domain.Money;
import org.example.api.logs.HistoryLog;
import org.example.api.logs.HistoryLogRepository;
import org.example.api.logs.HistoryLogService;
import org.example.api.pets.*;
import org.example.api.users.UserDto;
import org.example.api.users.UserModel;
import org.example.api.users.UserRepository;
import org.example.api.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class SeederService {
    @Autowired
    private final UserService userService;
    @Autowired
    private final PetService petService;
    @Autowired
    private final HistoryLogRepository historyLogRepository;
    private final Faker faker = new Faker();
    private final Random random = new Random();
    private int rangeMin = 10;
    private int rangeMax = 100;

    public void seedUsers() {
        for (int i = 0; i < 10; i++) {
            UserDto user = new UserDto();
            user.setFirstName(faker.name().firstName());
            user.setLastName(faker.name().lastName());
            user.setEmailAddress(faker.internet().emailAddress());
            user.setBudget(new Money(
                    BigDecimal.valueOf(rangeMin + (rangeMax - rangeMin) * random.nextDouble()),
                    Currency.getInstance("USD") // random.nextInt() > 0.5 ? Currency.getInstance("USD") : Currency.getInstance("EUR")
            ));
            userService.save(user);
        }
    }

    public void seedPets() {
        for (int i = 0; i < 20; i++) {
            boolean isDog = random.nextBoolean();

            String name = isDog ? "Doggo" + i : "Kitty" + i;
            String description = isDog ? "A playful dog number " + i : "A cute cat number " + i;
            Date dateOfBirth = randomDate();

            if (isDog) {
                int rating = random.nextInt(11);
                StorePetDto dog = new StorePetDto(name, description, dateOfBirth, Optional.of(rating));
                petService.save(dog, "dog");
            } else {
                StorePetDto cat = new StorePetDto(name, description, dateOfBirth, Optional.empty());
                petService.save(cat, "cat");
            }
        }
    }

    private Date randomDate() {
        long startMillis = new Date(115, 0, 1).getTime();
        long endMillis = System.currentTimeMillis();
        long randomMillisSinceEpoch = startMillis + (long) (random.nextDouble() * (endMillis - startMillis));
        return new Date(randomMillisSinceEpoch);
    }

    @Transactional
    public HistoryLog buy() {
        List<UserModel> users = userService.findAll()
                .stream()
                .sorted(Comparator.comparing((userModel) -> userModel.getBudget().getAmount() )).toList();
        List<PetModel> pets = petService.findByOwnerIsNull()
                .stream()
                .sorted(Comparator.comparing((petModel -> petModel.getPrice().getAmount()))).toList();
        users.forEach(userModel -> System.out.println(userModel.getBudget().getAmount()));
        pets.forEach(petModel -> System.out.println(petModel.getPrice().getAmount()));

        int total = 0;
        int successful = 0;
        for(UserModel userModel : users) {
            for(PetModel petModel : pets) {
                if(userService.buyPet(userModel.getId(),petModel.getId())) {
                    successful++;
                    break;
                }
            }
            total++;
        }

        HistoryLog newHistoryLog = new HistoryLog();
        newHistoryLog.setExecutedAt(LocalDateTime.now());
        newHistoryLog.setFailureCount(total-successful);
        newHistoryLog.setSuccessCount(successful);
        historyLogRepository.save(newHistoryLog);

        return newHistoryLog;
    }
}
