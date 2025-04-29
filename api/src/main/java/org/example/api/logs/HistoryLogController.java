package org.example.api.logs;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/logs")
public class HistoryLogController {
    private final HistoryLogService historyLogService;

    public HistoryLogController(final HistoryLogService historyLogService) {
        this.historyLogService = historyLogService;
    }

    @GetMapping("")
    public String getHistoryLogs() {
        return historyLogService.printAll();
    }
}
