package africa.semicolon.habitTracker.repository;

import africa.semicolon.habitTracker.model.Habit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HabitRepository extends JpaRepository<Habit, Long> {
}
