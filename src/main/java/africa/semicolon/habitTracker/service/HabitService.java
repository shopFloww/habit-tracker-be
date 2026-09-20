package africa.semicolon.habitTracker.service;

import africa.semicolon.habitTracker.dtos.HabitRequestDto;
import africa.semicolon.habitTracker.model.Habit;

import java.util.List;

public interface HabitService {
    List<Habit> findAllHabits(Long userId);

    Habit createHabit(HabitRequestDto habitRequestDto, Long userId);

    Habit findHabitById(Long id);
}
