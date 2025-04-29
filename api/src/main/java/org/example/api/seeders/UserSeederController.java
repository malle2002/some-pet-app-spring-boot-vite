package org.example.api.seeders;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/seed/user")
@RequiredArgsConstructor
public class UserSeederController {
    private final SeederService seederService;

    @PostMapping("/create-users")
    public String seedUsers() {
        seederService.seedUsers();
        return "Seeded 10 users successfully!";
    }
}
