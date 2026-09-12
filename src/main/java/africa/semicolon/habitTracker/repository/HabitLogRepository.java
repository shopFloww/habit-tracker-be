package africa.semicolon.habitTracker.repository;


import africa.semicolon.habitTracker.model.HabitLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HabitLogRepository extends JpaRepository<HabitLog, Long> {

    List <HabitLog> findByHabitIdOrderByDateDesc(long habitId);
}
