package africa.semicolon.habitTracker.controller;

import africa.semicolon.habitTracker.model.HabitLog;
import africa.semicolon.habitTracker.service.HabitLogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/habits")
public class HabitController {

    private final HabitLogService habitLogService;

    public HabitController(HabitLogService habitLogService) {
        this.habitLogService = habitLogService;
    }

    @PostMapping("/{habitId}/logs")
    public ResponseEntity<HabitLog> logHabit(
            @PathVariable Long habitId
    ) {

        HabitLog habitLog = habitLogService.logHabit(habitId);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(habitLog);
    }
}