package africa.semicolon.habitTracker.service;

import africa.semicolon.habitTracker.dtos.HabitRequestDto;
import africa.semicolon.habitTracker.model.Habit;

import java.util.List;
import java.util.UUID;

public interface HabitService {

    List<Habit> findAllHabits(Long userId);

    Habit createHabit(HabitRequestDto habitRequestDto, Long userId);

    Habit findHabitById(Long id);
import africa.semicolon.habitTracker.model.Habit;

import java.util.List;

public interface HabitService {

    Habit createHabit(Habit habit);

    List<Habit> getAllHabits();

    Habit getHabitById(Long id);

    Habit updateHabit(Long id, Habit habit);

    void deleteHabit(Long id);
}
