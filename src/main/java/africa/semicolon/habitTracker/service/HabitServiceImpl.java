package africa.semicolon.habitTracker.service;

import africa.semicolon.habitTracker.dtos.HabitRequestDto;
import africa.semicolon.habitTracker.exception.HabitNotFoundException;
import africa.semicolon.habitTracker.model.Habit;
import africa.semicolon.habitTracker.repository.HabitRepository;
import africa.semicolon.habitTracker.repository.UserRepository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class HabitServiceImpl implements HabitService {

    private final HabitRepository habitRepository;
    private final UserRepository userRepository;

    @Override
    public List<Habit> findAllHabits(String userId) {
        return habitRepository.findAllByUser_userId(userId);
    }

    @Override
    public Habit createHabit(HabitRequestDto habitRequestDto, String userId) {
        Habit habit = Habit.builder()
                .user(userRepository.findById(userId).orElseThrow())
                .name(habitRequestDto.getName())
                .description(habitRequestDto.getDescription())
                .build();

        return habitRepository.save(habit);
    }

    @Override
    public Habit findHabitById(Long id) {
        return habitRepository.findById(id)
                .orElseThrow(() -> new HabitNotFoundException("Habit not found"));
    }
}