package org.example.api.seeders;

import lombok.RequiredArgsConstructor;
import org.example.api.logs.HistoryLog;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/seed/pet")
@RequiredArgsConstructor
public class PetSeederController {
    private final SeederService seederService;

    @PostMapping("/create-pets")
    public String seedPets() {
        seederService.seedPets();
        return "Seeded 10 users successfully!";
    }

    @PostMapping("/buy")
    public HistoryLog buy() {
        return seederService.buy();
    }
}
