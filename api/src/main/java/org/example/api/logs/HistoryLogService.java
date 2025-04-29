package org.example.api.logs;

import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class HistoryLogService {
    private final HistoryLogRepository historyLogRepository;

    public HistoryLogService(HistoryLogRepository historyLogRepository) {
        this.historyLogRepository = historyLogRepository;
    }

    public String printAll() {
        return historyLogRepository.findAll().stream()
                .map(HistoryLog::toString)
                .collect(Collectors.joining("\n"));
    }
}
