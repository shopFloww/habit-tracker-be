package africa.semicolon.habitTracker.repository;

import africa.semicolon.habitTracker.model.HabitLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface HabitLogRepository extends JpaRepository<HabitLog, Long> {

    boolean existsByHabitIdAndLogDate(Long habitId, LocalDate logDate);

}
