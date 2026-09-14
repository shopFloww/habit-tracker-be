package africa.semicolon.habitTracker.service;

import africa.semicolon.habitTracker.repository.HabitLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HabitStreakService {

    private final HabitLogRepository habitLogRepository;

    public int calculateCurrentStreak(Long habitId) {
        return calculateCurrentStreak(habitId, LocalDate.now());
    }

    int calculateCurrentStreak(Long habitId, LocalDate today) {
        List<LocalDate> logDates = habitLogRepository.findLogDatesByHabitIdOrderByLogDateDesc(habitId);

        if (logDates.isEmpty()) {
            return 0;
        }

        LocalDate mostRecent = logDates.get(0);
        LocalDate yesterday = today.minusDays(1);

        if (mostRecent.isBefore(yesterday)) {
            return 0;
        }

        int streak = 1;
        LocalDate expected = mostRecent.minusDays(1);

        for (int streakCounter = 1; streakCounter < logDates.size(); streakCounter++) {
            LocalDate current = logDates.get(streakCounter);
            if (current.equals(expected)) {
                streak++;
                expected = expected.minusDays(1);
            } else if (current.isBefore(expected)) {
                break;
            }
        }

        return streak;
    }
}