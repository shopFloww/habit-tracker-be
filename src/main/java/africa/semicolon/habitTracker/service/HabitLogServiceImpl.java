package africa.semicolon.habitTracker.service;

import africa.semicolon.habitTracker.model.Habit;
import africa.semicolon.habitTracker.model.HabitLog;
import africa.semicolon.habitTracker.repository.HabitLogRepository;
import africa.semicolon.habitTracker.repository.HabitRepository;
import africa.semicolon.habitTracker.exception.HabitAlreadyLoggedException;
import africa.semicolon.habitTracker.exception.HabitNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class HabitLogServiceImpl implements HabitLogService {

    private final HabitRepository habitRepository;
    private final HabitLogRepository habitLogRepository;

    public HabitLogServiceImpl(HabitRepository habitRepository, HabitLogRepository habitLogRepository) {
        this.habitRepository = habitRepository;
        this.habitLogRepository = habitLogRepository;
    }

    public HabitLog logCompletion(Long habitId) {
        Habit habit = habitRepository.findById(habitId)
                .orElseThrow(() -> new HabitNotFoundException("Habit with ID " + habitId + " not found"));

        LocalDate today = LocalDate.now();

        boolean alreadyLogged = habitLogRepository.existsByHabitIdAndLogDate(habitId, today);
        if (alreadyLogged) {
            throw new HabitAlreadyLoggedException("Habit has already been logged for today");
        }

        HabitLog log = new HabitLog();
        log.setHabit(habit);
        log.setLogDate(today);
        log.setCompleted(true);

        return habitLogRepository.save(log);
    }
}