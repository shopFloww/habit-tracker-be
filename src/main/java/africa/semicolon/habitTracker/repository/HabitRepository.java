package africa.semicolon.habitTracker.repository;

import africa.semicolon.habitTracker.model.Habit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HabitRepository extends JpaRepository<Habit, Long> {

    List<Habit> findAllByUser_userId(String userId);
}
