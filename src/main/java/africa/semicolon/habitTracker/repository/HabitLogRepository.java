package africa.semicolon.habitTracker.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface HabitLogRepository {
    @Query("SELECT h.logDate FROM HabitLog h WHERE h.habitId = :habitId ORDER BY h.logDate DESC")
    List<LocalDate> findLogDatesByHabitIdOrderByLogDateDesc(@Param("habitId") Long habitId);
    }

