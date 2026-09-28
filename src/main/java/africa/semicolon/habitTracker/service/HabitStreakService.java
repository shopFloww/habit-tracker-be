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

    public int calculateCurrentStreak(Long habitId, LocalDate today) {
        List<LocalDate> logDates =
                habitLogRepository.findLogDatesByHabitIdOrderByLogDateDesc(habitId);

        if (logDates.isEmpty() || isStreakBroken(logDates.get(0), today)) {
            return 0;
        }
        return countConsecutiveDays(logDates);
    }

    private boolean isStreakBroken(LocalDate mostRecentLog, LocalDate today) {
        return mostRecentLog.isBefore(today.minusDays(1));
    }

    private int countConsecutiveDays(List<LocalDate> logDates) {
        int streak = 1;
        LocalDate expected = logDates.get(0).minusDays(1);

        for (LocalDate date : logDates.subList(1, logDates.size())) {
            if (date.equals(expected)) {
                streak++;
                expected = expected.minusDays(1);
            } else if (date.isBefore(expected)) {
                break;
            }
        }
        return streak;
    }
}