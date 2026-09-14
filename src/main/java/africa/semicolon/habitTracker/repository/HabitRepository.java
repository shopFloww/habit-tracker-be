package africa.semicolon.habitTracker.repository;

import africa.semicolon.habitTracker.model.Habit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface HabitRepository extends JpaRepository<Habit, Long> {

    Optional<Habit> findById(Long id);
    List<Habit> findAllByUser_id(Long userId);
}
