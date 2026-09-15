package africa.semicolon.habitTracker.service;

import africa.semicolon.habitTracker.model.Habit;
import africa.semicolon.habitTracker.repository.HabitRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class HabitServiceImpl implements HabitService {

    private final HabitRepository habitRepository;

    public HabitServiceImpl(HabitRepository habitRepository) {
        this.habitRepository = habitRepository;
    }

    @Override
    public Habit createHabit(Habit habit) {
        boolean duplicateExists = habitRepository.existsByDescriptionAndType(
                habit.getDescription(), habit.getType());

        if (duplicateExists) {
            throw new IllegalArgumentException(
                    "A habit with the same description and type already exists");
        }

        return habitRepository.save(habit);
    }

    @Override
    public List<Habit> getAllHabits() {
        return habitRepository.findAll();
    }

    @Override
    public Habit getHabitById(Long id) {
        return habitRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Habit not found with id: " + id));
    }

    @Override
    public Habit updateHabit(Long id, Habit habit) {
        Habit existingHabit = habitRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Habit not found with id: " + id));

        // Full overwrite, as agreed
        existingHabit.setDescription(habit.getDescription());
        existingHabit.setType(habit.getType());

        return habitRepository.save(existingHabit);
    }

    @Override
    public void deleteHabit(Long id) {
        if (!habitRepository.existsById(id)) {
            throw new NoSuchElementException("Habit not found with id: " + id);
        }
        habitRepository.deleteById(id);
    }
}