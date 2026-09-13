package africa.semicolon.habitTracker.service;

import africa.semicolon.habitTracker.model.Habit;

import java.util.List;

public interface HabitService {

    Habit createHabit(Habit habit);

    List<Habit> getAllHabits();

    Habit getHabitById(Long id);

    Habit updateHabit(Long id, Habit habit);

    void deleteHabit(Long id);
}
