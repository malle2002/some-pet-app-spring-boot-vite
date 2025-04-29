package org.example.api.logs;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
public class HistoryLog {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime executedAt;

    @Column(nullable = false)
    private int successCount;

    @Column(nullable = false)
    private int failureCount;


    @Override
    public String toString() {
        return  executedAt +
                ", " + successCount +
                ", " + failureCount;
    }
}
