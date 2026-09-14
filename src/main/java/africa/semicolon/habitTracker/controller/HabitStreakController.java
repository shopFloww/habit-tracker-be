package africa.semicolon.habitTracker.controller;

import africa.semicolon.habitTracker.service.HabitStreakService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/habits")
@RequiredArgsConstructor
public class HabitStreakController {

    private final HabitStreakService habitStreakService;

    @GetMapping("/{habitId}/streak")
    public ResponseEntity<StreakResponse> getStreak(@PathVariable Long habitId) {
        int streak = habitStreakService.calculateCurrentStreak(habitId);
        return ResponseEntity.ok(new StreakResponse(habitId, streak));
    }

    record StreakResponse(Long habitId, int currentStreak) {}
}