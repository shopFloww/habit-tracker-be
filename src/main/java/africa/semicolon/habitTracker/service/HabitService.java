package africa.semicolon.habitTracker.service;

import africa.semicolon.habitTracker.model.HabitLog;

import java.util.List;

public interface HabitService {

    List<HabitLog> getHabitLogs(Long habitId);
}
